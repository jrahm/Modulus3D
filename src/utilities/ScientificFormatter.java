/*    */ package utilities;
/*    */ 
/*    */ public class ScientificFormatter extends DecimalFormatter
/*    */ {
/*    */   private int base;
/*    */ 
/*    */   public ScientificFormatter(int radix, int base, boolean flt)
/*    */   {
/* 14 */     super(radix, flt);
/* 15 */     this.base = base;
/*    */   }
/*    */   public String format(String str) {
/* 18 */     if (Double.parseDouble(str) < this.base) return str;
/* 19 */     String temp = super.scientificNotation(str);
/* 20 */     String exp = temp.substring(temp.lastIndexOf("*10^") + 4);
/* 21 */     temp = temp.substring(0, temp.lastIndexOf("*10^") + 4);
/* 22 */     exp = BaseConverter.convertFromDecimal(Long.parseLong(exp), this.base);
/*    */ 
/* 24 */     return temp + exp;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     utilities.ScientificFormatter
 * JD-Core Version:    0.6.2
 */