/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.exception.BusinessException;
import br.com.usam.infrastructure.support.StringUtil;
import br.com.usam.objects.user.dao.IDaoUser;
import br.com.usam.objects.user.model.User;

/**
 *
 * @author vitor
 */
@Service
public class ServiceUser implements IServiceUser {

    // OBJETO
    @Autowired
    private IDaoUser daoUser;

    // CONSTANTES

    // mensagem de erro se o User for null;
    public final static String USUARIO_NULL = "Usuário null";

    // mensagem de erro se o User já existir;
    public final static String USUARIO_EXISTE = "O Usuário já existe,";

    // mensagem de erro se o User não existir no banco;
    public final static String USUARIO_NAO_EXISTE = "O Usuário não existe na base de dados";

    // mensagem de erro se o User for inválido;
    public final static String USUARIO_INVALIDO = "Usuário inválido";

    @Override
    public User save(User user) {
        if (user == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        return daoUser.save(user);
    }

    @Override
    public void delete(User user) {
        if (user == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        this.daoUser.delete(user);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) this.daoUser.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = daoUser.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    @Override
    public List<User> getLookup(String term) {
        if (term == null || term == "") {
            return new ArrayList<User>(getAll().subList(0, 3));
        }

        List<User> lookup = new ArrayList<>();
        for (User p : getAll()) {
            if (p.getName().toLowerCase().contains(term.toLowerCase()) ||
                    term.toLowerCase().contains(p.getName().toLowerCase())) {
                lookup.add(p);
            }
        }

        return lookup;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws Exception {
        if (StringUtil.getInstance().isNullOrEmpty(login)) {
            throw new Exception("Preencha o login!");
        }
        if (StringUtil.getInstance().isNullOrEmpty(password)) {
            throw new Exception("Preencha a senha!");
        }

        try {
            Optional<User> user = daoUser.findByLoginAndPassword(login, password);

            if (!user.isPresent()) {
                throw new Exception("Usuário não encontrado!");
            }

            return user.get();
        } catch (Exception e) {
            throw e;
        }
    }

}
