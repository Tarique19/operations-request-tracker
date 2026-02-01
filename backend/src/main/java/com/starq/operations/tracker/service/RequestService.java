package com.starq.operations.tracker.service;

import com.starq.operations.tracker.domain.Request;

import java.util.List;

public interface RequestService {
    Request createRequest(String title, String description);
    Request getRequestById(Long id);
    List<Request> getAllRequests();
}

