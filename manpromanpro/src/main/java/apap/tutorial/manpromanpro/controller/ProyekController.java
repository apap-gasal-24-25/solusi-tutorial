package apap.tutorial.manpromanpro.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        if(proyekService.validateTanggal(proyekDTO.getTanggalMulai(), proyekDTO.getTanggalSelesai())) {

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
        return "error-view";
       
    }

    @GetMapping("/proyek/viewall")
    public String listProyek(Model model) {
        // Mengambil semua proyek yang telah tersimpan
        List<Proyek> listProyek = proyekService.getAllProyek();

        // Add listProyek ke 'listProyek' untuk dirender di thymeleaf
        model.addAttribute("listProyek", listProyek);

        return "viewall-proyek";
    }

    @GetMapping("/proyek/{id}")
    public String detailProyek(@PathVariable("id") String id, Model model) {
        // Mengambil proyek berdasarkan id
        var proyek = proyekService.getProyekById(UUID.fromString(id));

        // Add proyek ke 'proyek' untuk dirender di thymeleaf
        model.addAttribute("proyek", proyek);

        return "view-proyek";
    }

    @GetMapping("/proyek/{id}/update")
    public String updateProyek(@PathVariable("id") UUID id, Model model) {
        // Mengambil proyek berdasarkan id
        var proyek = proyekService.getProyekById(id);

        // Membuat DTO baru sebagai isian form pengguna
        var proyekDTO = new ProyekDTO(proyek.getId(), proyek.getNama(), proyek.getTanggalMulai(), 
        proyek.getTanggalSelesai(), proyek.getStatus(), proyek.getDeveloper());

        model.addAttribute("proyekDTO", proyekDTO);
        model.addAttribute("id", id);

        return "form-update-proyek";
    }

    @PostMapping("/proyek/update")
    public String processUpdateProyek(@ModelAttribute ProyekDTO proyekDTO, Model model) {
        if(proyekService.validateTanggal(proyekDTO.getTanggalMulai(), proyekDTO.getTanggalSelesai())) {
           // Mendapatkan buku berdasarkan ID
            var proyek = proyekService.getProyekById(proyekDTO.getId());

            // Mengubah data proyek dengan data baru dari DTO
            proyek.setNama(proyekDTO.getNama());
            proyek.setTanggalMulai(proyekDTO.getTanggalMulai());
            proyek.setTanggalSelesai(proyekDTO.getTanggalSelesai());
            proyek.setStatus(proyekDTO.getStatus());
            proyek.setDeveloper(proyekDTO.getDeveloper());
            
            // Memanggil service untuk menyimpan perubahan
            proyekService.updateProyek(proyek);

            model.addAttribute("id", proyek.getId());

            return "success-update";
        }
    
        return "error-view";
    }

    @GetMapping("/proyek/{id}/delete")
    public String deleteBuku(@PathVariable("id") UUID id) {
       
        // Menghapus proyek berdasarkan ID
        proyekService.deleteProyek(id);
        
        return "success-delete";
    }
}
