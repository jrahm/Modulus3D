/*    */ import javax.swing.SpinnerModel;
/*    */ import javax.swing.event.ChangeListener;
/*    */ 
/*    */ public class NonZeroSpinnerModel
/*    */   implements SpinnerModel
/*    */ {
/*  7 */   int current = 1;
/*    */ 
/*    */   public void addChangeListener(ChangeListener arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public Object getNextValue()
/*    */   {
/* 16 */     return new Integer(this.current + 1);
/*    */   }
/*    */ 
/*    */   public Object getPreviousValue()
/*    */   {
/* 21 */     if (this.current == 1) return new Integer(this.current);
/* 22 */     return new Integer(this.current - 1);
/*    */   }
/*    */ 
/*    */   public Object getValue()
/*    */   {
/* 27 */     return new Integer(this.current);
/*    */   }
/*    */ 
/*    */   public void removeChangeListener(ChangeListener arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setValue(Object arg0)
/*    */   {
/* 38 */     if ((arg0 instanceof Integer))
/* 39 */       this.current = ((Integer)arg0).intValue();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     NonZeroSpinnerModel
 * JD-Core Version:    0.6.2
 */