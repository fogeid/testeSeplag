package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.dto.cidade.UnidadeDTO;
import io.github.fogeid.testeSeplag.entities.Unidade;
import io.github.fogeid.testeSeplag.repositories.UnidadeRepository;
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
public class UnidadeService {
    private static final Logger logger = LoggerFactory.getLogger(CidadeService.class);

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Transactional
    public UnidadeDTO insert(UnidadeDTO dto) {
        Unidade unidade = new Unidade();
        copyDtoToEntity(dto, unidade);
        unidade = unidadeRepository.save(unidade);
        return new UnidadeDTO(unidade);
    }

    @Transactional(readOnly = true)
    public UnidadeDTO findById(Long id) {
        Optional<Unidade> Unidade = unidadeRepository.findById(id);
        Unidade unidade = Unidade.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
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
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void copyDtoToEntity(UnidadeDTO dto, Unidade unidade) {
        unidade.setUnidNome(dto.getUnidNome());
        unidade.setUnidSigla(dto.getUnidSigla());
    }
}
