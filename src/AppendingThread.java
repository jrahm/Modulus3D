/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import utilities.DecimalFormatter;
/*    */ 
/*    */ public class AppendingThread extends Thread
/*    */ {
/* 12 */   private static int count = 1;
/*    */   private int index;
/*    */   private String str;
/*    */   private ArrayList<String> ans;
/*    */   private DecimalFormatter f;
/*    */ 
/*    */   public AppendingThread(String toSolve, int index, ArrayList<String> ans, DecimalFormatter f)
/*    */   {
/* 18 */     super(Thread.currentThread().getThreadGroup(), "AppendingThread - " + count);
/* 19 */     count += 1;
/* 20 */     this.str = toSolve;
/* 21 */     this.ans = ans;
/* 22 */     this.index = index;
/* 23 */     this.f = f;
/*    */   }
/*    */   public void run() {
/* 26 */     String temp = "";
/*    */     try {
/* 28 */       temp = ControlPanel.figure(this.str);
/*    */     } catch (Exception e) {
/* 30 */       System.out.println(e.getMessage());
/* 31 */     }CalculatorGUI.silentPrint(this.str + "->");
/* 32 */     System.out.println(this.f.format(temp));
/* 33 */     CalculatorGUI.silentPrint("\n");
/* 34 */     this.ans.add(this.index + 1, this.f.format(temp));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     AppendingThread
 * JD-Core Version:    0.6.2
 */