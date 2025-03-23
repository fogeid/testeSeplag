package io.github.fogeid.testeSeplag.controllers;

import io.github.fogeid.testeSeplag.dto.servidorEfetivo.ServidorEfetivoLotadoDTO;
import io.github.fogeid.testeSeplag.services.ServidorEfetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servidores-efetivos")
public class ServidorEfetivoController {
    @Autowired
    private ServidorEfetivoService servidorEfetivoService;

    @GetMapping("/lotados/unidade/{unidId}")
    public ResponseEntity<Page<ServidorEfetivoLotadoDTO>> findServidoresEfetivosByUnidade(
            @PathVariable Long unidId,
            @PageableDefault(page = 0, size = 10, sort = "pesNome") Pageable pageable) {
        Page<ServidorEfetivoLotadoDTO> servidores = servidorEfetivoService.findServidoresEfetivosByUnidade(unidId, pageable);
        return ResponseEntity.ok(servidores);
    }
}
