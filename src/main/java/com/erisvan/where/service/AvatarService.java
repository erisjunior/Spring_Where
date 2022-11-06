package com.erisvan.where.service;

import java.util.List;
import java.util.Optional;

import com.erisvan.where.model.Avatar;
import com.erisvan.where.rest.dto.AvatarDTO;

public interface AvatarService {
    Avatar save(AvatarDTO dto);

    Optional<Avatar> get(Integer id);

    List<Avatar> getAll();

    void delete(Integer id);

    Avatar update(Integer id, AvatarDTO dto);

}
