package com.intuit.commentservice.services;

import com.intuit.commentservice.models.Comment;
import com.intuit.commentservice.models.CommentClosure;
import com.intuit.commentservice.models.ReactionCounts;
import com.intuit.commentservice.models.Response;
import com.intuit.commentservice.repository.CommentsClosureRepo;
import com.intuit.commentservice.repository.CommentsRepoInterface;
import com.intuit.commentservice.repository.ReactionCountsRepoInterface;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {


    private final ReactionCountsRepoInterface reactionCountsRepoInterface;
    private final CommentsRepoInterface commentsRepoInterface;

    private final CommentsClosureRepo commentsClosureRepo;

    public CommentService(ReactionCountsRepoInterface reactionCountsRepoInterface, CommentsRepoInterface commentsRepoInterface, CommentsClosureRepo commentsClosureRepo) {
        this.reactionCountsRepoInterface = reactionCountsRepoInterface;
        this.commentsRepoInterface = commentsRepoInterface;
        this.commentsClosureRepo = commentsClosureRepo;
    }

    public Comment addCommentToPost(Long postId,Long userId,String comment_text){
        Comment comment =  commentsRepoInterface.save(new Comment(userId,postId,comment_text,0));
        commentsClosureRepo.save(new CommentClosure(comment.getId(),comment.getId(),0));
        commentsClosureRepo.save(new CommentClosure(0,comment.getId(),0));
        return comment;
    }

    public Comment addCommentToReply(Long postId,Long userId,String comment_text,long parentId){
        Comment comment =  commentsRepoInterface.save(new Comment(userId,postId,comment_text,parentId));
        List<CommentClosure> commentClosures = new ArrayList<>();
        commentClosures.add(new CommentClosure(comment.getId(),comment.getId(),0));


        List<CommentClosure> parentcommentClosures = commentsClosureRepo.findByDescendentId(parentId);

        for (CommentClosure parentcommentClosure:parentcommentClosures
             ) {
            commentClosures.add(new CommentClosure(parentcommentClosure.getAncestorId(),comment.getId(),
                                                        parentcommentClosure.getDepth()+1));
        }

        commentsClosureRepo.saveAll(commentClosures);

        return comment;


    }

    public List<Response> findComments(Long postId,Long parent_comment_id,int depth){
            Pageable topTwenty = PageRequest.of(0, 20);
            List<Comment> comments = commentsRepoInterface.findCommentsByTimestamp(postId,parent_comment_id,depth,topTwenty);

            HashMap<Long,Response> commentResponseMap = new HashMap<>();

            commentResponseMap.put(parent_comment_id,new Response(null,null));

            for (Comment comment:comments
                 ) {

                    List<ReactionCounts> reactionCounts = reactionCountsRepoInterface.findByComment_idAndPost_id(comment.getPostId(),comment.getId());
                    Response response = new Response(comment.getComment_text(),reactionCounts);
                    commentResponseMap.put(comment.getId(),response);

                    if(commentResponseMap.containsKey(comment.getParentId()))
                        commentResponseMap.get(comment.getParentId()).childrens.add(response);

            }

            return commentResponseMap.get(parent_comment_id).childrens;


    }

}
