package ru.job4j.store;

import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.Collection;

public interface Store {

    Collection<Item> findAllItems();

    Collection<Item> findFilterItems(boolean isDone);

    Item findItemById(int id);

    Item changeStatusAndUpdate(int id);

    void addItem(Item item);

    void removeItem(int id);

    void updateItem(int id, Item item);

    void saveUser(User user);

    User findUserById(int id);

    User findUserByEmail(String email);
}
