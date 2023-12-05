package com.example.curriculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private final PessoaService pessoaService;

    
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // Criar uma nova pessoa
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.salvarOuAtualizarPessoa(pessoa);
        return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
    }

    // Listar todas as pessoas
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodasPessoas();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    // Buscar uma pessoa pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorId(id);
        return pessoa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Atualizar uma pessoa
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(id, pessoa);
        return new ResponseEntity<>(pessoaAtualizada, HttpStatus.OK);
    }

    // Deletar uma pessoa pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

