/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public class XAxisChar extends Object3D
/*     */ {
/*     */   private Color color;
/*     */ 
/*     */   public XAxisChar(double xoff, double yoff, double zoff, double rotX, double rotY, double rotZ, CoordinateSystem graph, Color color)
/*     */   {
/* 129 */     super(new Point3D[][] { 
/*  15 */       { 
/*  17 */       new Point3D(-2.5D, 3.0D, 0.0D), 
/*  18 */       new Point3D(-2.5D, 2.0D, 0.0D), 
/*  19 */       new Point3D(-0.5D, 0.0D, 0.0D), 
/*  20 */       new Point3D(-2.5D, -3.0D, 0.0D), 
/*  21 */       new Point3D(-2.5D, -2.0D, 0.0D), 
/*  22 */       new Point3D(0.0D, -0.5D, 0.0D), 
/*  23 */       new Point3D(2.5D, -3.0D, 0.0D), 
/*  24 */       new Point3D(2.5D, -2.0D, 0.0D), 
/*  25 */       new Point3D(0.5D, 0.0D, 0.0D), 
/*  26 */       new Point3D(2.5D, 3.0D, 0.0D), 
/*  27 */       new Point3D(2.5D, 2.0D, 0.0D), 
/*  28 */       new Point3D(0.0D, 0.5D, 0.0D) }, 
/*  30 */       { 
/*  32 */       new Point3D(-2.5D, 3.0D, 3.0D), 
/*  33 */       new Point3D(-2.5D, 2.0D, 3.0D), 
/*  34 */       new Point3D(-0.5D, 0.0D, 3.0D), 
/*  35 */       new Point3D(-2.5D, -3.0D, 3.0D), 
/*  36 */       new Point3D(-2.5D, -2.0D, 3.0D), 
/*  37 */       new Point3D(0.0D, -0.5D, 3.0D), 
/*  38 */       new Point3D(2.5D, -3.0D, 3.0D), 
/*  39 */       new Point3D(2.5D, -2.0D, 3.0D), 
/*  40 */       new Point3D(0.5D, 0.0D, 3.0D), 
/*  41 */       new Point3D(2.5D, 3.0D, 3.0D), 
/*  42 */       new Point3D(2.5D, 2.0D, 3.0D), 
/*  43 */       new Point3D(0.0D, 0.5D, 3.0D) }, 
/*  45 */       { 
/*  47 */       new Point3D(0.0D, 0.5D, 0.0D), 
/*  48 */       new Point3D(0.0D, 0.5D, 3.0D), 
/*  49 */       new Point3D(-2.5D, 3.0D, 3.0D), 
/*  50 */       new Point3D(-2.5D, 3.0D, 0.0D) }, 
/*  52 */       { 
/*  54 */       new Point3D(-2.5D, 3.0D, 0.0D), 
/*  55 */       new Point3D(-2.5D, 3.0D, 3.0D), 
/*  56 */       new Point3D(-2.5D, 2.0D, 3.0D), 
/*  57 */       new Point3D(-2.5D, 2.0D, 0.0D) }, 
/*  59 */       { 
/*  61 */       new Point3D(-2.5D, 2.0D, 0.0D), 
/*  62 */       new Point3D(-2.5D, 2.0D, 3.0D), 
/*  63 */       new Point3D(-0.5D, 0.0D, 3.0D), 
/*  64 */       new Point3D(-0.5D, 0.0D, 0.0D) }, 
/*  66 */       { 
/*  68 */       new Point3D(-0.5D, 0.0D, 0.0D), 
/*  69 */       new Point3D(-0.5D, 0.0D, 3.0D), 
/*  70 */       new Point3D(-2.5D, -2.0D, 3.0D), 
/*  71 */       new Point3D(-2.5D, -2.0D, 0.0D) }, 
/*  73 */       { 
/*  75 */       new Point3D(-2.5D, -2.0D, 0.0D), 
/*  76 */       new Point3D(-2.5D, -2.0D, 3.0D), 
/*  77 */       new Point3D(-2.5D, -3.0D, 3.0D), 
/*  78 */       new Point3D(-2.5D, -3.0D, 0.0D) }, 
/*  80 */       { 
/*  82 */       new Point3D(-2.5D, -3.0D, 0.0D), 
/*  83 */       new Point3D(-2.5D, -3.0D, 3.0D), 
/*  84 */       new Point3D(0.0D, -0.5D, 3.0D), 
/*  85 */       new Point3D(0.0D, -0.5D, 0.0D) }, 
/*  87 */       { 
/*  89 */       new Point3D(0.0D, -0.5D, 0.0D), 
/*  90 */       new Point3D(0.0D, -0.5D, 3.0D), 
/*  91 */       new Point3D(2.5D, -3.0D, 3.0D), 
/*  92 */       new Point3D(2.5D, -3.0D, 0.0D) }, 
/*  94 */       { 
/*  96 */       new Point3D(2.5D, -3.0D, 0.0D), 
/*  97 */       new Point3D(2.5D, -3.0D, 3.0D), 
/*  98 */       new Point3D(2.5D, -2.0D, 3.0D), 
/*  99 */       new Point3D(2.5D, -2.0D, 0.0D) }, 
/* 101 */       { 
/* 103 */       new Point3D(2.5D, -2.0D, 0.0D), 
/* 104 */       new Point3D(2.5D, -2.0D, 3.0D), 
/* 105 */       new Point3D(0.5D, 0.0D, 3.0D), 
/* 106 */       new Point3D(0.5D, 0.0D, 0.0D) }, 
/* 108 */       { 
/* 110 */       new Point3D(0.5D, 0.0D, 0.0D), 
/* 111 */       new Point3D(0.5D, 0.0D, 3.0D), 
/* 112 */       new Point3D(2.5D, 2.0D, 3.0D), 
/* 113 */       new Point3D(2.5D, 2.0D, 0.0D) }, 
/* 115 */       { 
/* 117 */       new Point3D(2.5D, 2.0D, 0.0D), 
/* 118 */       new Point3D(2.5D, 2.0D, 3.0D), 
/* 119 */       new Point3D(2.5D, 3.0D, 3.0D), 
/* 120 */       new Point3D(2.5D, 3.0D, 0.0D) }, 
/* 122 */       { 
/* 124 */       new Point3D(2.5D, 3.0D, 0.0D), 
/* 125 */       new Point3D(2.5D, 3.0D, 3.0D), 
/* 126 */       new Point3D(0.0D, 0.5D, 3.0D), 
/* 127 */       new Point3D(0.0D, 0.5D, 0.0D) } }, 
/* 129 */       xoff, yoff, zoff, rotX, rotY, rotZ, graph);
/* 130 */     this.color = color;
/*     */   }
/*     */   public void invoke(Graphics g) {
/* 133 */     Color temp = g.getColor();
/* 134 */     g.setColor(this.color);
/* 135 */     super.invoke(g);
/* 136 */     g.setColor(temp);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     XAxisChar
 * JD-Core Version:    0.6.2
 */