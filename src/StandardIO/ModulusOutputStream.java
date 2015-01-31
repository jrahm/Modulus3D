/*     */ package StandardIO;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JTextArea;
/*     */ 
/*     */ public class ModulusOutputStream extends PrintStream
/*     */ {
/*     */   private JTextArea textAreaToAppend;
/*     */   private FileOutputArray output;
/*     */ 
/*     */   public ModulusOutputStream(OutputStream out, boolean autoFlush)
/*     */   {
/*  22 */     super(out, autoFlush);
/*  23 */     this.output = new FileOutputArray();
/*     */   }
/*     */ 
/*     */   public ModulusOutputStream(JTextArea append, OutputStream out, boolean autoFlush)
/*     */   {
/*  30 */     super(out, autoFlush);
/*  31 */     this.textAreaToAppend = append;
/*  32 */     this.output = new FileOutputArray();
/*     */   }
/*     */ 
/*     */   public void print(String s)
/*     */   {
/*     */     try
/*     */     {
/*  41 */       this.textAreaToAppend.append(s);
/*  42 */       this.output.append(s);
/*     */     }
/*     */     catch (Exception e) {
/*  45 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void print(int s) {
/*     */     try {
/*  51 */       this.textAreaToAppend.append(s);
/*  52 */       this.output.append(s);
/*     */     }
/*     */     catch (Exception e) {
/*  55 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void print(float s) {
/*     */     try {
/*  61 */       this.textAreaToAppend.append(s);
/*  62 */       this.output.append(s);
/*     */     }
/*     */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void print(double s) {
/*     */     try {
/*  71 */       this.textAreaToAppend.append(s);
/*  72 */       this.output.append(s);
/*     */     }
/*     */     catch (Exception e) {
/*  75 */       e.printStackTrace();
/*  76 */       this.output.append(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void print(boolean s) {
/*     */     try {
/*  82 */       this.textAreaToAppend.append(s);
/*  83 */       this.output.append(s);
/*     */     }
/*     */     catch (Exception e) {
/*  86 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void print(char s) {
/*     */     try {
/*  92 */       this.textAreaToAppend.append(s);
/*  93 */       this.output.append(s);
/*     */     }
/*     */     catch (Exception e) {
/*  96 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/* 100 */   public void print(char[] s) { print(new String(s)); }
/*     */ 
/*     */   public void println(String s)
/*     */   {
/*     */     try {
/* 105 */       this.textAreaToAppend.append(s + "\n");
/* 106 */       this.output.append(s + "\n");
/*     */     }
/*     */     catch (Exception e) {
/* 109 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void println(int s) {
/*     */     try {
/* 115 */       this.textAreaToAppend.append(s + "\n");
/* 116 */       this.output.append(s + "\n");
/*     */     }
/*     */     catch (Exception e) {
/* 119 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void println(float s) {
/*     */     try {
/* 125 */       this.textAreaToAppend.append(s + "\n");
/* 126 */       this.output.append(s + "\n");
/*     */     }
/*     */     catch (Exception e) {
/* 129 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void println(double s) {
/*     */     try {
/* 135 */       this.textAreaToAppend.append(s + "\n");
/* 136 */       this.output.append(s + "\n");
/*     */     }
/*     */     catch (Exception e) {
/* 139 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void println(boolean s) {
/*     */     try {
/* 145 */       this.textAreaToAppend.append(s + "\n");
/* 146 */       this.output.append(s + "\n");
/*     */     }
/*     */     catch (Exception e) {
/* 149 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void println(char s) {
/*     */     try {
/* 155 */       this.textAreaToAppend.append(s + "\n");
/* 156 */       this.output.append(s + "\n");
/*     */     }
/*     */     catch (Exception e) {
/* 159 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/* 163 */   public void println(char[] s) { println(new String(s)); }
/*     */ 
/*     */   public void silentPrint(String prnt) {
/* 166 */     this.output.append(prnt);
/*     */   }
/*     */   public void flushTo(File file) {
/* 169 */     this.output.flushToFile(file);
/*     */   }
/*     */   public void flushTo(String file) {
/* 172 */     this.output.flushToFile(file);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.ModulusOutputStream
 * JD-Core Version:    0.6.2
 */