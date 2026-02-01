package com.starq.operations.tracker.controller;

import com.starq.operations.tracker.domain.Request;
import com.starq.operations.tracker.dto.CreateRequestDto;
import com.starq.operations.tracker.dto.RequestResponseDto;
import com.starq.operations.tracker.dto.UpdateStatusDto;
import com.starq.operations.tracker.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<RequestResponseDto>> getAll(){
        List<RequestResponseDto> response = service.getAllRequests()
                .stream()
                .map(request -> new RequestResponseDto(
                        request.getId(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getStatus().name()
                ))
                .toList();
        return  ResponseEntity.ok(response);
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

