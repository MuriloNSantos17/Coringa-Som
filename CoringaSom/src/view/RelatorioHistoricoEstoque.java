/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.MetodosPadrao;
import dao.Conecta_Banco;
import dao.Conecta_Tabela;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import relatorios.RelatorioHistoricoEstoqueExcel;

/**
 *
 * @author Murilo Santos
 */
public class RelatorioHistoricoEstoque extends javax.swing.JInternalFrame {

    /**
     * Creates new form RelatorioEstoque
     */
    public RelatorioHistoricoEstoque() {
        initComponents();
    preencherTabela("SELECT PRD.NM_PRODUTO, DS_MOVIMENTACAO, USR.NM_USUARIO "
    + "AS NM_RESPONSAVEL, US.NM_USUARIO AS NM_USUARIO_MOVIMENTACAO,\n" +
    " DATE_FORMAT(HE.DT_MOVIMENTACAO,'%d/%m/%Y %hh:%mm:%ss') AS "
    + "DT_MOVIMENTACAO, HE.ESTOQUE_INICIAL, HE.ESTOQUE_FINAL, HE.DS_OBSERVACAO\n" +
    " FROM HISTORICO_ESTOQUE AS HE JOIN PRODUTOS AS PRD ON PRD.ID = "
    + "HE.ID_PRODUTO JOIN USUARIOS AS US ON US.ID = \n" +
    "HE.ID_USUARIO_MOVIMENTACAO JOIN USUARIOS AS USR ON USR.ID = "
    + "HE.ID_USUARIO_RESPONSAVEL");
    populaComboUsuarios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCodigoBarras = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVendas = new javax.swing.JTable();
        jComboBoxMovimentacao = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonConfirmarEntrada = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextFieldDataInicial = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextFieldDataFinal = new javax.swing.JFormattedTextField();
        jComboBoxUsuariosMovimentacao = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxUsuariosAprovador = new javax.swing.JComboBox<>();

        setClosable(true);
        setMaximizable(true);
        setTitle("Relatório de Estoque");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Coringa.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(34, 34, 34));

        jLabel1.setFont(new java.awt.Font("Old English Text MT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Relatório de Estoque");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconeCoringa.png"))); // NOI18N
        jLabel8.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addGap(20, 20, 20))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Código ou Nome do produto:");

        jTextFieldCodigoBarras.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTableVendas.setAutoCreateRowSorter(true);
        jTableVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableVendas);

        jComboBoxMovimentacao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBoxMovimentacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "CADASTRO", "ENTRADA", "SAIDA", "VENDA", "BAIXA" }));
        jComboBoxMovimentacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBoxMovimentacaoMouseEntered(evt);
            }
        });
        jComboBoxMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMovimentacaoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("MOVIMENTAÇÃO");

        jButtonPesquisar.setBackground(new java.awt.Color(68, 68, 66));
        jButtonPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonConfirmarEntrada.setBackground(new java.awt.Color(0, 168, 82));
        jButtonConfirmarEntrada.setForeground(new java.awt.Color(255, 255, 255));
        jButtonConfirmarEntrada.setText("Relatório Excel");
        jButtonConfirmarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarEntradaActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Período inicial:");

        try {
            jFormattedTextFieldDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataInicial.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Período Final:");

        try {
            jFormattedTextFieldDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataFinal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jComboBoxUsuariosMovimentacao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBoxUsuariosMovimentacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "A" }));
        jComboBoxUsuariosMovimentacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBoxUsuariosMovimentacaoMouseEntered(evt);
            }
        });
        jComboBoxUsuariosMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUsuariosMovimentacaoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Usuário Movimentação:");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Usuário Aprovador:");

        jComboBoxUsuariosAprovador.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBoxUsuariosAprovador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*", "A" }));
        jComboBoxUsuariosAprovador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBoxUsuariosAprovadorMouseEntered(evt);
            }
        });
        jComboBoxUsuariosAprovador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUsuariosAprovadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonConfirmarEntrada)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxUsuariosMovimentacao, 0, 365, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCodigoBarras)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxMovimentacao, 0, 213, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxUsuariosAprovador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jFormattedTextFieldDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jFormattedTextFieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxUsuariosMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBoxUsuariosAprovador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonConfirmarEntrada)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxMovimentacaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxMovimentacaoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMovimentacaoMouseEntered

    private void jComboBoxMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMovimentacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMovimentacaoActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
     
    MetodosPadrao metodos = new MetodosPadrao();
    
    int dataInicial = jFormattedTextFieldDataInicial.getText().trim().length();
    int dataFinal = jFormattedTextFieldDataFinal.getText().trim().length();        
    int indexUsuarioAprovador = jComboBoxUsuariosAprovador.getSelectedIndex();
    int indexUsuarioMovimentacao = jComboBoxUsuariosMovimentacao.getSelectedIndex();
    
    String cdBarras="";
    String movimentacao="";
    String dtInicial ="";
    String dtFinal="";
    String idUsuarioAprovador="";
    String idUsuarioMovimentacao="";
     String nomeProduto ="";
    if(jTextFieldCodigoBarras.getText().isEmpty())
    {
        cdBarras="null";
        nomeProduto="null";
    }
    else
    {
        cdBarras=jTextFieldCodigoBarras.getText();
      
        
        try {
            System.out.println(cdBarras.substring(0,1));
            
            int teste = Integer.parseInt(cdBarras.substring(0,1));
            nomeProduto ="null";
            
        } catch (Exception e) {
            nomeProduto=jTextFieldCodigoBarras.getText();
            cdBarras ="null";
        }
    }
    
    if(dataInicial==10)
    {
        dtInicial =  "'"+metodos.convertDataAmericana(jFormattedTextFieldDataInicial.getText())+" 00:00:00'";
    }
    else
    {
        dtInicial="null";
    }

    if(dataFinal==10)
    {
        dtFinal = "'"+metodos.convertDataAmericana(jFormattedTextFieldDataFinal.getText())+" 23:59:59'";
    }
    else
    {
        dtFinal="null";
    }
    
    if(indexUsuarioAprovador >0)
    {
        indexUsuarioAprovador--;
        
        idUsuarioAprovador = id_usuario_aprovacao[indexUsuarioAprovador]+"";
    }
    else
    {
        idUsuarioAprovador="null";
    }
    
    if(indexUsuarioMovimentacao >0)
    {
        indexUsuarioMovimentacao--;
        
        idUsuarioMovimentacao = id_usuario_movimentacao[indexUsuarioMovimentacao]+"";
    }
    else
    {
        idUsuarioMovimentacao="null";
    }
    
    if(jComboBoxMovimentacao.getSelectedIndex()<=0)
    {
        movimentacao="null";
    }
    else
    {
       movimentacao= "'"+jComboBoxMovimentacao.getSelectedItem().toString().substring(0,1)+"'";
    }
    
    preencherTabela("SELECT PRD.NM_PRODUTO, DS_MOVIMENTACAO, USR.NM_USUARIO AS NM_RESPONSAVEL, US.NM_USUARIO AS NM_USUARIO_MOVIMENTACAO,\n" +
    " DATE_FORMAT(HE.DT_MOVIMENTACAO,'%d/%m/%Y %hh:%mm:%ss') AS DT_MOVIMENTACAO, HE.ESTOQUE_INICIAL, HE.ESTOQUE_FINAL, HE.DS_OBSERVACAO\n" +
    " FROM HISTORICO_ESTOQUE AS HE JOIN PRODUTOS AS PRD ON PRD.ID = HE.ID_PRODUTO JOIN USUARIOS AS US ON US.ID = \n" +
    "HE.ID_USUARIO_MOVIMENTACAO JOIN USUARIOS AS USR ON USR.ID = HE.ID_USUARIO_RESPONSAVEL WHERE ("
    +cdBarras+" IS NULL OR PRD.CD_BARRAS="+cdBarras+")\n" +
    "AND ("+movimentacao+" IS NULL OR HE.DS_MOVIMENTACAO="+movimentacao+") AND ("+idUsuarioMovimentacao+
    " IS NULL OR US.ID="+idUsuarioMovimentacao+") AND ("+idUsuarioAprovador+" IS NULL OR USR.ID="+idUsuarioAprovador+") AND"+
    "("+dtInicial+" IS NULL  OR HE.DT_MOVIMENTACAO >= "+dtInicial+") AND ("+dtFinal+" IS NULL  OR HE.DT_MOVIMENTACAO <= "+dtFinal+")"
    + "AND (PRD.NM_PRODUTO LIKE '%"+nomeProduto+"%' OR PRD.CD_BARRAS="+cdBarras+");");
    
    
        
    
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jComboBoxUsuariosMovimentacaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxUsuariosMovimentacaoMouseEntered

    }//GEN-LAST:event_jComboBoxUsuariosMovimentacaoMouseEntered

    private void jComboBoxUsuariosMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUsuariosMovimentacaoActionPerformed

    }//GEN-LAST:event_jComboBoxUsuariosMovimentacaoActionPerformed

    private void jComboBoxUsuariosAprovadorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxUsuariosAprovadorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUsuariosAprovadorMouseEntered

    private void jComboBoxUsuariosAprovadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUsuariosAprovadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUsuariosAprovadorActionPerformed

    private void jButtonConfirmarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarEntradaActionPerformed
    MetodosPadrao metodos = new MetodosPadrao();
    
    int dataInicial = jFormattedTextFieldDataInicial.getText().trim().length();
    int dataFinal = jFormattedTextFieldDataFinal.getText().trim().length();        
    int indexUsuarioAprovador = jComboBoxUsuariosAprovador.getSelectedIndex();
    int indexUsuarioMovimentacao = jComboBoxUsuariosMovimentacao.getSelectedIndex();
    
    String cdBarras="";
    String movimentacao="";
    String dtInicial ="";
    String dtFinal="";
    String idUsuarioAprovador="";
    String idUsuarioMovimentacao="";
     String nomeProduto ="";
    if(jTextFieldCodigoBarras.getText().isEmpty())
    {
        cdBarras="null";
        nomeProduto="null";
    }
    else
    {
        cdBarras=jTextFieldCodigoBarras.getText();
      
        
        try {
            System.out.println(cdBarras.substring(0,1));
            
            int teste = Integer.parseInt(cdBarras.substring(0,1));
            nomeProduto ="null";
            
        } catch (Exception e) {
            nomeProduto=jTextFieldCodigoBarras.getText();
            cdBarras ="null";
        }
    }
    
    if(dataInicial==10)
    {
        dtInicial =  "'"+metodos.convertDataAmericana(jFormattedTextFieldDataInicial.getText())+" 00:00:00'";
    }
    else
    {
        dtInicial="null";
    }

    if(dataFinal==10)
    {
        dtFinal = "'"+metodos.convertDataAmericana(jFormattedTextFieldDataFinal.getText())+" 23:59:59'";
    }
    else
    {
        dtFinal="null";
    }
    
    if(indexUsuarioAprovador >0)
    {
        indexUsuarioAprovador--;
        
        idUsuarioAprovador = id_usuario_aprovacao[indexUsuarioAprovador]+"";
    }
    else
    {
        idUsuarioAprovador="null";
    }
    
    if(indexUsuarioMovimentacao >0)
    {
        indexUsuarioMovimentacao--;
        
        idUsuarioMovimentacao = id_usuario_movimentacao[indexUsuarioMovimentacao]+"";
    }
    else
    {
        idUsuarioMovimentacao="null";
    }
    
    if(jComboBoxMovimentacao.getSelectedIndex()<=0)
    {
        movimentacao="null";
    }
    else
    {
       movimentacao= "'"+jComboBoxMovimentacao.getSelectedItem().toString().substring(0,1)+"'";
    }
    
    
    
   
    
    String sql = "SELECT PRD.NM_PRODUTO, DS_MOVIMENTACAO, USR.NM_USUARIO AS NM_RESPONSAVEL, US.NM_USUARIO AS NM_USUARIO_MOVIMENTACAO,\n" +
    " DATE_FORMAT(HE.DT_MOVIMENTACAO,'%d/%m/%Y %hh:%mm:%ss') AS DT_MOVIMENTACAO, HE.ESTOQUE_INICIAL, HE.ESTOQUE_FINAL, HE.DS_OBSERVACAO\n" +
    " FROM HISTORICO_ESTOQUE AS HE JOIN PRODUTOS AS PRD ON PRD.ID = HE.ID_PRODUTO JOIN USUARIOS AS US ON US.ID = \n" +
    "HE.ID_USUARIO_MOVIMENTACAO JOIN USUARIOS AS USR ON USR.ID = HE.ID_USUARIO_RESPONSAVEL WHERE ("
    +cdBarras+" IS NULL OR PRD.CD_BARRAS="+cdBarras+")\n" +
    "AND ("+movimentacao+" IS NULL OR HE.DS_MOVIMENTACAO="+movimentacao+") AND ("+idUsuarioMovimentacao+
    " IS NULL OR US.ID="+idUsuarioMovimentacao+") AND ("+idUsuarioAprovador+" IS NULL OR USR.ID="+idUsuarioAprovador+") AND"+
    "("+dtInicial+" IS NULL  OR HE.DT_MOVIMENTACAO >= "+dtInicial+") AND ("+dtFinal+" IS NULL  OR HE.DT_MOVIMENTACAO <= "+dtFinal+")"
    + "AND (PRD.NM_PRODUTO LIKE '%"+nomeProduto+"%' OR PRD.CD_BARRAS="+cdBarras+");";
    
    try 
    {
        RelatorioHistoricoEstoqueExcel relatorio = new RelatorioHistoricoEstoqueExcel();
        relatorio.gerarRelatorioHistoricoEstoque(sql);
    } 
    catch (Exception e) 
    {
        JOptionPane.showMessageDialog(null,"Erro ao gerar relatório: "+e);
    }
    
    }//GEN-LAST:event_jButtonConfirmarEntradaActionPerformed

    public void preencherTabela(String SQL) 
    {               
        //System.out.println("SQL: \n\n"+SQL);
        //MUDANDO A FONTE DO CABEÇALHO
        JTableHeader cabecalho = jTableVendas.getTableHeader();
        cabecalho.setFont(new java.awt.Font("Arial", 1, 12));

        ArrayList dados = new ArrayList();

        Conecta_Banco conecta = new Conecta_Banco();
        conecta.conexao();

        String[] colunas = new String[]{"NOME DO PRODUTO","TIPO DA MOVIMENTAÇÃO",
        "USUÁRIO APROVADOR","USUÁRIO DA MOVIMENTAÇÃO","DATA DA MOVIMENTAÇÃO",
        "ESTOQUE INICIAL","ESTOQUE FINAL","OBSERVAÇÃO"};

        conecta.executaSQL(SQL);
        try {
            conecta.rs.first();
            do {
               
               String movimentacao = conecta.rs.getString("DS_MOVIMENTACAO");
               
               if(movimentacao.equals("E"))
               {
                   movimentacao = "ENTRADA";
               }
               else if(movimentacao.equals("V"))
               {
                   movimentacao = "VENDA";
               }
               else if(movimentacao.equals("B"))
               {
                   movimentacao = "BAIXA";
               }
                else if(movimentacao.equals("S"))
               {
                   movimentacao = "SAÍDA";
               }
               else
               {
                   movimentacao = "CADASTRO";
               }
               
               dados.add(new Object[]{conecta.rs.getString("NM_PRODUTO"),
               movimentacao,conecta.rs.getString("NM_RESPONSAVEL"),
               conecta.rs.getString("NM_USUARIO_MOVIMENTACAO"),
               conecta.rs.getString("DT_MOVIMENTACAO"),
               conecta.rs.getInt("ESTOQUE_INICIAL"),
               conecta.rs.getInt("ESTOQUE_FINAL"),
               conecta.rs.getString("DS_OBSERVACAO")
               });

            } while (conecta.rs.next());
        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null,"Nenhum dado Encontrado!");
            System.out.println(ex);
             
        }
        finally{
            conecta.desconecta();
        }
        
        jTableVendas.getTableHeader().setReorderingAllowed(false);
        jTableVendas.setAutoResizeMode(jTableVendas.AUTO_RESIZE_OFF);
        jTableVendas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JTableHeader header =  jTableVendas.getTableHeader();
        DefaultTableCellRenderer centralizado = (DefaultTableCellRenderer) header.getDefaultRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        Conecta_Tabela ct = new Conecta_Tabela(dados, colunas);
        jTableVendas.setModel(ct);

        jTableVendas.getColumnModel().getColumn(0).setPreferredWidth(600);
        jTableVendas.getColumnModel().getColumn(0).setResizable(true);
        
        jTableVendas.getColumnModel().getColumn(1).setPreferredWidth(180);
        jTableVendas.getColumnModel().getColumn(1).setResizable(true);
        
        jTableVendas.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTableVendas.getColumnModel().getColumn(2).setResizable(true);
        
        jTableVendas.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTableVendas.getColumnModel().getColumn(3).setResizable(true);
        
        jTableVendas.getColumnModel().getColumn(4).setPreferredWidth(200);
        jTableVendas.getColumnModel().getColumn(4).setResizable(true);
        
        jTableVendas.getColumnModel().getColumn(5).setPreferredWidth(200);
        jTableVendas.getColumnModel().getColumn(5).setResizable(true);
        
        jTableVendas.getColumnModel().getColumn(6).setPreferredWidth(200);
        jTableVendas.getColumnModel().getColumn(6).setResizable(true);
        
        jTableVendas.getColumnModel().getColumn(7).setPreferredWidth(400);
        jTableVendas.getColumnModel().getColumn(7).setResizable(true);

    }
    
     int id_usuario_movimentacao[] = new int[1000];
     int id_usuario_aprovacao[] = new int[1000];
     
    void populaComboUsuarios()
    {
        MetodosPadrao metodo = new MetodosPadrao();
        jComboBoxUsuariosMovimentacao.removeAllItems();
        jComboBoxUsuariosMovimentacao.addItem("Selecione");
        jComboBoxUsuariosAprovador.removeAllItems();
        jComboBoxUsuariosAprovador.addItem("Selecione");
        
         Conecta_Banco conecta = new Conecta_Banco();
        try 
        {
           
            conecta.conexao();
            conecta.executaSQL("SELECT ID, NM_USUARIO FROM USUARIOS WHERE DS_STATUS='A' ORDER BY NM_USUARIO;");
            int i=0;
            
            while(conecta.rs.next())                    
            {
                jComboBoxUsuariosMovimentacao.addItem(conecta.rs.getString("NM_USUARIO"));
                jComboBoxUsuariosAprovador.addItem(conecta.rs.getString("NM_USUARIO"));
                
                id_usuario_movimentacao[i] = conecta.rs.getInt("ID");
                id_usuario_aprovacao[i] = conecta.rs.getInt("ID");
                i++;
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao pesquisar Usuários: "+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
        }
        finally
        {
            conecta.desconecta();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmarEntrada;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JComboBox<String> jComboBoxMovimentacao;
    private javax.swing.JComboBox<String> jComboBoxUsuariosAprovador;
    private javax.swing.JComboBox<String> jComboBoxUsuariosMovimentacao;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataFinal;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVendas;
    private javax.swing.JTextField jTextFieldCodigoBarras;
    // End of variables declaration//GEN-END:variables
}
