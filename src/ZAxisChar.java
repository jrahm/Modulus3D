/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class ZAxisChar extends Object3D
/*     */ {
/*     */   private Color color;
/*     */ 
/*     */   public ZAxisChar(double xoff, double yoff, double zoff, double rotX, double rotY, double rotZ, CoordinateSystem graph, Color color)
/*     */   {
/* 113 */     super(new Point3D[][] { 
/*  15 */       { 
/*  17 */       new Point3D(-2.5D, 3.0D, 0.0D), 
/*  18 */       new Point3D(2.5D, 3.0D, 0.0D), 
/*  19 */       new Point3D(2.5D, 2.0D, 0.0D), 
/*  20 */       new Point3D(-1.5D, -2.0D, 0.0D), 
/*  21 */       new Point3D(2.5D, -2.0D, 0.0D), 
/*  22 */       new Point3D(2.5D, -3.0D, 0.0D), 
/*  23 */       new Point3D(-2.5D, -3.0D, 0.0D), 
/*  24 */       new Point3D(-2.5D, -2.0D, 0.0D), 
/*  25 */       new Point3D(1.5D, 2.0D, 0.0D), 
/*  26 */       new Point3D(-2.5D, 2.0D, 0.0D) }, 
/*  28 */       { 
/*  30 */       new Point3D(-2.5D, 3.0D, 3.0D), 
/*  31 */       new Point3D(2.5D, 3.0D, 3.0D), 
/*  32 */       new Point3D(2.5D, 2.0D, 3.0D), 
/*  33 */       new Point3D(-1.5D, -2.0D, 3.0D), 
/*  34 */       new Point3D(2.5D, -2.0D, 3.0D), 
/*  35 */       new Point3D(2.5D, -3.0D, 3.0D), 
/*  36 */       new Point3D(-2.5D, -3.0D, 3.0D), 
/*  37 */       new Point3D(-2.5D, -2.0D, 3.0D), 
/*  38 */       new Point3D(1.5D, 2.0D, 3.0D), 
/*  39 */       new Point3D(-2.5D, 2.0D, 3.0D) }, 
/*  41 */       { 
/*  43 */       new Point3D(-2.5D, 3.0D, 0.0D), 
/*  44 */       new Point3D(-2.5D, 3.0D, 3.0D), 
/*  45 */       new Point3D(-2.5D, 2.0D, 3.0D), 
/*  46 */       new Point3D(-2.5D, 2.0D, 0.0D) }, 
/*  48 */       { 
/*  50 */       new Point3D(-2.5D, 2.0D, 0.0D), 
/*  51 */       new Point3D(-2.5D, 2.0D, 3.0D), 
/*  52 */       new Point3D(1.5D, 2.0D, 3.0D), 
/*  53 */       new Point3D(1.5D, 2.0D, 0.0D) }, 
/*  55 */       { 
/*  57 */       new Point3D(1.5D, 2.0D, 0.0D), 
/*  58 */       new Point3D(1.5D, 2.0D, 3.0D), 
/*  59 */       new Point3D(-2.5D, -2.0D, 3.0D), 
/*  60 */       new Point3D(-2.5D, -2.0D, 0.0D) }, 
/*  62 */       { 
/*  64 */       new Point3D(-2.5D, -2.0D, 0.0D), 
/*  65 */       new Point3D(-2.5D, -2.0D, 3.0D), 
/*  66 */       new Point3D(-2.5D, -3.0D, 3.0D), 
/*  67 */       new Point3D(-2.5D, -3.0D, 0.0D) }, 
/*  70 */       { 
/*  72 */       new Point3D(-2.5D, -3.0D, 0.0D), 
/*  73 */       new Point3D(-2.5D, -3.0D, 3.0D), 
/*  74 */       new Point3D(2.5D, -3.0D, 3.0D), 
/*  75 */       new Point3D(2.5D, -3.0D, 0.0D) }, 
/*  77 */       { 
/*  79 */       new Point3D(2.5D, -2.0D, 0.0D), 
/*  80 */       new Point3D(2.5D, -2.0D, 3.0D), 
/*  81 */       new Point3D(2.5D, -3.0D, 3.0D), 
/*  82 */       new Point3D(2.5D, -3.0D, 0.0D) }, 
/*  84 */       { 
/*  86 */       new Point3D(2.5D, -2.0D, 0.0D), 
/*  87 */       new Point3D(2.5D, -2.0D, 3.0D), 
/*  88 */       new Point3D(-1.5D, -2.0D, 3.0D), 
/*  89 */       new Point3D(-1.5D, -2.0D, 0.0D) }, 
/*  91 */       { 
/*  93 */       new Point3D(2.5D, 2.0D, 0.0D), 
/*  94 */       new Point3D(2.5D, 2.0D, 3.0D), 
/*  95 */       new Point3D(-1.5D, -2.0D, 3.0D), 
/*  96 */       new Point3D(-1.5D, -2.0D, 0.0D) }, 
/*  98 */       { 
/* 100 */       new Point3D(2.5D, 3.0D, 0.0D), 
/* 101 */       new Point3D(2.5D, 3.0D, 3.0D), 
/* 102 */       new Point3D(2.5D, 2.0D, 3.0D), 
/* 103 */       new Point3D(2.5D, 2.0D, 0.0D) }, 
/* 105 */       { 
/* 107 */       new Point3D(-2.5D, -3.0D, 0.0D), 
/* 108 */       new Point3D(-2.5D, -3.0D, 3.0D), 
/* 109 */       new Point3D(2.5D, -3.0D, 3.0D), 
/* 110 */       new Point3D(2.5D, -3.0D, 0.0D) } }, 
/* 113 */       xoff, yoff, zoff, rotX, rotY, rotZ, graph);
/* 114 */     this.color = color;
/*     */   }
/*     */   public void invoke(Graphics g) {
/* 117 */     Color temp = g.getColor();
/* 118 */     g.setColor(this.color);
/* 119 */     super.invoke(g);
/* 120 */     g.setColor(temp);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     ZAxisChar
 * JD-Core Version:    0.6.2
 */