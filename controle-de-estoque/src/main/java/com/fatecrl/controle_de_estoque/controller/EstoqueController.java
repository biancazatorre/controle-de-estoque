package com.fatecrl.controle_de_estoque.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.controle_de_estoque.model.ControleDeEstoque;
import com.fatecrl.controle_de_estoque.service.EstoqueService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/ControleDeEstoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping
    public ResponseEntity<List<ControleDeEstoque>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControleDeEstoque> getById(@PathVariable("id") Long id) {
        ControleDeEstoque ControleDeEstoque = service.getById(id);
        if (ControleDeEstoque != null) {
            return ResponseEntity.ok(ControleDeEstoque);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ControleDeEstoque> getByNome(@PathVariable("nome") String nome) {
        ControleDeEstoque ControleDeEstoque = service.getByNome(nome);
        if (ControleDeEstoque != null) {
            return ResponseEntity.ok(ControleDeEstoque);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ControleDeEstoque> create(@RequestBody ControleDeEstoque ControleDeEstoque) {
        service.create(ControleDeEstoque);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ControleDeEstoque.getId())
                .toUri();
        return ResponseEntity.created(location).body(ControleDeEstoque);
    }

    @PutMapping
    public ResponseEntity<ControleDeEstoque> update(@RequestBody ControleDeEstoque ControleDeEstoque) {
        if (service.getById(ControleDeEstoque.getId()) == null) {
            return ResponseEntity.notFound().build();
        }
        if (service.update(ControleDeEstoque)) {
            return ResponseEntity.ok(ControleDeEstoque);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
