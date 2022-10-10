package com.intuit.commentservice.services;

import com.intuit.commentservice.models.*;
import com.intuit.commentservice.util.IdentifierCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Test
    void loadPost() {
        Optional<Post> post = postService.loadPost(1L);
        assert post.isPresent();
    }

    @Test
    void addPost() {

        String post_text = "This is a testing post "+ IdentifierCreator.randomGenerator();;
        User user = userService.findByEmail("nishant@xeno.in");
        Post post = postService.addPost("link.com", PostType.TEXT,post_text,user.getId());
        assert post.getText().equals(post_text);

    }

    @Test
    void addReactionToPost() {

        String post_text = "This is a testing post - "+ IdentifierCreator.randomGenerator();
        User user = userService.findByEmail("nishant@xeno.in");
        Post post = postService.addPost("link.com", PostType.TEXT,post_text,user.getId());
        assert postService.addReactionToPost(post.getId(),user.getId(), ReactionType.LIKE);


    }

    @Test
    void addReactionToComment() {

        String post_text = "This is a testing post - "+ IdentifierCreator.randomGenerator();
        User user = userService.findByEmail("nishant@xeno.in");
        Post post = postService.addPost("link.com", PostType.TEXT,post_text,user.getId());
        String comment_text = "This is a testing comment - "+ IdentifierCreator.randomGenerator();
        Comment comment = commentService.addCommentToPost(post.getId(),user.getId(),comment_text);
        assert postService.addReactionToComment(post.getId(),comment.getId(),user.getId(), ReactionType.LIKE);

    }

    @Test
    void getReactedUsers() {

        List<User> usersList = postService.getReactedUsers(1L,4L,ReactionType.LIKE,0,10);
        assert usersList.size()>0;

    }
}