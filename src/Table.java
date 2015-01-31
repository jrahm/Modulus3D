/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class Table extends JPanel
/*     */   implements MouseWheelListener, GraphTypeStateChangedListener
/*     */ {
/*     */   private JTextField[][] cells;
/*     */   private JPanel panel;
/*     */   private JPanel pane;
/*     */   public double start;
/*     */   public double step;
/*     */   ArrayList<MouseWheelListener> listeners;
/*  21 */   ArrayList<JLabel> labels = new ArrayList();
/*     */ 
/*     */   public Table(String[][] stuff, String[] names) {
/*  24 */     this.cells = new JTextField[stuff.length][stuff[0].length];
/*  25 */     this.panel = new JPanel();
/*  26 */     this.panel.setLayout(new GridLayout(this.cells.length + 1, this.cells[0].length, 2, 0));
/*  27 */     for (String str : names) {
/*  28 */       this.labels.add(new JLabel(str));
/*  29 */       this.panel.add((Component)this.labels.get(this.labels.size() - 1));
/*     */     }
/*  31 */     for (int i = 0; i < this.cells.length; i++) {
/*  32 */       for (int j = 0; j < this.cells[i].length; j++) {
/*  33 */         this.cells[i][j] = new JTextField(stuff[i][j], 5);
/*  34 */         this.cells[i][j].setEditable(false);
/*  35 */         this.cells[i][j].setBackground(new Color(16777215));
/*  36 */         this.cells[i][j].addMouseWheelListener(this);
/*  37 */         this.cells[i][j].setCaretPosition(0);
/*  38 */         this.panel.add(this.cells[i][j]);
/*     */       }
/*     */     }
/*  41 */     this.panel.addMouseWheelListener(this);
/*  42 */     this.panel.setBorder(BorderFactory.createLoweredBevelBorder());
/*  43 */     add(this.panel);
/*  44 */     this.listeners = new ArrayList();
/*     */   }
/*     */ 
/*     */   public void reset(String[][] stuff)
/*     */   {
/*  49 */     for (int i = 0; i < this.cells.length; i++)
/*  50 */       for (int j = 0; j < this.cells[i].length; j++) {
/*  51 */         this.cells[i][j].setText(stuff[i][j]);
/*  52 */         this.cells[i][j].setCaretPosition(0);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static Table getInstance(String[] equs, double start, double step) throws Exception {
/*  57 */     if (equs.length < 5) {
/*  58 */       String[] nw = new String[5];
/*  59 */       for (int i = 0; i < nw.length; i++) nw[i] = "";
/*  60 */       for (int i = 0; i < equs.length; i++) nw[i] = equs[i];
/*  61 */       equs = nw;
/*     */     }
/*  63 */     String[][] data = new String[6][10];
/*  64 */     String[] names = new String[6];
/*  65 */     names[0] = ("<html><a style='font-family: Times New Roman'>" + GraphTypeHolder.getInstance().getGraphPointMaker().getIndependentVariable() + "</a></html>");
/*  66 */     for (int i = 1; i < names.length; i++) {
/*  67 */       names[i] = ("<html><a style='font-family: Times New Roman'>" + GraphTypeHolder.getInstance().getGraphPointMaker().getDependentVariable() + "<sub>" + (i - 1) + "</sub></a></html>");
/*     */     }
/*     */ 
/*  70 */     double startCpy = start;
/*  71 */     for (int i = 0; i < data[0].length; start += step) { data[0][i] = (Math.round(start * 10000.0D) / 10000.0D); i++;
/*     */     }
/*  73 */     int cnt = 1; for (int i = 0; i < 5; cnt++)
/*     */     {
/*  75 */       start = startCpy;
/*     */ 
/*  77 */       for (int j = 0; j < data[cnt].length; start += step) {
/*  78 */         if (!equs[i].equals(""))
/*     */           try {
/*  80 */             String str = Math.round(Double.parseDouble(ControlPanel.figure(equs[i].replaceAll("<ivar>", new BigDecimal(start).toPlainString()))) * 10000.0D) / 10000.0D;
/*     */ 
/*  82 */             data[cnt][j] = str;
/*     */           }
/*     */           catch (NumberFormatException ex) {
/*  85 */             data[cnt][j] = "ERR";
/*     */           }
/*  77 */         j++;
/*     */       }
/*  73 */       i++;
/*     */     }
/*     */ 
/*  90 */     String[][] rev = new String[10][6];
/*  91 */     for (int i = 0; i < rev.length; i++) {
/*  92 */       rev[i] = { data[0][i], 
/*  93 */         data[1][i], 
/*  94 */         data[2][i], 
/*  95 */         data[3][i], 
/*  96 */         data[4][i], 
/*  97 */         data[5][i] };
/*     */     }
/*     */ 
/* 101 */     Table ret = new Table(rev, names);
/* 102 */     return ret;
/*     */   }
/*     */   public void addMouseWheelListener(MouseWheelListener x) {
/* 105 */     this.listeners.add(x);
/*     */   }
/*     */ 
/*     */   public void mouseWheelMoved(MouseWheelEvent e) {
/* 109 */     for (MouseWheelListener listener : this.listeners)
/* 110 */       listener.mouseWheelMoved(e);
/*     */   }
/*     */ 
/*     */   public static void resetInstance(Table tbl, String[] equs, double start, double step) throws Exception
/*     */   {
/* 115 */     if (equs.length < 5) {
/* 116 */       String[] nw = new String[5];
/* 117 */       for (int i = 0; i < nw.length; i++) nw[i] = "";
/* 118 */       for (int i = 0; i < equs.length; i++) nw[i] = equs[i];
/* 119 */       equs = nw;
/*     */     }
/* 121 */     String[][] data = new String[6][10];
/*     */ 
/* 123 */     double startCpy = start;
/* 124 */     for (int i = 0; i < data[0].length; start += step) { data[0][i] = (Math.round(start * 10000.0D) / 10000.0D); i++;
/*     */     }
/* 126 */     int cnt = 1; for (int i = 0; i < 5; cnt++)
/*     */     {
/* 128 */       start = startCpy;
/*     */ 
/* 130 */       for (int j = 0; j < data[cnt].length; start += step) {
/* 131 */         if (!equs[i].equals(""))
/*     */           try {
/* 133 */             String str = Math.round(Double.parseDouble(ControlPanel.figure(equs[i].replaceAll("<ivar>", new BigDecimal(start).toPlainString()))) * 10000.0D) / 10000.0D;
/*     */ 
/* 135 */             data[cnt][j] = str;
/*     */           }
/*     */           catch (NumberFormatException ex) {
/* 138 */             data[cnt][j] = "ERR";
/*     */           }
/* 130 */         j++;
/*     */       }
/* 126 */       i++;
/*     */     }
/*     */ 
/* 143 */     String[][] rev = new String[10][6];
/* 144 */     for (int i = 0; i < rev.length; i++) {
/* 145 */       rev[i] = { data[0][i], 
/* 146 */         data[1][i], 
/* 147 */         data[2][i], 
/* 148 */         data[3][i], 
/* 149 */         data[4][i], 
/* 150 */         data[5][i] };
/*     */     }
/*     */ 
/* 153 */     tbl.reset(rev);
/*     */   }
/*     */   private static void print(String[][] x) {
/* 156 */     String[][] arrayOfString = x; int j = x.length; for (int i = 0; i < j; i++) { String[] strs = arrayOfString[i];
/* 157 */       for (String prt : strs) {
/* 158 */         System.out.print(prt + " ");
/*     */       }
/* 160 */       System.out.print("\n"); }
/*     */   }
/*     */ 
/*     */   private static double roundTo(double x, int to) {
/* 164 */     x *= Math.pow(10.0D, to);
/* 165 */     x = ()x;
/* 166 */     x /= Math.pow(10.0D, to);
/* 167 */     return x;
/*     */   }
/*     */ 
/*     */   public void graphTypeChanged(Point2DMaker maker) {
/* 171 */     String[] names = new String[6];
/* 172 */     names[0] = ("<html><a style='font-family: Times New Roman'>" + GraphTypeHolder.getInstance().getGraphPointMaker().getIndependentVariable() + "</a></html>");
/* 173 */     for (int i = 1; i < names.length; i++) {
/* 174 */       names[i] = ("<html><a style='font-family: Times New Roman'>" + GraphTypeHolder.getInstance().getGraphPointMaker().getDependentVariable() + "<sub>" + (i - 1) + "</sub></a></html>");
/*     */     }
/* 176 */     for (int i = 0; (i < names.length) && (i < this.labels.size()); i++)
/* 177 */       ((JLabel)this.labels.get(i)).setText(names[i]);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     Table
 * JD-Core Version:    0.6.2
 */