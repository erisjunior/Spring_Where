package com.erisvan.where.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.erisvan.where.model.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarDTO {
    private String url;
    private Account account;
}
