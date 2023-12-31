package com.example.autoPujcovna.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long id;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List <Rezervace> rezervace;
    private int pocetRezervaci;
    


    public User(String username, String email, String password, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
