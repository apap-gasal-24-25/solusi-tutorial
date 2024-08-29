package com.example.romanconverter.model;

import java.util.HashMap;

public class RomanConverter {
    
    private String roman;
    private static HashMap<Character, Integer> map = new HashMap<>();

    public RomanConverter(String roman) {
        this.roman = roman;
        initializeMap();
    }

    public String getRoman() {
        return roman;
    }

    private void initializeMap() {
        map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }


    public int convertRomanToDecimal() {
        int res = 0;
        int curr = 0;
        int next = 0;

        int i = roman.length()-1;
        while (i >= 0) {

            curr = map.get(roman.charAt(i));
            next = (i == 0) ? 0 : map.get(roman.charAt(i-1));

            if (curr > next) {
                res += curr;
                res -= next;
                i -= 2;
            } else {
                res += curr;
                i--;
            }

            curr = 0;
            next = 0;

        }
        return res;
    }
}
