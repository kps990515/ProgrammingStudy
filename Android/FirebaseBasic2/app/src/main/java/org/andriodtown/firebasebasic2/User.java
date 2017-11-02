package org.andriodtown.firebasebasic2;

/**
 * Created by user on 2017-10-31.
 */

public class User {
    String id;
    String token;
    String email;

    public User(){

    }

    public User(String id, String token, String email){
        this.id = id;
        this.token = token;
        this.email = email;
    }
}
