package com.challenge.db.schedule;

import com.challenge.db.entity.Restaurant;
import com.challenge.db.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class VoteSchedule {

    private RestaurantService restaurantService;

    @Autowired
    public VoteSchedule(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Scheduled(cron = "0 0 0 * * MON")
    public void resetSelectedRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAll();
        restaurants.forEach(restaurant -> {
            restaurant.setSelected(Boolean.FALSE);
            restaurantService.save(restaurant);
        });
    }
}
