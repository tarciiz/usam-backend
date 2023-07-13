/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.usam.objects.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.repository.BaseRepository;
import br.com.usam.objects.product.model.Product;

/**
 *
 * @author vitor
 */
@Service
public interface IServiceProduct extends BaseRepository<Product> {
    public List<Product> getLookup(String term);
}
