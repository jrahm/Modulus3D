/*    */ import java.awt.Point;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RectangleDrawer
/*    */   implements MouseMotionListener, MouseListener
/*    */ {
/*    */   private Graph2D graph;
/*    */   private Point lastClick;
/*    */   private Rectangle lastRect;
/*    */   private boolean toggle;
/*    */   private ArrayList<GraphBoxListener> listener;
/*    */ 
/*    */   public RectangleDrawer(Graph2D graph)
/*    */   {
/* 17 */     this.graph = graph;
/* 18 */     this.lastClick = new Point(0, 0);
/* 19 */     this.listener = new ArrayList();
/*    */   }
/*    */ 
/*    */   public void mouseDragged(MouseEvent arg0)
/*    */   {
/* 24 */     this.graph.setRectangleShow(new Point(arg0.getPoint().x, arg0.getPoint().y), new Point(this.lastClick.x, this.lastClick.y));
/* 25 */     this.graph.repaint();
/* 26 */     this.lastRect = new Rectangle(this.lastClick.x, this.lastClick.y, arg0.getPoint().x - this.lastClick.x, arg0.getPoint().y - this.lastClick.y);
/*    */   }
/*    */ 
/*    */   public void mouseMoved(MouseEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseClicked(MouseEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseEntered(MouseEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseExited(MouseEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mousePressed(MouseEvent arg0)
/*    */   {
/* 57 */     this.lastClick = arg0.getPoint();
/* 58 */     this.toggle = true;
/*    */   }
/*    */ 
/*    */   public void mouseReleased(MouseEvent arg0)
/*    */   {
/* 63 */     this.toggle = false;
/* 64 */     this.graph.setRectangleShow(null, null);
/* 65 */     this.graph.setRectangleMode(false);
/* 66 */     this.graph.repaint();
/* 67 */     for (int i = 0; i < this.listener.size(); i++)
/* 68 */       ((GraphBoxListener)this.listener.get(i)).graphBoxMade(arg0.getPoint().x, arg0.getPoint().y, this.lastClick.x, this.lastClick.y);
/*    */   }
/*    */ 
/*    */   public void addGraphBoxListener(GraphBoxListener l) {
/* 72 */     this.listener.add(l);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     RectangleDrawer
 * JD-Core Version:    0.6.2
 */