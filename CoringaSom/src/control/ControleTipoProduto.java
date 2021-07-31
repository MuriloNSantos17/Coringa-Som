package control;

import dao.Conecta_Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ModeloTipoProduto;

public class ControleTipoProduto {
    
    public void gravarTipoProduto(ModeloTipoProduto tipo)
    {
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {            
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "INSERT INTO TIPO_PRODUTO(NM_TIPO,DS_STATUS) VALUES(?,?);");
            pst.setString(1,tipo.getNm_tipo());  
            pst.setString(2,tipo.getDs_status());  
            pst.executeUpdate();                                                                                    
            JOptionPane.showMessageDialog(null,"Tipo de Produto Cadastrado com Sucesso!");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Cadastrar tipo do Produto:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
    }
    
    public void alterarTipoProduto(ModeloTipoProduto tipo)
    {
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {         
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement
            ("UPDATE TIPO_PRODUTO SET NM_TIPO=? WHERE ID="+tipo.getId()+";");               
            pst.setString(1,tipo.getNm_tipo());              
            pst.executeUpdate();                                                                                   
            JOptionPane.showMessageDialog(null,"Tipo Alterado com Sucesso");
        } 
        catch (SQLException ex) {

          JOptionPane.showMessageDialog(null,"Erro ao tipo: "
           +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    public void ativarInativarTipo(ModeloTipoProduto tipo)
    {
          Conecta_Banco conecta = new Conecta_Banco();
        try 
        {         
            String operacao="";
            
            if(tipo.getDs_status().equals("A"))
            {
                operacao = "Ativado";
            }
            else
            {
                operacao = "Inativado";
            }
            
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement
            ("UPDATE TIPO_PRODUTO SET DS_STATUS=? WHERE ID="+tipo.getId()+";");               
            pst.setString(1,tipo.getDs_status());              
            pst.executeUpdate();                                                                                   
            JOptionPane.showMessageDialog(null,"Tipo "+operacao+" com Sucesso");
        } 
        catch (SQLException ex) {

          JOptionPane.showMessageDialog(null,"Erro ao alterar status do tipo: "
           +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            conecta.desconecta();
        }
    }
}
