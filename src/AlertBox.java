/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class AlertBox extends JFrame
/*    */   implements ActionListener
/*    */ {
/*    */   private JPanel bottom;
/*    */   private JPanel top;
/*    */   private JButton ok;
/*    */ 
/*    */   public AlertBox(String message, String title)
/*    */   {
/* 19 */     setTitle(title);
/* 20 */     setLayout(new BorderLayout());
/* 21 */     this.bottom = new JPanel();
/* 22 */     this.top = new JPanel();
/* 23 */     add(this.top, "North");
/* 24 */     add(this.bottom, "South");
/* 25 */     this.top.add(new JLabel(message));
/* 26 */     this.ok = new JButton("OK");
/* 27 */     this.bottom.add(this.ok);
/* 28 */     this.ok.addActionListener(this);
/* 29 */     setVisible(true);
/* 30 */     setIconImage(Toolkit.getDefaultToolkit().getImage("modulus_symbol.png"));
/* 31 */     pack();
/*    */   }
/* 33 */   public void actionPerformed(ActionEvent e) { setVisible(false); } 
/* 34 */   public static void throwError(String alert) { new AlertBox(alert, "Error"); }
/*    */ 
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     AlertBox
 * JD-Core Version:    0.6.2
 */