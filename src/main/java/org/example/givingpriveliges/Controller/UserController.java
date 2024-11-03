package org.example.givingpriveliges.Controller;

import org.example.givingpriveliges.Model.User;
import org.example.givingpriveliges.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
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
}
