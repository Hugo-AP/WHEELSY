package com.hacknet.wheelsy;

import com.hacknet.wheelsy.domain.model.Product;
import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.repository.EntrepreneurRepository;
import com.hacknet.wheelsy.domain.repository.ProductRepository;
import com.hacknet.wheelsy.domain.repository.SubscriptionPlanRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.ProductService;
import com.hacknet.wheelsy.domain.service.UserService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import com.hacknet.wheelsy.service.ProductServiceImpl;
import com.hacknet.wheelsy.service.UserServiceImpl;
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
public class ProductServiceImplIntegrationTest {

    @MockBean
    private EntrepreneurRepository entrepreneurRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @TestConfiguration
    static class ProductServiceImplTestConfiguration{
        @Bean
        public ProductService productService(){
            return new ProductServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetProductById With Valid Id Then Returns Product")
    public void whenGetProductByIdWithValidIdThenReturnsUser(){
        //Arrange
        long id = 1;
        Product product= new Product();
        product.setId(id);
        given(productRepository.findById(product.getId()))
                .willReturn(Optional.of(product));

        //Act
        Product foundProduct=productService.getProductById(id);

        //Assert
        assertThat(foundProduct.getId()).isEqualTo(id);

    }


    @Test
    @DisplayName("When GetProductById With Invalid Id Then Returns ResourceNotFound Exception")
    public void whenGetProductByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        long id = 1;
        String template="Resource %s not found for %s with value %s";
        when(productRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Product","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
            Product foundProduct= productService.getProductById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

}
