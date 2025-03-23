package com.example.e_commerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.dto.InvoiceRequestDto;
import com.example.e_commerce.dto.InvoicedProductsDto;
import com.example.e_commerce.entities.Invoice;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.service.InvoiceService;
import com.example.e_commerce.service.ProductService;

@RestController
@RequestMapping("/invoice")
@CrossOrigin("*")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        try {
            List<Invoice> invoices = invoiceService.getAllInvoices();
            return ResponseEntity.status(HttpStatus.OK).body(invoices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequestDto dto) {
        try {
            List<InvoicedProductsDto> invoicedProducts = dto.getInvoiceProducts();

            double totalPrice = 0.0;

            List<Product> productList = new ArrayList<>();

            for (InvoicedProductsDto invoiceProduct : invoicedProducts) {
                Long productId = invoiceProduct.getProductId();
                int quantity = invoiceProduct.getQty();

                if (quantity <= 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                Product products = productService.getProductById(productId);
                if (products == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                productList.add(products);
                totalPrice += products.getPrice() * quantity;
            }

            Invoice createInvoice = new Invoice();

            createInvoice.setInvoiceProducts(productList);
            createInvoice.setTotalPrice(totalPrice);

            Invoice createdInvoice = invoiceService.creatInvoice(createInvoice);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(invoice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice,
    // @PathVariable Long id) {
    // try {
    // Invoice updateInvoice = invoiceService.updateInvoice(id, invoice);
    // return ResponseEntity.status(HttpStatus.OK).body(updateInvoice);
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    // }
    // }
}
