package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {
    Item create(Long userId, Item item);

    Item getById(Long id);

    List<Item> getAll(Long id);

    Item update(Long userId, Item item, Long itemId);

    List<Item> search(String text);

    boolean contains(Long id);
}
