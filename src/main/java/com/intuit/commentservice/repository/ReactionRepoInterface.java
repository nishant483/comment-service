package com.intuit.commentservice.repository;

import com.intuit.commentservice.models.Comment;
import com.intuit.commentservice.models.Reaction;
import com.intuit.commentservice.models.ReactionType;
import com.intuit.commentservice.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepoInterface extends CrudRepository<Reaction,Integer> {

    @Query(value = "select * from reaction where user_id = ?1 and post_id = ?2 and comment_id = ?3 limit 0,1", nativeQuery = true)
    Optional<Reaction> findIfReactionExists(long userId, Long postId, Long commentId);

}
