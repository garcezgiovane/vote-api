package com.challenge.db.entity;

import com.challenge.db.dto.VoteDTO;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDate voteDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote( LocalDate voteDate, User user, Restaurant restaurant) {
        this.voteDate = voteDate;
        this.user = user;
        this.restaurant = restaurant;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public VoteDTO entityToDTO( Vote vote ) {
        return new VoteDTO( vote.getVoteDate(), vote.getUser().getLogin(), vote.getRestaurant().getName() );
    }

}
