package me.dataanalytics.app.mapper;

import me.dataanalytics.app.domain.Company;
import me.dataanalytics.app.domain.CompanyType;
import me.dataanalytics.app.domain.dto.CompanyTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyTypeMapper {

    public CompanyType mapToCompanyType(CompanyTypeDto companyTypeDto, List<Company> companyList) {
        return new CompanyType(
                companyTypeDto.getId(),
                companyTypeDto.getName(),
                companyList
        );
    }

    public CompanyTypeDto mapToCompanyTypeDto(CompanyType companyType) {
        return new CompanyTypeDto(
                companyType.getId(),
                companyType.getName()
        );
    }

    public List<CompanyTypeDto> mapToCompanyTypeDtoList(List<CompanyType> companyTypeList) {
        return companyTypeList.stream()
                .map(this::mapToCompanyTypeDto)
                .collect(Collectors.toList());
    }
}
