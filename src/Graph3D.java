/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Graph3D extends GraphWorld
/*     */   implements CoordinateSystem
/*     */ {
/*  20 */   protected List<PointGroup> points = new ArrayList();
/*  21 */   private boolean slow = false;
/*  22 */   private String[] equations = { "x%y" };
/*  23 */   private boolean loaded = false;
/*     */   private BufferedImage background;
/*     */   public static final double GLOBAL_SIZE = 300.0D;
/*  26 */   private Point3D[][] axies = { 
/*  27 */     { new Point3D(0.0D, 200.0D, 0.0D, 0.0D, 0.0D, 0.0D), new Point3D(0.0D, -200.0D, 0.0D, 0.0D, 0.0D, 0.0D) }, 
/*  28 */     { new Point3D(200.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D), new Point3D(-200.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D) }, 
/*  29 */     { new Point3D(0.0D, 0.0D, 200.0D, 0.0D, 0.0D, 0.0D), new Point3D(0.0D, 0.0D, -200.0D, 0.0D, 0.0D, 0.0D) } };
/*     */ 
/*  31 */   private static Color[] colors = { 
/*  32 */     Color.GREEN, 
/*  33 */     Color.BLUE, 
/*  34 */     Color.RED };
/*     */ 
/*  36 */   protected Object3D[] axiesChar = { 
/*  37 */     new YAxisChar(0.0D, 210.0D, 0.0D, 0.0D, 0.0D, 180.0D, this, Color.GREEN), 
/*  38 */     new XAxisChar(210.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, this, Color.BLUE), 
/*  39 */     new ZAxisChar(0.0D, 0.0D, 210.0D, 0.0D, 0.0D, 180.0D, this, Color.RED), 
/*  40 */     new YAxisChar(0.0D, -210.0D, 0.0D, 0.0D, 0.0D, 180.0D, this, Color.GREEN), 
/*  41 */     new XAxisChar(-210.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, this, Color.BLUE), 
/*  42 */     new ZAxisChar(0.0D, 0.0D, -210.0D, 0.0D, 0.0D, 180.0D, this, Color.RED), 
/*  43 */     new ZAxis(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, this, Color.RED), 
/*  44 */     new XAxis(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, this, Color.BLUE), 
/*  45 */     new YAxis(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, this, Color.GREEN) };
/*     */ 
/*  47 */   private double moveX = 0.0D;
/*  48 */   private double moveY = 0.0D;
/*  49 */   private double rotoZ = 0.0D;
/*  50 */   private double rotoY = 0.0D;
/*  51 */   private double rotoX = 0.0D;
/*     */ 
/*  53 */   private double scale = 1.0D;
/*  54 */   private double pivX = 0.0D;
/*  55 */   private double pivY = 0.0D;
/*  56 */   private double pivZ = 0.0D;
/*  57 */   private double zCam = 300.0D;
/*  58 */   private double xCam = 0.0D;
/*  59 */   private double yCam = 0.0D;
/*  60 */   private boolean drawAxies = true;
/*     */ 
/*  62 */   public Graph3D() { super(1280, 1024);
/*  63 */     this.background = new BufferedImage(1, 1, 1);
/*  64 */     Graphics g = this.background.getGraphics();
/*  65 */     g.setColor(new Color(0));
/*  66 */     g.fillRect(0, 0, this.background.getWidth(), this.background.getHeight());
/*  67 */     this.scale /= this.zCam;
/*     */ 
/*  69 */     for (Object3D add : this.axiesChar)
/*  70 */       addEvent(add);
/*     */   }
/*     */ 
/*     */   public void setSlow(boolean slow)
/*     */   {
/*  76 */     this.slow = slow;
/*     */   }
/*  78 */   public void reset() { this.points = new ArrayList(); } 
/*     */   public boolean isLoaded() {
/*  80 */     return this.loaded; } 
/*  81 */   public double getXRotation() { return this.rotoX; } 
/*  82 */   public double getYRotation() { return this.rotoY; } 
/*  83 */   public double getZRotation() { return this.rotoZ; } 
/*  84 */   public double getXMove() { return this.moveX; } 
/*  85 */   public double getYMove() { return this.moveY; } 
/*  86 */   public double getScale() { return this.scale; } 
/*  87 */   public double getXPivot() { return this.pivX; } 
/*  88 */   public double getYPivot() { return this.pivY; } 
/*  89 */   public double getZPivot() { return this.pivZ; } 
/*  90 */   public double getCameraZ() { return this.zCam; } 
/*  91 */   public double getCameraX() { return this.xCam; } 
/*  92 */   public double getCameraY() { return this.yCam; } 
/*     */   public void setBackground(BufferedImage image) {
/*  94 */     this.background = image;
/*  95 */     repaint();
/*     */   }
/*     */   public BufferedImage getBackgroundImage() {
/*  98 */     return this.background;
/*     */   }
/* 100 */   public void setEquations(String[] equations) { this.equations = equations; } 
/* 101 */   public String[] getEquations() { return this.equations; } 
/*     */   public void setXRotation(double x) {
/* 103 */     if (x < 0.0D) x += 360.0D;
/* 104 */     this.rotoX = (x % 360.0D);
/*     */   }
/*     */   public void setYRotation(double y) {
/* 107 */     if (y < 0.0D) y += 360.0D;
/* 108 */     this.rotoY = (y % 360.0D);
/*     */   }
/*     */   public void setZRotation(double z) {
/* 111 */     if (z < 0.0D) z += 360.0D;
/* 112 */     this.rotoZ = (z % 360.0D); } 
/* 113 */   public void setXMove(double x) { this.moveX = x; } 
/* 114 */   public void setYMove(double y) { this.moveY = y; } 
/* 115 */   public void setScale(double s) { this.scale = s; } 
/* 116 */   public void setXPivot(double x) { this.pivX = x; } 
/* 117 */   public void setYPivot(double y) { this.pivY = y; } 
/* 118 */   public void setZPivot(double z) { this.pivZ = z; } 
/* 119 */   public void setCameraZ(double z) { this.zCam = z; } 
/* 120 */   public void setCameraX(double x) { this.xCam = x; } 
/* 121 */   public void setCameraY(double y) { this.yCam = y; } 
/*     */   public void setDrawAxies(boolean draw) {
/* 123 */     this.drawAxies = draw;
/*     */   }
/*     */   public void invoke(Graphics g) {
/* 126 */     if (this.slow) g = getGraphics();
/* 127 */     Graphics2D g2 = (Graphics2D)g;
/* 128 */     if (this.background != null) {
/* 129 */       g2.drawImage(this.background, AffineTransform.getScaleInstance(getWidth() / this.background.getWidth(), getHeight() / this.background.getHeight()), null);
/*     */     }
/*     */ 
/* 141 */     g.setColor(new Color(11141120));
/*     */ 
/* 143 */     for (int i = 0; i < this.points.size(); i++) {
/* 144 */       PointGroup p = (PointGroup)this.points.get(i);
/* 145 */       if (p != null)
/* 146 */         p.invoke(g, this);
/*     */     }
/* 148 */     if (this.drawAxies) {
/* 149 */       for (int i = 0; i < super.getEvents().size(); i++) {
/* 150 */         ((GraphicsEvent)super.getEvents().get(i)).invoke(g);
/*     */       }
/*     */     }
/* 153 */     int y = 0;
/*     */   }
/*     */   public List<PointGroup> getPoints() {
/* 156 */     return this.points;
/*     */   }
/*     */   public boolean isFocusable() {
/* 159 */     return true;
/*     */   }
/*     */   public void rotateLeft(double amt) {
/* 162 */     this.rotoZ += amt;
/*     */   }
/*     */ 
/*     */   public static double equation(String equation, double x, double y) {
/*     */     try {
/* 167 */       return Double.parseDouble(ControlPanel.figure(equation.replaceAll("x", x).replaceAll("y", y)));
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 171 */     return (0.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   protected void clean() {
/* 175 */     for (int i = 0; i < this.points.size(); i++)
/* 176 */       if (this.points.get(i) == null) this.points.remove(i);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     Graph3D
 * JD-Core Version:    0.6.2
 */