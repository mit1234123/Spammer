package spammer;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;

public class Spammer extends JFrame implements Runnable {
   private boolean running = false;
   private JButton jButton1;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JScrollPane jScrollPane1;
   private JSpinner jSpinner1;
   private JTextArea jTextArea1;

   public Spammer() {
      this.initComponents();
      this.setTitle("Spammer");
      this.setLocationRelativeTo((Component)null);
      this.setResizable(false);
   }

   private void initComponents() {
      this.jLabel1 = new JLabel();
      this.jScrollPane1 = new JScrollPane();
      this.jTextArea1 = new JTextArea();
      this.jLabel2 = new JLabel();
      this.jSpinner1 = new JSpinner();
      this.jButton1 = new JButton();
      this.setDefaultCloseOperation(3);
      this.jLabel1.setText("Text :");
      this.jTextArea1.setColumns(20);
      this.jTextArea1.setRows(5);
      this.jScrollPane1.setViewportView(this.jTextArea1);
      this.jLabel2.setText("Interval :");
      this.jButton1.setText("Spam");
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            Spammer.this.jButton1ActionPerformed(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton1, -1, -1, 32767).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1, -2, 43, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -1, 251, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSpinner1))).addContainerGap()));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jScrollPane1, -2, 113, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jSpinner1, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton1).addContainerGap(-1, 32767)));
      this.pack();
   }

   private void jButton1ActionPerformed(ActionEvent evt) {
      if(!this.running) {
         this.jTextArea1.setEnabled(false);
         this.jSpinner1.setEnabled(false);
         this.jButton1.setText("Spamming in 3 seconds...");
         this.jButton1.setEnabled(false);
         this.running = true;
         (new Thread(this)).start();
      } else {
         this.running = false;
         this.jTextArea1.setEnabled(true);
         this.jSpinner1.setEnabled(true);
         this.jButton1.setText("Spam");
      }

   }

   public static void main(String[] args) {
      try {
         LookAndFeelInfo[] ex = UIManager.getInstalledLookAndFeels();
         int var2 = ex.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            LookAndFeelInfo info = ex[var3];
            if("Nimbus".equals(info.getName())) {
               UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var5) {
         Logger.getLogger(Spammer.class.getName()).log(Level.SEVERE, (String)null, var5);
      }

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            (new Spammer()).setVisible(true);
         }
      });
   }

   public void run() {
      Robot robot = null;

      try {
         robot = new Robot();
      } catch (AWTException var7) {
         Logger.getLogger(Spammer.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      int[] keys = new int[this.jTextArea1.getText().length()];
      if(((Integer)this.jSpinner1.getValue()).intValue() < 0) {
         this.jSpinner1.setValue(Integer.valueOf(0));
      }

      int interval = ((Integer)this.jSpinner1.getValue()).intValue();

      int ex;
      for(ex = 0; ex < keys.length; ++ex) {
         keys[ex] = KeyEvent.getExtendedKeyCodeForChar(this.jTextArea1.getText().charAt(ex));
      }

      try {
         Thread.sleep(3000L);
      } catch (InterruptedException var6) {
         Logger.getLogger(Spammer.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      this.jButton1.setEnabled(true);
      this.jButton1.setText("Stop");

      while(this.running) {
         for(ex = 0; ex < keys.length; ++ex) {
            robot.keyPress(keys[ex]);
            robot.keyRelease(keys[ex]);
         }

         try {
            Thread.sleep((long)interval);
         } catch (InterruptedException var5) {
            Logger.getLogger(Spammer.class.getName()).log(Level.SEVERE, (String)null, var5);
         }
      }

   }
}