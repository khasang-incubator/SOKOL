package io.khasang.sokol.service;


import io.khasang.sokol.model.RequestType;
import java.util.List;

public interface RequestTypeService {
    List<RequestType> getAll();
    void requestTypeDelete(long id);
}
