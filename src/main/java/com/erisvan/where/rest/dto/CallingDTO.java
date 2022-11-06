package com.erisvan.where.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.erisvan.where.model.Client;
import com.erisvan.where.model.Store;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallingDTO {
    private String title;
    private String description;
    private Client client;
    private List<Store> stores;
}
