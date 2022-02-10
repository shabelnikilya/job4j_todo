package ru.job4j.store;

import ru.job4j.model.Item;

import java.util.Collection;

public interface Store {

    Collection<Item> findAllItems();

    Collection<Item> findFilterItems(boolean isDone);

    Item findItemById(int id);

    void addItem(Item item);

    void removeItem(int id);

    void updateItem(int id, Item item);
}
