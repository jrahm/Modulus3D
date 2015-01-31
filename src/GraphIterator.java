/*    */ public abstract class GraphIterator
/*    */ {
/*    */   protected double index;
/*    */ 
/*    */   public GraphIterator(double start)
/*    */   {
/* 12 */     this.index = start;
/*    */   }
/*    */   public double getIndex() {
/* 15 */     return this.index;
/*    */   }
/*    */   public abstract boolean hasMoreTokens();
/*    */ 
/*    */   public abstract void onTurn();
/*    */ 
/*    */   public double translateIndex(Graph2D graph) {
/* 22 */     return this.index;
/*    */   }
/*    */   public double translateNumber(Graph2D graph, double i) {
/* 25 */     return i;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GraphIterator
 * JD-Core Version:    0.6.2
 */