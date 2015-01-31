/*    */ package utilities;
/*    */ 
/*    */ public class BaseConverter
/*    */ {
/*    */   public static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
/*    */ 
/*    */   public static double convertToDecimal(String original, int base)
/*    */   {
/* 12 */     int neg = 1;
/* 13 */     if (original.startsWith("-")) {
/* 14 */       original = original.substring(1);
/* 15 */       neg = -1;
/*    */     }
/* 17 */     if (original.contains(".")) return neg * convertToDecimal(original, base, original.indexOf(".") - 1);
/* 18 */     return neg * convertToDecimal(original, base, original.length() - 1);
/*    */   }
/*    */   private static double convertToDecimal(String original, int base, int length) {
/* 21 */     if (base == 10) return Double.parseDouble(original);
/* 22 */     if (base == 1) return original.length();
/* 23 */     if (original.length() == 0) return 0.0D;
/* 24 */     if (original.charAt(0) == '.') return convertToDecimal(original.substring(1), base, length);
/* 25 */     return Math.pow(base, length) * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(original.charAt(0)) + convertToDecimal(original.substring(1), base, length - 1);
/*    */   }
/*    */   public static String convertFromDecimal(double original, int base) {
/* 28 */     if (original < 0.0D) return "-" + convertFromDecimal(-1.0D * original, base);
/* 29 */     if ((original < 1.0D) && (original > 0.0D)) return "0" + convertFromDecimal(1.0D + original, base).substring(1);
/* 30 */     if (base == 10) return original;
/* 31 */     if (base == 1) {
/* 32 */       String ret = "";
/* 33 */       for (int i = 0; i < original; i++) ret = ret + "0";
/* 34 */       return ret;
/*    */     }
/* 36 */     double pwr = nearestLog(()original, base);
/* 37 */     String ret = "";
/* 38 */     boolean lessThan = false;
/* 39 */     while ((original > 1.E-09D) || (pwr >= 1.0D)) {
/* 40 */       if ((!lessThan) && (pwr < 1.0D)) {
/* 41 */         ret = ret + ".";
/* 42 */         lessThan = true;
/*    */       }
/* 44 */       if (original >= pwr) {
/* 45 */         ret = ret + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int)(original / pwr));
/* 46 */         original %= pwr;
/*    */       }
/*    */       else {
/* 49 */         ret = ret + 0;
/*    */       }
/* 51 */       pwr /= base;
/*    */     }
/* 53 */     return ret;
/*    */   }
/*    */   public static String convertFromDecimal(long original, int base, long start, int digits) {
/* 56 */     if (original < base) {
/* 57 */       String ret = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int)original);
/* 58 */       while (digits != 0) {
/* 59 */         ret = ret + 0;
/* 60 */         digits--;
/*    */       }
/* 62 */       return ret;
/*    */     }
/*    */ 
/* 65 */     return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int)(original / start)) + convertFromDecimal(original % start, base, start / base, digits - 1);
/*    */   }
/*    */   public static long nearestLog(long start, int base) {
/* 68 */     return ()Math.pow(base, (int)(Math.log(start) / Math.log(base)));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     utilities.BaseConverter
 * JD-Core Version:    0.6.2
 */