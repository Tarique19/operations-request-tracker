package com.starq.operations.tracker.controller;

import com.starq.operations.tracker.domain.Request;
import com.starq.operations.tracker.dto.CreateRequestDto;
import com.starq.operations.tracker.dto.RequestResponseDto;
import com.starq.operations.tracker.dto.UpdateStatusDto;
import com.starq.operations.tracker.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RequestResponseDto> create(
            @Valid  @RequestBody CreateRequestDto dto) {

        Request request = service.createRequest(dto.title(), dto.description());

        return ResponseEntity.ok(
                new RequestResponseDto(
                        request.getId(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getStatus().name()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponseDto> getById(@PathVariable Long id) {

        Request request = service.getRequestById(id);

        return ResponseEntity.ok(
                new RequestResponseDto(
                        request.getId(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getStatus().name()
                )
        );
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAll( @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                      @RequestParam(defaultValue = "createdAt") String sortBy,
                                                      @RequestParam(defaultValue = "desc") String direction
    ){
        Page<Request> requestPage =
                service.getAllRequests(page, size, sortBy, direction);

        List<RequestResponseDto> data =
                requestPage.getContent().stream()
                        .map(req -> new RequestResponseDto(
                                req.getId(),
                                req.getTitle(),
                                req.getDescription(),
                                req.getStatus().name()
                        ))
                        .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("currentPage", requestPage.getNumber());
        response.put("totalItems", requestPage.getTotalElements());
        response.put("totalPages", requestPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RequestResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusDto dto) {

        Request updated = service.updateStatus(id, dto.status());

        return ResponseEntity.ok(
                new RequestResponseDto(
                        updated.getId(),
                        updated.getTitle(),
                        updated.getDescription(),
                        updated.getStatus().name()
                )
        );
    }

}

