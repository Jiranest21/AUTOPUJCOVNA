package com.example.autoPujcovna.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.autoPujcovna.Model.User;
import com.example.autoPujcovna.Model.Vozidla;
import com.example.autoPujcovna.Repository.UserRepository;
import com.example.autoPujcovna.Service.StatistikaService;
import com.example.autoPujcovna.Service.VozidlaService;

@Controller
@RequestMapping(path = "autoPujcovna")
public class StatistikaController {
    
    private final UserRepository userRepository;
    private final StatistikaService statistikaService;

    public StatistikaController(UserRepository userRepository, StatistikaService statistikaService) 
    {
        this.userRepository = userRepository;
        this.statistikaService = statistikaService;
    }

    @GetMapping("/statistika")
    public String getStatistika(Model model, Model avgUser, Model avgCar, Model userRezervace, Model vozidlaRezervace) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);

        float userPrumer = statistikaService.avgUser();
        avgUser.addAttribute("avgUser", userPrumer);

        float vozidlaPrumer = statistikaService.avgCar();
        avgCar.addAttribute("avgVozidla", vozidlaPrumer);

        User user = statistikaService.user();
        userRezervace.addAttribute("userRezervace", user.getUsername());

        Vozidla vozidla = statistikaService.vozidla();
        vozidlaRezervace.addAttribute("vozidlaRezervace", vozidla.getModel());

        return "Statistika";
    }
}
