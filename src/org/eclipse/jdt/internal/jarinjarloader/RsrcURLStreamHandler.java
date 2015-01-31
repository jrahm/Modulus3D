/*    */ package org.eclipse.jdt.internal.jarinjarloader;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.net.URLStreamHandler;
/*    */ 
/*    */ public class RsrcURLStreamHandler extends URLStreamHandler
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */ 
/*    */   public RsrcURLStreamHandler(ClassLoader classLoader)
/*    */   {
/* 33 */     this.classLoader = classLoader;
/*    */   }
/*    */ 
/*    */   protected URLConnection openConnection(URL u) throws IOException {
/* 37 */     return new RsrcURLConnection(u, this.classLoader);
/*    */   }
/*    */ 
/*    */   protected void parseURL(URL url, String spec, int start, int limit)
/*    */   {
/*    */     String file;
/*    */     String file;
/* 42 */     if (spec.startsWith("rsrc:")) {
/* 43 */       file = spec.substring(5);
/*    */     }
/*    */     else
/*    */     {
/*    */       String file;
/* 44 */       if (url.getFile().equals("./")) {
/* 45 */         file = spec;
/*    */       }
/*    */       else
/*    */       {
/*    */         String file;
/* 46 */         if (url.getFile().endsWith("/"))
/* 47 */           file = url.getFile() + spec;
/*    */         else
/* 49 */           file = spec; 
/*    */       }
/*    */     }
/* 50 */     setURL(url, "rsrc", "", -1, null, null, file, null, null);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     org.eclipse.jdt.internal.jarinjarloader.RsrcURLStreamHandler
 * JD-Core Version:    0.6.2
 */