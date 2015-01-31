/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class YAxisChar extends Object3D
/*    */ {
/*    */   Color color;
/*    */ 
/*    */   public YAxisChar(double xoff, double yoff, double zoff, double rotX, double rotY, double rotZ, CoordinateSystem graph, Color color)
/*    */   {
/* 89 */     super(new Point3D[][] { { 
/* 18 */       new Point3D(-0.5D, -3.0D, 0.0D), 
/* 19 */       new Point3D(-0.5D, 0.0D, 0.0D), 
/* 20 */       new Point3D(-2.5D, 2.0D, 0.0D), 
/* 21 */       new Point3D(-2.5D, 3.0D, 0.0D), 
/* 22 */       new Point3D(0.0D, 0.5D, 0.0D), 
/* 23 */       new Point3D(2.5D, 3.0D, 0.0D), 
/* 24 */       new Point3D(2.5D, 2.0D, 0.0D), 
/* 25 */       new Point3D(0.5D, 0.0D, 0.0D), 
/* 26 */       new Point3D(0.5D, -3.0D, 0.0D) }, 
/* 28 */       { 
/* 30 */       new Point3D(-0.5D, -3.0D, 3.0D), 
/* 31 */       new Point3D(-0.5D, 0.0D, 3.0D), 
/* 32 */       new Point3D(-2.5D, 2.0D, 3.0D), 
/* 33 */       new Point3D(-2.5D, 3.0D, 3.0D), 
/* 34 */       new Point3D(0.0D, 0.5D, 3.0D), 
/* 35 */       new Point3D(2.5D, 3.0D, 3.0D), 
/* 36 */       new Point3D(2.5D, 2.0D, 3.0D), 
/* 37 */       new Point3D(0.5D, 0.0D, 3.0D), 
/* 38 */       new Point3D(0.5D, -3.0D, 3.0D) }, 
/* 40 */       { 
/* 42 */       new Point3D(2.5D, 3.0D, 0.0D), 
/* 43 */       new Point3D(2.5D, 3.0D, 3.0D), 
/* 44 */       new Point3D(2.5D, 2.0D, 3.0D), 
/* 45 */       new Point3D(2.5D, 2.0D, 0.0D) }, 
/* 47 */       { 
/* 49 */       new Point3D(2.5D, 2.0D, 0.0D), 
/* 50 */       new Point3D(2.5D, 2.0D, 3.0D), 
/* 51 */       new Point3D(0.5D, 0.0D, 3.0D), 
/* 52 */       new Point3D(0.5D, 0.0D, 0.0D) }, 
/* 54 */       { 
/* 56 */       new Point3D(0.5D, 0.0D, 0.0D), 
/* 57 */       new Point3D(0.5D, 0.0D, 3.0D), 
/* 58 */       new Point3D(0.5D, -3.0D, 3.0D), 
/* 59 */       new Point3D(0.5D, -3.0D, 0.0D) }, 
/* 61 */       { 
/* 63 */       new Point3D(0.5D, -3.0D, 0.0D), 
/* 64 */       new Point3D(0.5D, -3.0D, 3.0D), 
/* 65 */       new Point3D(-0.5D, -3.0D, 3.0D), 
/* 66 */       new Point3D(-0.5D, -3.0D, 0.0D) }, 
/* 68 */       { 
/* 70 */       new Point3D(-2.5D, 3.0D, 0.0D), 
/* 71 */       new Point3D(-2.5D, 3.0D, 3.0D), 
/* 72 */       new Point3D(-2.5D, 2.0D, 3.0D), 
/* 73 */       new Point3D(-2.5D, 2.0D, 0.0D) }, 
/* 75 */       { 
/* 77 */       new Point3D(-2.5D, 2.0D, 0.0D), 
/* 78 */       new Point3D(-2.5D, 2.0D, 3.0D), 
/* 79 */       new Point3D(-0.5D, 0.0D, 3.0D), 
/* 80 */       new Point3D(-0.5D, 0.0D, 0.0D) }, 
/* 82 */       { 
/* 84 */       new Point3D(-0.5D, 0.0D, 0.0D), 
/* 85 */       new Point3D(-0.5D, 0.0D, 3.0D), 
/* 86 */       new Point3D(-0.5D, -3.0D, 3.0D), 
/* 87 */       new Point3D(-0.5D, -3.0D, 0.0D) } }, 
/* 89 */       xoff, yoff, zoff, rotX, rotY, rotZ, graph);
/* 90 */     this.color = color;
/*    */   }
/*    */   public void invoke(Graphics g) {
/* 93 */     Color temp = g.getColor();
/* 94 */     g.setColor(this.color);
/* 95 */     super.invoke(g);
/* 96 */     g.setColor(temp);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     YAxisChar
 * JD-Core Version:    0.6.2
 */