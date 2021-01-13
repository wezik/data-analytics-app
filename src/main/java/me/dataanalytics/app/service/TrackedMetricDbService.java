package me.dataanalytics.app.service;

import lombok.RequiredArgsConstructor;
import me.dataanalytics.app.domain.TrackedMetric;
import me.dataanalytics.app.repository.TrackedMetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackedMetricDbService {

    private final TrackedMetricRepository trackedMetricRepository;

    public Optional<TrackedMetric> getTrackedMetric(int id) {
        return trackedMetricRepository.findById(id);
    }

    public List<TrackedMetric> getAllTrackedMetrics() {
        return trackedMetricRepository.findAll();
    }

    public List<TrackedMetric> getAllTrackedMetricsByMetricId(int id) {
        return trackedMetricRepository.findByMetricId(id);
    }

    public TrackedMetric saveTrackedMetric(TrackedMetric trackedMetric) {
        return trackedMetricRepository.save(trackedMetric);
    }

    public void deleteTrackedMetric(TrackedMetric trackedMetric) {
        trackedMetricRepository.delete(trackedMetric);
    }
}
