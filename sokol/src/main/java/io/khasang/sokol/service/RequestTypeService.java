package io.khasang.sokol.service;

import io.khasang.sokol.model.RequestType;
import io.khasang.sokol.repository.RequestTypeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
public class RequestTypeService {

    private RequestTypeRepository requestTypeRepository;

    @Autowired
    public RequestTypeService(RequestTypeRepository requestTypeRepository) {
        this.requestTypeRepository = requestTypeRepository;
    }

    public List<RequestType> getAll() {
        return requestTypeRepository.findAll();
    }

    public void requestTypeDelete(long id) {
        RequestType requestType = requestTypeRepository.getOne(id);
        requestType.setDeleted(true);
        requestType.setUpdatedDate(new Date());
        requestTypeRepository.save(requestType);
    }
}
