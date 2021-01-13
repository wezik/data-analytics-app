package me.dataanalytics.app.domain.dto;

import lombok.Value;

@Value
public class TrackedMetricDto {
    int id;
    int company;
    MetricDto metric;
    double value;
}
