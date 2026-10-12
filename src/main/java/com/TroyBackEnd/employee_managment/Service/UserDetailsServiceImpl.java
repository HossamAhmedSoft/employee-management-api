package com.TroyBackEnd.employee_managment.Service;

import com.TroyBackEnd.employee_managment.Entity.UserAccount;
import com.TroyBackEnd.employee_managment.Shared.CustomResponseException;
import com.TroyBackEnd.employee_managment.repositpories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> account = userAccountRepo.findByUsername(username);

        if (account.isEmpty()) {
            throw CustomResponseException.BadCredentials();
        }
        UserAccount user = account.get();

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
