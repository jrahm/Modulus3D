/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class GraphTypeHolder
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5227701003145329653L;
/*  21 */   private ArrayList<GraphTypeStateChangedListener> listeners = new ArrayList();
/*  22 */   private Point2DMaker graphType = new Euclidean2DPointMaker();
/*     */   private static GraphTypeHolder instance;
/*     */   private String[] equations;
/*     */ 
/*     */   public GraphTypeHolder()
/*     */   {
/*  27 */     for (int i = 0; i < this.listeners.size(); i++)
/*  28 */       ((GraphTypeStateChangedListener)this.listeners.get(i)).graphTypeChanged(this.graphType);
/*     */   }
/*     */ 
/*     */   public void setGraphPointMaker(Point2DMaker maker) {
/*  32 */     this.graphType = maker;
/*  33 */     fireGraphTypeStateChanged();
/*     */   }
/*     */   public void fireGraphTypeStateChanged() {
/*  36 */     for (int i = 0; i < this.listeners.size(); i++)
/*  37 */       ((GraphTypeStateChangedListener)this.listeners.get(i)).graphTypeChanged(this.graphType);
/*     */   }
/*     */ 
/*     */   public Point2DMaker getGraphPointMaker() {
/*  41 */     return this.graphType;
/*     */   }
/*     */   public void addGraphTypeStateChangedListener(GraphTypeStateChangedListener g) {
/*  44 */     if (g == null) return;
/*  45 */     this.listeners.add(g);
/*     */   }
/*     */   public String[] getEquations() {
/*  48 */     return this.equations;
/*     */   }
/*     */   public void setEquations(String[] equations) {
/*  51 */     this.equations = equations;
/*     */   }
/*     */   public static GraphTypeHolder getInstance() {
/*  54 */     if (instance == null) instance = new GraphTypeHolder();
/*  55 */     return instance;
/*     */   }
/*     */   public void serialize() {
/*  58 */     File file = null;
/*     */     try {
/*  60 */       file = new File(new File(".").getCanonicalFile(), "LastGraph.ser");
/*     */     }
/*     */     catch (IOException e) {
/*  63 */       e.printStackTrace();
/*  64 */       return;
/*     */     }
/*  66 */     FileOutputStream fos = null;
/*  67 */     ObjectOutputStream out = null;
/*     */     try
/*     */     {
/*  70 */       fos = new FileOutputStream(file);
/*  71 */       out = new ObjectOutputStream(fos);
/*  72 */       out.writeObject(this);
/*  73 */       out.close();
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/*  77 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*  81 */   private static GraphTypeHolder read() { File file = null;
/*     */     try {
/*  83 */       file = new File(new File(".").getCanonicalFile(), "LastGraph.ser");
/*     */     }
/*     */     catch (IOException e) {
/*  86 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  89 */     FileInputStream fis = null;
/*  90 */     ObjectInputStream in = null;
/*  91 */     GraphTypeHolder ret = null;
/*     */     try
/*     */     {
/*  94 */       fis = new FileInputStream(file);
/*  95 */       in = new ObjectInputStream(fis);
/*  96 */       ret = (GraphTypeHolder)in.readObject();
/*  97 */       in.close();
/*  98 */       return ret;
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/* 102 */       ex.printStackTrace();
/*     */     }
/*     */     catch (ClassNotFoundException ex)
/*     */     {
/* 106 */       ex.printStackTrace();
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     GraphTypeHolder
 * JD-Core Version:    0.6.2
 */