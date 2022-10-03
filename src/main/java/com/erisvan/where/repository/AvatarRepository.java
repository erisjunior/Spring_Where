package com.erisvan.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
}
