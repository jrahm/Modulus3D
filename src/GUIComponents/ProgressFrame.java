/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.FlowLayout;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JProgressBar;
/*    */ 
/*    */ public class ProgressFrame extends JDialog
/*    */ {
/*    */   private JProgressBar progress;
/*    */   private JPanel panelHolder;
/*    */   private double prog;
/*    */ 
/*    */   public ProgressFrame(int min, int max, JFrame parent)
/*    */   {
/* 17 */     super(parent);
/* 18 */     this.prog = 0.0D;
/* 19 */     setLayout(new BorderLayout());
/* 20 */     this.panelHolder = new JPanel(new FlowLayout());
/* 21 */     add(this.panelHolder, "North");
/* 22 */     this.progress = new JProgressBar(min, max);
/* 23 */     this.progress.setSize(200, 50);
/* 24 */     this.panelHolder.add(this.progress);
/* 25 */     pack();
/*    */   }
/*    */   public void incrementProgress(double amt) {
/* 28 */     this.prog += amt;
/* 29 */     this.progress.setValue((int)this.prog);
/*    */   }
/*    */   public void setValue(double amt) {
/* 32 */     this.prog = amt;
/* 33 */     this.progress.setValue((int)this.prog);
/*    */   }
/*    */   public void flagEnd() {
/* 36 */     setVisible(false);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.ProgressFrame
 * JD-Core Version:    0.6.2
 */