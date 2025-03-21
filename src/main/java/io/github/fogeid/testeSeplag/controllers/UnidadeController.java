package io.github.fogeid.testeSeplag.controllers;

import io.github.fogeid.testeSeplag.dto.cidade.CidadeDTO;
import io.github.fogeid.testeSeplag.dto.cidade.UnidadeDTO;
import io.github.fogeid.testeSeplag.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {
    @Autowired
    private UnidadeService unidadeService;

    @PostMapping
    public ResponseEntity<UnidadeDTO> insert(@RequestBody @Validated UnidadeDTO dto) {
        UnidadeDTO newDto = unidadeService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getUnidId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDTO> findById(@PathVariable Long id) {
        UnidadeDTO dto = unidadeService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<UnidadeDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "unidNome") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<UnidadeDTO> list = unidadeService.findAllPaged(pageRequest);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeDTO> update(@PathVariable Long id, @RequestBody @Validated UnidadeDTO dto) {
        UnidadeDTO newDto = unidadeService.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }
}
