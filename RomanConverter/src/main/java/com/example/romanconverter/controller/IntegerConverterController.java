package com.example.romanconverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.romanconverter.dto.IntegerRequestDTO;
import com.example.romanconverter.model.IntegerConverter;


@Controller
public class IntegerConverterController {
    

    @GetMapping("/convert-integer")
    public String getPage(Model model) {
        IntegerRequestDTO dto = new IntegerRequestDTO();
        model.addAttribute("integerRequestDTO", dto);
        return "form-integer-converter.html";
    }

    @PostMapping("/convert-integer")
    public String submit(
        @ModelAttribute IntegerRequestDTO dto,    
        Model model
    ) {
        int intFromView = dto.getNum();
        final IntegerConverter converter = new IntegerConverter(intFromView);
        model.addAttribute("integerConverter", converter);
        return "view-conversion-result-integer.html";
    }

}
