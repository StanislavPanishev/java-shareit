package ru.practicum.shareit.exeption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.item.ItemController;
import ru.practicum.shareit.user.UserController;

import java.util.Map;

@Slf4j
@RestControllerAdvice(assignableTypes = {
        ItemController.class,
        UserController.class
})
public class ErrorHandler {
    private void log(Throwable e) {
        log.error("Исключение {}: {}", e, e.getMessage());
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(final NotFoundException e) {
        log(e);
        return Map.of("error", "Object is not found",
                "errorMessage", e.getMessage());
    }
}