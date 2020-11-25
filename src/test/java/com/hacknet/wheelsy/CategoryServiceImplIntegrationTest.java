package com.hacknet.wheelsy;

import com.hacknet.wheelsy.domain.model.Category;
import com.hacknet.wheelsy.domain.model.Entrepreneur;
import com.hacknet.wheelsy.domain.repository.CategoryRepository;
import com.hacknet.wheelsy.domain.repository.EntrepreneurRepository;
import com.hacknet.wheelsy.domain.service.CategoryService;
import com.hacknet.wheelsy.domain.service.EntrepreneurService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import com.hacknet.wheelsy.service.CategoryServiceImpl;
import com.hacknet.wheelsy.service.EntrepreneurServicelmpl;
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
public class CategoryServiceImplIntegrationTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @TestConfiguration
    static class CategoryServiceImplTestConfiguration{
        @Bean
        public CategoryService categoryService(){
            return new CategoryServiceImpl() {
            };
        }
    }

    @Test
    @DisplayName("When GetCategoryById With Valid Id Then Returns Category")
    public void whenGetCategoryByIdWithValidIdThenReturnsCategory(){
        //Arrange
        long id = 1;
        Category category= new Category();
        category.setId(id);
        given(categoryRepository.findById(category.getId()))
                .willReturn(Optional.of(category));

        //Act
        Category foundCategory=categoryService.getCategoryById(id);

        //Assert
        assertThat(foundCategory.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When GetCategoryById With Invalid Id Then Returns ResourceNotFound Exception")
    public void whenGetUserIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        long id = 1;
        String template="Resource %s not found for %s with value %s";
        when(categoryRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Category","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
            Category foundCategory= categoryService.getCategoryById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
