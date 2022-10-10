package com.intuit.commentservice.models;

import com.intuit.commentservice.util.IdentifierCreator;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @NonNull
    private Long userId;

    @NonNull
    private Long postId;

    @NonNull
    private long parentId;

    @NonNull
    private String comment_text;

    @NonNull
    private Timestamp createdTimestamp;


    public Comment(@NonNull Long userId, @NonNull Long postId, @NonNull String comment_text,long parentId) {
        this.userId = userId;
        this.postId = postId;
        this.parentId = parentId;
        this.comment_text = comment_text;
        this.createdTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
