package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Item;

import java.util.Collection;
import java.util.List;

public class HbmStore implements Store, AutoCloseable {
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
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery(
                "from ru.job4j.model.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Collection<Item> findFilterItems(boolean isDone) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery(
                "from ru.job4j.model.Item where done = " + isDone).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void addItem(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeItem(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateItem(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        item.setId(id);
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Item findItemById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
