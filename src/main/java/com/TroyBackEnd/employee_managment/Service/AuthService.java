package com.TroyBackEnd.employee_managment.Service;

import com.TroyBackEnd.employee_managment.Entity.Employee;
import com.TroyBackEnd.employee_managment.Entity.UserAccount;
import com.TroyBackEnd.employee_managment.Jwt.JwtHelper;
import com.TroyBackEnd.employee_managment.Shared.CustomResponseException;
import com.TroyBackEnd.employee_managment.dtos.LoginRequest;
import com.TroyBackEnd.employee_managment.dtos.SginupRequest;
import com.TroyBackEnd.employee_managment.repositpories.EmployeeRepo;
import com.TroyBackEnd.employee_managment.repositpories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    private UserAccountRepo userAccountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;

    public void signup(SginupRequest sginupRequest) {
        Employee employee = employeeRepo.findById(sginupRequest.employeeId()).orElseThrow(() ->
                CustomResponseException.ResourceNotFound
                        ("Employee with id " + sginupRequest.employeeId() + "not found"));

        UserAccount account = new UserAccount();
        account.setUsername(sginupRequest.username());
        account.setPassword(passwordEncoder.encode(sginupRequest.password()));
        account.setEmployee(employee);
        userAccountRepo.save(account);
    }

    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        loginRequest.username()
                        , loginRequest.password())
        );
        UserAccount user = userAccountRepo.findByUsername(loginRequest.username()).orElseThrow(() -> CustomResponseException.BadCredentials());
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("userId", user.getId());
        return jwtHelper.generateToken(customClaims, user);
    }
}
