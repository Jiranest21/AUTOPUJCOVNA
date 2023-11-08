package com.example.autoPujcovna.Configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.autoPujcovna.Model.User;
import com.example.autoPujcovna.Model.Vozidla;
import com.example.autoPujcovna.Repository.RezervaceRepository;
import com.example.autoPujcovna.Repository.UserRepository;
import com.example.autoPujcovna.Repository.VozidlaRepository;


@Configuration
public class config {
    
    @Bean
    CommandLineRunner commandLineRunner (UserRepository userRepository, RezervaceRepository rezervaceRepository, VozidlaRepository vozidlaRepository){
            return args -> {
                //String username, String email, String password, String phoneNumber
                User mariam = new User(
                    "Mariam",
                    "Mariam@gmail.com",
                    "123",
                    "123456789"
                );

                User alex = new User(
                    "Alex",
                    "Alex@gmail.com",
                    "123",
                    "123456789"
                );

                Vozidla a = new Vozidla("a","a","6");

                userRepository.saveAll(
                    List.of(mariam, alex)
                );
                vozidlaRepository.save(a);
               
            
            };
    }
    
    
}
