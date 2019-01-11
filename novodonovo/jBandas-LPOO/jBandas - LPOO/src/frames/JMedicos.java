/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aluno
 */
public class JMedicos extends javax.swing.JFrame {

    public JMedicos() {
        initComponents();
        this.DesabilitaCampos();
        this.PopularCombo();
        this.PopularJTable("select"
                + " nome_medico, estilo_nome, crm "
                + " FROM "
                + " medicos "
                + " JOIN "
                + " tb01_estilos"
                + " WHERE"
                + " especialidade = estilo_cod;");
    }

    public int editar = 0; //0 para não editar e 1 para editar.

    model.jConexao conecta = new model.jConexao();
    int vetor[]; // inicia um vetor.

    public void DesabilitaCampos() {
        // Necessário quando o usuário clica em CANCELAR ou SALVAR
        jTblDados.setEnabled(true);
        jTxtPesquisar.setEditable(true);
        jTxtBanda.setEnabled(false);
        jCboEstilo.setEnabled(false);
        jTxtAno.setEnabled(false);
        jBtnCancelar.setEnabled(false);
        jBtnSalvar.setEnabled(false);
        jBtnNovo.setEnabled(true);
        jBtnEditar.setEnabled(true);
        jBtnExcluir.setEnabled(true);
        this.VerificaSelecionado();
    }

    public void HabilitaCampos() {
        // Necessário quando o usuário clica em NOVO ou EDITAR
        jTblDados.setEnabled(false);
        jTxtPesquisar.setEditable(false);
        jTxtBanda.setEnabled(true);
        jCboEstilo.setEnabled(true);
        jTxtAno.setEnabled(true);
        jBtnCancelar.setEnabled(true);
        jBtnSalvar.setEnabled(true);
        jBtnNovo.setEnabled(false);
        jBtnEditar.setEnabled(false);
        jBtnExcluir.setEnabled(false);
        this.VerificaSelecionado();
    }

    public void CarregaDadosList() {
        int linha = jTblDados.getSelectedRow(); // retorna a linha selecionada pelo usuario
        jTxtBanda.setText(jTblDados.getValueAt(linha, 0).toString()); // retorna o valor da celula linha X 0
        jCboEstilo.setSelectedItem(jTblDados.getValueAt(linha, 1).toString());
        jTxtAno.setText(jTblDados.getValueAt(linha, 2).toString()); // retorna o valor da celula linha X 2
        this.VerificaSelecionado();
    }

    public void VerificaSelecionado() {
        /*
          Este método verifica se alguma banda está selecionada no jTable, caso contrário
          os botões de Editar e Excluir ficarão desabilitados.
         */
        int linha = jTblDados.getSelectedRow(); // retorna a linha selecionada pelo usuario
        if (linha == -1) {// o -1 indica que não há seleção na jTable
            jBtnExcluir.setEnabled(false);
            jBtnEditar.setEnabled(false);
        } else {
            jBtnExcluir.setEnabled(true);
            jBtnEditar.setEnabled(true);
        }
    }

    // Código para popular um jTable
    public void PopularJTable(String sql) {
        System.out.println(sql);
        try {
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://" + conecta.servidor + "/" + conecta.banco + "?user=" + conecta.usuario + "&password=" + conecta.senha);
            PreparedStatement banco = (PreparedStatement) con.prepareStatement(sql);
            banco.execute(); // cria o vetor que contêm os dados da tabela do BD.

            ResultSet resultado = banco.executeQuery(sql); // executa o SELECT

            DefaultTableModel model = (DefaultTableModel) jTblDados.getModel();
            model.setNumRows(0);

            int cont = 0;

            while (resultado.next()) {
                // Cód. para retornar apenas o Ano.
                String data = resultado.getString("crm");
                String[] ano = data.split("-");

                // Popula o jList com os dados do vetor 
                model.addRow(new Object[]{
                    //retorna os dados da tabela do BD, cada campo em um coluna.
                    resultado.getString("nome_medico"),
                    resultado.getString("estilo_nome"),
                    ano[0]});
                cont++;
            }
            
            jTxtTotalRegistros.setText(cont + " bandas");
            VerificaSelecionado();

            banco.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("o erro foi " + ex);
        }
    }

    // Código para popular um ComboBox
    public void PopularCombo() {

        int ponteiro = 0;
        vetor = new int[99]; // ocupa 30 posicoes na memoria.

        try {
            String sql = "Select * from tb01_estilos ORDER BY estilo_nome";
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://" + conecta.servidor + "/" + conecta.banco + "?user=" + conecta.usuario + "&password=" + conecta.senha);
            PreparedStatement banco = (PreparedStatement) con.prepareStatement(sql);
            banco.execute();

            ResultSet resultado = banco.executeQuery(sql);

            while (resultado.next()) {
                vetor[ponteiro] = Integer.parseInt(resultado.getString("estilo_cod"));
                jCboEstilo.addItem(resultado.getString("estilo_nome"));
                ponteiro++;
            }

            banco.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("o erro foi " + ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jBtnNovo = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnSalvar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jPanelDados = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCboEstilo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTxtAno = new javax.swing.JTextField();
        jTxtBanda = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTxtPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblDados = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTxtTotalRegistros = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/009-add.png"))); // NOI18N
        jBtnNovo.setText("Novo");
        jBtnNovo.setActionCommand("jBtnNovo");
        jBtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNovoActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/008-error.png"))); // NOI18N
        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.setActionCommand("jBtnCancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 13, -1, -1));

        jBtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/save.png"))); // NOI18N
        jBtnSalvar.setText("Salvar");
        jBtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalvarActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 13, -1, -1));

        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/delete.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 13, -1, -1));

        jBtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/007-edit.png"))); // NOI18N
        jBtnEditar.setText("Editar");
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 13, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 50));

        jPanelDados.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jPanelDados.setEnabled(false);
        jPanelDados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nome do Médico:");
        jPanelDados.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 17, -1, -1));

        jLabel2.setText("Especialidade:");
        jPanelDados.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jCboEstilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboEstiloActionPerformed(evt);
            }
        });
        jPanelDados.add(jCboEstilo, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 50, 130, -1));

        jLabel3.setText("CRM:");
        jPanelDados.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        jTxtAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtAnoActionPerformed(evt);
            }
        });
        jTxtAno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtAnoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtAnoKeyTyped(evt);
            }
        });
        jPanelDados.add(jTxtAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 70, 20));

        jTxtBanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtBandaActionPerformed(evt);
            }
        });
        jPanelDados.add(jTxtBanda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 350, -1));

        getContentPane().add(jPanelDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 510, 90));

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Pesquisar Médico:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, 13));

        jTxtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtPesquisarActionPerformed(evt);
            }
        });
        jTxtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtPesquisarKeyPressed(evt);
            }
        });
        jPanel4.add(jTxtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 14, 214, -1));

        jTblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Médico", "Especialidade", "CRM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblDadosMouseClicked(evt);
            }
        });
        jTblDados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblDadosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTblDadosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTblDadosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTblDados);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 40, 487, 193));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/research.png"))); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 14, -1, -1));

        jTxtTotalRegistros.setText("0 bandas");
        jPanel4.add(jTxtTotalRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 510, 240));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCboEstiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboEstiloActionPerformed
        // TODO add your handling code here:
        System.out.println("Selecionou o item " + jCboEstilo.getSelectedIndex());
        System.out.println("O CÓDIGO correspondente é: " + vetor[jCboEstilo.getSelectedIndex()]);
    }//GEN-LAST:event_jCboEstiloActionPerformed

    private void jBtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalvarActionPerformed

        if ("".equals(jTxtBanda.getText())) {
            JOptionPane.showMessageDialog(null, "Informe o nome da banda");
            jTxtBanda.grabFocus();
            return; //Encerra o código
        }
        if ("".equals(jTxtAno.getText())) {
            JOptionPane.showMessageDialog(null, "Informe o ano da banda");
            jTxtAno.grabFocus();
            return; //Encerra o código
        }

        // Código para aceitar apenas 4 digitos
        if (jTxtAno.getText().length() != 4) {
            JOptionPane.showMessageDialog(null, "O ano deve possui  4 dígitos", "Informação", JOptionPane.INFORMATION_MESSAGE);
            jTxtAno.grabFocus();
            return; //Encerra o código
        }

        String ComandoSQL = "";
        if (editar == 1) {
            // UPDATE NOME_DA_TABELA SET campo1 = valor1, campo2 = valor2.
            ComandoSQL = "UPDATE medicos SET "
                    + "nome_medico='" + jTxtBanda.getText() + "',"
                    + "especialidade='" + vetor[jCboEstilo.getSelectedIndex()] + "',"
                    + "crm='" + jTxtAno.getText() + "';";
            System.out.println(ComandoSQL);
        } else {
            ComandoSQL = "INSERT INTO medicos "
                    + "(nome_medico, especialidade, crm)"
                    + "VALUES ('"
                    + jTxtBanda.getText()
                    + "', "
                    + vetor[jCboEstilo.getSelectedIndex()]
                    + ","
                    + jTxtAno.getText()
                    + ")";
        }

        System.out.println(ComandoSQL); // testa comando SQL;
        conecta.ManipularDados(ComandoSQL);
        PopularJTable("select"
                + " nome_medico, estilo_nome, crm "
                + " FROM "
                + " medicos "
                + " JOIN "
                + " tb01_estilos"
                + " WHERE"
                + " especialidade = estilo_cod;");
        JOptionPane.showMessageDialog(null, jTxtBanda.getText() + " gravado com sucesso!");
        this.DesabilitaCampos();
    }//GEN-LAST:event_jBtnSalvarActionPerformed

    private void jTxtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtPesquisarActionPerformed

    }//GEN-LAST:event_jTxtPesquisarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
        int linha = jTblDados.getSelectedRow(); // retorna a linha selecionada pelo usuario
        String BandaSelecionada = jTblDados.getValueAt(linha, 0).toString(); // retorna o valor da celula linha X 0

        // Pergunta ao usuário se ele realmende deseja excluir;
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a banda " + BandaSelecionada + "?");
        if (resposta == JOptionPane.YES_OPTION) {
            conecta.ManipularDados("DELETE FROM medicos WHERE nome_medico = '" + BandaSelecionada + "'");
            // Atualiza a jTabel após a exclusão.
            this.PopularJTable("select"
                    + " nome_medico, estilo_nome, crm "
                    + " FROM "
                    + " medicos "
                    + " JOIN "
                    + " tb01_estilos"
                    + " WHERE"
                    + " especialidade = estilo_cod;");
        }
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jTxtBandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtBandaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtBandaActionPerformed

    private void jBtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNovoActionPerformed
        this.HabilitaCampos();
        jTxtBanda.grabFocus();
        editar = 0;
        jTxtBanda.setText("");
        jTxtAno.setText("");
        jTxtBanda.grabFocus();
    }//GEN-LAST:event_jBtnNovoActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        this.HabilitaCampos();
        jTxtBanda.grabFocus();
        editar = 1;
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
        DesabilitaCampos();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jTblDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblDadosMouseClicked
        this.CarregaDadosList();// TODO add your handling code here:
    }//GEN-LAST:event_jTblDadosMouseClicked

    private void jTblDadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblDadosKeyPressed

    }//GEN-LAST:event_jTblDadosKeyPressed

    private void jTblDadosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblDadosKeyTyped

    }//GEN-LAST:event_jTblDadosKeyTyped

    private void jTblDadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblDadosKeyReleased
        this.CarregaDadosList();
    }//GEN-LAST:event_jTblDadosKeyReleased

    private void jTxtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPesquisarKeyPressed
        PopularJTable("select"
                + " nome_medico, estilo_nome, crm "
                + " FROM "
                + " medicos "
                + " JOIN "
                + " tb01_estilos"
                + " WHERE"
                + " especialidade = estilo_cod"
                + " AND nome_medico LIKE '%" + jTxtPesquisar.getText() + "%'");
    }//GEN-LAST:event_jTxtPesquisarKeyPressed

    private void jTxtAnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtAnoKeyReleased
        // Código que verifica se o texto digitado é um número.
        long valor;
        if (jTxtAno.getText().length() != 0) {
            try {
                valor = Long.parseLong(jTxtAno.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Esse Campo aceita apenas números", "Informação", JOptionPane.INFORMATION_MESSAGE);
                jTxtAno.setText("");
                jTxtAno.grabFocus();
            }
        }
        
        // Código para aceitar apenas 4 digitos
        if (jTxtAno.getText().length() > 4) {
            JOptionPane.showMessageDialog(null, "O ano deve possuir apenas 4 dígitos", "Informação", JOptionPane.INFORMATION_MESSAGE);
            jTxtAno.setText("");
            jTxtAno.grabFocus();
        }
    }//GEN-LAST:event_jTxtAnoKeyReleased

    private void jTxtAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtAnoActionPerformed

    private void jTxtAnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtAnoKeyTyped


    }//GEN-LAST:event_jTxtAnoKeyTyped

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
            java.util.logging.Logger.getLogger(JMedicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JMedicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JMedicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JMedicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JMedicos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnNovo;
    private javax.swing.JButton jBtnSalvar;
    private javax.swing.JComboBox<String> jCboEstilo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblDados;
    private javax.swing.JTextField jTxtAno;
    private javax.swing.JTextField jTxtBanda;
    private javax.swing.JTextField jTxtPesquisar;
    private javax.swing.JLabel jTxtTotalRegistros;
    // End of variables declaration//GEN-END:variables
}
