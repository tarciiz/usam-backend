/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.exception.BusinessException;
import br.com.usam.infrastructure.support.StringUtil;
import br.com.usam.objects.product.dao.IDaoProduct;
import br.com.usam.objects.product.model.Product;

/**
 *
 * @author vitor
 */
@Service
public class ServiceProduct implements IServiceProduct {

    // OBJETO
    @Autowired
    private IDaoProduct daoProduct;

    // CONSTANTES

    // mensagem de erro se o Product for null;
    public final static String USUARIO_NULL = "Usuário null";

    // mensagem de erro se o Product já existir;
    public final static String USUARIO_EXISTE = "O Usuário já existe,";

    // mensagem de erro se o Product não existir no banco;
    public final static String USUARIO_NAO_EXISTE = "O Usuário não existe na base de dados";

    // mensagem de erro se o Product for inválido;
    public final static String USUARIO_INVALIDO = "Usuário inválido";

    @Override
    public Product save(Product product) {
        if (product == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        return daoProduct.save(product);
    }

    @Override
    public void delete(Product product) {
        if (product == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        this.daoProduct.delete(product);
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) this.daoProduct.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = daoProduct.findById(id);
        return product.isPresent() ? product.get() : null;
    }

    @Override
    public List<Product> getLookup(String term) {
        if (term == null || term == "") {
            return new ArrayList<Product>(getAll().subList(0, 3));
        }

        List<Product> lookup = new ArrayList<>();
        for (Product p : getAll()) {
            if (p.getName().toLowerCase().contains(term.toLowerCase()) ||
                    term.toLowerCase().contains(p.getName().toLowerCase())) {
                lookup.add(p);
            }
        }

        return lookup;
    }

}
