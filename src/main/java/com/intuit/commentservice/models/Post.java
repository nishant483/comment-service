package com.intuit.commentservice.models;

import com.intuit.commentservice.util.IdentifierCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NonNull
    public String link_url;

    @NonNull
    public PostType postType;

    @NonNull
    public String text;

    @NonNull
    public Timestamp createdTimestamp;

    @NonNull
    private Long userId;

    public Post(@NonNull String link_url, @NonNull PostType postType, @NonNull String text, @NonNull Long userId){
            this.link_url = link_url;
            this.postType = postType;
            this.text = text;
            this.createdTimestamp = new Timestamp(System.currentTimeMillis());
            this.userId  = userId;
    }

}
