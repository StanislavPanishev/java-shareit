package ru.practicum.shareit.user;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserRepository {
    User create(User user);

    User getById(Long userId);

    List<User> getAll();

    User update(Long userId, User user);

    void delete(Long userId);

    boolean contains(Long id);
}
