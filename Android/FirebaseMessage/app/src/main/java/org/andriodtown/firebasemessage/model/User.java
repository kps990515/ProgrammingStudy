package org.andriodtown.firebasemessage.model;

/**
 * Created by user on 2017-11-03.
 */

public class User {
    public String id;
    public String name;
    public String email;
    public String token;
    public String birthday;
    public String gender;
    public String phone_number;
    public String chat_id;

    public User(){
        // default for firebase
    }

    public User(String id, String name, String email, String token, String birthday, String gender, String phone_number){
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
        this.birthday = birthday;
        this.gender = gender;
        this.phone_number = phone_number;
    }
}
