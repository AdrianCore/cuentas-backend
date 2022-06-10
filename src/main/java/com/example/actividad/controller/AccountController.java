package com.example.actividad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.actividad.exceptions.ResourceNotFoundException;
import com.example.actividad.model.Account;
import com.example.actividad.repository.AccountRepository;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Método para listar todas las cuentas
    @GetMapping
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    // Método para guardar la cuenta
    @PostMapping 
    public Account saveAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

    // Método para buscar cuentas por ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable Long id) {
        Account cuenta = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la cuenta con el id: " + id));
        return ResponseEntity.ok(cuenta);
    }

    // Método para actualizar cuenta
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        Account cuenta = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la cuenta con el id: " + id));

        cuenta.setClave(accountDetails.getClave());
        cuenta.setNombre(accountDetails.getNombre());

        Account updatedAccount = accountRepository.save(cuenta);
        return ResponseEntity.ok(updatedAccount);
    }

    // Método para eliminar una cuenta
    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,Boolean >> deleteAccount(@PathVariable Long id) {
        Account cuenta = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la cuenta con el id: " + id));

        accountRepository.delete(cuenta);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Eliminar",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
