package com.fatecrl.controle_de_estoque.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatecrl.controle_de_estoque.model.ControleDeEstoque;

@Service
public class EstoqueService {
    private static List <ControleDeEstoque> listaDeProdutos = new ArrayList<>();

    private void estoqueFake(){
        ControleDeEstoque estoqueFake = new ControleDeEstoque();
        estoqueFake.setId(1l);
        estoqueFake.setNome("Roupa");
        estoqueFake.setQuantidade(3);
        estoqueFake.setPreco(150.99);
        estoqueFake.setDescricao("Blusas e Camisas");
        listaDeProdutos.add(estoqueFake);
    }

    public EstoqueService(){
        estoqueFake();
    }

    public List<ControleDeEstoque> getAll(){
        return listaDeProdutos;
    }

    public ControleDeEstoque getById(Long id) {
        return listaDeProdutos.stream()
                .filter(r -> r.getId().equals(id))  // Filtro por ID
                .findFirst()
                .orElse(null);
    }
    
    public ControleDeEstoque getByNome(String nome) {
        return listaDeProdutos.stream()
                .filter(r -> r.getNome().equals(nome))  // Filtro por Nome
                .findFirst()
                .orElse(null);
    }
    

    public ControleDeEstoque get(ControleDeEstoque controledeestoque) {
        return this.getById(controledeestoque.getId());
    }
    

    public Boolean delete(Long id){
        ControleDeEstoque ControleDeEstoque = this.getById(id);
        if (ControleDeEstoque != null){
            listaDeProdutos.remove(ControleDeEstoque);
            return true;
        }
        return false;
    }

    public ControleDeEstoque create(ControleDeEstoque ControleDeEstoque){
        listaDeProdutos.add(ControleDeEstoque);
        return ControleDeEstoque;
    }

    public Boolean update(ControleDeEstoque ControleDeEstoqueParam) {
        //Receita _receita = this.getById(receita.getId());
        ControleDeEstoque _ControleDeEstoque = this.get(ControleDeEstoqueParam);
        if (_ControleDeEstoque != null){
            if (ControleDeEstoqueParam.getId() != null) {
                _ControleDeEstoque.setId(ControleDeEstoqueParam.getId());
            }
            if (ControleDeEstoqueParam.getNome() != null && !ControleDeEstoqueParam.getNome().isEmpty()) {
                _ControleDeEstoque.setNome(ControleDeEstoqueParam.getNome());
            }
            if (ControleDeEstoqueParam.getDescricao() != null && !ControleDeEstoqueParam.getDescricao().isEmpty()) {
                _ControleDeEstoque.setDescricao(ControleDeEstoqueParam.getDescricao());
            }
            if (ControleDeEstoqueParam.getPreco() > 0) {
                _ControleDeEstoque.setPreco(ControleDeEstoqueParam.getPreco());
            }
            if (ControleDeEstoqueParam.getQuantidade() > 0) {
                _ControleDeEstoque.setQuantidade(ControleDeEstoqueParam.getQuantidade());
            }
            return true;
        }
        return false;        
    }
    
}

