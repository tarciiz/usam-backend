/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.stock.model;

import lombok.Data;

@Data
public class StockAjustment {
    private Stock stock;

    private float ajust_quantity;

}
