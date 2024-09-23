package apap.tutorial.manpromanpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.request.DeleteMultiplePekerjaDTO;
import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.service.PekerjaService;
import apap.tutorial.manpromanpro.service.ProyekService;

@Controller
public class PekerjaController {

    @Autowired
    PekerjaService pekerjaService;

    @Autowired
    ProyekService proyekService;

    @GetMapping("/pekerja/add")
    public String formAddDeveloper(Model model) {
        var pekerjaDTO = new AddPekerjaRequestDTO();

        model.addAttribute("pekerjaDTO", pekerjaDTO);

        return "form-add-pekerja";
    }

    @PostMapping("/pekerja/add")
    public String addDeveloper(@ModelAttribute("developerDTO") AddPekerjaRequestDTO pekerjaDTO, Model model) {
        var pekerja = new Pekerja();
        pekerja.setNama(pekerjaDTO.getNama());
        pekerja.setBiografi(pekerjaDTO.getBiografi());
        pekerja.setUsia(pekerjaDTO.getUsia());
        pekerja.setPekerjaan(pekerjaDTO.getPekerjaan());

        pekerjaService.addPekerja(pekerja);

        model.addAttribute("responseMessage",
                String.format("Pekerja %s dengan ID %s berhasil ditambahkan.", pekerja.getNama(), pekerja.getId()));
        return "response-pekerja";
    }

    @GetMapping("/pekerja/viewall")
    public String listDeveloper(Model model) {
        var listPekerja = pekerjaService.getAllPekerja();
        var deleteDTO = new DeleteMultiplePekerjaDTO();

        model.addAttribute("listPekerja", listPekerja);
        model.addAttribute("deleteDTO", deleteDTO);
        return "viewall-pekerja";
    }

    @PostMapping("/pekerja/delete")
    public String deleteMultiplePekerja(
        @ModelAttribute DeleteMultiplePekerjaDTO deleteMultiplePekerjaDTO
    ){
        pekerjaService.deleteListPekerja(deleteMultiplePekerjaDTO.getListPekerja());

        return "success-delete-pekerja";        
    }
}
