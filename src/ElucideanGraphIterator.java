/*    */ public class ElucideanGraphIterator extends GraphIterator
/*    */ {
/*    */   private Graph2D graph;
/*    */ 
/*    */   public ElucideanGraphIterator(Graph2D graph)
/*    */   {
/* 12 */     super(-(int)(graph.getWidth() * 1.5D));
/* 13 */     this.graph = graph;
/*    */   }
/*    */   public static ElucideanGraphIterator getInstance(Graph2D graph) {
/* 16 */     return new ElucideanGraphIterator(graph);
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
 * Qualified Name:     ElucideanGraphIterator
 * JD-Core Version:    0.6.2
 */