/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.math.MathContext;
/*     */ import java.util.ArrayList;
/*     */ import utilities.BaseConverter;
/*     */ 
/*     */ public class Calculator
/*     */ {
/*  19 */   private static int base = 10;
/*  20 */   private static boolean radians = false;
/*     */   public static final String OPS = "%/*+^e:&\\?£";
/*  22 */   public static final String[] OPS_ARR = { "%", "/", "*", "+", "^", "e", ":", "&", "\\", "?", "£" };
/*     */ 
/*     */   public static String solve(String eq) throws Exception {
/*  25 */     eq = expandMult(eq);
/*  26 */     while (eq.contains("(")) {
/*  27 */       String part = eq.substring(eq.lastIndexOf("(", eq.indexOf(")")) + 1, eq.indexOf(")"));
/*  28 */       if (TernarySolver.containsTernary(part))
/*  29 */         eq = eq.substring(0, eq.lastIndexOf("(", eq.indexOf(")"))) + TernarySolver.solve(part) + eq.substring(eq.indexOf(")") + 1);
/*  30 */       else if (BooleanLogic.containsBoolean(part))
/*  31 */         eq = eq.substring(0, eq.lastIndexOf("(", eq.indexOf(")"))) + BooleanLogic.solve(part) + eq.substring(eq.indexOf(")") + 1);
/*     */       else
/*  33 */         eq = eq.substring(0, eq.lastIndexOf("(", eq.indexOf(")"))) + solve(part) + eq.substring(eq.indexOf(")") + 1);
/*     */     }
/*  35 */     while (eq.contains("|")) {
/*  36 */       String x = solve(eq.substring(eq.lastIndexOf("|", eq.lastIndexOf("|") - 1) + 1, eq.lastIndexOf("|")));
/*  37 */       if (x.startsWith("-")) x = x.substring(1);
/*  38 */       eq = eq.substring(0, eq.lastIndexOf("|", eq.lastIndexOf("|") - 1)) + x + eq.substring(eq.lastIndexOf("|") + 1);
/*     */     }
/*     */ 
/*  45 */     String hold = "+";
/*     */ 
/*  47 */     int errIndex = 0;
/*     */ 
/*  49 */     eq = eq.replaceAll("\\s", "");
/*     */ 
/*  51 */     eq = sudoSubtraction(eq);
/*  52 */     while (errIndex < 65535)
/*     */     {
/*  54 */       String[] numbers = splitNums(eq)[0];
/*  55 */       if (base != 10) replaceBase(numbers);
/*     */ 
/*  57 */       String[] operators = splitNums(eq)[1];
/*  58 */       if ((eq.startsWith("-")) || (eq.startsWith("+"))) hold = eq.charAt(0);
/*  59 */       if ((numbers.length == 1) && (operators.length == 0)) {
/*  60 */         singleArgCheck(numbers);
/*  61 */         if (base == 10) return numbers[0];
/*  62 */         return BaseConverter.convertFromDecimal(Double.parseDouble(numbers[0]), base);
/*     */       }
/*     */ 
/*  77 */       singleArgCheck(numbers);
/*     */ 
/*  79 */       int indexOfNext = indexOf(operators, new String[] { "^", "e", "£" });
/*  80 */       if (indexOfNext == -1) indexOfNext = indexOf(operators, new String[] { "*", "/", "%", ":" });
/*  81 */       if (indexOfNext == -1) indexOfNext = indexOf(operators, new String[] { "+" });
/*  82 */       if (indexOfNext == -1) indexOfNext = indexOf(operators, new String[] { "&", "\\", "?" });
/*  83 */       if (indexOfNext == -1) {
/*  84 */         if (base == 10) return eq;
/*  85 */         return BaseConverter.convertFromDecimal(Double.parseDouble(eq), base);
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/*  90 */         numbers = append(new String[][] { subarray(numbers, 0, indexOfNext), { solvePart(numbers[indexOfNext], operators[indexOfNext], numbers[(indexOfNext + 1)]) }, subarray(numbers, indexOfNext + 2, numbers.length) });
/*  91 */         operators = append(new String[][] { subarray(operators, 0, indexOfNext), subarray(operators, indexOfNext + 1, operators.length) });
/*     */       }
/*     */       catch (RuntimeException e) {
/*  94 */         if (base == 10) return solvePart(numbers[indexOfNext], operators[indexOfNext], numbers[(indexOfNext + 1)]);
/*  95 */         return BaseConverter.convertFromDecimal(Double.parseDouble(solvePart(numbers[indexOfNext], operators[indexOfNext], numbers[(indexOfNext + 1)])), base);
/*     */       }
/*     */ 
/*  98 */       eq = "";
/*  99 */       for (int i = 0; i < numbers.length; i++) {
/* 100 */         eq = eq + numbers[i];
/* 101 */         if (i < operators.length) eq = eq + operators[i];
/*     */       }
/* 103 */       errIndex++;
/*     */     }
/* 105 */     throw new Exception(eq + " could not be parsed");
/*     */   }
/*     */ 
/*     */   private static boolean checkOps(String[] ops, String[] nums) {
/* 109 */     boolean flag = false;
/* 110 */     if (ops.length >= nums.length) {
/* 111 */       flag = true;
/* 112 */       nums[0] = (ops[0] + nums[0]);
/*     */     }
/* 114 */     for (int i = 0; i < ops.length; i++) {
/* 115 */       if ((ops[i].length() > 1) && ((ops[i].charAt(ops[i].length() - 1) == '-') || (ops[i].charAt(ops[i].length() - 1) == '+'))) {
/* 116 */         nums[(flag ? i : i + 1)] = (ops[i].charAt(1) + nums[(i + 1)]);
/* 117 */         ops[i] = ops[i].charAt(0);
/*     */       }
/*     */     }
/* 120 */     return flag;
/*     */   }
/*     */   public static int indexOf(Object[] args, Object arg) {
/* 123 */     for (int i = 0; i < args.length; i++) if (arg.equals(args[i])) return i;
/* 124 */     return -1;
/*     */   }
/*     */   private static String solvePart(String num1, String op, String num2) {
/* 127 */     String ret = "";
/*     */ 
/* 132 */     if (num2.endsWith(".0")) num2 = num2.substring(0, num2.indexOf("."));
/* 133 */     if (num1.endsWith(".0")) num1 = num1.substring(0, num1.indexOf("."));
/* 134 */     if (op.equals("^")) ret = Math.pow(Double.parseDouble(num1), Double.parseDouble(num2));
/* 135 */     if (op.equals("£")) ret = Math.pow(Double.parseDouble(num1), 1.0D / Double.parseDouble(num2));
/* 136 */     if (op.equals("e"))
/*     */     {
/* 138 */       return Double.parseDouble(num1) * Math.pow(10.0D, Integer.parseInt(num2));
/*     */     }
/* 140 */     if (op.equals("*")) ret = new BigDecimal(num1).multiply(new BigDecimal(num2)).toPlainString();
/* 141 */     else if (op.equals(":")) ret = new BigDecimal(num1).divide(new BigDecimal(num1).add(new BigDecimal(num2)), MathContext.DECIMAL64).toPlainString();
/* 142 */     else if (op.equals("/")) {
/* 143 */       ret = new BigDecimal(num1).divide(new BigDecimal(num2), MathContext.DECIMAL64).toPlainString();
/*     */     }
/* 145 */     else if (op.equals("%")) ret = new BigDecimal(num1).remainder(new BigDecimal(num2)).toPlainString();
/* 146 */     else if (op.equals("+")) ret = new BigDecimal(num1).add(new BigDecimal(num2)).toPlainString();
/* 147 */     else if (op.equals("-")) ret = new BigDecimal(num1).subtract(new BigDecimal(num2)).toPlainString();
/* 148 */     else if (op.equals("&")) ret = new BigInteger(num1).and(new BigInteger(num2));
/* 149 */     else if (op.equals("\\")) ret = new BigInteger(num1).or(new BigInteger(num2));
/* 150 */     else if (op.equals("?")) ret = new BigInteger(num1).xor(new BigInteger(num2));
/* 151 */     return base == 10 ? ret : BaseConverter.convertFromDecimal(Double.parseDouble(ret), base);
/*     */   }
/*     */   private static void singleArgCheck(String[] nums) {
/* 154 */     boolean neg = false;
/* 155 */     for (int i = 0; i < nums.length; i++) {
/* 156 */       if (nums[i].startsWith("sqrt")) nums[i] = Math.sqrt(Double.parseDouble(nums[i].substring(4)));
/* 157 */       if (nums[i].startsWith("~")) nums[i] = new BigInteger(nums[i].substring(1)).not();
/* 158 */       if (nums[i].startsWith("log")) nums[i] = (Math.log(Double.parseDouble(nums[i].substring(3))) / Math.log(10.0D));
/* 159 */       if (nums[i].startsWith("ln")) nums[i] = Math.log(Double.parseDouble(nums[i].substring(2)));
/* 160 */       if (radians) {
/* 161 */         if (nums[i].startsWith("cos")) nums[i] = Math.cos(Double.parseDouble(nums[i].substring(3)));
/* 162 */         if (nums[i].startsWith("tan")) nums[i] = Math.tan(Double.parseDouble(nums[i].substring(3)));
/* 163 */         if (nums[i].startsWith("sin")) nums[i] = Math.sin(Double.parseDouble(nums[i].substring(3)));
/* 164 */         if (nums[i].startsWith("acos")) nums[i] = Math.acos(Double.parseDouble(nums[i].substring(4)));
/* 165 */         if (nums[i].startsWith("asin")) nums[i] = Math.asin(Double.parseDouble(nums[i].substring(4)));
/* 166 */         if (nums[i].startsWith("atan")) nums[i] = Math.atan(Double.parseDouble(nums[i].substring(4)));
/*     */       }
/*     */       else
/*     */       {
/* 170 */         if (nums[i].startsWith("cos")) nums[i] = Math.cos(Math.toRadians(Double.parseDouble(nums[i].substring(3))));
/* 171 */         if (nums[i].startsWith("tan")) nums[i] = Math.tan(Math.toRadians(Double.parseDouble(nums[i].substring(3))));
/* 172 */         if (nums[i].startsWith("sin")) nums[i] = Math.sin(Math.toRadians(Double.parseDouble(nums[i].substring(3))));
/* 173 */         if (nums[i].startsWith("acos")) nums[i] = Math.toDegrees(Math.acos(Double.parseDouble(nums[i].substring(4))));
/* 174 */         if (nums[i].startsWith("asin")) nums[i] = Math.toDegrees(Math.asin(Double.parseDouble(nums[i].substring(4))));
/* 175 */         if (nums[i].startsWith("atan")) nums[i] = Math.toDegrees(Math.atan(Double.parseDouble(nums[i].substring(4))));
/*     */       }
/*     */ 
/* 178 */       if (nums[i].endsWith("!")) {
/* 179 */         if (nums[i].contains(".")) nums[i] = (nums[i].substring(0, nums[i].indexOf(".")) + "!");
/* 180 */         nums[i] = factorial(new BigInteger(nums[i].substring(0, nums[i].length() - 1)));
/*     */       }
/* 182 */       if (nums[i].endsWith("_"))
/*     */       {
/*     */         String tmp866_860 = (new BigDecimal(nums[i].substring(0, nums[i].length() - 1)).toString() + ".0"); String x = tmp866_860; nums[i] = tmp866_860.substring(0, x.indexOf("."));
/*     */       }
/* 186 */       if (neg) nums[i] = ("-" + nums[i]); 
/*     */     }
/*     */   }
/*     */ 
/* 190 */   private static int indexOf(Object[] args, Object[] find) { int min = -1;
/*     */ 
/* 192 */     for (Object o : find)
/*     */     {
/*     */       int idx;
/* 193 */       min = ((idx = indexOf(args, o)) != -1) && ((idx < min) || (min == -1)) ? idx : min;
/*     */     }
/* 195 */     return min; }
/*     */ 
/*     */   public static String[] subarray(String[] args, int start, int end) {
/* 198 */     String[] ret = new String[end - start];
/* 199 */     for (int i = start; i < end; i++) ret[(i - start)] = args[i];
/* 200 */     return ret;
/*     */   }
/*     */   private static String[] append(String[][] args) {
/* 203 */     int max = 0;
/* 204 */     String[][] arrayOfString = args; int j = args.length; for (int i = 0; i < j; i++) { String[] x = arrayOfString[i]; max += x.length; }
/* 205 */     String[] ret = new String[max];
/* 206 */     int i = 0; for (int k = 0; i < args.length; i++)
/* 207 */       for (int j = 0; j < args[i].length; k++) {
/* 208 */         ret[k] = args[i][j];
/*     */ 
/* 207 */         j++;
/*     */       }
/* 209 */     return ret;
/*     */   }
/*     */   private static void swapAll(Object[] args, Object swap, Object with) {
/* 212 */     for (int i = 0; i < args.length; i++) if (swap.equals(args[i])) args[i] = with;  
/*     */   }
/*     */ 
/*     */   private static BigInteger factorial(BigInteger x)
/*     */   {
/* 215 */     if (x.longValue() <= 1L) return x;
/* 216 */     if (x.longValue() == 20L) return new BigInteger("2432902008176640000");
/* 217 */     if (x.longValue() == 50L) return new BigInteger("30414093201713378043612608166064768844377641568960512000000000000");
/* 218 */     if (x.longValue() == 70L) return new BigInteger("11978571669969891796070000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
/* 219 */     return x.multiply(factorial(x.subtract(new BigInteger("1"))));
/*     */   }
/*     */ 
/*     */   private static String[][] splitNums(String x) {
/* 223 */     ArrayList ret1 = new ArrayList();
/* 224 */     ArrayList ret2 = new ArrayList();
/* 225 */     boolean flag = true;
/* 226 */     String temp = "";
/* 227 */     for (int i = 0; i < x.length(); i++) {
/* 228 */       if ("%/*+^e:&\\?£".contains(x.charAt(i))) {
/* 229 */         if (flag) {
/* 230 */           flag = !flag;
/* 231 */           ret1.add(temp);
/* 232 */           temp = "";
/*     */         }
/*     */ 
/*     */       }
/* 236 */       else if (!flag) {
/* 237 */         flag = !flag;
/* 238 */         ret2.add(temp);
/* 239 */         temp = "";
/*     */       }
/*     */ 
/* 242 */       temp = temp + x.charAt(i);
/*     */     }
/* 244 */     if (!flag) {
/* 245 */       flag = !flag;
/* 246 */       ret2.add(temp);
/* 247 */       temp = "";
/*     */     }
/*     */     else {
/* 250 */       flag = !flag;
/* 251 */       ret1.add(temp);
/* 252 */       temp = "";
/*     */     }
/*     */ 
/* 255 */     return new String[][] { toStringArray(ret1), toStringArray(ret2) };
/*     */   }
/*     */   public static String[] toStringArray(ArrayList<String> x) {
/* 258 */     String[] ret = new String[x.size()];
/* 259 */     for (int i = 0; i < x.size(); i++) ret[i] = ((String)x.get(i)).toString();
/* 260 */     return ret;
/*     */   }
/*     */   public static boolean toggleRadians() {
/* 263 */     return Calculator.radians = radians ? 0 : 1;
/*     */   }
/*     */   private static String sudoSubtraction(String eq) {
/* 266 */     while ((eq.contains("++")) || (eq.contains("--"))) {
/* 267 */       eq = eq.replaceAll("--", "+");
/* 268 */       eq = eq.replaceAll("\\+\\+", "+");
/*     */     }
/* 270 */     String ret = "";
/* 271 */     if (eq.charAt(0) != '+') ret = ret + eq.charAt(0);
/*     */ 
/* 273 */     for (int i = 1; i < eq.length() - 1; i++) {
/* 274 */       if ((eq.charAt(i) == '-') && (Character.isDigit(eq.charAt(i - 1)))) {
/* 275 */         if (eq.charAt(i + 1) == '+') {
/* 276 */           i++;
/*     */         }
/* 278 */         ret = ret + "+-";
/*     */       }
/*     */       else {
/* 281 */         ret = ret + eq.charAt(i);
/*     */       }
/*     */     }
/* 284 */     return ret + (eq.length() > 1 ? Character.valueOf(eq.charAt(eq.length() - 1)) : "");
/*     */   }
/*     */   private static String expandAfterNodes(String eq) {
/* 287 */     String ret = "";
/* 288 */     for (int i = 0; i < eq.length(); i++) {
/* 289 */       ret = ret + eq.charAt(i);
/* 290 */       if ((eq.charAt(i) == ')') && (i < eq.length() - 1) && (Character.isDigit(eq.charAt(i + 1)))) ret = ret + "*";
/*     */     }
/* 292 */     return ret;
/*     */   }
/*     */   private static String expandMult(String eq) {
/* 295 */     String temp = "";
/* 296 */     eq = eq.replaceAll("\\)\\(", ")*(");
/*     */ 
/* 299 */     int i = eq.indexOf("("); for (int last = 0; i >= 0; i = eq.indexOf("(", i + 1)) {
/* 300 */       if (i != 0) {
/* 301 */         temp = temp + eq.substring(last, i);
/* 302 */         if (Character.isDigit(eq.charAt(i - 1))) temp = temp + "*";
/*     */       }
/* 299 */       last = i;
/*     */     }
/*     */ 
/* 304 */     eq = temp + eq.substring(last);
/*     */ 
/* 306 */     return expandAfterNodes(eq);
/*     */   }
/*     */   private static void replaceBase(String[] nums) {
/* 309 */     for (int i = 0; i < nums.length; i++) {
/* 310 */       String y = BaseConverter.convertToDecimal(nums[i].substring(indexOfDigit(nums[i]), lastIndexOfDigit(nums[i]) + 1), base);
/* 311 */       if (y.endsWith(".0")) y = y.substring(0, y.length() - 2);
/* 312 */       nums[i] = (nums[i].substring(0, indexOfDigit(nums[i])) + y + nums[i].substring(lastIndexOfDigit(nums[i]) + 1));
/*     */     }
/*     */   }
/* 315 */   public static int getBase() { return base; } 
/* 316 */   public static void setBase(int newBase) { base = newBase; } 
/*     */   private static int indexOfDigit(String eq) {
/* 318 */     for (int i = 0; i < eq.length(); i++) if ((Character.isDigit(eq.charAt(i))) || (Character.isUpperCase(eq.charAt(i)))) return i;
/* 319 */     return 0;
/*     */   }
/*     */   private static int lastIndexOfDigit(String eq) {
/* 322 */     for (int i = eq.length() - 1; i >= 0; i--) if ((Character.isDigit(eq.charAt(i))) || (Character.isUpperCase(eq.charAt(i)))) return i;
/* 323 */     return eq.length();
/*     */   }
/*     */   public static void test() {
/*     */   }
/* 327 */   public static void setRadians(boolean istrue) { radians = istrue; }
/*     */ 
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     Calculator
 * JD-Core Version:    0.6.2
 */