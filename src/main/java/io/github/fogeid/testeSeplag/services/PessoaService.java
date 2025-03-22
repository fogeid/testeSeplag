package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.dto.pessoa.PessoaDTO;
import io.github.fogeid.testeSeplag.dto.fotoPessoa.FotoPessoaDTO;
import io.github.fogeid.testeSeplag.dto.endereco.EnderecoDTO;
import io.github.fogeid.testeSeplag.dto.lotacao.LotacaoDTO;

import io.github.fogeid.testeSeplag.entities.*;
import io.github.fogeid.testeSeplag.repositories.*;
import io.github.fogeid.testeSeplag.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private FotoPessoaRepository fotoPessoaRepository;

    @Autowired
    private ServidorTemporarioRepository servidorTemporarioRepository;

    @Autowired
    private ServidorEfetivoRepository servidorEfetivoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private LotacaoRepository lotacaoRepository;

    @Transactional
    public PessoaDTO insert(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        copyDtoToEntity(dto, pessoa);
        pessoa = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoa);
    }

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        Optional<Pessoa> Pessoa = pessoaRepository.findById(id);
        Pessoa pessoa = Pessoa.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PessoaDTO(pessoa);
    }

    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAllPaged(PageRequest pageRequest) {
        Page<Pessoa> list = pessoaRepository.findAll(pageRequest);
        return list.map(PessoaDTO::new);
    }

    @Transactional
    public PessoaDTO update(Long id, PessoaDTO dto) {
        try {
            Pessoa pessoa = pessoaRepository.getReferenceById(id);
            copyDtoToEntity(dto, pessoa);
            pessoa = pessoaRepository.save(pessoa);
            return new PessoaDTO(pessoa);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void copyDtoToEntity(PessoaDTO dto, Pessoa pessoa) {
        pessoa.setPesNome(dto.getPesNome());
        pessoa.setPesDataNascimento(dto.getPesDataNascimento());
        pessoa.setPesSexo(dto.getPesSexo());
        pessoa.setPesMae(dto.getPesMae());
        pessoa.setPesPai(dto.getPesPai());

        pessoa.getFotos().clear();
        for (FotoPessoaDTO fotoDto : dto.getFotos()) {
            FotoPessoa foto = new FotoPessoa();
            foto.setPessoa(pessoa);
            foto.setFpBucket(fotoDto.getFpBucket());
            foto.setFpHash(fotoDto.getFpHash());
            pessoa.getFotos().add(foto);
        }

        if (dto.getServidorTemporario() != null) {
            ServidorTemporario servidorTemporario = pessoa.getServidorTemporario();
            if (servidorTemporario == null) {
                servidorTemporario = new ServidorTemporario();
                servidorTemporario.setPessoa(pessoa);
            }
            servidorTemporario.setStDataAdmissao(dto.getServidorTemporario().getStDataAdmissao());
            servidorTemporario.setStDataDemissao(dto.getServidorTemporario().getStDataDemissao());
            pessoa.setServidorTemporario(servidorTemporario);
        } else {
            pessoa.setServidorTemporario(null);
        }

        if (dto.getServidorEfetivo() != null) {
            ServidorEfetivo servidorEfetivo = pessoa.getServidorEfetivo();
            if (servidorEfetivo == null) {
                servidorEfetivo = new ServidorEfetivo();
                servidorEfetivo.setPessoa(pessoa);
            }
            servidorEfetivo.setSeMatricula(dto.getServidorEfetivo().getSeMatricula());
            pessoa.setServidorEfetivo(servidorEfetivo);
        } else {
            pessoa.setServidorEfetivo(null);
        }

        pessoa.getEnderecos().clear();
        for (EnderecoDTO enderecoDto : dto.getEnderecos()) {
            Endereco endereco = enderecoRepository.findById(enderecoDto.getEndId())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + enderecoDto.getEndId()));
            pessoa.getEnderecos().add(endereco);
        }

        pessoa.getLotacoes().clear();
        for (LotacaoDTO lotacaoDto : dto.getLotacoes()) {
            if (lotacaoDto.getUnidId() == null) {
                throw new IllegalArgumentException("O campo unidId é obrigatório para uma lotação.");
            }
            Lotacao lotacao = new Lotacao();
            lotacao.setPessoa(pessoa);
            lotacao.setLotDataLotacao(lotacaoDto.getLotDataLotacao());
            lotacao.setLotDataRemocao(lotacaoDto.getLotDataRemocao());
            lotacao.setLotPortaria(lotacaoDto.getLotPortaria());
            Unidade unidade = unidadeRepository.findById(lotacaoDto.getUnidId())
                    .orElseThrow(() -> new RuntimeException("Unidade não encontrada com ID: " + lotacaoDto.getUnidId()));
            lotacao.setUnidade(unidade);
            pessoa.getLotacoes().add(lotacao);
        }
    }
}
