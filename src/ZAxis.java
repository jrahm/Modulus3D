/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class ZAxis extends Object3D
/*     */ {
/*     */   private Color color;
/*     */ 
/*     */   public ZAxis(double xoff, double yoff, double zoff, double rotX, double rotY, double rotZ, CoordinateSystem graph, Color color)
/*     */   {
/*  95 */     super(new Point3D[][] { 
/*  15 */       { 
/*  17 */       new Point3D(0.0D, 1.0D, 200.0D), 
/*  18 */       new Point3D(0.75D, 0.75D, 200.0D), 
/*  19 */       new Point3D(1.0D, 0.0D, 200.0D), 
/*  20 */       new Point3D(0.75D, -0.75D, 200.0D), 
/*  21 */       new Point3D(0.0D, -1.0D, 200.0D), 
/*  22 */       new Point3D(-0.75D, -0.75D, 200.0D), 
/*  23 */       new Point3D(-1.0D, 0.0D, 200.0D), 
/*  24 */       new Point3D(-0.75D, 0.75D, 200.0D) }, 
/*  26 */       { 
/*  28 */       new Point3D(0.0D, 1.0D, -200.0D), 
/*  29 */       new Point3D(0.75D, 0.75D, -200.0D), 
/*  30 */       new Point3D(1.0D, 0.0D, 200.0D), 
/*  31 */       new Point3D(0.75D, -0.75D, -200.0D), 
/*  32 */       new Point3D(0.0D, -1.0D, 200.0D), 
/*  33 */       new Point3D(-0.75D, -0.75D, -200.0D), 
/*  34 */       new Point3D(-1.0D, 0.0D, 200.0D), 
/*  35 */       new Point3D(-0.75D, 0.75D, -200.0D) }, 
/*  37 */       { 
/*  39 */       new Point3D(0.0D, 1.0D, 200.0D), 
/*  40 */       new Point3D(0.0D, 1.0D, -200.0D), 
/*  41 */       new Point3D(0.75D, 0.75D, -200.0D), 
/*  42 */       new Point3D(0.75D, 0.75D, 200.0D) }, 
/*  44 */       { 
/*  46 */       new Point3D(0.75D, 0.75D, 200.0D), 
/*  47 */       new Point3D(0.75D, 0.75D, -200.0D), 
/*  48 */       new Point3D(1.0D, 0.0D, -200.0D), 
/*  49 */       new Point3D(1.0D, 0.0D, 200.0D) }, 
/*  51 */       { 
/*  53 */       new Point3D(1.0D, 0.0D, 200.0D), 
/*  54 */       new Point3D(1.0D, 0.0D, -200.0D), 
/*  55 */       new Point3D(0.75D, -0.75D, -200.0D), 
/*  56 */       new Point3D(0.75D, -0.75D, 200.0D) }, 
/*  58 */       { 
/*  60 */       new Point3D(0.75D, -0.75D, 200.0D), 
/*  61 */       new Point3D(0.75D, -0.75D, -200.0D), 
/*  62 */       new Point3D(0.0D, -1.0D, -200.0D), 
/*  63 */       new Point3D(0.0D, -1.0D, 200.0D) }, 
/*  66 */       { 
/*  68 */       new Point3D(0.0D, -1.0D, 200.0D), 
/*  69 */       new Point3D(0.0D, -1.0D, -200.0D), 
/*  70 */       new Point3D(-0.75D, -0.75D, -200.0D), 
/*  71 */       new Point3D(-0.75D, -0.75D, 200.0D) }, 
/*  73 */       { 
/*  75 */       new Point3D(-0.75D, -0.75D, 200.0D), 
/*  76 */       new Point3D(-0.75D, -0.75D, -200.0D), 
/*  77 */       new Point3D(-1.0D, 0.0D, -200.0D), 
/*  78 */       new Point3D(-1.0D, 0.0D, 200.0D) }, 
/*  80 */       { 
/*  82 */       new Point3D(-1.0D, 0.0D, 200.0D), 
/*  83 */       new Point3D(-1.0D, 0.0D, -200.0D), 
/*  84 */       new Point3D(-0.75D, 0.75D, -200.0D), 
/*  85 */       new Point3D(-0.75D, 0.75D, 200.0D) }, 
/*  87 */       { 
/*  89 */       new Point3D(-0.75D, 0.75D, 200.0D), 
/*  90 */       new Point3D(-0.75D, 0.75D, -200.0D), 
/*  91 */       new Point3D(0.0D, 1.0D, -200.0D), 
/*  92 */       new Point3D(0.0D, 1.0D, 200.0D) } }, 
/*  95 */       xoff, yoff, zoff, rotX, rotY, rotZ, graph);
/*  96 */     this.color = color;
/*     */   }
/*     */   public void invoke(Graphics g) {
/*  99 */     Color temp = g.getColor();
/* 100 */     g.setColor(this.color);
/* 101 */     super.invoke(g);
/* 102 */     g.setColor(temp);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     ZAxis
 * JD-Core Version:    0.6.2
 */