package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.dto.cidade.EnderecoDTO;
import io.github.fogeid.testeSeplag.entities.Cidade;
import io.github.fogeid.testeSeplag.entities.Endereco;
import io.github.fogeid.testeSeplag.repositories.CidadeRepository;
import io.github.fogeid.testeSeplag.repositories.EnderecoRepository;
import io.github.fogeid.testeSeplag.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnderecoService {
    private static final Logger logger = LoggerFactory.getLogger(CidadeService.class);

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    public EnderecoDTO insert(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        copyDtoToEntity(dto, endereco);
        endereco = enderecoRepository.save(endereco);
        return new EnderecoDTO(endereco);
    }

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id) {
        Optional<Endereco> Endereco = enderecoRepository.findById(id);
        Endereco endereco = Endereco.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new EnderecoDTO(endereco);
    }

    @Transactional(readOnly = true)
    public Page<EnderecoDTO> findAllPaged(PageRequest pageRequest) {
        Page<Endereco> list = enderecoRepository.findAll(pageRequest);
        return list.map(EnderecoDTO::new);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        try {
            Endereco endereco = enderecoRepository.getReferenceById(id);
            copyDtoToEntity(dto, endereco);
            endereco = enderecoRepository.save(endereco);
            return new EnderecoDTO(endereco);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void copyDtoToEntity(EnderecoDTO dto, Endereco endereco) {
        endereco.setEndTipoLogradouro(dto.getEndTipoLogradouro());
        endereco.setEndLougradouro(dto.getEndLougradouro());
        endereco.setEndNumero(dto.getEndNumero());
        endereco.setEndBairro(dto.getEndBairro());

        if (dto.getCidId() != null) {
            Cidade cidade = cidadeRepository.findById(dto.getCidId()).orElseThrow(
                    () -> new IllegalArgumentException("Cidade não encontrada para o ID: " + dto.getCidId())
            );
            endereco.setCidade(cidade);
        }
    }
}
