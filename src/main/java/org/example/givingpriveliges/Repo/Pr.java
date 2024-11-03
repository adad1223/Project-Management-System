package org.example.givingpriveliges.Repo;

import org.example.givingpriveliges.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pr extends JpaRepository<Project,Long> {
}
