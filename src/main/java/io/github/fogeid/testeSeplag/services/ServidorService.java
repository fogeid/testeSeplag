package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.dto.servidor.ServidorEnderecoFuncionalDTO;
import io.github.fogeid.testeSeplag.entities.Cidade;
import io.github.fogeid.testeSeplag.entities.Endereco;
import io.github.fogeid.testeSeplag.entities.Lotacao;
import io.github.fogeid.testeSeplag.entities.Pessoa;
import io.github.fogeid.testeSeplag.entities.Unidade;
import io.github.fogeid.testeSeplag.repositories.ServidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServidorService {

    @Autowired
    private ServidorRepository servidorRepository;

    public Page<ServidorEnderecoFuncionalDTO> findServidoresByNomeWithEnderecoFuncional(String nomeParcial, Pageable pageable) {
        Page<Object[]> pageResults = servidorRepository.findServidoresByNomeWithEnderecoFuncional(nomeParcial, pageable);

        List<ServidorEnderecoFuncionalDTO> dto = pageResults.getContent().stream().map(result -> {
            Pessoa pessoa = (Pessoa) result[0];
            Lotacao lotacao = (Lotacao) result[1];
            Unidade unidade = (Unidade) result[2];
            Endereco endereco = (Endereco) result[3];
            Cidade cidade = (Cidade) result[4];

            String endLogradouro = endereco.getEndLogradouro();
            Integer endNumero = endereco.getEndNumero();
            String endBairro = endereco.getEndBairro();
            String cidNome = cidade.getCidNome();

            return new ServidorEnderecoFuncionalDTO(
                    pessoa.getPesNome(),
                    endLogradouro,
                    endNumero,
                    endBairro,
                    cidNome
            );
        }).collect(Collectors.toList());

        return new PageImpl<>(dto, pageable, pageResults.getTotalElements());
    }
}