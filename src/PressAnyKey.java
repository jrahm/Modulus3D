/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class PressAnyKey
/*    */ {
/*    */   public PressAnyKey(String disp)
/*    */     throws Exception
/*    */   {
/* 13 */     System.out.println(disp);
/* 14 */     BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
/* 15 */     int ch = stdin.read();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     PressAnyKey
 * JD-Core Version:    0.6.2
 */