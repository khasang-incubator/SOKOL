package io.khasang.sokol.service;


import io.khasang.sokol.model.Request;
import io.khasang.sokol.model.RequestStatus;
import io.khasang.sokol.repository.RequestRepository;
import io.khasang.sokol.repository.RequestStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequestService {

    private RequestRepository requestRepository;
    private RequestStatusRepository requestStatusRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository, RequestStatusRepository requestStatusRepository) {
        this.requestRepository = requestRepository;
        this.requestStatusRepository = requestStatusRepository;
    }

    public void save(Request requestDetails) {
        SecurityContext context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        if (requestDetails.getId() == 0) { // create request
            requestDetails.setCreatedBy(userName);
            RequestStatus status = requestStatusRepository.findOne(1L);
            requestDetails.setStatus(status);
            requestDetails.setVersion(1);
            requestRepository.save(requestDetails);
        } else {
            // update request
            Request request = requestRepository.getOne(requestDetails.getId());
            request.setUpdatedBy(userName);
            request.setUpdatedDate(new Date());
            request.setTitle(requestDetails.getTitle());
            request.setDescription(requestDetails.getDescription());
            request.setRequestType(requestDetails.getRequestType());
            request.setStatus(requestDetails.getStatus());
            requestRepository.save(request);
        }
    }

    public void requestDelete(long id) {
        //Request request = requestRepository.getOne(id);
        requestRepository.delete(id);
    }
}

