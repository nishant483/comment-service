package com.intuit.commentservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "reaction_counts")
public class ReactionCounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public long id;

    public long post_id;
    public long comment_id;

    @Enumerated(EnumType.STRING)
    public ReactionType reaction_type;
    public int reactionCount;

    public ReactionCounts(Long post_id, Long comment_id, ReactionType reactionType){
            this.post_id = post_id;
            this.reaction_type = reactionType;
            this.reactionCount = 1;
            this.comment_id = comment_id;
    }

}
