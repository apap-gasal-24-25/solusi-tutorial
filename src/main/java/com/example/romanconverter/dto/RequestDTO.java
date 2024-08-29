package com.example.romanconverter.dto;

public class RequestDTO {
    private String roman;

    public String getRoman() {
        return roman;
    }

    public void setRoman(String roman) {
        this.roman = roman;
    }

    public RequestDTO() {
    }

    public RequestDTO(String roman) {
        this.roman = roman;
    }
}
