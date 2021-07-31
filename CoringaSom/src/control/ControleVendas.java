
package control;

import dao.Conecta_Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ModeloVenda;

public class ControleVendas {
    
    public void gravaVenda(ModeloVenda venda)
    {

        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {            
             conecta.conexao();
             PreparedStatement pst = conecta.conn.prepareStatement(
             "INSERT INTO VENDAS(ID,NR_VENDA,ID_PRODUTO,DS_QUANTIDADE,ID_"
           + "USUARIO_VENDA,ID_USUARIO_APROVADOR,DS_STATUS_VENDA,DT_VENDA,TOTAL,VL_DESCONTO)\n" +
            "VALUES(DEFAULT,?,?,?,?,?,?,NOW(),?,?);");
             pst.setInt(1,venda.getNr_venda());  //produto pesquisado acima
             pst.setInt(2,venda.getId_produto());
             pst.setInt(3,venda.getDs_quantidade());  
             pst.setInt(4,venda.getId_usuario_venda());     
             pst.setInt(5,venda.getId_usuario_aprovador());
             pst.setString(6,venda.getStatus_venda());
             pst.setFloat(7,venda.getTotal());
             pst.setFloat(8,venda.getDesconto());
             pst.executeUpdate();                                                                                    
            //  JOptionPane.showMessageDialog(null,"Estoque Atualizado com Sucesso");
        } 
        catch (SQLException ex) 
        {
             JOptionPane.showMessageDialog(null,"Erro ao Lançar Venda!:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
             System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    public void alteraProdutos(ModeloVenda venda)
    {
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "UPDATE VENDAS SET DS_QUANTIDADE=?, TOTAL=? WHERE ID="+venda.getId());       
            
            System.out.println("UPDATE VENDAS SET DS_QUANTIDADE=?, TOTAL=? WHERE ID="+venda.getId());
            pst.setInt(1,venda.getDs_quantidade());  
            pst.setFloat(2,venda.getTotal());      
            pst.executeUpdate();                                                                                    
            JOptionPane.showMessageDialog(null,"Quantidade alterada com Sucesso!");  
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar quantidade: "+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    
    public void alteraStatusVenda(ModeloVenda venda)
    {
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "UPDATE VENDAS SET DS_STATUS_VENDA=?, ID_USUARIO_APROVADOR=? WHERE NR_VENDA="+venda.getNr_venda()+";");                   
            pst.setString(1,venda.getStatus_venda());  
            pst.setInt(2,venda.getId_usuario_aprovador());    
            pst.executeUpdate();                                                                                    
            //JOptionPane.showMessageDialog(null,"Venda finalizada com Sucesso!");  
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar status da venda: "+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    
    //PEGA PRIMEIRO ID DA VENDA E ATUALIZA O DESCONTO
    public void atualizaDesconto(ModeloVenda venda)
    {
        Conecta_Banco conecta = new Conecta_Banco();
        
        conecta.conexao();
        
        String id="";
        try 
        {          
            conecta.executaSQL("SELECT ID FROM VENDAS WHERE NR_VENDA="+venda.getNr_venda()+";");
            conecta.rs.first();

            id = conecta.rs.getString("ID");

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Encontrar Id da venda"
            +ex,"Erro:",JOptionPane.ERROR_MESSAGE);

        }
        
        //System.out.println("** ID: "+id);
        
        System.out.println("venda get"+venda.getDesconto());
        try 
        {
            PreparedStatement pst = conecta.conn.prepareStatement(
            "UPDATE VENDAS SET  VL_DESCONTO=? WHERE ID="+id+";");                             
            pst.setFloat(1,venda.getDesconto());
            pst.executeUpdate();                                                                                    
            //JOptionPane.showMessageDialog(null,"Venda updatedata com Sucesso!");  
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar desconto da venda: "+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }

    }
}
