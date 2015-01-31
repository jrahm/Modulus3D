/*    */ package utilities;
/*    */ 
/*    */ public class NoFormatter extends DecimalFormatter
/*    */ {
/*    */   public NoFormatter(int radix)
/*    */   {
/* 13 */     super(radix, true);
/*    */   }
/*    */   public String format(String str) {
/* 16 */     return str;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     utilities.NoFormatter
 * JD-Core Version:    0.6.2
 */