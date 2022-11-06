package com.erisvan.where.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.erisvan.where.model.User;
import com.erisvan.where.repository.UserRepository;

/*
 * interface do spring security serve para definir o carregam, UserDetailsento de usuários através de uma base de dados
 */
@Component
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Transactional
    public User salvar(User usuario) {
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();

        /*
         * Usuário em memória
         * if(!username.equals("cicrano")){
         * throw new UsernameNotFoundException("Usuário não encontrado na base.");
         * }
         * 
         * return User
         * .builder()
         * .username("cicrano")
         * .password(passwordEncoder.encode("123"))
         * .roles("USER")
         * .build();
         */
    }

}
