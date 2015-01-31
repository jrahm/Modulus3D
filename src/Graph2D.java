/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Graph2D extends AbstractGraph
/*     */   implements PointInvoker, GraphTranslator, EquationChangeListener, GraphTypeStateChangedListener
/*     */ {
/*     */   private static final long serialVersionUID = 7997481241457409003L;
/*  31 */   private static Graph2D instance = null;
/*     */   private double thetaStep;
/*     */   private double thetaStart;
/*     */   private double thetaEnd;
/*     */   private boolean rectangleMode;
/*     */   private RectangleDrawer drawer;
/*  37 */   private ArrayList<EquationChangeListener> equationChangeListeners = new ArrayList();
/*     */   private ArrayList<StatPlot> statPlots;
/*     */   private MouseMotionListener[] mouseMotionHold;
/*     */   private MouseListener[] mouseHold;
/*     */   private Point startRect;
/*     */   private Point endRect;
/*  45 */   public static final Color[] colors = { 
/*  46 */     Color.red, 
/*  47 */     Color.blue, 
/*  48 */     Color.green, 
/*  49 */     Color.yellow.darker(), 
/*  50 */     Color.magenta, 
/*  51 */     Color.pink, 
/*  52 */     Color.orange, 
/*  53 */     Color.black, 
/*  54 */     new Color(100, 100, 0), 
/*  55 */     Color.gray };
/*     */   private double yres;
/*     */   private double xres;
/*     */ 
/*     */   public Graph2D(double xMin, double xMax, double yMin, double yMax)
/*     */   {
/*  60 */     super(500, 500);
/*     */ 
/*  62 */     super.setEquations(new String[] { "<ivar>" });
/*  63 */     super.setLastY((0.0D / 0.0D));
/*  64 */     super.setLastX((0.0D / 0.0D));
/*  65 */     super.setXSkip(1);
/*  66 */     super.setInvoker(new ExtendablePointInvoker(this));
/*     */ 
/*  68 */     super.setWindowRange(new WindowRange(xMin, yMin, xMax, yMax));
/*     */ 
/*  70 */     this.xres = ((getWindowRange().getXMax() - getWindowRange().getXMin()) / getWidth());
/*  71 */     this.yres = ((getWindowRange().getYMax() - getWindowRange().getYMin()) / getHeight());
/*  72 */     this.thetaStart = 0.0D;
/*  73 */     this.thetaEnd = 360.0D;
/*  74 */     this.thetaStep = 1.0D;
/*  75 */     this.statPlots = new ArrayList();
/*  76 */     this.drawer = new RectangleDrawer(this);
/*     */   }
/*     */   public void drawPoint(Graphics g, int x, int y) {
/*  79 */     if ((!Double.isNaN(getLastY())) && (!Double.isNaN(getLastX())))
/*  80 */       g.drawLine((int)getLastX(), (int)getLastY(), x, y);
/*  81 */     super.setLastX(x);
/*  82 */     super.setLastY(y);
/*     */   }
/*     */ 
/*     */   public void zoom(double amt) {
/*  86 */     super.setWindowRange(super.getWindowRange().getScaledInstance(amt));
/*  87 */     repaint();
/*  88 */     recreate();
/*     */   }
/*     */   public void invoke(Graphics g) {
/*  91 */     g.setColor(Color.white);
/*  92 */     g.fillRect(0, 0, getWidth(), getHeight());
/*  93 */     g.setColor(Color.black);
/*  94 */     this.xres = ((getWindowRange().getXMax() - getWindowRange().getXMin()) / getWidth());
/*  95 */     this.yres = ((getWindowRange().getYMax() - getWindowRange().getYMin()) / getHeight());
/*  96 */     drawAxis(g);
/*  97 */     int colorindex = 0;
/*  98 */     for (int i = 0; i < this.statPlots.size(); i++) ((StatPlot)this.statPlots.get(i)).drawPoints(g);
/*  99 */     for (ArrayList pointarr : super.getPoints()) {
/* 100 */       super.setLastY((0.0D / 0.0D));
/* 101 */       super.setLastX((0.0D / 0.0D));
/* 102 */       g.setColor(colors[colorindex]);
/* 103 */       for (int i = 0; i < pointarr.size(); i++) {
/* 104 */         Point2D trans = translate((Point2D)pointarr.get(i));
/* 105 */         if (trans != null) {
/* 106 */           super.getInvoker().drawPoint(g, trans.getX(), trans.getY());
/*     */         } else {
/* 108 */           super.setLastY((0.0D / 0.0D));
/* 109 */           super.setLastX((0.0D / 0.0D));
/*     */         }
/*     */       }
/* 112 */       colorindex = (colorindex + 1) % colors.length;
/*     */     }
/* 114 */     if ((this.startRect != null) && (this.endRect != null) && (this.rectangleMode))
/*     */     {
/* 117 */       g.drawLine(this.startRect.x, this.startRect.y, this.endRect.x, this.startRect.y);
/* 118 */       g.drawLine(this.startRect.x, this.startRect.y, this.startRect.x, this.endRect.y);
/*     */ 
/* 120 */       g.drawLine(this.startRect.x, this.endRect.y, this.endRect.x, this.endRect.y);
/* 121 */       g.drawLine(this.endRect.x, this.startRect.y, this.endRect.x, this.endRect.y);
/*     */     }
/* 123 */     for (int i = 0; i < super.getEvents().size(); i++)
/* 124 */       ((GraphicsEvent)super.getEvents().get(i)).invoke(g);
/*     */   }
/*     */ 
/*     */   public RectangleDrawer getRectangleDrawer() {
/* 128 */     return this.drawer;
/*     */   }
/*     */   public void makePoints() {
/* 131 */     for (int j = 0; j < super.getEquations().length; j++) {
/* 132 */       GraphIterator iterator = GraphTypeHolder.getInstance().getGraphPointMaker().getIteratorInstance(this);
/* 133 */       for (; iterator.hasMoreTokens(); iterator.onTurn())
/*     */       {
/* 136 */         Point2D toAdd = GraphTypeHolder.getInstance().getGraphPointMaker().createPoint(iterator.translateIndex(this), equation(iterator.translateIndex(this), super.getEquations()[j]));
/* 137 */         addPoint(toAdd, j);
/* 138 */         repaint();
/*     */         try {
/* 140 */           Thread.sleep(1L);
/*     */         } catch (Exception localException) {
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void remakePoints() {
/* 148 */     super.wipe();
/* 149 */     makePoints();
/*     */   }
/*     */   public void drawAxis(Graphics g) {
/* 152 */     DecimalFormat format = new DecimalFormat(".00");
/* 153 */     if ((getWindowRange().getYMin() < 0.0D) && (getWindowRange().getYMax() > 0.0D)) {
/* 154 */       g.drawLine(
/* 155 */         0, getHeight() - (int)(getHeight() * (-super.getWindowRange().getYMin() / (super.getWindowRange().getYMax() - super.getWindowRange().getYMin()))), 
/* 156 */         getWidth(), getHeight() - (int)(getHeight() * (-super.getWindowRange().getYMin() / (super.getWindowRange().getYMax() - super.getWindowRange().getYMin()))));
/*     */ 
/* 158 */       String xMinS = format.format(getWindowRange().getXMin());
/* 159 */       String xMaxS = format.format(getWindowRange().getXMax());
/* 160 */       g.drawString(xMinS, 0, getHeight() - (int)(getHeight() * (-super.getWindowRange().getYMin() / (super.getWindowRange().getYMax() - super.getWindowRange().getYMin()))) - 10);
/* 161 */       g.drawString(xMaxS, getWidth() - 8 * xMaxS.length(), getHeight() - (int)(getHeight() * (-super.getWindowRange().getYMin() / (super.getWindowRange().getYMax() - super.getWindowRange().getYMin()))) - 10);
/*     */     } else {
/* 163 */       String xMinS = format.format(getWindowRange().getXMin());
/* 164 */       String xMaxS = format.format(getWindowRange().getXMax());
/* 165 */       g.drawString(xMinS, 0, getHeight() / 2);
/* 166 */       g.drawString(xMaxS, getWidth() - 8 * xMaxS.length(), getHeight() / 2);
/*     */     }
/* 168 */     if ((super.getWindowRange().getXMin() < 0.0D) && (super.getWindowRange().getXMax() > 0.0D)) {
/* 169 */       String yMinS = format.format(getWindowRange().getYMin());
/* 170 */       String yMaxS = format.format(getWindowRange().getYMax());
/* 171 */       g.drawString(yMinS, (int)(getWidth() * (-super.getWindowRange().getXMin() / (super.getWindowRange().getXMax() - super.getWindowRange().getXMin()))), getHeight() - 8);
/* 172 */       g.drawString(yMaxS, (int)(getWidth() * (-super.getWindowRange().getXMin() / (super.getWindowRange().getXMax() - super.getWindowRange().getXMin()))), 15);
/* 173 */       g.drawLine((int)(getWidth() * (-super.getWindowRange().getXMin() / (super.getWindowRange().getXMax() - super.getWindowRange().getXMin()))), 0, (int)(getWidth() * (-super.getWindowRange().getXMin() / (super.getWindowRange().getXMax() - super.getWindowRange().getXMin()))), getHeight());
/*     */     } else {
/* 175 */       String yMinS = format.format(getWindowRange().getYMin());
/* 176 */       String yMaxS = format.format(getWindowRange().getYMax());
/* 177 */       g.drawString(yMinS, getWidth() / 2, getHeight() - 8);
/* 178 */       g.drawString(yMaxS, getWidth() / 2, 15);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Point2D translate(Point2D point) {
/* 183 */     if ((point == null) || (Double.isNaN(point.getRealY())) || (Double.isNaN(point.getRealX()))) {
/* 184 */       return new Point2D((point.getRealX() - super.getWindowRange().getXMin()) / this.xres, (0.0D / 0.0D));
/*     */     }
/* 186 */     double x = (point.getRealX() - super.getWindowRange().getXMin()) / this.xres;
/* 187 */     double y = (point.getRealY() - super.getWindowRange().getYMin()) / this.yres;
/* 188 */     return new Point2D(x, getHeight() - y);
/*     */   }
/*     */   public Point2D translateInv(Point2D point) {
/* 191 */     if ((point == null) || (Double.isNaN(point.getRealY())) || (Double.isNaN(point.getRealX()))) {
/* 192 */       return new Point2D((point.getRealX() - super.getWindowRange().getXMin()) / this.xres, (0.0D / 0.0D));
/*     */     }
/* 194 */     double x = point.getRealX() * this.xres + super.getWindowRange().getXMin();
/* 195 */     double y = getHeight() * this.yres - point.getRealY() * this.yres + super.getWindowRange().getYMin();
/* 196 */     return new Point2D(x, y);
/*     */   }
/*     */   public double translateY(int pixels) {
/* 199 */     return getHeight() * this.yres - pixels * this.yres + super.getWindowRange().getYMin();
/*     */   }
/*     */   public double translateX(int pixels) {
/* 202 */     return pixels * this.xres + super.getWindowRange().getXMin();
/*     */   }
/*     */   public static Graph2D getGraphInstance() {
/* 205 */     if (instance != null) return instance;
/* 206 */     instance = new Graph2D(-10.0D, 10.0D, -10.0D, 10.0D);
/* 207 */     instance.setSize(500, 500);
/* 208 */     return instance;
/*     */   }
/*     */   public double equation(double x, String equation) {
/*     */     try {
/* 212 */       return Double.parseDouble(ControlPanel.figure(equation.replaceAll("<ivar>", "(" + new BigDecimal(x).toPlainString() + ")")));
/*     */     }
/*     */     catch (ArithmeticException e) {
/* 215 */       return (0.0D / 0.0D);
/*     */     }
/*     */     catch (NumberFormatException e) {
/*     */       try {
/* 219 */         return Double.parseDouble(ControlPanel.figure(equation.replaceAll("<ivar>", "(" + (int)x + ")")));
/*     */       } catch (Exception c) {
/* 221 */         c.printStackTrace();
/* 222 */         return (0.0D / 0.0D);
/*     */       }
/*     */     } catch (Exception e) {
/*     */     }
/* 226 */     return (0.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public void recreate()
/*     */   {
/* 231 */     if (getEquations().length > 0)
/* 232 */       GraphTypeHolder.getInstance().getGraphPointMaker().getGraphBuilder().recreate(this); 
/*     */   }
/*     */ 
/* 235 */   public void trimUp(int index) { if (getPoints()[index].size() > getWidth() * 5)
/* 236 */       for (int i = getPoints()[index].size() - 1; getPoints()[index].size() >= getWidth() * 4; i--)
/* 237 */         getPoints()[index].remove(i);
/*     */   }
/*     */ 
/*     */   public void trimDown(int index)
/*     */   {
/* 242 */     if (getPoints()[index].size() > getWidth() * 5)
/* 243 */       for (int i = 0; getPoints()[index].size() >= getWidth() * 4; i++)
/* 244 */         getPoints()[index].remove(i);
/*     */   }
/*     */ 
/*     */   public double getXRes()
/*     */   {
/* 254 */     return this.xres;
/*     */   }
/*     */   public double getYRes() {
/* 257 */     return this.yres;
/*     */   }
/*     */   public double getTStart() {
/* 260 */     return this.thetaStart;
/*     */   }
/*     */   public double getTEnd() {
/* 263 */     return this.thetaEnd;
/*     */   }
/*     */   public double getTStep() {
/* 266 */     return this.thetaStep;
/*     */   }
/*     */   public void setTStart(double t) {
/* 269 */     boolean temp = t != this.thetaStart;
/* 270 */     this.thetaStart = t;
/* 271 */     if (temp)
/* 272 */       ModulusThreads.addRunnable("Point Remaker", new Runnable() {
/*     */         public void run() {
/* 274 */           Graph2D.this.remakePoints();
/*     */         }
/*     */       });
/*     */   }
/*     */ 
/*     */   public void setTEnd(double t) {
/* 280 */     boolean temp = t != this.thetaEnd;
/* 281 */     this.thetaEnd = t;
/* 282 */     if (temp)
/* 283 */       ModulusThreads.addRunnable("Point Remaker", new Runnable() {
/*     */         public void run() {
/* 285 */           Graph2D.this.remakePoints();
/*     */         }
/*     */       });
/*     */   }
/*     */ 
/*     */   public void setTStep(double t) {
/* 291 */     boolean temp = t != this.thetaStep;
/* 292 */     this.thetaStep = t;
/* 293 */     if (temp)
/* 294 */       ModulusThreads.addRunnable("Point Remaker", new Runnable() {
/*     */         public void run() {
/* 296 */           Graph2D.this.remakePoints();
/*     */         }
/*     */       });
/*     */   }
/*     */ 
/*     */   public void printPoints() {
/* 302 */     for (int i = 0; i < getPoints()[0].size(); i++) System.out.println(getPoints()[0].get(i)); 
/*     */   }
/*     */ 
/* 305 */   public void setEquations(String[] equations) { super.setEquations(equations);
/* 306 */     for (int i = 0; i < this.equationChangeListeners.size(); i++)
/* 307 */       ((EquationChangeListener)this.equationChangeListeners.get(i)).setEquations(equations); }
/*     */ 
/*     */   public void addEquationChangeListener(EquationChangeListener listener)
/*     */   {
/* 311 */     this.equationChangeListeners.add(listener);
/*     */   }
/*     */ 
/*     */   public void graphTypeChanged(Point2DMaker maker) {
/* 315 */     remakePoints();
/*     */   }
/*     */ 
/*     */   public void addStatPlot(StatPlot plot) {
/* 319 */     this.statPlots.add(plot);
/* 320 */     repaint();
/*     */   }
/*     */   public void removeStatPlot(StatPlot plot) {
/* 323 */     this.statPlots.remove(plot);
/* 324 */     repaint();
/*     */   }
/*     */   public void resetPlots() {
/* 327 */     this.statPlots = new ArrayList();
/* 328 */     repaint();
/*     */   }
/*     */   public void setRectangleMode(boolean mode) {
/* 331 */     if ((mode) && (!this.rectangleMode)) {
/* 332 */       this.mouseHold = getMouseListeners();
/* 333 */       this.mouseMotionHold = getMouseMotionListeners();
/* 334 */       for (MouseListener x : this.mouseHold)
/* 335 */         removeMouseListener(x);
/* 336 */       for (MouseMotionListener x : this.mouseMotionHold)
/* 337 */         removeMouseMotionListener(x);
/* 338 */       addMouseMotionListener(this.drawer);
/* 339 */       addMouseListener(this.drawer);
/*     */     } else {
/* 341 */       removeMouseMotionListener(this.drawer);
/* 342 */       removeMouseListener(this.drawer);
/* 343 */       for (MouseListener x : this.mouseHold)
/* 344 */         addMouseListener(x);
/* 345 */       for (MouseMotionListener x : this.mouseMotionHold)
/* 346 */         addMouseMotionListener(x);
/*     */     }
/* 348 */     this.rectangleMode = mode;
/*     */   }
/*     */   public Thread getThread() {
/* 351 */     return new Graph2D.remakeLaunch();
/*     */   }
/*     */   public void setRectangleShow(Point p1, Point p2) {
/* 354 */     this.startRect = p1;
/* 355 */     this.endRect = p2;
/*     */   }
/*     */ 
/*     */   public void removeEquationChangeListener(EquationChangeListener listen)
/*     */   {
/* 363 */     this.equationChangeListeners.remove(listen);
/*     */   }
/*     */ 
/*     */   public class remakeLaunch extends Thread
/*     */   {
/*     */     public remakeLaunch()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void run()
/*     */     {
/* 359 */       Graph2D.this.remakePoints();
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     Graph2D
 * JD-Core Version:    0.6.2
 */