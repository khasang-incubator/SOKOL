package io.khasang.sokol.repository;

import io.khasang.sokol.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    //List<Request> findAllByDeletedIsFalse();
}
