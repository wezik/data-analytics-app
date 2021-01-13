package me.dataanalytics.app.controller;

import lombok.RequiredArgsConstructor;
import me.dataanalytics.app.domain.Company;
import me.dataanalytics.app.domain.Metric;
import me.dataanalytics.app.domain.TrackedMetric;
import me.dataanalytics.app.domain.dto.TrackedMetricDto;
import me.dataanalytics.app.exception.CompanyNotFoundException;
import me.dataanalytics.app.exception.MetricNotFoundException;
import me.dataanalytics.app.exception.TrackedMetricNotFoundException;
import me.dataanalytics.app.mapper.TrackedMetricMapper;
import me.dataanalytics.app.service.CompanyDbService;
import me.dataanalytics.app.service.MetricDbService;
import me.dataanalytics.app.service.TrackedMetricDbService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/tracked")
public class TrackedMetricController {

    private final CompanyDbService companyDbService;
    private final MetricDbService metricDbService;
    private final TrackedMetricDbService trackedMetricDbService;

    private final TrackedMetricMapper trackedMetricMapper;

    @GetMapping("get")
    public TrackedMetricDto getTrackedMetric(@RequestParam int id) {
        TrackedMetric trackedMetric = trackedMetricDbService.getTrackedMetric(id).orElseThrow(TrackedMetricNotFoundException::new);
        return trackedMetricMapper.mapToTrackedMetricDto(trackedMetric);
    }

    @GetMapping("list")
    public List<TrackedMetricDto> getTrackedMetrics() {
        List<TrackedMetric> trackedMetricList = trackedMetricDbService.getAllTrackedMetrics();
        return trackedMetricMapper.mapToTrackedMetricDtoList(trackedMetricList);
    }

    @PostMapping("add")
    public TrackedMetricDto createTrackedMetric(@RequestParam("company_id") int company_id, @RequestParam("metric_id") int metric_id, @RequestParam("value") double value) {
        Company company = companyDbService.getCompany(company_id).orElseThrow(CompanyNotFoundException::new);
        Metric metric = metricDbService.getMetric(metric_id).orElseThrow(MetricNotFoundException::new);
        TrackedMetric trackedMetric = new TrackedMetric(company,metric,value);
        return trackedMetricMapper.mapToTrackedMetricDto(trackedMetricDbService.saveTrackedMetric(trackedMetric));
    }

    @PutMapping("set")
    public TrackedMetricDto setTrackedMetricValue(@RequestParam int id,@RequestParam double value) {
        TrackedMetric trackedMetric = trackedMetricDbService.getTrackedMetric(id).orElseThrow(TrackedMetricNotFoundException::new);
        trackedMetric.setValue(value);
        return trackedMetricMapper.mapToTrackedMetricDto(trackedMetricDbService.saveTrackedMetric(trackedMetric));
    }

    @PostMapping("create")
    public TrackedMetricDto createTrackedMetric(@RequestBody TrackedMetricDto trackedMetricDto) {
        Company company = companyDbService.getCompany(trackedMetricDto.getCompany()).orElseThrow(CompanyNotFoundException::new);
        Metric metric = metricDbService.getMetric(trackedMetricDto.getMetric().getId()).orElseThrow(MetricNotFoundException::new);
        TrackedMetric trackedMetric = trackedMetricMapper.mapToTrackedMetric(trackedMetricDto,metric,company);
        return trackedMetricMapper.mapToTrackedMetricDto(trackedMetricDbService.saveTrackedMetric(trackedMetric));
    }

    @PutMapping("update")
    public TrackedMetricDto updateTrackedMetric(@RequestBody TrackedMetricDto trackedMetricDto) {
        TrackedMetric trackedMetric = trackedMetricDbService.getTrackedMetric(trackedMetricDto.getId()).orElseThrow(TrackedMetricNotFoundException::new);
        TrackedMetric updated = trackedMetricMapper.mapToTrackedMetric(trackedMetricDto,trackedMetric.getMetric(),trackedMetric.getCompany());
        return trackedMetricMapper.mapToTrackedMetricDto(trackedMetricDbService.saveTrackedMetric(updated));
    }


    @DeleteMapping("delete")
    public void deleteTrackedMetric(@RequestParam int id) {
        TrackedMetric trackedMetric = trackedMetricDbService.getTrackedMetric(id).orElseThrow(TrackedMetricNotFoundException::new);
        trackedMetricDbService.deleteTrackedMetric(trackedMetric);
    }

}
