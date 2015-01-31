/*    */ package StandardIO;
/*    */ 
/*    */ import StandardIO.FileIO.FileDecomposer;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class PropertiesReader
/*    */ {
/*    */   private String dir;
/*    */ 
/*    */   public PropertiesReader(String directory)
/*    */   {
/* 11 */     this.dir = directory;
/*    */   }
/*    */   public String[] readProperties() {
/* 14 */     String[] file = new String[0];
/* 15 */     String[] toReturn = new String[6];
/*    */     try {
/* 17 */       file = FileDecomposer.decompose(this.dir);
/*    */     }
/*    */     catch (IOException e) {
/* 20 */       System.out.println("Properties Not Found");
/* 21 */       return new String[] { "1234567890\\QWERTYUIOP\\ASDFGHJKL\\ZXCVBNM", "none", "none", "none", "full", "false" };
/*    */     }
/*    */ 
/* 24 */     for (String str : file) {
/* 25 */       if (str.startsWith("keyboard-style"))
/* 26 */         if (str.substring(str.indexOf(":") + 1).trim().equals("QWERTY")) toReturn[0] = "1234567890\\QWERTYUIOP\\ASDFGHJKL\\ZXCVBNM";
/* 27 */         else if (str.substring(str.indexOf(":") + 1).trim().equals("DVORAK")) toReturn[0] = "1234567890\\PYFGCRL\\AOEUIDHTNS\\QJKXBMWVZ";
/* 28 */         else if (str.substring(str.indexOf(":") + 1).trim().equals("ALPHABETICAL")) toReturn[0] = "0123456789\\ABCDEFGHIJ\\KLMNOPQRST\\UVWXYZ";
/* 29 */       if (str.startsWith("keyboard")) toReturn[0] = str.substring(str.indexOf(":") + 1);
/* 30 */       if (str.startsWith("ColorKeys")) toReturn[1] = str.substring(str.indexOf(":") + 1);
/* 31 */       if (str.startsWith("LastFunctionDir")) toReturn[2] = str.substring(str.indexOf(":") + 1);
/* 32 */       if (str.startsWith("LastLoadDir")) toReturn[3] = str.substring(str.indexOf(":") + 1);
/* 33 */       if (str.startsWith("calcsize:")) toReturn[4] = str.substring(str.indexOf(":") + 1);
/* 34 */       if (str.startsWith("ontop:")) toReturn[5] = str.substring(str.indexOf(":") + 1);
/*    */     }
/* 36 */     return toReturn;
/*    */   }
/*    */   public void writeProperties(String[] props) {
/* 39 */     String[] scrpt = { "keyboard-style:", "ColorKeys:", "LastFunctionDir:", "LastLoadDir:", "calcsize:", "ontop:" };
/*    */     try {
/* 41 */       BufferedWriter out = new BufferedWriter(new FileWriter(this.dir));
/* 42 */       for (int i = 0; i < props.length; i++) {
/* 43 */         out.write(scrpt[i] + props[i] + "\n");
/*    */       }
/* 45 */       out.close();
/*    */     }
/*    */     catch (IOException localIOException)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.PropertiesReader
 * JD-Core Version:    0.6.2
 */