/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.profile.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.usam.infrastructure.model.PersistenceEntity;
import br.com.usam.objects.shop.model.Shop;
import br.com.usam.objects.user.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author vitor
 */

@Entity
@Table(name = "profile")
@Data
@EqualsAndHashCode(callSuper = false)
public class Profile extends PersistenceEntity {
    private String description;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnoreProperties({ "profile", "password" })
    List<User> users;
}
