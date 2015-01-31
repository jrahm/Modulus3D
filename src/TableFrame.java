/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class TableFrame extends JFrame
/*     */   implements ActionListener, MouseWheelListener
/*     */ {
/*  16 */   private JButton snap = new JButton("Snap");
/*     */ 
/*  18 */   private JButton tblSet = new JButton("Set");
/*     */ 
/*  20 */   private JMenuBar bar = new JMenuBar();
/*     */ 
/*  23 */   private JTextField field = new JTextField("0", 5);
/*  24 */   private JLabel xEq = new JLabel("x = ");
/*     */   private JPanel buttonPanel;
/*     */   private Table table;
/*  27 */   private Menu[] menus = { 
/*  28 */     new Menu("File", new JComponent[0]), 
/*  30 */     new Menu("Table Set", new JComponent[] { 
/*  31 */     new JMenuItem("Reset Table"), 
/*  32 */     new JMenuItem("Equations") }) };
/*     */   private JScrollPane pane;
/*     */   private JPanel panePanel;
/*     */   private double start;
/*     */   private double step;
/*     */   private String[] equs;
/*     */   private TableSetter tableSetterInstance;
/*     */ 
/*     */   public TableFrame(Table table)
/*     */   {
/*  41 */     setTitle("Modulus 20 Table Frame");
/*  42 */     for (Menu menu : this.menus) {
/*  43 */       this.bar.add(menu);
/*  44 */       menu.addActionListener(this);
/*     */     }
/*  46 */     setLayout(new BorderLayout());
/*  47 */     this.panePanel = new JPanel();
/*  48 */     this.table = table;
/*  49 */     this.tableSetterInstance = new TableSetter(this, new String[] { this.start, this.step });
/*     */ 
/*  55 */     this.panePanel.add(table);
/*  56 */     add(this.bar, "North");
/*  57 */     add(this.panePanel, "Center");
/*     */ 
/*  59 */     this.buttonPanel = new JPanel(new FlowLayout());
/*     */ 
/*  61 */     this.snap.addActionListener(this);
/*     */ 
/*  64 */     this.buttonPanel.add(this.snap);
/*  65 */     this.buttonPanel.add(this.xEq);
/*  66 */     this.buttonPanel.add(this.field);
/*     */ 
/*  68 */     add(this.buttonPanel, "South");
/*  69 */     pack();
/*  70 */     setIconImage(Toolkit.getDefaultToolkit().getImage("modulus_symbol.png"));
/*  71 */     setVisible(true);
/*  72 */     this.panePanel.addMouseWheelListener(this);
/*  73 */     table.addMouseWheelListener(this);
/*  74 */     addMouseWheelListener(this);
/*     */   }
/*     */ 
/*     */   public TableFrame(String[] equs, double start, double step) throws Exception {
/*  78 */     this(Table.getInstance(equs, start, step));
/*  79 */     this.start = start;
/*  80 */     this.step = step;
/*  81 */     this.equs = equs;
/*     */   }
/*     */   public void mouseWheelMoved(MouseWheelEvent e) {
/*  84 */     this.start += e.getWheelRotation();
/*     */     try {
/*  86 */       Table.resetInstance(this.table, this.equs, this.start, this.step);
/*     */     }
/*     */     catch (Exception ex) {
/*  89 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*  97 */     if (e.getSource().getClass() == TableSetter.class) {
/*  98 */       TableSetter y = (TableSetter)e.getSource();
/*  99 */       this.start = Double.parseDouble(y.getText()[0]);
/* 100 */       this.step = Double.parseDouble(y.getText()[1]);
/*     */     }
/* 102 */     if (e.getSource() == this.snap) {
/*     */       try {
/* 104 */         Table.resetInstance(this.table, this.equs, this.start = Double.parseDouble(this.field.getText()), this.step);
/*     */       }
/*     */       catch (Exception ex) {
/* 107 */         ex.printStackTrace();
/*     */       }
/*     */     }
/* 110 */     else if (e.getSource() == this.menus[1].get(0))
/* 111 */       this.tableSetterInstance.setVisible(true);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     TableFrame
 * JD-Core Version:    0.6.2
 */