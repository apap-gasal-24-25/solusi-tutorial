package apap.tutorial.manpromanpro.controller;

import apap.tutorial.manpromanpro.dto.DeveloperMapper;
import apap.tutorial.manpromanpro.dto.request.CreateDeveloperRequestDTO;
import apap.tutorial.manpromanpro.service.DeveloperService;
import apap.tutorial.manpromanpro.service.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeveloperController {
    @Autowired
    DeveloperMapper developerMapper;

    @Autowired
    DeveloperService developerService;

    @Autowired
    ProyekService proyekService;

    @GetMapping("/developer/create")
    public String formAddDeveloper(Model model) {
        var developerDTO = new CreateDeveloperRequestDTO();
        model.addAttribute("developerDTO", developerDTO);

        return "form-create-developer";
    }

    @PostMapping("/developer/create")
    public String addDeveloper(@ModelAttribute("developerDTO") CreateDeveloperRequestDTO developerDTO, Model model) {
        var developer = developerMapper.createDeveloperRequestDTOToDeveloper(developerDTO);

        model.addAttribute("developer", developer);
        return "form-create-developer";
    }

    @GetMapping("/developer/viewall")
    public String listDeveloper(Model model) {
        var listDeveloper = developerService.getAllDeveloper();

        model.addAttribute("listDeveloper", listDeveloper);
        return "viewall-developer";
    }

    @GetMapping("/developer/{id}")
    public String detailDeveloper(@PathVariable("id") Long id, Model model) {
        var developer = developerService.getDeveloperById(id);

        model.addAttribute("developer", developer);
        return "view-developer";
    }
}
