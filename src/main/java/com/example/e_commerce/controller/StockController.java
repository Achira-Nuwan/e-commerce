package com.example.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.entities.Stock;
import com.example.e_commerce.service.ProductService;
import com.example.e_commerce.service.StockService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock() {
        try {
            List<Stock> stock = stockService.getAllStocks();
            if (!stock.isEmpty()) {
                return ResponseEntity.ok(stock);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * @PostMapping
     * public ResponseEntity<Object> createStock(@RequestBody StockRequestDto dto) {
     * try {
     * Stock createStock = new Stock();
     * 
     * if (dto.getProductId() == null) {
     * return ResponseEntity.status(HttpStatus.BAD_REQUEST).
     * body("Product id Cannot be null");
     * }
     * 
     * createStock.setId(stock.getId());
     * createStock.setQty(stock.getQty());
     * return ResponseEntity.ok(createStock);
     * } catch (Exception e) {
     * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     * }
     * }
     */

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(Long productId) {
        try {
            Stock stock = stockService.getStockByProductId(productId);
            if (stock == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.ok(stock);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * @PutMapping("/{id}")
     * public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody
     * Stock stock) {
     * try {
     * Stock exiStock = stockService.getStockById(id);
     * if (exiStock == null) {
     * return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
     * } else {
     * exiStock.setId(stock.getId());
     * exiStock.setQty(stock.getQty());
     * Stock updatedStock = stockService.updateStock(id, exiStock);
     * return ResponseEntity.ok(updatedStock);
     * }
     * } catch (Exception e) {
     * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     * }
     * }
     */

    /*
     * @DeleteMapping("/{id}")
     * public void removeStockById(Long id) {
     * stockService.removeStockById(id);
     * }
     */
}
