package com.intuit.commentservice.controllers;

import com.intuit.commentservice.models.*;
import com.intuit.commentservice.services.PostService;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/posts",produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/createPost", produces = "application/json")
    public @ResponseBody Post createPost(@RequestParam("link_url") String link_url, @RequestParam("post_type") @NonNull PostType post_type
    , @RequestParam("text") @NonNull String text, @RequestParam("userId") @NonNull Long userId) {
        return postService.addPost(link_url,post_type,text,userId);
    }

    @GetMapping(value = "/getPostById", produces = "application/json")
    public @ResponseBody Optional<Post> viewPost(@RequestParam("postId") Long postId) {
        return postService.loadPost(postId);
    }

    @GetMapping(value = "/addReactionToComment", produces = "application/json")
    public @ResponseBody boolean addReactionToComment(@RequestParam("postId") Long postId,
    @RequestParam("commentId") Long commentId,@RequestParam("userId") Long userId,@RequestParam("reactionType") ReactionType reactionType
    ) {
        return postService.addReactionToComment(postId,commentId,userId,reactionType);
    }

    @GetMapping(value = "/addReactionToPost", produces = "application/json")
    public @ResponseBody boolean addReactionToPost( @RequestParam("commentId") Long commentId,@RequestParam("userId") Long userId,@RequestParam("reactionType") ReactionType reactionType
    ) {
        return postService.addReactionToPost(commentId,userId,reactionType);
    }

    @GetMapping(value = "/getReactedUsers", produces = "application/json")
    public @ResponseBody List<User> getReactedUsers(@RequestParam("postId") Long postId,
                                                    @RequestParam("start") int start,
                                                    @RequestParam("limit") int limit,
                                                    @RequestParam("commentId") Long commentId, @RequestParam("reactionType") ReactionType reactionType
    ) {
        return postService.getReactedUsers(postId,commentId,reactionType,start,limit);
    }



}
