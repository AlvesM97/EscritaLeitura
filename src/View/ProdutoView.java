package View;

import Controller.ClienteController;
import Controller.ProdutoController;
import Controller.VendaController;
import Model.ClienteModel;
import Model.ProdutoModel;
import Model.VendaModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author froos
 */
public class ProdutoView extends javax.swing.JFrame {

    ClienteController clienteController = new ClienteController();
    ProdutoController produtoController = new ProdutoController();
    VendaController vendaController = new VendaController();

    ArrayList<ClienteModel> listaCliente = new ArrayList<>();
    ArrayList<ProdutoModel> listaProduto = new ArrayList<>();
    ArrayList<VendaModel> listaVenda = new ArrayList<>();

    public ProdutoView() {
        initComponents();

        String[] options = {"Arquivo Binário", "Arquivo de Texto", "Banco de Dados"};
        int choice = JOptionPane.showOptionDialog(this, "Escolha o tipo de arquivo a ser utilizado:", "Escolha de Tipo de Arquivo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            clienteController = new ClienteController("binario");
            produtoController = new ProdutoController("binario");
            vendaController = new VendaController("binario");

        } else if (choice == 1) {
            clienteController = new ClienteController("texto");
            produtoController = new ProdutoController("texto");
            vendaController = new VendaController("texto");
        } else if (choice == 2) {
            clienteController = new ClienteController("banco");
            produtoController = new ProdutoController("banco");
            vendaController = new VendaController("banco");
        } else {
            System.exit(0);
        }

        listaCliente = clienteController.carregarClientes();
        listaProduto = produtoController.carregarProdutos();
        listaVenda = vendaController.carregarVendas();

        adicionarDadosTabela(0);
        adicionarDadosTabela(1);
        adicionarDadosTabela(2);

        jSpinnerQuantidade.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int quantidade = (int) jSpinnerQuantidade.getValue();

                int idProduto = Integer.parseInt(jTextFieldIdProdutoVenda.getText()); // Substitua pelo campo correto

                ProdutoModel produto = vendaController.buscarProdutoPorID(listaProduto, idProduto);

                if (produto != null) {
                    String valorUnitario = produto.getValor();

                    String valorTotal = vendaController.calcularValorTotal(valorUnitario, quantidade);

                    jTextFieldValorTotal.setText(valorTotal);
                } else {
                    jTextFieldValorTotal.setText("Produto não encontrado");
                }
            }
        });

        jTableCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTableCliente.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) jTableCliente.getValueAt(selectedRow, 0);
                        String nome = (String) jTableCliente.getValueAt(selectedRow, 1);
                        String email = (String) jTableCliente.getValueAt(selectedRow, 2);
                        String telefone = (String) jTableCliente.getValueAt(selectedRow, 3);

                        jLabelIdCliente.setText(Integer.toString(id));
                        jTextFieldNomeCliente.setText(nome);
                        jTextFieldEmail.setText(email);
                        jTextFieldTelefone.setText(telefone);
                    }
                }
            }
        });

        jTableVenda.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTableVenda.getSelectedRow();
                    if (selectedRow != -1) {
                        int idCliente = (int) jTableVenda.getValueAt(selectedRow, 0);
                        int idProduto = (int) jTableVenda.getValueAt(selectedRow, 1);
                        int quantidade = (int) jTableVenda.getValueAt(selectedRow, 2);
                        String valorTotal = (String) jTableVenda.getValueAt(selectedRow, 3);

                        jTextFieldIdProdutoVenda.setText(Integer.toString(idProduto));
                        jTextFieldIdClienteVenda.setText(Integer.toString(idCliente));
                        jSpinnerQuantidade.setValue(quantidade);
                        jTextFieldValorTotal.setText(valorTotal);
                    }
                }
            }
        });

        jTableProduto.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTableProduto.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) jTableProduto.getValueAt(selectedRow, 0);
                        String nome = (String) jTableProduto.getValueAt(selectedRow, 1);
                        String valor = (String) jTableProduto.getValueAt(selectedRow, 2);
                        String fornecedor = (String) jTableProduto.getValueAt(selectedRow, 3);

                        jLabelIdProduto.setText(Integer.toString(id));
                        jTextFieldNomeProduto.setText(nome);
                        jTextFieldValorProduto.setText(valor);
                        jTextFieldFornecedor.setText(fornecedor);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabelIdCliente = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jTextFieldTelefone = new javax.swing.JTextField();
        jButtonExcluirCliente = new javax.swing.JButton();
        jButtonEditarCliente = new javax.swing.JButton();
        jButtonCriarCliente = new javax.swing.JButton();
        jButtonBuscarCliente = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVenda = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldIdProdutoVenda = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldIdClienteVenda = new javax.swing.JTextField();
        jTextFieldValorTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSpinnerQuantidade = new javax.swing.JSpinner();
        jButtonExcluirVenda = new javax.swing.JButton();
        jButtonEditarVenda = new javax.swing.JButton();
        jButtonCriarVenda = new javax.swing.JButton();
        jButtonBuscarVenda = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeProduto = new javax.swing.JTextField();
        jTextFieldFornecedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldValorProduto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabelIdProduto = new javax.swing.JLabel();
        jButtonExcluirProduto = new javax.swing.JButton();
        jButtonEditarProduto = new javax.swing.JButton();
        jButtonCriarProduto = new javax.swing.JButton();
        jButtonBuscarProduto = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setText("Email:");

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Email", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableCliente);
        if (jTableCliente.getColumnModel().getColumnCount() > 0) {
            jTableCliente.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel10.setText("Nome:");

        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });

        jLabel11.setText("Id:");

        jLabel12.setText("Telefone:");

        jButtonExcluirCliente.setText("Excluir");
        jButtonExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirClienteActionPerformed(evt);
            }
        });

        jButtonEditarCliente.setText("Editar");
        jButtonEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarClienteActionPerformed(evt);
            }
        });

        jButtonCriarCliente.setText("Criar");
        jButtonCriarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCriarClienteActionPerformed(evt);
            }
        });

        jButtonBuscarCliente.setText("Buscar");
        jButtonBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonCriarCliente)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditarCliente)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExcluirCliente)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscarCliente))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9)
                        .addComponent(jLabel12)
                        .addComponent(jTextFieldEmail)
                        .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(183, 183, 183))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabelIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(8, 8, 8)
                .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluirCliente)
                    .addComponent(jButtonEditarCliente)
                    .addComponent(jButtonCriarCliente)
                    .addComponent(jButtonBuscarCliente))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Cliente", jPanel3);

        jLabel5.setText("Id Produto:");

        jTableVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Cliente", "Id Produto", "Quantidade", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableVenda);
        if (jTableVenda.getColumnModel().getColumnCount() > 0) {
            jTableVenda.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel6.setText("Id Cliente:");

        jLabel8.setText("Valor Total:");

        jTextFieldValorTotal.setEditable(false);

        jLabel13.setText("Quantidade:");

        jButtonExcluirVenda.setText("Excluir");
        jButtonExcluirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirVendaActionPerformed(evt);
            }
        });

        jButtonEditarVenda.setText("Editar");
        jButtonEditarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarVendaActionPerformed(evt);
            }
        });

        jButtonCriarVenda.setText("Criar");
        jButtonCriarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCriarVendaActionPerformed(evt);
            }
        });

        jButtonBuscarVenda.setText("Buscar");
        jButtonBuscarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonCriarVenda)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditarVenda)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExcluirVenda)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscarVenda))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(jLabel8)
                        .addComponent(jTextFieldValorTotal)
                        .addComponent(jTextFieldIdProdutoVenda)
                        .addComponent(jTextFieldIdClienteVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addComponent(jLabel13)))
                .addGap(183, 183, 183))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(8, 8, 8)
                .addComponent(jTextFieldIdClienteVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIdProdutoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluirVenda)
                    .addComponent(jButtonEditarVenda)
                    .addComponent(jButtonCriarVenda)
                    .addComponent(jButtonBuscarVenda))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Venda", jPanel4);

        jLabel3.setText("Fornecedor:");

        jLabel2.setText("Valor:");

        jLabel1.setText("Nome:");

        jTextFieldValorProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorProdutoActionPerformed(evt);
            }
        });

        jTableProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Valor", "Fornecedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableProduto);
        if (jTableProduto.getColumnModel().getColumnCount() > 0) {
            jTableProduto.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel4.setText("Id:");

        jButtonExcluirProduto.setText("Excluir");
        jButtonExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirProdutoActionPerformed(evt);
            }
        });

        jButtonEditarProduto.setText("Editar");
        jButtonEditarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarProdutoActionPerformed(evt);
            }
        });

        jButtonCriarProduto.setText("Criar");
        jButtonCriarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCriarProdutoActionPerformed(evt);
            }
        });

        jButtonBuscarProduto.setText("Buscar");
        jButtonBuscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jTextFieldFornecedor)
                        .addComponent(jTextFieldValorProduto)
                        .addComponent(jTextFieldNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(183, 183, 183))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonCriarProduto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditarProduto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExcluirProduto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscarProduto))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(jTextFieldNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldValorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluirProduto)
                    .addComponent(jButtonEditarProduto)
                    .addComponent(jButtonCriarProduto)
                    .addComponent(jButtonBuscarProduto))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Produto", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setSize(new java.awt.Dimension(558, 476));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldValorProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorProdutoActionPerformed

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jButtonCriarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCriarClienteActionPerformed
        clienteController.criarCliente(jTextFieldNomeCliente.getText(), jTextFieldEmail.getText(), jTextFieldTelefone.getText());
        adicionarDadosTabela(0);
    }//GEN-LAST:event_jButtonCriarClienteActionPerformed

    private void jButtonEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarClienteActionPerformed
        int idClienteParaEditar;
        String novoNome, novoEmail, novoTelefone;

        try {
            idClienteParaEditar = Integer.parseInt(jLabelIdCliente.getText());
            novoNome = jTextFieldNomeCliente.getText();
            novoEmail = jTextFieldEmail.getText();
            novoTelefone = jTextFieldTelefone.getText();

            if (novoNome.isEmpty() || novoEmail.isEmpty() || novoTelefone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "ID Inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        clienteController.editarCliente(idClienteParaEditar, novoNome, novoEmail, novoTelefone);

        adicionarDadosTabela(0);
    }//GEN-LAST:event_jButtonEditarClienteActionPerformed

    private void jButtonExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirClienteActionPerformed
        int idClienteParaExcluir = Integer.parseInt(jLabelIdCliente.getText());

        clienteController.excluirCliente(idClienteParaExcluir);

        adicionarDadosTabela(0);
    }//GEN-LAST:event_jButtonExcluirClienteActionPerformed

    private void jButtonCriarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCriarProdutoActionPerformed
        produtoController.criarProduto(jTextFieldNomeProduto.getText(), jTextFieldValorProduto.getText(), jTextFieldFornecedor.getText());
        adicionarDadosTabela(2);
    }//GEN-LAST:event_jButtonCriarProdutoActionPerformed

    private void jButtonEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarProdutoActionPerformed
        int idProdutoParaEditar;
        String novoNome, novoValor, novoFornecedor;

        try {
            idProdutoParaEditar = Integer.parseInt(jLabelIdProduto.getText());
            novoNome = jTextFieldNomeProduto.getText();
            novoValor = jTextFieldValorProduto.getText();
            novoFornecedor = jTextFieldFornecedor.getText();

            if (novoNome.isEmpty() || novoValor.isEmpty() || novoFornecedor.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "ID Inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        produtoController.editarProduto(idProdutoParaEditar, novoNome, novoValor, novoFornecedor);

        adicionarDadosTabela(2);
    }//GEN-LAST:event_jButtonEditarProdutoActionPerformed

    private void jButtonExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirProdutoActionPerformed
        int idProdutoParaExcluir = Integer.parseInt(jLabelIdProduto.getText());

        produtoController.excluirProduto(idProdutoParaExcluir);

        adicionarDadosTabela(2);
    }//GEN-LAST:event_jButtonExcluirProdutoActionPerformed

    private void jButtonCriarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCriarVendaActionPerformed
        String idClienteText = jTextFieldIdClienteVenda.getText();
        String idProdutoText = jTextFieldIdProdutoVenda.getText();
        int quantidade = (int) jSpinnerQuantidade.getValue();

        if (idClienteText.isEmpty() || idProdutoText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos em Branco", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                int idCliente = Integer.parseInt(idClienteText);
                int idProduto = Integer.parseInt(idProdutoText);

                vendaController.realizarVenda(listaCliente, listaProduto, idCliente, idProduto, quantidade);
                adicionarDadosTabela(1);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID do cliente ou ID do produto inválido. Por favor, insira números válidos.", "ID Inválido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonCriarVendaActionPerformed

    private void jButtonEditarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarVendaActionPerformed
        int idProduto, idCliente, novaQuantidade;
        String novoValorTotal;

        try {
            idProduto = Integer.parseInt(jTextFieldIdProdutoVenda.getText());
            idCliente = Integer.parseInt(jTextFieldIdClienteVenda.getText());
            novaQuantidade = (int) jSpinnerQuantidade.getValue();
            novoValorTotal = jTextFieldValorTotal.getText();

            if (novoValorTotal.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha o campo 'Valor Total'.", "Campo Vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "ID Inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        vendaController.editarVenda(idProduto, idCliente, novaQuantidade, novoValorTotal);

        adicionarDadosTabela(1);
    }//GEN-LAST:event_jButtonEditarVendaActionPerformed

    private void jButtonExcluirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirVendaActionPerformed
        int idCliente = Integer.parseInt(jTextFieldIdClienteVenda.getText());
        int idProduto = Integer.parseInt(jTextFieldIdProdutoVenda.getText());

        vendaController.excluirVenda(idProduto, idCliente);

        adicionarDadosTabela(1);
    }//GEN-LAST:event_jButtonExcluirVendaActionPerformed

    private void jButtonBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarProdutoActionPerformed
        int idBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto:"));
        ProdutoModel produtoEncontrado = produtoController.buscarProdutoPorId(idBusca);

        if (produtoEncontrado != null) {
            String nome = produtoEncontrado.getNome();
            String valor = produtoEncontrado.getValor();
            String fornecedor = produtoEncontrado.getFornecedor();

            jLabelIdProduto.setText(Integer.toString(idBusca));
            jTextFieldNomeProduto.setText(nome);
            jTextFieldValorProduto.setText(valor);
            jTextFieldFornecedor.setText(fornecedor);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }//GEN-LAST:event_jButtonBuscarProdutoActionPerformed

    private void jButtonBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarClienteActionPerformed
        int idBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));
        ClienteModel clienteEncontrado = clienteController.buscarClientePorId(idBusca);
        if (clienteEncontrado != null) {
            String nome = clienteEncontrado.getNome();
            String email = clienteEncontrado.getEmail();
            String telefone = clienteEncontrado.getTelefone();

            jLabelIdCliente.setText(Integer.toString(idBusca));
            jTextFieldNomeCliente.setText(nome);
            jTextFieldEmail.setText(email);
            jTextFieldTelefone.setText(telefone);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
     }//GEN-LAST:event_jButtonBuscarClienteActionPerformed

    private void jButtonBuscarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarVendaActionPerformed
        int idClienteBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));
        int idProdutoBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto:"));

        VendaModel vendaEncontrada = vendaController.buscarVendaPorIds(idProdutoBusca, idClienteBusca);
        if (vendaEncontrada != null) {
            int quantidade = vendaEncontrada.getQuantidade();
            String valorTotal = vendaEncontrada.getValorTotal();

            jTextFieldIdClienteVenda.setText(Integer.toString(idClienteBusca));
            jTextFieldIdProdutoVenda.setText(Integer.toString(idProdutoBusca));
            jSpinnerQuantidade.setValue(quantidade);
            jTextFieldValorTotal.setText(valorTotal);
        } else {
            JOptionPane.showMessageDialog(null, "Venda não encontrada.");
        }
    }//GEN-LAST:event_jButtonBuscarVendaActionPerformed

    private void limparCamposCliente() {
        jLabelIdCliente.setText("");
        jTextFieldNomeCliente.setText("");
        jTextFieldEmail.setText("");
        jTextFieldTelefone.setText("");
    }

    private void limparCamposProduto() {
        jLabelIdProduto.setText("");
        jTextFieldNomeProduto.setText("");
        jTextFieldValorProduto.setText("");
        jTextFieldFornecedor.setText("");
    }

    private void limparCamposVenda() {
        jTextFieldIdProdutoVenda.setText("");
        jTextFieldIdClienteVenda.setText("");
        jTextFieldValorTotal.setText("");
    }

    private void adicionarDadosTabela(int tabela) {
        if (tabela == 0) {
            DefaultTableModel modelo = (DefaultTableModel) jTableCliente.getModel();
            modelo.setRowCount(0);
            for (ClienteModel cliente : listaCliente) {
                Object[] rowData = {cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone()};
                modelo.addRow(rowData);
            }
            modelo.fireTableDataChanged();
            limparCamposCliente();
        } else if (tabela == 1) {
            DefaultTableModel modelo = (DefaultTableModel) jTableVenda.getModel();
            modelo.setRowCount(0);
            for (VendaModel venda : listaVenda) {
                Object[] rowData = {venda.getIdCliente(), venda.getIdProduto(), venda.getQuantidade(), venda.getValorTotal()};
                modelo.addRow(rowData);
            }
            modelo.fireTableDataChanged();
            limparCamposVenda();
        } else {
            DefaultTableModel modelo = (DefaultTableModel) jTableProduto.getModel();
            modelo.setRowCount(0);
            for (ProdutoModel produto : listaProduto) {
                Object[] rowData = {produto.getId(), produto.getNome(), produto.getValor(), produto.getFornecedor()};
                modelo.addRow(rowData);
            }
            modelo.fireTableDataChanged();
            limparCamposProduto();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            // Define o tema FlatLaf Dark
//            FlatDarculaLaf.setup();
//        } catch (Exception ex) {
//            System.err.println("Erro ao configurar o tema FlatLaf: " + ex);
//        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdutoView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarCliente;
    private javax.swing.JButton jButtonBuscarProduto;
    private javax.swing.JButton jButtonBuscarVenda;
    private javax.swing.JButton jButtonCriarCliente;
    private javax.swing.JButton jButtonCriarProduto;
    private javax.swing.JButton jButtonCriarVenda;
    private javax.swing.JButton jButtonEditarCliente;
    private javax.swing.JButton jButtonEditarProduto;
    private javax.swing.JButton jButtonEditarVenda;
    private javax.swing.JButton jButtonExcluirCliente;
    private javax.swing.JButton jButtonExcluirProduto;
    private javax.swing.JButton jButtonExcluirVenda;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIdCliente;
    private javax.swing.JLabel jLabelIdProduto;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinnerQuantidade;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JTable jTableVenda;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFornecedor;
    private javax.swing.JTextField jTextFieldIdClienteVenda;
    private javax.swing.JTextField jTextFieldIdProdutoVenda;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldNomeProduto;
    private javax.swing.JTextField jTextFieldTelefone;
    private javax.swing.JTextField jTextFieldValorProduto;
    private javax.swing.JTextField jTextFieldValorTotal;
    // End of variables declaration//GEN-END:variables
}
