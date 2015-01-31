/*    */ public abstract interface PointModel
/*    */ {
/* 10 */   public static final PointModel TRUE_Euclidean = new PointModel() {
/*    */     public double[] getRealCoords(double xPos, double yPos, double zPos, double xPivot, double yPivot, double zPivot, double zRotation, double xRotation, double yRotation, double scale, double xMove, double yMove, double camX, double camY, double camZ) {
/* 12 */       double xd = xPos - xPivot;
/* 13 */       double yd = yPos - yPivot;
/* 14 */       double zd = zPos - zPivot;
/*    */ 
/* 16 */       double zx = xd * Math.cos(Math.toRadians(zRotation)) - yd * Math.sin(Math.toRadians(zRotation)) - xd;
/* 17 */       double zy = xd * Math.sin(Math.toRadians(zRotation)) + yd * Math.cos(Math.toRadians(zRotation)) - yd;
/*    */ 
/* 19 */       double yx = (xd + zx) * Math.cos(Math.toRadians(yRotation)) - zd * Math.sin(Math.toRadians(yRotation)) - (xd + zx);
/* 20 */       double yz = (xd + zx) * Math.sin(Math.toRadians(yRotation)) + zd * Math.cos(Math.toRadians(yRotation)) - zd;
/*    */ 
/* 22 */       double xy = (yd + zy) * Math.cos(Math.toRadians(xRotation)) - (zd + yz) * Math.sin(Math.toRadians(xRotation)) - (yd + zy);
/* 23 */       double xz = (yd + zy) * Math.sin(Math.toRadians(xRotation)) + (zd + yz) * Math.cos(Math.toRadians(xRotation)) - (zd + yz);
/*    */ 
/* 25 */       double xrotoff = yx + zx;
/* 26 */       double yrotoff = zy + xy;
/* 27 */       double zrotoff = xz + yz;
/*    */ 
/* 31 */       double realZ = zPos + zrotoff + camZ;
/* 32 */       if (realZ <= 0.0D) realZ = 0.1D;
/*    */ 
/* 34 */       double realX = (xPos + xrotoff + camX) / realZ / scale + xMove;
/* 35 */       double realY = (yPos + yrotoff + camY) / realZ / scale + yMove;
/* 36 */       return new double[] { realX, realY, realZ };
/*    */     }
/* 10 */   };
/*    */ 
/* 39 */   public static final PointModel FISHEYE = new PointModel() {
/*    */     public double[] getRealCoords(double xPos, double yPos, double zPos, double xPivot, double yPivot, double zPivot, double zRotation, double xRotation, double yRotation, double scale, double xMove, double yMove, double camX, double camY, double camZ) {
/* 41 */       double xd = xPos - xPivot;
/* 42 */       double yd = yPos - yPivot;
/* 43 */       double zd = zPos - zPivot;
/*    */ 
/* 45 */       double zx = xd * Math.cos(Math.toRadians(zRotation)) - yd * Math.sin(Math.toRadians(zRotation)) - xd;
/* 46 */       double zy = xd * Math.sin(Math.toRadians(zRotation)) + yd * Math.cos(Math.toRadians(zRotation)) - yd;
/*    */ 
/* 48 */       double yx = (xd + zx) * Math.cos(Math.toRadians(yRotation)) - zd * Math.sin(Math.toRadians(yRotation)) - (xd + zx);
/* 49 */       double yz = (xd + zx) * Math.sin(Math.toRadians(yRotation)) + zd * Math.cos(Math.toRadians(yRotation)) - zd;
/*    */ 
/* 51 */       double xy = (yd + zy) * Math.cos(Math.toRadians(xRotation)) - (zd + yz) * Math.sin(Math.toRadians(xRotation)) - (yd + zy);
/* 52 */       double xz = (yd + zy) * Math.sin(Math.toRadians(xRotation)) + (zd + yz) * Math.cos(Math.toRadians(xRotation)) - (zd + yz);
/*    */ 
/* 54 */       double xrotoff = yx + zx;
/* 55 */       double yrotoff = zy + xy;
/* 56 */       double zrotoff = xz + yz;
/*    */ 
/* 60 */       double realZ = zPos + zrotoff + camZ;
/* 61 */       if (realZ <= 0.0D) realZ = 0.1D;
/*    */ 
/* 63 */       double realX = (xPos + xrotoff + camX * 2.0D) / realZ / scale + xMove;
/* 64 */       double realY = (yPos + yrotoff + camY * 2.0D) / realZ / scale + yMove;
/* 65 */       return new double[] { realX, realY, realZ };
/*    */     }
/* 39 */   };
/*    */ 
/* 68 */   public static final PointModel ISOMETRIC_Euclidean = new PointModel() {
/*    */     public double[] getRealCoords(double xPos, double yPos, double zPos, double xPivot, double yPivot, double zPivot, double zRotation, double xRotation, double yRotation, double scale, double xMove, double yMove, double camX, double camY, double camZ) {
/* 70 */       double xd = xPos - xPivot;
/* 71 */       double yd = yPos - yPivot;
/* 72 */       double zd = zPos - zPivot;
/*    */ 
/* 74 */       double zx = xd * Math.cos(Math.toRadians(zRotation)) - yd * Math.sin(Math.toRadians(zRotation)) - xd;
/* 75 */       double zy = xd * Math.sin(Math.toRadians(zRotation)) + yd * Math.cos(Math.toRadians(zRotation)) - yd;
/*    */ 
/* 77 */       double yx = (xd + zx) * Math.cos(Math.toRadians(yRotation)) - zd * Math.sin(Math.toRadians(yRotation)) - (xd + zx);
/* 78 */       double yz = (xd + zx) * Math.sin(Math.toRadians(yRotation)) + zd * Math.cos(Math.toRadians(yRotation)) - zd;
/*    */ 
/* 80 */       double xy = (yd + zy) * Math.cos(Math.toRadians(xRotation)) - (zd + yz) * Math.sin(Math.toRadians(xRotation)) - (yd + zy);
/* 81 */       double xz = (yd + zy) * Math.sin(Math.toRadians(xRotation)) + (zd + yz) * Math.cos(Math.toRadians(xRotation)) - (zd + yz);
/*    */ 
/* 83 */       double xrotoff = yx + zx;
/* 84 */       double yrotoff = zy + xy;
/* 85 */       double zrotoff = xz + yz;
/*    */ 
/* 87 */       double realZ = zPos + zrotoff;
/* 88 */       double realX = (xPos + xrotoff) / (camZ / 300.0D) + xMove;
/* 89 */       double realY = (yPos + yrotoff) / (camZ / 300.0D) + yMove;
/* 90 */       return new double[] { realX, realY, realZ };
/*    */     }
/* 68 */   };
/*    */ 
/*    */   public abstract double[] getRealCoords(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9, double paramDouble10, double paramDouble11, double paramDouble12, double paramDouble13, double paramDouble14, double paramDouble15);
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     PointModel
 * JD-Core Version:    0.6.2
 */