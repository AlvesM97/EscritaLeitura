/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ClienteModel;
import Model.ProdutoModel;
import Model.VendaModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Gustavo
 */
public class ArquivoTextoController {

    public void salvarClientesEmArquivo(ArrayList<ClienteModel> listaClientes, String nomeArquivo) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (ClienteModel cliente : listaClientes) {
                String linha = String.format("%d;%s;%s;%s", cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
                writer.write(linha);
                writer.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ClienteModel> carregarClientesDeArquivo(String nomeArquivo) {
        ArrayList<ClienteModel> listaClientes = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    String email = partes[2];
                    String telefone = partes[3];
                    ClienteModel cliente = new ClienteModel(id, nome, email, telefone);
                    listaClientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    public void salvarProdutosEmArquivo(ArrayList<ProdutoModel> listaProdutos, String nomeArquivo) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (ProdutoModel produto : listaProdutos) {
                String linha = String.format("%d;%s;%s;%s", produto.getId(), produto.getNome(), produto.getValor(), produto.getFornecedor());
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProdutoModel> carregarProdutosDeArquivo(String nomeArquivo) {
        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    String valor = partes[2];
                    String fornecedor = partes[3];
                    ProdutoModel produto = new ProdutoModel(id, nome, valor, fornecedor);
                    listaProdutos.add(produto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

    public void salvarVendasEmArquivo(ArrayList<VendaModel> listaVendas, String nomeArquivo) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (VendaModel venda : listaVendas) {
                String linha = String.format("%d;%d;%d;%s", venda.getIdProduto(), venda.getIdCliente(), venda.getQuantidade(), venda.getValorTotal());
                writer.write(linha);
                writer.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<VendaModel> carregarVendasDeArquivo(String nomeArquivo) {
        ArrayList<VendaModel> listaVendas = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    int idProduto = Integer.parseInt(partes[0]);
                    int idCliente = Integer.parseInt(partes[1]);
                    int quantidade = Integer.parseInt(partes[2]);
                    String valorTotal = partes[3];
                    VendaModel venda = new VendaModel(idProduto, idCliente, quantidade, valorTotal);
                    listaVendas.add(venda);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaVendas;
    }

}
