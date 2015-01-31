/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ public class Prompt<E extends JComponent> extends JDialog
/*    */   implements ActionListener
/*    */ {
/*  7 */   private JPanel mainPanel = new JPanel(new FlowLayout());
/*    */   private Object[] args;
/*    */   private JButton done;
/*    */   private JButton cancel;
/*    */   private JPanel bottom;
/*    */ 
/*    */   public Prompt(JFrame owner, String text, E[] args, ActionListener listener, JButton ok)
/*    */   {
/* 13 */     super(owner, true);
/* 14 */     this.done = ok;
/* 15 */     this.bottom = new JPanel(new FlowLayout());
/* 16 */     this.cancel = new JButton("Cancel");
/*    */     try {
/* 18 */       UIManager.setLookAndFeel(
/* 19 */         UIManager.getSystemLookAndFeelClassName());
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 23 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 26 */     SwingUtilities.updateComponentTreeUI(this);
/*    */ 
/* 28 */     this.args = args;
/* 29 */     setTitle(text);
/* 30 */     add(new JLabel(text), "North");
/* 31 */     for (int i = 0; i < this.args.length; i++) {
/* 32 */       this.mainPanel.add((JComponent)this.args[i]);
/*    */     }
/* 34 */     ok.addActionListener(listener);
/* 35 */     ok.addActionListener(this);
/* 36 */     this.cancel.addActionListener(this);
/* 37 */     this.bottom.add(this.done);
/* 38 */     this.bottom.add(this.cancel);
/* 39 */     add(this.mainPanel, "Center");
/* 40 */     add(this.bottom, "South");
/* 41 */     setIconImage(Toolkit.getDefaultToolkit().getImage("modulus_symbol.png"));
/* 42 */     pack();
/*    */   }
/* 44 */   public E get(int i) { return (JComponent)this.args[i]; } 
/*    */   public String[] getFields() {
/* 46 */     String[] ret = new String[this.args.length];
/* 47 */     for (int i = 0; i < this.args.length; i++) ret[i] = this.args[i].toString();
/* 48 */     return ret;
/*    */   }
/*    */   public void actionPerformed(ActionEvent e) {
/* 51 */     setVisible(false);
/*    */   }
/* 53 */   public JButton getButton() { return this.done; }
/*    */ 
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Prompt
 * JD-Core Version:    0.6.2
 */