package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Calling;
import com.erisvan.where.rest.dto.CallingDTO;

@Service
public interface CallingService {
    Calling save(CallingDTO dto);

    Calling get(Integer id);

    List<Calling> getAll();

    void delete(Integer id);

    Calling update(Integer id, CallingDTO dto);

}
