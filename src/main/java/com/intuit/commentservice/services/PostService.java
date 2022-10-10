package com.intuit.commentservice.services;

import com.intuit.commentservice.models.*;
import com.intuit.commentservice.repository.ReactionCountsRepoInterface;
import com.intuit.commentservice.repository.ReactionRepoInterface;
import com.intuit.commentservice.repository.PostsRepoInterface;
import com.intuit.commentservice.repository.UserRepoInterface;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostsRepoInterface postsRepoInterface;
    private final ReactionRepoInterface reactionRepoInterface;
    private final ReactionCountsRepoInterface reactionCountsRepoInterface;

    private final UserRepoInterface userRepoInterface;

    public PostService(PostsRepoInterface postsRepoInterface, ReactionRepoInterface reactionRepoInterface, ReactionCountsRepoInterface reactionCountsRepoInterface, UserRepoInterface userRepoInterface) {
        this.postsRepoInterface = postsRepoInterface;
        this.reactionRepoInterface = reactionRepoInterface;
        this.reactionCountsRepoInterface = reactionCountsRepoInterface;
        this.userRepoInterface = userRepoInterface;
    }


    public Optional<Post> loadPost(@NonNull final Long postId){

        return postsRepoInterface.findById(postId);
    }

    public Post addPost(@NonNull String link_url, @NonNull PostType postType, @NonNull String text, @NonNull Long userId){
        return postsRepoInterface.save(new Post(link_url,postType,text,userId));
    }

    public boolean addReactionToPost(@NonNull Long postId, @NonNull Long userId, @NonNull ReactionType reactionType){
        if(reactionRepoInterface.findIfReactionExists(postId,userId,0L).isPresent())
                return false;
        reactionRepoInterface.save(new Reaction(reactionType,userId,postId, 0L));
        Optional<ReactionCounts> counts = reactionCountsRepoInterface.findByReactionTypeAAndComment_idAndPost_id(reactionType.toString(),postId,0L);
        if(counts.isPresent())
            reactionCountsRepoInterface.updateReactionCounter(reactionType.toString(),postId,0L);

        return true;
    }

    public boolean addReactionToComment(@NonNull Long postId,@NonNull Long commentId, @NonNull Long userId, @NonNull ReactionType reactionType){

        if(reactionRepoInterface.findIfReactionExists(postId,userId,commentId).isPresent())
            return false;

        reactionRepoInterface.save(new Reaction(reactionType,userId,postId, commentId));
        Optional<ReactionCounts> counts = reactionCountsRepoInterface.findByReactionTypeAAndComment_idAndPost_id(reactionType.toString(),postId,0L);
        if(counts.isPresent())
            reactionCountsRepoInterface.updateReactionCounter(reactionType.toString(),postId,commentId);
        else
            reactionCountsRepoInterface.save(new ReactionCounts(postId,commentId,reactionType));

        return true;

    }

    public List<User> getReactedUsers(@NonNull Long postId, @NonNull Long commentId,ReactionType reactionType, int start, int limit){

        Pageable page = PageRequest.of(start, limit);

        return userRepoInterface.findReactions(postId,commentId,reactionType.toString(),page);
    }

}
