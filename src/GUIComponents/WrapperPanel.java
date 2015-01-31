/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.FlowLayout;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class WrapperPanel extends JPanel
/*    */ {
/*    */   public WrapperPanel(Component t)
/*    */   {
/* 10 */     add(t);
/*    */   }
/*    */   public WrapperPanel(Component[] cArr) {
/* 13 */     super(new FlowLayout());
/* 14 */     for (Component x : cArr) add(x);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.WrapperPanel
 * JD-Core Version:    0.6.2
 */