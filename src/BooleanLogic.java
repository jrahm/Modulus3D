/*    */ import java.math.BigDecimal;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BooleanLogic
/*    */ {
/*    */   public static String solve(String eq)
/*    */     throws Exception
/*    */   {
/* 13 */     eq = eq.replaceAll("\\s", "");
/* 14 */     while (eq.contains("(")) {
/* 15 */       String part = eq.substring(eq.lastIndexOf("(", eq.indexOf(")")) + 1, eq.indexOf(")"));
/* 16 */       if (TernarySolver.containsTernary(part))
/* 17 */         eq = eq.substring(0, eq.lastIndexOf("(", eq.indexOf(")"))) + TernarySolver.solve(part) + eq.substring(eq.indexOf(")") + 1);
/* 18 */       else if (containsBoolean(part))
/* 19 */         eq = eq.substring(0, eq.lastIndexOf("(", eq.indexOf(")"))) + solve(part) + eq.substring(eq.indexOf(")") + 1);
/*    */       else
/* 21 */         eq = eq.substring(0, eq.lastIndexOf("(", eq.indexOf(")"))) + Calculator.solve(part) + eq.substring(eq.indexOf(")") + 1);
/*    */     }
/* 23 */     String[] parts = eq.split("\\s*and\\s*|\\s*xor\\s*|\\s*or\\s*");
/* 24 */     String[] logic = splitLogic(eq);
/* 25 */     boolean[] answers = new boolean[parts.length];
/* 26 */     for (int i = 0; i < parts.length; i++) {
/* 27 */       answers[i] = parseSingleBoolean(parts[i]);
/*    */     }
/* 29 */     boolean ans = answers[0];
/* 30 */     for (int i = 0; i < logic.length; i++) {
/* 31 */       if (logic[i].equals("and")) ans &= answers[(i + 1)];
/* 32 */       else if (logic[i].equals("xor")) ans ^= answers[(i + 1)];
/* 33 */       else if (logic[i].equals("or")) ans |= answers[(i + 1)];
/*    */     }
/* 35 */     return ans ? 1 : 0;
/*    */   }
/*    */   public static boolean parseSingleBoolean(String eq) throws Exception {
/* 38 */     eq = eq.trim();
/* 39 */     String store = "";
/* 40 */     boolean flag = false;
/* 41 */     while (eq.startsWith("not")) {
/* 42 */       if (eq.equals("not0")) return true;
/* 43 */       if (eq.equals("not1")) return false;
/* 44 */       flag = !flag;
/* 45 */       eq = eq.substring(3);
/*    */     }
/* 47 */     if ((eq.equals("1")) || (eq.equals("0")) || (eq.equals("true")) || (eq.equals("false"))) return eq.replaceAll("true", "1").contains("1") ^ flag;
/* 48 */     if ((eq.contains(">=")) || (eq.contains("=>"))) store = ">=";
/* 49 */     else if ((eq.contains("<=")) || (eq.contains("=<"))) store = "<=";
/* 50 */     else if (eq.contains(">")) store = ">";
/* 51 */     else if (eq.contains("<")) store = "<"; else
/* 52 */       store = "=";
/* 53 */     String[] args = eq.split(">=|=>|<=|=<|<|>|=");
/* 54 */     String hold1 = Calculator.solve(args[0]);
/* 55 */     String hold2 = Calculator.solve(args[1]);
/* 56 */     switch (new BigDecimal(hold1).compareTo(new BigDecimal(hold2))) { case 0:
/* 57 */       return store.contains("=") ^ flag;
/*    */     case 1:
/* 58 */       return store.contains(">") ^ flag; }
/* 59 */     return store.contains("<") ^ flag;
/*    */   }
/*    */ 
/*    */   public static String[] splitLogic(String eq)
/*    */   {
/* 64 */     ArrayList ret = new ArrayList();
/* 65 */     for (int i = 0; i < eq.length(); i++) {
/* 66 */       if ((eq.startsWith("or", i)) && (eq.charAt(i - 1) != 'x')) ret.add("or");
/* 67 */       if (eq.startsWith("and", i)) ret.add("and");
/* 68 */       if (eq.startsWith("xor", i)) ret.add("xor");
/*    */     }
/* 70 */     return Calculator.toStringArray(ret);
/*    */   }
/*    */ 
/*    */   public static boolean containsBoolean(String eq) {
/* 74 */     return (eq.contains("and")) || (eq.contains("or")) || (eq.contains("not")) || 
/* 74 */       (eq.contains(">")) || (eq.contains("<")) || (eq.contains("="));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     BooleanLogic
 * JD-Core Version:    0.6.2
 */