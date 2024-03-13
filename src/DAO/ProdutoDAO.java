package DAO;

import Controller.JDBCUtil;
import Model.ProdutoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {
        try {
            this.connection = JDBCUtil.getConnection();
        } catch (SQLException error) {
            System.out.println("Falha na conexao, comando sql = " + error);
        }
    }

    public ArrayList<ProdutoModel> carregarProdutos() {
        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();

        try {
            PreparedStatement pstdados = connection.prepareStatement("SELECT * FROM produto;");
            ResultSet rsdados = pstdados.executeQuery();

            while (rsdados.next()) {
                int id = rsdados.getInt("idProduto");
                String nome = rsdados.getString("nome");
                String valor = rsdados.getString("valor");
                String fornecedor = rsdados.getString("fornecedor");

                ProdutoModel produto = new ProdutoModel(id, nome, valor, fornecedor);
                listaProdutos.add(produto);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }

        return listaProdutos;
    }

    public boolean inserirProduto(ProdutoModel produto) {
        try {
            String query = "INSERT INTO produto (idProduto, nome, valor, fornecedor) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, produto.getId());
                stmt.setString(2, produto.getNome());
                stmt.setString(3, produto.getValor());
                stmt.setString(4, produto.getFornecedor());

                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao inserir produto = " + erro);
            return false;
        }
    }

    public boolean atualizarProduto(ProdutoModel produto) {
        try {
            String query = "UPDATE produto SET nome=?, valor=?, fornecedor=? WHERE idProduto=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, produto.getNome());
                stmt.setString(2, produto.getValor());
                stmt.setString(3, produto.getFornecedor());
                stmt.setInt(4, produto.getId());

                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar produto = " + erro);
            return false;
        }
    }

    public boolean excluirProduto(int idProduto) {
        try {
            String query = "DELETE FROM produto WHERE idProduto=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idProduto);

                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir produto = " + erro);
            return false;
        }
    }

    public ProdutoModel buscarProdutoPorId(int idProduto) {
        try {
            String query = "SELECT * FROM produto WHERE idProduto=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idProduto);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String valor = rs.getString("valor");
                    String fornecedor = rs.getString("fornecedor");

                    return new ProdutoModel(idProduto, nome, valor, fornecedor);
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar produto por ID = " + erro);
        }
        return null;
    }
}
