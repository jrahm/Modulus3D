/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Main
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 12 */     String[] arrayOfString = args; int j = args.length; for (int i = 0; i < j; i++) { String i = arrayOfString[i];
/*    */       try {
/* 14 */         ScriptReader.runScript(i);
/*    */       }
/*    */       catch (Exception e) {
/* 17 */         e.printStackTrace();
/* 18 */         System.err.println("in file: " + i);
/*    */       }
/*    */       try {
/* 21 */         new PressAnyKey("Press enter to continue...");
/*    */       }
/*    */       catch (Exception localException1)
/*    */       {
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Main
 * JD-Core Version:    0.6.2
 */