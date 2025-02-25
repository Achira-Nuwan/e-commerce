package com.example.e_commerce.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Stock;
import com.example.e_commerce.repositories.StockRepository;
import com.example.e_commerce.service.StockService;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock getStockByProductId(Long productId) {
        return stockRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public Stock updateStock(Long productId, Stock stock) {
        Stock exiStock = stockRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        exiStock.setProduct(stock.getProduct());
        exiStock.setQty(stock.getQty());
        return stockRepository.save(exiStock);
    }

}
