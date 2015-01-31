/*     */ package utilities;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ 
/*     */ public abstract class DecimalFormatter
/*     */ {
/*     */   private int sigFigs;
/*     */   private String sigstr;
/*     */   private boolean flt;
/*     */ 
/*     */   public DecimalFormatter(int sigFigs, boolean flt)
/*     */   {
/*  17 */     this.sigFigs = sigFigs;
/*  18 */     this.flt = flt;
/*  19 */     this.sigstr = "";
/*  20 */     for (int i = 0; i < sigFigs + 1; i++) this.sigstr += "0"; 
/*     */   }
/*     */ 
/*  23 */   public String scientificNotation(BigDecimal d) { int hold = 0;
/*  24 */     boolean negatory = d.compareTo(BigDecimal.ZERO) == -1;
/*  25 */     if (negatory) d = d.abs();
/*  26 */     while (d.compareTo(BigDecimal.ONE) == -1)
/*     */     {
/*  28 */       d = d.multiply(BigDecimal.TEN);
/*  29 */       hold--;
/*     */     }
/*  31 */     while (d.compareTo(BigDecimal.TEN) == 1)
/*     */     {
/*  33 */       d = d.divide(BigDecimal.TEN);
/*  34 */       hold++;
/*     */     }
/*     */ 
/*  37 */     String dec = roundTo(d, this.sigFigs - 1).toString();
/*  38 */     if (!this.flt) {
/*  39 */       dec = removeTrailingZeros(dec);
/*     */     }
/*  41 */     return (negatory ? "-" : "") + dec.substring(0, this.sigFigs + 1) + "*10^" + hold; }
/*     */ 
/*     */   public String scientificNotation(String num) {
/*  44 */     return scientificNotation(new BigDecimal(num));
/*     */   }
/*     */   public String scientificNotation(double num) {
/*  47 */     return scientificNotation(new BigDecimal(num));
/*     */   }
/*     */   public String eNotation(BigDecimal d) {
/*  50 */     int hold = 0;
/*  51 */     boolean negatory = d.compareTo(BigDecimal.ZERO) == -1;
/*  52 */     if (negatory) d = d.abs();
/*  53 */     while (d.compareTo(BigDecimal.ONE) == -1)
/*     */     {
/*  55 */       d = d.multiply(BigDecimal.TEN);
/*  56 */       hold--;
/*     */     }
/*  58 */     while (d.compareTo(BigDecimal.TEN) == 1)
/*     */     {
/*  60 */       d = d.divide(BigDecimal.TEN);
/*  61 */       hold++;
/*     */     }
/*     */ 
/*  64 */     String dec = roundTo(d, this.sigFigs - 1).toString();
/*  65 */     if (!this.flt) {
/*  66 */       dec = removeTrailingZeros(dec);
/*     */     }
/*  68 */     return (negatory ? "-" : "") + dec + "e" + hold;
/*     */   }
/*     */   public static BigDecimal roundTo(BigDecimal b, int to) {
/*  71 */     b = b.movePointRight(to);
/*  72 */     b = b.round(MathContext.DECIMAL64);
/*  73 */     b = b.movePointLeft(to);
/*  74 */     return b;
/*     */   }
/*     */   public String eNotation(String num) {
/*  77 */     return eNotation(new BigDecimal(num));
/*     */   }
/*     */   public String eNotation(double num) {
/*  80 */     return eNotation(new BigDecimal(num));
/*     */   }
/*     */   public String getSigFigs(String num) {
/*  83 */     boolean rec = false;
/*  84 */     String ret = "";
/*  85 */     int cnt = 0;
/*  86 */     for (int i = 0; (i < num.length()) && (cnt < this.sigFigs); i++) {
/*  87 */       ret = ret + num.charAt(i);
/*  88 */       if ((num.charAt(i) != '0') && (num.charAt(i) != '.') && (!rec)) rec = true;
/*  89 */       if (rec) cnt++;
/*     */     }
/*  91 */     return ret;
/*     */   }
/*     */   private static String removeTrailingZeros(String str) {
/*  94 */     if (!str.contains(".")) return str;
/*  95 */     for (int i = str.length() - 1; i >= 0; i--) {
/*  96 */       if (str.charAt(i) != '0')
/*  97 */         return str.substring(0, i + 1);
/*     */     }
/*  99 */     return str;
/*     */   }
/*     */   public abstract String format(String paramString);
/*     */ 
/* 103 */   public void setFigs(int sigFigs) { this.sigFigs = sigFigs;
/* 104 */     this.sigstr = "";
/* 105 */     for (int i = 0; i < sigFigs + 1; i++) this.sigstr += "0";
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     utilities.DecimalFormatter
 * JD-Core Version:    0.6.2
 */