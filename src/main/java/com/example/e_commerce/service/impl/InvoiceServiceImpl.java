package com.example.e_commerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Invoice;
import com.example.e_commerce.repositories.InvoiceRepository;
import com.example.e_commerce.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice creatInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(Long invNo) {
        return invoiceRepository.findById(invNo).orElse(null);
    }

    @Override
    public Invoice updateInvoice(Long invNo, Invoice invoice) {
        Invoice exInvoice = invoiceRepository.findById(invNo).orElse(null);

        if (exInvoice == null) {
            return null;
        } else {
            try {
                exInvoice.setCustName(invoice.getCustName());
                exInvoice.setInvoiceProducts(invoice.getInvoiceProducts());
                exInvoice.setTotalPrice(invoice.getTotalPrice());

                return invoiceRepository.save(exInvoice);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteInvoice(Long invNo) {
        invoiceRepository.deleteById(invNo);
    }

}
