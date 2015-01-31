/*    */ public class RotatingThread extends Thread
/*    */ {
/*    */   private boolean stop;
/*    */   private Graph3D graph;
/*    */   private String axies;
/*    */ 
/*    */   public RotatingThread(Graph3D graph, String axies)
/*    */   {
/* 14 */     this.graph = graph;
/* 15 */     this.axies = axies;
/* 16 */     this.stop = false;
/*    */   }
/*    */   public void interrupt() {
/* 19 */     super.interrupt();
/* 20 */     this.stop = true;
/*    */   }
/*    */   public void run() {
/* 23 */     boolean x = this.axies.contains("x");
/* 24 */     boolean y = this.axies.contains("y");
/* 25 */     boolean z = this.axies.contains("z");
/* 26 */     while (!this.stop)
/*    */     {
/* 28 */       if (x) {
/* 29 */         this.graph.setXRotation(this.graph.getXRotation() + 1.0D);
/*    */       }
/* 31 */       if (y) {
/* 32 */         this.graph.setYRotation(this.graph.getYRotation() + 1.0D);
/*    */       }
/* 34 */       if (z) {
/* 35 */         this.graph.setZRotation(this.graph.getZRotation() + 1.0D);
/*    */       }
/* 37 */       this.graph.repaint();
/*    */       try {
/* 39 */         Thread.sleep(33L);
/*    */       }
/*    */       catch (Exception localException)
/*    */       {
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     RotatingThread
 * JD-Core Version:    0.6.2
 */