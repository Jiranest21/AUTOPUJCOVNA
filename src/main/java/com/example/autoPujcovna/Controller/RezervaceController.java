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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.autoPujcovna.Model.Rezervace;
import com.example.autoPujcovna.Model.User;
import com.example.autoPujcovna.Model.Vozidla;
import com.example.autoPujcovna.Repository.UserRepository;
import com.example.autoPujcovna.Repository.VozidlaRepository;
import com.example.autoPujcovna.Service.RezervaceService;

@Controller
@RequestMapping(path = "autoPujcovna")
public class RezervaceController {


    private final UserRepository userRepository;
    private final RezervaceService rezervaceService;
    private final VozidlaRepository vozidlaRepository;

    public RezervaceController(RezervaceService rezervaceService, UserRepository userRepository, VozidlaRepository vozidlaRepository) {
        this.rezervaceService = rezervaceService;
        this.userRepository = userRepository;
        this.vozidlaRepository = vozidlaRepository;
    }


    @GetMapping("/rezervace")
    public String getReservace (Model model) {
        List<Rezervace> listRezervaci = rezervaceService.getRezervace();
        model.addAttribute("rezervace", listRezervaci);
        return "rezervace";
    }



    @GetMapping("/rezervace/add")
    public String getAddForm(Model r, Model u, Model v) {
        List<Vozidla> vozidlaList = vozidlaRepository.findAll();
        v.addAttribute("vozidlaList", vozidlaList);
        List<User> userList = userRepository.findAll();
        u.addAttribute("userList", userList);
        Rezervace rezervace = new  Rezervace();
        r.addAttribute("rezervace", rezervace);
        return "rezervaceAdd";
    }

    @PostMapping("/rezervace/add")
    public String addRezervace (@ModelAttribute("Rezervace") Rezervace rezervace, BindingResult bindingResult, RedirectAttributes redirectAttributes){
         if (bindingResult.hasErrors()) {
        return "createReservation";
    }
        rezervaceService.creteRezervace(rezervace);
        return "redirect:http://localhost:8080/autoPujcovna/rezervace";
    }

    @GetMapping(path = "/rezervace/{rezervaceId}")
    public String deleteRezervace(
        @PathVariable("rezervaceId") Long rezervaceId) {
        rezervaceService.deleteRezervace(rezervaceId);
        return "redirect:http://localhost:8080/autoPujcovna/rezervace";
    }

}
