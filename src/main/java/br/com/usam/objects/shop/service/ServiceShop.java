/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.exception.BusinessException;
import br.com.usam.infrastructure.support.StringUtil;
import br.com.usam.objects.shop.dao.IDaoShop;
import br.com.usam.objects.shop.model.Shop;

/**
 *
 * @author vitor
 */
@Service
public class ServiceShop implements IServiceShop {

    // OBJETO
    @Autowired
    private IDaoShop daoShop;

    // CONSTANTES

    // mensagem de erro se o Shop for null;
    public final static String USUARIO_NULL = "Usuário null";

    // mensagem de erro se o Shop já existir;
    public final static String USUARIO_EXISTE = "O Usuário já existe,";

    // mensagem de erro se o Shop não existir no banco;
    public final static String USUARIO_NAO_EXISTE = "O Usuário não existe na base de dados";

    // mensagem de erro se o Shop for inválido;
    public final static String USUARIO_INVALIDO = "Usuário inválido";

    @Override
    public Shop save(Shop shop) {
        if (shop == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        return daoShop.save(shop);
    }

    @Override
    public void delete(Shop shop) {
        if (shop == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        this.daoShop.delete(shop);
    }

    @Override
    public List<Shop> getAll() {
        return (List<Shop>) this.daoShop.findAll();
    }

    @Override
    public Shop findById(Long id) {
        Optional<Shop> shop = daoShop.findById(id);
        return shop.isPresent() ? shop.get() : null;
    }

    @Override
    public List<Shop> getLookup(String term) {
        if (term == null || term == "") {
            return new ArrayList<Shop>(getAll().subList(0, 3));
        }

        List<Shop> lookup = new ArrayList<>();
        for (Shop p : getAll()) {
            if (p.getName().toLowerCase().contains(term.toLowerCase()) ||
                    term.toLowerCase().contains(p.getName().toLowerCase())) {
                lookup.add(p);
            }
        }

        return lookup;
    }

}
