package com.intuit.commentservice.models;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum PostType {

    TEXT("TEXT"),
    LINK("LINK"),
    IMAGE("IMAGE"),
    VIDEO("VIDEO");

    @NonNull
    public final String post_type_string;

    PostType(String post_type_string) {
        this.post_type_string = post_type_string;
    }
}
