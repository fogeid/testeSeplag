package io.github.fogeid.testeSeplag.controllers;

import io.github.fogeid.testeSeplag.entities.FotoPessoa;
import io.github.fogeid.testeSeplag.services.FotoPessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/fotos")
public class FotoPessoaController {
    @Autowired
    private FotoPessoaService fotoPessoaService;

    @PostMapping(value = "/upload/{pessoaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FotoPessoa> uploadFoto(
            @Parameter(description = "ID da pessoa associada à foto", required = true)
            @PathVariable Long pessoaId,
            @Parameter(description = "Arquivo da foto a ser enviado", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {
        FotoPessoa fotoPessoa = fotoPessoaService.uploadFoto(pessoaId, file);
        return ResponseEntity.ok(fotoPessoa);
    }

    @GetMapping("/download/{fotoId}")
    public ResponseEntity<InputStreamResource> downloadFoto(
            @Parameter(description = "ID da foto a ser baixada", required = true)
            @PathVariable Long fotoId) throws Exception {
        InputStream inputStream = fotoPessoaService.downloadFoto(fotoId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"foto-" + fotoId + ".jpg\"")
                .body(new InputStreamResource(inputStream));
    }
}