package com.atomic.nexus.util;

/**
* Dante Pasionek created: com.atomic.nexus.util on Jul. 25, 2014 *
*/
public class StrUtil {

    public StrUtil() { }


    /**
     * @param c = Correction String
     * @param s = String to modify
     * @return - returns modified String (String without the correction character)
     */
    public static String remove(String c, String s) {
        return s.replaceAll(c, "");
    }

    /**
     * @param s = String to modify
     * @return - Returns modified String (removes all punctuation)
     */
    public static String removePunctuation(String s) {
            s = s.replaceAll("\\p{Punct}+", "");
        return s;
    }
}
