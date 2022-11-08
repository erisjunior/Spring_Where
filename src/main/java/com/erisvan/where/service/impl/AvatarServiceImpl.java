package com.erisvan.where.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.exception.BusinessException;
import com.erisvan.where.model.Account;
import com.erisvan.where.model.Avatar;
import com.erisvan.where.repository.AccountRepository;
import com.erisvan.where.repository.AvatarRepository;
import com.erisvan.where.rest.dto.AvatarDTO;
import com.erisvan.where.service.AvatarService;

@Component
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Avatar save(AvatarDTO dto) {
        Avatar avatar = new Avatar();
        avatar.setUrl(dto.getUrl());

        Account account = accountRepository
                .findById(dto.getAccountId())
                .orElseThrow(() -> new BusinessException("Invalid account id."));

        avatar.setAccount(account);
        return repository.save(avatar);
    }

    @Override
    public Avatar get(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar not found"));
    }

    @Override
    public List<Avatar> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Avatar update(Integer id, AvatarDTO dto) {
        Optional<Avatar> avatar = repository.findById(id);

        if (avatar.isPresent()) {
            avatar.get().setUrl(dto.getUrl() != null ? dto.getUrl() : avatar.get().getUrl());

            if (dto.getAccountId() != null) {
                Account account = accountRepository
                        .findById(dto.getAccountId())
                        .orElseThrow(() -> new BusinessException("Invalid account id."));
                avatar.get().setAccount(account);
            }

            return repository.save(avatar.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar not found");
    }

}
