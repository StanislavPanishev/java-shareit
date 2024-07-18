package ru.practicum.shareit.user;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private Long generatedId = 0L;


    @Override
    public User create(User user) {
        if (checkEmail(user.getEmail())) {
            throw new IllegalArgumentException("Такой email уже существует");
        }
        user.setId(++generatedId);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User getById(Long userId) {
        return users.get(userId);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User update(Long userId, User newUser) {
        User user = users.get(userId);
        if (newUser.getEmail() != null
                && !newUser.getEmail().equals(user.getEmail())
                && checkEmail(newUser.getEmail())) {
            throw new IllegalArgumentException("Такой email уже существует");
        }
        if (newUser.getEmail() != null
                && !newUser.getEmail().isBlank()) {
            user.setEmail(newUser.getEmail());
        }
        if (newUser.getName() != null
                && !newUser.getName().isBlank()) {
            user.setName(newUser.getName());
        }
        return user;
    }

    @Override
    public void delete(Long userId) {
        users.remove(userId);
    }

    private boolean checkEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public boolean contains(Long id) {
        return users.containsKey(id);
    }
}
