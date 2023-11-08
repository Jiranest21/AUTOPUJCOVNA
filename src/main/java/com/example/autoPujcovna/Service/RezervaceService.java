package com.example.autoPujcovna.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.autoPujcovna.Model.Rezervace;
import com.example.autoPujcovna.Model.User;
import com.example.autoPujcovna.Model.Vozidla;
import com.example.autoPujcovna.Repository.RezervaceRepository;
import com.example.autoPujcovna.Repository.UserRepository;
import com.example.autoPujcovna.Repository.VozidlaRepository;

@Service
public class RezervaceService {

    private final UserRepository userRepository;
    private final RezervaceRepository rezervaceRepository;
    private final VozidlaRepository vozidlaRepository;

    public RezervaceService(UserRepository userRepository, RezervaceRepository rezervaceRepository,
            VozidlaRepository vozidlaRepository) {
        this.userRepository = userRepository;
        this.rezervaceRepository = rezervaceRepository;
        this.vozidlaRepository = vozidlaRepository;
    }

    public List<Rezervace> getRezervace() {
        
        return rezervaceRepository.findAll();
    }

    public void creteRezervace(Rezervace rezervace) {

        if (rezervace.getEndDate().isBefore(rezervace.getStartDate())) {
            System.out.println("špatné datum");
            return;
        }

        List<Rezervace> overlappingReservations = rezervaceRepository.findOverlappingReservations(rezervace.getVozidla().getId(), rezervace.getStartDate(), rezervace.getEndDate());
        
        if (overlappingReservations.isEmpty()) {
            
            User user = rezervace.getUser(); 
            user.setPocetRezervaci(user.getPocetRezervaci() + 1);
            userRepository.save(user);

            Vozidla vozidla = rezervace.getVozidla();
            vozidla.setPocetRezervaci(vozidla.getPocetRezervaci() + 1);
            vozidlaRepository.save(vozidla);

            rezervaceRepository.save(rezervace);
        } else {
            System.out.println("auto je již zabrané");
        }
        
    }



    public void deleteRezervace(Long rezervaceID) {
		boolean exists = rezervaceRepository.existsById(rezervaceID);
		if (!exists) {
			throw new IllegalStateException("rezervace nexistuje");
		}
		rezervaceRepository.deleteById(rezervaceID);
	}

    

}
