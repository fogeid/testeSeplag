package io.github.fogeid.testeSeplag.repositories;

import io.github.fogeid.testeSeplag.entities.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {
}
