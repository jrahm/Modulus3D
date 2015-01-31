/*    */ public class Euclidean2DPointMaker extends Point2DMaker
/*    */ {
/*    */   public Euclidean2DPointMaker()
/*    */   {
/* 15 */     super('x', 'y', new EuclideanDynamicGraphBuilder());
/*    */   }
/*    */   public Point2D createPoint(double x, double y) {
/* 18 */     return new Point2D(x, y);
/*    */   }
/*    */   public GraphIterator getIteratorInstance(Graph2D graph) {
/* 21 */     return EuclideanGraphIterator.getInstance(graph);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Euclidean2DPointMaker
 * JD-Core Version:    0.6.2
 */