package io.github.fogeid.testeSeplag.repositories;

import io.github.fogeid.testeSeplag.entities.FotoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {
    Optional<FotoPessoa> findByFpIdAndPessoa_PesId(Long fpId, Long pesId);
}
