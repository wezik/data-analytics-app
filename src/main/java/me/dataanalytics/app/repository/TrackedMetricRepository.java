package me.dataanalytics.app.repository;

import me.dataanalytics.app.domain.TrackedMetric;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrackedMetricRepository extends CrudRepository<TrackedMetric,Integer> {
    Optional<TrackedMetric> findById(int id);
    @Query
    List<TrackedMetric> findByMetricId(@Param("id")int id);
    List<TrackedMetric> findAll();
}
