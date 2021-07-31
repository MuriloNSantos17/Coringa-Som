package dao;

import java.sql.*;
import javax.swing.JOptionPane;


public class Conecta_Banco 
{
    // Variveis Obrigatorias para conexão com o Banco de Dados
    public  Statement stm; //statement é responsável por preparar e realizar pesquisas no banco de dados
    public  ResultSet rs; // responsável por armazenar o resultado de uma pesquisa passada para o Statement
    private String driver  = "com.mysql.jdbc.Driver"; //Responsável por identificar serviço do Banco de Dados
    private String caminho = "jdbc:mysql://localhost:3306/coringadb"; // Responsável por Setar o Local do Banco de Dados
    private String usuario = "root"; //Usuário para acesso do banco
    private String senha   = "R1s3w@rr10r"; //senha pré definida na instalação
    public  Connection conn; //Responsável por realizar a conexão com o Banco de Dados 
    
    //Métodos de Conexão
    
    public void conexao() //esse método é responsável por realizar a conexão com o banco de dados
    {
        try //Tentativa Inicial
        {
            System.setProperty("jdbc.Drivers",driver); //Seta a Propriedade do Driver de Conexão
            conn = DriverManager.getConnection(caminho, usuario, senha); //Realiza a Conexão com o Banco de Dados
            //JOptionPane.showMessageDialog(null,"Conectado com Sucesso!"); //Imprime uma caixa de mensagem (se for True); 
        }   
        
        catch (SQLException ex) // Caso de Errado (exceção)
        {
            JOptionPane.showMessageDialog(null,"Erro de Conexão!\n Erro: "+ex.getMessage());//Imprime uma caixa de mensagem (se for False);
        }
    }
    
    public void desconecta() // Metodo Para Fechar a Conexão com o Banco de Dados
    {
        try 
        {
            conn.close(); //Fecha a Conexão com Banco
        } 
        
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Fechar Conexão!\n Erro"+ex.getMessage()); //Exeção se der Errado
        }
    }
    
    public void executaSQL(String sql)
    {
        try 
        {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } 
        
        catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null,"Erro No Metodo SQL !\n Erro"+ex.getMessage());
        }
    }
}






