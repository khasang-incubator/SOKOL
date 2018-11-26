package ru.sokol.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sokol.dto.user.UserDto;
import ru.sokol.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<Page<UserDto>> getUsers(Pageable pageable){
        Page<UserDto> result = userService.findAllUsers(pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
