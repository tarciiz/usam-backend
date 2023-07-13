/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.exception.BusinessException;
import br.com.usam.infrastructure.support.StringUtil;
import br.com.usam.objects.stock.dao.IDaoStock;
import br.com.usam.objects.stock.model.Stock;

/**
 *
 * @author vitor
 */
@Service
public class ServiceStock implements IServiceStock {

    // OBJETO
    @Autowired
    private IDaoStock daoStock;

    // CONSTANTES

    // mensagem de erro se o Stock for null;
    public final static String USUARIO_NULL = "Usuário null";

    // mensagem de erro se o Stock já existir;
    public final static String USUARIO_EXISTE = "O Usuário já existe,";

    // mensagem de erro se o Stock não existir no banco;
    public final static String USUARIO_NAO_EXISTE = "O Usuário não existe na base de dados";

    // mensagem de erro se o Stock for inválido;
    public final static String USUARIO_INVALIDO = "Usuário inválido";

    @Override
    public Stock save(Stock stock) {
        if (stock == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        return daoStock.save(stock);
    }

    @Override
    public void delete(Stock stock) {
        if (stock == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        this.daoStock.delete(stock);
    }

    @Override
    public List<Stock> getAll() {
        return (List<Stock>) this.daoStock.findAll();
    }

    @Override
    public Stock findById(Long id) {
        Optional<Stock> stock = daoStock.findById(id);
        return stock.isPresent() ? stock.get() : null;
    }

    @Override
    public List<Stock> getLookup(String term) {
        if (term == null || term == "") {
            return new ArrayList<Stock>(getAll().subList(0, 3));
        }

        List<Stock> lookup = new ArrayList<>();
        for (Stock p : getAll()) {
            if (p.getName().toLowerCase().contains(term.toLowerCase()) ||
                    term.toLowerCase().contains(p.getName().toLowerCase())) {
                lookup.add(p);
            }
        }

        return lookup;
    }

}
