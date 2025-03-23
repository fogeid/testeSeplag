package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.dto.servidorEfetivo.ServidorEfetivoLotadoDTO;
import io.github.fogeid.testeSeplag.entities.Lotacao;
import io.github.fogeid.testeSeplag.entities.Pessoa;
import io.github.fogeid.testeSeplag.entities.ServidorEfetivo;
import io.github.fogeid.testeSeplag.entities.Unidade;
import io.github.fogeid.testeSeplag.repositories.ServidorEfetivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
            Lotacao lotacao = (Lotacao) result[2];
            Unidade unidade = (Unidade) result[3];
            return new ServidorEfetivoLotadoDTO(
                    pessoa.getPesId(),
                    pessoa.getPesNome(),
                    servidorEfetivo.getSeMatricula(),
                    unidade.getUnidId(),
                    unidade.getUnidNome(),
                    lotacao.getLotDataLotacao(),
                    lotacao.getLotDataRemocao(),
                    lotacao.getLotPortaria()
            );
        }).collect(Collectors.toList());

        return new PageImpl<>(dto, pageable, pageResults.getTotalElements());
    }
}
