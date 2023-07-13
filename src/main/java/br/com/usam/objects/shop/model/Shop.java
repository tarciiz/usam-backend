/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.shop.model;

import br.com.usam.infrastructure.model.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author vitor
 */

@Entity
@Table(name = "shop")
@Data
@EqualsAndHashCode(callSuper = false)
public class Shop extends PersistenceEntity {
    private String description;

    private String legal_document;

}
