/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Dynamic3DGraph extends Graph3D
/*     */   implements MouseWheelListener, MouseMotionListener, MouseListener, KeyListener
/*     */ {
/*     */   private static final long serialVersionUID = 5815581053362655149L;
/*  24 */   private int lastX = 0;
/*  25 */   private int lastY = 0;
/*     */   private long lastClick;
/*     */   private int multiplier;
/*     */   private boolean shift;
/*     */   private boolean ctrl;
/*     */   private boolean alt;
/*     */   private ArrayList<MoveListener> listeners;
/*     */ 
/*     */   public Dynamic3DGraph()
/*     */   {
/*  34 */     this.multiplier = 1;
/*  35 */     addMouseWheelListener(this);
/*  36 */     addMouseMotionListener(this);
/*  37 */     addMouseListener(this);
/*  38 */     super.setCameraZ(220.0D);
/*  39 */     this.listeners = new ArrayList();
/*     */   }
/*     */   public void addMoveListener(MoveListener listen) {
/*  42 */     this.listeners.add(listen);
/*     */   }
/*     */   public void firePreformed(AWTEvent e) {
/*  45 */     for (int i = 0; i < this.listeners.size(); i++)
/*  46 */       ((MoveListener)this.listeners.get(i)).objectMoved(e);
/*     */   }
/*     */ 
/*     */   public void mouseWheelMoved(MouseWheelEvent e)
/*     */   {
/*  51 */     super.setCameraZ(super.getCameraZ() + e.getWheelRotation() * this.multiplier);
/*     */ 
/*  53 */     super.repaint();
/*  54 */     firePreformed(e);
/*     */   }
/*     */   public void mouseDragged(MouseEvent e) {
/*  57 */     super.setXRotation(super.getXRotation() - (e.getY() - this.lastY) * this.multiplier);
/*  58 */     if (!this.alt) super.setYRotation(super.getYRotation() + (this.lastX - e.getX()) * this.multiplier); else {
/*  59 */       super.setZRotation(super.getZRotation() + (this.lastX - e.getX()) * this.multiplier);
/*     */     }
/*  61 */     this.lastX = e.getX();
/*  62 */     this.lastY = e.getY();
/*  63 */     super.repaint();
/*  64 */     firePreformed(e);
/*     */   }
/*     */ 
/*     */   public void mouseMoved(MouseEvent e)
/*     */   {
/*  69 */     this.lastX = e.getX();
/*  70 */     this.lastY = e.getY();
/*  71 */     firePreformed(e);
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e) {
/*  75 */     this.lastX = e.getX();
/*  76 */     this.lastY = e.getY();
/*     */ 
/*  78 */     long lastClickNew = System.currentTimeMillis();
/*  79 */     if (Math.abs(this.lastClick - lastClickNew) < 500L) {
/*  80 */       super.setXRotation(0.0D);
/*  81 */       super.setYRotation(0.0D);
/*  82 */       super.setCameraX(0.0D);
/*  83 */       super.setCameraY(0.0D);
/*  84 */       super.setCameraZ(200.0D);
/*  85 */       super.repaint();
/*     */     }
/*  87 */     this.lastClick = lastClickNew;
/*  88 */     firePreformed(e);
/*     */   }
/*     */   public void invoke(Graphics g) {
/*  91 */     super.invoke(g);
/*  92 */     if (this.alt) new Point3D(getXPivot(), getYPivot(), getZPivot()).drawOn(g, this);
/*  93 */     firePreformed(null);
/*     */   }
/*     */   public void mouseEntered(MouseEvent e) {
/*     */   }
/*     */   public void mouseExited(MouseEvent e) {
/*     */   }
/*     */   public void mousePressed(MouseEvent e) {
/* 100 */     this.lastX = e.getX();
/* 101 */     this.lastY = e.getY();
/*     */   }
/*     */   public void mouseReleased(MouseEvent e) {
/*     */   }
/* 105 */   public void keyReleased(KeyEvent evt) { int keyCode = evt.getKeyCode();
/* 106 */     if (keyCode == 17) {
/* 107 */       this.multiplier /= 5;
/* 108 */       this.ctrl = false;
/*     */     }
/* 110 */     else if (keyCode == 16) {
/* 111 */       this.multiplier /= 5;
/* 112 */       this.shift = false;
/*     */     }
/* 114 */     else if (keyCode == 18) {
/* 115 */       this.alt = false;
/* 116 */       repaint();
/*     */     } }
/*     */ 
/*     */   public void keyTyped(KeyEvent evt)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent evt) {
/* 124 */     int keyCode = evt.getKeyCode();
/* 125 */     if (keyCode == 37) {
/* 126 */       if (this.alt)
/* 127 */         setXPivot(getXPivot() - 5 * this.multiplier);
/*     */       else {
/* 129 */         setYRotation(getYRotation() + 5 * this.multiplier);
/*     */       }
/* 131 */       repaint();
/*     */     }
/* 133 */     else if (keyCode == 39) {
/* 134 */       if (this.alt)
/* 135 */         setXPivot(getXPivot() + 5 * this.multiplier);
/*     */       else {
/* 137 */         setYRotation(getYRotation() - 5 * this.multiplier);
/*     */       }
/* 139 */       repaint();
/*     */     }
/* 141 */     else if (keyCode == 38) {
/* 142 */       if (this.alt) {
/* 143 */         setYPivot(getYPivot() + 5 * this.multiplier);
/*     */       }
/*     */       else {
/* 146 */         setXRotation(getXRotation() + 5.0D);
/*     */       }
/* 148 */       repaint();
/*     */     }
/* 150 */     else if (keyCode == 40) {
/* 151 */       if (this.alt)
/* 152 */         setYPivot(getYPivot() - 5 * this.multiplier);
/*     */       else {
/* 154 */         setXRotation(getXRotation() - 5 * this.multiplier);
/*     */       }
/* 156 */       repaint();
/*     */     }
/* 158 */     else if (keyCode == 17) {
/* 159 */       if (!this.ctrl) {
/* 160 */         this.multiplier *= 5;
/* 161 */         this.ctrl = true;
/*     */       }
/*     */     }
/* 164 */     else if (keyCode == 16) {
/* 165 */       if (!this.shift) {
/* 166 */         this.multiplier *= 5;
/* 167 */         this.shift = true;
/*     */       }
/*     */     }
/* 170 */     else if ((keyCode == 18) && (!this.alt)) {
/* 171 */       this.alt = true;
/* 172 */       repaint();
/*     */     }
/* 174 */     else if (keyCode == 61) {
/* 175 */       setCameraZ(getCameraZ() - 15 * this.multiplier);
/* 176 */       repaint();
/*     */     }
/* 178 */     else if (keyCode == 45) {
/* 179 */       setCameraZ(getCameraZ() + 15 * this.multiplier);
/* 180 */       repaint();
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     Dynamic3DGraph
 * JD-Core Version:    0.6.2
 */