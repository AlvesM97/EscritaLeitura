package DAO;

import Controller.JDBCUtil;
import Model.ClienteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() {
        try {
            this.connection = JDBCUtil.getConnection();
        } catch (SQLException error) {
            System.out.println("Falha na conexao, comando sql = " + error);
        }
    }

    public ArrayList<ClienteModel> carregarClientes() {
        ArrayList<ClienteModel> listaClientes = new ArrayList<>();

        try {
            PreparedStatement pstdados = connection.prepareStatement("SELECT * FROM cliente;");
            ResultSet rsdados = pstdados.executeQuery();

            while (rsdados.next()) {
                int id = rsdados.getInt("idCliente");
                String nome = rsdados.getString("nome");
                String email = rsdados.getString("email");
                String telefone = rsdados.getString("telefone");

                ClienteModel cliente = new ClienteModel(id, nome, email, telefone);
                listaClientes.add(cliente);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }

        return listaClientes;
    }

    public boolean inserirCliente(ClienteModel cliente) {
        try {
            String query = "INSERT INTO cliente (idCliente, nome, email, telefone) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, cliente.getId());
                stmt.setString(2, cliente.getNome());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getTelefone());

                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir cliente = " + erro);
            return false;
        }
    }

    public boolean atualizarCliente(ClienteModel cliente) {
        try {
            String query = "UPDATE cliente SET nome=?, email=?, telefone=? WHERE idCliente=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getTelefone());
                stmt.setInt(4, cliente.getId());

                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar cliente = " + erro);
            return false;
        }
    }

    public boolean excluirCliente(int idCliente) {
        try {
            String query = "DELETE FROM cliente WHERE idCliente=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idCliente);

                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir cliente = " + erro);
            return false;
        }
    }

    public ClienteModel buscarClientePorId(int idCliente) {
        try {
            String query = "SELECT * FROM cliente WHERE idCliente=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idCliente);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String telefone = rs.getString("telefone");

                    return new ClienteModel(idCliente, nome, email, telefone);
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar cliente por ID = " + erro);
        }
        return null;
    }
}
