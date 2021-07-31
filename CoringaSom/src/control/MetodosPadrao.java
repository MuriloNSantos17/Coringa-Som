package control;

import dao.Conecta_Banco;
import java.awt.Color;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MetodosPadrao {
    
    //MÉTODOS PADRÕES DEVEM SER UTILIZADOS AQUI
    
    public String formataFloats(float myFloat)
    {
        String formatado = String.format("%.02f", myFloat);               
        return formatado; 
    }
    
    /*
        Os métodos abaixo serve para validar os campos preenchidos
        caso não tenham sido preenchidos corretamente eles ficarão amarelos
        e vão requisitar foco na ordem escolhida
    */
    public void validaCampo(JTextField textField, String campo)
    {
        if(textField.getText().isEmpty())
        {
            textField.setBackground(Color.YELLOW);
            textField.requestFocus();
            JOptionPane.showMessageDialog(null,
            "O Campo "+campo+" está vazio","Erro:",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void validaCampoSenha(JPasswordField textField, String campo)
    {
        if(textField.getText().isEmpty())
        {
            textField.setBackground(Color.YELLOW);
            textField.requestFocus();
            JOptionPane.showMessageDialog(null,
            "O Campo "+campo+" está vazio","Erro:",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void validaCampoFormatado(JFormattedTextField textField, String campo, int tamanho)
    {
        if(textField.getText().trim().length() <tamanho)
        {
            textField.setBackground(Color.YELLOW);
            textField.requestFocus();
            JOptionPane.showMessageDialog(null,
            "O Campo "+campo+" está vazio","Erro:",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*
        O método abaixo serve para validar os combobox selecionados
        caso não tenham sido selecionados corretamente eles ficarão amarelos
        e vão requisitar foco na ordem escolhida
    */
    
    public void validaCombo(JComboBox combobox, String combo)
    {
        if(combobox.getSelectedIndex() <=0)
        {
               JOptionPane.showMessageDialog(null,
            "O combobox "+combo+" não foi selecionado!","Erro:",JOptionPane.ERROR_MESSAGE);
               combobox.requestFocus();
               combobox.setBackground(Color.yellow);
        }
    }
    
    /*
        Os métodos abaixo serve para deixar os campos que perderem foco brancos novamente
    */
    
    public void desfocaCampo(JTextField textField)
    {
         textField.setBackground(Color.WHITE);
    }
    
    
    public void desfocaCampoSenha(JPasswordField textField)
    {
         textField.setBackground(Color.WHITE);
    }
    
      public void desfocaCampoFormatado(JFormattedTextField textField)
    {
         textField.setBackground(Color.WHITE);
    }
    
    
     /*
        O método abaixo serve para deixar os combobox que perderem foco brancos novamente
    */
    
    public void desfocaCombo(JComboBox combo)
    {
         combo.setBackground(Color.WHITE);
    }
    
     /*
        O método abaixo serve para criptografar senhas
    */
     public String criptografar(String senha)
        {
            MessageDigest m;
            String novaSenha="";
            try {
                m = MessageDigest.getInstance("MD5");
                m.update(senha.getBytes(),0,senha.length());
                novaSenha = ""+new BigInteger(1,m.digest()).toString(16);
                //System.out.println(novaSenha);
            } catch (NoSuchAlgorithmException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao Criptografar Senha!\n"+ex);
            }
            return novaSenha;
        }
    
     
   /*
        Busca Id do fornecedor pelo CNPJ
     */
     
     
     public int buscaIdFornecedor(String cnpj)
     {
        Conecta_Banco conecta = new Conecta_Banco();
       //BUSCO O ID DO CLIENTE PELO NOME
       try 
       {
          
           //System.out.println(nome);
           conecta.conexao();
           conecta.executaSQL("SELECT ID FROM FORNECEDORES WHERE NR_CNPJ='"+cnpj+"';");
           conecta.rs.first();
          
           return conecta.rs.getInt("ID");
          
       } 
       catch (SQLException ex) 
       {
           JOptionPane.showMessageDialog(null,"Erro ao Encontrar Id do Fornecedor"
           +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            return 0;
       }
       
    }
     
     public String replace(String valor)
     {
         return valor.replace(",",".");
     }
     
     
     public int buscaUltimaMatricula()
     {
       Conecta_Banco conecta = new Conecta_Banco();
       //BUSCO O ID DO CLIENTE PELO NOME
       try 
       {
          
           //System.out.println(nome);
           conecta.conexao();
           conecta.executaSQL("SELECT MAX(DS_MATRICULA+1) AS MATRICULA FROM USUARIOS;");
           conecta.rs.first();
          
           return conecta.rs.getInt("MATRICULA");
          
       } 
       catch (SQLException ex) 
       {
           JOptionPane.showMessageDialog(null,"Erro ao Encontrar Máximo da Matrícula"
           +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            return 0;
       }
       finally{
           conecta.desconecta();
       }
       
    }
     
     public int buscaNumeroVenda()
     {
        Conecta_Banco conecta = new Conecta_Banco();
        //BUSCO O ID DO CLIENTE PELO NOME
       try 
       {
          
           //System.out.println(nome);
           conecta.conexao();
           conecta.executaSQL("SELECT MAX(NR_VENDA) AS TOTALVENDA FROM VENDAS;");
           conecta.rs.first();
          
           int i =  conecta.rs.getInt("TOTALVENDA");
           i++;
           
           return i;
          
       } 
       catch (SQLException ex) 
       {
           JOptionPane.showMessageDialog(null,"Erro ao Encontrar Máximo de vendas!"
           +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            return 1;
       }
       finally{
           conecta.desconecta();
       }
     }
     
     public int buscaIdProduto(String nome)
     {
         //SELECT ID FROM PRODUTOS WHERE NM_PRODUTO = 'GPS'
         Conecta_Banco conecta = new Conecta_Banco();
        //BUSCO O ID DO CLIENTE PELO NOME
       try 
       {
          
           //System.out.println(nome);
           conecta.conexao();
           conecta.executaSQL("SELECT ID FROM PRODUTOS WHERE NM_PRODUTO = '"+nome+"'");
           conecta.rs.first();
          
           int i =  conecta.rs.getInt("ID");
            
           return i;
          
       } 
       catch (SQLException ex) 
       {
           JOptionPane.showMessageDialog(null,"Erro ao Encontrar id do produto"
           +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
           return 0;
       }
       finally{
           conecta.desconecta();
       }
     }
     
     public String convertDataAmericana(String dat)
     {
        
        String dia = dat.substring(0,2);
        String mes = dat.substring(3,5);
        String ano = dat.substring(6,10);
       
        String dataFinal = ano+"-"+mes+"-"+dia;
        
        return dataFinal;
     }
     
     public JTable removeLinha(JTable tabela)
     {
         return tabela;
     }
}
