package ru.rarescrap.rsstats.utils;

public class DescriptionCutter {
    public static String[] cut(int numberOfWords, String sourceString) {
        sourceString = sourceString + " ";
        String[] asd = sourceString.split(" ");
        String[] returnStrings = new String[(int)java.lang.Math.ceil(sourceString.split(" ").length / (double) numberOfWords)];
        for (int i = 0, j = 0; i < asd.length; i+=numberOfWords, j++) {
            returnStrings[j] = "";
            for (int g = i; g < i+numberOfWords; g++) {
                if (g < asd.length) {
                    returnStrings[j] += asd[g] + " ";
                }
            }
        }
        return returnStrings;
    }
}