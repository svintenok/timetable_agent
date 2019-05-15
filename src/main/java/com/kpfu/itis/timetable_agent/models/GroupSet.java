package com.kpfu.itis.timetable_agent.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_set")
public class GroupSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_set_group",
            joinColumns = @JoinColumn(name = "group_set_id"),
            inverseJoinColumns = @JoinColumn(name = "study_group_id"))
    private Set<Group> groups;

    @Override
    public String toString() {
        StringBuilder groupSetString = new StringBuilder();
        groupSetString.append(name).append("{");
        for (Group group: groups){
            groupSetString.append(group.getGroupNum()).append(", ");
        }
        groupSetString.delete(groupSetString.lastIndexOf(","), groupSetString.length()).append("}");
        return groupSetString.toString();
    }

    @Transient
    public int getStudentsCount(){
        int studentCounts = 0;
        for (Group group : groups){
            if (group.getStudentCount() == -1)
                return -1;
            studentCounts += group.getStudentCount();
        }
        return studentCounts;
    }
}

