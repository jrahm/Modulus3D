/*     */ import GUIComponents.ProgressFrame;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EquasiveGraph3D extends Dynamic3DGraph
/*     */ {
/*     */   private double xMax;
/*     */   private double yMax;
/*     */   private double zMax;
/*     */   private double zMin;
/*     */   private double xMin;
/*     */   private double yMin;
/*     */   private double xStep;
/*     */   private double yStep;
/*     */   private double yPix;
/*     */   private double xPix;
/*     */   private double zPix;
/*     */   protected ProgressFrame progress;
/*     */   private EquasiveGraph3D.InterchangeablePointModel pointModel;
/*     */ 
/*     */   public EquasiveGraph3D()
/*     */   {
/*  27 */     this(1.0D, -1.0D, -1.0D, 1.0D, -1.0D, 1.0D, 10.0D, 10.0D);
/*     */   }
/*     */   public EquasiveGraph3D(double xMax, double xMin, double yMin, double yMax, double zMin, double zMax, double xStep, double yStep) {
/*  30 */     this.xMax = xMax;
/*  31 */     this.yMax = yMax;
/*  32 */     this.zMin = zMin;
/*  33 */     this.zMax = zMax;
/*  34 */     this.xMin = xMin;
/*  35 */     this.yMin = yMin;
/*  36 */     this.xStep = xStep;
/*  37 */     this.yStep = yStep;
/*     */ 
/*  39 */     this.yPix = ((yMax - yMin) / 300.0D);
/*  40 */     this.xPix = ((xMax - xMin) / 300.0D);
/*  41 */     this.zPix = ((zMax - zMin) / 300.0D);
/*  42 */     this.pointModel = new EquasiveGraph3D.InterchangeablePointModel(PointModel.TRUE_Euclidean, PointMaker.ORIGINAL, PointGroup.DEFAULT);
/*     */   }
/*     */ 
/*     */   private void recalc() {
/*  46 */     this.yPix = ((this.yMax - this.yMin) / 300.0D);
/*  47 */     this.xPix = ((this.xMax - this.xMin) / 300.0D);
/*  48 */     this.zPix = ((this.zMax - this.zMin) / 300.0D);
/*     */   }
/*     */   public void setXMax(double x) {
/*  51 */     this.xMax = x;
/*  52 */     recalc();
/*     */   }
/*     */   public void setYMax(double y) {
/*  55 */     this.yMax = y;
/*  56 */     recalc();
/*     */   }
/*     */   public void setXMin(double x) {
/*  59 */     this.xMin = x;
/*  60 */     recalc();
/*     */   }
/*     */   public void setYMin(double y) {
/*  63 */     this.yMin = y;
/*  64 */     recalc();
/*     */   }
/*     */   public void setZMax(double z) {
/*  67 */     this.zMax = z;
/*  68 */     recalc();
/*     */   }
/*     */   public void setZMin(double z) {
/*  71 */     this.zMin = z;
/*  72 */     recalc();
/*     */   }
/*     */   public void setXStep(double x) {
/*  75 */     this.xStep = x;
/*  76 */     recalc();
/*     */   }
/*     */   public void setYStep(double y) {
/*  79 */     this.yStep = y;
/*  80 */     recalc();
/*     */   }
/*     */   public void clear() {
/*  83 */     super.reset();
/*     */   }
/*  85 */   public double getXMax() { return this.xMax; } 
/*  86 */   public double getYMin() { return this.yMin; } 
/*  87 */   public double getXMin() { return this.xMin; } 
/*  88 */   public double getYMax() { return this.yMax; } 
/*  89 */   public double getZMax() { return this.zMax; } 
/*  90 */   public double getZMin() { return this.zMin; } 
/*  91 */   public double getXStep() { return this.xStep; } 
/*  92 */   public double getYStep() { return this.yStep; } 
/*  93 */   protected double convertY(double y) { return y * this.yPix; } 
/*  94 */   protected double convertX(double x) { return x * this.xPix; } 
/*  95 */   protected double convertZ(double z) { return z * this.zPix; } 
/*     */   protected double getXPix() {
/*  97 */     return this.xPix;
/*     */   }
/*     */   protected double getYPix() {
/* 100 */     return this.yPix;
/*     */   }
/*     */   protected double getZPix() {
/* 103 */     return this.zPix;
/*     */   }
/* 105 */   public ProgressFrame getFrame() { return this.progress; } 
/*     */   public void reset() {
/* 107 */     this.points = new ArrayList(0);
/* 108 */     int i = 0;
/* 109 */     double equlen = getCount(getEquations());
/*     */ 
/* 111 */     boolean go = true;
/* 112 */     if (this.progress == null) {
/* 113 */       this.progress = new ProgressFrame(0, 100, M3DGraphWindow.getCurrent());
/*     */     }
/* 115 */     this.progress.setVisible(true);
/* 116 */     this.progress.setValue(0.0D);
/* 117 */     for (Object3D obj : this.axiesChar) {
/* 118 */       obj.setModel(this.pointModel);
/*     */     }
/* 120 */     for (double x = this.xMin / this.xPix; x < 150.0D; x += this.xStep) {
/* 121 */       int red = 255;
/* 122 */       int green = 0;
/* 123 */       int blue = 0;
/* 124 */       for (String equation : getEquations())
/*     */       {
/* 126 */         if (!equation.equals(""))
/* 127 */           for (double y = this.yMin / this.yPix; y < 150.0D; y += this.yStep)
/*     */           {
/* 129 */             Point3D[] pnts = { 
/* 130 */               this.pointModel.makePoint(x, y, equation(equation, convertX(x), convertY(y)) / this.zPix, this.pointModel), 
/* 131 */               this.pointModel.makePoint(x + this.xStep, y, equation(equation, convertX(x + this.xStep), convertY(y)) / this.zPix, this.pointModel), 
/* 132 */               this.pointModel.makePoint(x + this.xStep, y + this.yStep, equation(equation, convertX(x + this.xStep), convertY(y + this.yStep)) / this.zPix, this.pointModel), 
/* 133 */               this.pointModel.makePoint(x, y + this.yStep, equation(equation, convertX(x), convertY(y + this.yStep)) / this.zPix, this.pointModel) };
/*     */ 
/* 135 */             for (Point3D pnt : pnts) {
/* 136 */               if ((Double.isNaN(pnt.getZ())) || (Double.isInfinite(pnt.getZ()))) go = false;
/*     */             }
/* 138 */             if (go) {
/* 139 */               PointGroup temp = new PointGroup(pnts, this.pointModel);
/* 140 */               temp.setColor(new Color(red, green, blue));
/* 141 */               this.points.add(temp);
/*     */             } else {
/* 143 */               go = true;
/*     */             }
/* 145 */             if ((red == 255) && (blue == 0) && (green < 255)) {
/* 146 */               green += 10;
/* 147 */               if (green >= 255) green = 255;
/*     */             }
/* 149 */             else if ((green == 255) && (red > 0) && (blue == 0)) {
/* 150 */               red -= 10;
/* 151 */               if (red <= 0) red = 0;
/*     */             }
/* 153 */             else if ((green == 255) && (red == 0) && (blue < 255)) {
/* 154 */               blue += 10;
/* 155 */               if (blue >= 255) blue = 255;
/*     */             }
/* 157 */             else if ((blue == 255) && (red == 0) && (green > 0)) {
/* 158 */               green -= 10;
/* 159 */               if (green <= 0) green = 0;
/*     */             }
/* 161 */             else if ((blue == 255) && (green == 0) && (red < 255)) {
/* 162 */               red += 10;
/* 163 */               if (red >= 255) red = 255; 
/*     */             }
/*     */             else
/*     */             {
/* 166 */               blue -= 10;
/* 167 */               if (blue <= 0) blue = 0;
/*     */ 
/*     */             }
/*     */ 
/* 172 */             double yChange = (150.0D - this.yMin / this.yPix) / this.yStep;
/* 173 */             double xChange = (150.0D - this.xMin / this.xPix) / this.xStep;
/* 174 */             this.progress.incrementProgress(100.0D / (yChange * xChange * equlen));
/* 175 */             repaint();
/*     */           }
/*     */       }
/*     */     }
/* 179 */     this.progress.flagEnd();
/* 180 */     clean();
/*     */   }
/*     */ 
/*     */   public EquasiveGraph3D.InterchangeablePointModel getModel() {
/* 184 */     return this.pointModel;
/*     */   }
/*     */   public void setPointModel(PointModel pm) {
/* 187 */     this.pointModel.setModel(pm);
/*     */   }
/*     */   protected int getCount(String[] args) {
/* 190 */     int i = 0;
/* 191 */     for (String x : args) {
/* 192 */       if (!x.equals("")) i++;
/*     */     }
/* 194 */     return i; } 
/*     */   protected class InterchangeablePointModel implements PointModel, PointMaker, WirePlotter { private PointModel model;
/*     */     private PointMaker maker;
/*     */     private WirePlotter plotter;
/*     */ 
/* 201 */     public InterchangeablePointModel(PointModel model, PointMaker maker, WirePlotter plotter) { this.model = model;
/* 202 */       this.maker = maker;
/* 203 */       this.plotter = plotter; }
/*     */ 
/*     */     public double[] getRealCoords(double xPos, double yPos, double zPos, double xPivot, double yPivot, double zPivot, double zRotation, double xRotation, double yRotation, double scale, double xMove, double yMove, double camX, double camY, double camZ) {
/* 206 */       return this.model.getRealCoords(xPos, yPos, zPos, xPivot, yPivot, zPivot, zRotation, xRotation, yRotation, scale, xMove, yMove, camX, camY, camZ);
/*     */     }
/*     */     public void setModel(PointModel model) {
/* 209 */       this.model = model;
/*     */     }
/*     */     public void setMaker(PointMaker maker) {
/* 212 */       this.maker = maker;
/*     */     }
/*     */     public Point3D makePoint(double x, double y, double z, PointModel mod) {
/* 215 */       return this.maker.makePoint(x, y, z, mod);
/*     */     }
/*     */     public void plotWire(Graphics g, CoordinateSystem graph, Point3D[] points) {
/* 218 */       this.plotter.plotWire(g, graph, points);
/*     */     }
/*     */     public void setPlotter(WirePlotter plotter) {
/* 221 */       this.plotter = plotter;
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     EquasiveGraph3D
 * JD-Core Version:    0.6.2
 */