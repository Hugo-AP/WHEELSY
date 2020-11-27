package com.hacknet.wheelsy;

import com.hacknet.wheelsy.domain.model.Product;
import com.hacknet.wheelsy.domain.model.Sales;
import com.hacknet.wheelsy.domain.repository.EntrepreneurRepository;
import com.hacknet.wheelsy.domain.repository.ProductRepository;
import com.hacknet.wheelsy.domain.repository.SalesRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.ProductService;
import com.hacknet.wheelsy.domain.service.SalesService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import com.hacknet.wheelsy.service.ProductServiceImpl;
import com.hacknet.wheelsy.service.SalesServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SalesServiceImplIntegrationTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SalesRepository salesRepository;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private SalesService salesService;


    @TestConfiguration
    static class SalesServiceImplTestConfiguration{
        @Bean
        public SalesService salesService(){
            return new SalesServiceImpl();
        }
    }

    @Test
    @DisplayName("When getSaleById With Valid Id Then Returns Sale")
    public void whenGetSaleByIdWithValidIdThenReturnsUser(){
        //Arrange
        long id = 1;
        Sales sales= new Sales();
        sales.setId(id);
        given(salesRepository.findById(sales.getId()))
                .willReturn(Optional.of(sales));

        //Act
        Sales foundSale=salesService.getSaleById(id);

        //Assert
        assertThat(foundSale.getId()).isEqualTo(id);

    }


    @Test
    @DisplayName("When GetSaleById With Invalid Id Then Returns ResourceNotFound Exception")
    public void whenGetSaleByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        long id = 1;
        String template="Resource %s not found for %s with value %s";
        when(salesRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Sale","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
            Sales sales= salesService.getSaleById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

}
