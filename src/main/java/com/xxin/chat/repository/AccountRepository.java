package com.xxin.chat.repository;

import com.xxin.chat.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    public Account findByToken(String token);


}
