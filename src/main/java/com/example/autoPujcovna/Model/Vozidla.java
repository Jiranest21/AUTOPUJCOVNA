package com.example.autoPujcovna.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vozidla")
@Data
@NoArgsConstructor
public class Vozidla {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long id;
    private String model;
    private String znacka;
    private String rokVyroby;
    @OneToMany(mappedBy = "vozidla", cascade = CascadeType.REMOVE)
    private List<Rezervace> reservations;
    private int pocetRezervaci;
    
    public Vozidla(String model, String znacka, String rokVyroby) {
        this.model = model;
        this.znacka = znacka;
        this.rokVyroby = rokVyroby;
    }
}
