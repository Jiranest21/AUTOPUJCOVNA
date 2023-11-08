package com.example.autoPujcovna.Model;

import java.time.LocalDate;

import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Rezervace {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Vozidla vozidla;

    private LocalDate startDate;
    private LocalDate endDate;

    public Rezervace(User user, Vozidla vozidla, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.vozidla = vozidla;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    


}
