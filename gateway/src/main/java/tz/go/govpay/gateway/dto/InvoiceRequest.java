package tz.go.govpay.gateway.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InvoiceRequest {

    @NotBlank(message = "invoiceId is required")
    private String invoiceId;

    @NotBlank(message = "sellerCode is required")
    private String sellerCode;

    @NotBlank(message = "sellerName is required")
    private String sellerName;

    @NotBlank(message = "payerName is required")
    private String payerName;

    @NotBlank(message = "payerId is required")
    private String payerId;

    @NotNull(message = "amount is required")
    @DecimalMin(value = "0.01", message = "amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "currency is required")
    private String currency;

    @NotNull(message = "dueDate is required")
    @Future(message = "dueDate must be in the future")
    private LocalDateTime dueDate;

    private String description;
}