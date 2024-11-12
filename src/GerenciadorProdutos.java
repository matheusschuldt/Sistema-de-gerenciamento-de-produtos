package com.loja.gerenciador;

import com.loja.modelo.produto;
import com.loja.exception.ProdutoException;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {
    private List<produto> produtos;
    private int proximoId;

    public GerenciadorProdutos() {
        this.produtos = new ArrayList<>();
        this.proximoId = 1;
    }

    public void criar(produto produto) {
        if (produto.getNome().isEmpty() || produto.getPreco() <= 0 || produto.getQuantidadeEstoque() < 0 || produto.getCategoria().isEmpty()) {
            throw new ProdutoException("Dados inválidos para cadastro.");
        }
        produto.setId(proximoId++);
        produtos.add(produto);
    }

    public produto buscarPorId(int id) {
        return produtos.stream().filter(produto -> produto.getId() == id).findFirst().orElse(null);
    }

    public List<produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public boolean atualizar(produto produto) {
        com.loja.modelo.produto produtoExistente = buscarPorId(produto.getId());
        if (produtoExistente != null) {
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setPreco(produto.getPreco());
            produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            produtoExistente.setCategoria(produto.getCategoria());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        produto produto = buscarPorId(id);
        if (produto != null) {
            return produtos.remove(produto);
        }
        return false;
    }

    public List<produto> buscarPorNome(String nome) {
        return null;
    }

    public List<produto> buscarPorCategoria(String categoria) {
        return null;
    }
}