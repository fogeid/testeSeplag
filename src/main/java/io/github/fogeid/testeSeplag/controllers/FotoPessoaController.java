package io.github.fogeid.testeSeplag.controllers;

import io.github.fogeid.testeSeplag.entities.FotoPessoa;
import io.github.fogeid.testeSeplag.services.FotoPessoaService;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/fotos-pessoa")
public class FotoPessoaController {
    private static final Logger log = LoggerFactory.getLogger(FotoPessoaController.class);

    @Autowired
    private FotoPessoaService fotoPessoaService;

    @PostMapping(value = "/upload/{pesId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FotoPessoa> uploadFoto(
            @Parameter(description = "ID da pessoa associada à foto", required = true)
            @PathVariable Long pesId,
            @Parameter(description = "Arquivo da foto a ser enviado", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {
        FotoPessoa fotoPessoa = fotoPessoaService.uploadFoto(pesId, file);
        return ResponseEntity.ok(fotoPessoa);
    }

    @GetMapping("/download/{fpId}")
    public ResponseEntity<InputStreamResource> downloadFoto(
            @Parameter(description = "ID da foto a ser baixada", required = true)
            @PathVariable Long fpId) throws Exception {
        InputStream inputStream = fotoPessoaService.downloadFoto(fpId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"foto-" + fpId + ".jpg\"")
                .body(new InputStreamResource(inputStream));
    }

    @GetMapping("/{pesId}/{fpId}/url")
    public ResponseEntity<String> getFotoUrl(@PathVariable Long pesId, @PathVariable Long fpId) {
        try {
            String url = fotoPessoaService.getFotoUrl(pesId, fpId);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            log.error("Erro ao gerar URL temporária para fotoId {} da pessoaId {}: {}", fpId, pesId, e.getMessage());
            return ResponseEntity.status(500).body("Erro ao gerar URL temporária: " + e.getMessage());
        }
    }
}