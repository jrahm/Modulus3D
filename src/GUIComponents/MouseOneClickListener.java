/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.Container;
/*    */ import java.awt.event.MouseEvent;
/*    */ 
/*    */ public class MouseOneClickListener extends MouseClickListener
/*    */ {
/*    */   public MouseOneClickListener(Container listener)
/*    */   {
/* 16 */     super(listener);
/*    */   }
/*    */ 
/*    */   public MouseOneClickListener()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseClicked(MouseEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mousePressed(MouseEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseReleased(MouseEvent arg0)
/*    */   {
/* 47 */     getContainer().removeMouseListener(this);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.MouseOneClickListener
 * JD-Core Version:    0.6.2
 */