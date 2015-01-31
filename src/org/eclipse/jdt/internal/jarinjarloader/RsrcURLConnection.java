/*    */ package org.eclipse.jdt.internal.jarinjarloader;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.net.URLDecoder;
/*    */ 
/*    */ public class RsrcURLConnection extends URLConnection
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */ 
/*    */   public RsrcURLConnection(URL url, ClassLoader classLoader)
/*    */   {
/* 34 */     super(url);
/* 35 */     this.classLoader = classLoader;
/*    */   }
/*    */ 
/*    */   public void connect() throws IOException {
/*    */   }
/*    */ 
/*    */   public InputStream getInputStream() throws IOException {
/* 42 */     String file = URLDecoder.decode(this.url.getFile(), "UTF-8");
/* 43 */     InputStream result = this.classLoader.getResourceAsStream(file);
/* 44 */     if (result == null) {
/* 45 */       throw new MalformedURLException("Could not open InputStream for URL '" + this.url + "'");
/*    */     }
/* 47 */     return result;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     org.eclipse.jdt.internal.jarinjarloader.RsrcURLConnection
 * JD-Core Version:    0.6.2
 */