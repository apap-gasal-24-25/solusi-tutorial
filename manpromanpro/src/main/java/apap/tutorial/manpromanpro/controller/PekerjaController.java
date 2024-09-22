package apap.tutorial.manpromanpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tutorial.manpromanpro.service.PekerjaService;

@Controller
public class PekerjaController {
    @Autowired
    PekerjaService pekerjaService;

    @GetMapping("/pekerja/viewall")
    public String listPekerja(Model model) {
        var listPekerja = pekerjaService.getAllPekerja();

        model.addAttribute("listPekerja", listPekerja);
        return "viewall-pekerja";
    }

    @GetMapping("/pekerja/{id}")
    public String detailPekerja(@PathVariable("id") Long id, Model model) {
        var pekerja = pekerjaService.getPekerjaById(id);

        model.addAttribute("pekerja", pekerja);

        return "view-pekerja";
    }
}
