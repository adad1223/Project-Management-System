package org.example.studentcourse;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "technicalSkill")
    private String technicalSkill;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "EMPLOYEE_PROJECT_MAPPING", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects=new HashSet<>();

    public Employee(String name, String email, String technicalSkill) {
        this.name = name;
        this.email = email;
        this.technicalSkill = technicalSkill;
    }
    public void addProject(Project project) {
        projects.add(project);
        project.getEmployees().add(this); // Maintain both sides
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.getEmployees().remove(this); // Maintain both sides
    }
    @Override
    public String toString() {
        return "Employee [email=" + email + ", id=" + id + ", name=" + name + ", technicalSkill=" + technicalSkill + "]";
    }
}