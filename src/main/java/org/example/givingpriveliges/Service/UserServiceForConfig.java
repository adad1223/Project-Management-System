package org.example.givingpriveliges.Service;

import org.example.givingpriveliges.Model.User;
import org.example.givingpriveliges.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceForConfig implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u=userRepo.findByUsername(username);
        if (u==null){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrinciple(u);
    }
}
