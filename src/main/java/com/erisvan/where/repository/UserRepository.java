package com.erisvan.where.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erisvan.where.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  // @Query(value = "SELECT u FROM User u JOIN FETCH u.callings WHERE u.id =:id")
  // User findOneWithCallings(@Param("id") Integer id);
}
