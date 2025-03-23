package io.github.fogeid.testeSeplag.repositories;

import io.github.fogeid.testeSeplag.entities.ServidorEfetivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
    @Query("SELECT p, se, l, u, f " +
            "FROM Pessoa p " +
            "INNER JOIN ServidorEfetivo se ON p.pesId = se.pesId " +
            "INNER JOIN Lotacao l ON p.pesId = l.pessoa.pesId " +
            "INNER JOIN Unidade u ON l.unidade.unidId = u.unidId " +
            "LEFT JOIN FotoPessoa f ON p.pesId = f.pessoa.pesId " +
            "WHERE l.unidade.unidId = :unidId " +
            "AND (l.lotDataRemocao IS NULL OR l.lotDataRemocao > CURRENT_DATE)")
    Page<Object[]> findServidoresEfetivosByUnidade(@Param("unidId") Long unidId, Pageable pageable);
}
