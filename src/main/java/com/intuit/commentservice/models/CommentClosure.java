package com.intuit.commentservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "comment_closure")
@NoArgsConstructor
public class CommentClosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    private long ancestorId;
    private long descendentId;
    private int depth;

    public CommentClosure(long ancestorId, long descendentId, int depth){

        this.ancestorId = ancestorId;
        this.depth = depth;
        this.descendentId = descendentId;

    }

}
