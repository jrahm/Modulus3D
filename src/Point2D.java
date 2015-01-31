/*    */ import java.awt.Graphics;
/*    */ import java.awt.Point;
/*    */ 
/*    */ public class Point2D
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private PointInvoker invoker;
/* 16 */   public static final PointInvoker DONOTHING_POINT_INVOKER = new PointInvoker() {
/* 16 */     public void drawPoint(Graphics g, int x, int y) {  }  } ;
/*    */ 
/*    */   public Point2D(double x, double y)
/*    */   {
/* 24 */     this.x = x;
/* 25 */     this.y = y;
/* 26 */     this.invoker = DONOTHING_POINT_INVOKER;
/*    */   }
/*    */ 
/*    */   public Point2D(Point x)
/*    */   {
/* 32 */     this(x.x, x.y);
/*    */   }
/*    */ 
/*    */   public double getRealX()
/*    */   {
/* 38 */     return this.x;
/*    */   }
/*    */ 
/*    */   public int getX()
/*    */   {
/* 44 */     return (int)this.x;
/*    */   }
/*    */ 
/*    */   public double getRealY()
/*    */   {
/* 50 */     return this.y;
/*    */   }
/*    */ 
/*    */   public int getY()
/*    */   {
/* 56 */     return (int)this.y;
/*    */   }
/*    */ 
/*    */   public void setInvoker(PointInvoker invoker)
/*    */   {
/* 62 */     this.invoker = invoker;
/*    */   }
/*    */ 
/*    */   public PointInvoker getInvoker()
/*    */   {
/* 68 */     return this.invoker;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 75 */     return "(" + this.x + "," + this.y + ")";
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Point2D
 * JD-Core Version:    0.6.2
 */