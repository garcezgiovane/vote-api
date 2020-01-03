package com.challenge.db.dto;

public class VoteResult {
    private Long amountVotes;
    private String restaurantName;

    public Long getAmountVotes() {
        return amountVotes;
    }

    public void setAmountVotes(Long amountVotes) {
        this.amountVotes = amountVotes;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public VoteResult(Long amountVotes, String restaurantName) {
        this.amountVotes = amountVotes;
        this.restaurantName = restaurantName;
    }
}
