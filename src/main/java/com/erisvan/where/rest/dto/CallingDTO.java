package com.erisvan.where.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallingDTO {
    private String title;
    private String description;
    private Integer clientId;
    private Integer categoryId;
}
