package edu.iu.c212.programs;

import edu.iu.c212.models.Staff;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class SawPrimePlanks {
    public static void main(String[] args) {

    }



    public static List<Integer> getPlankLength(int longPlankLength) {
        List<Integer> ren = new ArrayList<>();
        int cutLen = sawPlank(longPlankLength);

        // update inventory with new plank width and recalculate the count and the price \

        for (int i = 0; i < longPlankLength/cutLen; i++) {
            ren.add(cutLen);
        }




        //writing to output
        for (Integer e : ren) {
            try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
                String temp = e.toString();
                pw.println(temp);
            } catch (IOException ex) {
                System.out.println("IO Exception");
            }
        }
        return null;

    }







    public static int sawPlank(int plankLength) {
        if (plankLength <= 2) {
            return plankLength;
        }
        int divNum = 2;
        while (plankLength % divNum != 0) {
            divNum++;
        }
        boolean primeNum = isPrime(plankLength / divNum);
        if (primeNum) {
            return plankLength / divNum;
        } else {
            return sawPlank(plankLength / divNum);
        }
    }















    // Helper method to check if a number is prime
    private static boolean isPrime ( int number){
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}



