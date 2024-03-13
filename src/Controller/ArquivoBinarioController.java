package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Model.ClienteModel;
import Model.ProdutoModel;
import Model.VendaModel;

/**
 *
 * @author froos
 */

public class ArquivoBinarioController {

    public void salvarClientesEmArquivo(ArrayList<ClienteModel> listaClientes, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaClientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ClienteModel> carregarClientesDeArquivo(String nomeArquivo) {
        ArrayList<ClienteModel> listaClientes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaClientes = (ArrayList<ClienteModel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    public void salvarProdutosEmArquivo(ArrayList<ProdutoModel> listaProdutos, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaProdutos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProdutoModel> carregarProdutosDeArquivo(String nomeArquivo) {
        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaProdutos = (ArrayList<ProdutoModel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

    public void salvarVendasEmArquivo(ArrayList<VendaModel> listaVendas, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaVendas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<VendaModel> carregarVendasDeArquivo(String nomeArquivo) {
        ArrayList<VendaModel> listaVendas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaVendas = (ArrayList<VendaModel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaVendas;
    }
}