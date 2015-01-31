/*    */ import javax.swing.ButtonGroup;
/*    */ import javax.swing.JRadioButton;
/*    */ 
/*    */ public class MButtonGroup extends ButtonGroup
/*    */ {
/*    */   public MButtonGroup(JRadioButton[] arr)
/*    */   {
/* 14 */     for (JRadioButton b : arr) add(b);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     MButtonGroup
 * JD-Core Version:    0.6.2
 */