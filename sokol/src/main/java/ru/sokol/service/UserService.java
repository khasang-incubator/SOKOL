package ru.sokol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sokol.dto.user.UserDto;
import ru.sokol.model.User;
import ru.sokol.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserDto> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::convert);
    }

    private UserDto convert(User user){
        UserDto result = new UserDto();
        result.setUsername(user.getUsername());
        result.setFullName(user.getFullName());
        return  result;
    }
}
