package me.dataanalytics.app.controller;

import lombok.RequiredArgsConstructor;
import me.dataanalytics.app.domain.Company;
import me.dataanalytics.app.domain.CompanyType;
import me.dataanalytics.app.domain.TrackedMetric;
import me.dataanalytics.app.domain.dto.CompanyDto;
import me.dataanalytics.app.exception.CompanyNotFoundException;
import me.dataanalytics.app.exception.CompanyTypeNotFoundException;
import me.dataanalytics.app.exception.TrackedMetricNotFoundException;
import me.dataanalytics.app.mapper.CompanyMapper;
import me.dataanalytics.app.service.CompanyDbService;
import me.dataanalytics.app.service.CompanyTypeDbService;
import me.dataanalytics.app.service.TrackedMetricDbService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/company")
public class CompanyController {

    private final CompanyDbService companyDbService;
    private final CompanyTypeDbService companyTypeDbService;
    private final TrackedMetricDbService trackedMetricDbService;

    private final CompanyMapper companyMapper;

    @GetMapping("get")
    public CompanyDto getCompany(@RequestParam int id) {
        return companyMapper.mapToCompanyDto(companyDbService.getCompany(id).orElseThrow(CompanyNotFoundException::new));
    }

    @GetMapping("list")
    public List<CompanyDto> getCompanies() {
        return companyMapper.mapToCompanyDtoList(companyDbService.getAllCompanies());
    }

    @PostMapping("create")
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        CompanyType companyType = companyTypeDbService.getCompanyType(companyDto.getType()).orElseThrow(CompanyTypeNotFoundException::new);
        Company company = new Company(companyDto.getName(),companyType);
        return companyMapper.mapToCompanyDto(companyDbService.saveCompany(company));
    }

    @PutMapping("update")
    public CompanyDto updateCompany(@RequestBody CompanyDto companyDto) {
        CompanyType companyType = companyTypeDbService.getCompanyType(companyDto.getType()).orElseThrow(CompanyTypeNotFoundException::new);
        List<TrackedMetric> trackedMetrics = companyDto.getMetrics().stream()
                .map(e -> trackedMetricDbService.getTrackedMetric(e.getId()).orElseThrow(TrackedMetricNotFoundException::new))
                .collect(Collectors.toList());
        Company company = companyMapper.mapToCompany(companyDto,companyType,trackedMetrics);
        return companyMapper.mapToCompanyDto(companyDbService.saveCompany(company));
    }

    @DeleteMapping("delete")
    public void deleteCompany(@RequestParam int id) {
        Company company = companyDbService.getCompany(id).orElseThrow(CompanyNotFoundException::new);
        companyDbService.deleteCompany(company);
    }

}
