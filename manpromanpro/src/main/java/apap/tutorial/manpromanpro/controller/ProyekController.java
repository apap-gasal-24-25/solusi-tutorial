package apap.tutorial.manpromanpro.controller;

import java.util.List;
import java.util.UUID;

import apap.tutorial.manpromanpro.dto.ProyekMapper;
import apap.tutorial.manpromanpro.dto.request.UpdateProyekRequestDTO;
import apap.tutorial.manpromanpro.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.manpromanpro.dto.request.CreateProyekRequestDTO;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.service.ProyekService;

@Controller
public class ProyekController {

    @Autowired
    private ProyekMapper proyekMapper;
    
    @Autowired
    private ProyekService proyekService;

    @Autowired
    private DeveloperService developerService;

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @GetMapping("/proyek/add")
    public String addProyekForm(Model model) {

        var proyekDTO = new CreateProyekRequestDTO();

        model.addAttribute("proyekDTO", proyekDTO);
        model.addAttribute("listDeveloper", developerService.getAllDeveloper());

        return "form-add-proyek";
    }

    @PostMapping("/proyek/add")
    public String addProyek(@ModelAttribute CreateProyekRequestDTO proyekDTO, Model model) {
        var proyek = proyekMapper.createProyekRequestDTOToProyek(proyekDTO);

        model.addAttribute("id", proyek.getId());
        model.addAttribute("Nama", proyek.getNama());

        return "success-add-proyek";
    }

    @GetMapping("/proyek/viewall")
    public String listProyek(Model model) {
        // Mengambil semua proyek yang telah tersimpan
        List<Proyek> listProyek = proyekService.getAllProyek();

        // Add listProyek ke 'listProyek' untuk dirender di thymeleaf
        model.addAttribute("listProyek", listProyek);

        return "viewall-proyek";
    }

    @GetMapping("/proyek/")
    public String detailProyek(@RequestParam("id") String id, Model model) {
        // Mengambil proyek berdasarkan id
        var proyek = proyekService.getProyekById(UUID.fromString(id));

        // Add proyek ke 'proyek' untuk dirender di thymeleaf
        model.addAttribute("proyek", proyek);

        return "view-proyek";
    }

    @GetMapping("/proyek/update/{id}")
    public String updateProyek(@PathVariable("id") String id, Model model) {
        var proyek = proyekService.getProyekById(UUID.fromString(id));

        var proyekDTO = proyekMapper.proyekToUpdateProyekRequestDTO(proyek);

        model.addAttribute("proyekDTO", proyekDTO);
        model.addAttribute("listDeveloper", developerService.getAllDeveloper());

        return "form-update-proyek";
    }

    @PostMapping("/proyek/update")
    public String updateProyek(@ModelAttribute UpdateProyekRequestDTO proyekDTO, Model model) {
        var proyekFromDTO = proyekMapper.updateProyekRequestDTOToProyek(proyekDTO);
        var proyek = proyekService.updateProyek(proyekFromDTO);

        model.addAttribute("id", proyek.getId());
        model.addAttribute("Nama", proyek.getNama());

        return "success-update-proyek";
    }

    @GetMapping("/proyek/delete/{id}")
    public String deleteProyek(@PathVariable("id") UUID id, Model model) {
        var proyek = proyekService.getProyekById(id);
        proyekService.deleteProyek(proyek);
        model.addAttribute("id", proyek.getId());
        return "success-delete-proyek";
    }

}
