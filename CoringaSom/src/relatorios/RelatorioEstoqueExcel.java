package relatorios;

import control.MetodosPadrao;
import dao.Conecta_Banco;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.write.*;


public class RelatorioEstoqueExcel {
    
    public void gerarRelatorioEstoque(String sql) throws WriteException
    {
        MetodosPadrao metodos = new MetodosPadrao();
        String nomeArquivo = "RELATORIO ESTOQUE";
           
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
                   aba= planilha2.createSheet("RELATORIO ESTOQUE",0);
                   int numeroLinha=0;
                        
                        jxl.format.Colour bckcolor = Colour.GREEN;
                        WritableCellFormat cellFormat = new WritableCellFormat();
                        cellFormat.setBackground(bckcolor);

                        // Cor e tipo de fonte
                        WritableFont fonte = new WritableFont(WritableFont.ARIAL);
                        fonte.setColour(Colour.WHITE);
                        fonte.setBoldStyle(WritableFont.BOLD);
                        cellFormat.setFont(fonte);
                        
                        String cabecalho[] = new String[7];
                        cabecalho[0] = "PRODUTO";
                        cabecalho[1] = "ESTOQUE";
                        cabecalho[2] = "ESTOQUE ABAIXO";
                        cabecalho[3] = "VALOR DE COMPRA DO PRODUTO";
                        cabecalho[4] = "TOTAL EM PRODUTOS DISPONÍVEIS";
                        cabecalho[5] = "VALOR DE VENDA DO PRODUTO";
                        cabecalho[6] = "TOTAL DISPONÍVEL PARA VENDA DO PRODUTO";
                        
                        for(int i=0; i<cabecalho.length; i++)
                        {
                            jxl.write.Label label = new jxl.write.Label(i, numeroLinha, cabecalho[i]);
                            aba.addCell(label);
                            WritableCell cell = aba.getWritableCell(i, numeroLinha);
                            cell.setCellFormat(cellFormat);
                        }
                        numeroLinha++;
                        try 
                        {
                           Conecta_Banco conecta = new Conecta_Banco();
                           conecta.conexao();
                           conecta.executaSQL
                           (sql);

                           while(conecta.rs.next())
                           {         
                                String isAbaixo = "NÃO";
                                
                                if(conecta.rs.getString("ESTOQUE_ABAIXO").equals("S"))
                                {
                                    isAbaixo = "SIM";
                                }
                                
                                
                                int estoque = conecta.rs.getInt("ESTOQUE");
                                float valorVenda=conecta.rs.getFloat("VL_VENDA");
                                float valorCompra=conecta.rs.getFloat("VL_COMPRA");

                                float totalVenda=0;
                                float totalCompra=0;

                                if(estoque>0)
                                {
                                    totalVenda = valorVenda*estoque;
                                    totalCompra = valorCompra*estoque;
                                }


                                String valorVendaFinal  = metodos.formataFloats(valorVenda);
                                String valorCompraFinal = metodos.formataFloats(valorCompra);
                                String totalVendaFinal  = metodos.formataFloats(totalVenda); 
                                String totalCompraFinal = metodos.formataFloats(totalCompra);
                                
                                
                                
                                
                                jxl.write.Label label = null;
                                label = new jxl.write.Label(0, numeroLinha,conecta.rs.getString("NM_PRODUTO"));
                                aba.addCell(label);
                                label = new jxl.write.Label(1, numeroLinha,conecta.rs.getInt("ESTOQUE")+"");
                                aba.addCell(label);                           
                                label = new jxl.write.Label(2, numeroLinha,isAbaixo);
                                aba.addCell(label);
                                
                                label = new jxl.write.Label(3, numeroLinha,valorCompraFinal);
                                aba.addCell(label);
                                
                                label = new jxl.write.Label(4, numeroLinha,totalCompraFinal);
                                aba.addCell(label);
                                
                                label = new jxl.write.Label(5, numeroLinha,valorVendaFinal);
                                aba.addCell(label);
                                
                                label = new jxl.write.Label(6, numeroLinha,totalVendaFinal);
                                aba.addCell(label);
                                 
                                numeroLinha++;
                           }
                        }
                        catch(SQLException e)
                        {
                            JOptionPane.showMessageDialog(null, "Erro ao verificar dados do relatório: "+e);
                        } 
                   
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
