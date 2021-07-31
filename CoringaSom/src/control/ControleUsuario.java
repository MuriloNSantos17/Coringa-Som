/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.Conecta_Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ModeloUsuario;

/**
 *
 * @author muril
 */
public class ControleUsuario {
    
    public void Login(ModeloUsuario usuario)
    {
        MetodosPadrao metodo = new MetodosPadrao();
        
       Conecta_Banco conecta = new Conecta_Banco();
       
       try 
       {        
           String senha = metodo.criptografar(usuario.getDs_senha());
           
           conecta.conexao();
           conecta.executaSQL("SELECT ID, IN_LOGIN, DS_STATUS FROM USUARIOS WHERE ID_USUARIO='"+usuario.getId_usuario()+"' AND \n" +
            "DS_SENHA='"+senha+"';"
            );
           
           if(conecta.rs.first()==true)
           {
               
               usuario.setId(conecta.rs.getInt("ID"));
               usuario.setIn_alterar_senha(conecta.rs.getString("IN_LOGIN"));
               
               if(conecta.rs.getString("DS_STATUS").equals("I"))
               {
                   usuario.setLogin(false);
                    JOptionPane.showMessageDialog(null,"Atenção, Usuário Inativo!\n"
                    + "Procure um Administrador"
                    ,"Erro:",JOptionPane.WARNING_MESSAGE);
                    usuario.setDs_status("I");
               }
               else
               {
                   usuario.setLogin(true);
               }
           }
           else
           {
               
               usuario.setLogin(false);
               usuario.setDs_status("E"); //E de EMPTY, VAZIO
              
           }
       } 
       catch (SQLException ex) 
       {
           JOptionPane.showMessageDialog(null,"Erro SQL no id do usuário\n"+ex);
           
       }
    }
    
    public void CadastrarUsuario(ModeloUsuario usuario)
    {
        
        MetodosPadrao metodo = new MetodosPadrao();
        
        String senha = metodo.criptografar(usuario.getDs_senha());
         
         Conecta_Banco conecta = new Conecta_Banco();
        try 
        {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "INSERT INTO USUARIOS (ID,NM_USUARIO,ID_USUARIO,DS_MATRICULA,DS_SENHA,DS_STATUS,IN_LOGIN)\n" +
            "VALUES (DEFAULT,?,?,?,?,'A','S');");            
            pst.setString(1,usuario.getNm_usuario());  
            pst.setString(2,usuario.getId_usuario()); 
            pst.setString(3,usuario.getDs_matricula()); 
            pst.setString(4,senha);       
            pst.executeUpdate();                                                                                    
            JOptionPane.showMessageDialog(null,"Funcionário Cadastrado com Sucesso!");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Cadastrar Funcionário:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    public void alterarSenha(ModeloUsuario usuario)
    {
        MetodosPadrao metodo = new MetodosPadrao();
        
        String senha = metodo.criptografar(usuario.getDs_senha());
         
         Conecta_Banco conecta = new Conecta_Banco();
        try 
        {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "UPDATE USUARIOS SET IN_LOGIN =?, DS_SENHA=? WHERE ID_USUARIO='"+usuario.getId_usuario()+"';");            
            pst.setString(1,usuario.getIn_alterar_senha());  
            pst.setString(2,senha);      
            pst.executeUpdate();                                                                                    
            JOptionPane.showMessageDialog(null,"Senha Alterada com Sucesso!");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar senha: "+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    public void alterarUsuario(ModeloUsuario usuario)
    {
        MetodosPadrao metodo = new MetodosPadrao();
        
        String senha = metodo.criptografar(usuario.getDs_senha());
         
         Conecta_Banco conecta = new Conecta_Banco();
        try 
        {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "UPDATE USUARIOS SET IN_LOGIN =?, DS_SENHA=?, NM_USUARIO=? WHERE ID_USUARIO='"+usuario.getId_usuario()+"';");            
            pst.setString(1,usuario.getIn_alterar_senha());  
            pst.setString(2,senha);      
            pst.setString(3,usuario.getNm_usuario());
            pst.executeUpdate();                                                                                    
            JOptionPane.showMessageDialog(null,"Usuário Alterado com Sucesso!");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar Usuário: "+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    //UPDATE USUARIOS SET DS_STATUS='I' WHERE ID_USUARIO='';
    
    
     public void ativaInativaUsuario(ModeloUsuario usuario)
    {
        String status=usuario.getDs_status();
        
        int opc=0;
        if(status.equals("A"))
        {
            opc = JOptionPane.showConfirmDialog(null,"Deseja realmente Ativar o usuário "
            +usuario.getId_usuario()+"?");           
        }
        else
        {
             opc = JOptionPane.showConfirmDialog(null,"Deseja realmente Inativar o usuário "
            +usuario.getId_usuario()+"?");
        }
        
        if(opc ==JOptionPane.YES_OPTION)
        {
            Conecta_Banco conecta = new Conecta_Banco();
            try 
            {
                conecta.conexao();
                PreparedStatement pst = conecta.conn.prepareStatement(
                "UPDATE USUARIOS SET DS_STATUS=? WHERE ID_USUARIO='"+usuario.getId_usuario()+"'");            
                pst.setString(1,usuario.getDs_status());  
                pst.executeUpdate();                        
                if(status.equals("A"))
                {
                    JOptionPane.showMessageDialog(null,"Usuário Ativado com Sucesso!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Usuário Inativado com Sucesso!");
                }
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao Ativar/Inativar Usuário: "+
                ex,"Erro:",JOptionPane.WARNING_MESSAGE);
                System.out.println("ERRO: "+ex);
            }
            finally
            {
                conecta.desconecta();
            }
        }
         
    }
    
    public int buscaMaximoIdUsuario()
    {
        
       Conecta_Banco conecta = new Conecta_Banco();
       //BUSCO O ID DO CLIENTE PELO NOME
       try 
       {
          
           //System.out.println(nome);
           conecta.conexao();
           conecta.executaSQL("SELECT MAX(ID) AS ID FROM USUARIOS;");
           conecta.rs.first();
          
           return conecta.rs.getInt("ID");
          
       } 
       catch (SQLException ex) 
       {
           JOptionPane.showMessageDialog(null,"Erro ao Encontrar Máximo do id: "
           +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            return 0;
       }
       finally{
           conecta.desconecta();
       }
    }
    
    public void cadastrarAcesso(int id)
    {
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "INSERT INTO ACESSO (ID,ID_USUARIO,CAD_FORN,CAD_FUNC,CAD_PROD,CAD_TP_PROD,\n" +
            "EST_ENT,EST_SA,CONT_ACESSO,SOLC_VENDA,VD_DIRETA,APV_VENDA,REL_VENDA,\n" +
            "REL_ESTQ,REL_HIST_ESTQ)  VALUES (DEFAULT, "+id+",'F','F','F','F','F','F','F',"
            + "'F','F','F','F','F','F')");                 
            pst.executeUpdate();                                                                                    
            JOptionPane.showMessageDialog(null,"Acesso cadastrado com sucesso!");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Cadastrar acesso:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
}
