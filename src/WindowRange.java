/*    */ public class WindowRange
/*    */ {
/*    */   private double xMin;
/*    */   private double xMax;
/*    */   private double yMin;
/*    */   private double yMax;
/*    */ 
/*    */   public WindowRange(double xMin, double yMin, double xMax, double yMax)
/*    */   {
/* 10 */     this.xMin = xMin;
/* 11 */     this.xMax = xMax;
/* 12 */     this.yMin = yMin;
/* 13 */     this.yMax = yMax;
/*    */   }
/*    */   private WindowRange(double[] args) {
/* 16 */     this(args[0], args[1], args[2], args[3]);
/*    */   }
/*    */   public WindowRange(Point2D p1, Point2D p2) {
/* 19 */     this(getFromPoints(p1, p2));
/*    */   }
/*    */   public WindowRange() {
/* 22 */     this(-10.0D, -10.0D, 10.0D, 10.0D);
/*    */   }
/*    */   public double getXMin() {
/* 25 */     return this.xMin;
/*    */   }
/*    */   public double getYMin() {
/* 28 */     return this.yMin;
/*    */   }
/*    */   public double getXMax() {
/* 31 */     return this.xMax;
/*    */   }
/*    */   public double getYMax() {
/* 34 */     return this.yMax;
/*    */   }
/*    */ 
/*    */   public WindowRange getScaledInstance(double scale) {
/* 38 */     WindowRange ret = new WindowRange();
/* 39 */     double origdifx = getXMax() - getXMin();
/* 40 */     double newdifx = origdifx * scale;
/* 41 */     double change = (newdifx - origdifx) / 2.0D;
/* 42 */     ret.xMin = (getXMin() - change);
/* 43 */     ret.xMax = (getXMax() + change);
/*    */ 
/* 45 */     double origdify = getYMax() - getYMin();
/* 46 */     double newdify = origdify * scale;
/* 47 */     change = (newdify - origdify) / 2.0D;
/* 48 */     ret.yMin = (getYMin() - change);
/* 49 */     ret.yMax = (getYMax() + change);
/*    */ 
/* 51 */     return ret;
/*    */   }
/*    */   public WindowRange getTranslatedInstance(double difx, double dify) {
/* 54 */     return new WindowRange(this.xMin - difx, this.yMin + dify, this.xMax - difx, this.yMax + dify);
/*    */   }
/*    */ 
/*    */   private static double[] getFromPoints(Point2D p1, Point2D p2)
/*    */   {
/*    */     double s3;
/*    */     double s3;
/*    */     double s1;
/* 58 */     if (p1.getX() > p2.getX()) {
/* 59 */       double s1 = p2.getX();
/* 60 */       s3 = p1.getX();
/*    */     } else {
/* 62 */       s3 = p2.getX();
/* 63 */       s1 = p1.getX();
/*    */     }
/*    */     double s4;
/*    */     double s4;
/*    */     double s2;
/* 66 */     if (p1.getY() > p2.getY()) {
/* 67 */       double s2 = p2.getY();
/* 68 */       s4 = p1.getY();
/*    */     } else {
/* 70 */       s4 = p2.getY();
/* 71 */       s2 = p1.getY();
/*    */     }
/* 73 */     return new double[] { s1, s2, s3, s4 };
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     WindowRange
 * JD-Core Version:    0.6.2
 */