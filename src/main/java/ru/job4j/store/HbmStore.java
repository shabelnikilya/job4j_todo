package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Item;

import java.util.Collection;
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
        return this.tx(session -> session.createQuery("from ru.job4j.model.Item").list());
    }

    @Override
    public Collection<Item> findFilterItems(boolean isDone) {
        return this.tx(session ->
                session.createQuery(
                        "from ru.job4j.model.Item where done = " + isDone).list()
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
