/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.formdev.flatlaf.FlatLightLaf;
import control.ControleUsuario;
import control.MetodosPadrao;
import dao.Conecta_Banco;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.ModeloUsuario;

/**
 *
 * @author muril
 */
public class AlterarSenha extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    
    
    
    public AlterarSenha() {
      initComponents();
      this.setIconImage(new ImageIcon(getClass().getResource("/imagens/Coringa.png")).getImage());
    }
    
  
    public void passaDados(String usuario)
    {               
        jTextFieldUsuario.setText(usuario);
        jPasswordFieldSenha.requestFocus();
        jPasswordFieldSenha.setText("");
    }
    

    MetodosPadrao metodo = new MetodosPadrao();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPasswordFieldConfirmarSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabelFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alterar Senha");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Login");

        jTextFieldUsuario.setEditable(false);
        jTextFieldUsuario.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTextFieldUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldUsuarioFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Senha");

        jPasswordFieldSenha.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPasswordFieldSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordFieldSenhaFocusLost(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 168, 82));
        jLabel3.setFont(new java.awt.Font("Old English Text MT", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 168, 82));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Coringa Som");

        jButton1.setBackground(new java.awt.Color(0, 168, 82));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Alterar Senha");
        jButton1.setToolTipText("Alterar Senha");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPasswordFieldConfirmarSenha.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPasswordFieldConfirmarSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordFieldConfirmarSenhaFocusLost(evt);
            }
        });
        jPasswordFieldConfirmarSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldConfirmarSenhaKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Confirmar Senha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldUsuario)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jPasswordFieldSenha)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(jPasswordFieldConfirmarSenha))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordFieldConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 590));

        jLabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo_login.png"))); // NOI18N
        getContentPane().add(jLabelFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        alterar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioFocusLost
       metodo.desfocaCampo(jTextFieldUsuario);
    }//GEN-LAST:event_jTextFieldUsuarioFocusLost

    private void jPasswordFieldSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFieldSenhaFocusLost
       metodo.desfocaCampoSenha(jPasswordFieldSenha);
    }//GEN-LAST:event_jPasswordFieldSenhaFocusLost

    private void jPasswordFieldConfirmarSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFieldConfirmarSenhaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldConfirmarSenhaFocusLost

    private void jPasswordFieldConfirmarSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldConfirmarSenhaKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
           alterar();
       }
    }//GEN-LAST:event_jPasswordFieldConfirmarSenhaKeyPressed

    void alterar()
    {
        if(ValidaCampos()==true)
        {
            ModeloUsuario modeloUsuario = new ModeloUsuario();
            modeloUsuario.setId_usuario(jTextFieldUsuario.getText());
            modeloUsuario.setDs_senha(jPasswordFieldSenha.getText());
            modeloUsuario.setIn_alterar_senha("N");
            
            
            ControleUsuario controleUsuario = new ControleUsuario();
            controleUsuario.alterarSenha(modeloUsuario);
            Login login = new Login();
            login.passaDados(modeloUsuario.getId_usuario());
            login.setVisible(true);
            this.dispose();                        
        }
    }
    
    boolean ValidaCampos()
    {
        if(jPasswordFieldSenha.getText().isEmpty() 
        || jPasswordFieldConfirmarSenha.getText().isEmpty() 
        || (!jPasswordFieldSenha.getText().equals(jPasswordFieldConfirmarSenha.getText())))
        {            
            metodo.validaCampoSenha(jPasswordFieldSenha,"Senha");
            metodo.validaCampo(jTextFieldUsuario,"Usuário");
            if(!jPasswordFieldConfirmarSenha.getText().equals(jPasswordFieldSenha.getAccessibleContext()))                
            {
                JOptionPane.showMessageDialog(null,
                "As Senhas não conferem","Erro:",JOptionPane.ERROR_MESSAGE);
                jPasswordFieldSenha.setText("");
                jPasswordFieldConfirmarSenha.setText("");
            }
            jPasswordFieldSenha.requestFocus();
            return false;            
        }
        else
        {
            return true;
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AlterarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarSenha().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFundo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldConfirmarSenha;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}