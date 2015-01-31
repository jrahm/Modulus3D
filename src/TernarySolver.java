/*    */ public class TernarySolver
/*    */ {
/*    */   public static String solve(String arg)
/*    */     throws Exception
/*    */   {
/* 11 */     arg = arg.replaceAll("\\s+", "");
/* 12 */     while (arg.contains("(")) {
/* 13 */       String part = arg.substring(arg.lastIndexOf("(", arg.indexOf(")")) + 1, arg.indexOf(")"));
/* 14 */       if (containsTernary(part))
/* 15 */         arg = arg.substring(0, arg.lastIndexOf("(", arg.indexOf(")"))) + solve(part) + arg.substring(arg.indexOf(")") + 1);
/* 16 */       else if (BooleanLogic.containsBoolean(part))
/* 17 */         arg = arg.substring(0, arg.lastIndexOf("(", arg.indexOf(")"))) + BooleanLogic.solve(part) + arg.substring(arg.indexOf(")") + 1);
/*    */       else {
/* 19 */         arg = arg.substring(0, arg.lastIndexOf("(", arg.indexOf(")"))) + Calculator.solve(part) + arg.substring(arg.indexOf(")") + 1);
/*    */       }
/*    */     }
/* 22 */     if (!BooleanLogic.solve(arg.substring(2, arg.indexOf("then"))).equals("0")) {
/* 23 */       return Calculator.solve(arg.substring(arg.indexOf("then") + 4, arg.indexOf("else")));
/*    */     }
/* 25 */     return Calculator.solve(arg.substring(arg.indexOf("else") + 4));
/*    */   }
/*    */   public static String solveCP(String arg) throws Exception {
/* 28 */     arg = arg.replaceAll("\\s+", "");
/* 29 */     while (arg.contains("(")) {
/* 30 */       String part = arg.substring(arg.lastIndexOf("(", arg.indexOf(")")) + 1, arg.indexOf(")"));
/* 31 */       if (containsTernary(part))
/* 32 */         arg = arg.substring(0, arg.lastIndexOf("(", arg.indexOf(")"))) + solve(part) + arg.substring(arg.indexOf(")") + 1);
/* 33 */       else if (BooleanLogic.containsBoolean(part))
/* 34 */         arg = arg.substring(0, arg.lastIndexOf("(", arg.indexOf(")"))) + BooleanLogic.solve(part) + arg.substring(arg.indexOf(")") + 1);
/*    */       else {
/* 36 */         arg = arg.substring(0, arg.lastIndexOf("(", arg.indexOf(")"))) + Calculator.solve(part) + arg.substring(arg.indexOf(")") + 1);
/*    */       }
/*    */     }
/* 39 */     if (!BooleanLogic.solve(arg.substring(2, arg.indexOf("then"))).equals("0")) {
/* 40 */       return ControlPanel.figure(arg.substring(arg.indexOf("then") + 4, arg.indexOf("else")));
/*    */     }
/* 42 */     return ControlPanel.figure(arg.substring(arg.indexOf("else") + 4));
/*    */   }
/*    */   public static boolean containsTernary(String x) {
/* 45 */     return (x.contains("if")) && (x.contains("then")) && (x.contains("else"));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     TernarySolver
 * JD-Core Version:    0.6.2
 */