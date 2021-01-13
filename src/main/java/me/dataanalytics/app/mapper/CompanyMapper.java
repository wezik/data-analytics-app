package me.dataanalytics.app.mapper;

import me.dataanalytics.app.domain.Company;
import me.dataanalytics.app.domain.CompanyType;
import me.dataanalytics.app.domain.Metric;
import me.dataanalytics.app.domain.TrackedMetric;
import me.dataanalytics.app.domain.dto.CompanyDto;
import me.dataanalytics.app.domain.dto.MetricDto;
import me.dataanalytics.app.domain.dto.TrackedMetricDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyMapper {

    public Company mapToCompany(CompanyDto companyDto, CompanyType companyType, List<TrackedMetric> trackedMetricList) {
        return new Company(
                companyDto.getId(),
                companyDto.getName(),
                companyType,
                trackedMetricList
        );
    }

    public CompanyDto mapToCompanyDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getType().getId(),
                mapToTrackedMetricsDtoList(company.getMetrics())
        );
    }

    public List<CompanyDto> mapToCompanyDtoList(List<Company> companyList) {
        return companyList.stream()
                .map(this::mapToCompanyDto)
                .collect(Collectors.toList());
    }

    private List<TrackedMetricDto> mapToTrackedMetricsDtoList(List<TrackedMetric> trackedMetricList) {
        return trackedMetricList.stream()
                .map(this::mapToTrackedMetricDto)
                .collect(Collectors.toList());
    }

    private TrackedMetricDto mapToTrackedMetricDto(TrackedMetric trackedMetric) {
        return new TrackedMetricDto(
                trackedMetric.getId(),
                trackedMetric.getCompany().getId(),
                mapToMetricDto(trackedMetric.getMetric()),
                trackedMetric.getValue()
        );
    }

    private MetricDto mapToMetricDto(Metric metric) {
        return new MetricDto(
                metric.getId(),
                metric.getName()
        );
    }

}
