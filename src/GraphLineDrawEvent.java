/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class GraphLineDrawEvent
/*    */   implements GraphicsEvent
/*    */ {
/*    */   private Graph2D graph;
/*    */   private Point2D from;
/*    */   private Point2D to;
/*    */ 
/*    */   public GraphLineDrawEvent(Graph2D graph, Point2D from, Point2D to)
/*    */   {
/* 15 */     this.graph = graph;
/* 16 */     this.from = from;
/* 17 */     this.to = to;
/*    */   }
/*    */ 
/*    */   public void invoke(Graphics graphics)
/*    */   {
/* 24 */     Point2D transFrom = this.graph.translate(this.from);
/* 25 */     Point2D transTo = this.graph.translate(this.to);
/* 26 */     graphics.drawLine(transFrom.getX(), transFrom.getY(), transTo.getX(), transTo.getY());
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GraphLineDrawEvent
 * JD-Core Version:    0.6.2
 */