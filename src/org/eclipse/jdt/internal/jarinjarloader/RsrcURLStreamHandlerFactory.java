/*    */ package org.eclipse.jdt.internal.jarinjarloader;
/*    */ 
/*    */ import java.net.URLStreamHandler;
/*    */ import java.net.URLStreamHandlerFactory;
/*    */ 
/*    */ public class RsrcURLStreamHandlerFactory
/*    */   implements URLStreamHandlerFactory
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */   private URLStreamHandlerFactory chainFac;
/*    */ 
/*    */   public RsrcURLStreamHandlerFactory(ClassLoader cl)
/*    */   {
/* 30 */     this.classLoader = cl;
/*    */   }
/*    */ 
/*    */   public URLStreamHandler createURLStreamHandler(String protocol) {
/* 34 */     if ("rsrc".equals(protocol))
/* 35 */       return new RsrcURLStreamHandler(this.classLoader);
/* 36 */     if (this.chainFac != null)
/* 37 */       return this.chainFac.createURLStreamHandler(protocol);
/* 38 */     return null;
/*    */   }
/*    */ 
/*    */   public void setURLStreamHandlerFactory(URLStreamHandlerFactory fac)
/*    */   {
/* 50 */     this.chainFac = fac;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     org.eclipse.jdt.internal.jarinjarloader.RsrcURLStreamHandlerFactory
 * JD-Core Version:    0.6.2
 */