
package hr.infomare.instalacija.test;

import java.awt.Frame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;

/**
 *
 * @author damir
 */
public class FormRSyntax extends javax.swing.JFrame {
    RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
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
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        textArea.setCodeFoldingEnabled(true);
        textArea.setAntiAliasingEnabled(true);

        sp = new RTextScrollPane(textArea);
        sp.setFoldIndicatorEnabled(true);
        sp.setIconRowHeaderEnabled(true);
        jPanel1.add(sp);
        textArea.setText(sql);
        textArea.setFadeCurrentLineHighlight(true);
        textArea.setMarkOccurrences(true);
        textArea.setEOLMarkersVisible(true);
        textArea.setCaretPosition(0);    
        textArea.requestFocus();        
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(727, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(12, Short.MAX_VALUE))
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

        SearchContext context = new SearchContext();
        String text = "SELECT";
        context.setSearchFor(text);
        context.setMatchCase(false);
        context.setRegularExpression(false);
        context.setSearchForward(true);
        context.setWholeWord(false);
        SearchEngine.find(textArea, context);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
