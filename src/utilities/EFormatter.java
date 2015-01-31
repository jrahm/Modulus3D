/*    */ package utilities;
/*    */ 
/*    */ public class EFormatter extends DecimalFormatter
/*    */ {
/*    */   private int base;
/*    */ 
/*    */   public EFormatter(int radix, int base, boolean flt)
/*    */   {
/* 14 */     super(radix, flt);
/* 15 */     this.base = base;
/*    */   }
/*    */ 
/*    */   public String format(String str) {
/* 19 */     if (Double.parseDouble(str) < this.base) return str;
/* 20 */     String temp = super.eNotation(str);
/* 21 */     String exp = temp.substring(temp.lastIndexOf("e") + 1);
/* 22 */     temp = temp.substring(0, temp.lastIndexOf("e") + 1);
/* 23 */     exp = BaseConverter.convertFromDecimal(Long.parseLong(exp), this.base);
/* 24 */     return temp + exp;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     utilities.EFormatter
 * JD-Core Version:    0.6.2
 */