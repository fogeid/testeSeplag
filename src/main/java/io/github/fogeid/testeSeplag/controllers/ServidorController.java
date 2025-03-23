package io.github.fogeid.testeSeplag.controllers;

import io.github.fogeid.testeSeplag.dto.servidor.ServidorEnderecoFuncionalDTO;
import io.github.fogeid.testeSeplag.services.ServidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servidores")
public class ServidorController {

    @Autowired
    private ServidorService servidorService;

    @GetMapping("/endereco-funcional")
    public ResponseEntity<Page<ServidorEnderecoFuncionalDTO>> findServidoresByNomeWithEnderecoFuncional(
            @RequestParam("nome") String pesNome,
            @PageableDefault(page = 0, size = 10, sort = "pesNome") Pageable pageable) {
        Page<ServidorEnderecoFuncionalDTO> servidores = servidorService.findServidoresByNomeWithEnderecoFuncional(pesNome, pageable);
        return ResponseEntity.ok(servidores);
    }
}