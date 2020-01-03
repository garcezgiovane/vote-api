package com.challenge.db.dto;

import java.time.LocalDate;

public class VoteDTO {

    private LocalDate voteDate;
    private String username;
    private String restaurantName;

    public VoteDTO(LocalDate voteDate, String username, String restaurantName) {
        this.voteDate = voteDate;
        this.username = username;
        this.restaurantName = restaurantName;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
