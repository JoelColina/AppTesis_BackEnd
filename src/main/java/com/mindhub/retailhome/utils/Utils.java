package com.mindhub.retailhome.utils;

import java.time.Month;

public class Utils {

    public static String getConvierteMes(Month nombreMes){

        Month mes = Month.valueOf(nombreMes.toString());
        int largo = 2;

        return String.format("%0" + largo  + "d",  mes.getValue());
    }
}
