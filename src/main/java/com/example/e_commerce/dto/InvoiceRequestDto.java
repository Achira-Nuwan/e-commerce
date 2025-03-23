package com.example.e_commerce.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRequestDto {
    private List<InvoicedProductsDto> invoiceProducts;
}
