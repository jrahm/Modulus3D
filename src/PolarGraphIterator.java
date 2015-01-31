/*    */ public class PolarGraphIterator extends GraphIterator
/*    */ {
/*    */   private double tEnd;
/*    */   private double tStep;
/*    */ 
/*    */   private PolarGraphIterator(double tStart, double tEnd, double tStep)
/*    */   {
/* 13 */     super(tStart);
/* 14 */     this.tEnd = tEnd;
/* 15 */     this.tStep = tStep;
/*    */   }
/*    */   public static PolarGraphIterator getInstance(Graph2D graph) {
/* 18 */     return new PolarGraphIterator(graph.getTStart(), graph.getTEnd(), graph.getTStep());
/*    */   }
/*    */   public boolean hasMoreTokens() {
/* 21 */     return getIndex() <= this.tEnd;
/*    */   }
/*    */   public void onTurn() {
/* 24 */     this.index += this.tStep;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     PolarGraphIterator
 * JD-Core Version:    0.6.2
 */