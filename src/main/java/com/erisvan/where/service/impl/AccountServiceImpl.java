package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.model.Account;
import com.erisvan.where.repository.AccountRepository;
import com.erisvan.where.rest.dto.AccountDTO;

@Component
public class AccountServiceImpl implements UserDetailsService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository repository;

    @Transactional
    public Account save(Account account) {
        return repository.save(account);
    }

    public Optional<Account> get(Integer id) {
        return repository.findById(id);
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Account update(Integer id, AccountDTO dto) {
        Optional<Account> account = repository.findById(id);

        if (account.isPresent()) {
            account.get().setLogin(dto.getLogin() != null ? dto.getLogin() : account.get().getLogin());
            account.get().setPassword(dto.getPassword() != null ? dto.getPassword() : account.get().getPassword());
            account.get().setAvatar(dto.getAvatar() != null ? dto.getAvatar() : account.get().getAvatar());
            account.get().setStore(dto.getStore() != null ? dto.getStore() : account.get().getStore());
            account.get().setClient(dto.getClient() != null ? dto.getClient() : account.get().getClient());
            return repository.save(account.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Account account = repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        String[] roles;

        if (account.isAdmin()) {
            roles = account.getStore() != null ? new String[] { "ADMIN", "STORE", "CLIENT" }
                    : new String[] { "ADMIN", "CLIENT" };
        } else {
            roles = account.getStore() != null ? new String[] { "STORE", "CLIENT" } : new String[] { "CLIENT" };
        }

        return User
                .builder()
                .username(account.getLogin())
                .password(account.getPassword())
                .roles(roles)
                .build();

    }

}
