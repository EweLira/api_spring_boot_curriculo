package com.example.curriculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

     @Autowired
    private final PessoaRepository pessoaRepository;

   
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    // Salvar ou atualizar uma pessoa
    public Pessoa salvarOuAtualizarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // Buscar uma pessoa pelo ID
    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    // Listar todas as pessoas
    public List<Pessoa> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    // Deletar uma pessoa pelo ID
    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    // Atualizar uma pessoa existente
    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(pessoaAtualizada.getNome());
                    pessoa.setEmail(pessoaAtualizada.getEmail());
                    pessoa.setTelefone(pessoaAtualizada.getTelefone());
                    pessoa.setEducacao(pessoaAtualizada.getEducacao());
                    pessoa.setExperienciaProfissional(pessoaAtualizada.getExperienciaProfissional());
                    pessoa.setHabilidades(pessoaAtualizada.getHabilidades());
                    pessoa.setProjetos(pessoaAtualizada.getProjetos());
                    return pessoaRepository.save(pessoa);
                }).orElseGet(() -> {
                    pessoaAtualizada.setId(id);
                    return pessoaRepository.save(pessoaAtualizada);
                });
    }
}

