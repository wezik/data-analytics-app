package me.dataanalytics.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Company_Types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CompanyType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "COMPANY_TYPE_ID")
    private int id;

    @Column(name = "COMPANY_TYPE_NAME")
    private String name;

    @OneToMany(
            targetEntity = Company.class,
            mappedBy = "type",
            cascade = CascadeType.REMOVE
    )
    private List<Company> companies;

}
