package com.mindhub.retailhome.utils;

public class NumberAccountRandom {

    public static String getRandomNumber() {

        String number = "VIN";
        int max = 99999999;
        int min = 1;

        String newAccount = number + ((int)(Math.random() * (max - min)) + min);

        return newAccount;
    }
}
