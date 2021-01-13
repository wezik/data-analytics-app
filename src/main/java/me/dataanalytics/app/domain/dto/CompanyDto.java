package me.dataanalytics.app.domain.dto;

import lombok.Value;

import java.util.List;

@Value
public class CompanyDto {
    int id;
    String name;
    int type;
    List<TrackedMetricDto> metrics;
}
