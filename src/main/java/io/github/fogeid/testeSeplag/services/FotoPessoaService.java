package io.github.fogeid.testeSeplag.services;

import io.github.fogeid.testeSeplag.entities.FotoPessoa;
import io.github.fogeid.testeSeplag.entities.Pessoa;
import io.github.fogeid.testeSeplag.repositories.FotoPessoaRepository;
import io.github.fogeid.testeSeplag.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;

@Service
public class FotoPessoaService {
    @Autowired
    private FotoPessoaRepository fotoPessoaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MinioService minioService;

    @Value("${minio.bucket}")
    private String bucketName;

    public FotoPessoa uploadFoto(Long pessoaId, MultipartFile file) throws Exception {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new Exception("Pessoa com ID " + pessoaId + " não encontrada."));

        String fileHash = minioService.uploadFile(file);

        FotoPessoa fotoPessoa = new FotoPessoa();
        fotoPessoa.setPessoa(pessoa);
        fotoPessoa.setFpData(LocalDate.now());
        fotoPessoa.setFpBucket(bucketName);
        fotoPessoa.setFpHash(fileHash);

        return fotoPessoaRepository.save(fotoPessoa);
    }

    public InputStream downloadFoto(Long fotoId) throws Exception {
        FotoPessoa fotoPessoa = fotoPessoaRepository.findById(fotoId)
                .orElseThrow(() -> new Exception("Foto com ID " + fotoId + " não encontrada."));

        return minioService.downloadFile(fotoPessoa.getFpHash());
    }
}