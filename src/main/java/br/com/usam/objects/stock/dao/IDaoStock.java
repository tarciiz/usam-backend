package br.com.usam.objects.stock.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usam.objects.stock.model.Stock;

@Repository
public interface IDaoStock extends JpaRepository<Stock, Long> {

    public abstract Optional<Stock> findById(Long id);

    public abstract List<Stock> findAll();
}
