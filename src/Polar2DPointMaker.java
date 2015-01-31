/*    */ public class Polar2DPointMaker extends Point2DMaker
/*    */ {
/*    */   public Polar2DPointMaker()
/*    */   {
/* 11 */     super('t', 'r', DynamicGraphBuilder.DO_NOTHING_GRAPH_BUILDER);
/*    */   }
/*    */   public Point2D createPoint(double t, double r) {
/* 14 */     return new Point2D(r * Math.cos(Math.toRadians(t)), r * Math.sin(Math.toRadians(t)));
/*    */   }
/*    */   public GraphIterator getIteratorInstance(Graph2D graph) {
/* 17 */     return PolarGraphIterator.getInstance(graph);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Polar2DPointMaker
 * JD-Core Version:    0.6.2
 */