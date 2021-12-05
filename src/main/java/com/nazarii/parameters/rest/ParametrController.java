package com.nazarii.parameters.rest;

import com.nazarii.parameters.entity.Parametr;
import com.nazarii.parameters.repository.ParametrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parametr")
public class ParametrController{

    private ParametrRepository repository;

    @Autowired
    public ParametrController(ParametrRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Parametr get(@PathVariable long id) {
        Optional<Parametr> optional = repository.findById(id);
        Parametr parametr = null;
        if(optional.isPresent())
            parametr = optional.get();
        return parametr;
    }

    @GetMapping("/")
    public List<Parametr> getAll() {
        return (List<Parametr>) repository.findAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Parametr newParametr) {
        Optional<Parametr> optional = repository.findById(id);
        optional.ifPresent(parametr -> {
            Parametr updatedParametr = getUpdatedParametr(parametr, newParametr);
            repository.save(updatedParametr);
        });
    }

    private Parametr getUpdatedParametr(Parametr oldParametr, Parametr newParametr) {
        if(newParametr.getName() != null)
            oldParametr.setName(newParametr.getName());
        if(newParametr.getDescription() != null)
            oldParametr.setDescription(newParametr.getDescription());
        if(newParametr.getWartosciParametru() != null) {
            if (newParametr.getWartosciParametru().size() != 0)
                oldParametr.setWartosciParametru(newParametr.getWartosciParametru());
        }
        return oldParametr;
    }

    @PostMapping("/")
    public Parametr save(@RequestBody Parametr newParametr){
        return repository.save(newParametr);
    }

    @PostMapping("/all")
    public List saveAll(@RequestBody List<Parametr> list) {
        return (List) repository.saveAll(list);
    }

    @DeleteMapping("/{id}")
    public Parametr delete(@PathVariable long id) {
        Optional<Parametr> optional = repository.findById(id);
        Parametr parametr = null;
        if(optional.isPresent()) {
            parametr = optional.get();
            repository.delete(parametr);
        }
        return parametr;
    }
}
