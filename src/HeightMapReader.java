/*    */ import java.awt.Color;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class HeightMapReader
/*    */ {
/*    */   private BufferedImage heightMap;
/*    */   private double multiplier;
/*    */ 
/*    */   public HeightMapReader(String file, double multiplier)
/*    */     throws Exception
/*    */   {
/* 18 */     this(new File(file), multiplier);
/*    */   }
/*    */   public HeightMapReader(File file, double multiplier) throws Exception {
/* 21 */     this(ImageIO.read(file), multiplier);
/*    */   }
/*    */   public HeightMapReader(BufferedImage image, double multiplier) {
/* 24 */     this.heightMap = image;
/* 25 */     this.multiplier = multiplier;
/*    */   }
/*    */   public Point3D readPoint(int x, int y) {
/* 28 */     Color pixelColor = new Color(this.heightMap.getRGB(x, y));
/*    */ 
/* 30 */     return new Point3D(x - this.heightMap.getWidth() / 2.0D, pixelColor.getRed() * this.multiplier, y - this.heightMap.getHeight() / 2.0D);
/*    */   }
/*    */ 
/*    */   public Point3D[][] readAll(int xstep, int ystep) {
/* 34 */     Point3D[][] ret = new Point3D[this.heightMap.getWidth() / xstep][this.heightMap.getHeight() / ystep];
/*    */ 
/* 36 */     for (int x = 0; (x < ret.length * xstep) && (x < this.heightMap.getWidth()); x += xstep) {
/* 37 */       Point3D[] toAdd = new Point3D[this.heightMap.getWidth() / xstep];
/* 38 */       for (int y = 0; (y < toAdd.length * ystep) && (y < this.heightMap.getHeight()); y += ystep) {
/* 39 */         toAdd[(y / ystep)] = readPoint(x, y);
/*    */       }
/* 41 */       ret[(x / xstep)] = toAdd;
/*    */     }
/*    */ 
/* 44 */     return ret;
/*    */   }
/* 46 */   public double getMultiplier() { return this.multiplier; } 
/*    */   public Color getColorAt(int x, int y) {
/* 48 */     return new Color(this.heightMap.getRGB(x, y));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     HeightMapReader
 * JD-Core Version:    0.6.2
 */