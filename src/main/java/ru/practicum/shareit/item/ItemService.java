package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto create(Long userId, ItemDto item);

    ItemDto getById(Long id);

    List<ItemDto> getAll(Long id);

    ItemDto update(Long userId, ItemDto item, Long itemId);

    List<ItemDto> search(String text);
}
