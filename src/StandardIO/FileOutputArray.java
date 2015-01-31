/*    */ package StandardIO;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FileOutputArray
/*    */ {
/*    */   private List<String> output;
/*    */ 
/*    */   public FileOutputArray()
/*    */   {
/* 15 */     this.output = new ArrayList();
/*    */   }
/*    */   public void flushToFile(String file) {
/*    */     try {
/* 19 */       PrintWriter stream = new SaveFileWriter(file);
/* 20 */       for (int i = 0; i < this.output.size(); i++) {
/* 21 */         stream.print((String)this.output.get(i));
/*    */       }
/* 23 */       stream.flush();
/* 24 */       stream.close();
/*    */     }
/*    */     catch (IOException e) {
/* 27 */       e.printStackTrace();
/* 28 */       System.err.println("File could not be written.");
/*    */     }
/*    */   }
/*    */ 
/* 32 */   public void flushToFile(File f) { flushToFile(f.toString()); }
/*    */ 
/*    */   public void reset() {
/* 35 */     this.output = new ArrayList();
/*    */   }
/*    */   public void setOutput(List<String> arr) {
/* 38 */     this.output = arr;
/*    */   }
/*    */   public void append(String what) {
/* 41 */     this.output.add(what);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.FileOutputArray
 * JD-Core Version:    0.6.2
 */