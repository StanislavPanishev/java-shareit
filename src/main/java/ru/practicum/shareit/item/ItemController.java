package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                          @Valid @RequestBody ItemDto itemDto) {
        log.info("Получен HTTP-запрос по адресу /items (метод POST). "
                + "Вызван метод create()");
        return service.create(userId, itemDto);
    }

    @GetMapping("{id}")
    public ItemDto getForId(@PathVariable Long id) {
        ItemDto item = service.getById(id);
        log.info("Получен HTTP-запрос по адресу /items/{id} (метод GET). "
                + "Вызван метод getById(id)");
        return item;
    }

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader("X-Sharer-User-Id") Long id) {
        List<ItemDto> items = service.getAll(id);
        log.info("Получен HTTP-запрос по адресу /items (метод GET). "
                + "Вызван метод getAll(id)");
        return items;
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                              @RequestBody ItemDto itemDto,
                              @PathVariable Long itemId) {

        return service.update(userId, itemDto, itemId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchByNameOrDesc(@RequestParam("text") String text) {
        return service.search(text);
    }
}
