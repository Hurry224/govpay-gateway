package tz.go.govpay.gateway.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz.go.govpay.gateway.dto.InvoiceRequest;
import tz.go.govpay.gateway.dto.InvoiceResponse;
import tz.go.govpay.gateway.service.InvoiceService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/invoice")
    public ResponseEntity<InvoiceResponse> submitInvoice(
            @Valid @RequestBody InvoiceRequest request) {

        InvoiceResponse response = invoiceService.registerInvoice(request);

        if ("SUCCESS".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}