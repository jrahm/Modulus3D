/*    */ package utilities;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URI;
/*    */ import java.net.URL;
/*    */ import java.net.URLClassLoader;
/*    */ 
/*    */ public class PublicFileClassLoader extends URLClassLoader
/*    */ {
/*    */   public PublicFileClassLoader()
/*    */   {
/* 15 */     super(new URL[0]);
/*    */   }
/*    */   public PublicFileClassLoader(String dir) throws MalformedURLException {
/* 18 */     this(new File(dir));
/*    */   }
/*    */   public PublicFileClassLoader(File directory) throws MalformedURLException {
/* 21 */     super(new URL[] { directory.toURI().toURL() });
/*    */   }
/*    */   public PublicFileClassLoader(File[] dirs) throws MalformedURLException {
/* 24 */     this();
/* 25 */     for (File f : dirs) addURL(f.toURI().toURL()); 
/*    */   }
/*    */ 
/* 28 */   public void addURL(URL url) { super.addURL(url); }
/*    */ 
/*    */   public boolean addDir(String dir) {
/* 31 */     return addDir(new File(dir));
/*    */   }
/*    */   public boolean addDir(File dir) {
/*    */     try {
/* 35 */       addURL(dir.toURI().toURL());
/* 36 */       return true;
/*    */     } catch (MalformedURLException e) {
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     utilities.PublicFileClassLoader
 * JD-Core Version:    0.6.2
 */