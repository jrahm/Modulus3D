/*    */ public class ReverseEuclidean2DPointMaker extends Point2DMaker
/*    */ {
/*    */   public ReverseEuclidean2DPointMaker()
/*    */   {
/* 13 */     super('y', 'x', new XEqualsDynamicGraphBuilder());
/*    */   }
/*    */   public Point2D createPoint(double x, double y) {
/* 16 */     return new Point2D(y, x);
/*    */   }
/*    */   public GraphIterator getIteratorInstance(Graph2D graph) {
/* 19 */     return XEqualsGraphIterator.getInstance(graph);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     ReverseEuclidean2DPointMaker
 * JD-Core Version:    0.6.2
 */