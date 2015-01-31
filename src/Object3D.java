/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class Object3D
/*    */   implements CoordinateSystem, GraphicsEvent
/*    */ {
/*    */   private Point3D[][] points;
/*    */   private PointGroup[] pointGroups;
/*    */   private double xoff;
/*    */   private double yoff;
/*    */   private double zoff;
/*    */   private double pivY;
/*    */   private double pivX;
/*    */   private double pivZ;
/*    */   private double rotoX;
/*    */   private double rotoY;
/*    */   private double rotoZ;
/*    */   private CoordinateSystem graph;
/*    */ 
/*    */   public Object3D(Point3D[][] points, double xoff, double yoff, double zoff, double rotX, double rotY, double rotZ, CoordinateSystem graph)
/*    */   {
/* 28 */     this.points = points;
/* 29 */     this.pointGroups = new PointGroup[points.length];
/* 30 */     this.xoff = xoff;
/* 31 */     this.yoff = yoff;
/* 32 */     this.zoff = zoff;
/* 33 */     this.rotoX = rotX;
/* 34 */     this.rotoY = rotY;
/* 35 */     this.rotoZ = rotZ;
/*    */ 
/* 37 */     for (int i = 0; i < points.length; i++) {
/* 38 */       this.pointGroups[i] = new PointGroup(points[i]);
/*    */     }
/* 40 */     this.graph = graph;
/*    */   }
/*    */ 
/*    */   public Object3D(Point3D[][] points, Color[] colors, double xoff, double yoff, double zoff, double rotX, double rotY, double rotZ, CoordinateSystem graph) {
/* 44 */     this(points, xoff, yoff, zoff, rotX, rotY, rotZ, graph);
/* 45 */     for (int i = 0; (i < this.pointGroups.length) && (i < colors.length); i++)
/* 46 */       this.pointGroups[i].setColor(colors[i]); 
/*    */   }
/*    */ 
/* 49 */   public double getCameraY() { return this.graph.getCameraY(); } 
/* 50 */   public double getCameraZ() { return this.graph.getCameraZ(); } 
/* 51 */   public double getCameraX() { return this.graph.getCameraX(); } 
/*    */   public double getXRotation() {
/* 53 */     return this.rotoX; } 
/* 54 */   public double getYRotation() { return this.rotoY; } 
/* 55 */   public double getZRotation() { return this.rotoZ; } 
/* 56 */   public double getXMove() { return this.graph.getXMove(); } 
/* 57 */   public double getYMove() { return this.graph.getYMove(); } 
/* 58 */   public double getScale() { return this.graph.getScale(); } 
/* 59 */   public double getXPivot() { return this.xoff; } 
/* 60 */   public double getYPivot() { return this.yoff; } 
/* 61 */   public double getZPivot() { return this.zoff; } 
/* 62 */   public int getWidth() { return this.graph.getWidth(); } 
/* 63 */   public int getHeight() { return this.graph.getHeight(); } 
/* 64 */   public void setXRotation(double x) { this.rotoX = (x % 360.0D); } 
/* 65 */   public void setYRotation(double y) { this.rotoY = (y % 360.0D); } 
/* 66 */   public void setZRotation(double z) { this.rotoZ = (z % 360.0D); } 
/* 67 */   public void setXPivot(double x) { this.pivX = x; } 
/* 68 */   public void setYPivot(double y) { this.pivY = y; } 
/* 69 */   public void setZPivot(double z) { this.pivZ = z; }
/*    */ 
/*    */ 
/*    */   public void invoke(Graphics g)
/*    */   {
/* 74 */     for (PointGroup pg : this.pointGroups) {
/* 75 */       PointGroup temp = pg.translateAll(this.xoff, this.yoff, this.zoff, this.rotoZ, this.rotoX, this.rotoY, 0.0D, 0.0D, 0.0D);
/*    */ 
/* 77 */       temp.invoke(g, this.graph);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setModel(PointModel pm) {
/* 82 */     for (Point3D[] p3darr : this.points) for (Point3D p3d : p3darr) p3d.setModel(pm);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Object3D
 * JD-Core Version:    0.6.2
 */