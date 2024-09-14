package apap.tutorial.manpromanpro.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.manpromanpro.dto.request.AddProyekRequestDTO;
import apap.tutorial.manpromanpro.dto.request.UpdateProyekRequestDTO;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.service.DeveloperService;
import apap.tutorial.manpromanpro.service.ProyekService;

@Controller
public class ProyekController {
    
    @Autowired
    private ProyekService proyekService;

    @Autowired
    private DeveloperService developerService;

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
        var listDeveloper = developerService.getAllDeveloper();

        model.addAttribute("proyekDTO", proyekDTO);
        model.addAttribute("listDeveloper", developerService.getAllDeveloper());
        model.addAttribute("statusLevel", StatusLevel.values());
        model.addAttribute("listDeveloper", listDeveloper);

        return "form-add-proyek";
    }

    @PostMapping("/proyek/add")
    public String addProyek(@ModelAttribute AddProyekRequestDTO proyekDTO, Model model) {
        var proyek = new Proyek();
        proyek.setNama(proyekDTO.getNama());
        proyek.setDeskripsi(proyekDTO.getDeskripsi());
        proyek.setTanggalMulai(proyekDTO.getTanggalMulai());
        proyek.setTanggalSelesai(proyekDTO.getTanggalSelesai());
        proyek.setStatus(proyekDTO.getStatus());
        proyek.setDeveloper(proyekDTO.getDeveloper());

        proyekService.addProyek(proyek);
        
        model.addAttribute("responseMessage",
                String.format("Proyek %s dengan ID %s berhasil ditambahkan.", proyek.getNama(), proyek.getId()));

        return "response-proyek";
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
        proyekDTO.setDeveloper(proyek.getDeveloper());

        model.addAttribute("proyekDTO", proyekDTO);
        model.addAttribute("listDeveloper", developerService.getAllDeveloper());
        model.addAttribute("statusLevel", StatusLevel.values());

        return "form-update-proyek";
    }

    @PostMapping("/proyek/update")
    public String updateProyek(@ModelAttribute UpdateProyekRequestDTO proyekDTO, Model model) {
        var proyekFromDTO = new Proyek();
        proyekFromDTO.setId(proyekDTO.getId());
        proyekFromDTO.setNama(proyekDTO.getNama());
        proyekFromDTO.setDeskripsi(proyekDTO.getDeskripsi());
        proyekFromDTO.setTanggalMulai(proyekDTO.getTanggalMulai());
        proyekFromDTO.setTanggalSelesai(proyekDTO.getTanggalSelesai());
        proyekFromDTO.setStatus(proyekDTO.getStatus());
        proyekFromDTO.setDeveloper(proyekDTO.getDeveloper());

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
