package br.com.usam.objects.profile.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usam.objects.profile.model.Profile;

@Repository
public interface IDaoProfile extends JpaRepository<Profile, Long> {

    public abstract Optional<Profile> findById(Long id);

    public abstract List<Profile> findAll();
}
