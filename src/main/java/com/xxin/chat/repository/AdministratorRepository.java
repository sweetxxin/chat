package com.xxin.chat.repository;

import com.xxin.chat.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    public Administrator findByUsername(String username);

    public Administrator findByToken(String token);
    public Administrator findByEmail(String email);
}
