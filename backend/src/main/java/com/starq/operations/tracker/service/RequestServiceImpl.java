package com.starq.operations.tracker.service;

import com.starq.operations.tracker.domain.Request;
import com.starq.operations.tracker.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Request createRequest(String title, String description) {
        return repository.save(new Request(title, description));
    }

    @Override
    public Request getRequestById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public List<Request> getAllRequests() {
        return repository.findAll();
    }

}

