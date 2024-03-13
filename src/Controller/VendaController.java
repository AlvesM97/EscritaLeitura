package Controller;

import DAO.VendaDAO;
import Model.ClienteModel;
import Model.ProdutoModel;
import Model.VendaModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VendaController {

    private VendaDAO vendaDAO;
    private ArrayList<VendaModel> listaVenda;
    private ArquivoBinarioController arquivoBinarioController;
    private ArquivoTextoController arquivoTextoController;
    private String formatoArquivo;

    public VendaController(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
        listaVenda = new ArrayList<>();
        arquivoBinarioController = new ArquivoBinarioController();
        arquivoTextoController = new ArquivoTextoController();

        if ("banco".equals(formatoArquivo)) {
            this.vendaDAO = new VendaDAO();
        }
    }

    public VendaController() {
        this.formatoArquivo = "texto";
        listaVenda = new ArrayList<>();
        arquivoBinarioController = new ArquivoBinarioController();
        arquivoTextoController = new ArquivoTextoController();
    }

    public void realizarVenda(ArrayList<ClienteModel> listaClientes, ArrayList<ProdutoModel> listaProdutos, int idCliente, int idProduto, int quantidade) {
        ClienteModel cliente = buscarClientePorID(listaClientes, idCliente);
        ProdutoModel produto = buscarProdutoPorID(listaProdutos, idProduto);

        if (cliente != null && produto != null) {
            boolean clienteJaComprou = verificarClienteComprouProduto(idCliente, idProduto);

            if (clienteJaComprou) {
                JOptionPane.showMessageDialog(null, "O cliente já comprou este produto.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                float valorUnitario = Float.parseFloat(produto.getValor());
                float valorTotalFloat = valorUnitario * quantidade;
                String valorTotalString = String.format("%.2f", valorTotalFloat);
                VendaModel venda = new VendaModel(idProduto, idCliente, quantidade, valorTotalString);
                listaVenda.add(venda);

                if ("binario".equals(formatoArquivo)) {
                    arquivoBinarioController.salvarVendasEmArquivo(listaVenda, "vendas.dat");
                } else if ("texto".equals(formatoArquivo)) {
                    arquivoTextoController.salvarVendasEmArquivo(listaVenda, "vendas.txt");
                } else if ("banco".equals(formatoArquivo)) {
                    vendaDAO.realizarVenda(venda);
                } else {
                    throw new IllegalArgumentException("Formato de arquivo inválido");
                }
            }
        } else {
            System.out.println("Cliente ou produto não encontrado.");
        }
    }

    public ArrayList<VendaModel> carregarVendas() {
        if ("binario".equals(formatoArquivo)) {
            listaVenda = arquivoBinarioController.carregarVendasDeArquivo("vendas.dat");
        } else if ("texto".equals(formatoArquivo)) {
            listaVenda = arquivoTextoController.carregarVendasDeArquivo("vendas.txt");
        } else if ("banco".equals(formatoArquivo)) {
            listaVenda = vendaDAO.carregarVendas();
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido");
        }

        return listaVenda;
    }

    public void editarVenda(int idProduto, int idCliente, int novaQuantidade, String novoValorTotal) {
        for (VendaModel venda : listaVenda) {
            if (venda.getIdProduto() == idProduto && venda.getIdCliente() == idCliente) {
                venda.setQuantidade(novaQuantidade);
                venda.setValorTotal(novoValorTotal);
                if ("binario".equals(formatoArquivo)) {
                    arquivoBinarioController.salvarVendasEmArquivo(listaVenda, "vendas.dat");
                } else if ("texto".equals(formatoArquivo)) {
                    arquivoTextoController.salvarVendasEmArquivo(listaVenda, "vendas.txt");
                } else if ("banco".equals(formatoArquivo)) {
                    vendaDAO.atualizarVenda(venda);
                } else {
                    throw new IllegalArgumentException("Formato de arquivo inválido");
                }
                return;
            }
        }
        System.out.println("Venda não encontrada.");
    }

    public void excluirVenda(int idProduto, int idCliente) {
        for (VendaModel venda : listaVenda) {
            if (venda.getIdProduto() == idProduto && venda.getIdCliente() == idCliente) {
                listaVenda.remove(venda);
                if ("binario".equals(formatoArquivo)) {
                    arquivoBinarioController.salvarVendasEmArquivo(listaVenda, "vendas.dat");
                } else if ("texto".equals(formatoArquivo)) {
                    arquivoTextoController.salvarVendasEmArquivo(listaVenda, "vendas.txt");
                } else if ("banco".equals(formatoArquivo)) {
                    vendaDAO.excluirVenda(idProduto, idCliente);
                    listaVenda = vendaDAO.carregarVendas();
                } else {
                    throw new IllegalArgumentException("Formato de arquivo inválido");
                }
                return;
            }
        }
        System.out.println("Venda não encontrada.");
    }

    private boolean verificarClienteComprouProduto(int idCliente, int idProduto) {
        for (VendaModel venda : listaVenda) {
            if (venda.getIdCliente() == idCliente && venda.getIdProduto() == idProduto) {
                return true;
            }
        }
        return false;
    }

    public ClienteModel buscarClientePorID(ArrayList<ClienteModel> listaClientes, int idCliente) {
        for (ClienteModel cliente : listaClientes) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public ProdutoModel buscarProdutoPorID(ArrayList<ProdutoModel> listaProdutos, int idProduto) {
        for (ProdutoModel produto : listaProdutos) {
            if (produto.getId() == idProduto) {
                return produto;
            }
        }
        return null;
    }

    public String calcularValorTotal(String valorUnitario, int quantidade) {
        float valorUnitarioFloat = Float.parseFloat(valorUnitario);
        float valorTotalFloat = valorUnitarioFloat * quantidade;
        String valorTotalString = String.format("%.2f", valorTotalFloat);
        return valorTotalString;
    }

    public VendaModel buscarVendaPorIds(int idProduto, int idCliente) {
        if ("binario".equals(formatoArquivo)) {
            ArrayList<VendaModel> vendas = arquivoBinarioController.carregarVendasDeArquivo("vendas.dat");
            for (VendaModel venda : vendas) {
                if (venda.getIdProduto() == idProduto && venda.getIdCliente() == idCliente) {
                    return venda;
                }
            }
        } else if ("texto".equals(formatoArquivo)) {
            ArrayList<VendaModel> vendas = arquivoTextoController.carregarVendasDeArquivo("vendas.txt");
            for (VendaModel venda : vendas) {
                if (venda.getIdProduto() == idProduto && venda.getIdCliente() == idCliente) {
                    return venda;
                }
            }
        } else if ("banco".equals(formatoArquivo)) {
            VendaModel venda = vendaDAO.buscarVendaPorIds(idProduto, idCliente);
            return venda;
        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido");
        }
        return null;
    }
}
