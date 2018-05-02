package io.khasang.sokol.service.Impl;

import io.khasang.sokol.model.RequestType;
import io.khasang.sokol.repository.RequestTypeRepository;
import io.khasang.sokol.service.RequestTypeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
public class RequestTypeServiceImpl implements RequestTypeService {

    private RequestTypeRepository requestTypeRepository;

    @Autowired
    public RequestTypeServiceImpl(RequestTypeRepository requestTypeRepository) {
        this.requestTypeRepository = requestTypeRepository;
    }

    @Override
    public List<RequestType> getAll() {
        return requestTypeRepository.findAll();
    }

    @Override
    public void requestTypeDelete(long id) {
        RequestType requestType = requestTypeRepository.getOne(id);
        requestType.setDeleted(true);
        requestType.setUpdatedDate(new Date());
        requestTypeRepository.save(requestType);
    }
}
