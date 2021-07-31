package control;

import dao.Conecta_Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ModeloProduto;

public class ControleProduto {
    
    public void gravarProduto(ModeloProduto produto)
    {
        boolean valida_cadastro=false;
        
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {            
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement(
            "INSERT INTO PRODUTOS (CD_BARRAS,NM_PRODUTO,DS_QUANTIDADE,VL_COMPRA,"
            + "VL_VENDA,ID_FORNECEDOR,ID_USUARIO,DS_STATUS,ID_TIPO) "
            + "VALUES(?,?,?,?,?,?,?,?,?);");
            
            pst.setString(1,produto.getCd_barras());  
            pst.setString(2,produto.getNm_produto());  
            pst.setInt(3,Integer.parseInt(produto.getDs_quantidade())); 
            pst.setFloat(4,produto.getVl_compra()); 
            pst.setFloat(5,produto.getVl_venda()); 
            pst.setInt(6,produto.getId_fornecedor()); 
            pst.setInt(7,produto.getId_usuario()); 
            pst.setString(8,produto.getStatus());
            pst.setInt(9,produto.getId_tipo()); 
            pst.executeUpdate();                                                                                    
            JOptionPane.showMessageDialog(null,"Produto Cadastrado com Sucesso!");
            valida_cadastro =true;
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Cadastrar Produto:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
            System.out.println("ERRO: "+ex);
        }
        
        //FIM DO CADASTRO DO PRODUTO AGORA VAMOS GRAVAR O PREÇO POR HISTÓRICO
        
        
       int id_produto =0;
       
       if(valida_cadastro ==true) //SE O CADASTRO DEU CERTO EXECUTE AS OPERAÇÕES ABAIXO
       {
        
        /*
           PRIMEIRO BUSCAMOS O ID MAXIMO DE PRODUTO NO CASO O ÚLTIMO CADASTRADO
           ALI EM CIMA
        */
        try 
        {          
            conecta.executaSQL("SELECT MAX(ID) AS ID FROM PRODUTOS;");
            conecta.rs.first();

            id_produto= conecta.rs.getInt("ID");

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Encontrar Id do Produto"
            +ex,"Erro:",JOptionPane.ERROR_MESSAGE);

        }


        /*
           AGORA PODEMOS GRAVAR O PRODUTO NA TABELA DE HISTÓRICO_PRECO
        */

        try 
        {            
             conecta.conexao();
             PreparedStatement pst = conecta.conn.prepareStatement(
             "INSERT INTO HISTORICO_PRECO (ID_PRODUTO,DT_ALTERACAO,VL_COMPRA,VL_VENDA,ID_USUARIO) VALUES(?,CURDATE(),?,?,?);");
             pst.setInt(1,id_produto);  //produto pesquisado acima
             pst.setFloat(2,produto.getVl_compra());  
             pst.setFloat(3,produto.getVl_venda()); 
             pst.setInt(4,produto.getId_usuario());            
             pst.executeUpdate();                                                                                    
             //JOptionPane.showMessageDialog(null,"O preço do Produto foi Registrado!");
        } 
        catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Erro ao Cadastrar Preço do Produto:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
             System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
        
        
        /*
            Gravamos o histórico do estoque deste produto
        */
        
        try 
        {            
             conecta.conexao();
             PreparedStatement pst = conecta.conn.prepareStatement(
             "INSERT INTO HISTORICO_ESTOQUE(ID,ID_PRODUTO,DS_MOVIMENTACAO,ID_USUARIO_MOVIMENTACAO,ID_USUARIO_RESPONSAVEL,DT_MOVIMENTACAO,"
             +"ESTOQUE_INICIAL,ESTOQUE_FINAL)\n" +
             "VALUES(DEFAULT,?,'C',?,?,NOW(),0,0);");
             pst.setInt(1,id_produto);  //produto pesquisado acima
             pst.setInt(2,produto.getId_usuario());  
             pst.setInt(3,produto.getId_usuario());        
             pst.executeUpdate();                                                                                    
             //JOptionPane.showMessageDialog(null,"O Histórico do Estoque do Produto foi Registrado!");
        } 
        catch (SQLException ex) 
        {
             JOptionPane.showMessageDialog(null,"Erro ao Cadastrar Histórico do Estoque do Produto:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
             System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
        
        /*
            Gravamos o estoque do produto em si
        */
        
        //INSERT INTO ESTOQUE(ID,ESTOQUE,ID_PRODUTO,ID_USUARIO) VALUES(DEFAULT,0,?,?);
        try 
        {            
             conecta.conexao();
             PreparedStatement pst = conecta.conn.prepareStatement(
             "INSERT INTO ESTOQUE(ID,ESTOQUE,ID_PRODUTO,ID_USUARIO,ESTOQUE_ABAIXO) VALUES(DEFAULT,0,?,?,'S');");
             pst.setInt(1,id_produto);  //produto pesquisado acima
             pst.setInt(2,produto.getId_usuario());    
             pst.executeUpdate();                                                                                    
             //JOptionPane.showMessageDialog(null,"O Estoque do Produto foi Registrado!");
        } 
        catch (SQLException ex) 
        {
             JOptionPane.showMessageDialog(null,"Erro ao Cadastrar o Estoque do Produto:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
             System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
        
        JOptionPane.showMessageDialog(null,"O Estoque do produto foi configurado!\nO estoque atual dele é 0, gere uma entrada!");
        
       }
       
       
       
       
       
    }
    
    
    public void editaProduto(ModeloProduto produto, String nome)
    {
        int opc = JOptionPane.showConfirmDialog(null,"Deseja mesmo Alterar o Produto "+
                nome+" ?");
        
        if(opc==JOptionPane.YES_OPTION)
        {
            Conecta_Banco conecta = new Conecta_Banco();
            //BUSCAMOS O ID PELO CÓDIGO DE BARRAS PRIMEIRO
            
            try 
            {          
                conecta.conexao();
                conecta.executaSQL("SELECT ID FROM PRODUTOS WHERE CD_BARRAS="+
                produto.getCd_barras()+";");
                conecta.rs.first();
                produto.setId(conecta.rs.getInt("ID"));

            } 
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null,"Erro ao Encontrar Id do Produto"
                +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            }
            
            
            
            //Alteramos o preço do produto pelo id encontrado acima
            try 
            {         
                PreparedStatement pst = conecta.conn.prepareStatement
                ("UPDATE PRODUTOS SET NM_PRODUTO =?, DS_QUANTIDADE =?, "
                + "VL_COMPRA=?, VL_VENDA=?, ID_FORNECEDOR=?, ID_TIPO=? "
                + "WHERE ID="+produto.getId());   
                
                pst.setString(1,produto.getNm_produto());
                pst.setInt(2,Integer.parseInt(produto.getDs_quantidade()));
                pst.setFloat(3,produto.getVl_compra());
                pst.setFloat(4,produto.getVl_venda());
                pst.setInt(5,produto.getId_fornecedor());
                pst.setInt(6,produto.getId_tipo() );
                pst.executeUpdate();                                                                                   
                JOptionPane.showMessageDialog(null,"Produto Alterado com Sucesso");

            } 
            catch (SQLException ex) {

              JOptionPane.showMessageDialog(null,"Erro ao Alterar Produto"
               +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            }
            
            //AGORA GRAVAMOS O HISTÓRICO PREÇO ATUALIZADO       
            try 
            {            
                PreparedStatement pst = conecta.conn.prepareStatement(
                "INSERT INTO HISTORICO_PRECO (ID_PRODUTO,DT_ALTERACAO,VL_COMPRA,VL_VENDA,ID_USUARIO) VALUES(?,CURDATE(),?,?,?);");
                pst.setInt(1,produto.getId());  //produto pesquisado acima
                pst.setFloat(2,produto.getVl_compra());  
                pst.setFloat(3,produto.getVl_venda()); 
                pst.setInt(4,produto.getId_usuario());            
                pst.executeUpdate();                                                                                    
                JOptionPane.showMessageDialog(null,"O preço do Produto foi Alterado!");
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao Alterar Preço do Produto:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
                System.out.println("ERRO: "+ex);
            }
           finally
           {
               conecta.desconecta();
           }
        }
        
    }
    
    public void ativaInativaProduto(String nome, ModeloProduto produto,String operacao)
    {
        
        int opc = JOptionPane.showConfirmDialog(null,"Deseja mesmo "+operacao+" o Produto "+
                nome+" ?");
        
        if(opc ==JOptionPane.YES_OPTION)
        {
             Conecta_Banco conecta = new Conecta_Banco();
            //BUSCAMOS O ID PELO CÓDIGO DE BARRAS PRIMEIRO
            
            try 
            {          
                conecta.conexao();
                conecta.executaSQL("SELECT ID FROM PRODUTOS WHERE CD_BARRAS='"+
                produto.getCd_barras()+"';");
                conecta.rs.first();
                produto.setId(conecta.rs.getInt("ID"));

            } 
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null,"Erro ao Encontrar Id do Produto"
                +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            }
            
            //ATIVAMOS OU INATIVAMOS O PRODUTO;

            if(produto.getStatus().equals("A"))
            {
                operacao = "Ativado";
            }
            else
            {
                operacao = "Inativado";
            }
            
             try 
            {         
                
                
                PreparedStatement pst = conecta.conn.prepareStatement
                ("UPDATE PRODUTOS SET DS_STATUS='"+produto.getStatus()+"' WHERE ID="+produto.getId());   
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Produto "+operacao+" com Sucesso");

            } 
            catch (SQLException ex) {

              JOptionPane.showMessageDialog(null,"Erro ao "+operacao+" Produto"
               +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                conecta.desconecta();
            }
            
        }
    }
    
    
    public void gravaHistoricoProduto(ModeloProduto produto)
    {
        //System.out.println("ENTOU NO GRAVA HISTÒRICO");
         /*
            Gravamos o histórico do estoque deste produto
        */
        
        Conecta_Banco conecta = new Conecta_Banco();
        try 
        {            
             conecta.conexao();
             PreparedStatement pst = conecta.conn.prepareStatement(
             "INSERT INTO HISTORICO_ESTOQUE(ID,ID_PRODUTO,DS_MOVIMENTACAO,ID_USUARIO_MOVIMENTACAO,ID_USUARIO_RESPONSAVEL,DT_MOVIMENTACAO,"
             +"ESTOQUE_INICIAL,ESTOQUE_FINAL,DS_OBSERVACAO)\n" +
             "VALUES(DEFAULT,?,?,?,?,NOW(),?,?,?);");
             pst.setInt(1,produto.getId());  //produto pesquisado acima
             pst.setString(2,produto.getDs_movimentacao());
             pst.setInt(3,produto.getId_usuario());  
             pst.setInt(4,produto.getId_responsavel());     
             pst.setInt(5,produto.getEstoque_inicial());
             pst.setInt(6,produto.getEstoque_final());
             pst.setString(7,produto.getObservacao());
             pst.execute();                                                                                    
             //JOptionPane.showMessageDialog(null,"Estoque Atualizado com Sucesso");
        } 
        catch (SQLException ex) 
        {
             JOptionPane.showMessageDialog(null,"Erro ao Atualizar histórico do estoque do Produto:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
             System.out.println("ERRO: "+ex);
        }
        finally
        {
            conecta.desconecta();
        }
    }
    
    public void atualizaEstoqueProduto(ModeloProduto produto)
    {
        System.out.println("ATUALIZA ESTOQUE");
        //Alteramos o preço do produto pelo id encontrado acima
        Conecta_Banco conecta = new Conecta_Banco();
        conecta.conexao();
        try 
        {         
            PreparedStatement pst = conecta.conn.prepareStatement
            ("UPDATE ESTOQUE SET ID_USUARIO =?, ESTOQUE_ABAIXO=?, ESTOQUE=? WHERE ID_PRODUTO="+produto.getId());   
            pst.setInt(1,produto.getId_usuario());
            pst.setString(2,produto.getStatus_estoque());
            pst.setFloat(3,produto.getEstoque_final());           
            pst.executeUpdate();                                                                                   
            //JOptionPane.showMessageDialog(null,"Estoque Update com Sucesso");

        } 
        catch (SQLException ex) 
        {

          JOptionPane.showMessageDialog(null,"Erro ao Atualizar estoque do Produto:"+ ex,"Erro:",JOptionPane.WARNING_MESSAGE);
        }
        finally
        {
            conecta.desconecta();
        }
    }

    public void cancelaVenda(int numeroVenda) 
    {
        Conecta_Banco conecta = new Conecta_Banco();
        conecta.conexao();
        
        
        int id_produto[] = new int[2000];
        int quantidade[] = new int[2000];
        int i=0; //contador
        
        
        //BUSCO O QUE SERIA VENDIDO
        try 
        {                          
            conecta.executaSQL("SELECT ID_PRODUTO, DS_QUANTIDADE FROM vendas WHERE nr_venda="+numeroVenda);                                              
            while(conecta.rs.next())
            {
                
                id_produto[i] = conecta.rs.getInt("ID_PRODUTO");
                quantidade[i] = conecta.rs.getInt("DS_QUANTIDADE");
                //System.out.println("ID: "+id_produto[i]);
                
                i++;
            }

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Encontrar Id do Produto"
            +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
        }
         
        //BUSCO O ATUAL 
        
        
        int estoqueRecomendado[] = new int[2000];
        int estoqueAtual[] = new int[2000];
        
        for(int j=0;j<i;j++)
        {
            try 
            {
                String sql ="SELECT ID_PRODUTO, ESTOQUE, "
                + "PRD.DS_QUANTIDADE FROM ESTOQUE AS EST JOIN PRODUTOS "
                + "AS PRD ON PRD.ID = EST.ID_PRODUTO " +
                "WHERE ID_PRODUTO="+id_produto[j]+";" ;
                
                
                conecta.executaSQL(sql);
                conecta.rs.first();
                
                estoqueRecomendado[j] = conecta.rs.getInt("DS_QUANTIDADE");
                estoqueAtual[j] = conecta.rs.getInt("ESTOQUE");
 
              
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null,"Erro ao Encontrar estoque atual "
                + "do Produto: "
                +ex,"Erro:",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        //atualizo o estoque
         
        for(int k=0;k<i;k++)
        {         
            
            int estoqueFinal = estoqueAtual[k]+quantidade[k];
            
            //System.out.println("estoque final:"+estoqueFinal);
            
            String isAbaixo="N";
            
            if(estoqueFinal<estoqueRecomendado[k])
            {
                isAbaixo = "S";
            }
            
            
            try 
            {  
                String sqlUpdate = "UPDATE ESTOQUE SET ESTOQUE =?, ESTOQUE_ABAIXO=? WHERE ID_PRODUTO="+id_produto[k];
                
                PreparedStatement pst;
                pst = conecta.conn.prepareStatement
                (sqlUpdate);
                pst.setInt(1,estoqueFinal);
                pst.setString(2,isAbaixo);           
                pst.executeUpdate();     
                //System.out.println("ESTOQUE ATUALIZADO");
            } 
            catch (SQLException ex) {
                Logger.getLogger(ControleProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
        
    }
}

