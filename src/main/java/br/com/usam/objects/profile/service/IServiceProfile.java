/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.usam.objects.profile.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.usam.infrastructure.repository.BaseRepository;
import br.com.usam.objects.profile.model.Profile;

/**
 *
 * @author vitor
 */
@Service
public interface IServiceProfile extends BaseRepository<Profile> {
    public List<Profile> getLookup(String term);

}