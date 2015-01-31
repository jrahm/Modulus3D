/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class YAxis extends Object3D
/*     */ {
/*     */   private Color color;
/*     */ 
/*     */   public YAxis(double xoff, double yoff, double zoff, double rotX, double rotY, double rotZ, CoordinateSystem graph, Color color)
/*     */   {
/*  95 */     super(new Point3D[][] { 
/*  15 */       { 
/*  17 */       transpose(0.0D, 1.0D, 200.0D), 
/*  18 */       transpose(0.75D, 0.75D, 200.0D), 
/*  19 */       transpose(1.0D, 0.0D, 200.0D), 
/*  20 */       transpose(0.75D, -0.75D, 200.0D), 
/*  21 */       transpose(0.0D, -1.0D, 200.0D), 
/*  22 */       transpose(-0.75D, -0.75D, 200.0D), 
/*  23 */       transpose(-1.0D, 0.0D, 200.0D), 
/*  24 */       transpose(-0.75D, 0.75D, 200.0D) }, 
/*  26 */       { 
/*  28 */       transpose(0.0D, 1.0D, -200.0D), 
/*  29 */       transpose(0.75D, 0.75D, -200.0D), 
/*  30 */       transpose(1.0D, 0.0D, 200.0D), 
/*  31 */       transpose(0.75D, -0.75D, -200.0D), 
/*  32 */       transpose(0.0D, -1.0D, 200.0D), 
/*  33 */       transpose(-0.75D, -0.75D, -200.0D), 
/*  34 */       transpose(-1.0D, 0.0D, 200.0D), 
/*  35 */       transpose(-0.75D, 0.75D, -200.0D) }, 
/*  37 */       { 
/*  39 */       transpose(0.0D, 1.0D, 200.0D), 
/*  40 */       transpose(0.0D, 1.0D, -200.0D), 
/*  41 */       transpose(0.75D, 0.75D, -200.0D), 
/*  42 */       transpose(0.75D, 0.75D, 200.0D) }, 
/*  44 */       { 
/*  46 */       transpose(0.75D, 0.75D, 200.0D), 
/*  47 */       transpose(0.75D, 0.75D, -200.0D), 
/*  48 */       transpose(1.0D, 0.0D, -200.0D), 
/*  49 */       transpose(1.0D, 0.0D, 200.0D) }, 
/*  51 */       { 
/*  53 */       transpose(1.0D, 0.0D, 200.0D), 
/*  54 */       transpose(1.0D, 0.0D, -200.0D), 
/*  55 */       transpose(0.75D, -0.75D, -200.0D), 
/*  56 */       transpose(0.75D, -0.75D, 200.0D) }, 
/*  58 */       { 
/*  60 */       transpose(0.75D, -0.75D, 200.0D), 
/*  61 */       transpose(0.75D, -0.75D, -200.0D), 
/*  62 */       transpose(0.0D, -1.0D, -200.0D), 
/*  63 */       transpose(0.0D, -1.0D, 200.0D) }, 
/*  66 */       { 
/*  68 */       transpose(0.0D, -1.0D, 200.0D), 
/*  69 */       transpose(0.0D, -1.0D, -200.0D), 
/*  70 */       transpose(-0.75D, -0.75D, -200.0D), 
/*  71 */       transpose(-0.75D, -0.75D, 200.0D) }, 
/*  73 */       { 
/*  75 */       transpose(-0.75D, -0.75D, 200.0D), 
/*  76 */       transpose(-0.75D, -0.75D, -200.0D), 
/*  77 */       transpose(-1.0D, 0.0D, -200.0D), 
/*  78 */       transpose(-1.0D, 0.0D, 200.0D) }, 
/*  80 */       { 
/*  82 */       transpose(-1.0D, 0.0D, 200.0D), 
/*  83 */       transpose(-1.0D, 0.0D, -200.0D), 
/*  84 */       transpose(-0.75D, 0.75D, -200.0D), 
/*  85 */       transpose(-0.75D, 0.75D, 200.0D) }, 
/*  87 */       { 
/*  89 */       transpose(-0.75D, 0.75D, 200.0D), 
/*  90 */       transpose(-0.75D, 0.75D, -200.0D), 
/*  91 */       transpose(0.0D, 1.0D, -200.0D), 
/*  92 */       transpose(0.0D, 1.0D, 200.0D) } }, 
/*  95 */       xoff, yoff, zoff, rotX, rotY, rotZ, graph);
/*  96 */     this.color = color;
/*     */   }
/*     */   private static Point3D transpose(double x, double z, double y) {
/*  99 */     return new Point3D(x, y, z);
/*     */   }
/*     */   public void invoke(Graphics g) {
/* 102 */     Color temp = g.getColor();
/* 103 */     g.setColor(this.color);
/* 104 */     super.invoke(g);
/* 105 */     g.setColor(temp);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     YAxis
 * JD-Core Version:    0.6.2
 */