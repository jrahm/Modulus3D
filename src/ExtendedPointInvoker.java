/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ 
/*     */ public abstract interface ExtendedPointInvoker extends PointInvoker
/*     */ {
/*     */   public abstract Color getColor();
/*     */ 
/*     */   public abstract void setSize(int paramInt);
/*     */ 
/*     */   public abstract int getSize();
/*     */ 
/*     */   public abstract void setColor(Color paramColor);
/*     */ 
/*     */   public static final class CirclePointInvoker
/*     */     implements ExtendedPointInvoker
/*     */   {
/*     */     private int radius;
/*     */     private Color color;
/*     */ 
/*     */     public CirclePointInvoker(int radius, Color color)
/*     */     {
/*  78 */       this.radius = radius;
/*  79 */       this.color = color;
/*     */     }
/*     */ 
/*     */     public void drawPoint(Graphics g, int x, int y) {
/*  83 */       Color temp = g.getColor();
/*  84 */       g.setColor(this.color);
/*  85 */       g.drawOval(x - this.radius / 2, y - this.radius / 2, this.radius, this.radius);
/*  86 */       g.setColor(temp);
/*     */     }
/*     */ 
/*     */     public Color getColor() {
/*  90 */       return this.color;
/*     */     }
/*     */ 
/*     */     public void setColor(Color color) {
/*  94 */       this.color = color;
/*     */     }
/*     */ 
/*     */     public int getSize()
/*     */     {
/*  99 */       return this.radius;
/*     */     }
/*     */ 
/*     */     public void setSize(int size) {
/* 103 */       this.radius = size;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class PixelPointInvoker implements ExtendedPointInvoker {
/*     */     private Color color;
/*     */ 
/*     */     public PixelPointInvoker(Color color) {
/* 111 */       this.color = color;
/*     */     }
/*     */ 
/*     */     public void drawPoint(Graphics g, int x, int y) {
/* 115 */       Color temp = g.getColor();
/* 116 */       g.setColor(this.color);
/* 117 */       g.drawRect(x, y, 0, 0);
/* 118 */       g.setColor(temp);
/*     */     }
/*     */ 
/*     */     public Color getColor() {
/* 122 */       return this.color;
/*     */     }
/*     */ 
/*     */     public void setColor(Color color) {
/* 126 */       this.color = color;
/*     */     }
/*     */ 
/*     */     public int getSize()
/*     */     {
/* 131 */       return 1;
/*     */     }
/*     */ 
/*     */     public void setSize(int size)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class SquareFillPointInvoker
/*     */     implements ExtendedPointInvoker
/*     */   {
/*     */     private int size;
/*     */     private Color color;
/*     */ 
/*     */     public SquareFillPointInvoker(int size, Color color)
/*     */     {
/*  45 */       this.size = size;
/*  46 */       this.color = color;
/*     */     }
/*     */ 
/*     */     public void drawPoint(Graphics g, int x, int y) {
/*  50 */       Color temp = g.getColor();
/*  51 */       g.setColor(this.color);
/*  52 */       g.fillRect(x - this.size / 2, y - this.size / 2, this.size, this.size);
/*  53 */       g.setColor(temp);
/*     */     }
/*     */ 
/*     */     public Color getColor() {
/*  57 */       return this.color;
/*     */     }
/*     */ 
/*     */     public void setColor(Color color) {
/*  61 */       this.color = color;
/*     */     }
/*     */ 
/*     */     public int getSize()
/*     */     {
/*  66 */       return this.size;
/*     */     }
/*     */ 
/*     */     public void setSize(int size) {
/*  70 */       this.size = size;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class SquarePointInvoker
/*     */     implements ExtendedPointInvoker
/*     */   {
/*     */     private int size;
/*     */     private Color color;
/*     */ 
/*     */     public SquarePointInvoker(int size, Color color)
/*     */     {
/*  11 */       this.size = size;
/*  12 */       this.color = color;
/*     */     }
/*     */ 
/*     */     public void drawPoint(Graphics g, int x, int y) {
/*  16 */       Color temp = g.getColor();
/*  17 */       g.setColor(this.color);
/*  18 */       g.drawRect(x - this.size / 2, y - this.size / 2, this.size, this.size);
/*  19 */       g.setColor(temp);
/*     */     }
/*     */ 
/*     */     public Color getColor() {
/*  23 */       return this.color;
/*     */     }
/*     */ 
/*     */     public void setColor(Color color) {
/*  27 */       this.color = color;
/*     */     }
/*     */ 
/*     */     public int getSize()
/*     */     {
/*  32 */       return this.size;
/*     */     }
/*     */ 
/*     */     public void setSize(int size) {
/*  36 */       this.size = size;
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     ExtendedPointInvoker
 * JD-Core Version:    0.6.2
 */