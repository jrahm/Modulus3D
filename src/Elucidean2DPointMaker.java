/*    */ public class Elucidean2DPointMaker extends Point2DMaker
/*    */ {
/*    */   public Elucidean2DPointMaker()
/*    */   {
/* 13 */     super('x', 'y', new ElucideanDynamicGraphBuilder());
/*    */   }
/*    */   public Point2D createPoint(double x, double y) {
/* 16 */     return new Point2D(x, y);
/*    */   }
/*    */   public GraphIterator getIteratorInstance(Graph2D graph) {
/* 19 */     return ElucideanGraphIterator.getInstance(graph);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Elucidean2DPointMaker
 * JD-Core Version:    0.6.2
 */