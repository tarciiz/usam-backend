/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.product.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.usam.infrastructure.model.PersistenceEntity;
import br.com.usam.objects.shop.model.Shop;
import jakarta.persistence.Column;
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
@Table(name = "product")
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends PersistenceEntity {
    private String description;

    @Column(nullable = true)
    private String barcode;
    private float purchase_price; // Compra
    private float sale_price; // venda
    private float quantity; // venda
    @ManyToOne(fetch = FetchType.EAGER)
    // @JsonIgnoreProperties("usuarios")
    private Shop shop; // loja

}
