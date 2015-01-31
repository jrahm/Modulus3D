/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Scanner;
/*     */ import javax.swing.JTextArea;
/*     */ import utilities.PublicFileClassLoader;
/*     */ 
/*     */ public class ControlPanel
/*     */ {
/*  24 */   private static ArrayList<String> functionList = new ArrayList();
/*  25 */   private static ArrayList<String> files = new ArrayList();
/*  26 */   private static ArrayList<Class> classes = new ArrayList();
/*     */ 
/*  28 */   private static String locs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
/*  29 */   private static ArrayList<String>[] values = null;
/*  30 */   private static boolean init = false;
/*  31 */   private static ArrayList<ArrayList<String>> popped = new ArrayList();
/*  32 */   private static JTextArea comp = null;
/*     */ 
/*  50 */   private static PublicFileClassLoader classFileLoader = new PublicFileClassLoader();
/*     */   public static Method[] objectMethods;
/*  35 */   public static String[] graphFunctions = new String[0];
/*     */ 
/*     */   static
/*     */   {
/*     */     try {
/*  40 */       objectMethods = Class.forName("java.lang.Object").getMethods();
/*     */     } catch (Exception e) {
/*  42 */       e.printStackTrace();
/*  43 */     }init = true;
/*  44 */     popped.add(new ArrayList());
/*  45 */     values = new ArrayList[26];
/*  46 */     for (int i = 0; i < values.length; i++) {
/*  47 */       values[i] = new ArrayList();
/*  48 */       values[i].add("0");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void load(String name) throws Exception
/*     */   {
/*  54 */     if (name.endsWith(".class"))
/*     */     {
/*  57 */       String org = name;
/*     */ 
/*  59 */       name = name.replaceAll("\\\\", "/");
/*  60 */       name = name.substring(0, name.length() - 6);
/*     */ 
/*  62 */       File f = new File(name);
/*  63 */       classFileLoader.addDir(f.getParentFile());
/*     */ 
/*  65 */       name = name.substring(name.lastIndexOf("/") + 1);
/*     */ 
/*  67 */       Class cls = Class.forName(name, true, classFileLoader);
/*  68 */       for (Method method : cls.getMethods()) {
/*  69 */         int mod = method.getModifiers();
/*  70 */         if ((method.getParameterTypes().length >= 1) && (Modifier.isPublic(mod)) && (Modifier.isStatic(mod)) && (!contains(objectMethods, method)) && (method.getParameterTypes()[0].equals(new String[0].getClass())) && 
/*  71 */           (method.getReturnType().equals(new String().getClass())))
/*  72 */           addToFunctionList(method.getName(), org, cls);
/*     */       }
/*  74 */       return;
/*     */     }
/*  76 */     if (name.endsWith(".marc")) {
/*  77 */       name = name.replaceAll("\\\\", "/");
/*  78 */       addToFunctionList(name.substring(name.lastIndexOf("/") + 1, name.indexOf(".")), name, null);
/*     */     }
/*  80 */     File file = new File(name);
/*  81 */     if (!file.exists()) {
/*  82 */       file = new File(new File(ScriptReader.getFile()).getParent(), name);
/*  83 */       name = file.toString();
/*     */     }
/*     */ 
/*  86 */     FileInputStream fstream = new FileInputStream(file);
/*  87 */     DataInputStream in = new DataInputStream(fstream);
/*  88 */     InputStreamReader y = new InputStreamReader(in);
/*  89 */     BufferedReader br = new BufferedReader(y);
/*     */     String str;
/*  91 */     while ((str = br.readLine()) != null)
/*     */     {
/*     */       String str;
/*  92 */       if (str.trim().startsWith("function"))
/*     */       {
/*     */         String x;
/*  94 */         addToFunctionList((x = str.trim().substring(8).trim()).substring(0, x.indexOf("(")).trim(), name, null);
/*     */       }
/*     */     }
/*  97 */     file = null;
/*  98 */     fstream = null;
/*  99 */     in = null;
/* 100 */     y = null;
/* 101 */     br = null;
/*     */   }
/*     */ 
/*     */   public static void launchShell() throws Exception
/*     */   {
/* 106 */     Scanner scan = new Scanner(System.in);
/* 107 */     System.out.print(">>");
/*     */     String line;
/* 108 */     while (!(line = scan.nextLine()).equals("HLT"))
/*     */     {
/*     */       String line;
/* 109 */       parseLine(line);
/* 110 */       System.out.print(">>");
/*     */     }
/*     */   }
/*     */ 
/* 114 */   public static void parseLine(String line) throws Exception { parseLine(line, null); }
/*     */ 
/*     */   public static void parseLine(String line, JTextArea disp) throws Exception
/*     */   {
/* 118 */     String[] split = line.split("\\s+");
/*     */ 
/* 120 */     if (split[0].equalsIgnoreCase("CLRHOME")) {
/* 121 */       if (disp != null) disp.setText("");
/* 122 */       else if (comp != null) comp.setText("");
/*     */     }
/* 124 */     else if (split[0].equalsIgnoreCase("STO"))
/*     */     {
/* 126 */       String[] sec = { line.substring(3, line.lastIndexOf(",")), line.substring(line.lastIndexOf(",") + 1) };
/* 127 */       if (sec[1].contains("[")) {
/* 128 */         String toSolve = sec[1].substring(sec[1].indexOf("[") + 1, sec[1].lastIndexOf("]"));
/*     */ 
/* 130 */         values[locs.indexOf(sec[1].substring(0, sec[1].indexOf("[")))].set(Integer.parseInt(figure(toSolve)), solve(sec[0]).replaceAll("E", "e"));
/*     */       }
/*     */       else {
/* 133 */         values[locs.indexOf(sec[1])].set(0, figure(sec[0]).replaceAll("E", "e"));
/*     */       }
/* 135 */     } else if (split[0].equalsIgnoreCase("STP"))
/*     */     {
/* 137 */       String[] sec = { split[1].substring(0, split[1].lastIndexOf(",")), split[1].substring(split[1].lastIndexOf(",") + 1) };
/* 138 */       parseLine("PUSH " + sec[1]);
/* 139 */       parseLine("STO " + sec[1] + "[1]," + sec[1]);
/* 140 */       parseLine("STO " + split[1]);
/*     */     }
/* 142 */     else if (split[0].equalsIgnoreCase("DISP")) {
/* 143 */       System.out.println(parseOut(line.substring(4)));
/*     */     }
/* 145 */     else if (split[0].equalsIgnoreCase("PUSH"))
/*     */     {
/* 147 */       values[locs.indexOf(split[1])].add(0, "0");
/*     */     }
/* 149 */     else if (split[0].equalsIgnoreCase("POP"))
/*     */     {
/* 151 */       if (values[locs.indexOf(split[1])].size() > 1) {
/* 152 */         ((ArrayList)popped.get(popped.size() - 1)).add(0, (String)values[locs.indexOf(split[1])].get(0));
/* 153 */         values[locs.indexOf(split[1])].remove(0);
/*     */       }
/*     */ 
/*     */     }
/* 157 */     else if (split[0].equalsIgnoreCase("LOD")) {
/* 158 */       load(line.substring(split[0].length()));
/*     */     }
/* 160 */     else if (split[0].equalsIgnoreCase("STACK"))
/*     */     {
/* 162 */       String[] sec = { split[1].substring(0, split[1].lastIndexOf(",")), split[1].substring(split[1].lastIndexOf(",") + 1) };
/* 163 */       values[locs.indexOf(sec[1])].add(figure(sec[0]));
/*     */     }
/* 165 */     else if (split[0].trim().equals("DEL")) {
/* 166 */       if (((ArrayList)popped.get(popped.size() - 1)).size() > 0)
/* 167 */         ((ArrayList)popped.get(popped.size() - 1)).remove(0);
/*     */     }
/* 169 */     else if (split[0].equalsIgnoreCase("PROMPT"))
/*     */     {
/* 171 */       String[] sec = { line.substring(6, line.lastIndexOf(",")), line.substring(line.lastIndexOf(",")) };
/* 172 */       SwingPrompter prompt = new SwingPrompter();
/* 173 */       parseLine("STO " + SwingPrompter.getPrompt(sec) + sec[1]);
/*     */     }
/* 175 */     else if (split[0].equalsIgnoreCase("SLEEP"))
/*     */     {
/* 177 */       long crt = System.currentTimeMillis();
/* 178 */       long to = Long.parseLong(split[1].trim());
/* 179 */       while (System.currentTimeMillis() - crt < to);
/*     */     }
/* 183 */     else if (disp != null) { comp.append(figure(line) + "\n");
/* 184 */     } else if (comp != null) { comp.append(figure(line) + "\n"); } else {
/* 185 */       System.out.println(figure(line));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String solve(String eq)
/*     */     throws Exception
/*     */   {
/* 200 */     eq = parseOutVars(eq);
/*     */ 
/* 202 */     return Calculator.solve(eq);
/*     */   }
/*     */ 
/*     */   public static String figure(String line) throws Exception {
/* 206 */     line = parseOutFunctions(line);
/* 207 */     if (TernarySolver.containsTernary(removeParan(line))) {
/* 208 */       line = solveTernary(line);
/*     */     }
/* 210 */     if (BooleanLogic.containsBoolean(removeParan(line))) {
/* 211 */       return solveBoolean(line);
/*     */     }
/* 213 */     return solve(line);
/*     */   }
/*     */ 
/*     */   public static String solveBoolean(String eq) throws Exception {
/* 217 */     eq = parseOutVars(eq);
/* 218 */     return BooleanLogic.solve(eq);
/*     */   }
/*     */ 
/*     */   public static String solveTernary(String eq) throws Exception {
/* 222 */     eq = parseOutVars(eq);
/* 223 */     return TernarySolver.solve(eq);
/*     */   }
/*     */ 
/*     */   public static String solveTernaryCP(String eq) throws Exception {
/* 227 */     eq = parseOutVars(eq);
/* 228 */     return TernarySolver.solveCP(eq);
/*     */   }
/*     */   public static String parseOutInnerVoids(String x) {
/* 231 */     return "";
/*     */   }
/*     */   public static String parseOutVars(String eq) {
/* 234 */     if (Calculator.getBase() != 10) return eq;
/* 235 */     for (int i = 0; i < ((ArrayList)popped.get(popped.size() - 1)).size(); i++) {
/* 236 */       eq = eq.replaceAll("Hold\\[" + i + "\\]", "(" + (String)((ArrayList)popped.get(popped.size() - 1)).get(i) + ")");
/*     */     }
/* 238 */     if (((ArrayList)popped.get(popped.size() - 1)).size() > 0) eq = eq.replaceAll("Hold", "(" + (String)((ArrayList)popped.get(popped.size() - 1)).get(0) + ")");
/* 239 */     eq = eq.replaceAll("pi", "(3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421)");
/* 240 */     for (int i = 0; i < values.length; i++) {
/* 241 */       for (int j = 0; j < values[i].size(); j++) {
/* 242 */         eq = eq.replaceAll(locs.charAt(i) + "\\[" + j + "\\]", (String)values[i].get(j));
/*     */       }
/* 244 */       eq = eq.replaceAll(locs.charAt(i), "(" + (String)values[i].get(0) + ")");
/*     */     }
/* 246 */     eq = eq.replaceAll("time", System.currentTimeMillis());
/* 247 */     return eq;
/*     */   }
/*     */   public static String parseOut(String eq) throws Exception {
/* 250 */     if (!eq.contains("{")) return eq;
/* 251 */     return parseOut(eq.substring(0, eq.lastIndexOf("{")) + 
/* 252 */       figure(eq.substring(eq.lastIndexOf("{") + 1, eq.indexOf("}", eq.lastIndexOf("{")))) + 
/* 253 */       eq.substring(eq.indexOf("}", eq.lastIndexOf("{")) + 1));
/*     */   }
/*     */   public static String parseOutFunctions(String x) throws Exception {
/* 256 */     for (int i = 0; i < functionList.size(); i++) {
/* 257 */       while (x.contains((CharSequence)functionList.get(i))) {
/* 258 */         popped.add(new ArrayList());
/* 259 */         x = x.substring(0, x.indexOf((String)functionList.get(i))) + 
/* 260 */           ScriptReader.preformFunction(x.substring(x.indexOf((String)functionList.get(i)), 
/* 261 */           match(x, x.indexOf((String)functionList.get(i))) + 1), 
/* 262 */           (String)files.get(i), 
/* 263 */           new Object[] { classes.get(i) }) + 
/* 264 */           x.substring(match(x, x.indexOf((String)functionList.get(i))) + 1);
/* 265 */         popped.remove(popped.size() - 1);
/*     */       }
/*     */     }
/* 268 */     for (int j = 0; j < graphFunctions.length; j++) {
/* 269 */       int i = j + 1;
/* 270 */       while (x.contains("y" + i)) {
/* 271 */         x = x.substring(0, x.indexOf(new StringBuilder("y").append(i).toString())) + 
/* 272 */           figure(graphFunctions[j].replaceAll("X", figure(x.substring(x.indexOf(new StringBuilder("y").append(i).toString()) + 3, 
/* 273 */           match(x, x.indexOf(new StringBuilder("y").append(i).toString())))))) + 
/* 274 */           x.substring(match(x, x.indexOf(new StringBuilder("y").append(i).toString())) + 1);
/*     */       }
/*     */     }
/* 277 */     return x;
/*     */   }
/*     */   private static int match(String eq, int index) {
/* 280 */     while (eq.charAt(index) != '(') index++;
/* 281 */     int i = index + 1; for (int x = 1; i < eq.length(); i++) {
/* 282 */       if (eq.charAt(i) == ')') x--;
/* 283 */       else if (eq.charAt(i) == '(') x++;
/* 284 */       if (x == 0) return i;
/*     */     }
/* 286 */     return -1;
/*     */   }
/*     */   public static void addToFunctionList(String x, String fileName, Class cls) {
/* 289 */     if (functionList.contains(x)) return;
/* 290 */     functionList.add(x);
/* 291 */     files.add(fileName);
/* 292 */     classes.add(cls);
/*     */   }
/*     */   private static int lastIndexOf(String arg, String[] args) {
/* 295 */     int max = -1;
/* 296 */     for (String i : args) {
/* 297 */       if (arg.indexOf(i) > max) max = arg.lastIndexOf(i);
/*     */     }
/* 299 */     return max;
/*     */   }
/*     */   private static int indexOf(String arg, String[] args) {
/* 302 */     int max = -1;
/* 303 */     for (String i : args) {
/* 304 */       if ((arg.indexOf(i) < max) || (max == -1)) max = arg.indexOf(i);
/*     */     }
/* 306 */     return max;
/*     */   }
/*     */   public static void setComponent(JTextArea comp) {
/* 309 */     comp = comp;
/*     */   }
/* 311 */   public static Object[] getFunctions() { return functionList.toArray(); } 
/* 312 */   public static Object[] getFiles() { return files.toArray(); } 
/*     */   public static String removeParan(String eq) {
/* 314 */     int x = 0;
/* 315 */     String temp = "";
/* 316 */     String ret = "";
/*     */ 
/* 318 */     for (int i = 0; i < eq.length(); i++) {
/* 319 */       if (eq.charAt(i) == '(') x++;
/* 320 */       else if (eq.charAt(i) == ')') x--;
/* 321 */       else if (x == 0) ret = ret + eq.charAt(i);
/*     */     }
/*     */ 
/* 324 */     if (ret.equals("")) ret = removeParan(eq.substring(1, eq.length() - 1));
/* 325 */     return ret;
/*     */   }
/*     */   public static ArrayList<String>[] getStacks() {
/* 328 */     return values;
/*     */   }
/*     */   public static ArrayList<String> getCurrentHold() {
/* 331 */     return (ArrayList)popped.get(popped.size() - 1);
/*     */   }
/* 333 */   public static boolean isInit() { return init; } 
/*     */   public static void reset() {
/* 335 */     functionList = new ArrayList();
/* 336 */     files = new ArrayList();
/*     */   }
/*     */   public static ArrayList<String> getFunctionList() {
/* 339 */     return functionList;
/*     */   }
/*     */   public static ArrayList<Class> getClasses() {
/* 342 */     return classes;
/*     */   }
/*     */   private static boolean contains(Object[] x, Object y) {
/* 345 */     Object[] arrayOfObject = x; int j = x.length; for (int i = 0; i < j; i++) { Object z = arrayOfObject[i];
/* 346 */       if (z.equals(y)) return true;
/*     */     }
/* 348 */     return false;
/*     */   }
/*     */   public static void test() {
/*     */   }
/* 352 */   public static void setGraphFunctions(String[] func) { graphFunctions = func; } 
/*     */   public static PublicFileClassLoader getClassLoader() {
/* 354 */     return classFileLoader;
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     ControlPanel
 * JD-Core Version:    0.6.2
 */