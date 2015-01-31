/*    */ public class EuclideanGraphIterator extends GraphIterator
/*    */ {
/*    */   private Graph2D graph;
/*    */ 
/*    */   public EuclideanGraphIterator(Graph2D graph)
/*    */   {
/* 12 */     super(-(int)(graph.getWidth() * 1.5D));
/* 13 */     this.graph = graph;
/*    */   }
/*    */   public static EuclideanGraphIterator getInstance(Graph2D graph) {
/* 16 */     return new EuclideanGraphIterator(graph);
/*    */   }
/*    */   public boolean hasMoreTokens() {
/* 19 */     return this.index < this.graph.getWidth() * 1.5D;
/*    */   }
/*    */   public void onTurn() {
/* 22 */     this.index += this.graph.getXSkip();
/*    */   }
/*    */   public double translateIndex(Graph2D graph) {
/* 25 */     return this.index * graph.getXRes() + graph.getWindowRange().getXMin();
/*    */   }
/*    */   public double translateNumber(Graph2D graph, double i) {
/* 28 */     return i * graph.getXRes() + graph.getWindowRange().getXMin();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     EuclideanGraphIterator
 * JD-Core Version:    0.6.2
 */