package com.example.romanconverter.model;

public class IntegerConverter {
    private int num;
    private static int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    
    public IntegerConverter(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public static String convertIntegerToRoman(int num) {
        
        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {  
            while(num >= values[i]) {  
                num = num - values[i];  
                roman.append(romanLetters[i]);  
            }  
        }  
        return roman.toString();  
    }
}

/*
 * Source:
 * https://www.javatpoint.com/convert-integer-to-roman-numerals-in-java
 */