package ru.sokol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokol.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
