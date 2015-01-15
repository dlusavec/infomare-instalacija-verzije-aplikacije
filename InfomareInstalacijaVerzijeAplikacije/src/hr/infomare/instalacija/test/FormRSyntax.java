
package hr.infomare.instalacija.test;

import java.awt.Frame;

import org.apache.commons.lang3.StringUtils;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;
import org.fife.ui.rtextarea.SearchResult;

/**
 *
 * @author damir
 */
public class FormRSyntax extends javax.swing.JFrame {
    RSyntaxTextArea editor = new RSyntaxTextArea(20, 60);
    RTextScrollPane sp;
    String sql =
        "/*Proca*/create or replace PROCEDURE sp_HR_PH110_31028\n" + "(\n" + "  p_Uprava IN CHAR DEFAULT NULL ,\n" +
        "  p_ID_sesion IN NUMBER DEFAULT NULL ,\n" + "  p_Aktivnost IN CHAR DEFAULT NULL\n" + ")\n" + "AS\n" +
        "  v_ACT VARCHAR2(1);\n" + "BEGIN\n" + "v_ACT := COALESCE(p_Aktivnost,' ');\n" + "   DELETE TT_HR_PH110\n" +
        "\n" + "      WHERE SessionId = p_ID_sesion;\n" + "\n" + "   INSERT INTO TT_HR_PH110\n" +
        "     ( SessionId, Uprava, SIFRA, PREZIME, IME, RODITELJ, OIB, SPOL, D_RODJ, \n" +
        "                  AKTIVNOST, BRRJ, DRZ, DRZ_NAZIV, RDOZ, ADRESA, PTT, MJESTO, H81H80, \n" +
        "                  H80NAZ, H81VSS, H81SSP, K21DAD, K21DVO, K21DVD, K21RMJ, H25NAZ, K21VRO, \n" +
        "                  H10NAZ, K21PRO, K21OVJ, K21VDD, K21MJR, K21BEN, K17NAZ, K21DRV, K14NAZ, K21RPR, \n" +
        "                  H12NAZ, K21ZAB, H04ESD )\n" + "     SELECT p_ID_sesion,\n" + "            K01UPR,\n" +
        "            K01CTR,\n" + "            K01PRE,\n" + "            K01IME,\n" + "            K01IRO,\n" +
        "            K01OIB,\n" + "            K01SPO,\n" + "            K01DRO,\n" + "            K01ACT,\n" +
        "            K01BRJ,\n" + "            H03DRZ,\n" + "            H09NAZ,\n" + "            H03RDO,\n" +
        "            K01ADR,\n" + "            K01PTT,\n" + "            K01MST,\n" + "            x.H81H80,\n" +
        "            x.H80NAZ,\n" + "            x.H81VSS,\n" + "            x.H81SSP,\n" + "            K21DAD,\n" +
        "            K21DVO,\n" + "            K21DVD,\n" + "            K21RMJ,\n" + "            ( SELECT H25NAZ\n" +
        "              FROM FH025\n" + "                 WHERE H25CTR = K21RMJ ) H25NAZ,\n" + "            K21VRO,\n" +
        "            ( SELECT H10NAZ\n" + "              FROM FH010\n" +
        "                 WHERE H10CTR = K21VRO ) H10NAZ,\n" + "            K21PRO,\n" + "            K21OVJ,\n" +
        "            K21VDD,\n" + "            K21MJR,\n" + "            K21BEN,\n" + "            ( SELECT K17NAZ\n" +
        "              FROM FK017\n" + "                 WHERE K17CTR = K21BEN ) K17NAZ,\n" + "            K21DRV,\n" +
        "            ( SELECT K14NAZ\n" + "              FROM FK014\n" +
        "                 WHERE K14CTR = K21DRV ) K14NAZ,\n" + "            K21RPR,\n" +
        "            ( SELECT H12NAZ\n" + "              FROM FH012\n" +
        "                 WHERE H12CTR = K21RPR ) H12NAZ,\n" + "            K21ZAB,\n" + "            Y.H04ESD\n" +
        "             FROM FK001\n" + "              LEFT JOIN FK021\n" + "               ON K21CTR = K01BRJ\n" +
        "              LEFT JOIN FH003\n" + "               ON H03RAD = K01CTR\n" +
        "              LEFT JOIN ( SELECT a.H04RAD,\n" + "                                 a.H04ESD\n" +
        "                          FROM FH004 a\n" +
        "                             WHERE a.H04DVA = ( SELECT MAX(b.H04DVA)\n" +
        "                                                FROM FH004 b\n" +
        "                                                   WHERE b.H04RAD = a.H04RAD\n" +
        "                                                           AND b.H04ACT = 'A' ) ) Y\n" +
        "               ON Y.H04RAD = K01CTR\n" + "              LEFT JOIN ( SELECT a.H81K01 H81K01,\n" +
        "                                 a.H81TIP H81TIP,\n" + "                                 a.H81H80 H81H80,\n" +
        "                                 H80NAZ H80NAZ,\n" + "                                 a.H81VSS H81VSS,\n" +
        "                                 a.H81SSP H81SSP\n" + "                          FROM FH081 a,\n" +
        "                               FH080\n" +
        "                             WHERE a.H81REC = ( SELECT MAX(b.H81REC)\n" +
        "                                                FROM FH081 b\n" +
        "                                                   WHERE b.h81K01 = a.h81K01\n" +
        "                                                           AND b.H81TIP = 'O'\n" +
        "                                                           AND H81ACT = 'A' )\n" +
        "                                     AND H80CTR = a.H81H80\n" +
        "                                     AND H80TIP = a.H81TIP ) x\n" + "               ON x.H81K01 = K01CTR,\n" +
        "            FH009\n" + "        WHERE K01UPR = p_Uprava\n" + "                AND H09DRZ = H03DRZ\n" +
        "                AND ( ( K01ACT = v_ACT\n" + "                AND v_ACT <> ' ' )\n" +
        "                OR v_ACT = ' ' )\n" + ";\n" + "\n" + "END;\n" + " ";

    /** Creates new form FormRSyntax */
    public FormRSyntax() {
        initComponents();
        editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        editor.setCodeFoldingEnabled(true);
        editor.setAntiAliasingEnabled(true);

        sp = new RTextScrollPane(editor);
        sp.setFoldIndicatorEnabled(true);
        sp.setIconRowHeaderEnabled(true);
        jPanel1.add(sp);
        editor.setText(sql);
        editor.setFadeCurrentLineHighlight(true);
        editor.setMarkOccurrences(true);
        //  textArea.setEOLMarkersVisible(true);
        editor.setCaretPosition(0);
        editor.requestFocus();
        setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jtfTrazi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Traži");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtfTrazi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfTraziFocusGained(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Traži");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(566, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jtfTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        SearchContext trazilica = new SearchContext();
        String tekst = jtfTrazi.getText().trim();
        if (StringUtils.isBlank(tekst))
            return;
        trazilica.setSearchFor(tekst);
        trazilica.setMatchCase(false);
        trazilica.setRegularExpression(false);
        trazilica.setSearchForward(true);
        trazilica.setWholeWord(false);
        SearchResult sr = SearchEngine.find(editor, trazilica);
        if (!sr.wasFound()) {
            editor.setCaretPosition(0);
            SearchEngine.find(editor, trazilica);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtfTraziFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfTraziFocusGained
        // TODO add your handling code here:
        jtfTrazi.selectAll();
    }//GEN-LAST:event_jtfTraziFocusGained

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
            java.util.logging.Logger.getLogger(FormRSyntax.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRSyntax.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRSyntax.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRSyntax.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRSyntax().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jtfTrazi;
    // End of variables declaration//GEN-END:variables

}
