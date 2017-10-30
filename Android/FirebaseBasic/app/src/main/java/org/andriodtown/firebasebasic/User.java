package org.andriodtown.firebasebasic;

import java.util.List;

/**
 * Created by user on 2017-10-29.
 */

public class User {

    public String user_id;
    public String username;
    public String email;
    public int age;

    // 내가 작성한 글 목록
    public List<Bbs> bbs;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,int age) {
        this.username = username;
        this.email = email;
        this.age=age;
    }

}
