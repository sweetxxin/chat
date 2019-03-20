package com.xxin.chat.repository;

import com.xxin.chat.entity.Account;
import com.xxin.chat.entity.Department;
import com.xxin.chat.entity.EnrollTable;
import com.xxin.chat.entity.Society;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollTableRepository extends JpaRepository<EnrollTable, Integer> {

    public List<EnrollTable> findByAccount(Account account);

    public List<EnrollTable> findByDepartment(Department department);

    public List<EnrollTable> findBySociety(Society society);

    public EnrollTable findByAccountAndDepartment(Account account, Department department);
}
