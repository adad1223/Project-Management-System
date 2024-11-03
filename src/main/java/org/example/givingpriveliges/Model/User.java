package org.example.givingpriveliges.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="ua")
public class User {
    @Id
    private String username;
    private String password;
    private String Role;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="user_projec",
    joinColumns = @JoinColumn(name="username"),
    inverseJoinColumns = @JoinColumn(name="project_id"))
    private Set<Project> projects=new HashSet<>();
}
