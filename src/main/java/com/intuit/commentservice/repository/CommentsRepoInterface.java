package com.intuit.commentservice.repository;

import com.intuit.commentservice.models.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// rank will tell us how many comments within any comment we can get
// level will tell us till how much min-maximum depth can we go

@Repository
public interface CommentsRepoInterface extends CrudRepository<Comment,Integer> {

    @Query(value = "SELECT * FROM Comment c,Comment_Closure cc WHERE c.post_id = ?1 and c.id = cc.descendent_id and ANCESTOR_ID = ?2 and depth <= ?3 order by c.id asc", nativeQuery = true)
    List<Comment> findCommentsByTimestamp(Long post_id,long parent_comment_id,int depth, Pageable pageable);

//    @Query(value = "SELECT * FROM Comment WHERE POST_ID = ?1 and parent_comment_id is null order by created_timestamp", nativeQuery = true)
//    List<Comment> findBaseComments(String postId, Pageable pageable);
//
//    @Query(value = "select * from (select *,DENSE_RANK() OVER (PARTITION BY parent_comment_id ORDER BY created_timestamp DESC) AS ranks from COMMENT where level > ?1 and level < ?2) where ranks < 2", nativeQuery = true)
//    List<Comment> drillComments(String postId,String parent_comment_id);



}
