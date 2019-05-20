package com.kpfu.itis.timetable_agent.models;

import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restriction")
public class Restriction extends RestrictionViolation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "factor_id")
    private Factor factor;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    @Column(name = "restriction_value", nullable = false)
    private double restrictionValue;

    @Column(nullable = false)
    private boolean hard;

    @Column(nullable = true)
    private Integer priority;

    @Column
    private boolean enabled;

    @Override
    public String warningString() {
        return name;
    }
}
