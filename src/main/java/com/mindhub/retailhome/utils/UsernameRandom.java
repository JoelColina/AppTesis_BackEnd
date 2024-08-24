package com.mindhub.retailhome.utils;

import java.security.SecureRandom;
import java.util.Date;

public class UsernameRandom {
    public static String userNameRandom( String value) {
        String length = "000000000";
        int min_val = 100;
        int max_val = 1000000;
        SecureRandom rand = new SecureRandom();
        rand.setSeed(new Date().getTime());
        long num = rand.nextInt((max_val - min_val) + 1) + min_val;
        int numLength =  Long.toString(num).length();
        String newNum = Long.toString(num);
        int numSubString = 8-numLength;
        String complete = length.substring(0,numSubString);
        return value.concat(complete).concat(newNum);
    }
}
