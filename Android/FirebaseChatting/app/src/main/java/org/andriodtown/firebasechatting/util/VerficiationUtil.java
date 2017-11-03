package org.andriodtown.firebasechatting.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2017-11-03.
 */

public class VerficiationUtil {

    public static boolean isValidPassword(String password) {
        boolean err = false;
        String regex = "^[A-Za-z0-9]{8,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

    public static boolean isValidName(String name){
        boolean err = false;
        String regex = "^[가-힣A-Za-z0-9]{2,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

    public static boolean isValidId(String Id){
        if(Id.length()<16){
            return true;
        }
        return false;
    }

    public static boolean isValidPhone(String phone){
        if(phone.length()<16){
            return true;
        }
        return false;
    }
}
