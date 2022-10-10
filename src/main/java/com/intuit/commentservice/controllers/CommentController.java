package com.intuit.commentservice.controllers;

import com.intuit.commentservice.models.Comment;
import com.intuit.commentservice.models.PostType;
import com.intuit.commentservice.models.Response;
import com.intuit.commentservice.services.CommentService;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/comments",produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/getComments", produces = "application/json")
    public @ResponseBody List<Response> getComments(@RequestParam("depth") int depth
            , @RequestParam("parent_comment_id") @NonNull Long parent_comment_id, @RequestParam("postId") @NonNull Long postId, @RequestParam("start") @NonNull int start, @RequestParam("limit") @NonNull int limit) {
        return commentService.findComments(postId,parent_comment_id,depth,start,limit);
    }

    @GetMapping(value = "/addCommentToReply", produces = "application/json")
    public @ResponseBody Comment addCommentsToReply(@RequestParam("comment_text") String comment_text, @RequestParam("user_id") Long user_id,
             @RequestParam("parent_comment_id") @NonNull Long parent_comment_id, @RequestParam("postId") @NonNull Long postId) {
        return commentService.addCommentToReply(postId,user_id,comment_text,parent_comment_id);
    }

    @GetMapping(value = "/addCommentToPost", produces = "application/json")
    public @ResponseBody Comment addCommentsToPost(@RequestParam("comment_text") String comment_text, @RequestParam("user_id") Long user_id,
                                                   @RequestParam("postId") @NonNull Long postId) {
        return commentService.addCommentToPost(postId,user_id,comment_text);
    }

}
