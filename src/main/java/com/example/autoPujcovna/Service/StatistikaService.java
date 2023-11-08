package com.example.autoPujcovna.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.autoPujcovna.Model.User;
import com.example.autoPujcovna.Model.Vozidla;
import com.example.autoPujcovna.Repository.UserRepository;
import com.example.autoPujcovna.Repository.VozidlaRepository;

@Service
public class StatistikaService {
    
    private final UserRepository userRepository;
    private final VozidlaRepository vozidlaRepository;
    
   

    public StatistikaService(UserRepository userRepository, VozidlaRepository vozidlaRepository) {
        this.userRepository = userRepository;
        this.vozidlaRepository = vozidlaRepository;
    }

    public float avgUser(){
        float pocetRezervaci = 0;
        List<User> users = userRepository.findAll();
        for (User user : users) {
            pocetRezervaci = pocetRezervaci + user.getPocetRezervaci();
        }
        float pocetUsers = users.size();
        if (pocetUsers <= 0) {
            throw new IllegalArgumentException("Pocet Users must be bigger than 0");
        }
        
        return pocetRezervaci/pocetUsers;
    }

    public float avgCar(){
        float pocetRezervaci = 0;
        List<Vozidla> cars = vozidlaRepository.findAll();
        for (Vozidla vozidla : cars) {
            pocetRezervaci = pocetRezervaci + vozidla.getPocetRezervaci();
        }
        float pocetVozidel = cars.size();
        if (pocetVozidel <= 0) {
            throw new IllegalArgumentException("Pocet Vozidel must be bigger than 0");
        }

        return pocetRezervaci/pocetVozidel;
    }

    public User user(){
        List<User> userList = userRepository.findAll();
        Optional<User> userOptional = userList.stream().max(Comparator.comparing(r -> r.getPocetRezervaci()));
       
        if (userOptional.isEmpty()) {
            User exception = new User("žadný user nenalezen","a","a","a");
            return exception;
        }

        User user = userOptional.get();
        return user;
    }

    public Vozidla vozidla(){
        List<Vozidla> vozidlaList = vozidlaRepository.findAll();
        Optional<Vozidla> vozidlaOptional = vozidlaList.stream().max(Comparator.comparing(r -> r.getPocetRezervaci()));

        if (vozidlaOptional.isEmpty()) {
            Vozidla exception = new Vozidla("žádné auto nenalezeno","","");
            return exception;
        }   

        Vozidla vozidla = vozidlaOptional.get();
        return vozidla;
    }
}
