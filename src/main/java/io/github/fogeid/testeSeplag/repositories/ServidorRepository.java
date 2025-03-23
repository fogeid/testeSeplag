package io.github.fogeid.testeSeplag.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ServidorRepository {
    Page<Object[]> findServidoresByNomeWithEnderecoFuncional(@Param("pesNome") String nomeParcial, Pageable pageable);
}
