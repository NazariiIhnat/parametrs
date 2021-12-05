package com.nazarii.parameters.rest;

import com.nazarii.parameters.entity.Parametr;
import com.nazarii.parameters.entity.WartoscLiczbowaParametru;
import com.nazarii.parameters.entity.WartoscOpisowaParametru;
import com.nazarii.parameters.entity.WartoscParametru;
import com.nazarii.parameters.repository.WartoscParametruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wartoscParametru")
public class WartoscParametruController {

    private WartoscParametruRepository repository;

    @Autowired
    public WartoscParametruController(WartoscParametruRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public WartoscParametru get (@PathVariable long id) {
        Optional<WartoscParametru> optional = repository.findById(id);
        WartoscParametru parametr = null;
        if(optional.isPresent())
            parametr = optional.get();
        return parametr;
    }

    @GetMapping("/")
    public List<WartoscParametru> getAll() {
        return (List<WartoscParametru>) repository.findAll();
    }

    @PutMapping("/{id}")
    public void update (@PathVariable long id, @RequestBody WartoscParametru newWartoscParametru) {
        Optional<WartoscParametru> optional = repository.findById(id);
        if(optional.isPresent()){
            WartoscParametru wartoscParametru = getUpdatedWartoscParametru(optional.get(), newWartoscParametru);
            repository.save(wartoscParametru);
        }
    }

    private WartoscParametru getUpdatedWartoscParametru(WartoscParametru oldWartosc, WartoscParametru newWartosc){
        WartoscParametru wartoscParametru = null;
        if(newWartosc.getDataOd() != null)
            oldWartosc.setDataOd(newWartosc.getDataOd());
        if(newWartosc.getDataDo() != null)
            oldWartosc.setDataDo(newWartosc.getDataDo());
        if(newWartosc.getParametr() != null)
            oldWartosc.setParametr(newWartosc.getParametr());
        if(newWartosc.getClass() == WartoscLiczbowaParametru.class)
            wartoscParametru = updateWartoscLiczbowaParametru(oldWartosc, newWartosc);
        if(newWartosc.getClass() == WartoscOpisowaParametru.class)
            wartoscParametru = updateWartoscOpisowaParametru(oldWartosc, newWartosc);
        return wartoscParametru;
    }

    private WartoscLiczbowaParametru updateWartoscLiczbowaParametru(WartoscParametru o,
                                                                    WartoscParametru n) {
        WartoscLiczbowaParametru oldWartosc = (WartoscLiczbowaParametru) o;
        WartoscLiczbowaParametru newWartosc = (WartoscLiczbowaParametru) n;
        if(newWartosc.getJednostka() != null)
            oldWartosc.setJednostka(newWartosc.getJednostka());
        if(newWartosc.getWartosc() != null)
            oldWartosc.setWartosc(newWartosc.getWartosc());
        return oldWartosc;
    }

    private WartoscOpisowaParametru updateWartoscOpisowaParametru(WartoscParametru o, WartoscParametru n) {
        WartoscOpisowaParametru oldWartosc = (WartoscOpisowaParametru) o;
        WartoscOpisowaParametru newWartosc = (WartoscOpisowaParametru) n;
        if(newWartosc.getWartosc() != null)
            oldWartosc.setWartosc(newWartosc.getWartosc());
        return oldWartosc;
    }

    @PostMapping("/")
    public void save(@RequestBody WartoscParametru wartoscParametru){
        repository.save(wartoscParametru);
    }

    @PostMapping("/all")
    public List<WartoscParametru> saveAll(@RequestBody List<WartoscParametru> list){
        return (List<WartoscParametru>) repository.saveAll(list);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        Optional<WartoscParametru> optional = repository.findById(id);
        if(optional.isPresent()) {
            WartoscParametru wartoscParametru = optional.get();
            Parametr parametr = wartoscParametru.getParametr();
            parametr.getWartosciParametru().remove(wartoscParametru);
            repository.delete(wartoscParametru);
        }
    }
}
