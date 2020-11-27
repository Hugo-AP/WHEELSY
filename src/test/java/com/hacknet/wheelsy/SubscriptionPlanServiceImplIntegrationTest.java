package com.hacknet.wheelsy;

import com.hacknet.wheelsy.domain.model.SubscriptionPlan;
import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.repository.SubscriptionPlanRepository;
import com.hacknet.wheelsy.domain.service.EntrepreneurService;
import com.hacknet.wheelsy.domain.service.SubscriptionPlanService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import com.hacknet.wheelsy.service.EntrepreneurServicelmpl;
import com.hacknet.wheelsy.service.SubscriptionPlanServiceImpl;
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
public class SubscriptionPlanServiceImplIntegrationTest {
    @MockBean
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @TestConfiguration
    static class SubscriptionPlanServiceImplConfiguration{
        @Bean
        public SubscriptionPlanService subscriptionPlanService(){
            return new SubscriptionPlanServiceImpl() {
            };
        }
    }
    @Test
    @DisplayName("When GetSubscriptionPlanById With Valid Id Then Returns SubscriptionPlan")
    public void whenGetSubscriptionPlanByIdWithValidIdThenReturnsSubscriptionPlan(){
        //Arrange
        long id = 1;
        SubscriptionPlan subscriptionPlan= new SubscriptionPlan();
        subscriptionPlan.setId(id);
        given(subscriptionPlanRepository.findById(subscriptionPlan.getId()))
                .willReturn(Optional.of(subscriptionPlan));

        //Act
        SubscriptionPlan foundSubscriptionPlan=subscriptionPlanService.getSubscriptionPlanById(id);

        //Assert
        assertThat(foundSubscriptionPlan.getId()).isEqualTo(id);

    }


    @Test
    @DisplayName("When GetSubscriptionPlanById With Invalid Id Then Returns ResourceNotFound Exception")
    public void whenGetSubscriptionPlanByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        long id = 1;
        String template="Resource %s not found for %s with value %s";
        when(subscriptionPlanRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"SubscriptionPlan","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
            SubscriptionPlan foundSubscriptionPlan= subscriptionPlanService.getSubscriptionPlanById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
