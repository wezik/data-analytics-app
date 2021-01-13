package me.dataanalytics.app.service;

import lombok.RequiredArgsConstructor;
import me.dataanalytics.app.domain.Metric;
import me.dataanalytics.app.repository.MetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetricDbService {

    private final MetricRepository metricRepository;

    public Optional<Metric> getMetric(int id) {
        return metricRepository.findById(id);
    }

    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public Metric saveMetric(Metric metric) {
        return metricRepository.save(metric);
    }

    public void deleteMetric(Metric metric) {
        metricRepository.delete(metric);
    }
}
