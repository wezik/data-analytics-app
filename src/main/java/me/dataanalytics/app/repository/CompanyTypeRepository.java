package me.dataanalytics.app.repository;

import me.dataanalytics.app.domain.CompanyType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyTypeRepository extends CrudRepository<CompanyType,Integer> {
    Optional<CompanyType> findById(int id);
    List<CompanyType> findAll();
}
