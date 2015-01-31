/*     */ import GUIComponents.ProgressFrame;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Polar3DGraph extends EquasiveGraph3D
/*     */ {
/*     */   private double t1Min;
/*     */   private double t1Max;
/*     */   private double t2Min;
/*     */   private double t2Max;
/*     */   private double t1Step;
/*     */   private double t2Step;
/*     */ 
/*     */   public Polar3DGraph(double t1Min, double t1Max, double t2Min, double t2Max, double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double t1Step, double t2Step)
/*     */   {
/*  14 */     super(xMax, xMin, yMin, yMax, zMin, zMax, 0.0D, 0.0D);
/*  15 */     this.t1Min = t1Min;
/*  16 */     this.t2Min = t2Min;
/*  17 */     this.t1Max = t1Max;
/*  18 */     this.t2Max = t2Max;
/*  19 */     this.t1Step = t1Step;
/*  20 */     this.t2Step = t2Step;
/*     */   }
/*     */ 
/*     */   public void setT1Min(double t1) {
/*  24 */     this.t1Min = t1;
/*     */   }
/*  26 */   public void setT2Min(double t2) { this.t2Min = t2; } 
/*     */   public void setT1Max(double t1) {
/*  28 */     this.t1Max = t1;
/*     */   }
/*  30 */   public void setT2Max(double t2) { this.t2Max = t2; }
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/*  35 */     this.points = new ArrayList();
/*  36 */     boolean go = true;
/*  37 */     double equlen = getCount(getEquations());
/*  38 */     int red = 255;
/*  39 */     int green = 0;
/*  40 */     int blue = 0;
/*  41 */     if (this.progress == null) {
/*  42 */       this.progress = new ProgressFrame(0, 100, M3DGraphWindow.getCurrent());
/*     */     }
/*  44 */     this.progress.setVisible(true);
/*  45 */     this.progress.setValue(0.0D);
/*  46 */     this.progress.setTitle("Progress Frame");
/*  47 */     for (String equation : getEquations()) {
/*  48 */       if (!equation.equals(""))
/*  49 */         for (double t1 = this.t1Min; t1 <= this.t1Max; t1 += this.t1Step) {
/*  50 */           for (double t2 = this.t2Min; t2 <= this.t2Max; t2 += this.t2Step) {
/*  51 */             Point3D[] pnts = { 
/*  52 */               getModel().makePoint(t1, t2, equation(equation, t1, t2) / getZPix(), getModel()), 
/*  53 */               getModel().makePoint(t1 + this.t1Step, t2, equation(equation, t1 + this.t1Step, t2) / getZPix(), getModel()), 
/*  54 */               getModel().makePoint(t1 + this.t1Step, t2 + this.t2Step, equation(equation, t1 + this.t1Step, t2 + this.t2Step) / getZPix(), getModel()), 
/*  55 */               getModel().makePoint(t1, t2 + this.t2Step, equation(equation, t1, t2 + this.t2Step) / getZPix(), getModel()) };
/*     */ 
/*  57 */             for (Point3D pnt : pnts) {
/*  58 */               if ((Double.isNaN(pnt.getZ())) || (Double.isInfinite(pnt.getZ()))) go = false;
/*     */             }
/*  60 */             if (go) {
/*  61 */               PointGroup temp = new PointGroup(pnts, getModel());
/*  62 */               temp.setColor(new Color(red, green, blue));
/*  63 */               this.points.add(temp);
/*     */             } else {
/*  65 */               go = true;
/*  66 */             }double yChange = (this.t1Max - this.t1Min) / this.t1Step;
/*  67 */             double xChange = (this.t2Max - this.t2Min) / this.t2Step;
/*  68 */             this.progress.incrementProgress(100.0D / (yChange * xChange * equlen));
/*  69 */             repaint();
/*     */           }
/*     */ 
/*  72 */           if ((red == 255) && (blue == 0) && (green < 255)) {
/*  73 */             green += 10;
/*  74 */             if (green >= 255) green = 255;
/*     */           }
/*  76 */           else if ((green == 255) && (red > 0) && (blue == 0)) {
/*  77 */             red -= 10;
/*  78 */             if (red <= 0) red = 0;
/*     */           }
/*  80 */           else if ((green == 255) && (red == 0) && (blue < 255)) {
/*  81 */             blue += 10;
/*  82 */             if (blue >= 255) blue = 255;
/*     */           }
/*  84 */           else if ((blue == 255) && (red == 0) && (green > 0)) {
/*  85 */             green -= 10;
/*  86 */             if (green <= 0) green = 0;
/*     */           }
/*  88 */           else if ((blue == 255) && (green == 0) && (red < 255)) {
/*  89 */             red += 10;
/*  90 */             if (red >= 255) red = 255; 
/*     */           }
/*     */           else
/*     */           {
/*  93 */             blue -= 10;
/*  94 */             if (blue <= 0) blue = 0;
/*     */           }
/*     */         }
/*     */     }
/*  98 */     this.progress.flagEnd();
/*  99 */     clean();
/* 100 */     this.progress.setVisible(false);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     Polar3DGraph
 * JD-Core Version:    0.6.2
 */