package com.nazarii.parameters.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Parametr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, unique = true, nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "parametr", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WartoscParametru> wartosciParametru;

    public Parametr(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Parametr() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WartoscParametru> getWartosciParametru() {
        return wartosciParametru;
    }

    public void setWartosciParametru(List<WartoscParametru> wartosciParametru) {
        this.wartosciParametru = wartosciParametru;
    }

    public void addWartoscParametru(WartoscParametru wartoscParametru) {
        wartosciParametru.add(wartoscParametru);
    }
}
