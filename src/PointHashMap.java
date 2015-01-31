/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class PointHashMap
/*    */ {
/*    */   private ArrayList<ArrayList<ArrayList<PointGroup>>> pointsXYZ;
/*    */ 
/*    */   public PointHashMap()
/*    */   {
/* 13 */     this.pointsXYZ = new ArrayList();
/*    */   }
/*    */   public void add(PointGroup point) {
/* 16 */     if (this.pointsXYZ.size() == 0) {
/* 17 */       ArrayList p = new ArrayList();
/* 18 */       p.add(point);
/* 19 */       ArrayList np = new ArrayList();
/* 20 */       np.add(p);
/* 21 */       this.pointsXYZ.add(np);
/* 22 */       return;
/*    */     }
/* 24 */     double lastx = ((PointGroup)((ArrayList)((ArrayList)this.pointsXYZ.get(0)).get(0)).get(0)).getAverageX();
/* 25 */     double lasty = ((PointGroup)((ArrayList)((ArrayList)this.pointsXYZ.get(0)).get(0)).get(0)).getAverageY();
/* 26 */     double lastz = ((PointGroup)((ArrayList)((ArrayList)this.pointsXYZ.get(0)).get(0)).get(0)).getAverageZ();
/* 27 */     for (int i = 0; i < this.pointsXYZ.size(); i++) {
/* 28 */       double xPos = ((PointGroup)((ArrayList)((ArrayList)this.pointsXYZ.get(i)).get(0)).get(0)).getAverageX();
/* 29 */       if (xPos == point.getAverageX())
/*    */       {
/* 31 */         for (int j = 0; j < ((ArrayList)this.pointsXYZ.get(i)).size(); j++) {
/* 32 */           double yPos = ((PointGroup)((ArrayList)((ArrayList)this.pointsXYZ.get(i)).get(j)).get(0)).getAverageY();
/* 33 */           if (yPos == point.getAverageY())
/*    */           {
/* 35 */             for (int k = 0; k < ((ArrayList)((ArrayList)this.pointsXYZ.get(i)).get(j)).size(); k++) {
/* 36 */               double zPos = ((PointGroup)((ArrayList)((ArrayList)this.pointsXYZ.get(i)).get(j)).get(k)).getAverageY();
/* 37 */               if (zPos == point.getAverageZ()) {
/* 38 */                 ((ArrayList)((ArrayList)this.pointsXYZ.get(i)).get(j)).add(k, point);
/* 39 */                 return;
/*    */               }
/* 41 */               if (zPos < point.getAverageZ()) {
/* 42 */                 ((ArrayList)((ArrayList)this.pointsXYZ.get(i)).get(j)).add(k, point);
/* 43 */                 return;
/*    */               }
/* 45 */               lastz = zPos;
/*    */             }
/*    */ 
/*    */           }
/* 49 */           else if (yPos < point.getAverageY()) {
/* 50 */             ArrayList p = new ArrayList();
/* 51 */             p.add(point);
/* 52 */             ((ArrayList)this.pointsXYZ.get(i)).add(j, p);
/* 53 */             return;
/*    */           }
/* 55 */           lasty = yPos;
/*    */         }
/*    */ 
/*    */       }
/* 59 */       else if (xPos < point.getAverageX()) {
/* 60 */         ArrayList p = new ArrayList();
/* 61 */         p.add(point);
/* 62 */         ArrayList np = new ArrayList();
/* 63 */         np.add(p);
/* 64 */         this.pointsXYZ.add(i, np);
/* 65 */         return;
/*    */       }
/* 67 */       lastx = xPos;
/*    */     }
/* 69 */     ArrayList p = new ArrayList();
/* 70 */     p.add(point);
/* 71 */     ArrayList np = new ArrayList();
/* 72 */     np.add(p);
/* 73 */     this.pointsXYZ.add(np);
/*    */   }
/*    */ 
/*    */   public void printAll() {
/* 77 */     for (int i = 0; i < this.pointsXYZ.size(); i++)
/* 78 */       for (int j = 0; j < ((ArrayList)this.pointsXYZ.get(i)).size(); j++)
/* 79 */         for (int k = 0; k < ((ArrayList)((ArrayList)this.pointsXYZ.get(i)).get(j)).size(); k++)
/* 80 */           System.out.println(((ArrayList)((ArrayList)this.pointsXYZ.get(i)).get(j)).get(k));
/*    */   }
/*    */ 
/*    */   public ArrayList<ArrayList<ArrayList<PointGroup>>> getList()
/*    */   {
/* 86 */     return this.pointsXYZ;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     PointHashMap
 * JD-Core Version:    0.6.2
 */