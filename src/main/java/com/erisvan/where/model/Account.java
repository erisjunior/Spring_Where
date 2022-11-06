package com.erisvan.where.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotEmpty(message = "{field.login.required}")
    private String login;

    @Column
    @NotEmpty(message = "{field.password.required}")
    private String password;

    @OneToOne(optional = true)
    private Avatar avatar;

    @OneToOne(optional = true)
    private Store store;

    @OneToOne(optional = true)
    private Client client;

    @Column
    private boolean admin;

}
