package com.intuit.commentservice.repository;

import com.intuit.commentservice.models.Comment;
import com.intuit.commentservice.models.CommentClosure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsClosureRepo extends CrudRepository<CommentClosure,Integer>{

    List<CommentClosure> findByDescendentId(long descendentId);

}
