package control;

import dao.Conecta_Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ModeloAcesso;


public class ControlerAcesso {
    
    
    public void alteraAcesso(ModeloAcesso acesso, int id)
    {
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {       
            String SQL = "UPDATE ACESSO SET CAD_FORN='"+acesso.getCad_forn()+"', "
            + "CAD_FUNC='"+acesso.getCad_func()+"',"
            + "CAD_PROD='"+acesso.getCad_prod()+"',"
            + "CAD_TP_PROD='"+acesso.getCad_tp_prod()+"',\n" +
            "EST_ENT='"+acesso.getEst_ent()+"',"
            + "EST_SA='"+acesso.getEst_sa()+"',"
            + "CONT_ACESSO='"+acesso.getCont_acesso()+"',"
            + "SOLC_VENDA='"+acesso.getSolc_venda()+"',\n" +
            "VD_DIRETA='"+acesso.getVd_direta()+"',"
            + "APV_VENDA='"+acesso.getApv_venda()+"',"
            + "REL_VENDA='"+acesso.getRel_venda()+"',\n" +
            "REL_ESTQ='"+acesso.getRel_estq()+"',"
            + "REL_HIST_ESTQ='"+acesso.getRel_hist_estq()+"' "
            + "WHERE ID_USUARIO="+id;
            
            
            
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement
            (SQL); 
            pst.executeUpdate();                                                                                   
            JOptionPane.showMessageDialog(null,"Acesso alterado com Sucesso");

        } 
        catch (SQLException ex) {

          JOptionPane.showMessageDialog(null,"Erro ao alterar Acesso"
           +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            conecta.desconecta();
        }
    }
}
