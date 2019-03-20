package com.xxin.chat.repository;

import com.xxin.chat.entity.Department;
import com.xxin.chat.entity.Society;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    public List<Department> findBySociety(Society society);
}
