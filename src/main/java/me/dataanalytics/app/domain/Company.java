package me.dataanalytics.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "Company.findByCompanyTypeId",
        query = "FROM Companies WHERE COMPANY_TYPE_ID = :id"
)

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Companies")
public class Company {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private int id;

    @Column(name = "COMPANY_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "COMPANY_TYPE_ID")
    private CompanyType type;

    @OneToMany(
            targetEntity = TrackedMetric.class,
            mappedBy = "company",
            cascade = CascadeType.REMOVE
    )
    private List<TrackedMetric> metrics;

    public Company(String name, CompanyType type) {
        this.name = name;
        this.type = type;
        this.metrics = new ArrayList<>();
    }

}
