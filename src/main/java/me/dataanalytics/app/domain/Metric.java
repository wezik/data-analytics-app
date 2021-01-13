package me.dataanalytics.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Metrics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Metric {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "METRIC_ID")
    private int id;

    @Column(name = "METRIC_NAME")
    private String name;
    
    @OneToMany(
            targetEntity = TrackedMetric.class,
            mappedBy = "metric",
            cascade = CascadeType.REMOVE
    )
    private List<TrackedMetric> tracked;

    public Metric(String name) {
        this.name = name;
    }

}
