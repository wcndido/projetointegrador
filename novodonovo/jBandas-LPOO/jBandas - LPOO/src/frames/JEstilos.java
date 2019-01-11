/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Professor
 */
public class JEstilos extends javax.swing.JFrame {

  model.jConexao conecta = new model.jConexao();
  
  public void PopularJTable(String sql) {
  try
  {
   com.mysql.jdbc.Connection con =  (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://" + conecta.servidor +  "/" + conecta.banco + "?user=" + conecta.usuario + "&password=" + conecta.senha);
   PreparedStatement banco = (PreparedStatement)con.prepareStatement(sql);
   banco.execute(); // cria o vetor
 
   ResultSet resultado = banco.executeQuery(sql);
 
   DefaultTableModel model =(DefaultTableModel) jTable1.getModel();
   model.setNumRows(0);
 
   
   jBtnExcluir.setEnabled(true);
   int cont = 0;
   
   while(resultado.next())
   {
       model.addRow(new Object[] 
       { 
          //retorna os dados da tabela do BD, cada campo em um coluna.
          resultado.getString("estilo_nome")
       }); 
       cont++;
  } 
   banco.close();
   con.close();
   
   // desabilita
   if (cont == 0) jBtnExcluir.setEnabled(false);
   
  }
 catch (SQLException ex)
 {
    System.out.println("o erro foi " +ex);
  }
 }
    
    
    /**
     * Creates new form JEstilos
     */
    public JEstilos() {
        initComponents();
        this.PopularJTable("SELECT estilo_nome FROM tb01_estilos ORDER BY estilo_nome ASC"); //ORDENA EM ORDEM ALFABÉTICA
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTxtEstilo = new javax.swing.JTextField();
        jBtnGravar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 59, 400, 10));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estilo"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 380, 190));

        jLabel1.setText("Digite a especialidade:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTxtEstilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtEstiloActionPerformed(evt);
            }
        });
        jTxtEstilo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtEstiloKeyTyped(evt);
            }
        });
        getContentPane().add(jTxtEstilo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 280, -1));

        jBtnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/009-add.png"))); // NOI18N
        jBtnGravar.setText("Gravar");
        jBtnGravar.setEnabled(false);
        jBtnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGravarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnGravar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 27, -1, -1));

        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/008-error.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGravarActionPerformed
        // TODO add your handling code here:
        conecta.ManipularDados("INSERT INTO tb01_estilos (estilo_nome) VALUES ('" + jTxtEstilo.getText() + "');");
        this.PopularJTable("SELECT estilo_nome FROM tb01_estilos ORDER BY estilo_nome ASC"); //ORDENA EM ORDEM ALFABÉTICA
        
        jTxtEstilo.setText("");       
        jTxtEstilo.grabFocus();
        jBtnGravar.setEnabled(false);
    }//GEN-LAST:event_jBtnGravarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
       int linha = jTable1.getSelectedRow(); // retorna a linha selecionada pelo usuario
       String EstiloSelecionado = jTable1.getValueAt(linha,0).toString(); // retorna o valor da celula linha X 0
       
       // Pergunta ao usuário se ele realmende deseja excluir;
       int resposta = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir o estilo " + EstiloSelecionado + "?");  
       if(resposta == JOptionPane.YES_OPTION) 
       {
           conecta.ManipularDados("DELETE FROM tb01_estilos WHERE estilo_nome = '" + EstiloSelecionado  + "'");
           // Atualiza a jTabel após a exclusão.
           this.PopularJTable("SELECT estilo_nome FROM tb01_estilos ORDER BY estilo_nome ASC"); 
       }

    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jTxtEstiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtEstiloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtEstiloActionPerformed

    private void jTxtEstiloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtEstiloKeyTyped
        
        if (!"".equals(jTxtEstilo.getText()))
        {
            jBtnGravar.setEnabled(true);
        }
        else
        {
            jBtnGravar.setEnabled(false);
        }
        
    }//GEN-LAST:event_jTxtEstiloKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JEstilos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JEstilos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JEstilos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JEstilos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JEstilos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnGravar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTxtEstilo;
    // End of variables declaration//GEN-END:variables
}
