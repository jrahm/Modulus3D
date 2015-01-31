/*    */ package GUIComponents;
/*    */ 
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ public class ThreadRunner extends Thread
/*    */ {
/*    */   private ProgressFrame progress;
/*    */ 
/*    */   public ThreadRunner(JFrame parent)
/*    */   {
/* 15 */     super(new ThreadGroup(Thread.currentThread().getThreadGroup().getParent(), "Loading"), "Bar");
/* 16 */     this.progress = new ProgressFrame(0, 100, parent);
/* 17 */     setPriority(1);
/*    */   }
/*    */   public void increment(double amt) {
/* 20 */     this.progress.incrementProgress(amt);
/*    */   }
/*    */   public void flagEnd() {
/* 23 */     this.progress.setVisible(false);
/*    */   }
/*    */   public void run() {
/* 26 */     this.progress.setVisible(true);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.ThreadRunner
 * JD-Core Version:    0.6.2
 */