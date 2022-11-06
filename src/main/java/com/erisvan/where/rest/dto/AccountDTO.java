package com.erisvan.where.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.erisvan.where.model.Avatar;
import com.erisvan.where.model.Client;
import com.erisvan.where.model.Store;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String login;
    private String password;
    private Avatar avatar;
    private Store store;
    private Client client;
    private boolean admin;
}
