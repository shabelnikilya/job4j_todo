package ru.job4j.store;

import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.Collection;
import java.util.List;

public interface Store {

    Collection<Item> findAllItems();

    Collection<Item> findFilterItems(boolean isDone);

    Item findItemById(int id);

    void changeStatusAndUpdate(int id, boolean done);

    void addItem(Item item);

    void addItem(Item item, String[] ids);

    void removeItem(int id);

    void updateItem(int id, Item item);

    void saveUser(User user);

    User findUserById(int id);

    User findUserByEmail(String email);

    List<Category> allCategories();
}
