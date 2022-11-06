package com.erisvan.where.service;

import java.util.List;
import java.util.Optional;

import com.erisvan.where.model.Calling;
import com.erisvan.where.rest.dto.CallingDTO;

public interface CallingService {
    Calling save(CallingDTO dto);

    Optional<Calling> get(Integer id);

    List<Calling> getAll();

    void delete(Integer id);

    Calling update(Integer id, CallingDTO dto);

}
