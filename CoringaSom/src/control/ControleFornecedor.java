
package control;

import dao.Conecta_Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ModeloFornecedor;


public class ControleFornecedor {
    
    public void cadastrarFornecedores(ModeloFornecedor fornecedor) //Método para gravar Fornecedores no banco de dados
    {
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "INSERT INTO FORNECEDORES (NM_FORNECEDOR,NR_CNPJ,NR_TELEFONE,ID_USUARIO,DS_STATUS,ID_EMAIL)\n" +
            "VALUES(?,?,?,?,?,?);");            
            pst.setString(1,fornecedor.getNm_fornecedor());  
            pst.setString(2,fornecedor.getNr_cnpj()); 
            pst.setString(3,fornecedor.getNr_telefone()); 
            pst.setInt(4,Integer.parseInt(fornecedor.getId_usuario())); 
            pst.setString(5,fornecedor.getDs_status()); 
            pst.setString(6,fornecedor.getId_email()); 
            pst.executeUpdate();                                                                                    
            JOptionPane.showMessageDialog(null,"Fornecedor Cadastrado com Sucesso!");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Cadastrar Fornecedor:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    public void inativaFornecedor(int id,String nome)
    {
        
        int opc = JOptionPane.showConfirmDialog(null,"Deseja mesmo Inativar o Fornecedor "+
                nome+" ?");
        
        if(opc==JOptionPane.YES_OPTION)
        {
            Conecta_Banco conecta = new Conecta_Banco();
            try 
            {         
                conecta.conexao();
                PreparedStatement pst = conecta.conn.prepareStatement
                ("UPDATE FORNECEDORES SET DS_STATUS='I' WHERE ID="+id+";"); 
                pst.executeUpdate();                                                                                   
                JOptionPane.showMessageDialog(null,"Fornecedor Inativado com Sucesso");

            } 
            catch (SQLException ex) {

              JOptionPane.showMessageDialog(null,"Erro ao Inativar Fornecedor"
               +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                conecta.desconecta();
            }
        }
        
    }
    
     public void editaFornecedor(ModeloFornecedor modeloFornecedor,String nome)
    {
        
        int opc = JOptionPane.showConfirmDialog(null,"Deseja mesmo Alterar o Fornecedor "+
                nome+" ?");
        
        if(opc==JOptionPane.YES_OPTION)
        {
            Conecta_Banco conecta = new Conecta_Banco();
            try 
            {         
                conecta.conexao();
                PreparedStatement pst = conecta.conn.prepareStatement
                ("UPDATE FORNECEDORES SET NM_FORNECEDOR=?, NR_TELEFONE=?, ID_EMAIL=? WHERE ID="
                +modeloFornecedor.getId());   
                pst.setString(1,modeloFornecedor.getNm_fornecedor());
                pst.setString(2,modeloFornecedor.getNr_telefone());
                pst.setString(3,modeloFornecedor.getId_email());
                pst.executeUpdate();                                                                                   
                JOptionPane.showMessageDialog(null,"Fornecedor Alterado com Sucesso");

            } 
            catch (SQLException ex) {

              JOptionPane.showMessageDialog(null,"Erro ao Alterar Fornecedor"
               +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                conecta.desconecta();
            }
        }
        
    }
}
