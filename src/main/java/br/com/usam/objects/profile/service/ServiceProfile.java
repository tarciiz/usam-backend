/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.usam.objects.profile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.exception.BusinessException;
import br.com.usam.infrastructure.support.StringUtil;
import br.com.usam.objects.profile.dao.IDaoProfile;
import br.com.usam.objects.profile.model.Profile;

/**
 *
 * @author vitor
 */
@Service
public class ServiceProfile implements IServiceProfile {

    // OBJETO
    @Autowired
    private IDaoProfile daoProfile;

    // CONSTANTES

    // mensagem de erro se o Profile for null;
    public final static String USUARIO_NULL = "Usuário null";

    // mensagem de erro se o Profile já existir;
    public final static String USUARIO_EXISTE = "O Usuário já existe,";

    // mensagem de erro se o Profile não existir no banco;
    public final static String USUARIO_NAO_EXISTE = "O Usuário não existe na base de dados";

    // mensagem de erro se o Profile for inválido;
    public final static String USUARIO_INVALIDO = "Usuário inválido";

    @Override
    public Profile save(Profile profile) {
        if (profile == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        return daoProfile.save(profile);
    }

    @Override
    public void delete(Profile profile) {
        if (profile == null) {
            throw new BusinessException(USUARIO_NULL);
        }

        this.daoProfile.delete(profile);
    }

    @Override
    public List<Profile> getAll() {
        return (List<Profile>) this.daoProfile.findAll();
    }

    @Override
    public Profile findById(Long id) {
        Optional<Profile> profile = daoProfile.findById(id);
        return profile.isPresent() ? profile.get() : null;
    }

    @Override
    public List<Profile> getLookup(String term) {
        if (term == null || term == "") {
            return new ArrayList<Profile>(getAll().subList(0, 3));
        }

        List<Profile> lookup = new ArrayList<>();
        for (Profile p : getAll()) {
            if (p.getName().toLowerCase().contains(term.toLowerCase()) ||
                    term.toLowerCase().contains(p.getName().toLowerCase())) {
                lookup.add(p);
            }
        }

        return lookup;
    }

}
