package com.kpfu.itis.timetable_agent.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pair_type")
public class PairType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String type;
}


/*
public enum PairType {
    PRACTICE("практика"),
    LECTURE("лекция"),
    ELECTIVE("курс по выбору"),
    PHYSICAL_CULTURE("физкультура");

    private String type;

    PairType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
*/