/*     */ package org.eclipse.jdt.internal.jarinjarloader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.jar.Manifest;
/*     */ 
/*     */ public class JarRsrcLoader
/*     */ {
/*     */   public static void main(String[] args)
/*     */     throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, IOException
/*     */   {
/*  41 */     ManifestInfo mi = getManifestInfo();
/*  42 */     ClassLoader cl = Thread.currentThread().getContextClassLoader();
/*  43 */     URL.setURLStreamHandlerFactory(new RsrcURLStreamHandlerFactory(cl));
/*  44 */     URL[] rsrcUrls = new URL[mi.rsrcClassPath.length];
/*  45 */     for (int i = 0; i < mi.rsrcClassPath.length; i++) {
/*  46 */       String rsrcPath = mi.rsrcClassPath[i];
/*  47 */       if (rsrcPath.endsWith("/"))
/*  48 */         rsrcUrls[i] = new URL("rsrc:" + rsrcPath);
/*     */       else
/*  50 */         rsrcUrls[i] = new URL("jar:rsrc:" + rsrcPath + "!/");
/*     */     }
/*  52 */     ClassLoader jceClassLoader = new URLClassLoader(rsrcUrls, null);
/*  53 */     Thread.currentThread().setContextClassLoader(jceClassLoader);
/*  54 */     Class c = Class.forName(mi.rsrcMainClass, true, jceClassLoader);
/*  55 */     Method main = c.getMethod("main", new Class[] { args.getClass() });
/*  56 */     main.invoke(null, new Object[] { args });
/*     */   }
/*     */ 
/*     */   private static ManifestInfo getManifestInfo() throws IOException
/*     */   {
/*  61 */     Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
/*  62 */     while (resEnum.hasMoreElements()) {
/*     */       try {
/*  64 */         URL url = (URL)resEnum.nextElement();
/*  65 */         InputStream is = url.openStream();
/*  66 */         if (is != null) {
/*  67 */           ManifestInfo result = new ManifestInfo(null);
/*  68 */           Manifest manifest = new Manifest(is);
/*  69 */           Attributes mainAttribs = manifest.getMainAttributes();
/*  70 */           result.rsrcMainClass = mainAttribs.getValue("Rsrc-Main-Class");
/*  71 */           String rsrcCP = mainAttribs.getValue("Rsrc-Class-Path");
/*  72 */           if (rsrcCP == null)
/*  73 */             rsrcCP = "";
/*  74 */           result.rsrcClassPath = splitSpaces(rsrcCP);
/*  75 */           if ((result.rsrcMainClass != null) && (!result.rsrcMainClass.trim().equals("")))
/*  76 */             return result;
/*     */         }
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*  83 */     System.err.println("Missing attributes for RsrcLoader in Manifest (Rsrc-Main-Class, Rsrc-Class-Path)");
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */   private static String[] splitSpaces(String line)
/*     */   {
/*  95 */     if (line == null)
/*  96 */       return null;
/*  97 */     List result = new ArrayList();
/*  98 */     int firstPos = 0;
/*  99 */     while (firstPos < line.length()) {
/* 100 */       int lastPos = line.indexOf(' ', firstPos);
/* 101 */       if (lastPos == -1)
/* 102 */         lastPos = line.length();
/* 103 */       if (lastPos > firstPos) {
/* 104 */         result.add(line.substring(firstPos, lastPos));
/*     */       }
/* 106 */       firstPos = lastPos + 1;
/*     */     }
/* 108 */     return (String[])result.toArray(new String[result.size()]);
/*     */   }
/*     */ 
/*     */   private static class ManifestInfo
/*     */   {
/*     */     String rsrcMainClass;
/*     */     String[] rsrcClassPath;
/*     */ 
/*     */     private ManifestInfo()
/*     */     {
/*     */     }
/*     */ 
/*     */     ManifestInfo(ManifestInfo paramManifestInfo)
/*     */     {
/*  35 */       this();
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     org.eclipse.jdt.internal.jarinjarloader.JarRsrcLoader
 * JD-Core Version:    0.6.2
 */