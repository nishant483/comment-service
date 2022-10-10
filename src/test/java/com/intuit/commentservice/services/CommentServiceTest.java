package com.intuit.commentservice.services;

import com.intuit.commentservice.models.*;
import com.intuit.commentservice.repository.CommentsRepoInterface;
import com.intuit.commentservice.util.IdentifierCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CommentServiceTest {


    @Autowired private CommentsRepoInterface commentsRepoInterface;
    @Autowired private CommentService commentService;
    @Autowired private PostService postService;
    @Autowired private UserService userService;

    @Test
    void addComment() {

    }

    @Test
    void addCommentToPost() {

        User user = userService.createUser("nishant@xeno.in","nishant");
        Optional<Post> post = postService.loadPost(2L);
        assert post.isPresent();

        String insertedComment = "base_level_test_comment";
        commentService.addCommentToPost(post.get().getId(), user.getId(),insertedComment);
        List<Response> responseList = commentService.findComments(post.get().getId(), 0L,1);
        boolean isCommentPresent = false;

        for(Response response:responseList){
            if (response.comment_text.equals(insertedComment)) {
                isCommentPresent = true;
                break;
            }
        }

        assert isCommentPresent;


    }

    @Test
    void addCommentToReply() {

        User user = userService.createUser("nishant@xeno.in","nishant");
        Optional<Post> post = postService.loadPost(2L);
        assert post.isPresent();

        String insertedComment = "base_level_test_comment_2";
        Comment comment = commentService.addCommentToPost(post.get().getId(), user.getId(),insertedComment);

        insertedComment = "base_level_test_comment_2_reply";
        Comment comment2 = commentService.addCommentToReply(post.get().getId(), user.getId(),insertedComment,comment.getId());

        assert (comment2.getId()>0);



    }

    @Test
    void findComments() {

        User user = userService.createUser("nishant@xeno.in","nishant");
        Optional<Post> post = postService.loadPost(2L);
        assert post.isPresent();

        List<Response> responseList = commentService.findComments(post.get().getId(), 0L,2);
        assert responseList.size()>1;

    }
}