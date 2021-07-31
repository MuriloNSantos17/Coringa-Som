/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.Conecta_Banco;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author muril
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        jLabelAlerta.setVisible(false);
        this.setIconImage(new ImageIcon(getClass().getResource("/imagens/Coringa.png")).getImage());
        this.setExtendedState(MAXIMIZED_BOTH);
       
    }
    
    String usuario ="";
    public void passaDados(String user)
    {
        usuario=user;
        controleAcesso();
        verificaEstoque();
    }
    
    void controleAcesso()
    {    
        Conecta_Banco conecta = new Conecta_Banco(); 
        try 
        {
           
            conecta.conexao();
            conecta.executaSQL("SELECT * FROM ACESSO WHERE ID="+usuario+";");
            conecta.rs.first();
            
            if(conecta.rs.getString("CAD_FORN").equals("F"))
            {
                jMenuItemCadastroFornecedores.setEnabled(false);
            }
            if(conecta.rs.getString("CAD_FUNC").equals("F"))                
            {
                jMenuItemCadastroFuncionarios.setEnabled(false);
            }
            if(conecta.rs.getString("CAD_PROD").equals("F"))                
            {
                jMenuItemCadastroProdutos.setEnabled(false);
            }
            if(conecta.rs.getString("CAD_TP_PROD").equals("F"))                
            {
                jMenuItemCadastroTipodeProduto.setEnabled(false);
            }
            if(conecta.rs.getString("EST_ENT").equals("F"))
            {
                jMenuItemEntradaEstoque.setEnabled(false);
            }
            if(conecta.rs.getString("EST_SA").equals("F"))
            {
                jMenuItemSaidaEstoque.setEnabled(false);
            }
            if(conecta.rs.getString("CONT_ACESSO").equals("F"))
            {
                jMenuControleAcesso.setVisible(false);
            }
            if(conecta.rs.getString("SOLC_VENDA").equals("F"))
            {
                jMenuItemSolicitacaoVenda.setEnabled(false);
            }
            if(conecta.rs.getString("VD_DIRETA").equals("F"))
            {
                jMenuItemVendaDireta.setEnabled(false);
            }
            if(conecta.rs.getString("APV_VENDA").equals("F"))
            {
                jMenuItemAprovaVenda.setEnabled(false);
            }
            if(conecta.rs.getString("REL_VENDA").equals("F"))
            {
                jMenuItemRelatorioVendas.setEnabled(false);
            }
            if(conecta.rs.getString("REL_ESTQ").equals("F"))
            {
                jMenuItemRelatorioEstoque.setEnabled(false);
            }
            if(conecta.rs.getString("REL_HIST_ESTQ").equals("F"))
            {
                jMenuItemRelatorioHistoricoEstoque.setEnabled(false);
            }
            
            //DESATIVA MENU PAIS
            if(jMenuItemRelatorioVendas.isEnabled()==false && 
            jMenuItemRelatorioEstoque.isEnabled()==false &&
            jMenuItemRelatorioHistoricoEstoque.isEnabled()==false)
            {
                jMenuRelatorios.setEnabled(false);
            }
            
            if(jMenuItemSolicitacaoVenda.isEnabled()==false && 
            jMenuItemVendaDireta.isEnabled()==false &&
            jMenuItemAprovaVenda.isEnabled()==false)
            {
                jMenuVenda.setEnabled(false);
            }
            
            if(jMenuItemCadastroFornecedores.isEnabled()==false && 
              jMenuItemCadastroFuncionarios.isEnabled()==false &&
              jMenuItemCadastroProdutos.isEnabled()==false &&
              jMenuItemCadastroTipodeProduto.isEnabled()==false)
            {
                jMenuCadastros.setEnabled(false);
            }
            
            if(jMenuItemSaidaEstoque.isEnabled()==false && 
            jMenuItemEntradaEstoque.isEnabled()==false)
            {
                jMenuEstoque.setEnabled(false);
            }
            
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao pesquisar Menus: "+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
        }
        finally
        {
            conecta.desconecta();
        }
    }

   
    int contador=0;
    
    void verificaEstoque()
    {
        try 
        {
            Conecta_Banco conecta = new Conecta_Banco();
            conecta.conexao();
            conecta.executaSQL("SELECT NM_PRODUTO, ESTQ.ESTOQUE FROM ESTOQUE AS \n" +
            "ESTQ JOIN PRODUTOS AS PRD ON PRD.ID = ESTQ.ID_PRODUTO WHERE \n" +
            "ESTOQUE_ABAIXO='S' AND PRD.DS_STATUS!='I'");
            int i=0;
            
            while(conecta.rs.next())
            {
                contador++;
            }
            if(contador>0)
            {
                jLabelAlerta.setVisible(true);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jPanel1 = new JPanelGradient();
        jLabelAlerta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCadastros = new javax.swing.JMenu();
        jMenuItemCadastroFornecedores = new javax.swing.JMenuItem();
        jMenuItemCadastroFuncionarios = new javax.swing.JMenuItem();
        jMenuItemCadastroProdutos = new javax.swing.JMenuItem();
        jMenuItemCadastroTipodeProduto = new javax.swing.JMenuItem();
        jMenuEstoque = new javax.swing.JMenu();
        jMenuItemEntradaEstoque = new javax.swing.JMenuItem();
        jMenuItemSaidaEstoque = new javax.swing.JMenuItem();
        jMenuControleAcesso = new javax.swing.JMenu();
        jMenuVenda = new javax.swing.JMenu();
        jMenuItemSolicitacaoVenda = new javax.swing.JMenuItem();
        jMenuItemVendaDireta = new javax.swing.JMenuItem();
        jMenuItemAprovaVenda = new javax.swing.JMenuItem();
        jMenuRelatorios = new javax.swing.JMenu();
        jMenuItemRelatorioVendas = new javax.swing.JMenuItem();
        jMenuItemRelatorioEstoque = new javax.swing.JMenuItem();
        jMenuItemRelatorioHistoricoEstoque = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coringa Som LTDA");

        jDesktopPane1.setBackground(new java.awt.Color(255, 102, 102));

        jLabelAlerta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alerta.gif"))); // NOI18N
        jLabelAlerta.setToolTipText("Estoque Negativo, clique aqui e veja quais programas");
        jLabelAlerta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlertaMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconeCoringaInicial.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelAlerta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 751, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(725, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelAlerta)
                        .addContainerGap())))
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuCadastros.setText("Cadastros");

        jMenuItemCadastroFornecedores.setText("Fornecedores");
        jMenuItemCadastroFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroFornecedoresActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadastroFornecedores);

        jMenuItemCadastroFuncionarios.setText("Funcionários");
        jMenuItemCadastroFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroFuncionariosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadastroFuncionarios);

        jMenuItemCadastroProdutos.setText("Produtos");
        jMenuItemCadastroProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroProdutosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadastroProdutos);

        jMenuItemCadastroTipodeProduto.setText("Tipo de Produto");
        jMenuItemCadastroTipodeProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroTipodeProdutoActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemCadastroTipodeProduto);

        jMenuBar1.add(jMenuCadastros);

        jMenuEstoque.setText("Estoque");

        jMenuItemEntradaEstoque.setText("Entrada");
        jMenuItemEntradaEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEntradaEstoqueActionPerformed(evt);
            }
        });
        jMenuEstoque.add(jMenuItemEntradaEstoque);

        jMenuItemSaidaEstoque.setText("Saída / Baixa");
        jMenuItemSaidaEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaidaEstoqueActionPerformed(evt);
            }
        });
        jMenuEstoque.add(jMenuItemSaidaEstoque);

        jMenuBar1.add(jMenuEstoque);

        jMenuControleAcesso.setText("Controle de Acesso");
        jMenuControleAcesso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuControleAcessoMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuControleAcesso);

        jMenuVenda.setText("Venda");

        jMenuItemSolicitacaoVenda.setText("Solicitação de Venda");
        jMenuItemSolicitacaoVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSolicitacaoVendaActionPerformed(evt);
            }
        });
        jMenuVenda.add(jMenuItemSolicitacaoVenda);

        jMenuItemVendaDireta.setText("Venda Direta");
        jMenuItemVendaDireta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendaDiretaActionPerformed(evt);
            }
        });
        jMenuVenda.add(jMenuItemVendaDireta);

        jMenuItemAprovaVenda.setText("Aprovação de Venda");
        jMenuItemAprovaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAprovaVendaActionPerformed(evt);
            }
        });
        jMenuVenda.add(jMenuItemAprovaVenda);

        jMenuBar1.add(jMenuVenda);

        jMenuRelatorios.setText("Relatórios");

        jMenuItemRelatorioVendas.setText("Vendas");
        jMenuItemRelatorioVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioVendasActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioVendas);

        jMenuItemRelatorioEstoque.setText("Relatório de Estoque");
        jMenuItemRelatorioEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioEstoqueActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioEstoque);

        jMenuItemRelatorioHistoricoEstoque.setText("Relatório Histórico do Estoque");
        jMenuItemRelatorioHistoricoEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRelatorioHistoricoEstoqueActionPerformed(evt);
            }
        });
        jMenuRelatorios.add(jMenuItemRelatorioHistoricoEstoque);

        jMenuBar1.add(jMenuRelatorios);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCadastroProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroProdutosActionPerformed
        CadastroProdutos cadastro = new CadastroProdutos();
        cadastro.passaDados(usuario);
        jDesktopPane1.add(cadastro);
        cadastro.setVisible(true);
        try {
            cadastro.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItemCadastroProdutosActionPerformed

    private void jMenuItemCadastroFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroFornecedoresActionPerformed
       CadastroFornecedores cadastro = new CadastroFornecedores();
       cadastro.passaDados(usuario);
       jDesktopPane1.add(cadastro);
       cadastro.setVisible(true);
        try {
            cadastro.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCadastroFornecedoresActionPerformed

    private void jMenuItemCadastroFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroFuncionariosActionPerformed
        CadastroFuncionarios funcionario = new CadastroFuncionarios();
        funcionario.passaDados(usuario);
        jDesktopPane1.add(funcionario);
        funcionario.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastroFuncionariosActionPerformed

    private void jMenuItemCadastroTipodeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroTipodeProdutoActionPerformed
        CadastroTipoProdutos cadastro = new CadastroTipoProdutos();
        jDesktopPane1.add(cadastro);
        cadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadastroTipodeProdutoActionPerformed

    private void jMenuItemEntradaEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEntradaEstoqueActionPerformed
        EntradaEstoque entrada = new EntradaEstoque();
        entrada.passaDados(usuario);
        jDesktopPane1.add(entrada);
        entrada.setVisible(true);
         try {
            entrada.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemEntradaEstoqueActionPerformed

    private void jMenuItemSaidaEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaidaEstoqueActionPerformed
       SaidaEstoque saida = new SaidaEstoque();
       saida.passaDados(usuario);
       jDesktopPane1.add(saida);
       saida.setVisible(true);
       try {
            saida.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemSaidaEstoqueActionPerformed

    private void jMenuItemVendaDiretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendaDiretaActionPerformed
        VendaDireta venda = new VendaDireta();
        venda.passaDados(usuario);
        jDesktopPane1.add(venda);
        venda.setVisible(true);
         try {
            venda.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemVendaDiretaActionPerformed

    private void jMenuItemSolicitacaoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSolicitacaoVendaActionPerformed
        SolicitacaoVenda venda = new SolicitacaoVenda();
        venda.passaDados(usuario);
        jDesktopPane1.add(venda);
        venda.setVisible(true);
         try {
            venda.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemSolicitacaoVendaActionPerformed

    private void jMenuItemAprovaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAprovaVendaActionPerformed
        AprovacaoDeVendas aprovacao = new AprovacaoDeVendas();
        jDesktopPane1.add(aprovacao);
        aprovacao.passaDados(usuario,this);
      
        aprovacao.setVisible(true);
         try {
            aprovacao.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemAprovaVendaActionPerformed

    private void jMenuItemRelatorioVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioVendasActionPerformed
        RelatorioVendas relatorioVendas = new RelatorioVendas();
         
        jDesktopPane1.add(relatorioVendas);
        relatorioVendas.setVisible(true);
        try {
            relatorioVendas.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemRelatorioVendasActionPerformed

    private void jMenuItemRelatorioEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioEstoqueActionPerformed
       RelatorioEstoque relatorioEstoque = new RelatorioEstoque();
       jDesktopPane1.add(relatorioEstoque);
       relatorioEstoque.setVisible(true);
       try {
            relatorioEstoque.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemRelatorioEstoqueActionPerformed

    private void jMenuItemRelatorioHistoricoEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRelatorioHistoricoEstoqueActionPerformed
       RelatorioHistoricoEstoque relatorioEstoque = new RelatorioHistoricoEstoque();
       jDesktopPane1.add(relatorioEstoque);
       relatorioEstoque.setVisible(true);
       try {
            relatorioEstoque.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemRelatorioHistoricoEstoqueActionPerformed

    private void jMenuControleAcessoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuControleAcessoMouseClicked
       ControleAcesso controleAcesso = new ControleAcesso();
       jDesktopPane1.add(controleAcesso);
       controleAcesso.passaDados(usuario);
       controleAcesso.setVisible(true);
    }//GEN-LAST:event_jMenuControleAcessoMouseClicked

    private void jLabelAlertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlertaMouseClicked
       ProdutosEstoqueNegativo produtos = new ProdutosEstoqueNegativo();
       jDesktopPane1.add(produtos);
       produtos.setVisible(true);
    }//GEN-LAST:event_jLabelAlertaMouseClicked
    
    class JPanelGradient extends JPanel
    {
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            
            //143,199,74
            Color color1 = new Color(172,172,170);
            Color color2 = new Color(218,218,218);
            
            
            GradientPaint gp = new GradientPaint(0,0,color1,180,height,color2);            
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
                    
    }
    
    public void abreTela()
    {
        AprovacaoDeVendas aprovacao = new AprovacaoDeVendas();
        jDesktopPane1.add(aprovacao);
        aprovacao.passaDados(usuario,this);
      
        aprovacao.setVisible(true);
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAlerta;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCadastros;
    private javax.swing.JMenu jMenuControleAcesso;
    private javax.swing.JMenu jMenuEstoque;
    private javax.swing.JMenuItem jMenuItemAprovaVenda;
    private javax.swing.JMenuItem jMenuItemCadastroFornecedores;
    private javax.swing.JMenuItem jMenuItemCadastroFuncionarios;
    private javax.swing.JMenuItem jMenuItemCadastroProdutos;
    private javax.swing.JMenuItem jMenuItemCadastroTipodeProduto;
    private javax.swing.JMenuItem jMenuItemEntradaEstoque;
    private javax.swing.JMenuItem jMenuItemRelatorioEstoque;
    private javax.swing.JMenuItem jMenuItemRelatorioHistoricoEstoque;
    private javax.swing.JMenuItem jMenuItemRelatorioVendas;
    private javax.swing.JMenuItem jMenuItemSaidaEstoque;
    private javax.swing.JMenuItem jMenuItemSolicitacaoVenda;
    private javax.swing.JMenuItem jMenuItemVendaDireta;
    private javax.swing.JMenu jMenuRelatorios;
    private javax.swing.JMenu jMenuVenda;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
