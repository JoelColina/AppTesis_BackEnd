package com.mindhub.homebanking.utils;

public class NumberCardRandom {

    public static int getNumberCvv() {

        int max = 999;
        int min = 1;

        return ((int)(Math.random() * (max - min)) + min);
    }
    public static String getNumberCard() {

        int max = 9999;
        int min = 1;

        String newNumCard = ((int)(Math.random() * (max - min)) + min) + "-" + ((int)(Math.random() * (max - min)) + min)  + "-";
        newNumCard = newNumCard +  ((int)(Math.random() * (max - min)) + min) + "-" + ((int)(Math.random() * (max - min)) + min);

        return newNumCard;
    }
}
