/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ClienteDAO;
import Model.ClienteModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class ClienteController {

    private ClienteDAO clienteDAO;
    private ArrayList<ClienteModel> listaCliente;
    private ArquivoBinarioController arquivoBinarioController;
    private ArquivoTextoController arquivoTextoController;
    private String formatoArquivo;

    public ClienteController(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
        listaCliente = new ArrayList<>();
        arquivoBinarioController = new ArquivoBinarioController();
        arquivoTextoController = new ArquivoTextoController();

        if ("banco".equals(formatoArquivo)) {
            this.clienteDAO = new ClienteDAO();
        }
    }

    public ClienteController() {
        this.formatoArquivo = "texto";
        listaCliente = new ArrayList<>();
        arquivoBinarioController = new ArquivoBinarioController();
        arquivoTextoController = new ArquivoTextoController();
    }

    public void criarCliente(String nome, String email, String telefone) {
        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ClienteModel cliente = new ClienteModel();
        cliente.setId(listaCliente.size() + 1);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        listaCliente.add(cliente);

        if ("binario".equals(formatoArquivo)) {
            arquivoBinarioController.salvarClientesEmArquivo(listaCliente, "clientes.dat");
        } else if ("texto".equals(formatoArquivo)) {
            arquivoTextoController.salvarClientesEmArquivo(listaCliente, "clientes.txt");
        } else if ("banco".equals(formatoArquivo)) {
            clienteDAO.inserirCliente(cliente);
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido");
        }
    }

    public ArrayList<ClienteModel> carregarClientes() {
        if ("binario".equals(formatoArquivo)) {
            listaCliente = arquivoBinarioController.carregarClientesDeArquivo("clientes.dat");
        } else if ("texto".equals(formatoArquivo)) {
            listaCliente = arquivoTextoController.carregarClientesDeArquivo("clientes.txt");
        } else if ("banco".equals(formatoArquivo)) {
            listaCliente = clienteDAO.carregarClientes();
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido");
        }

        return listaCliente;
    }

    public void excluirCliente(int idCliente) {
        int index = -1;
        for (int i = 0; i < listaCliente.size(); i++) {
            if (listaCliente.get(i).getId() == idCliente) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            listaCliente.remove(index);

            if ("binario".equals(formatoArquivo)) {
                arquivoBinarioController.salvarClientesEmArquivo(listaCliente, "clientes.dat");
            } else if ("texto".equals(formatoArquivo)) {
                arquivoTextoController.salvarClientesEmArquivo(listaCliente, "clientes.txt");
            } else if ("banco".equals(formatoArquivo)) {
                clienteDAO.excluirCliente(idCliente);
                listaCliente = clienteDAO.carregarClientes();
            } else {
                throw new IllegalArgumentException("Formato de arquivo inválido");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void editarCliente(int idCliente, String novoNome, String novoEmail, String novoTelefone) {
        if (novoNome.isEmpty() || novoEmail.isEmpty() || novoTelefone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int index = -1;
        for (int i = 0; i < listaCliente.size(); i++) {
            if (listaCliente.get(i).getId() == idCliente) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            ClienteModel cliente = listaCliente.get(index);
            cliente.setNome(novoNome);
            cliente.setEmail(novoEmail);
            cliente.setTelefone(novoTelefone);

            if ("binario".equals(formatoArquivo)) {
                arquivoBinarioController.salvarClientesEmArquivo(listaCliente, "clientes.dat");
            } else if ("texto".equals(formatoArquivo)) {
                arquivoTextoController.salvarClientesEmArquivo(listaCliente, "clientes.txt");
            } else if ("banco".equals(formatoArquivo)) {
                clienteDAO.atualizarCliente(cliente);
            } else {
                throw new IllegalArgumentException("Formato de arquivo inválido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Cliente não encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ClienteModel buscarClientePorId(int idCliente) {
        if ("binario".equals(formatoArquivo)) {
            ArrayList<ClienteModel> clientes = arquivoBinarioController.carregarClientesDeArquivo("clientes.dat");
            for (ClienteModel cliente : clientes) {
                if (cliente.getId() == idCliente) {
                    return cliente;
                }
            }
        } else if ("texto".equals(formatoArquivo)) {
            ArrayList<ClienteModel> clientes = arquivoTextoController.carregarClientesDeArquivo("clientes.txt");
            for (ClienteModel cliente : clientes) {
                if (cliente.getId() == idCliente) {
                    return cliente;
                }
            }
        } else if ("banco".equals(formatoArquivo)) {
            return clienteDAO.buscarClientePorId(idCliente);
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido");
        }
        return null;
    }
}
