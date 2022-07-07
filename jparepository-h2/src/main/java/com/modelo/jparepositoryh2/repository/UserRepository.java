package com.modelo.jparepositoryh2.repository;

import com.modelo.jparepositoryh2.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("SELECT obj FROM Users obj WHERE obj.salary >= :minSalary AND obj.salary <= :maxSalary")
    Page<Users> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);

    @Query("SELECT obj FROM Users obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Users> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
