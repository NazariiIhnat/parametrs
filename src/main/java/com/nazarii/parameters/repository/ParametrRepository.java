package com.nazarii.parameters.repository;

import com.nazarii.parameters.entity.Parametr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametrRepository extends CrudRepository<Parametr, Long> {
}
