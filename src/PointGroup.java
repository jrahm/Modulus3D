/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Polygon;
/*     */ 
/*     */ public class PointGroup
/*     */ {
/*     */   private Point3D[] points;
/*     */   private Color color;
/*     */   private double averageZ;
/*     */   private double averageY;
/*     */   private double averageX;
/*     */   private WirePlotter plotter;
/*  19 */   public static final WirePlotter DEFAULT = new WirePlotter() {
/*     */     public void plotWire(Graphics g, CoordinateSystem graph, Point3D[] points) {
/*  21 */       int[] xPoints = new int[points.length];
/*  22 */       int[] yPoints = new int[points.length];
/*  23 */       for (int i = 0; i < points.length; i++) {
/*  24 */         double[] xyPoints = points[i].getRealCoords(graph);
/*  25 */         xPoints[i] = ((int)xyPoints[0]);
/*  26 */         yPoints[i] = ((int)xyPoints[1]);
/*     */       }
/*     */ 
/*  32 */       g.drawPolyline(xPoints, yPoints, points.length);
/*     */     }
/*  19 */   };
/*     */ 
/*  36 */   public static final WirePlotter PLOT_X_LINES = new WirePlotter() {
/*     */     public void plotWire(Graphics g, CoordinateSystem graph, Point3D[] points) {
/*  38 */       if (points.length < 4) {
/*  39 */         PointGroup.DEFAULT.plotWire(g, graph, points);
/*  40 */         return;
/*     */       }
/*  42 */       double[] xyPoints1 = points[0].getRealCoords(graph);
/*  43 */       double[] xyPoints2 = points[1].getRealCoords(graph);
/*  44 */       g.drawLine((int)xyPoints1[0], (int)xyPoints1[1], (int)xyPoints2[0], (int)xyPoints2[1]);
/*     */ 
/*  46 */       xyPoints1 = points[2].getRealCoords(graph);
/*  47 */       xyPoints2 = points[3].getRealCoords(graph);
/*  48 */       g.drawLine((int)xyPoints1[0], (int)xyPoints1[1], (int)xyPoints2[0], (int)xyPoints2[1]);
/*     */     }
/*  36 */   };
/*     */ 
/*  51 */   public static final WirePlotter PLOT_Y_LINES = new WirePlotter() {
/*     */     public void plotWire(Graphics g, CoordinateSystem graph, Point3D[] points) {
/*  53 */       if (points.length < 4) {
/*  54 */         PointGroup.DEFAULT.plotWire(g, graph, points);
/*  55 */         return;
/*     */       }
/*  57 */       double[] xyPoints1 = points[1].getRealCoords(graph);
/*  58 */       double[] xyPoints2 = points[2].getRealCoords(graph);
/*  59 */       g.drawLine((int)xyPoints1[0], (int)xyPoints1[1], (int)xyPoints2[0], (int)xyPoints2[1]);
/*     */ 
/*  61 */       xyPoints1 = points[0].getRealCoords(graph);
/*  62 */       xyPoints2 = points[3].getRealCoords(graph);
/*  63 */       g.drawLine((int)xyPoints1[0], (int)xyPoints1[1], (int)xyPoints2[0], (int)xyPoints2[1]);
/*     */     }
/*  51 */   };
/*     */ 
/*     */   public PointGroup(Point3D[] points)
/*     */   {
/*  67 */     this.points = points;
/*  68 */     double clumpX = 0.0D;
/*  69 */     double clumpY = 0.0D;
/*  70 */     double clumpZ = 0.0D;
/*  71 */     for (int i = 0; i < points.length; i++)
/*     */     {
/*  73 */       clumpZ += points[i].getZ();
/*  74 */       clumpX += points[i].getX();
/*  75 */       clumpY += points[i].getY();
/*     */     }
/*  77 */     this.averageZ = (clumpZ / points.length);
/*  78 */     this.averageX = (clumpX / points.length);
/*  79 */     this.averageY = (clumpY / points.length);
/*  80 */     this.plotter = DEFAULT;
/*     */   }
/*     */   public PointGroup(Point3D[] points, WirePlotter plotter) {
/*  83 */     this(points);
/*  84 */     this.plotter = plotter;
/*     */   }
/*     */   public void setColor(Color color) {
/*  87 */     this.color = color;
/*     */   }
/*     */   public PointGroup translateAll(double x, double y, double z, double zRotation, double xRotation, double yRotation, double pivotx, double pivoty, double pivotz) {
/*  90 */     Point3D[] newPoints = new Point3D[this.points.length];
/*  91 */     double clumpX = 0.0D;
/*  92 */     double clumpY = 0.0D;
/*  93 */     double clumpZ = 0.0D;
/*  94 */     for (int i = 0; i < this.points.length; i++) {
/*  95 */       newPoints[i] = this.points[i].transpose(x, y, z, zRotation, xRotation, yRotation, pivotx, pivoty, pivotz);
/*  96 */       clumpZ += newPoints[i].getZ();
/*  97 */       clumpX += newPoints[i].getX();
/*  98 */       clumpY += newPoints[i].getY();
/*     */     }
/*     */ 
/* 101 */     this.averageZ = (clumpZ / this.points.length);
/* 102 */     this.averageX = (clumpX / this.points.length);
/* 103 */     this.averageY = (clumpY / this.points.length);
/*     */ 
/* 105 */     PointGroup newp = new PointGroup(newPoints);
/* 106 */     newp.setColor(this.color);
/* 107 */     return newp;
/*     */   }
/*     */   public double getAverageTrueZ(CoordinateSystem syst) {
/* 110 */     double clump = 0.0D;
/* 111 */     for (Point3D pnt : this.points) {
/* 112 */       clump += pnt.getTrueZ(syst);
/*     */     }
/* 114 */     return clump / this.points.length;
/*     */   }
/*     */   public double getAverageZ() {
/* 117 */     return this.averageZ;
/*     */   }
/*     */   public double getAverageX() {
/* 120 */     return this.averageX;
/*     */   }
/*     */   public double getAverageY() {
/* 123 */     return this.averageY;
/*     */   }
/*     */   public void invoke(Graphics g, CoordinateSystem graph) {
/* 126 */     if (this.color != null) {
/* 127 */       g.setColor(this.color);
/*     */     }
/* 129 */     this.plotter.plotWire(g, graph, this.points);
/*     */   }
/*     */ 
/*     */   public void invoke(Graphics g, CoordinateSystem graph, boolean fill) {
/* 133 */     if (!fill) { invoke(g, graph);
/*     */     } else {
/* 135 */       if (this.color != null) {
/* 136 */         g.setColor(this.color);
/*     */       }
/* 138 */       int[] xPoints = new int[this.points.length];
/* 139 */       int[] yPoints = new int[this.points.length];
/* 140 */       for (int i = 0; i < this.points.length; i++) {
/* 141 */         double[] xyPoints = this.points[i].getRealCoords(graph);
/*     */ 
/* 143 */         xPoints[i] = ((int)xyPoints[0]);
/* 144 */         yPoints[i] = ((int)xyPoints[1]);
/*     */       }
/*     */ 
/* 147 */       g.fillPolygon(new Polygon(xPoints, yPoints, this.points.length));
/*     */     }
/*     */   }
/*     */ 
/* 151 */   public String toString() { return this.averageX + ", " + this.averageY + ", " + this.averageZ; }
/*     */ 
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     PointGroup
 * JD-Core Version:    0.6.2
 */