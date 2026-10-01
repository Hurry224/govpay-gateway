package tz.go.govpay.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tz.go.govpay.gateway.dto.InvoiceRequest;
import tz.go.govpay.gateway.dto.InvoiceResponse;
import tz.go.govpay.gateway.model.Invoice;
import tz.go.govpay.gateway.repository.InvoiceRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceResponse registerInvoice(InvoiceRequest request) {

        // Angalia kama invoiceId hii tayari imesajiliwa awali
        if (invoiceRepository.existsByInvoiceId(request.getInvoiceId())) {
            return InvoiceResponse.builder()
                    .status("FAILED")
                    .message("Invoice with this invoiceId already exists")
                    .build();
        }

        // Tengeneza gatewayInvoiceRef ya kipekee
        String gatewayInvoiceRef = generateGatewayRef();

        Invoice invoice = Invoice.builder()
                .gatewayInvoiceRef(gatewayInvoiceRef)
                .invoiceId(request.getInvoiceId())
                .sellerCode(request.getSellerCode())
                .sellerName(request.getSellerName())
                .payerName(request.getPayerName())
                .payerId(request.getPayerId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .dueDate(request.getDueDate())
                .description(request.getDescription())
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        invoiceRepository.save(invoice);

        return InvoiceResponse.builder()
                .status("SUCCESS")
                .message("Invoice registered successfully")
                .gatewayInvoiceRef(gatewayInvoiceRef)
                .build();
    }

    private String generateGatewayRef() {
        int year = LocalDateTime.now().getYear();
        String randomPart = UUID.randomUUID().toString()
                .substring(0, 4).toUpperCase();
        return "GWY-" + year + "-" + randomPart;
    }
}