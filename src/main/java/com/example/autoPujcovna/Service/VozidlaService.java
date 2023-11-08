package com.example.autoPujcovna.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.autoPujcovna.Model.Vozidla;
import com.example.autoPujcovna.Repository.VozidlaRepository;

@Service
public class VozidlaService {
    
    private final VozidlaRepository vozidlaRepository;

    public VozidlaService(VozidlaRepository vozidlaRepository) {
        this.vozidlaRepository = vozidlaRepository;
    }

    public List<Vozidla> getVozidla(){
        return vozidlaRepository.findAll();
    }

    public void addVozidla(Vozidla vozidla) {
		
		vozidlaRepository.save(vozidla);
	}

	public void deleteVozidla(Long vozidlaId) {
		boolean exists = vozidlaRepository.existsById(vozidlaId);
		if (!exists) {
			throw new IllegalStateException("student with id " + vozidlaId + " does not exist");
		}
		vozidlaRepository.deleteById(vozidlaId);
	}





}
