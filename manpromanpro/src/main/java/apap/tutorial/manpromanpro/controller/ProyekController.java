package apap.tutorial.manpromanpro.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.manpromanpro.controller.DTO.ProyekDTO;
import apap.tutorial.manpromanpro.model.Proyek;
import apap.tutorial.manpromanpro.service.ProyekService;

@Controller
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @GetMapping("/proyek/add")
    public String addProyekForm(Model model) {
        // Membuat DTO baru sebagai isian form pengguna
        var proyekDTO = new ProyekDTO();

        model.addAttribute("proyekDTO", proyekDTO);

        return "form-add-proyek";
    }

    @PostMapping("/proyek/add")
    public String addProyek(@ModelAttribute ProyekDTO proyekDTO, Model model) {

        // Generate UUID baru untuk proyek
        UUID idProyek = UUID.randomUUID();

        // Membuat objek proyek baru dengan data dari DTO
        var proyek = new Proyek(idProyek, proyekDTO.getNama(), proyekDTO.getTanggalMulai(),
                proyekDTO.getTanggalSelesai(), proyekDTO.getStatus(), proyekDTO.getDeveloper());

        // Memanggil service untuk menambahkan proyek
        proyekService.createProyek(proyek);

        // add variabel id proyek ke 'id' untuk dirender di thymeleaf
        model.addAttribute("id", proyek.getId());

        // add variabel nama proyek ke 'Nama' untuk dirender di thymeleaf
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

    @GetMapping("/proyek")
    public String detailProyek(@RequestParam("id") String id, Model model) {
        // Mengambil proyek berdasarkan id
        var proyek = proyekService.getProyekById(UUID.fromString(id));

        // Add proyek ke 'proyek' untuk dirender di thymeleaf
        model.addAttribute("proyek", proyek);

        return "view-proyek";
    }

}
