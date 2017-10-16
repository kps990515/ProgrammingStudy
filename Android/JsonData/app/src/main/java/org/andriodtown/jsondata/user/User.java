package org.andriodtown.jsondata.user;

/**
 * Created by user on 2017-10-16.
 */

public class User {
     String login;
     int id;
     String avatar_url;
     String gravatar_id;
     String url;
     String html_url;
     String followers_url;
     String following_url;
     String gists_url;
     String starred_url;
     String subscriptions_url;
     String organizations_url;
     String repos_url;
     String events_url;
     String received_events_url;
     String type;
     boolean site_admin;

    public int getId(){
        return id;
    }

    public String getLogin(){
        return login;
    }

    public String getAvatar_url(){
        return avatar_url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
