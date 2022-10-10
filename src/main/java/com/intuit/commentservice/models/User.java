package com.intuit.commentservice.models;

import com.intuit.commentservice.util.IdentifierCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "SOCIAL_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Setter
    @NonNull
    private String name;

    @Setter
    @NonNull
    private String email;

    @NonNull
    public Timestamp createdTimestamp;

    public User(@NonNull final String name, @NonNull final String email) {
        this.name = name;
        this.email = email;
        this.createdTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
