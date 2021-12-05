package com.nazarii.parameters.repository;

import com.nazarii.parameters.entity.WartoscParametru;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WartoscParametruRepository extends CrudRepository<WartoscParametru, Long> {
    @Query(value = "from WartoscParametru w where w.dataOd >= :dataOd and w.dataDo <= :dataDo")
    List<WartoscParametru> findAllByDateRange(@Param("dataOd") LocalDate dataOd, @Param("dataDo")LocalDate dataDo);
}
