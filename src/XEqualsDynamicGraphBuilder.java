/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class XEqualsDynamicGraphBuilder
/*    */   implements DynamicGraphBuilder
/*    */ {
/*    */   public void recreate(Graph2D graph)
/*    */   {
/* 12 */     Point2D maxY = (Point2D)graph.getPoints()[0].get(graph.getPoints()[0].size() - 1);
/* 13 */     Point2D minY = (Point2D)graph.getPoints()[0].get(0);
/* 14 */     Point2D minStart = graph.translate(minY);
/* 15 */     Point2D maxStart = graph.translate(maxY);
/* 16 */     GraphIterator iterator = GraphTypeHolder.getInstance().getGraphPointMaker().getIteratorInstance(graph);
/* 17 */     if (maxY.getY() < graph.getWindowRange().getYMax())
/*    */     {
/* 19 */       for (int j = 0; j < graph.getEquations().length; j++)
/* 20 */         for (int i = graph.translate(maxY).getY(); i >= 0; i--) {
/* 21 */           double y = graph.translateY(i);
/* 22 */           Point2D toAdd = GraphTypeHolder.getInstance().getGraphPointMaker().createPoint(y, graph.equation(y, graph.getEquations()[j]));
/* 23 */           graph.addPoint(toAdd, j);
/*    */         }
/*    */     }
/* 26 */     else if (minY.getY() > graph.getWindowRange().getYMin())
/*    */     {
/* 28 */       for (int j = 0; j < graph.getEquations().length; j++)
/* 29 */         for (int i = graph.translate(minY).getY(); i <= graph.getHeight(); i++) {
/* 30 */           double y = graph.translateY(i);
/* 31 */           Point2D toAdd = GraphTypeHolder.getInstance().getGraphPointMaker().createPoint(y, graph.equation(y, graph.getEquations()[j]));
/* 32 */           graph.pushPoint(toAdd, j);
/*    */         }
/*    */     }
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     XEqualsDynamicGraphBuilder
 * JD-Core Version:    0.6.2
 */