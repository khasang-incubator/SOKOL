package io.khasang.sokol.repository;

import io.khasang.sokol.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestStatusRepository extends JpaRepository<RequestStatus, Long> {
}
