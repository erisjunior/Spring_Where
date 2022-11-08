package com.erisvan.where.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String login;
    private String password;
    private Integer avatarId;
    private Integer storeId;
    private Integer clientId;
    private boolean admin;
}
