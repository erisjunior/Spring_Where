package com.erisvan.where.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erisvan.where.model.Avatar;
import com.erisvan.where.repository.AvatarRepository;

@Component
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepository avatarRepository;

    @Override
    public List<Avatar> getAllAvatars() {
        return avatarRepository.findAll();
    }

    @Override
    public void createAvatar(Avatar avatar) {
        avatarRepository.save(avatar);
    }

    @Override
    public void deleteAvatar(Avatar avatar) {
        avatarRepository.delete(avatar);
    }

    @Override
    public Avatar getAvatarById(Integer id) {
        return avatarRepository.findById(id).map(avatar -> {
            return avatar;
        }).orElseThrow(() -> null);
    }

}
