package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmStore implements Store, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(HbmStore.class);
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf;

    private HbmStore() {
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final Store INST = new HbmStore();
    }

    public static Store instance() {
        return Lazy.INST;
    }

    @Override
    public Collection<Item> findAllItems() {
        return this.tx(session -> session.createQuery(
                "select distinct i from Item i join fetch i.categories"
            ).list());
    }

    @Override
    public Collection<Item> findFilterItems(boolean isDone) {
        return this.tx(session -> {
                            Query query = session.createQuery(
                                    "select distinct i from Item i "
                                            + "join fetch i.categories where i.done = :param"
                                    );
                            query.setParameter("param", isDone);
                            return query.list();
                            }
                    );
    }

    @Override
    public void addItem(Item item) {
        this.makeTransaction(session -> session.save(item));
    }

    @Override
    public void removeItem(int id) {
        this.makeTransaction(session -> {
                                          Item item = new Item();
                                          item.setId(id);
                                          session.delete(item);
                                        });
    }

    @Override
    public void updateItem(int id, Item item) {
        this.makeTransaction(session -> {
                                        item.setId(id);
                                        session.update(item);
                                        });
    }

    @Override
    public Item findItemById(int id) {
        return this.tx(session -> session.get(Item.class, id));
    }

    @Override
    public void changeStatusAndUpdate(int id, boolean isDone) {
        this.makeTransaction(session -> {
            Query query = session.createQuery(
                    "update ru.job4j.model.Item set done = :bool where id = :param"
            );
            query.setParameter("bool", !isDone);
            query.setParameter("param", id);
            query.executeUpdate();
        });
    }

    @Override
    public void saveUser(User user) {
        this.makeTransaction(session -> session.save(user));
    }

    @Override
    public User findUserById(int id) {
        return this.tx(session -> session.get(User.class, id));
    }

    @Override
    public User findUserByEmail(String email) {
        return this.tx(session -> {
            Query query = session.createQuery(
                    "from ru.job4j.model.User where email =:param"
            );
            query.setParameter("param", email);
            return (User) query.uniqueResult();
        });
    }

    @Override
    public void addItem(Item item, String[] ids) {
        this.makeTransaction(session -> {
            for (String id : ids) {
                Category category = session.find(Category.class, Integer.parseInt(id));
                item.addCategory(category);
            }
            session.persist(item);
        });
    }

    @Override
    public List<Category> allCategories() {
        return this.tx(session -> session.createQuery(
                "select c from Category c", Category.class)
                .list());
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T tx(Function<Session, T> command) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        T rsl = null;
        try {
            rsl = command.apply(session);
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error with transaction", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    private void makeTransaction(Consumer<Session> command) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error with transaction", e);
        } finally {
            session.close();
        }
    }
}
