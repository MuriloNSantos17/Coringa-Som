package relatorios;

import dao.Conecta_Banco;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.write.Colour;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class RelatorioVendasExcel {
    
    public void gerarRelatorioEstoque(String sql) throws WriteException
    {
        String nomeArquivo = "RELATORIO VENDAS";
           
        JFileChooser fc = new JFileChooser();
        File diretorio = fc.getSelectedFile();
        // restringe a amostra a diretorios apenas
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = fc.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION)
        {
             diretorio = fc.getSelectedFile();    
               //System.out.println("Diretório: "+diretorio);
               try 
               {    
                   WritableSheet aba = null;
                   String nomeFile = diretorio+"/"+nomeArquivo+".xls";             
                   WritableWorkbook planilha2;
                   planilha2 = Workbook.createWorkbook(new File(nomeFile));
                   aba= planilha2.createSheet("RELATORIO VENDAS",0);
                   int numeroLinha=0;
                        
                        jxl.format.Colour bckcolor = Colour.GREEN;
                        WritableCellFormat cellFormat = new WritableCellFormat();
                        cellFormat.setBackground(bckcolor);

                        // Cor e tipo de fonte
                        WritableFont fonte = new WritableFont(WritableFont.ARIAL);
                        fonte.setColour(Colour.WHITE);
                        fonte.setBoldStyle(WritableFont.BOLD);
                        cellFormat.setFont(fonte);
                        
                        String cabecalho[] = new String[10];
                        cabecalho[0] = "NÚMERO VENDA";
                        cabecalho[1] = "NOME DO PRODUTO";
                        cabecalho[2] = "QUANTIDADE";
                        cabecalho[3] = "VALOR UNITÁRIO";
                        cabecalho[4] = "VALOR DESCONTO";
                        cabecalho[5] = "TOTAL";
                        cabecalho[6] = "VENDEDOR";
                        cabecalho[7] = "APROVADOR";
                        cabecalho[8] = "STATUS DA VENDA";
                        cabecalho[9] = "DATA VENDA";
                       
                        
                        for(int i=0; i<cabecalho.length; i++)
                        {
                            jxl.write.Label label = new jxl.write.Label(i, numeroLinha, cabecalho[i]);
                            aba.addCell(label);
                            WritableCell cell = aba.getWritableCell(i, numeroLinha);
                            cell.setCellFormat(cellFormat);
                        }
                        numeroLinha++;
                        float total=0;
                        jxl.write.Label label = null;
                        try 
                        {
                           Conecta_Banco conecta = new Conecta_Banco();
                           conecta.conexao();
                           conecta.executaSQL
                           (sql);
                            System.out.println("SQL PESQUISA: "+sql);
                           while(conecta.rs.next())
                           {         
                                String statusAprovacao = conecta.rs.getString("DS_STATUS_VENDA");
               
                                if(statusAprovacao.equals("A"))
                                {
                                    statusAprovacao = "APROVADA";
                                }
                                else if(statusAprovacao.equals("D"))
                                {
                                    statusAprovacao="DIRETA";
                                }
                                else if(statusAprovacao.equals("C"))
                                {
                                    statusAprovacao="CANCELADA";
                                }
                                else
                                {
                                    statusAprovacao = "PENDENTE";
                                }
                                
                                
                                label = new jxl.write.Label(0, numeroLinha,conecta.rs.getInt("NR_VENDA")+"");
                                aba.addCell(label);
                                label = new jxl.write.Label(1, numeroLinha,conecta.rs.getString("NM_PRODUTO"));
                                aba.addCell(label);                           
                                label = new jxl.write.Label(2, numeroLinha,conecta.rs.getInt("DS_QUANTIDADE")+"");
                                aba.addCell(label);  
                                label = new jxl.write.Label(3, numeroLinha,conecta.rs.getFloat("VL_UNITARIO")+"");
                                aba.addCell(label);
                                label = new jxl.write.Label(4, numeroLinha,conecta.rs.getFloat("VL_DESCONTO")+"");
                                aba.addCell(label);
                                label = new jxl.write.Label(5, numeroLinha,conecta.rs.getFloat("TOTAL")+"");
                                aba.addCell(label);
                                total = total+conecta.rs.getFloat("TOTAL");
                                label = new jxl.write.Label(6, numeroLinha,conecta.rs.getString("NM_USUARIO"));
                                aba.addCell(label);   
                                label = new jxl.write.Label(7, numeroLinha,conecta.rs.getString("NM_USUARIO_APROVADOR"));
                                aba.addCell(label);   
                                label = new jxl.write.Label(8, numeroLinha,statusAprovacao);
                                aba.addCell(label);
                                label = new jxl.write.Label(9, numeroLinha,conecta.rs.getString("DT_VENDA"));
                                aba.addCell(label);   
                                numeroLinha++;
                           }
                        }
                        catch(SQLException e)
                        {
                            JOptionPane.showMessageDialog(null, "Erro ao verificar dados do relatório: "+e);
                        } 
                   
                        label = new jxl.write.Label(4, numeroLinha,"TOTAL:");
                        aba.addCell(label);
                        WritableCell cell = aba.getWritableCell(4, numeroLinha);
                        cell.setCellFormat(cellFormat);
                        label = new jxl.write.Label(5, numeroLinha,total+"");
                        aba.addCell(label);  
                        cell = aba.getWritableCell(5, numeroLinha);
                        cell.setCellFormat(cellFormat);
                        planilha2.write();
                           // Fecha o arquivo
                        planilha2.close();
                        JOptionPane.showMessageDialog(null,"Relatório Gerado com Sucesso!");
                        
                       String dir = diretorio+"/"+ nomeArquivo+".xls";
                        try 
                        {

                           Desktop dks = Desktop.getDesktop();
                           dks.open(new File(dir));
                           //this.dispose();
                        } 
                        catch (IOException ex) 
                        {
                            JOptionPane.showMessageDialog(null,"Erro ao abrir arquivo: "+ex);
                        }
                      }
                      catch (IOException ex) {
                          JOptionPane.showMessageDialog(null,"ERRO ao gerar Relatório: "+ex);
                      }
        }
    }
}
