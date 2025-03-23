package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Invoice;

@Service
public interface InvoiceService {
    List<Invoice> getAllInvoices();

    Invoice creatInvoice(Invoice invoice);

    Invoice getInvoiceById(Long invNo);

    Invoice updateInvoice(Long invNo, Invoice invoice);

    void deleteInvoice(Long invNo);
}
