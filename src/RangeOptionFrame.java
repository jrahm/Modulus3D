/*     */ import GUIComponents.GridPanel;
/*     */ import GUIComponents.OptionPanel;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class RangeOptionFrame extends JDialog
/*     */   implements ActionListener
/*     */ {
/*  15 */   public static final PointMaker[] makers = { 
/*  16 */     PointMaker.ORIGINAL, 
/*  17 */     PointMaker.POLAR, 
/*  18 */     PointMaker.POLAR3D };
/*     */ 
/*  20 */   public static final PointModel[] models = { 
/*  22 */     PointModel.TRUE_Euclidean, 
/*  23 */     PointModel.ISOMETRIC_Euclidean, 
/*  24 */     PointModel.FISHEYE };
/*     */ 
/*  26 */   public static final WirePlotter[] plotters = { 
/*  27 */     PointGroup.DEFAULT, 
/*  28 */     PointGroup.PLOT_X_LINES, 
/*  29 */     PointGroup.PLOT_Y_LINES };
/*     */ 
/*  31 */   private static RangeOptionFrame current = null;
/*  32 */   private static int zequalsNumbers = 5;
/*     */   private EquasiveGraph3D graph;
/*     */   private M3DGraphWindow window;
/*     */   private GridPanel xyzMinMax;
/*     */   private GridPanel xySteps;
/*     */   private GridPanel equations;
/*     */   private GridPanel wires;
/*     */   private GridPanel mode;
/*     */   private JButton ok;
/*     */   private JButton cancel;
/*     */   private JComboBox coordinateSystem;
/*     */   private MButtonGroup bg;
/*     */   private JRadioButton radians;
/*     */   private JRadioButton degrees;
/*     */   private MButtonGroup rd;
/*     */   private JComboBox model;
/*     */   private MButtonGroup ip;
/*     */   private JTextField xMin;
/*     */   private JTextField yMin;
/*     */   private JTextField zMin;
/*     */   private JTextField xMax;
/*     */   private JTextField yMax;
/*     */   private JTextField zMax;
/*     */   private JLabel xMinT;
/*     */   private JLabel yMinT;
/*     */   private JLabel zMinT;
/*     */   private JLabel xMaxT;
/*     */   private JLabel yMaxT;
/*     */   private JLabel zMaxT;
/*     */   private JSpinner xStep;
/*     */   private JSpinner yStep;
/*     */   private JTextField[] zEquals;
/*     */   private JRadioButton solid;
/*     */   private JRadioButton gradient;
/*     */   private JRadioButton multiColor;
/*     */   private MButtonGroup colorTypes;
/*     */   private JComboBox plotType;
/*     */   private OptionPanel options;
/*     */ 
/*     */   private RangeOptionFrame(M3DGraphWindow parent)
/*     */   {
/*  83 */     super(parent, true);
/*  84 */     this.window = parent;
/*  85 */     this.graph = parent.getGraph();
/*  86 */     setTitle("Options");
/*  87 */     setLayout(new BorderLayout());
/*  88 */     this.xyzMinMax = new GridPanel(2, 3, 0, 0);
/*  89 */     this.xySteps = new GridPanel(1, 2, 0, 0);
/*  90 */     this.equations = new GridPanel(1, zequalsNumbers, 0, 0);
/*  91 */     this.wires = new GridPanel(3, 1, 0, 0);
/*  92 */     this.mode = new GridPanel(3, 2, 0, 0);
/*     */ 
/*  94 */     this.coordinateSystem = new JComboBox(new String[] { "Euclidean", "Polar", "Spheric" });
/*  95 */     this.coordinateSystem.addActionListener(this);
/*  96 */     this.radians = new JRadioButton("Radians");
/*  97 */     this.degrees = new JRadioButton("Degrees");
/*     */ 
/*  99 */     this.model = new JComboBox(new String[] { "Perspective", "Isometric", "Fisheye" });
/* 100 */     this.plotType = new JComboBox(new String[] { "Wire Mesh", "X-Wires Only", "Y-Wires Only" });
/* 101 */     this.rd = new MButtonGroup(new JRadioButton[] { this.radians, this.degrees });
/*     */ 
/* 103 */     this.xMin = new JTextField(this.graph.getXMin(), 5);
/* 104 */     this.yMin = new JTextField(this.graph.getYMin(), 5);
/* 105 */     this.zMin = new JTextField(this.graph.getZMin(), 5);
/* 106 */     this.xMax = new JTextField(this.graph.getXMax(), 5);
/* 107 */     this.yMax = new JTextField(this.graph.getYMax(), 5);
/* 108 */     this.zMax = new JTextField(this.graph.getZMax(), 5);
/*     */ 
/* 110 */     this.xStep = new JSpinner();
/* 111 */     this.xStep.setValue(Integer.valueOf(10));
/* 112 */     this.xStep.setSize(30, 10);
/* 113 */     this.yStep = new JSpinner();
/* 114 */     this.yStep.setValue(Integer.valueOf(10));
/* 115 */     this.yStep.setSize(30, 10);
/*     */ 
/* 117 */     this.zEquals = new JTextField[zequalsNumbers];
/* 118 */     for (int i = 0; i < this.zEquals.length; i++) {
/* 119 */       this.zEquals[i] = new JTextField(5);
/*     */     }
/*     */ 
/* 122 */     this.solid = new JRadioButton("Solid Color");
/* 123 */     this.gradient = new JRadioButton("Gradient");
/* 124 */     this.multiColor = new JRadioButton("Multi Color");
/* 125 */     this.colorTypes = new MButtonGroup(new JRadioButton[] { this.solid, this.gradient, this.multiColor });
/*     */ 
/* 127 */     this.xyzMinMax.add(this.xMinT = new JLabel("x Min: "), 0, 0);
/* 128 */     this.xyzMinMax.add(this.yMinT = new JLabel("y Min: "), 0, 2);
/* 129 */     this.xyzMinMax.add(this.zMinT = new JLabel("z Min: "), 1, 1);
/* 130 */     this.xyzMinMax.add(this.xMaxT = new JLabel("x Max: "), 0, 1);
/* 131 */     this.xyzMinMax.add(this.yMaxT = new JLabel("y Max: "), 1, 0);
/* 132 */     this.xyzMinMax.add(this.zMaxT = new JLabel("z Max: "), 1, 2);
/* 133 */     this.xyzMinMax.add(this.xMin, 0, 0);
/* 134 */     this.xyzMinMax.add(this.yMin, 0, 2);
/* 135 */     this.xyzMinMax.add(this.zMin, 1, 1);
/* 136 */     this.xyzMinMax.add(this.xMax, 0, 1);
/* 137 */     this.xyzMinMax.add(this.yMax, 1, 0);
/* 138 */     this.xyzMinMax.add(this.zMax, 1, 2);
/*     */ 
/* 140 */     this.xySteps.add(new JLabel("X Interval: "), 0, 0);
/* 141 */     this.xySteps.add(this.xStep, 0, 0);
/* 142 */     this.xySteps.add(new JLabel("Y Interval: "), 0, 0);
/* 143 */     this.xySteps.add(this.yStep, 0, 0);
/*     */ 
/* 145 */     for (int i = 0; i < this.zEquals.length; i++) {
/* 146 */       this.equations.add(new JLabel("z" + i), 0, i);
/* 147 */       this.equations.add(this.zEquals[i], 0, i);
/*     */     }
/*     */ 
/* 150 */     this.wires.add(this.plotType, 0, 0);
/* 151 */     this.wires.add(this.gradient, 1, 0);
/* 152 */     this.wires.add(this.multiColor, 2, 0);
/*     */ 
/* 154 */     this.mode.add(this.model, 0, 0);
/*     */ 
/* 156 */     this.mode.add(this.degrees, 1, 0);
/*     */ 
/* 158 */     this.mode.add(this.coordinateSystem, 0, 1);
/* 159 */     this.mode.add(this.radians, 2, 1);
/* 160 */     JPanel[] args = { 
/* 161 */       this.xyzMinMax, 
/* 162 */       this.xySteps, 
/* 164 */       0, this.wires, 
/* 165 */       this.mode, 
/* 166 */       new JPanel() };
/*     */ 
/* 170 */     this.options = new OptionPanel(new String[] { "Range", "Intervals", 0, "Wires", "Mode" }, args);
/* 171 */     this.ok = new JButton("Set");
/* 172 */     this.cancel = new JButton("Cancel");
/* 173 */     JPanel buttonHolder = new JPanel(new FlowLayout());
/* 174 */     add(this.options, "Center");
/*     */ 
/* 176 */     buttonHolder.add(this.ok);
/* 177 */     buttonHolder.add(this.cancel);
/* 178 */     this.ok.addActionListener(this);
/* 179 */     this.cancel.addActionListener(this);
/* 180 */     add(buttonHolder, "South");
/* 181 */     setSize(new Dimension(805, 269));
/* 182 */     setResizable(false);
/*     */   }
/*     */ 
/*     */   public void attribute()
/*     */   {
/*     */     try {
/* 188 */       if (((this.coordinateSystem.getSelectedIndex() == 1) || (this.coordinateSystem.getSelectedIndex() == 2)) && (this.window.getGraph().getClass() != Polar3DGraph.class)) {
/* 189 */         this.window.setGraph(new Polar3DGraph(0.0D, 180.0D, 0.0D, 180.0D, -15.0D, 15.0D, -15.0D, 15.0D, -15.0D, 15.0D, 5.0D, 5.0D));
/*     */       }
/* 191 */       else if ((this.coordinateSystem.getSelectedIndex() != 1) && (this.coordinateSystem.getSelectedIndex() != 2) && 
/* 192 */         (this.window.getGraph().getClass() != EquasiveGraph3D.class)) {
/* 193 */         this.window.setGraph(new EquasiveGraph3D(15.0D, -15.0D, -15.0D, 15.0D, -15.0D, 15.0D, 10.0D, 10.0D));
/*     */       }
/*     */ 
/* 196 */       this.graph = this.window.getGraph();
/* 197 */       this.window.getGraph().getModel().setMaker(makers[this.coordinateSystem.getSelectedIndex()]);
/* 198 */       this.window.getGraph().getModel().setModel(models[this.model.getSelectedIndex()]);
/* 199 */       this.window.getGraph().getModel().setPlotter(plotters[this.plotType.getSelectedIndex()]);
/* 200 */       if (this.window.getGraph().getClass() == Polar3DGraph.class) {
/* 201 */         Polar3DGraph grph = (Polar3DGraph)this.graph;
/* 202 */         grph.setT1Min(Double.parseDouble(ControlPanel.figure(this.xMin.getText())));
/* 203 */         grph.setT2Min(Double.parseDouble(ControlPanel.figure(this.yMin.getText())));
/*     */ 
/* 205 */         grph.setT1Max(Double.parseDouble(ControlPanel.figure(this.xMax.getText())));
/* 206 */         grph.setT2Max(Double.parseDouble(ControlPanel.figure(this.yMax.getText())));
/*     */       } else {
/* 208 */         this.graph.setXMin(Double.parseDouble(ControlPanel.figure(this.xMin.getText())));
/* 209 */         this.graph.setYMin(Double.parseDouble(ControlPanel.figure(this.yMin.getText())));
/*     */ 
/* 211 */         this.graph.setXMax(Double.parseDouble(ControlPanel.figure(this.xMax.getText())));
/* 212 */         this.graph.setYMax(Double.parseDouble(ControlPanel.figure(this.yMax.getText())));
/*     */       }
/* 214 */       this.graph.setZMin(Double.parseDouble(ControlPanel.figure(this.zMin.getText())));
/* 215 */       this.graph.setZMax(Double.parseDouble(ControlPanel.figure(this.zMax.getText())));
/* 216 */       this.graph.setXStep(((Integer)this.xStep.getValue()).intValue());
/* 217 */       this.graph.setYStep(((Integer)this.yStep.getValue()).intValue());
/*     */ 
/* 219 */       Calculator.setRadians(!this.radians.isSelected());
/* 220 */       this.window.reset();
/*     */     }
/*     */     catch (Exception e) {
/* 223 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/* 227 */   public void setToEuclidean() { this.xMinT.setText("x Min");
/* 228 */     this.xMaxT.setText("x Max");
/* 229 */     this.yMinT.setText("y Min");
/* 230 */     this.yMaxT.setText("y Max");
/* 231 */     this.zMinT.setText("z Min");
/* 232 */     this.zMaxT.setText("z Max"); }
/*     */ 
/*     */   public void setToPolar() {
/* 235 */     this.xMinT.setText("ϴ Min");
/* 236 */     this.xMaxT.setText("ϴ Max");
/* 237 */     this.yMinT.setText("r Min");
/* 238 */     this.yMaxT.setText("r Max");
/* 239 */     this.zMinT.setText("z Min");
/* 240 */     this.zMaxT.setText("z Max");
/*     */   }
/*     */   public void setTo3DPolar() {
/* 243 */     this.xMinT.setText("ϴ1 Min");
/* 244 */     this.xMaxT.setText("ϴ1 Max");
/* 245 */     this.yMinT.setText("ϴ2 Min");
/* 246 */     this.yMaxT.setText("ϴ2 Max");
/* 247 */     this.zMinT.setText(" r Min");
/* 248 */     this.zMaxT.setText(" r Max");
/*     */   }
/*     */   public void actionPerformed(ActionEvent e) {
/* 251 */     if (e.getSource() == this.coordinateSystem) {
/* 252 */       if (this.coordinateSystem.getSelectedIndex() == 0) {
/* 253 */         setToEuclidean();
/*     */       }
/* 255 */       else if (this.coordinateSystem.getSelectedIndex() == 1) {
/* 256 */         setToPolar();
/*     */       }
/* 258 */       else if (this.coordinateSystem.getSelectedIndex() == 2) {
/* 259 */         setTo3DPolar();
/*     */       }
/*     */     }
/*     */ 
/* 263 */     if ((e.getSource() == this.ok) || (e.getSource() == this.cancel)) {
/* 264 */       if (e.getSource() == this.ok) attribute();
/* 265 */       setVisible(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reset(M3DGraphWindow parent)
/*     */   {
/* 271 */     current = new RangeOptionFrame(parent);
/*     */   }
/*     */   public static void showDialog() {
/* 274 */     current.setVisible(true);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     RangeOptionFrame
 * JD-Core Version:    0.6.2
 */