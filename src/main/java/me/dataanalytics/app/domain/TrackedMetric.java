package me.dataanalytics.app.domain;

import lombok.*;

import javax.persistence.*;

@NamedQuery(
        name = "TrackedMetric.findByMetricId",
        query = "FROM Tracked_Metrics WHERE METRIC_ID = :id"
)

@Entity(name = "Tracked_Metrics")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrackedMetric {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "TRACKED_METRIC_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "METRIC_ID")
    private Metric metric;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Column(name = "TRACKED_METRIC_VALUE")
    private double value;

    public TrackedMetric(Company company,Metric metric,double value) {
        this.company = company;
        this.metric = metric;
        this.value = value;
    }

    public String getName() {
        return metric.getName();
    }
}
