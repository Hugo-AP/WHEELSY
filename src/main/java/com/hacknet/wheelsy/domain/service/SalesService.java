package com.hacknet.wheelsy.domain.service;

import com.hacknet.wheelsy.domain.model.Sales;
import com.hacknet.wheelsy.domain.model.Sales;
import com.hacknet.wheelsy.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SalesService {
    Page<Sales> getAllSales(Pageable pageable);
    Page<Sales> getAllSalesByUserId(Long user_id, Pageable pageable);
    Sales getSaleById(Long salesId);
    Sales getSalesByIdAndUserId(Long user_id, Long salesId);
    Sales createSales(Long user_id, Sales sales);
    Sales updateSales(Long user_id, Long salesId, Sales salesDetails);
    ResponseEntity<?> deleteSales(Long user_id, Long salesId);
    Sales assignProduct(Long salesId, Long productId);
}
