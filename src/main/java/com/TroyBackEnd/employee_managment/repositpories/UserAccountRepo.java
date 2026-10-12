package com.TroyBackEnd.employee_managment.repositpories;

import com.TroyBackEnd.employee_managment.Entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, UUID> {


    Optional<UserAccount> findByUsername(String username);
}
