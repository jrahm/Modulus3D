/*    */ import java.awt.EventQueue;
/*    */ 
/*    */ public class GraphThread extends EventQueue
/*    */   implements Runnable
/*    */ {
/*    */   public GraphThread()
/*    */   {
/*    */     try
/*    */     {
/* 12 */       invokeLater(this);
/*    */     }
/*    */     catch (Exception e) {
/* 15 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/* 19 */   public void run() { GraphFrame.run(); }
/*    */ 
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GraphThread
 * JD-Core Version:    0.6.2
 */