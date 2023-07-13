package br.com.usam.objects.product.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usam.objects.product.model.Product;

@Repository
public interface IDaoProduct extends JpaRepository<Product, Long> {

    public abstract Optional<Product> findById(Long id);

    public abstract List<Product> findAll();
}
