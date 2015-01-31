/*    */ package StandardIO;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ public class MFileFilter extends javax.swing.filechooser.FileFilter
/*    */ {
/*    */   java.io.FileFilter filter;
/*    */   String description;
/*    */   String[] ext;
/*    */ 
/*    */   public MFileFilter(java.io.FileFilter f, String description)
/*    */   {
/* 18 */     this.filter = f;
/* 19 */     this.description = description;
/* 20 */     this.ext = null;
/*    */   }
/*    */ 
/*    */   public MFileFilter(String[] acceptedExtensions, String description) {
/* 24 */     this.ext = acceptedExtensions;
/* 25 */     this.description = description;
/* 26 */     this.filter = null;
/*    */   }
/*    */   public boolean accept(File f) {
/* 29 */     if (f.isDirectory()) return true;
/* 30 */     if (this.filter != null)
/* 31 */       return this.filter.accept(f);
/* 32 */     for (String ex : this.ext)
/* 33 */       if (f.toString().endsWith(ex))
/* 34 */         return true;
/* 35 */     return false;
/*    */   }
/*    */   public String getDescription() {
/* 38 */     return this.description;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.MFileFilter
 * JD-Core Version:    0.6.2
 */