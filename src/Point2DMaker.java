/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class Point2DMaker
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2479846522556400217L;
/*    */   private char iVaraible;
/*    */   private char dVaraible;
/*    */   private DynamicGraphBuilder graphBuilder;
/*    */ 
/*    */   public Point2DMaker(char independentVariable, char dependentVariable, DynamicGraphBuilder graphBuilder)
/*    */   {
/* 20 */     this.iVaraible = independentVariable;
/* 21 */     this.dVaraible = dependentVariable;
/* 22 */     this.graphBuilder = graphBuilder;
/*    */   }
/*    */   public char getIndependentVariable() {
/* 25 */     return this.iVaraible;
/*    */   }
/*    */   public char getDependentVariable() {
/* 28 */     return this.dVaraible;
/*    */   }
/*    */   public abstract GraphIterator getIteratorInstance(Graph2D paramGraph2D);
/*    */ 
/*    */   public abstract Point2D createPoint(double paramDouble1, double paramDouble2);
/*    */ 
/* 34 */   public DynamicGraphBuilder getGraphBuilder() { return this.graphBuilder; }
/*    */ 
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Point2DMaker
 * JD-Core Version:    0.6.2
 */