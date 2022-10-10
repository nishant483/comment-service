package com.intuit.commentservice.repository;

import com.intuit.commentservice.models.CommentClosure;
import com.intuit.commentservice.models.Post;
import com.intuit.commentservice.models.PostType;
import com.intuit.commentservice.models.User;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepoInterface extends CrudRepository<Post,Integer> {

    Optional<Post> findById(long id);

}

