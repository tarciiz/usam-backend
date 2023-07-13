/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.usam.objects.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.repository.BaseRepository;
import br.com.usam.objects.user.model.User;

/**
 *
 * @author vitor
 */
@Service
public interface IServiceUser extends BaseRepository<User> {
    public List<User> getLookup(String term);

    public User findByLoginAndPassword(String login, String password) throws Exception;

}
