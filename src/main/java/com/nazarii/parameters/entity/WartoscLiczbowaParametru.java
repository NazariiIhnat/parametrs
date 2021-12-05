package com.nazarii.parameters.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class WartoscLiczbowaParametru extends WartoscParametru{

    private BigDecimal wartosc;
    @Enumerated(EnumType.STRING)
    private Jednostka jednostka;

    public WartoscLiczbowaParametru(LocalDate dataOd, LocalDate dataDo, Parametr parametr, BigDecimal wartosc, Jednostka jednostka) {
        super(dataOd, dataDo, parametr);
        this.wartosc = wartosc;
        this.jednostka = jednostka;
    }

    public WartoscLiczbowaParametru() {
        super();
    }

    public BigDecimal getWartosc() {
        return wartosc;
    }

    public void setWartosc(BigDecimal wartosc) {
        this.wartosc = wartosc;
    }

    public Jednostka getJednostka() {
        return jednostka;
    }

    public void setJednostka(Jednostka jednostka) {
        this.jednostka = jednostka;
    }
}
