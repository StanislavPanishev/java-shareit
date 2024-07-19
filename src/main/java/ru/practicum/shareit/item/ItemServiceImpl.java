package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exeption.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public ItemDto create(Long userId, ItemDto item) {
        if (!userRepository.contains(userId)) {
            throw new NotFoundException("Пользователь не найден");
        }
        log.info("Добавление новой вещи");
        Item createdItem = itemRepository.create(userId, ItemMapper.toItem(item));
        log.info("Добавлена новая вещь");
        return ItemMapper.toItemDto(createdItem);
    }

    @Override
    public ItemDto getById(Long id) {
        log.info("Начало процесса получения вещи по id = {}", id);
        Item item = itemRepository.getById(id);
        log.info("Вещь получена");
        return ItemMapper.toItemDto(item);
    }

    @Override
    public List<ItemDto> getAll(Long id) {
        log.info("Начало процесса получения всех вещей");
        List<Item> items = itemRepository.getAll(id);
        log.info("Список всех вещей получен");
        return items.stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }

    @Override
    public ItemDto update(Long id, ItemDto item, Long itemId) {
        if (!userRepository.contains(id)) {
            throw new NotFoundException("Пользователь не найден");
        }
        if (!itemRepository.contains(itemId)) {
            throw new NotFoundException("Вещь не найдена");
        }
        log.info("Начало процесса обновления вещи");
        Item newItem = itemRepository.update(id, ItemMapper.toItem(item), itemId);
        log.info("Вещь обновлена");
        return ItemMapper.toItemDto(newItem);
    }

    @Override
    public List<ItemDto> search(String text) {
        List<Item> items = itemRepository.search(text);
        return items.stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

}
