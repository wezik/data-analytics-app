package me.dataanalytics.app.controller;

import lombok.RequiredArgsConstructor;
import me.dataanalytics.app.domain.Company;
import me.dataanalytics.app.domain.CompanyType;
import me.dataanalytics.app.domain.dto.CompanyTypeDto;
import me.dataanalytics.app.exception.CompanyTypeNotFoundException;
import me.dataanalytics.app.mapper.CompanyTypeMapper;
import me.dataanalytics.app.service.CompanyDbService;
import me.dataanalytics.app.service.CompanyTypeDbService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/type")
public class CompanyTypeController {

    private final CompanyTypeDbService companyTypeDbService;
    private final CompanyDbService companyDbService;

    private final CompanyTypeMapper companyTypeMapper;

    @GetMapping("get")
    public CompanyTypeDto getCompanyType(@RequestParam int id) {
        return companyTypeMapper.mapToCompanyTypeDto(companyTypeDbService.getCompanyType(id).orElseThrow(CompanyTypeNotFoundException::new));
    }

    @GetMapping("list")
    public List<CompanyTypeDto> getCompanyTypes() {
        return companyTypeMapper.mapToCompanyTypeDtoList(companyTypeDbService.getAllCompanyTypes());
    }

    @PostMapping("create")
    public CompanyTypeDto createCompanyType(@RequestBody CompanyTypeDto companyTypeDto) {
        CompanyType companyType = companyTypeMapper.mapToCompanyType(companyTypeDto, new ArrayList<>());
        return companyTypeMapper.mapToCompanyTypeDto(companyTypeDbService.saveCompanyType(companyType));
    }

    @PutMapping("update")
    public CompanyTypeDto updateCompanyType(@RequestBody CompanyTypeDto companyTypeDto) {
        List<Company> companyList = companyDbService.getAllCompaniesByCompanyType(companyTypeDto.getId());
        CompanyType companyType = companyTypeMapper.mapToCompanyType(companyTypeDto, companyList);
        return companyTypeMapper.mapToCompanyTypeDto(companyTypeDbService.saveCompanyType(companyType));
    }

    @DeleteMapping("delete")
    public void deleteCompanyType(@RequestParam int id) {
        CompanyType companyType = companyTypeDbService.getCompanyType(id).orElseThrow(CompanyTypeNotFoundException::new);
        companyTypeDbService.deleteCompanyType(companyType);
    }

}
