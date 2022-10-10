package com.intuit.commentservice.util;

import java.util.UUID;

public class IdentifierCreator {

    public static String randomGenerator(){
            return UUID.randomUUID().toString();
    }


}
