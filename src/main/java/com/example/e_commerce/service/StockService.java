package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Stock;

@Service
public interface StockService {
    List<Stock> getAllStocks();

    Stock createStock(Stock stock);

    Stock getStockByProductId(Long productId);

    Stock updateStock(Long productId, Stock stock);
}
