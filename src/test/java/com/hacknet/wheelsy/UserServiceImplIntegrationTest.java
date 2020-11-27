package com.hacknet.wheelsy;

import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.repository.SubscriptionPlanRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.UserService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import com.hacknet.wheelsy.service.UserServiceImpl;
import org.assertj.core.api.ThrowableAssert;
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
public class UserServiceImplIntegrationTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration{
        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetUserById With Valid Id Then Returns User")
    public void whenGetUserByIdWithValidIdThenReturnsUser(){
        //Arrange
        long id = 1;
        User user= new User();
        user.setId(id);
        given(userRepository.findById(user.getId()))
                .willReturn(Optional.of(user));

        //Act
        User foundUser=userService.getUserById(id);

        //Assert
        assertThat(foundUser.getId()).isEqualTo(id);

    }


    @Test
    @DisplayName("When GetUserId With Invalid Id Then Returns ResourceNotFound Exception")
    public void whenGetUserIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        long id = 1;
        String template="Resource %s not found for %s with value %s";
        when(userRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"User","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
                User foundUser= userService.getUserById(id);
                });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

}
