package DAO;

import Controller.JDBCUtil;
import Model.VendaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendaDAO {

    private Connection connection;

    public VendaDAO() {
        try {
            this.connection = JDBCUtil.getConnection();
        } catch (SQLException error) {
            System.out.println("Falha na conexao, comando sql = " + error);
        }
    }

    public boolean realizarVenda(VendaModel venda) {
        try {
            String query = "INSERT INTO venda (Produto_idProduto, Cliente_idCliente, quantidade, valorTotal) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, venda.getIdProduto());
                preparedStatement.setInt(2, venda.getIdCliente());
                preparedStatement.setInt(3, venda.getQuantidade());
                preparedStatement.setString(4, venda.getValorTotal());

                int linhasAfetadas = preparedStatement.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao realizar venda: " + erro);
            return false;
        }
    }

    public ArrayList<VendaModel> carregarVendas() {
        ArrayList<VendaModel> listaVendas = new ArrayList<>();
        try {
            String query = "SELECT * FROM venda";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idProduto = resultSet.getInt("Produto_idProduto");
                    int idCliente = resultSet.getInt("Cliente_idCliente");
                    int quantidade = resultSet.getInt("quantidade");
                    String valorTotal = resultSet.getString("valorTotal");

                    VendaModel venda = new VendaModel(idProduto, idCliente, quantidade, valorTotal);
                    listaVendas.add(venda);
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar vendas: " + erro);
        }

        return listaVendas;
    }

    public boolean atualizarVenda(VendaModel venda) {
        try {
            String query = "UPDATE venda SET quantidade = ?, valorTotal = ? WHERE Produto_idProduto = ? AND Cliente_idCliente = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, venda.getQuantidade());
                preparedStatement.setString(2, venda.getValorTotal());
                preparedStatement.setInt(3, venda.getIdProduto());
                preparedStatement.setInt(4, venda.getIdCliente());

                int linhasAfetadas = preparedStatement.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar venda: " + erro);
            return false;
        }
    }

    public boolean excluirVenda(int idProduto, int idCliente) {
        try {
            String query = "DELETE FROM venda WHERE Produto_idProduto = ? AND Cliente_idCliente = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idProduto);
                preparedStatement.setInt(2, idCliente);

                int linhasAfetadas = preparedStatement.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir venda: " + erro);
            return false;
        }
    }

    public VendaModel buscarVendaPorIds(int idProduto, int idCliente) {
        try {
            String query = "SELECT * FROM venda WHERE Produto_idProduto = ? AND Cliente_idCliente = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idProduto);
                preparedStatement.setInt(2, idCliente);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int quantidade = resultSet.getInt("quantidade");
                    String valorTotal = resultSet.getString("valorTotal");

                    return new VendaModel(idProduto, idCliente, quantidade, valorTotal);
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar venda: " + erro);
        }
        return null;
    }
}
