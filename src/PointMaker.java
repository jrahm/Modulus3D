/*    */ public abstract interface PointMaker
/*    */ {
/* 11 */   public static final PointMaker ORIGINAL = new PointMaker() {
/*    */     public Point3D makePoint(double x, double y, double z, PointModel mod) {
/* 13 */       return new Point3D(x, y, z, mod);
/*    */     }
/* 11 */   };
/*    */ 
/* 17 */   public static final PointMaker POLAR = new PointMaker() {
/*    */     public Point3D makePoint(double x, double y, double z, PointModel mod) {
/* 19 */       return new Point3D(y * Math.cos(Math.toRadians(x)), y * Math.sin(Math.toRadians(x)), z, mod);
/*    */     }
/* 17 */   };
/*    */ 
/* 22 */   public static final PointMaker POLAR3D = new PointMaker()
/*    */   {
/*    */     public Point3D makePoint(double x, double y, double z, PointModel mod) {
/* 25 */       return new Point3D(z * Math.cos(Math.toRadians(x)) * Math.cos(Math.toRadians(y)), z * Math.sin(Math.toRadians(x)), z * Math.cos(Math.toRadians(x)) * Math.cos(Math.toRadians(90.0D - y)), mod);
/*    */     }
/* 22 */   };
/*    */ 
/*    */   public abstract Point3D makePoint(double paramDouble1, double paramDouble2, double paramDouble3, PointModel paramPointModel);
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     PointMaker
 * JD-Core Version:    0.6.2
 */