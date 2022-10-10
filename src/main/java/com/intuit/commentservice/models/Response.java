package com.intuit.commentservice.models;

import java.util.ArrayList;
import java.util.List;

public class Response {

    public String comment_text;
    public List<ReactionCounts> reactionCounts;


    public List<Response> childrens;

    public Response(String comment_text,List<ReactionCounts> reactionCounts){
        this.comment_text = comment_text;
        this.reactionCounts = reactionCounts;
        childrens = new ArrayList<>();
    }

}
