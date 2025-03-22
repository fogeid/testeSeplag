package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.dto.unidade.UnidadeDTO;
import io.github.fogeid.testeSeplag.entities.Endereco;
import io.github.fogeid.testeSeplag.entities.Unidade;
import io.github.fogeid.testeSeplag.repositories.EnderecoRepository;
import io.github.fogeid.testeSeplag.repositories.UnidadeRepository;
import io.github.fogeid.testeSeplag.services.exceptions.DatabaseException;
import io.github.fogeid.testeSeplag.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UnidadeService {
    private static final Logger logger = LoggerFactory.getLogger(UnidadeService.class);

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public UnidadeDTO insert(UnidadeDTO dto) {
        Unidade unidade = new Unidade();
        copyDtoToEntity(dto, unidade);
        unidade = unidadeRepository.save(unidade);
        return new UnidadeDTO(unidade);
    }

    @Transactional(readOnly = true)
    public UnidadeDTO findById(Long id) {
        Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);
        Unidade unidade = unidadeOptional.orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrada no ID: " + id));
        return new UnidadeDTO(unidade);
    }

    @Transactional(readOnly = true)
    public Page<UnidadeDTO> findAllPaged(PageRequest pageRequest) {
        Page<Unidade> list = unidadeRepository.findAll(pageRequest);
        return list.map(UnidadeDTO::new);
    }

    @Transactional
    public UnidadeDTO update(Long id, UnidadeDTO dto) {
        try {
            Unidade unidade = unidadeRepository.getReferenceById(id);
            copyDtoToEntity(dto, unidade);
            unidade = unidadeRepository.save(unidade);
            return new UnidadeDTO(unidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Unidade não encontrada no ID: " + id);
        }
    }

    @Transactional
    public void addEndereco(Long unidadeId, Long enderecoId) {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrada com ID: " + unidadeId));
        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + enderecoId));
        unidade.getEnderecos().add(endereco);
        unidadeRepository.save(unidade);
    }

    @Transactional
    public void removeEndereco(Long unidadeId, Long enderecoId) {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrada com ID: " + unidadeId));
        unidade.getEnderecos().removeIf(endereco -> endereco.getEndId().equals(enderecoId));
        unidadeRepository.save(unidade);
    }

    public void delete(Long id) {
        try {
            unidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Unidade não encontrada com ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }

    private void copyDtoToEntity(UnidadeDTO dto, Unidade unidade) {
        unidade.setUnidNome(dto.getUnidNome());
        unidade.setUnidSigla(dto.getUnidSigla());

        unidade.getEnderecos().clear();
        if (dto.getEnderecoIds() != null && !dto.getEnderecoIds().isEmpty()) {
            for (Long enderecoId : dto.getEnderecoIds()) {
                Endereco endereco = enderecoRepository.findById(enderecoId)
                        .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + enderecoId));
                unidade.getEnderecos().add(endereco);
            }
        }
    }
}