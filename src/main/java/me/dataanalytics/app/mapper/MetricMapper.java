package me.dataanalytics.app.mapper;

import me.dataanalytics.app.domain.Metric;
import me.dataanalytics.app.domain.TrackedMetric;
import me.dataanalytics.app.domain.dto.MetricDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetricMapper {

    public Metric mapToMetric(MetricDto metricDto, List<TrackedMetric> trackedMetricList) {
        return new Metric(
                metricDto.getId(),
                metricDto.getName(),
                trackedMetricList
                );
    }

    public MetricDto mapToMetricDto(Metric metric) {
        return new MetricDto(
                metric.getId(),
                metric.getName()
        );
    }

    public List<MetricDto> mapToMetricDtoList(List<Metric> metricList) {
        return metricList.stream()
                .map(this::mapToMetricDto)
                .collect(Collectors.toList());
    }

}
