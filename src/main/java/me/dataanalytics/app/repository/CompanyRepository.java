package me.dataanalytics.app.repository;

import me.dataanalytics.app.domain.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    List<Company> findAll();
    @Query
    List<Company> findByCompanyTypeId(@Param("id")int id);
    Optional<Company> findById(int id);
}
