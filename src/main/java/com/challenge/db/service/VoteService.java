package com.challenge.db.service;

import com.challenge.db.dto.VoteDTO;
import com.challenge.db.dto.VoteResult;
import com.challenge.db.entity.Restaurant;
import com.challenge.db.entity.User;
import com.challenge.db.entity.Vote;
import com.challenge.db.exception.VoteException;
import com.challenge.db.repository.UserRepository;
import com.challenge.db.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private RestaurantService restaurantService;
    private VoteRepository voteRepository;
    private UserRepository userRepository;

    @Autowired
    public VoteService(RestaurantService restaurantService, VoteRepository voteRepository, UserRepository userRepository) {
        this.restaurantService = restaurantService;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;

    }

    public ResponseEntity<List<VoteResult>> getResult() {
        return ResponseEntity.ok(voteRepository.countVotes(LocalDate.now()));
    }

    public ResponseEntity<?> endVoting() {
        List<VoteResult> results = voteRepository.countVotes(LocalDate.now());
        VoteResult voteResult = getTopRated(results);
        Restaurant restaurant = findRestaurantByVoteResult(voteResult);
        recordTopRated(restaurant);
        return ResponseEntity.ok().build();
    }

    private Restaurant findRestaurantByVoteResult(VoteResult voteResult) {
        return restaurantService.getRestaurantByName(voteResult.getRestaurantName());
    }

    private void recordTopRated(Restaurant restaurant) {
        restaurant.setSelected(Boolean.TRUE);
        restaurantService.save(restaurant);
    }

    private VoteResult getTopRated(List<VoteResult> results) {
        return results.stream().max(Comparator.comparingLong(VoteResult::getAmountVotes)).get();
    }

    public ResponseEntity<?> vote(Long restaurantId, String username) throws Exception {
        LocalDate today = LocalDate.now();
        Restaurant restaurant = checkIfExistsRestaurant(restaurantId);
        User user = checkIfExistsUser(username);
        checkAlreadyVotedToday(today, user);
        checkRestaurantAlreadySelectedThisWeek(restaurant);

        VoteDTO voteDTO = new Vote().entityToDTO(voteRepository.save(new Vote(today, user, restaurant)));
        return ResponseEntity.ok(voteDTO);
    }

    private void checkRestaurantAlreadySelectedThisWeek(Restaurant restaurant) throws VoteException {
        if(restaurant.getSelected()) {
            throw new VoteException("Restaurant already selected this week");
        }
    }

    private Restaurant checkIfExistsRestaurant(Long restaurantId) throws VoteException {
        return restaurantService.getRestaurantById(restaurantId).orElseThrow(() ->
                new VoteException("Restaurant not found"));
    }

    private User checkIfExistsUser(String name) throws VoteException {
        return userRepository.findByLogin(name).orElseThrow(() ->
                new VoteException("User not found"));
    }

    private void checkAlreadyVotedToday(LocalDate now, User user) throws VoteException {
        Optional<Vote> vote = voteRepository.findByVoteDateAndUser(now, user);
            if(vote.isPresent()) {
                throw new VoteException("Already voted today");
            }
    }
}
