/*     */ import StandardIO.Approvable;
/*     */ import StandardIO.MFileFilter;
/*     */ import StandardIO.ModulusFileChooser;
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JSeparator;
/*     */ 
/*     */ public class M3DGraphWindow extends JFrame
/*     */   implements ActionListener, Flagger, MouseListener, MoveListener
/*     */ {
/*     */   private static M3DGraphWindow current;
/*     */   private ArrayList<Thread> threads;
/*     */   private ImageFrame imageholder;
/*     */   private boolean realTime;
/*  23 */   private JComponent[] comp = { 
/*  25 */     new JMenuItem("Set Equations"), 
/*  26 */     new JMenuItem("Options..."), 
/*  27 */     new JMenuItem("Re-Draw all Equations"), 
/*  28 */     new Menu("Rotate", new JComponent[] { 
/*  29 */     new JMenuItem("X - Axis"), 
/*  30 */     new JMenuItem("Y - Axis"), 
/*  31 */     new JMenuItem("Z - Axis"), 
/*  32 */     new JSeparator(), 
/*  33 */     new JSeparator(), 
/*  34 */     new JMenuItem("Stop") }), 
/*  36 */     new JMenuItem("Re-Center") };
/*     */ 
/*  39 */   private Menu[] menus = { 
/*  40 */     new Menu("File", new JComponent[] { 
/*  41 */     new JMenuItem("Capture Bitmap"), 
/*  42 */     new JRadioButton("Real-Time Capture"), 
/*  43 */     new JMenuItem("Load Graph"), 
/*  44 */     new JMenuItem("Load Heightmap") }), 
/*  46 */     new Menu("Options", new JComponent[] { 
/*  47 */     new JMenuItem("Set Equations"), 
/*  48 */     new JMenuItem("Options..."), 
/*  49 */     new JMenuItem("Re-Draw all Equations"), 
/*  50 */     new Menu("Rotate", new JComponent[] { 
/*  51 */     new JMenuItem("X - Axis"), 
/*  52 */     new JMenuItem("Y - Axis"), 
/*  53 */     new JMenuItem("Z - Axis"), 
/*  54 */     new JSeparator(), 
/*  55 */     new JSeparator(), 
/*  56 */     new JMenuItem("Stop") }), 
/*  58 */     new JMenuItem("Re-Center") }), 
/*  60 */     new Menu("Graph", new JComponent[] { 
/*  61 */     new JRadioButton("Show Axies", true), 
/*  62 */     new JMenuItem("Set Background") }) };
/*     */ 
/*  66 */   private JMenuBar bar = new JMenuBar();
/*  67 */   private EquasiveGraph3D graph = new EquasiveGraph3D(15.0D, -15.0D, -15.0D, 15.0D, -15.0D, 15.0D, 10.0D, 10.0D);
/*     */   private JPopupMenu popup;
/*  69 */   private Approvable hmapapprove = new Approvable() {
/*     */     public void onApprove(File f) {
/*  71 */       M3DGraphWindow.this.setGraph(new HeightMapGraph(f.toString()));
/*     */     }
/*     */ 
/*     */     public void onCancel()
/*     */     {
/*     */     }
/*  69 */   };
/*     */ 
/*  76 */   private Approvable bgimgapprove = new Approvable() {
/*     */     public void onApprove(File f) {
/*     */       try {
/*  79 */         M3DGraphWindow.this.getGraph().setBackground(ImageIO.read(f));
/*     */       } catch (Exception e) {
/*  81 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*     */     public void onCancel()
/*     */     {
/*     */     }
/*  76 */   };
/*     */ 
/*     */   public M3DGraphWindow()
/*     */   {
/*  89 */     setTitle("Modulus 3D Graphing Canvas");
/*  90 */     addMouseListener(this);
/*  91 */     current = this;
/*  92 */     setLayout(new BorderLayout());
/*     */ 
/*  94 */     for (Menu m : this.menus) {
/*  95 */       this.bar.add(m);
/*  96 */       m.addActionListener(this);
/*     */     }
/*  98 */     for (JComponent c : this.comp) {
/*  99 */       if ((c instanceof AbstractButton)) ((AbstractButton)c).addActionListener(this);
/*     */     }
/*     */ 
/* 102 */     add(this.graph, "Center");
/* 103 */     add(this.bar, "North");
/* 104 */     setVisible(true);
/* 105 */     setSize(new Dimension(500, 500));
/* 106 */     setMinimumSize(getSize());
/* 107 */     this.graph.addMouseListener(this);
/* 108 */     this.graph.reset();
/* 109 */     this.imageholder = new ImageFrame(this.graph.getImage());
/* 110 */     addKeyListener(this.graph);
/* 111 */     this.popup = new Menu("Options", this.comp).getPopupMenu();
/* 112 */     RangeOptionFrame.reset(this);
/* 113 */     this.threads = new ArrayList();
/*     */ 
/* 115 */     this.graph.addMoveListener(this);
/* 116 */     this.realTime = false;
/*     */   }
/*     */   public boolean isFocusable() {
/* 119 */     return true;
/*     */   }
/*     */   public void flag2(Object obj) {
/* 122 */     if ((obj instanceof String[])) {
/* 123 */       this.graph.setEquations((String[])obj);
/* 124 */       reset();
/*     */     }
/*     */   }
/*     */ 
/* 128 */   public EquasiveGraph3D getGraph() { return this.graph; }
/*     */ 
/*     */   public void setGraph(EquasiveGraph3D grph) {
/* 131 */     Dimension dim = this.graph.getSize();
/* 132 */     BufferedImage graphbg = this.graph.getBackgroundImage();
/* 133 */     String[] equations = this.graph.getEquations();
/* 134 */     remove(this.graph);
/* 135 */     grph.setSize(dim);
/* 136 */     this.graph = grph;
/* 137 */     this.graph.setBackground(graphbg);
/* 138 */     this.graph.setEquations(equations);
/* 139 */     add(this.graph, "Center");
/* 140 */     this.graph.addMouseListener(this);
/* 141 */     this.graph.addMoveListener(this);
/* 142 */     addKeyListener(this.graph);
/* 143 */     for (Component x : getComponents()) {
/* 144 */       if ((x instanceof JComponent)) {
/* 145 */         ((JComponent)x).updateUI();
/*     */       }
/*     */     }
/* 148 */     reset();
/*     */   }
/*     */   public void reset() {
/* 151 */     repaint();
/* 152 */     Thread x = new Thread(new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*     */       }
/*     */     });
/* 159 */     Thread y = new Thread(new Runnable() {
/*     */       public void run() {
/* 161 */         M3DGraphWindow.this.graph.reset();
/*     */       }
/*     */     });
/* 164 */     x.start();
/* 165 */     y.start();
/* 166 */     repaint();
/*     */   }
/*     */   public void flag(double x, double y, String key) {
/*     */   }
/*     */   public void flag0(int i) {  } 
/*     */   public void flag1(Component x) {  } 
/*     */   public void flag3() {  } 
/*     */   public void flag4() {  } 
/*     */   public void flag5() {  } 
/*     */   public void flag6() {  } 
/*     */   public void flag7() {  } 
/*     */   public void flag8() {  } 
/*     */   public void flag9() {  } 
/* 179 */   public static M3DGraphWindow getCurrent() { return current; } 
/*     */   public static void test() {
/*     */   }
/*     */   public void actionPerformed(ActionEvent e) {
/* 183 */     if ((e.getSource() == this.menus[1].get(0)) || (e.getSource() == this.comp[0])) { new ZGraphEquationChooser(this, this.graph.getEquations()).setVisible(true);
/* 184 */     } else if ((e.getSource() == this.menus[1].get(2)) || (e.getSource() == this.comp[2])) { reset();
/* 185 */     } else if ((e.getSource() == this.menus[1].get(1)) || (e.getSource() == this.comp[1])) { RangeOptionFrame.showDialog();
/* 186 */     } else if (e.getSource() == this.menus[0].get(3)) {
/* 187 */       ModulusFileChooser mfc = new ModulusFileChooser(this.hmapapprove, "", new MFileFilter[] { new MFileFilter(new String[] { ".jpg", ".gif", ".bmp", ".png" }, "All accepted images") });
/* 188 */       mfc.promptOpen();
/*     */     }
/* 190 */     else if (e.getSource() == this.menus[2].get(1)) {
/* 191 */       ModulusFileChooser mfc = new ModulusFileChooser(this.bgimgapprove, "", new MFileFilter[] { new MFileFilter(new String[] { ".jpg", ".gif", ".bmp", ".png" }, "All accepted images") });
/* 192 */       mfc.promptOpen();
/*     */     }
/* 194 */     else if ((e.getSource() == ((Menu)this.menus[1].get(3)).get(0)) || (e.getSource() == ((Menu)this.comp[3]).get(0))) {
/* 195 */       this.threads.add(new RotatingThread(getGraph(), "x"));
/* 196 */       ((Thread)this.threads.get(this.threads.size() - 1)).start();
/*     */     }
/* 198 */     else if ((e.getSource() == ((Menu)this.menus[1].get(3)).get(1)) || (e.getSource() == ((Menu)this.comp[3]).get(1))) {
/* 199 */       this.threads.add(new RotatingThread(getGraph(), "y"));
/* 200 */       ((Thread)this.threads.get(this.threads.size() - 1)).start();
/*     */     }
/* 202 */     else if ((e.getSource() == ((Menu)this.menus[1].get(3)).get(2)) || (e.getSource() == ((Menu)this.comp[3]).get(2))) {
/* 203 */       this.threads.add(new RotatingThread(getGraph(), "z"));
/* 204 */       ((Thread)this.threads.get(this.threads.size() - 1)).start();
/*     */     }
/* 206 */     else if ((e.getSource() == ((Menu)this.menus[1].get(3)).get(5)) || (e.getSource() == ((Menu)this.comp[3]).get(5)))
/*     */     {
/* 208 */       for (int i = 0; i < this.threads.size(); i++) {
/* 209 */         ((Thread)this.threads.get(i)).interrupt();
/*     */       }
/* 211 */       this.threads.clear();
/*     */     }
/* 213 */     else if (e.getSource() == this.menus[2].get(0)) {
/* 214 */       getGraph().setDrawAxies(((JRadioButton)this.menus[2].get(0)).isSelected());
/*     */     }
/* 216 */     else if (e.getSource() == this.menus[0].get(0)) {
/* 217 */       createImageFrame();
/*     */     }
/* 219 */     else if (e.getSource() == this.menus[0].get(1)) {
/* 220 */       this.realTime = ((JRadioButton)this.menus[0].get(1)).isSelected();
/* 221 */       createImageFrame();
/*     */     }
/* 223 */     getGraph().repaint();
/*     */   }
/*     */   public void mouseClicked(MouseEvent e) {
/*     */   }
/*     */   public void mousePressed(MouseEvent e) {
/* 228 */     if (e.getButton() == 3)
/* 229 */       this.popup.show(this, e.getX(), e.getY()); 
/*     */   }
/*     */   public void mouseReleased(MouseEvent e) {
/*     */   }
/*     */   public void mouseEntered(MouseEvent e) {
/*     */   }
/*     */   public void mouseExited(MouseEvent e) {  }
/*     */ 
/* 236 */   public void createImageFrame() { this.imageholder.setImage(this.graph.getImage());
/* 237 */     if (!this.imageholder.isVisible())
/* 238 */       this.imageholder.setVisible(true); }
/*     */ 
/*     */   public void objectMoved(AWTEvent e)
/*     */   {
/* 242 */     if (this.realTime) createImageFrame(); 
/*     */   }
/*     */ 
/* 245 */   public static Thread getThread() { return new M3DGraphWindow.GuiThread(null); }
/*     */ 
/*     */   private static class GuiThread extends Thread {
/*     */     public void run() {
/* 249 */       new M3DGraphWindow().setVisible(true);
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     M3DGraphWindow
 * JD-Core Version:    0.6.2
 */