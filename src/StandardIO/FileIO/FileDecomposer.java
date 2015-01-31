/*    */ package StandardIO.FileIO;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ public class FileDecomposer
/*    */ {
/*    */   public static String[] decompose(String file)
/*    */     throws IOException
/*    */   {
/* 14 */     return decompose(new File(file));
/*    */   }
/*    */   public static String[] decompose(File file) throws IOException {
/* 17 */     String[] toReturn = new String[getCountOf(file)];
/* 18 */     int count = 0;
/*    */ 
/* 20 */     FileInputStream fstream = new FileInputStream(file);
/* 21 */     DataInputStream in = new DataInputStream(fstream);
/* 22 */     BufferedReader br = new BufferedReader(new InputStreamReader(in));
/* 23 */     String str = "";
/* 24 */     while ((str = br.readLine()) != null) {
/* 25 */       toReturn[count] = str;
/* 26 */       count++;
/*    */     }
/*    */ 
/* 29 */     fstream.close();
/* 30 */     in.close();
/* 31 */     br.close();
/*    */ 
/* 33 */     return toReturn;
/*    */   }
/*    */   public static int getCountOf(File f) {
/*    */     try {
/* 37 */       int count = 0;
/* 38 */       FileInputStream fstream = new FileInputStream(f);
/* 39 */       DataInputStream in = new DataInputStream(fstream);
/* 40 */       BufferedReader br = new BufferedReader(new InputStreamReader(in));
/*    */ 
/* 42 */       while (br.readLine() != null) count++;
/*    */ 
/* 44 */       fstream.close();
/* 45 */       in.close();
/* 46 */       br.close();
/*    */ 
/* 48 */       return count;
/*    */     }
/*    */     catch (Exception e) {
/* 51 */       e.printStackTrace();
/* 52 */     }return 0;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.FileIO.FileDecomposer
 * JD-Core Version:    0.6.2
 */