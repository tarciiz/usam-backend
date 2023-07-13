/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.usam.infrastructure.model.PersistenceEntity;
import br.com.usam.objects.profile.model.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author vitor
 */

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends PersistenceEntity {
    private String login;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Profile profile; // loja
}
