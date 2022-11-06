package com.erisvan.where.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import com.erisvan.where.model.Calling;
import com.erisvan.where.model.Store;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;
    private Set<Calling> callings;
    private List<Store> stores;
}
