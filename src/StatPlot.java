/*    */ import java.awt.Graphics;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class StatPlot
/*    */ {
/*    */   private ArrayList<Point2D> points;
/*    */   private ExtendablePointInvoker invoker;
/*    */   private GraphTranslator translate;
/* 11 */   private static final Comparator comparator = new Comparator()
/*    */   {
/*    */     public int compare(Object o1, Object o2)
/*    */     {
/* 15 */       if ((!(o1 instanceof Point2D)) || (!(o2 instanceof Point2D))) return 0;
/* 16 */       Point2D p1 = (Point2D)o1;
/* 17 */       Point2D p2 = (Point2D)o2;
/* 18 */       return p1.getRealX() == p2.getRealX() ? 0 : p1.getRealX() > p2.getRealX() ? 1 : -1;
/*    */     }
/* 11 */   };
/*    */ 
/*    */   public StatPlot(ArrayList<Point2D> points, GraphTranslator translate)
/*    */   {
/* 24 */     this.points = ((ArrayList)points.clone());
/* 25 */     Collections.sort(this.points, comparator);
/* 26 */     this.translate = translate;
/* 27 */     this.invoker = new ExtendablePointInvoker(new ArrayList());
/*    */   }
/*    */   public StatPlot(ArrayList<Point2D> points, PointInvoker firstInvoker, GraphTranslator translate) {
/* 30 */     this(points, translate);
/* 31 */     this.invoker.concat(firstInvoker);
/*    */   }
/*    */   public Point2D[] getPoints() {
/* 34 */     Point2D[] p = new Point2D[this.points.size()];
/* 35 */     return p = (Point2D[])this.points.toArray(p);
/*    */   }
/*    */   public void setPoints(ArrayList<Point2D> points) {
/* 38 */     this.points = points;
/*    */   }
/*    */   public ExtendablePointInvoker getInvoker() {
/* 41 */     return this.invoker;
/*    */   }
/*    */   public void setInvoker(ExtendablePointInvoker invoker) {
/* 44 */     this.invoker = invoker;
/*    */   }
/*    */   public void drawPoints(Graphics g) {
/* 47 */     for (int i = 0; i < this.points.size(); i++) {
/* 48 */       Point2D trans = this.translate.translate((Point2D)this.points.get(i));
/* 49 */       this.invoker.drawPoint(g, trans.getX(), trans.getY());
/*    */     }
/*    */   }
/*    */ 
/* 53 */   public String toString() { String ret = "";
/* 54 */     for (int i = 0; i < this.points.size(); i++) {
/* 55 */       ret = ret + "," + this.points.get(i);
/*    */     }
/* 57 */     return "[" + ret + "]"; }
/*    */ 
/*    */   public Point2D getPoint(int index) {
/* 60 */     return (Point2D)this.points.get(index);
/*    */   }
/*    */   public double getYAt(int index) {
/* 63 */     return getPoint(index).getRealY();
/*    */   }
/*    */   public double getXAt(int index) {
/* 66 */     return getPoint(index).getRealX();
/*    */   }
/*    */   public int size() {
/* 69 */     return this.points.size();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     StatPlot
 * JD-Core Version:    0.6.2
 */