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

import com.erisvan.where.exception.BusinessException;
import com.erisvan.where.exception.IncorrectCredentialsException;
import com.erisvan.where.model.Account;
import com.erisvan.where.model.Avatar;
import com.erisvan.where.model.Client;
import com.erisvan.where.model.Store;
import com.erisvan.where.repository.AccountRepository;
import com.erisvan.where.repository.AvatarRepository;
import com.erisvan.where.repository.ClientRepository;
import com.erisvan.where.repository.StoreRepository;
import com.erisvan.where.rest.dto.AccountCredentialsDTO;
import com.erisvan.where.rest.dto.AccountDTO;

@Component
public class AccountServiceImpl implements UserDetailsService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    public Account save(AccountCredentialsDTO dto) {

        try {
            Optional<Account> existentAccount = repository.findByLogin(dto.getLogin());
            if (existentAccount.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login already in use");
            }
        } catch (Exception e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
        }

        Account account = new Account();
        account.setLogin(dto.getLogin());

        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        account.setPassword(encryptedPassword);
        account.setAdmin(false);

        return repository.save(account);

    }

    public Account get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Account update(Integer id, AccountDTO dto) {
        Optional<Account> account = repository.findById(id);

        try {
            if (dto.getLogin() != null) {
                Optional<Account> existentAccount = repository.findByLogin(dto.getLogin());
                if (existentAccount.isPresent()) {
                    if (existentAccount.get().getId() != account.get().getId()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login already in use");
                    }
                }
            }
        } catch (Exception e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
        }

        if (account.isPresent()) {

            if (dto.getPassword() != null) {
                String encryptedPassword = passwordEncoder.encode(dto.getPassword());
                account.get().setPassword(encryptedPassword);
            }

            account.get().setLogin(dto.getLogin() != null ? dto.getLogin() : account.get().getLogin());

            if (dto.getAvatarId() != null) {
                Avatar avatar = avatarRepository
                        .findById(dto.getAvatarId())
                        .orElseThrow(() -> new BusinessException("Invalid avatar id."));

                account.get().setAvatar(avatar);
            }

            if (dto.getClientId() != null) {
                Client client = clientRepository
                        .findById(dto.getAvatarId())
                        .orElseThrow(() -> new BusinessException("Invalid client id."));

                account.get().setClient(client);
            }

            if (dto.getStoreId() != null) {
                Store store = storeRepository
                        .findById(dto.getStoreId())
                        .orElseThrow(() -> new BusinessException("Invalid store id."));

                account.get().setStore(store);
            }

            return repository.save(account.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
    }

    public UserDetails auth(Account account) {
        UserDetails user = loadUserByUsername(account.getLogin());
        boolean passwordMatch = passwordEncoder.matches(account.getPassword(), user.getPassword());

        if (passwordMatch) {
            return user;
        }

        throw new IncorrectCredentialsException();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Account account = repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found."));

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
