package com.modelo.jparepositoryh2.controller;

import com.modelo.jparepositoryh2.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.modelo.jparepositoryh2.repository.UserRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<Users>> findAll() {
        List<Users> result = repository.findAll();
        return ResponseEntity.ok(result);
    }
    @GetMapping(value = "/page")
    public ResponseEntity<Page<Users>> findAll(Pageable pageable) {
        Page<Users> result = repository.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/search-salary")
    public ResponseEntity<Page<Users>> searchBySalary(@RequestParam(defaultValue = "0") Double minSalary, @RequestParam(defaultValue = "1000000000000") Double maxSalary, Pageable pageable) {
        Page<Users> result = repository.findBySalaryBetween(minSalary, maxSalary, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/search-name")
    public ResponseEntity<Page<Users>> searchByName(@RequestParam(defaultValue = "") String name, Pageable pageable) {
        Page<Users> result = repository.findByNameContainingIgnoreCase(name, pageable);
        return ResponseEntity.ok(result);
    }
}
