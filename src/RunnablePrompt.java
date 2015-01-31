/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class RunnablePrompt
/*    */   implements Runnable, ActionListener
/*    */ {
/*    */   Prompt<JTextField> tempPrompt;
/*    */   String[] args;
/*    */ 
/*    */   public RunnablePrompt(String[] args)
/*    */   {
/* 16 */     this.args = args;
/*    */   }
/*    */   public void run() {
/*    */     try {
/* 20 */       this.tempPrompt = new Prompt(null, ControlPanel.parseOut(this.args[0]), new JTextField[] { new JTextField("", 5) }, this, new JButton("OK"));
/* 21 */       this.tempPrompt.setVisible(true);
/*    */     }
/*    */     catch (Exception e) {
/* 24 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/* 28 */   public void actionPerformed(ActionEvent e) { SwingPrompter.hold = ((JTextField)this.tempPrompt.get(0)).getText();
/* 29 */     SwingPrompter.toggle = true;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     RunnablePrompt
 * JD-Core Version:    0.6.2
 */