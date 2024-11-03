package org.example.givingpriveliges.Service;

import org.example.givingpriveliges.Model.Project;
import org.example.givingpriveliges.Repo.Pr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private Pr projectRepository;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }
//    public

}
