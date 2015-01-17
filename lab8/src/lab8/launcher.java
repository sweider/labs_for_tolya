/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab8;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import lab8.gui.MainFrame;
import lab8.logic.JournalRecordsController;

/**
 *
 * 
 */
public class launcher {
    private static String TABLE_INITIALIZATION_ERROR_MSG() {
        return "Error during creation table model. Method cannot be found. \n"
                + "Please, check reflection part, seems you have changed method name\n"
                + "but not changed reflicting of this method";
    }

    /**
     * Точка входа в приложение.
     * Создаем контроллер. 
     * Создаем и запускаем форму в отдельном потоке {@link java.awt.EventDispatchThread}
     * Так как сами этим заниматься не хотим -- делегируем специальному классу {@link SwingUtilities}
     * Если произошла ошибка - сообщаем.
     * @param args the command line arguments
     */
    public static void main(String[] args){  
        try {        
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
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>            
            final JournalRecordsController journalRecordsController = new JournalRecordsController();
            Runnable startFormTask = new Runnable() { @Override public void run() { new MainFrame(journalRecordsController).setVisible(true); } };
            SwingUtilities.invokeLater(startFormTask);
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
           JOptionPane.showMessageDialog(null, TABLE_INITIALIZATION_ERROR_MSG(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
