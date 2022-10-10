package com.intuit.commentservice.repository;

import com.intuit.commentservice.models.CommentClosure;
import com.intuit.commentservice.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepoInterface extends CrudRepository<User,Integer> {

    boolean existsByEmail(String email);
    User findByEmail(String email);

    @Query(value = "select sc.* from reaction,social_users sc where user_id = sc.id and post_id = ?1 and comment_id = ?2 and reaction_type = ?3", nativeQuery = true)
    List<User> findReactions(Long postId, Long commentId, String reaction_type, Pageable pageable);
}
