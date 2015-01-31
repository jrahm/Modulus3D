/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class AbstractGraph extends GraphWorld
/*     */ {
/*     */   private static final long serialVersionUID = -4806217320367527179L;
/*  15 */   public static final Color[] colors = { 
/*  16 */     Color.red, 
/*  17 */     Color.blue, 
/*  18 */     Color.green, 
/*  19 */     Color.yellow.darker(), 
/*  20 */     Color.magenta, 
/*  21 */     Color.pink, 
/*  22 */     Color.orange, 
/*  23 */     Color.black, 
/*  24 */     new Color(100, 100, 0), 
/*  25 */     Color.gray };
/*     */   private ArrayList<Point2D>[] points;
/*     */   private static ExtendablePointInvoker invoker;
/*     */   private double lastY;
/*     */   private double lastX;
/*     */   private WindowRange winRange;
/*     */   private int xskip;
/*     */ 
/*     */   public AbstractGraph(int w, int h)
/*     */   {
/*  38 */     super(w, h);
/*  39 */     this.points = new ArrayList[10];
/*  40 */     for (int i = 0; i < this.points.length; i++) this.points[i] = new ArrayList();
/*  41 */     this.winRange = new WindowRange();
/*     */   }
/*     */   public void setWindowRange(WindowRange winRange) {
/*  44 */     this.winRange = winRange;
/*  45 */     repaint();
/*     */   }
/*     */   public WindowRange getWindowRange() {
/*  48 */     return this.winRange;
/*     */   }
/*     */   public void addPoint(Point2D point, int index) {
/*  51 */     this.points[index].add(point);
/*     */   }
/*     */   public void pushPoint(Point2D point, int index) {
/*  54 */     this.points[index].add(0, point);
/*     */   }
/*     */   public void removePoint(Point2D point, int index) {
/*  57 */     this.points[index].remove(point);
/*     */   }
/*     */ 
/*     */   public ArrayList<Point2D>[] getPoints() {
/*  61 */     return this.points;
/*     */   }
/*     */   public String[] getEquations() {
/*  64 */     return GraphTypeHolder.getInstance().getEquations();
/*     */   }
/*     */   public void setEquations(String[] equations) {
/*  67 */     GraphTypeHolder.getInstance().setEquations(equations);
/*  68 */     clearEvents();
/*  69 */     repaint();
/*     */   }
/*     */ 
/*     */   protected void setLastY(double last) {
/*  73 */     this.lastY = last;
/*     */   }
/*     */   protected void setLastX(double last) {
/*  76 */     this.lastX = last;
/*     */   }
/*     */   public double getLastY() {
/*  79 */     return this.lastY;
/*     */   }
/*     */   public double getLastX() {
/*  82 */     return this.lastX;
/*     */   }
/*     */   public ExtendablePointInvoker getInvoker() {
/*  85 */     return invoker;
/*     */   }
/*     */   public void setInvoker(ExtendablePointInvoker invoker) {
/*  88 */     invoker = invoker;
/*  89 */     repaint();
/*     */   }
/*     */   public int getXSkip() {
/*  92 */     return this.xskip;
/*     */   }
/*     */   public void setXSkip(int xSkip) {
/*  95 */     this.xskip = xSkip;
/*  96 */     repaint();
/*     */   }
/*     */   protected void wipe() {
/*  99 */     this.points = new ArrayList[10];
/* 100 */     for (int i = 0; i < this.points.length; i++) {
/* 101 */       this.points[i] = new ArrayList();
/*     */     }
/* 103 */     repaint();
/*     */   }
/*     */   public void appendEquation(String equation) {
/* 106 */     String[] temp = GraphTypeHolder.getInstance().getEquations();
/* 107 */     String[] nother = new String[temp.length + 1];
/* 108 */     for (int i = 0; i < temp.length; i++) nother[i] = temp[i];
/* 109 */     nother[(nother.length - 1)] = equation;
/* 110 */     GraphTypeHolder.getInstance().setEquations(nother); } 
/*     */   public abstract void makePoints();
/*     */ 
/*     */   public abstract void remakePoints();
/*     */ 
/* 115 */   protected void clearEvents() { int max = super.getEvents().size();
/* 116 */     for (int i = 0; i < max; i++)
/* 117 */       super.removeEvent(0);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     AbstractGraph
 * JD-Core Version:    0.6.2
 */