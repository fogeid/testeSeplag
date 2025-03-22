package io.github.fogeid.testeSeplag.controllers;

import io.github.fogeid.testeSeplag.dto.cidade.CidadeDTO;
import io.github.fogeid.testeSeplag.dto.pessoa.PessoaDTO;
import io.github.fogeid.testeSeplag.dto.unidade.UnidadeDTO;
import io.github.fogeid.testeSeplag.repositories.PessoaRepository;
import io.github.fogeid.testeSeplag.services.PessoaService;
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
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDTO> insert(@RequestBody @Validated PessoaDTO dto) {
        PessoaDTO newDto = pessoaService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getPesId())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        PessoaDTO dto = pessoaService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "pesNome") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<PessoaDTO> list = pessoaService.findAllPaged(pageRequest);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody @Validated PessoaDTO dto) {
        PessoaDTO newDto = pessoaService.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }
}
