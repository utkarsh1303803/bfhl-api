package com.acropolis.bfhl.controller;

import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;
import com.acropolis.bfhl.dto.OperationCodeResponse;
import com.acropolis.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
@RequiredArgsConstructor
public class BfhlController {

    private final BfhlService bfhlService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BfhlResponse> process(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processData(request.getData());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OperationCodeResponse> getOperationCode() {
        return ResponseEntity.ok(new OperationCodeResponse(200));
    }

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OperationCodeResponse> getHealth() {
        return ResponseEntity.ok(new OperationCodeResponse(200));
    }
}
