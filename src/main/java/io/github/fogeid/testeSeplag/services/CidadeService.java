package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.dto.cidade.CidadeDTO;
import io.github.fogeid.testeSeplag.entities.Cidade;
import io.github.fogeid.testeSeplag.repositories.CidadeRepository;
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
public class CidadeService {
    private static final Logger logger = LoggerFactory.getLogger(CidadeService.class);

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    public CidadeDTO insert(CidadeDTO dto) {
        Cidade cidade = new Cidade();
        copyDtoToEntity(dto, cidade);
        cidade = cidadeRepository.save(cidade);
        return new CidadeDTO(cidade);
    }

    @Transactional(readOnly = true)
    public CidadeDTO findById(Long id) {
        System.out.println("Id: " + id);
        Optional<Cidade> Cidade = cidadeRepository.findById(id);
        Cidade cidade = Cidade.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CidadeDTO(cidade);
    }

    @Transactional(readOnly = true)
    public Page<CidadeDTO> findAllPaged(PageRequest pageRequest) {
        Page<Cidade> list = cidadeRepository.findAll(pageRequest);
        return list.map(CidadeDTO::new);
    }

    @Transactional
    public CidadeDTO update(Long id, CidadeDTO dto) {
        try {
            Cidade cidade = cidadeRepository.getReferenceById(id);
            copyDtoToEntity(dto, cidade);
            cidade = cidadeRepository.save(cidade);
            return new CidadeDTO(cidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void copyDtoToEntity(CidadeDTO dto, Cidade cidade) {
        cidade.setCidNome(dto.getCidNome());
        cidade.setCidUf(dto.getCidUf());
    }
}
