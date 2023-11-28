package org.banksys.info.Servicios;

import java.util.Scanner;

public class scannerinput {

    public static Scanner scanner;

    public static Scanner getScanner(){
        if(scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeScanner(){
        if(scanner != null){
            scanner.close();
        }
    }

}
