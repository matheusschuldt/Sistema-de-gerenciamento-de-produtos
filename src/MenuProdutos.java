package com.loja.ui;

import com.loja.gerenciador.GerenciadorProdutos;
import com.loja.modelo.produto;

import java.util.List;
import java.util.Scanner;

public class MenuProdutos {
    private Scanner scanner;
    private GerenciadorProdutos gerenciador;

    public MenuProdutos() {
        this.scanner = new Scanner(System.in);
        this.gerenciador = new GerenciadorProdutos();
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Buscar Produto por ID");
            System.out.println("3. Listar Todos os Produtos");
            System.out.println("4. Atualizar Produto");
            System.out.println("5. Deletar Produto");
            System.out.println("6. Buscar por Nome");
            System.out.println("7. Buscar por Categoria");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do scanner

            switch (opcao) {
                case 1: cadastrarProduto(); break;
                case 2: buscarProduto(); break;
                case 3: listarProdutos(); break;
                case 4: atualizarProduto(); break;
                case 5: deletarProduto(); break;
                case 6: buscarPorNome(); break;
                case 7: buscarPorCategoria(); break;
                case 8: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!"); break;
            }
        } while (opcao != 8);
    }

    private void cadastrarProduto() {
        System.out.println("=== Cadastro de Produto ===");
        String nome = lerEntradaString("Digite o nome: ");
        double preco = lerEntradaDouble("Digite o preço: ");
        int quantidade = lerEntradaInteira("Digite a quantidade: ");
        String categoria = lerEntradaString("Digite a categoria: ");

        produto produto = new produto(nome, preco, quantidade, categoria);
        gerenciador.criar(produto);
        System.out.println("Produto cadastrado com sucesso! ID gerado: " + produto.getId());
    }

    private void buscarProduto() {
        int id = lerEntradaInteira("Digite o ID: ");
        produto produto = gerenciador.buscarPorId(id);
        if (produto != null) {
            System.out.println("Produto encontrado:");
            System.out.println(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void listarProdutos() {
        List<produto> produtos = gerenciador.listarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    private void atualizarProduto() {
        int id = lerEntradaInteira("Digite o ID do produto a ser atualizado: ");
        produto produto = gerenciador.buscarPorId(id);
        if (produto != null) {
            System.out.println("Dados atuais: " + produto);
            String nome = lerEntradaString("Digite o novo nome: ");
            double preco = lerEntradaDouble("Digite o novo preço: ");
            int quantidade = lerEntradaInteira("Digite a nova quantidade: ");
            String categoria = lerEntradaString("Digite a nova categoria: ");

            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setQuantidadeEstoque(quantidade);
            produto.setCategoria(categoria);

            boolean atualizado = gerenciador.atualizar(produto);
            if (atualizado) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Falha na atualização.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void deletarProduto() {
        int id = lerEntradaInteira("Digite o ID do produto a ser deletado: ");
        boolean deletado = gerenciador.deletar(id);
        if (deletado) {
            System.out.println("Produto deletado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void buscarPorNome() {
        String nome = lerEntradaString("Digite o nome do produto: ");
        List<produto> produtos = gerenciador.buscarPorNome(nome);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    private void buscarPorCategoria() {
        String categoria = lerEntradaString("Digite a categoria: ");
        List<produto> produtos = gerenciador.buscarPorCategoria(categoria);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    private String lerEntradaString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private double lerEntradaDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Digite um valor numérico.");
            scanner.next(); // Limpa a entrada inválida
        }
        return scanner.nextDouble();
    }

    private int lerEntradaInteira(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número inteiro.");
            scanner.next(); // Limpa a entrada inválida
        }
        return scanner.nextInt();
    }
}