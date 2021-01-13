package me.dataanalytics.app.service;

import lombok.RequiredArgsConstructor;
import me.dataanalytics.app.domain.CompanyType;
import me.dataanalytics.app.repository.CompanyTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyTypeDbService {

    private final CompanyTypeRepository companyTypeRepository;

    public Optional<CompanyType> getCompanyType(int id) {
        return companyTypeRepository.findById(id);
    }

    public List<CompanyType> getAllCompanyTypes() {
        return companyTypeRepository.findAll();
    }

    public CompanyType saveCompanyType(CompanyType companyType) {
        return companyTypeRepository.save(companyType);
    }

    public void deleteCompanyType(CompanyType companyType) {
        companyTypeRepository.delete(companyType);
    }
}
