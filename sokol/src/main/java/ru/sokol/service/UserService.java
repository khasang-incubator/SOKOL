package ru.sokol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sokol.dto.user.CreateUserRequest;
import ru.sokol.dto.user.UserDto;
import ru.sokol.model.Department;
import ru.sokol.model.User;
import ru.sokol.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentService departmentService;

    public UserService(UserRepository userRepository, DepartmentService departmentService) {
        this.userRepository = userRepository;
        this.departmentService = departmentService;
    }

    public Page<UserDto> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::convert);
    }

    private UserDto convert(User user){
        UserDto result = new UserDto();
        result.setUsername(user.getUsername());
        result.setFullName(user.getFullName());
        result.setId(user.getId());
        return  result;
    }

    public UserDto createUser(CreateUserRequest request) {
        User user =  new User();
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        Department department = departmentService.findById(request.getDepartmentId());
        user.setDepartment(department);
        userRepository.save(user);
        return convert(user);
    }
}
