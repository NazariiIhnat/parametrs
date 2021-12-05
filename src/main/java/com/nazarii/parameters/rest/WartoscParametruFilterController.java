package com.nazarii.parameters.rest;

import com.nazarii.parameters.entity.WartoscLiczbowaParametru;
import com.nazarii.parameters.entity.WartoscOpisowaParametru;
import com.nazarii.parameters.entity.WartoscParametru;
import com.nazarii.parameters.repository.WartoscParametruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wartoscParametru/filter")
public class WartoscParametruFilterController {

    private WartoscParametruRepository repository;

    @Autowired
    public WartoscParametruFilterController(WartoscParametruRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/data")
    public List<WartoscParametru> filterByDateRange(@RequestParam("dataOd")String dataOd,
                                                    @RequestParam("dataDo")String dataDo){
        return repository.findAllByDateRange(LocalDate.parse(dataOd),
                LocalDate.parse(dataDo));
    }

    @GetMapping("/type")
    public List<WartoscParametru> filterByType(@RequestParam("type") String type){
        List<WartoscParametru> list = new ArrayList<>();
        Iterable<WartoscParametru> iterable = repository.findAll();
        if(type.equals("Opisowa")) {
            iterable.forEach(wartoscParametru -> {
                if (wartoscParametru.getClass() == WartoscOpisowaParametru.class)
                    list.add(wartoscParametru);
            });
        }

        if(type.equals("Liczbowa")){
            iterable.forEach(wartoscParametru -> {
                if(wartoscParametru.getClass() == WartoscLiczbowaParametru.class)
                    list.add(wartoscParametru);
            });
        }
        return list;
    }
}
