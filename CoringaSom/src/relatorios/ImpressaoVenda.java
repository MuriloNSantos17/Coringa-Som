/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import control.MetodosPadrao;
import dao.Conecta_Banco;
import java.awt.Desktop;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Murilo Santos
 */
public class ImpressaoVenda {
    
    public void imprimeVenda(String nrVenda)
    {
        
        Conecta_Banco conecta = new Conecta_Banco();
        
        
        String diretorio="";
        
        
        MetodosPadrao metodos = new MetodosPadrao();
        
        String data = "25/12/1998";
        String atendente = "Fabricio Krulinkowski";
        String desconto = "10,00";
        
        String descricao="";
        String total ="";
        String totalFinal="";
        
        
         conecta.conexao();
        
        String sql = "SELECT DIRETORIO FROM DIRETORIOS";
        
        
        try  
        {   
            
            conecta.executaSQL(sql);
            conecta.rs.first();
            diretorio = conecta.rs.getString("DIRETORIO");
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Encontrar diretório: \n"
            +ex,"Erro:",JOptionPane.ERROR_MESSAGE);

        }
        
        
        
        
        sql = "SELECT NR_VENDA, PRD.NM_PRODUTO, VD.DS_QUANTIDADE, VD.TOTAL, \n" +
        "US.NM_USUARIO, AP.NM_USUARIO AS NM_USUARIO_APROVADOR,  DATE_FORMAT(VD.DT_VENDA,'%d/%m/%Y')AS DT_VENDA,\n" +
        "DATE_FORMAT(now(),'%d/%m/%Y') AS DT_ATUAL\n" +
        "FROM VENDAS AS VD JOIN PRODUTOS AS \n" +
        "PRD ON PRD.ID=VD.ID_PRODUTO \n" +
        "JOIN USUARIOS AS US ON US.ID = VD.ID_USUARIO_VENDA JOIN USUARIOS AS AP ON AP.ID = VD.ID_USUARIO_APROVADOR \n" +
        "WHERE NR_VENDA="+nrVenda+";";
        
       
        try  
        {   
            
            conecta.executaSQL(sql);
             
             
            while(conecta.rs.next())
            {
                total = metodos.formataFloats(conecta.rs.getFloat("TOTAL"));

                descricao = descricao+"PRODUTO: "+ conecta.rs.getString("NM_PRODUTO") +"\n"+
                "QUANTIDADE: "+conecta.rs.getString("DS_QUANTIDADE")+"\n"+
                "TOTAL: R$"+total+"\n\n";
                
                atendente = conecta.rs.getString("NM_USUARIO");
                data = conecta.rs.getString("DT_ATUAL");
            }

        } 
        catch (SQLException ex) 
        {
           JOptionPane.showMessageDialog(null,"Erro ao Encontrar dados da venda:\n"
            +ex,"Erro:",JOptionPane.ERROR_MESSAGE);

        }
        
        sql = "SELECT NR_VENDA, SUM(TOTAL) AS TOTAL, VL_DESCONTO, (SUM(TOTAL)-VL_DESCONTO) AS VALOR_FINAL\n" +
        "FROM VENDAS AS VD JOIN USUARIOS AS USER ON USER.ID =\n" +
        "VD.ID_USUARIO_VENDA WHERE NR_VENDA="+nrVenda+" GROUP BY NR_VENDA";
        
        try  
        {   
            
            conecta.executaSQL(sql);
            conecta.rs.first();
            desconto = metodos.formataFloats(conecta.rs.getFloat("VL_DESCONTO"));
            total = metodos.formataFloats(conecta.rs.getFloat("TOTAL"));
            totalFinal = metodos.formataFloats(conecta.rs.getFloat("VALOR_FINAL"));
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null,"Erro ao Encontrar demais dados da venda:\n"
            +ex,"Erro:",JOptionPane.ERROR_MESSAGE);

        }
        finally{
            conecta.desconecta();
        }
        
        
        
        
        System.out.println(descricao);
        
        System.out.println(diretorio);
        
        FileWriter arquivo;
	
	try {
		arquivo = new FileWriter(new File(diretorio));
		arquivo.write("    CORINGA SOM\n");
                arquivo.write("  R. FERNANDINA\n");
                arquivo.write(" MARQUES Nº 252\n");
                arquivo.write(" BORDA DO CAMPO\n");
                arquivo.write("-----------------\n");
                arquivo.write("DATA: "+data+"\n");
                arquivo.write("Nº VENDA: "+nrVenda+"\n");
                arquivo.write("-----------------\n");
                arquivo.write("    ATENDENTE\n");
                arquivo.write(atendente.toUpperCase()+"\n");
                arquivo.write("-----------------\n");
                arquivo.write(descricao);
                arquivo.write("TOTAL:\n"+total+" R$\n");
                arquivo.write("-----------------\n");
                arquivo.write("DESCONTO:\n"+desconto+" R$\n");
                arquivo.write("-----------------\n");
                arquivo.write("VALOR FINAL: "+totalFinal+" R$");
                
                Desktop desktop = Desktop.getDesktop();
        
                try {
                    File arquivoImprimir = new File(diretorio);
                    desktop.print(arquivoImprimir);
                } catch (Exception e) {
                    System.out.println("ERRO: "+e);
                }
                
                
		arquivo.close();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	

        
    }
}
