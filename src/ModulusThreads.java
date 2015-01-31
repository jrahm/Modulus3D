/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ModulusThreads
/*    */ {
/*  8 */   private static Map<String, Thread> executing = new LinkedHashMap();
/*    */ 
/* 10 */   public static void addThread(String key, Thread thread) { if (executing.containsKey(key)) {
/* 11 */       executing.remove(key);
/* 12 */       executing.put(key, thread);
/*    */     }
/*    */ 
/* 15 */     executing.put(key, thread);
/* 16 */     if (!thread.isAlive())
/* 17 */       thread.start(); }
/*    */ 
/*    */   public static void addRunnable(String key, Runnable x)
/*    */   {
/* 21 */     addThread(key, new Thread(x));
/*    */   }
/*    */   public static Thread getThread(String key) {
/* 24 */     return (Thread)executing.get(key);
/*    */   }
/*    */   public static void removeThread(String key) {
/* 27 */     Thread temp = (Thread)executing.get(key);
/* 28 */     executing.remove(key);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     ModulusThreads
 * JD-Core Version:    0.6.2
 */