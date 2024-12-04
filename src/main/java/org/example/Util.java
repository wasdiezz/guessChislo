package org.example;

public class Util {
    public static boolean canConvertStringToInt(String str) {
        try {
            Integer.parseInt(str);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
