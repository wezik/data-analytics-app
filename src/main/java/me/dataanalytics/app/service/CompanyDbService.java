package me.dataanalytics.app.service;

import lombok.RequiredArgsConstructor;
import me.dataanalytics.app.domain.Company;
import me.dataanalytics.app.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyDbService {

    private final CompanyRepository companyRepository;

    public Optional<Company> getCompany(int id) {
        return companyRepository.findById(id);
    }

    public List<Company> getAllCompaniesByCompanyType(int id) {
        return companyRepository.findByCompanyTypeId(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

}
