package com.mindhub.retailhome.utils;

import java.security.SecureRandom;
import java.util.Date;

public class UsernameRandom {
    public static long userNameRandom( long value) {
        String length = "000000000";
        int min_val = 100;
        int max_val = 1000000;
        SecureRandom rand = new SecureRandom();
        rand.setSeed(new Date().getTime());
        long num = rand.nextInt((max_val - min_val) + 1) + min_val;
        int numLength =  Long.toString(num).length();
        long newNum = Long.parseLong(Long.toString(num));
        int numSubString = 8-numLength;
        long complete = Long.parseLong(length.substring(0,numSubString));
        return value + complete + newNum;
    }
}
