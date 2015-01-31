/*    */ import java.awt.Point;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ import java.awt.event.MouseWheelEvent;
/*    */ import java.awt.event.MouseWheelListener;
/*    */ 
/*    */ public class GraphEventHandler
/*    */   implements MouseListener, MouseMotionListener, MouseWheelListener
/*    */ {
/*    */   private Point lastClick;
/*    */   private Graph2D graph;
/*    */ 
/*    */   public GraphEventHandler(Graph2D target)
/*    */   {
/* 19 */     this.graph = target;
/*    */   }
/*    */ 
/*    */   public void mouseClicked(MouseEvent e) {
/* 23 */     this.lastClick = e.getPoint();
/*    */   }
/*    */ 
/*    */   public void mouseEntered(MouseEvent e)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseExited(MouseEvent e)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mousePressed(MouseEvent e)
/*    */   {
/* 38 */     this.lastClick = e.getPoint();
/*    */   }
/*    */ 
/*    */   public void mouseReleased(MouseEvent e)
/*    */   {
/* 44 */     this.lastClick = e.getPoint();
/*    */   }
/*    */ 
/*    */   public void mouseDragged(MouseEvent e)
/*    */   {
/* 50 */     Point move = e.getPoint();
/*    */ 
/* 52 */     double difx = (move.x - this.lastClick.x) * this.graph.getXRes();
/* 53 */     double dify = (move.y - this.lastClick.y) * this.graph.getYRes();
/* 54 */     this.graph.setWindowRange(this.graph.getWindowRange().getTranslatedInstance(difx, dify));
/* 55 */     this.graph.recreate();
/* 56 */     this.graph.repaint();
/* 57 */     this.lastClick = e.getPoint();
/*    */   }
/*    */ 
/*    */   public void mouseMoved(MouseEvent e)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseWheelMoved(MouseWheelEvent e)
/*    */   {
/* 67 */     this.graph.zoom(Math.min(Math.pow(1.2D, e.getWheelRotation()), 1.728D));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GraphEventHandler
 * JD-Core Version:    0.6.2
 */