package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserDto create(UserDto user) {
        log.info("Начало процесса создания пользователя");
        User createdUser = repository.create(UserMapper.toUser(user));
        log.info("Пользователь создан");
        return UserMapper.toUserDto(createdUser);
    }

    @Override
    public UserDto getById(Long userId) {
        log.info("Начало процесса получения пользователя по id = {}", userId);
        User user = repository.getById(userId);
        log.info("Пользователь получен");
        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        log.info("Начало процесса получения всех пользователей");
        List<User> users = repository.getAll();
        log.info("Список всех пользователей получен");
        return users.stream()
                .map(UserMapper::toUserDto)
                .toList();
    }

    @Override
    public UserDto update(Long userId, UserDto user) {
        log.info("Начало процесса обновления пользователя");
        User newuser = repository.update(userId, UserMapper.toUser(user));
        log.info("Пользователь обновлен");
        return UserMapper.toUserDto(newuser);
    }

    @Override
    public void delete(Long userId) {
        log.info("Начало процесса удаления пользователя");
        repository.delete(userId);
        log.info("Пользователь удален");
    }
}
