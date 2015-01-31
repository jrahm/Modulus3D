/*    */ import GUIComponents.ProgressFrame;
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HeightMapGraph extends EquasiveGraph3D
/*    */ {
/*    */   private HeightMapReader map;
/*    */ 
/*    */   public HeightMapGraph(String image)
/*    */   {
/* 15 */     super(300.0D, -300.0D, -300.0D, 300.0D, -300.0D, 300.0D, 10.0D, 10.0D);
/*    */     try {
/* 17 */       this.map = new HeightMapReader(image, 0.5D);
/*    */     }
/*    */     catch (Exception localException) {
/*    */     }
/*    */   }
/*    */ 
/*    */   public void reset() {
/* 24 */     this.points = new ArrayList();
/* 25 */     Point3D[][] points = this.map.readAll((int)getXStep(), (int)getYStep());
/* 26 */     if (this.progress == null) {
/* 27 */       this.progress = new ProgressFrame(0, 100, M3DGraphWindow.getCurrent());
/* 28 */       this.progress.setVisible(true);
/*    */     }
/* 30 */     this.progress.setVisible(true);
/* 31 */     this.progress.setValue(0.0D);
/* 32 */     for (int x = 0; x < points.length - 1; x++)
/* 33 */       for (int y = 0; y < points[x].length - 1; y++) {
/* 34 */         Point3D[] group = { 
/* 35 */           points[x][y], 
/* 36 */           points[(x + 1)][y], 
/* 37 */           points[(x + 1)][(y + 1)], 
/* 38 */           points[x][(y + 1)] };
/*    */ 
/* 41 */         if (checkGroup(group)) {
/* 42 */           for (Point3D p : group) {
/* 43 */             p.setModel(getModel());
/*    */           }
/* 45 */           PointGroup temp = new PointGroup(group);
/* 46 */           int adv = (int)Math.min(averageHeight(group) / this.map.getMultiplier(), 255.0D);
/* 47 */           temp.setColor(new Color(adv, adv, adv));
/* 48 */           this.points.add(temp);
/* 49 */           repaint();
/*    */         }
/*    */       }
/* 52 */     this.progress.flagEnd();
/*    */   }
/*    */   private boolean checkGroup(Point3D[] points) {
/* 55 */     for (Point3D x : points) if (x == null) return false;
/* 56 */     return true;
/*    */   }
/*    */   private double averageHeight(Point3D[] pnts) {
/* 59 */     double total = 0.0D;
/* 60 */     for (Point3D p : pnts) {
/* 61 */       total += p.getY();
/*    */     }
/* 63 */     return total / pnts.length;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     HeightMapGraph
 * JD-Core Version:    0.6.2
 */