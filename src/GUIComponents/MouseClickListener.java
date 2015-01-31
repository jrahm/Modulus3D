/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.Container;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ 
/*    */ public abstract class MouseClickListener
/*    */   implements MouseListener
/*    */ {
/*    */   private static Container listener;
/*    */ 
/*    */   public MouseClickListener(Container listener)
/*    */   {
/* 10 */     listener = listener;
/*    */   }
/*    */ 
/*    */   public MouseClickListener()
/*    */   {
/*    */   }
/*    */ 
/*    */   public abstract void mouseClicked(MouseEvent paramMouseEvent);
/*    */ 
/*    */   public void mouseEntered(MouseEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseExited(MouseEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public abstract void mousePressed(MouseEvent paramMouseEvent);
/*    */ 
/*    */   public abstract void mouseReleased(MouseEvent paramMouseEvent);
/*    */ 
/*    */   public Container getContainer()
/*    */   {
/* 36 */     return listener;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.MouseClickListener
 * JD-Core Version:    0.6.2
 */