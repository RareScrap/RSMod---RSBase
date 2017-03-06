package ru.rarescrap.rsstats.utils;

public class DescriptionCutter {
    public String[] cut(int numberOfWords, String sourceString) {
        int i;
        sourceString = sourceString + " ";
        String[] returnStrings = new String[(int)java.lang.Math.ceil(sourceString.split(" ").length / numberOfWords)];
        for (i = 0; i < returnStrings.length; ++i) {
            returnStrings[i] = "";
        }
        for (i = 0; i < returnStrings.length; ++i) {
            for (int j = 0; j < numberOfWords; ++j) {
                String word = sourceString.substring(0, sourceString.indexOf(" "));
                String[] arrstring = returnStrings;
                int n = i;
                arrstring[n] = arrstring[n] + word + " ";
                sourceString = sourceString.replace(word + " ", "");
            }
            returnStrings[i] = returnStrings[i].substring(0, returnStrings[i].length() - 1);
        }
        return returnStrings;
    }
}