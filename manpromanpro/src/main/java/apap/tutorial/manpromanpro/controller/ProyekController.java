package apap.tutorial.manpromanpro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.manpromanpro.dto.request.AddProyekRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateProyekRequestDTO;
import apap.tutorial.manpromanpro.model.Pekerja;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.service.DeveloperService;
import apap.tutorial.manpromanpro.service.PekerjaService;
import apap.tutorial.manpromanpro.service.ProyekService;
import jakarta.validation.Valid;

@Controller
public class ProyekController {
    
    @Autowired
    private ProyekService proyekService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private PekerjaService pekerjaService;

    enum StatusLevel {
        STARTED,
        ONGOING,
        FINISHED,
    }

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @GetMapping("/proyek/add")
    public String addProyekForm(Model model) {

        var proyekDTO = new AddProyekRequestDTO();

        model.addAttribute("proyekDTO", proyekDTO);
        model.addAttribute("listDeveloper", developerService.getAllDeveloper());
        model.addAttribute("statusLevel", StatusLevel.values());
        model.addAttribute("listPekerjaExisting", pekerjaService.getAllPekerja());

        return "form-add-proyek";
    }

    @PostMapping("/proyek/add")
    public String addProyek(@ModelAttribute @Valid AddProyekRequestDTO proyekDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());

            model.addAttribute("errors", errors);
            return "response-error";
        }
        
        var proyek = new Proyek();
        proyek.setNama(proyekDTO.getNama());
        proyek.setDeskripsi(proyekDTO.getDeskripsi());
        proyek.setTanggalMulai(proyekDTO.getTanggalMulai());
        proyek.setTanggalSelesai(proyekDTO.getTanggalSelesai());
        proyek.setStatus(proyekDTO.getStatus());
        proyek.setDeveloper(proyekDTO.getDeveloper());
        proyek.setListPekerja(proyekDTO.getListPekerja());

        proyekService.addProyek(proyek);
        
        model.addAttribute("responseMessage",
                String.format("Proyek %s dengan ID %s berhasil ditambahkan.", proyek.getNama(), proyek.getId()));

        return "response-proyek";
    }

    @PostMapping(value="/proyek/add", params={"addRow"})
    public String addRowDeveloperProyek(@ModelAttribute AddProyekRequestDTO addProyekRequestDTO, Model model){
        
        if(addProyekRequestDTO.getListPekerja() == null || addProyekRequestDTO.getListPekerja().isEmpty()){
            addProyekRequestDTO.setListPekerja(new ArrayList<>());
        }

        addProyekRequestDTO.getListPekerja().add(new Pekerja());

        model.addAttribute("listPekerjaExisting", pekerjaService.getAllPekerja());
        model.addAttribute("listDeveloper", developerService.getAllDeveloper());
        model.addAttribute("proyekDTO", addProyekRequestDTO);
        model.addAttribute("statusLevel", StatusLevel.values());

        return "form-add-proyek";
    }

    @PostMapping(value="/proyek/add", params={"deleteRow"})
    public String deleteRowDeveloperProyek(@ModelAttribute AddProyekRequestDTO addProyekRequestDTO,
    @RequestParam("deleteRow") int row,
    Model model){
        addProyekRequestDTO.getListPekerja().remove(row);

        model.addAttribute("listPekerjaExisting", pekerjaService.getAllPekerja());
        model.addAttribute("listDeveloper", developerService.getAllDeveloper());
        model.addAttribute("proyekDTO", addProyekRequestDTO);
        model.addAttribute("statusLevel", StatusLevel.values());

        return "form-add-proyek";
    }

    @GetMapping("/proyek/viewall")
    public String listProyek(
            @RequestParam(value = "nama", required = false) String searchNama, 
            @RequestParam(value = "status", required = false) String searchStatus, 
            Model model) {

        List<Proyek> listProyek;

        if (StringUtils.isBlank(searchNama) && StringUtils.isBlank(searchStatus)) {
            // Jika search field kosong, tampilkan semuanya
            listProyek = proyekService.getAllProyek();
        } else if (!StringUtils.isBlank(searchNama) && !StringUtils.isBlank(searchStatus)){
            // Search menggunakan nama dan status
            listProyek = proyekService.getProyekByNamaAndStatus(searchNama, searchStatus);
        } else if (!StringUtils.isBlank(searchNama)) {
            // Search menggunakan nama
            listProyek = proyekService.getProyekByNama(searchNama);
        } else {
            // Search menggunakan status
            listProyek = proyekService.getProyekByStatus(searchStatus);
        }

        model.addAttribute("listProyek", listProyek);
        model.addAttribute("statusLevel", StatusLevel.values());
        model.addAttribute("searchNama", searchNama );
        model.addAttribute("searchStatus", searchStatus );

        return "viewall-proyek";
    }

    @GetMapping("/proyek/{id}")
    public String detailProyek(@PathVariable("id") UUID id, Model model) {
        var proyek = proyekService.getProyekById(id);

        model.addAttribute("proyek", proyek);

        return "view-proyek";
    }

    @GetMapping("/proyek/{id}/update")
    public String updateProyek(@PathVariable("id") UUID id, Model model) {
        var proyek = proyekService.getProyekById(id);

        var proyekDTO = new UpdateProyekRequestDTO();
        proyekDTO.setId(proyek.getId());
        proyekDTO.setNama(proyek.getNama());
        proyekDTO.setDeskripsi(proyek.getDeskripsi());
        proyekDTO.setTanggalMulai(proyek.getTanggalMulai());
        proyekDTO.setTanggalSelesai(proyek.getTanggalSelesai());
        proyekDTO.setStatus(proyek.getStatus());
        // proyekDTO.setDeveloper(proyek.getDeveloper());

        model.addAttribute("proyekDTO", proyekDTO);
        model.addAttribute("listDeveloper", developerService.getAllDeveloper());
        model.addAttribute("statusLevel", StatusLevel.values());

        return "form-update-proyek";
    }

    @PostMapping("/proyek/update")
    public String updateProyek(@ModelAttribute @Valid UpdateProyekRequestDTO proyekDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());

            model.addAttribute("errors", errors);
            return "response-error";
        }
        
        var proyekFromDTO = new Proyek();
        proyekFromDTO.setId(proyekDTO.getId());
        proyekFromDTO.setNama(proyekDTO.getNama());
        proyekFromDTO.setDeskripsi(proyekDTO.getDeskripsi());
        proyekFromDTO.setTanggalMulai(proyekDTO.getTanggalMulai());
        proyekFromDTO.setTanggalSelesai(proyekDTO.getTanggalSelesai());
        proyekFromDTO.setStatus(proyekDTO.getStatus());
        // proyekFromDTO.setDeveloper(proyekDTO.getDeveloper());

        var proyek = proyekService.updateProyek(proyekFromDTO);

        model.addAttribute("responseMessage",
                String.format("Proyek %s dengan ID %s berhasil diupdate.", proyek.getNama(), proyek.getId()));

        return "response-proyek";
    }

    @GetMapping("/proyek/{id}/delete")
    public String deleteProyek(@PathVariable("id") UUID id, Model model) {
        var proyek = proyekService.getProyekById(id);
        proyekService.deleteProyek(proyek);

        model.addAttribute("responseMessage",
                String.format("Proyek %s dengan ID %s berhasil dihapus.", proyek.getNama(), proyek.getId()));

        return "response-proyek";
    }

}
