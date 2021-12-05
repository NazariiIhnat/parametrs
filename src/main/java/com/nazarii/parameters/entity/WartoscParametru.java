package com.nazarii.parameters.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=WartoscLiczbowaParametru.class, name = "Liczbowa"),
        @JsonSubTypes.Type(value=WartoscOpisowaParametru.class, name = "Opisowa")
})
public abstract class WartoscParametru {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataOd;
    private LocalDate dataDo;
    @ManyToOne
    @JoinColumn(name = "parametr_id")
    @JsonIgnore
    private Parametr parametr;

    public WartoscParametru(LocalDate dataOd, LocalDate dataDo, Parametr parametr) {
        this.dataOd = dataOd;
        this.dataDo = dataDo;
        this.parametr = parametr;
    }

    public WartoscParametru(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataOd() {
        return dataOd;
    }

    public void setDataOd(LocalDate dataOd) {
        this.dataOd = dataOd;
    }

    public LocalDate getDataDo() {
        return dataDo;
    }

    public void setDataDo(LocalDate dataDo) {
        this.dataDo = dataDo;
    }

    public Parametr getParametr() {
        return parametr;
    }

    public void setParametr(Parametr parametr) {
        this.parametr = parametr;
    }
}
