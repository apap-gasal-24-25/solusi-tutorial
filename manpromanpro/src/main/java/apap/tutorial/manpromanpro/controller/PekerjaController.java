package apap.tutorial.manpromanpro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import apap.tutorial.manpromanpro.restdto.request.UpdatePekerjaRequestRestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tutorial.manpromanpro.dto.request.AddPekerjaRequestDTO;
import apap.tutorial.manpromanpro.dto.request.DeleteMultiplePekerjaDTO;
import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.restdto.request.AddPekerjaRequestRestDTO;
import apap.tutorial.manpromanpro.service.PekerjaService;
import apap.tutorial.manpromanpro.service.ProyekService;
import jakarta.validation.Valid;

@Controller
public class PekerjaController {

    @Autowired
    PekerjaService pekerjaService;

    @Autowired
    ProyekService proyekService;

    @GetMapping("/pekerja/add")
    public String formAddPekerja(Model model) {
        var pekerjaDTO = new AddPekerjaRequestDTO();

        model.addAttribute("pekerjaDTO", pekerjaDTO);

        return "form-add-pekerja";
    }

    @PostMapping("/pekerja/add")
    public String addPekerja(@ModelAttribute("pekerjaDTO") AddPekerjaRequestDTO pekerjaDTO, Model model) {
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
    public String listPekerja(Model model) {
        try {
            var listPekerja = pekerjaService.getAllPekerja();
            var deleteDTO = new DeleteMultiplePekerjaDTO();

            model.addAttribute("listPekerja", listPekerja);
            model.addAttribute("deleteDTO", deleteDTO);
            return "viewall-pekerja";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "response-error-rest";
        }
    }

    @PostMapping("/pekerja/delete")
    public String deleteMultiplePekerja(
            @ModelAttribute DeleteMultiplePekerjaDTO deleteMultiplePekerjaDTO
    ){
        pekerjaService.deleteListPekerja(deleteMultiplePekerjaDTO.getListPekerja());

        return "success-delete-pekerja";
    }

    @GetMapping("/pekerja/rest/viewall")
    public String listRestPekerja(Model model) {
        try {
            var listPekerja = pekerjaService.getAllPekerjaFromRest();
            var deleteDTO = new DeleteMultiplePekerjaDTO();

            model.addAttribute("listPekerja", listPekerja);
            model.addAttribute("deleteDTO", deleteDTO);
            return "viewall-pekerja";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "response-error-rest";
        }
    }

    @GetMapping("/pekerja/rest/{id}")
    public String detailRestPekerja(@PathVariable("id") Long id, Model model) {
        try {
            var pekerja = pekerjaService.getPekerjaByIdFromRest(id);
            model.addAttribute("pekerja", pekerja);

            return "view-pekerja";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "response-error-rest";
        }
    }

    @GetMapping("/pekerja/rest/add")
    public String formAddRestPekerja(Model model) {
        var pekerjaDTO = new AddPekerjaRequestRestDTO();

        model.addAttribute("pekerjaDTO", pekerjaDTO);
        model.addAttribute("listProyek", proyekService.getAllProyek());

        return "form-add-pekerja-rest";
    }

    @PostMapping("/pekerja/rest/add")
    public String addPekerja(@Valid @ModelAttribute("pekerjaDTO") AddPekerjaRequestRestDTO pekerjaDTO, 
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listProyek", proyekService.getAllProyek());
            return "form-add-pekerja-rest";
        }
        
        try {
            var pekerja = pekerjaService.addPekerjaFromRest(pekerjaDTO);
            model.addAttribute("responseMessage",
                    String.format("Pekerja %s dengan ID %d berhasil ditambahkan.", pekerja.getNama(), 
                    pekerja.getId()));
            return "response-pekerja";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "response-error-rest";
        }
    }

    @GetMapping("/pekerja/rest/{id}/update")
    public String formUpdateRestPekerja(@PathVariable("id") Long id, Model model) {
        try {
            var pekerja = pekerjaService.getPekerjaByIdFromRest(id);
            var pekerjaDTO = new UpdatePekerjaRequestRestDTO();

            pekerjaDTO.setId(pekerja.getId());
            pekerjaDTO.setNama(pekerja.getNama());
            pekerjaDTO.setUsia(pekerja.getUsia());
            pekerjaDTO.setPekerjaan(pekerja.getPekerjaan());
            pekerjaDTO.setBiografi(pekerja.getBiografi());
            var listIdProyek = new ArrayList<UUID>();
            pekerja.getListProyek().forEach(proyek -> listIdProyek.add(proyek.getId()));
            pekerjaDTO.setListProyek(listIdProyek);

            model.addAttribute("pekerjaDTO", pekerjaDTO);
            model.addAttribute("listProyek", proyekService.getAllProyek());

            return "form-update-pekerja-rest";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "response-error-rest";
        }
    }

    @PostMapping("/pekerja/rest/update")
    public String updatePekerja(@Valid @ModelAttribute("pekerjaDTO") UpdatePekerjaRequestRestDTO pekerjaDTO,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listProyek", proyekService.getAllProyek());
            return "form-update-pekerja-rest";
        }

        try {
            var pekerja = pekerjaService.updatePekerjaFromRest(pekerjaDTO);
            model.addAttribute("responseMessage",
                    String.format("Pekerja %s dengan ID %d berhasil diubah.", pekerja.getNama(),
                            pekerja.getId()));
            return "response-pekerja";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "response-error-rest";
        }
    }
}
