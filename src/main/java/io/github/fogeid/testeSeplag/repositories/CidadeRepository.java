package io.github.fogeid.testeSeplag.repositories;

import io.github.fogeid.testeSeplag.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
