package io.github.fogeid.testeSeplag.repositories.implement;

import io.github.fogeid.testeSeplag.repositories.ServidorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServidorRepositoryImpl implements ServidorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public Page<Object[]> findServidoresByNomeWithEnderecoFuncional(String pesNome, Pageable pageable) {
        String jpql = "SELECT p, l, u, e, c " +
                "FROM Pessoa p " +
                "LEFT JOIN ServidorEfetivo se ON p.pesId = se.pesId " +
                "LEFT JOIN ServidorTemporario st ON p.pesId = st.pesId " +
                "INNER JOIN Lotacao l ON p.pesId = l.pessoa.pesId " +
                "INNER JOIN Unidade u ON l.unidade.unidId = u.unidId " +
                "INNER JOIN PessoaEndereco pe ON p.pesId = pe.pessoa.pesId " +
                "INNER JOIN Endereco e ON pe.endereco.endId = e.endId " +
                "INNER JOIN Cidade c ON e.cidade.cidId = c.cidId " +
                "WHERE (se.pesId IS NOT NULL OR st.pesId IS NOT NULL) " +
                "AND UPPER(p.pesNome) LIKE UPPER(CONCAT('%', :pesNome, '%')) " +
                "AND (l.lotDataRemocao IS NULL OR l.lotDataRemocao > CURRENT_DATE)";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("pesNome", pesNome);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> list = query.getResultList();

        String countJpql = "SELECT COUNT(*) " +
                "FROM Pessoa p " +
                "LEFT JOIN ServidorEfetivo se ON p.pesId = se.pesId " +
                "LEFT JOIN ServidorTemporario st ON p.pesId = st.pesId " +
                "INNER JOIN Lotacao l ON p.pesId = l.pessoa.pesId " +
                "INNER JOIN Unidade u ON l.unidade.unidId = u.unidId " +
                "INNER JOIN PessoaEndereco pe ON p.pesId = pe.pessoa.pesId " +
                "INNER JOIN Endereco e ON pe.endereco.endId = e.endId " +
                "INNER JOIN Cidade c ON e.cidade.cidId = c.cidId " +
                "WHERE (se.pesId IS NOT NULL OR st.pesId IS NOT NULL) " +
                "AND UPPER(p.pesNome) LIKE UPPER(CONCAT('%', :pesNome, '%')) " +
                "AND (l.lotDataRemocao IS NULL OR l.lotDataRemocao > CURRENT_DATE)";
        Query countQuery = entityManager.createQuery(countJpql);
        countQuery.setParameter("pesNome", pesNome);
        Long total = (Long) countQuery.getSingleResult();

        return new PageImpl<>(list, pageable, total);
    }
}
