package tz.go.govpay.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "invoices")
public class Invoice {

    @Id
    private String gatewayInvoiceRef;

    private String invoiceId;
    private String sellerCode;
    private String sellerName;
    private String payerName;
    private String payerId;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime dueDate;
    private String status; // PENDING, PAID, CANCELLED
    private String description;
    private LocalDateTime createdAt;
}