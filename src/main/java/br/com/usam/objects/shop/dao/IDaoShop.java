package br.com.usam.objects.shop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usam.objects.shop.model.Shop;

@Repository
public interface IDaoShop extends JpaRepository<Shop, Long> {

    public abstract Optional<Shop> findById(Long id);

    public abstract List<Shop> findAll();
}
