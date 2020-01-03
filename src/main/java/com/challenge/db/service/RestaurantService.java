package com.challenge.db.service;

import com.challenge.db.entity.Restaurant;
import com.challenge.db.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public void save( Restaurant restaurant ) {
        restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }
}
