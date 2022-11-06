package com.erisvan.where.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.erisvan.where.model.Account;
import com.erisvan.where.model.Calling;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private String name;
    private Account account;
    private List<Calling> callings;
}
