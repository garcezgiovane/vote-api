package com.challenge.db.service;

import com.challenge.db.entity.Restaurant;
import com.challenge.db.entity.User;
import com.challenge.db.exception.VoteException;
import com.challenge.db.repository.RestaurantRepository;
import com.challenge.db.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @Mock
    private VoteService voteService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UserRepository userRepository;

    @Test(expected = VoteException.class)
    public void shouldReturnAExceptionWhenReceiveARestaurantThatNotExists() throws VoteException {
        Restaurant restaurant = new Restaurant(5L, "test", Boolean.FALSE);
        restaurantRepository.findById(restaurant.getId()).orElseThrow(() ->
                new VoteException("Restaurant not found"));

    }

    @Test(expected = VoteException.class)
    public void shouldReturnAExceptionWhenReceiveAUserThatNotExists() throws VoteException {
        User user = new User(1L, "test", "123");
        userRepository.findByLogin(user.getLogin()).orElseThrow(() ->
                new VoteException("User not found"));

    }

    @Test
    public void shouldReturnAUserWhenReceiveAValidUser() {
        User user = new User(1L, "test", "123");
        Optional<User> repoUser = userRepository.findById(user.getId());

        repoUser.ifPresent(value -> Assert.assertEquals(user, value));

    }
}
