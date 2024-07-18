package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exeption.NotFoundException;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();
    private Long generatedId = 0L;

    @Override
    public Item create(Long userId, Item item) {
        Long itemId = ++generatedId;
        Item itemWithId = Item.builder()
                .id(itemId)
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .owner(userId)
                .build();
        items.put(itemId, itemWithId);
        return itemWithId;
    }

    @Override
    public Item getById(Long id) {
        return items.get(id);
    }

    @Override
    public List<Item> getAll(Long id) {
        return items.values().stream()
                .filter(item -> item.getOwner().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Item update(Long id, Item newItem, Long itemId) {
        Item item = items.get(itemId);
        if (!item.getOwner().equals(id)) {
            throw new NotFoundException("Данный предмет принадлежит другому пользователю");
        }

        if (newItem.getName() != null
                && !newItem.getName().isBlank()) {
            item.setName(newItem.getName());
        }
        if (newItem.getDescription() != null
                && !newItem.getDescription().isBlank()) {
            item.setDescription(newItem.getDescription());
        }
        if (newItem.getAvailable() != null) {
            item.setAvailable(newItem.getAvailable());
        }
        return item;
    }

    @Override
    public List<Item> search(String text) {
        List<Item> foundItems = new ArrayList<>();
        if (text.isEmpty()) {
            return foundItems;
        }

        return items.values().stream()
                .filter(item -> (item.getName().toLowerCase().contains(text.toLowerCase())
                        || item.getDescription().toLowerCase().contains(text.toLowerCase()))
                        && item.getAvailable())
                .toList();
    }

    @Override
    public boolean contains(Long id) {
        return items.containsKey(id);
    }
}
