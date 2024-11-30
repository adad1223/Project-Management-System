package org.example.givingpriveliges.Controller;

import org.example.givingpriveliges.Model.Project;
import org.example.givingpriveliges.Model.User;
import org.example.givingpriveliges.Service.JWTService;
import org.example.givingpriveliges.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addUser(@RequestBody User user) throws Exception {
        return userService.addUser(user);
    }
    @GetMapping("{id}")
    public User getUser(@PathVariable String id) {
        return userService.findUser(id);
    }
    @PostMapping("/project/{id}")
    public User addProject(@RequestBody User user,@PathVariable Long id) {
        return userService.CreateUserforProject(user,id);
    }
    @GetMapping("/projects")
    public Set<Project> getProjectsForAuthenticatedUser() {
        return userService.getProjectsForAuthenticatedUser();
    }
    @PostMapping("/projects/{username}/{id}")
    public String addUseerToProj(@PathVariable String username,@PathVariable Long id) {
        return userService.addExistingUsertoProject(username,id);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {
//        @Autowired
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        else{
            return "Not Logged in";
        }
    }
}
