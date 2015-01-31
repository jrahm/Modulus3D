/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class Point3D
/*     */ {
/*     */   private double xPos;
/*     */   private double yPos;
/*     */   private double zPos;
/*     */   private double xPivot;
/*     */   private double yPivot;
/*     */   private double zPivot;
/*     */   private PointModel model;
/*     */ 
/*     */   public Point3D(double x, double y, double z, double pivotx, double pivoty, double pivotz, PointModel model)
/*     */   {
/*  21 */     this.xPos = x;
/*  22 */     this.yPos = y;
/*  23 */     this.zPos = z;
/*  24 */     this.xPivot = pivotx;
/*  25 */     this.yPivot = pivoty;
/*  26 */     this.zPivot = pivotz;
/*  27 */     this.model = model;
/*     */   }
/*     */   public Point3D(double x, double y, double z, double pivotx, double pivoty, double pivotz) {
/*  30 */     this(x, y, z, pivotx, pivoty, pivotz, PointModel.TRUE_Euclidean);
/*     */   }
/*     */   public Point3D(double x, double y, double z, PointModel model) {
/*  33 */     this(x, y, z, 0.0D, 0.0D, 0.0D, model);
/*     */   }
/*     */   public Point3D(double x, double y, double z) {
/*  36 */     this(x, y, z, 0.0D, 0.0D, 0.0D, PointModel.TRUE_Euclidean);
/*     */   }
/*     */   public double getX() {
/*  39 */     return this.xPos;
/*     */   }
/*     */   public double getY() {
/*  42 */     return this.yPos;
/*     */   }
/*     */   public double getZ() {
/*  45 */     return this.zPos;
/*     */   }
/*     */   public void setX(double x) {
/*  48 */     this.xPos = x;
/*     */   }
/*     */   public void setY(double y) {
/*  51 */     this.yPos = y;
/*     */   }
/*     */   public void setZ(double z) {
/*  54 */     this.zPos = z;
/*     */   }
/*     */   public void setPointModel(PointModel model) {
/*  57 */     this.model = model;
/*     */   }
/*     */   public double getTrueZ(CoordinateSystem graph) {
/*  60 */     return getRealCoords(graph)[2];
/*     */   }
/*     */ 
/*     */   public double[] getRealCoords(double xPivot, double yPivot, double zPivot, double zRotation, double xRotation, double yRotation, double scale, double xMove, double yMove, double camX, double camY, double camZ) {
/*  64 */     return this.model.getRealCoords(this.xPos, this.yPos, this.zPos, xPivot, yPivot, zPivot, zRotation, xRotation, yRotation, scale, xMove, yMove, camX, camY, camZ);
/*     */   }
/*     */   public double[] getRealCoords(double xPivot, double yPivot, double zPivot, double zRotation, double xRotation, double yRotation) {
/*  67 */     return getRealCoords(xPivot, yPivot, zPivot, zRotation, xRotation, yRotation, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
/*     */   }
/*     */   public double[] getRealCoords(double zRotation, double xRotation, double yRotation) {
/*  70 */     return getRealCoords(this.xPivot, this.yPivot, this.zPivot, zRotation, xRotation, yRotation, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
/*     */   }
/*     */   public String toString() {
/*  73 */     return "[" + this.xPos + ", " + this.yPos + ", " + this.zPos + "]";
/*     */   }
/*     */   public double[] getRealCoords(CoordinateSystem graph) {
/*  76 */     double[] realPoints = getRealCoords(graph.getXPivot(), graph.getYPivot(), graph.getZPivot(), graph.getZRotation(), graph.getXRotation(), graph.getYRotation(), graph.getScale(), graph.getXMove(), graph.getYMove(), graph.getCameraX(), graph.getCameraY(), graph.getCameraZ());
/*  77 */     realPoints[0] += graph.getWidth() / 2;
/*  78 */     realPoints[1] += graph.getHeight() / 2;
/*  79 */     return realPoints;
/*     */   }
/*     */   public void drawOn(Graphics g, CoordinateSystem graph) {
/*  82 */     double[] realPoints = getRealCoords(graph.getXPivot(), graph.getYPivot(), graph.getZPivot(), graph.getZRotation(), graph.getXRotation(), graph.getYRotation(), graph.getScale(), graph.getXMove(), graph.getYMove(), graph.getCameraX(), graph.getCameraY(), graph.getCameraZ());
/*     */ 
/*  85 */     g.drawRect((int)(realPoints[0] + graph.getWidth() / 2), (int)(realPoints[1] + graph.getHeight() / 2), (int)(2.0D / graph.getCameraZ() / graph.getScale()), (int)(2.0D / graph.getCameraZ() / graph.getScale()));
/*     */   }
/*     */   public Point3D transpose(double x, double y, double z, double zRotation, double xRotation, double yRotation, double pivotx, double pivoty, double pivotz) {
/*  88 */     double newx = this.xPos;
/*  89 */     double newy = this.yPos;
/*  90 */     double newz = this.zPos;
/*     */ 
/*  92 */     double xd = newx - pivotx;
/*  93 */     double yd = newy - pivoty;
/*  94 */     double zd = newz - pivotz;
/*     */ 
/*  96 */     double zx = xd * Math.cos(Math.toRadians(zRotation)) - yd * Math.sin(Math.toRadians(zRotation)) - xd;
/*  97 */     double zy = xd * Math.sin(Math.toRadians(zRotation)) + yd * Math.cos(Math.toRadians(zRotation)) - yd;
/*     */ 
/*  99 */     double yx = (xd + zx) * Math.cos(Math.toRadians(yRotation)) - zd * Math.sin(Math.toRadians(yRotation)) - (xd + zx);
/* 100 */     double yz = (xd + zx) * Math.sin(Math.toRadians(yRotation)) + zd * Math.cos(Math.toRadians(yRotation)) - zd;
/*     */ 
/* 102 */     double xy = (yd + zy) * Math.cos(Math.toRadians(xRotation)) - (yz + zd) * Math.sin(Math.toRadians(xRotation)) - (yd + zy);
/* 103 */     double xz = (yd + zy) * Math.sin(Math.toRadians(xRotation)) + (yz + zd) * Math.cos(Math.toRadians(xRotation)) - (zd + yz);
/*     */ 
/* 105 */     double xrotooff = yx + zx;
/* 106 */     double yrotooff = zy + xy;
/* 107 */     double zrotooff = xz + yz;
/*     */ 
/* 111 */     return new Point3D(newx + xrotooff + x, newy + yrotooff + y, newz + zrotooff + z, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   public void setModel(PointModel model) {
/* 114 */     this.model = model;
/*     */   }
/*     */ 
/*     */   public void invoke(Graphics g)
/*     */   {
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     Point3D
 * JD-Core Version:    0.6.2
 */