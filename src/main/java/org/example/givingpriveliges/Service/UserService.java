package org.example.givingpriveliges.Service;

import org.example.givingpriveliges.Model.User;
import org.example.givingpriveliges.Repo.Pr;
import org.example.givingpriveliges.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.givingpriveliges.Model.Project;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Pr projectRepository;
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//    boolean isAdmin = authorities.stream()
//            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addUser(User user) throws Exception {
//        if (isAdmin) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepo.save(user);
//        }
//        throw new Exception("NO PERMISSION");
    }


    public User findUser(String id){
        User user = userRepo.findById(id).orElse(null);
        return user;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User CreateUserforProject(User user,long id){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        Project pr=projectRepository.findById(id).orElse(null);

        if (pr!=null){
//            pr.getUsers().add(user);
            user.getProjects().add(pr);
//            projectRepository.save(pr);
        }
        return userRepo.save(user);
    }
    }

