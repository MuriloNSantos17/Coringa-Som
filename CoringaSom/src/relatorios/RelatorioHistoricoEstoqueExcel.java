package relatorios;

import dao.Conecta_Banco;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.write.*;


public class RelatorioHistoricoEstoqueExcel {
     public void gerarRelatorioHistoricoEstoque(String sql) throws WriteException
    {
        String nomeArquivo = "RELATORIO HISTORICO ESTOQUE";
           
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
                   aba= planilha2.createSheet("RELATORIO HISTORICO ESTOQUE",0);
                   int numeroLinha=0;
                        
                        jxl.format.Colour bckcolor = Colour.GREEN;
                        WritableCellFormat cellFormat = new WritableCellFormat();
                        cellFormat.setBackground(bckcolor);

                        // Cor e tipo de fonte
                        WritableFont fonte = new WritableFont(WritableFont.ARIAL);
                        fonte.setColour(Colour.WHITE);
                        fonte.setBoldStyle(WritableFont.BOLD);
                        cellFormat.setFont(fonte);
                        
                        String cabecalho[] = new String[8];
                        cabecalho[0] = "PRODUTO";
                        cabecalho[1] = "MOVIMENTAÇÃO";
                        cabecalho[2] = "RESPONSÁVEL";
                        cabecalho[3] = "USUÁRIO MOVIMENTAÇÃO";
                        cabecalho[4] = "DATA DA MOVIMENTAÇÃO";
                        cabecalho[5] = "ESTOQUE INICIAL";
                        cabecalho[6] = "ESTOQUE FINAL";
                        cabecalho[7] = "OBSERVAÇÃO";
                       
                        
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
                                 String movimentacao = conecta.rs.getString("DS_MOVIMENTACAO");
               
                                if(movimentacao.equals("E"))
                                {
                                    movimentacao = "ENTRADA";
                                }
                                else if(movimentacao.equals("V"))
                                {
                                    movimentacao = "VENDA";
                                }
                                else if(movimentacao.equals("B"))
                                {
                                    movimentacao = "BAIXA";
                                }
                                 else if(movimentacao.equals("S"))
                                {
                                    movimentacao = "SAÍDA";
                                }
                                else
                                {
                                    movimentacao = "CADASTRO";
                                }
                                
                                jxl.write.Label label = null;
                                label = new jxl.write.Label(0, numeroLinha,conecta.rs.getString("NM_PRODUTO"));
                                aba.addCell(label);
                                label = new jxl.write.Label(1, numeroLinha,movimentacao);
                                aba.addCell(label);                           
                                label = new jxl.write.Label(2, numeroLinha,conecta.rs.getString("NM_RESPONSAVEL"));
                                aba.addCell(label);
                                label = new jxl.write.Label(3, numeroLinha,conecta.rs.getString("NM_USUARIO_MOVIMENTACAO"));
                                aba.addCell(label);
                                label = new jxl.write.Label(4, numeroLinha,conecta.rs.getString("DT_MOVIMENTACAO"));
                                aba.addCell(label);
                                label = new jxl.write.Label(5, numeroLinha,conecta.rs.getInt("ESTOQUE_INICIAL")+"");
                                aba.addCell(label);
                                label = new jxl.write.Label(6, numeroLinha,conecta.rs.getInt("ESTOQUE_FINAL")+"");
                                aba.addCell(label);
                                label = new jxl.write.Label(7, numeroLinha,conecta.rs.getString("DS_OBSERVACAO"));
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
