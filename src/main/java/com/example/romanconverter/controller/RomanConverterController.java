package com.example.romanconverter.controller;

import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.romanconverter.model.RomanConverter;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RomanConverterController {

    @GetMapping(value = "/roman-converter/{roman}")
    public String romanConverterWithPathVeriable(@PathVariable(value = "roman") String roman, Model model) {
        return getRomanConverterPage(roman, model);
    }

    @GetMapping(value = "/roman-converter")
    public String romanConverterWithReqParam(@RequestParam(value = "roman") String roman, Model model) {
        return getRomanConverterPage(roman, model);
    }
    
    private String getRomanConverterPage(String roman, Model model) {
        final RomanConverter romanConverter = new RomanConverter(roman);
        model.addAttribute("romanConverter", romanConverter);
        return "romanConverterPage.html";
    }
}
