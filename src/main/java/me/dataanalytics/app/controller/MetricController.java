package me.dataanalytics.app.controller;

import lombok.RequiredArgsConstructor;
import me.dataanalytics.app.domain.Metric;
import me.dataanalytics.app.domain.TrackedMetric;
import me.dataanalytics.app.domain.dto.MetricDto;
import me.dataanalytics.app.exception.MetricNotFoundException;
import me.dataanalytics.app.mapper.MetricMapper;
import me.dataanalytics.app.service.MetricDbService;
import me.dataanalytics.app.service.TrackedMetricDbService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/metric")
public class MetricController {

    private final MetricDbService metricDbService;
    private final TrackedMetricDbService trackedMetricDbService;

    private final MetricMapper metricMapper;

    @GetMapping("get")
    public MetricDto getMetric(@RequestParam int id) {
        return metricMapper.mapToMetricDto(metricDbService.getMetric(id).orElseThrow(MetricNotFoundException::new));
    }

    @GetMapping("list")
    public List<MetricDto> getMetrics() {
        return metricMapper.mapToMetricDtoList(metricDbService.getAllMetrics());
    }

    @PostMapping("create")
    public MetricDto createMetric(@RequestBody MetricDto metricDto) {
        return metricMapper.mapToMetricDto(metricDbService.saveMetric(new Metric(metricDto.getName())));
    }

    @PutMapping("update")
    public MetricDto updateMetric(@RequestBody MetricDto metricDto) {
        List<TrackedMetric> trackedMetricList = trackedMetricDbService.getAllTrackedMetricsByMetricId(metricDto.getId());
        Metric metric = metricMapper.mapToMetric(metricDto,trackedMetricList);
        return metricMapper.mapToMetricDto(metricDbService.saveMetric(metric));
    }

    @DeleteMapping("delete")
    public void deleteMetric(@RequestParam int id) {
        Metric metric = metricDbService.getMetric(id).orElseThrow(MetricNotFoundException::new);
        metricDbService.deleteMetric(metric);
    }

}
