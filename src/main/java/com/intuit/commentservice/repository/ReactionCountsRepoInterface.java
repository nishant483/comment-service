package com.intuit.commentservice.repository;

import com.intuit.commentservice.models.Comment;
import com.intuit.commentservice.models.Reaction;
import com.intuit.commentservice.models.ReactionCounts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionCountsRepoInterface extends CrudRepository<ReactionCounts,Integer> {

    @Query(value = "update reaction_counts set reaction_count = reaction_count+1 where reaction_type = ?1 and post_id = ?2 and comment_id = ?3", nativeQuery = true)
    boolean updateReactionCounter(String reaction_type, long post_id, long comment_id);

    @Query(value = "select * from reaction_counts where reaction_type = ?1 and post_id = ?2 and comment_id = ?3", nativeQuery = true)
    Optional<ReactionCounts> findByReactionTypeAAndComment_idAndPost_id(String reaction_type, long post_id, long comment_id);

    @Query(value = "select * from reaction_counts where post_id = ?1 and comment_id = ?2", nativeQuery = true)
    List<ReactionCounts> findByComment_idAndPost_id(long post_id, long comment_id);

//    Optional<ReactionCounts> findByReactionTypeAAndComment_idAndPost_id(String reaction_type,long post_id,long comment_id);

}
