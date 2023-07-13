/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.stock.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.usam.infrastructure.model.PersistenceEntity;
import br.com.usam.objects.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "stock")
@Data
@EqualsAndHashCode(callSuper = false)
public class Stock extends PersistenceEntity {
    private String description;

    private String measure_type;
    private int quantity_per_measure;
    private float quantity_on_stock;
    private float total_quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    // @JsonIgnoreProperties("usuarios")
    private Product product; // producto

}
