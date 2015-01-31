/*    */ import java.awt.Frame;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class ComplicationAlert extends JDialog
/*    */   implements ActionListener
/*    */ {
/* 12 */   private JButton ok = new JButton("OK");
/*    */ 
/* 14 */   public ComplicationAlert(JDialog owner, String text) { super(owner, "Complication Alert", true);
/* 15 */     JPanel main = new JPanel();
/* 16 */     main.setLayout(new BoxLayout(main, 1));
/* 17 */     JPanel holder1 = new JPanel();
/* 18 */     JPanel holder2 = new JPanel();
/* 19 */     holder1.add(new JLabel(text));
/* 20 */     holder2.add(this.ok);
/*    */ 
/* 22 */     main.add(holder1);
/* 23 */     main.add(holder2);
/*    */ 
/* 25 */     this.ok.addActionListener(this);
/* 26 */     add(main);
/* 27 */     pack();
/* 28 */     setVisible(true); }
/*    */ 
/*    */   public ComplicationAlert(Frame owner, String text, String title) {
/* 31 */     super(owner, title, true);
/* 32 */     JPanel main = new JPanel();
/* 33 */     main.setLayout(new BoxLayout(main, 1));
/* 34 */     JPanel holder1 = new JPanel();
/* 35 */     JPanel holder2 = new JPanel();
/* 36 */     holder1.add(new JLabel(text));
/* 37 */     holder2.add(this.ok);
/*    */ 
/* 39 */     main.add(holder1);
/* 40 */     main.add(holder2);
/*    */ 
/* 42 */     this.ok.addActionListener(this);
/* 43 */     add(main);
/* 44 */     pack();
/* 45 */     setVisible(true);
/*    */   }
/*    */ 
/*    */   public void actionPerformed(ActionEvent arg0) {
/* 49 */     setVisible(false);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     ComplicationAlert
 * JD-Core Version:    0.6.2
 */