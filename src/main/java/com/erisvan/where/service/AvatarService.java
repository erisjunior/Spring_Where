package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Avatar;
import com.erisvan.where.rest.dto.AvatarDTO;

@Service
public interface AvatarService {
    Avatar save(AvatarDTO dto);

    Avatar get(Integer id);

    List<Avatar> getAll();

    void delete(Integer id);

    Avatar update(Integer id, AvatarDTO dto);

}
