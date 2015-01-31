/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ElucideanDynamicGraphBuilder
/*    */   implements DynamicGraphBuilder
/*    */ {
/*    */   public void recreate(Graph2D graph)
/*    */   {
/* 11 */     Point2D minX = (Point2D)graph.getPoints()[0].get(0);
/* 12 */     Point2D maxX = (Point2D)graph.getPoints()[0].get(graph.getPoints()[0].size() - 1);
/* 13 */     Point2D minStart = graph.translate(minX);
/* 14 */     Point2D maxStart = graph.translate(maxX);
/*    */ 
/* 16 */     if (minX.getX() > graph.getWindowRange().getXMin()) {
/* 17 */       for (int i = minStart.getX() - graph.getXSkip(); i >= -graph.getXSkip(); i -= graph.getXSkip()) {
/* 18 */         for (int j = 0; j < graph.getEquations().length; j++) {
/* 19 */           Point2D toAdd = new Point2D(i * graph.getXRes() + graph.getWindowRange().getXMin(), graph.equation(i * graph.getXRes() + graph.getWindowRange().getXMin(), graph.getEquations()[j]));
/* 20 */           graph.pushPoint(toAdd, j);
/* 21 */           graph.trimUp(j);
/*    */         }
/*    */       }
/*    */     }
/* 25 */     if (maxX.getX() < graph.getWindowRange().getXMax())
/* 26 */       for (int i = maxStart.getX() + graph.getXSkip(); i <= graph.getWidth() + graph.getXSkip(); i += graph.getXSkip())
/* 27 */         for (int j = 0; j < graph.getEquations().length; j++) {
/* 28 */           Point2D toAdd = new Point2D(i * graph.getXRes() + graph.getWindowRange().getXMin(), graph.equation(i * graph.getXRes() + graph.getWindowRange().getXMin(), graph.getEquations()[j]));
/* 29 */           graph.addPoint(toAdd, j);
/* 30 */           graph.trimDown(j);
/*    */         }
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     ElucideanDynamicGraphBuilder
 * JD-Core Version:    0.6.2
 */