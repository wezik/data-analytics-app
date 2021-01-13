package me.dataanalytics.app.repository;

import me.dataanalytics.app.domain.Metric;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MetricRepository extends CrudRepository<Metric, Integer> {
    Optional<Metric> findById(int id);
    List<Metric> findAll();
}
