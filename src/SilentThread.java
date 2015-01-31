/*    */ public class SilentThread extends Thread
/*    */ {
/*    */   private String info;
/*    */ 
/*    */   public SilentThread(Runnable run, String info)
/*    */   {
/* 14 */     super(run);
/* 15 */     this.info = info;
/*    */   }
/*    */   public void start() {
/* 18 */     super.start();
/* 19 */     CalculatorGUI.silentPrint("------------------------------\n");
/* 20 */     CalculatorGUI.silentPrint("Finished Running File: " + this.info);
/* 21 */     CalculatorGUI.silentPrint("------------------------------\n");
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     SilentThread
 * JD-Core Version:    0.6.2
 */