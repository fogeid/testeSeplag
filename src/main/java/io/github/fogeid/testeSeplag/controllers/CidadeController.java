package io.github.fogeid.testeSeplag.controllers;

import io.github.fogeid.testeSeplag.dto.cidade.CidadeDTO;
import io.github.fogeid.testeSeplag.services.CidadeService;
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
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping
    public ResponseEntity<CidadeDTO> insert(@RequestBody @Validated CidadeDTO dto) {
        CidadeDTO newDto = cidadeService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getCidId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {
        CidadeDTO dto = cidadeService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<CidadeDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "cidNome") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<CidadeDTO> list = cidadeService.findAllPaged(pageRequest);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeDTO> update(@PathVariable Long id, @RequestBody @Validated CidadeDTO dto) {
        CidadeDTO newDto = cidadeService.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }
}
