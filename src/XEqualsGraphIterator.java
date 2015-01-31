/*    */ public class XEqualsGraphIterator extends GraphIterator
/*    */ {
/*    */   private Graph2D graph;
/*    */ 
/*    */   private XEqualsGraphIterator(Graph2D graph)
/*    */   {
/* 13 */     super(-(int)(graph.getHeight() * 1.5D));
/* 14 */     this.graph = graph;
/*    */   }
/*    */   public static XEqualsGraphIterator getInstance(Graph2D graph) {
/* 17 */     return new XEqualsGraphIterator(graph);
/*    */   }
/*    */   public boolean hasMoreTokens() {
/* 20 */     return this.index < this.graph.getHeight() * 1.5D;
/*    */   }
/*    */   public void onTurn() {
/* 23 */     this.index += this.graph.getXSkip();
/*    */   }
/*    */   public double translateIndex(Graph2D graph) {
/* 26 */     return this.index * graph.getYRes() + graph.getWindowRange().getYMin();
/*    */   }
/*    */   public double translateNumber(Graph2D graph, double i) {
/* 29 */     return i * graph.getYRes() + graph.getWindowRange().getYMin();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     XEqualsGraphIterator
 * JD-Core Version:    0.6.2
 */