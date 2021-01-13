package me.dataanalytics.app.mapper;

import me.dataanalytics.app.domain.Company;
import me.dataanalytics.app.domain.Metric;
import me.dataanalytics.app.domain.TrackedMetric;
import me.dataanalytics.app.domain.dto.MetricDto;
import me.dataanalytics.app.domain.dto.TrackedMetricDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackedMetricMapper {

    public TrackedMetric mapToTrackedMetric(TrackedMetricDto trackedMetricDto, Metric metric, Company company) {
        return new TrackedMetric(
                trackedMetricDto.getId(),
                metric,
                company,
                trackedMetricDto.getValue()
        );
    }

    public TrackedMetricDto mapToTrackedMetricDto(TrackedMetric trackedMetric) {
        return new TrackedMetricDto(
                trackedMetric.getId(),
                trackedMetric.getCompany().getId(),
                mapToMetricDto(trackedMetric.getMetric()),
                trackedMetric.getValue()
        );
    }

    public List<TrackedMetricDto> mapToTrackedMetricDtoList(List<TrackedMetric> trackedMetricList) {
        return trackedMetricList.stream()
                .map(this::mapToTrackedMetricDto)
                .collect(Collectors.toList());
    }

    private MetricDto mapToMetricDto(Metric metric) {
        return new MetricDto(
                metric.getId(),
                metric.getName()
        );
    }
}
