package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.Product;
import com.hacknet.wheelsy.domain.model.Sales;
import com.hacknet.wheelsy.domain.model.SubscriptionPlan;
import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.repository.ProductRepository;
import com.hacknet.wheelsy.domain.repository.SalesRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.SalesService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService{

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Sales> getAllSales(Pageable pageable) {
        return salesRepository.findAll(pageable);
    }

    @Override
    public Page<Sales> getAllSalesByUserId(Long user_id, Pageable pageable){
        return salesRepository.findByUserId(user_id, pageable);
    }

    @Override
    public Sales getSaleById(Long salesId) {
        return salesRepository.findById(salesId).orElseThrow(()->new ResourceNotFoundException(
                "Sale","Id",salesId
        ));
    }

    @Override
    public Sales getSalesByIdAndUserId(Long user_id, Long salesId) {
        return salesRepository.findByIdAndUserId(salesId,user_id).orElseThrow(
                ()->new ResourceNotFoundException(
                        "Results not found with Id "+salesId+" and SpecialistId "+user_id
                )
        );
    }

    @Override
    public Sales createSales(Long user_id, Sales sales) {
        return userRepository.findById(user_id).map(user -> {
            sales.setUser(user);
            return salesRepository.save(sales);
        }).orElseThrow(()->new ResourceNotFoundException(
                "User","Id",user_id
        ));
    }

    @Override
    public Sales updateSales(Long user_id, Long salesId, Sales salesDetails) {
        if(!userRepository.existsById(user_id))
            throw new ResourceNotFoundException("User","Id",user_id);
        return salesRepository.findById(salesId).map(sales -> {
            sales.setWay_to_pay(salesDetails.getWay_to_pay());
            sales.setDate_register(salesDetails.getDate_register());
            sales.setTime(salesDetails.getTime());
            return salesRepository.save(sales);
        }).orElseThrow(()->new ResourceNotFoundException("Sales","Id",salesId));
    }

    @Override
    public ResponseEntity<?> deleteSales(Long user_id, Long salesId) {
        if(!userRepository.existsById(user_id))
            throw new ResourceNotFoundException("User", "Id", user_id);

        return salesRepository.findById(salesId).map(session -> {
            salesRepository.delete(session);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Sales", "Id", salesId));

    }

    @Override
    public Sales assignProduct(Long salesId, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product", "Id", productId));
        return salesRepository.findById(salesId).map(
                sales -> salesRepository.save(sales.OnSaleWith(product)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sale", "Id", salesId));
    }
}
