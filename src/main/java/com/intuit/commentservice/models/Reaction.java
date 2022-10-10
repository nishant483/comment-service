package com.intuit.commentservice.models;

import com.intuit.commentservice.util.IdentifierCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "reaction")
public class Reaction {

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Long user_id;
    private Timestamp createdTimestamp;
    private Long post_id;
    private Long comment_id;

    public Reaction(ReactionType reactionType, Long user_id, Long post_id, Long comment_id) {
        this.reactionType = reactionType;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.createdTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
