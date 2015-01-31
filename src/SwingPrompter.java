/*    */ public class SwingPrompter
/*    */   implements Runnable
/*    */ {
/* 13 */   public static String hold = "";
/* 14 */   private String[] runArgs = { "" };
/* 15 */   public static boolean toggle = false;
/*    */ 
/*    */   public static String getPrompt(String[] args) throws Exception {
/* 18 */     SwingPrompter prompt = new SwingPrompter();
/* 19 */     prompt.runArgs = args;
/* 20 */     Thread run = new Thread(prompt);
/* 21 */     run.start();
/* 22 */     while (!toggle);
/* 23 */     toggle = false;
/* 24 */     return hold;
/*    */   }
/*    */ 
/*    */   public void run() {
/* 28 */     Thread newThread = new Thread(new ThreadGroup(Thread.currentThread().getThreadGroup().getParent(), "Prompt Window"), new RunnablePrompt(this.runArgs));
/* 29 */     newThread.start();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     SwingPrompter
 * JD-Core Version:    0.6.2
 */