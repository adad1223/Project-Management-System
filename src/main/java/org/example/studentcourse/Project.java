package org.example.studentcourse;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "projectName")
    private String projectName;

    @Column(name = "technologyUsed")
    private String technologyUsed;

    @ManyToMany(mappedBy = "projects",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Employee> employees;

    public Project(String projectName, String technologyUsed) {
        this.projectName = projectName;
        this.technologyUsed = technologyUsed;

    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.getProjects().add(this); // Maintain both sides
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.getProjects().remove(this); // Maintain both sides
    }
    @Override
    public String toString() {
        return "Project [id=" + id + ", projectName=" + projectName
                + ", technologyUsed=" + technologyUsed + "]";
    }
}