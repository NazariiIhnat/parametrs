package com.nazarii.parameters.entity;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class WartoscOpisowaParametru extends WartoscParametru {

    private String wartosc;

    public WartoscOpisowaParametru(LocalDate dataOd, LocalDate dataDo, Parametr parametr, String wartosc) {
        super(dataOd, dataDo, parametr);
        this.wartosc = wartosc;
    }

    public WartoscOpisowaParametru(){}

    public String getWartosc() {
        return wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }
}
