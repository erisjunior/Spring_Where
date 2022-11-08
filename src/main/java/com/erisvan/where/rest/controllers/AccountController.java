package com.erisvan.where.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.exception.IncorrectCredentialsException;
import com.erisvan.where.model.Account;
import com.erisvan.where.rest.dto.AccountCredentialsDTO;
import com.erisvan.where.rest.dto.AccountDTO;
import com.erisvan.where.rest.dto.TokenDTO;
import com.erisvan.where.service.impl.AccountServiceImpl;
import com.erisvan.where.security.JwtService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl service;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDTO save(@RequestBody @Valid AccountCredentialsDTO dto) {
        service.save(dto);

        return auth(dto);
    }

    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody AccountCredentialsDTO dto) {
        try {
            Account account = Account
                    .builder()
                    .login(dto.getLogin())
                    .password(dto.getPassword())
                    .build();
            service.auth(account);
            String token = jwtService.generateToken(account);
            return new TokenDTO(account.getLogin(), token);
        } catch (UsernameNotFoundException | IncorrectCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("{id}")
    public Account get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    public List<Account> getAll(Account filter) {
        return service.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody AccountDTO dto) {
        service.update(id, dto);
    }

    @PostMapping("{id}/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void toggleAdmin(@PathVariable Integer id) {
        service.toggleAdmin(id);
    }
}
