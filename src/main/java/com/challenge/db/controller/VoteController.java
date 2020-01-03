package com.challenge.db.controller;

import com.challenge.db.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class VoteController {

    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/results")
    public ResponseEntity<?> getResults() {
        return voteService.getResult();
    }

    @GetMapping("/close")
    public ResponseEntity<?> endVoting() {
        return voteService.endVoting();

    }

    @PostMapping(value = "/vote/{restaurantId}")
    public ResponseEntity<?> vote(@PathVariable Long restaurantId, @AuthenticationPrincipal  UserDetails userDetails) throws Exception {
        return voteService.vote(restaurantId, userDetails.getUsername());
    }
}
