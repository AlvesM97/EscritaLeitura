package Controller;

import DAO.ProdutoDAO;
import Model.ProdutoModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    private ArrayList<ProdutoModel> listaProduto;
    private ArquivoBinarioController arquivoBinarioController;
    private ArquivoTextoController arquivoTextoController;
    private String formatoArquivo;

    public ProdutoController(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
        listaProduto = new ArrayList<>();
        arquivoBinarioController = new ArquivoBinarioController();
        arquivoTextoController = new ArquivoTextoController();

        if ("banco".equals(formatoArquivo)) {
            this.produtoDAO = new ProdutoDAO();
        }
    }

    public ProdutoController() {
        this.formatoArquivo = "texto";
        listaProduto = new ArrayList<>();
        arquivoBinarioController = new ArquivoBinarioController();
        arquivoTextoController = new ArquivoTextoController();
    }

    public void criarProduto(String nome, String valor, String fornecedor) {
        if (nome.isEmpty() || valor.isEmpty() || fornecedor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProdutoModel produto = new ProdutoModel();
        produto.setId(listaProduto.size() + 1);
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setFornecedor(fornecedor);

        listaProduto.add(produto);

        if ("binario".equals(formatoArquivo)) {
            arquivoBinarioController.salvarProdutosEmArquivo(listaProduto, "produtos.dat");
        } else if ("texto".equals(formatoArquivo)) {
            arquivoTextoController.salvarProdutosEmArquivo(listaProduto, "produtos.txt");
        } else if ("banco".equals(formatoArquivo)) {
            produtoDAO.inserirProduto(produto);
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido");
        }
    }

    public ArrayList<ProdutoModel> carregarProdutos() {
        if ("binario".equals(formatoArquivo)) {
            listaProduto = arquivoBinarioController.carregarProdutosDeArquivo("produtos.dat");
        } else if ("texto".equals(formatoArquivo)) {
            listaProduto = arquivoTextoController.carregarProdutosDeArquivo("produtos.txt");
        } else if ("banco".equals(formatoArquivo)) {
            listaProduto = produtoDAO.carregarProdutos();
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido");
        }

        return listaProduto;
    }

    public void excluirProduto(int idProduto) {
        int index = -1;
        for (int i = 0; i < listaProduto.size(); i++) {
            if (listaProduto.get(i).getId() == idProduto) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            listaProduto.remove(index);

            if ("binario".equals(formatoArquivo)) {
                arquivoBinarioController.salvarProdutosEmArquivo(listaProduto, "produtos.dat");
            } else if ("texto".equals(formatoArquivo)) {
                arquivoTextoController.salvarProdutosEmArquivo(listaProduto, "produtos.txt");
            } else if ("banco".equals(formatoArquivo)) {
                produtoDAO.excluirProduto(idProduto);
                listaProduto = produtoDAO.carregarProdutos();
            } else {
                throw new IllegalArgumentException("Formato de arquivo inválido");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void editarProduto(int idProduto, String novoNome, String novoValor, String novoFornecedor) {
        if (novoNome.isEmpty() || novoValor.isEmpty() || novoFornecedor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int index = -1;
        for (int i = 0; i < listaProduto.size(); i++) {
            if (listaProduto.get(i).getId() == idProduto) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            ProdutoModel produto = listaProduto.get(index);
            produto.setNome(novoNome);
            produto.setValor(novoValor);
            produto.setFornecedor(novoFornecedor);

            if ("binario".equals(formatoArquivo)) {
                arquivoBinarioController.salvarProdutosEmArquivo(listaProduto, "produtos.dat");
            } else if ("texto".equals(formatoArquivo)) {
                arquivoTextoController.salvarProdutosEmArquivo(listaProduto, "produtos.txt");
            } else if ("banco".equals(formatoArquivo)) {
                produtoDAO.atualizarProduto(produto);
            } else {
                throw new IllegalArgumentException("Formato de arquivo inválido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Produto não encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ProdutoModel buscarProdutoPorId(int idProduto) {
        if ("binario".equals(formatoArquivo)) {
            ArrayList<ProdutoModel> produtos = arquivoBinarioController.carregarProdutosDeArquivo("produtos.dat");
            for (ProdutoModel produto : produtos) {
                if (produto.getId() == idProduto) {
                    return produto;
                }
            }
        } else if ("texto".equals(formatoArquivo)) {
            ArrayList<ProdutoModel> produtos = arquivoTextoController.carregarProdutosDeArquivo("produtos.txt");
            for (ProdutoModel produto : produtos) {
                if (produto.getId() == idProduto) {
                    return produto;
                }
            }
        } else if ("banco".equals(formatoArquivo)) {
            return produtoDAO.buscarProdutoPorId(idProduto);
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido");
        }
        return null;
    }
}