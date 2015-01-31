/*    */ import java.awt.Graphics;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ExtendablePointInvoker
/*    */   implements PointInvoker
/*    */ {
/*    */   private List<PointInvoker> invokers;
/*    */ 
/*    */   public ExtendablePointInvoker(List<PointInvoker> invokers)
/*    */   {
/* 14 */     this.invokers = invokers;
/*    */   }
/*    */   public ExtendablePointInvoker(PointInvoker invoker) {
/* 17 */     this.invokers = new ArrayList();
/* 18 */     this.invokers.add(invoker);
/*    */   }
/*    */   public void remove(PointInvoker invoke) {
/* 21 */     this.invokers.remove(invoke);
/*    */   }
/*    */   public void drawPoint(Graphics g, int x, int y) {
/* 24 */     for (int i = 0; i < this.invokers.size(); i++)
/* 25 */       ((PointInvoker)this.invokers.get(i)).drawPoint(g, x, y);
/*    */   }
/*    */ 
/*    */   public void concat(PointInvoker invoke) {
/* 29 */     this.invokers.add(invoke);
/*    */   }
/*    */   public void setInvoker(PointInvoker invoke) {
/* 32 */     this.invokers = new ArrayList();
/* 33 */     concat(invoke);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     ExtendablePointInvoker
 * JD-Core Version:    0.6.2
 */