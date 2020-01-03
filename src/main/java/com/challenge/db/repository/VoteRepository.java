package com.challenge.db.repository;

import com.challenge.db.dto.VoteResult;
import com.challenge.db.entity.User;
import com.challenge.db.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByVoteDateAndUser(LocalDate voteDate, User user);

    @Query("select new com.challenge.db.dto.VoteResult( count (v.id), r.name) from votes v " +
        " join restaurants r on r.id = v.restaurant.id where v.voteDate = :voteDate group by r.name")
    List<VoteResult> countVotes(@Param("voteDate") LocalDate today);
}
