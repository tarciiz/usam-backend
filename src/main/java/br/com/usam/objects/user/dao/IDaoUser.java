package br.com.usam.objects.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usam.objects.user.model.User;

@Repository
public interface IDaoUser extends JpaRepository<User, Long> {

    public abstract Optional<User> findById(Long id);

    public abstract Optional<User> findByLoginAndPassword(String login, String password);
}
