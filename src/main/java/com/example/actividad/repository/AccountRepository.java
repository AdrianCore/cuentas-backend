package com.example.actividad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.actividad.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
   
}
