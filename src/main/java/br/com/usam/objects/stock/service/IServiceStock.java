/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.usam.objects.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.repository.BaseRepository;
import br.com.usam.objects.stock.model.Stock;

/**
 *
 * @author vitor
 */
@Service
public interface IServiceStock extends BaseRepository<Stock> {
    public List<Stock> getLookup(String term);

}