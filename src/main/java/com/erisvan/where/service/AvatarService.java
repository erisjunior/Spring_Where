package com.erisvan.where.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erisvan.where.model.Avatar;

@Service
public interface AvatarService {

    public List<Avatar> getAllAvatars();

    public void createAvatar(Avatar avatar);

    public void deleteAvatar(Avatar avatar);

    public Avatar getAvatarById(Integer id);

}
