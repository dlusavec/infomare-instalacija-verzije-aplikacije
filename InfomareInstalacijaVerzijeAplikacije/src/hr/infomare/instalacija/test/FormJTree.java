
package hr.infomare.instalacija.test;

import java.net.URL;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 *
 * @author damir
 */
public class FormJTree extends javax.swing.JFrame {

    /** Creates new form FormJTree */
    public FormJTree() {
        initComponents();
        setLocationRelativeTo(null);
        kreirajStablo();
    }
    private void kreirajStablo(){
        DefaultMutableTreeNode top= new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode category = null;
           DefaultMutableTreeNode book = null;
           
           category = new DefaultMutableTreeNode("Books for Java Programmers");
           top.add(category);
           
           //original Tutorial
           book = new DefaultMutableTreeNode(new BookInfo
               ("The Java Tutorial: A Short Course on the Basics",
               "tutorial.html"));
           category.add(book);
           
           //Tutorial Continued
           book = new DefaultMutableTreeNode(new BookInfo
               ("The Java Tutorial Continued: The Rest of the JDK",
               "tutorialcont.html"));
           category.add(book);
           
           //Swing Tutorial
           book = new DefaultMutableTreeNode(new BookInfo
               ("The Swing Tutorial: A Guide to Constructing GUIs",
               "swingtutorial.html"));
           category.add(book);

           //...add more books for programmers...

           category = new DefaultMutableTreeNode("Books for Java Implementers");
           top.add(category);

           //VM
           book = new DefaultMutableTreeNode(new BookInfo
               ("The Java Virtual Machine Specification",
                "vm.html"));
           category.add(book);

           //Language Spec
           book = new DefaultMutableTreeNode(new BookInfo
               ("The Java Language Specification",
                "jls.html"));
           category.add(book);
        DefaultTreeModel treeModel = new DefaultTreeModel( top );
                       jTree1.setModel(treeModel);
        for (int i = 0; i < jTree1.getRowCount(); i++) {
            jTree1.expandRow(i);
        }
                       
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTree1.setAutoscrolls(true);
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 121, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FormJTree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormJTree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormJTree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormJTree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormJTree().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
    private class BookInfo {
           public String bookName;
           public URL bookURL;
    
           public BookInfo(String book, String filename) {
               bookName = book;
               bookURL = getClass().getResource(filename);
               if (bookURL == null) {
                   System.err.println("Couldn't find file: "
                                      + filename);
               }
           }
    
           public String toString() {
               return bookName;
           }
       }
}