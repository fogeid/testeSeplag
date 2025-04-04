package io.github.fogeid.testeSeplag.repositories;

import io.github.fogeid.testeSeplag.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
