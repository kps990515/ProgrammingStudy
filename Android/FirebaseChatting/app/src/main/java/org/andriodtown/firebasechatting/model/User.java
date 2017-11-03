package org.andriodtown.firebasechatting.model;

/**
 * Created by user on 2017-11-03.
 */

public class User {
    public String id;
    public String email;
    public String token;
    public String phone;

    public User(){

    }

    public User(String id, String email, String token, String phone) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.phone = phone;
    }
}
