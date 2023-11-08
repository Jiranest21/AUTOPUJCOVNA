package com.example.autoPujcovna.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.autoPujcovna.Model.Vozidla;
import com.example.autoPujcovna.Service.VozidlaService;

@Controller
@RequestMapping("autoPujcovna")
public class VozidlaController {

    private final VozidlaService vozidlaService;

    public VozidlaController(VozidlaService vozidlaService) {
        this.vozidlaService = vozidlaService;
    }

    @GetMapping("/vozidla")
    public String getVozidla(Model model){
        List<Vozidla> vozidlaList = vozidlaService.getVozidla();
        model.addAttribute("vozidla", vozidlaList);
        return "vozidla";
    }


    @GetMapping("/vozidla/add")
    public String getAddForm(Model model) {
        Vozidla vozidla = new Vozidla();
        model.addAttribute("Vozidla",vozidla);

        return "vozidlaAdd";
    }
    @PostMapping("/vozidla/add")
    public String addVozidla(@ModelAttribute("Vozidla") Vozidla vozidla, BindingResult result ) {
        if (result.hasErrors()) {
            return "vozidlaAdd";
        }
        vozidlaService.addVozidla(vozidla);
        return "redirect:http://localhost:8080/autoPujcovna/vozidla";
    }

    @GetMapping("/vozidla/{vozidlaId}")
    public String deleteVozidla(@PathVariable("vozidlaId") long vozidlaId){
        vozidlaService.deleteVozidla(vozidlaId);
        return "redirect:http://localhost:8080/autoPujcovna/vozidla";
    }


    
}
