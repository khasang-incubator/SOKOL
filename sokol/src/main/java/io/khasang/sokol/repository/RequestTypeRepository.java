package io.khasang.sokol.repository;

import io.khasang.sokol.model.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {
    List<RequestType> findAllByDeletedIsFalse();
}
