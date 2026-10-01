package tz.go.govpay.gateway.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tz.go.govpay.gateway.model.Invoice;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {

    Optional<Invoice> findByInvoiceId(String invoiceId);

    boolean existsByInvoiceId(String invoiceId);
}