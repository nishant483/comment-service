package com.intuit.commentservice.models;

public enum ReactionType {
    LIKE("LIKE"),
    DISLIKE("DISLIKE");

    private final String reaction_type;

    ReactionType(String reaction_type) {
        this.reaction_type = reaction_type;
    }
}
