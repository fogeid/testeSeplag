package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.dto.servidorEfetivo.ServidorEfetivoLotadoDTO;
import io.github.fogeid.testeSeplag.entities.*;
import io.github.fogeid.testeSeplag.repositories.ServidorEfetivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServidorEfetivoService {
    private static final Logger logger = LoggerFactory.getLogger(ServidorEfetivoService.class);
    @Autowired
    private ServidorEfetivoRepository servidorEfetivoRepository;

    @Transactional(readOnly = true)
    public Page<ServidorEfetivoLotadoDTO> findServidoresEfetivosByUnidade(Long unidId, Pageable pageable) {
        Page<Object[]> pageResults = servidorEfetivoRepository.findServidoresEfetivosByUnidade(unidId, pageable);

        List<ServidorEfetivoLotadoDTO> dto = pageResults.getContent().stream().map(result -> {
            Pessoa pessoa = (Pessoa) result[0];
            ServidorEfetivo servidorEfetivo = (ServidorEfetivo) result[1];
            Unidade unidade = (Unidade) result[3];
            FotoPessoa fotoPessoa = (FotoPessoa) result[4];

            Integer idade = Period.between(pessoa.getPesDataNascimento(), LocalDate.now()).getYears();

            String fotografia = (fotoPessoa != null) ? fotoPessoa.getFpBucket() : null;

            return new ServidorEfetivoLotadoDTO(
                    pessoa.getPesNome(),
                    idade,
                    unidade.getUnidNome(),
                    fotografia
            );
        }).collect(Collectors.toList());

        return new PageImpl<>(dto, pageable, pageResults.getTotalElements());
    }
}
