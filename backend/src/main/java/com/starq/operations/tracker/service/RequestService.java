package com.starq.operations.tracker.service;

import com.starq.operations.tracker.domain.Request;
import com.starq.operations.tracker.domain.RequestStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RequestService {
    Request createRequest(String title, String description);
    Request getRequestById(Long id);
    Page<Request> getAllRequests(int page, int size, String sortBy, String direction);
    Request updateStatus(Long id, RequestStatus status);
}

