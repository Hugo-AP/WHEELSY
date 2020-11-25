package com.hacknet.wheelsy;

import com.hacknet.wheelsy.domain.model.Entrepreneur;
import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.repository.EntrepreneurRepository;
import com.hacknet.wheelsy.domain.repository.SubscriptionPlanRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.EntrepreneurService;
import com.hacknet.wheelsy.domain.service.UserService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import com.hacknet.wheelsy.service.EntrepreneurServicelmpl;
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
public class EntrepreneurServiceImplIntegrationTest {
    @MockBean
    private EntrepreneurRepository entrepreneurRepository;

    @Autowired
    private EntrepreneurService entrepreneurService;

    @TestConfiguration
    static class EntrepreneurServiceImplTestConfiguration{
        @Bean
        public EntrepreneurService entrepreneurService(){
            return new EntrepreneurServicelmpl() {
            };
        }
    }

    @Test
    @DisplayName("When GetEntrepreneurById With Valid Id Then Returns Entrepreneur")
    public void whenGetEntrepreneurByIdWithValidIdThenReturnsEntrepreneur(){
        //Arrange
        long id = 1;
        Entrepreneur entrepreneur= new Entrepreneur();
        entrepreneur.setId(id);
        given(entrepreneurRepository.findById(entrepreneur.getId()))
                .willReturn(Optional.of(entrepreneur));

        //Act
        Entrepreneur foundEntrepreneur=entrepreneurService.getEntrepreneurById(id);

        //Assert
        assertThat(foundEntrepreneur.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When GetEntrepreneurById With Invalid Id Then Returns ResourceNotFound Exception")
    public void whenGetEntrepreneurByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        long id = 1;
        String template="Resource %s not found for %s with value %s";
        when(entrepreneurRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Entrepreneur","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
            Entrepreneur foundEntrepreneur= entrepreneurService.getEntrepreneurById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
