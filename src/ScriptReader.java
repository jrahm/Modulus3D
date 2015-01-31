/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ScriptReader
/*     */ {
/*     */   private static int idx;
/*  15 */   private static int tst = 0;
/*  16 */   private static boolean lastBoolean = false;
/*  17 */   private static String currentFile = "";
/*     */   public static final String MARC_DIR = "I:/Computer Stuff/Marc Virtual Machine/";
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  21 */       System.loadLibrary("javamarc");
/*     */     }
/*     */     catch (UnsatisfiedLinkError e) {
/*  24 */       e.printStackTrace();
/*  25 */       System.out.println("Marc Virtual Machine Could Not Be Loaded"); }  } 
/*     */   public static native void setIndex(int paramInt);
/*     */ 
/*     */   public static native void interpretFrom(int paramInt1, int[] paramArrayOfInt, int paramInt2);
/*     */ 
/*     */   public static native int getFromStack(int paramInt);
/*     */ 
/*     */   public static native int stackLength();
/*     */ 
/*     */   public static native void printData();
/*     */ 
/*     */   public static native void run(String paramString);
/*     */ 
/*     */   public static native void interpret(String paramString);
/*     */ 
/*     */   public static native void push(int paramInt);
/*     */ 
/*     */   public static native void clrstk();
/*     */ 
/*  38 */   public static int tempRun(String file) { run(file);
/*  39 */     return getFromStack(0); }
/*     */ 
/*     */   public static void go(String file) {
/*     */   }
/*     */ 
/*     */   public static ArrayList<String> loadUp(String name) throws Exception {
/*  45 */     FileInputStream fstream = new FileInputStream(new File(name));
/*  46 */     DataInputStream in = new DataInputStream(fstream);
/*  47 */     BufferedReader br = new BufferedReader(new InputStreamReader(in));
/*  48 */     ArrayList args = new ArrayList();
/*     */     String str;
/*  50 */     while ((str = br.readLine()) != null)
/*     */     {
/*     */       String str;
/*  51 */       if ((!str.trim().equals("")) && (!str.trim().startsWith(";")))
/*  52 */         args.add(str.trim());
/*  53 */       if (str.trim().startsWith("function"))
/*     */       {
/*     */         String x;
/*  55 */         ControlPanel.addToFunctionList((x = str.trim().substring(8).trim()).substring(0, x.indexOf("(")).trim(), name, null);
/*     */       }
/*     */     }
/*  58 */     currentFile = name;
/*  59 */     return args;
/*     */   }
/*     */ 
/*     */   public static String runScript(String name) throws Exception {
/*  63 */     return run(loadUp(name));
/*     */   }
/*     */   private static String run(ArrayList<String> args) throws Exception {
/*  66 */     for (idx = 0; idx < args.size(); idx += 1) {
/*  67 */       if (Thread.currentThread().isInterrupted())
/*     */       {
/*  69 */         return "";
/*     */       }
/*  71 */       if (((String)args.get(idx)).equals("HLT")) return "";
/*  72 */       if ((!((String)args.get(idx)).equals("then")) && (!((String)args.get(idx)).equals("end"))) {
/*  73 */         if (((String)args.get(idx)).equals("continue")) return null;
/*  74 */         if (((String)args.get(idx)).startsWith("return")) {
/*  75 */           if ((((String)args.get(idx)).endsWith("return")) && (((String)args.get(idx)).length() == 6)) return "";
/*  76 */           return ControlPanel.parseOut(((String)args.get(idx)).substring(7));
/*     */         }
/*  78 */         if (((String)args.get(idx)).startsWith("if")) {
/*  79 */           if (ControlPanel.figure(((String)args.get(idx)).substring(2)).equals("0")) {
/*  80 */             if (!((String)args.get(idx + 1)).equals("then")) idx += 1; else
/*  81 */               idx = indexOfCorrect(args, idx + 1);
/*  82 */             lastBoolean = false;
/*     */           }
/*  84 */           else if (((String)args.get(idx + 1)).equals("then")) {
/*  85 */             lastBoolean = true;
/*  86 */             idx += 1;
/*     */           } else {
/*  88 */             lastBoolean = true;
/*     */           }
/*  90 */         } else if (((String)args.get(idx)).startsWith("elseif")) {
/*  91 */           if ((lastBoolean) || (ControlPanel.figure(((String)args.get(idx)).substring(6)).equals("0"))) {
/*  92 */             if (!((String)args.get(idx + 1)).equals("then")) idx += 1; else
/*  93 */               idx = indexOfCorrect(args, idx + 1);
/*  94 */             lastBoolean = false;
/*     */           }
/*  96 */           else if (((String)args.get(idx + 1)).equals("then")) {
/*  97 */             lastBoolean = true;
/*  98 */             idx += 1;
/*     */           } else {
/* 100 */             lastBoolean = true;
/*     */           }
/* 102 */         } else if (((String)args.get(idx)).startsWith("else")) {
/* 103 */           if (lastBoolean) {
/* 104 */             if (!((String)args.get(idx + 1)).equals("then")) idx += 1; else
/* 105 */               idx = indexOfCorrect(args, idx + 1);
/*     */           }
/* 107 */           else if (((String)args.get(idx + 1)).equals("then")) {
/* 108 */             lastBoolean = true;
/* 109 */             idx += 1;
/*     */           } else {
/* 111 */             lastBoolean = true;
/*     */           }
/* 113 */         } else if (((String)args.get(idx)).startsWith("while")) {
/* 114 */           if (ControlPanel.figure(((String)args.get(idx)).substring(5)).equals("0")) {
/* 115 */             if (!((String)args.get(idx)).equals("then")) idx += 1; else
/* 116 */               idx = indexOfCorrect(args, idx + 1);
/*     */           }
/*     */           else
/*     */           {
/* 120 */             ArrayList chomp;
/*     */             ArrayList chomp;
/* 120 */             if (!((String)args.get(idx + 1)).trim().equals("then")) chomp = subArray(args, idx + 1, idx + 2); else
/* 121 */               chomp = subArray(args, idx + 2, indexOfCorrect(args, idx + 2));
/* 122 */             int idxC = idx;
/* 123 */             int neIdx = 0;
/* 124 */             while (!ControlPanel.figure(((String)args.get(idx)).substring(5)).equals("0"))
/*     */             {
/* 126 */               String x = run(chomp);
/* 127 */               if (x != null) return x;
/* 128 */               idx = idxC;
/*     */             }
/* 130 */             if (!((String)args.get(idxC + 1)).trim().equals("then"))
/* 131 */               idx = idxC + 1;
/*     */             else
/* 133 */               idx = indexOfCorrect(args, idxC + 2) - 1;
/*     */           }
/*     */         }
/*     */         else
/* 137 */           ControlPanel.parseLine((String)args.get(idx));
/*     */       }
/*     */     }
/* 140 */     return null;
/*     */   }
/*     */   private static int indexOfLastControl(ArrayList<String> args, int from) {
/* 143 */     int i = from;
/* 144 */     while ((((String)args.get(i)).startsWith("if")) || (((String)args.get(i)).startsWith("else")) || (((String)args.get(i)).startsWith("while"))) i++;
/* 145 */     return i + 1;
/*     */   }
/*     */   private static int indexOfCorrect(ArrayList<String> args, int from) {
/* 148 */     int i = 0;
/*     */ 
/* 151 */     i = from; for (int x = 1; x > 0; i++) {
/* 152 */       if (((String)args.get(i)).startsWith("then")) x++;
/* 153 */       else if (((String)args.get(i)).startsWith("end")) x--;
/*     */     }
/* 155 */     return i;
/*     */   }
/*     */   private static int indexOf(ArrayList<Object> args, Object arg, int from) {
/* 158 */     for (int i = from; i < args.size(); i++) {
/* 159 */       if (args.get(i).equals(arg)) return i;
/*     */     }
/* 161 */     return -1;
/*     */   }
/*     */   private static ArrayList<String> subArray(ArrayList<String> args, int from, int to) {
/* 164 */     ArrayList ret = new ArrayList();
/* 165 */     for (int i = from; i < to; i++) ret.add((String)args.get(i));
/* 166 */     return ret;
/*     */   }
/*     */   public static String preformFunction(String function, ArrayList<String> file) throws Exception {
/* 169 */     int idxC = idx;
/* 170 */     String toSplit = function.substring(function.indexOf("(") + 1, function.lastIndexOf(")"));
/* 171 */     String[] splitted = toSplit.split(",");
/*     */ 
/* 174 */     int index = 0;
/*     */     String str;
/* 175 */     while (!(str = (String)file.get(index++)).startsWith("function " + function.substring(0, function.indexOf("(") + 1)));
/* 176 */     String arg = str;
/* 177 */     String[] splitArg = arg.substring(arg.indexOf("(") + 1, arg.lastIndexOf(")")).split(",");
/* 178 */     if (splitArg.length != splitted.length) throw new Exception("The Parameters do not match");
/* 179 */     int x = 1;
/* 180 */     ArrayList compileList = new ArrayList();
/*     */ 
/* 182 */     while (((str = (String)file.get(index++)) != null) && (x > 0)) {
/* 183 */       if (str.startsWith("then")) x++;
/* 184 */       else if (str.startsWith("end")) x--;
/* 185 */       if ((x != 0) && 
/* 186 */         (!str.trim().equals(""))) {
/* 187 */         compileList.add(str.trim());
/*     */       }
/*     */     }
/* 190 */     for (int i = 0; i < splitArg.length; i++) {
/* 191 */       ControlPanel.parseLine("STP " + splitted[i] + "," + splitArg[i]);
/*     */     }
/*     */ 
/* 194 */     String ret = run(compileList);
/*     */ 
/* 196 */     for (int i = 0; i < splitArg.length; i++) {
/* 197 */       ControlPanel.parseLine("POP " + splitArg[i]);
/*     */     }
/* 199 */     idx = idxC;
/* 200 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String preformFunction(String function, String fileName, Object[] objs) throws Exception {
/* 204 */     int idxC = idx;
/* 205 */     String temp = function.substring(0, function.indexOf("("));
/* 206 */     if ((!contains(ControlPanel.getFunctions(), temp)) || (!contains(ControlPanel.getFiles(), fileName)))
/* 207 */       ControlPanel.addToFunctionList(temp, fileName, null);
/* 208 */     if (fileName.endsWith(".marc"))
/*     */     {
/* 210 */       String toSplit = function.substring(function.indexOf("(") + 1, function.lastIndexOf(")"));
/* 211 */       String[] split = toSplit.split(",");
/* 212 */       for (int i = split.length - 1; i >= 0; i--)
/* 213 */         if (!split[i].equals("")) {
/* 214 */           String s = split[i];
/*     */ 
/* 216 */           push(Integer.parseInt(ControlPanel.figure(s)));
/*     */         }
/* 218 */       run(fileName);
/* 219 */       String ret = getFromStack(0);
/* 220 */       clrstk();
/* 221 */       return ret;
/*     */     }
/* 223 */     if (fileName.endsWith(".class")) {
/* 224 */       fileName = fileName.replaceAll("\\\\", "/");
/*     */ 
/* 226 */       MethodClass cls = new MethodClass((Class)objs[0]);
/* 227 */       Method methodToParse = cls.getMethod(function.substring(0, function.indexOf("(")));
/*     */ 
/* 231 */       String[] arguments = function.substring(function.indexOf("(") + 1, function.lastIndexOf(")")).split(",");
/* 232 */       for (int i = 0; i < arguments.length; i++) {
/* 233 */         if ((arguments[i].startsWith("\"")) && (arguments[i].endsWith("\"")))
/* 234 */           arguments[i] = ControlPanel.parseOut(arguments[i].substring(1, arguments[i].length() - 1));
/*     */         else {
/* 236 */           arguments[i] = ControlPanel.figure(arguments[i]);
/*     */         }
/*     */       }
/* 239 */       Object ret = methodToParse.invoke(null, new Object[] { arguments });
/* 240 */       return ret.toString();
/*     */     }
/* 242 */     String toSplit = function.substring(function.indexOf("(") + 1, function.lastIndexOf(")"));
/* 243 */     String[] splitted = toSplit.split(",");
/*     */ 
/* 245 */     FileInputStream fstream = new FileInputStream(new File(fileName));
/* 246 */     DataInputStream in = new DataInputStream(fstream);
/*     */     BufferedReader br;
/*     */     String str;
/* 250 */     while (!(str = br.readLine()).startsWith("function " + function.substring(0, function.indexOf("(") + 1)));
/* 251 */     String arg = str;
/* 252 */     String[] splitArg = arg.substring(arg.indexOf("(") + 1, arg.lastIndexOf(")")).split(",");
/*     */ 
/* 255 */     if (splitArg.length != splitted.length) throw new Exception("The Parameters do not match");
/* 256 */     int x = 1;
/* 257 */     ArrayList compileList = new ArrayList();
/*     */ 
/* 259 */     while (((str = br.readLine()) != null) && (x > 0)) {
/* 260 */       if ((str.startsWith("if")) || (str.startsWith("else")) || (str.startsWith("while"))) x++;
/* 261 */       else if (str.startsWith("end")) x--;
/* 262 */       if ((x != 0) && 
/* 263 */         (!str.trim().equals(""))) {
/* 264 */         compileList.add(str.trim());
/*     */       }
/*     */     }
/* 267 */     for (int i = 0; i < splitArg.length; i++) {
/* 268 */       ControlPanel.parseLine("STP " + splitted[i] + "," + splitArg[i]);
/*     */     }
/*     */ 
/* 271 */     String ret = run(compileList);
/*     */ 
/* 273 */     for (int i = 0; i < splitArg.length; i++) {
/* 274 */       ControlPanel.parseLine("POP " + splitArg[i]);
/*     */     }
/* 276 */     idx = idxC;
/* 277 */     return ret;
/*     */   }
/*     */   private static boolean contains(Object[] x, Object y) {
/* 280 */     for (int i = 0; i < x.length; i++) if (x[i].equals(y)) return true;
/* 281 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getFile() {
/* 285 */     return currentFile;
/*     */   }
/*     */   public static String remesh(String[] args) {
/* 288 */     String ret = "";
/* 289 */     String[] arrayOfString = args; int j = args.length; for (int i = 0; i < j; i++) { String x = arrayOfString[i];
/* 290 */       ret = ret + x;
/* 291 */       ret = ret + ",";
/*     */     }
/* 293 */     ret = ret.substring(0, ret.length() - 1);
/* 294 */     return ret;
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     ScriptReader
 * JD-Core Version:    0.6.2
 */