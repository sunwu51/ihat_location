package myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class test {
    public static void main(String[] args) {
        String aString = "";
        if (aString.isEmpty())
            System.out.println("孔");
    }

    private static String lpad(int length, String number) {
        if (number.length() >= length)
            return number;
        else {
            return "0" + number;
        }
    }
}