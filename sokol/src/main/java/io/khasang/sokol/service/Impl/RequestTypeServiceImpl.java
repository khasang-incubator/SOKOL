package io.khasang.sokol.service.Impl;

import io.khasang.sokol.model.RequestType;
import io.khasang.sokol.repository.DepartmentRepository;
import io.khasang.sokol.repository.RequestTypeRepository;
import io.khasang.sokol.service.RequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RequestTypeServiceImpl implements RequestTypeService {

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Override
    public List<RequestType> getAll() {
        return requestTypeRepository.findAll();
    }
}
