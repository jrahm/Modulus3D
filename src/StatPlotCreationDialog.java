/*     */ import GUIComponents.PushablePanel;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ public class StatPlotCreationDialog extends JDialog
/*     */   implements ActionListener, ChangeListener
/*     */ {
/*     */   private static final int invoker_length = 3;
/*  34 */   private PushablePanel panel = new PushablePanel();
/*     */   private JButton add;
/*     */   private JButton cancel;
/*     */   private JButton delete;
/*     */   private JButton ok;
/*  36 */   private StatPlot ret = null;
/*     */   private GraphTranslator translate;
/*     */   private StatPlotDialog owner;
/*     */   private Color colorChosen;
/*     */   private JButton colorButton;
/*  41 */   private ExtendedPointInvoker[] invokers = { 
/*  42 */     new ExtendedPointInvoker.SquarePointInvoker(5, Color.black), 
/*  43 */     new ExtendedPointInvoker.SquareFillPointInvoker(5, Color.black), 
/*  44 */     new ExtendedPointInvoker.CirclePointInvoker(5, Color.black), 
/*  45 */     new ExtendedPointInvoker.PixelPointInvoker(Color.black) };
/*     */   private JComboBox combobox;
/*     */   private JSpinner spinner;
/*     */   private int editNumber;
/*     */ 
/*     */   public StatPlotCreationDialog(StatPlotDialog owner, GraphTranslator translate, int editNumber)
/*     */   {
/*  52 */     super(owner, "Statplot Maker", true);
/*  53 */     this.owner = owner;
/*  54 */     this.translate = translate;
/*  55 */     this.editNumber = editNumber;
/*  56 */     this.colorChosen = Color.black;
/*  57 */     this.colorButton = new JButton(getButtonIcon());
/*  58 */     this.colorButton.setPreferredSize(new Dimension(30, 30));
/*  59 */     this.colorButton.addActionListener(this);
/*  60 */     this.combobox = new JComboBox();
/*  61 */     hideArrow();
/*  62 */     this.combobox.setPreferredSize(new Dimension(80, 30));
/*  63 */     this.spinner = new JSpinner();
/*  64 */     this.spinner.addChangeListener(this);
/*  65 */     updateOptions();
/*  66 */     JPanel main = new JPanel();
/*  67 */     JPanel buttons = new JPanel(new FlowLayout());
/*  68 */     JPanel head = new JPanel(new FlowLayout());
/*  69 */     JPanel addDelete = new JPanel();
/*  70 */     this.panel = new PushablePanel();
/*  71 */     addDelete.setLayout(new BoxLayout(addDelete, 1));
/*  72 */     main.setLayout(new BoxLayout(main, 1));
/*     */ 
/*  74 */     addDelete.setPreferredSize(new Dimension(105, 250));
/*  75 */     this.panel.setPreferredSize(new Dimension(200, 250));
/*  76 */     buttons.setPreferredSize(new Dimension(350, 50));
/*     */ 
/*  78 */     this.add = new JButton("add");
/*  79 */     this.cancel = new JButton("Cancel");
/*  80 */     this.delete = new JButton("delete");
/*  81 */     this.ok = new JButton("Apply");
/*  82 */     JPanel addhold = new JPanel();
/*  83 */     JPanel deletehold = new JPanel();
/*  84 */     this.add.addActionListener(this);
/*  85 */     this.cancel.addActionListener(this);
/*  86 */     this.delete.addActionListener(this);
/*  87 */     this.ok.addActionListener(this);
/*  88 */     addhold.add(this.add);
/*  89 */     deletehold.add(this.delete);
/*  90 */     addDelete.add(addhold);
/*  91 */     addDelete.add(deletehold);
/*     */ 
/*  93 */     buttons.add(this.ok);
/*  94 */     buttons.add(this.cancel);
/*     */ 
/*  96 */     JPanel options = new JPanel();
/*  97 */     JPanel options1 = new JPanel(new FlowLayout());
/*  98 */     JPanel options2 = new JPanel(new FlowLayout());
/*  99 */     options.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Options"));
/* 100 */     options.setLayout(new BoxLayout(options, 1));
/* 101 */     options.setPreferredSize(new Dimension(105, 175));
/* 102 */     options1.add(this.colorButton);
/* 103 */     options1.add(this.combobox);
/* 104 */     options2.add(this.spinner);
/* 105 */     options.add(options1);
/* 106 */     options.add(options2);
/*     */ 
/* 108 */     addDelete.add(options);
/* 109 */     head.add(this.panel);
/* 110 */     head.add(addDelete);
/*     */ 
/* 112 */     main.add(head);
/* 113 */     main.add(buttons);
/*     */ 
/* 115 */     add(main);
/* 116 */     setPreferredSize(new Dimension(325, 350));
/* 117 */     setResizable(false);
/* 118 */     pack();
/*     */   }
/*     */ 
/*     */   public StatPlot getPlot()
/*     */   {
/* 146 */     ArrayList comps = this.panel.getPushedComponents();
/* 147 */     ArrayList points = new ArrayList();
/* 148 */     for (int i = 0; i < comps.size(); i++) {
/* 149 */       if ((comps.get(i) instanceof StatPlotCreationDialog.PointPanel)) {
/* 150 */         StatPlotCreationDialog.PointPanel pointpanel = (StatPlotCreationDialog.PointPanel)comps.get(i);
/* 151 */         points.add(pointpanel.getPoint());
/*     */       }
/*     */     }
/* 154 */     return new StatPlot(points, this.invokers[this.combobox.getSelectedIndex()], this.translate);
/*     */   }
/*     */   private void sweep() {
/* 157 */     ArrayList delete = new ArrayList();
/* 158 */     ArrayList comps = this.panel.getPushedComponents();
/* 159 */     for (int i = 0; i < comps.size(); i++) {
/* 160 */       if ((comps.get(i) instanceof StatPlotCreationDialog.PointPanel)) {
/* 161 */         StatPlotCreationDialog.PointPanel comp = (StatPlotCreationDialog.PointPanel)comps.get(i);
/* 162 */         if (comp.isSelected()) {
/* 163 */           delete.add(comp);
/*     */         }
/*     */       }
/*     */     }
/* 167 */     for (int i = 0; i < delete.size(); i++)
/* 168 */       this.panel.removeComponent((JComponent)delete.get(i));
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 173 */     if (e.getSource() == this.cancel) {
/* 174 */       setVisible(false);
/* 175 */     } else if (e.getSource() == this.add) {
/* 176 */       this.panel.pushComponent(new StatPlotCreationDialog.PointPanel());
/* 177 */     } else if (e.getSource() == this.delete) {
/* 178 */       sweep();
/* 179 */     } else if (e.getSource() == this.ok) {
/* 180 */       boolean upset = false;
/*     */       try {
/* 182 */         this.owner.setStatPlot(getPlot(), this.editNumber);
/*     */       }
/*     */       catch (Exception c) {
/* 185 */         upset = true;
/* 186 */         new ComplicationAlert(this, "One or more points cannot be parsed");
/* 187 */         c.printStackTrace();
/*     */       }
/*     */       finally {
/* 190 */         if (!upset)
/* 191 */           setVisible(false);
/*     */       }
/* 193 */     } else if (e.getSource() == this.colorButton) {
/* 194 */       this.colorChosen = JColorChooser.showDialog(this, "Choose a button color: ", this.colorChosen);
/* 195 */       updateOptions();
/*     */     }
/*     */   }
/*     */ 
/*     */   private Icon getButtonIcon() {
/* 200 */     BufferedImage bi = new BufferedImage(25, 25, 1);
/* 201 */     Graphics g = bi.getGraphics();
/* 202 */     g.setColor(this.colorChosen);
/* 203 */     g.fillRect(0, 0, 25, 25);
/* 204 */     return new ImageIcon(bi);
/*     */   }
/*     */   private Icon makeIconInstance(PointInvoker invoker, int spinneramt) {
/* 207 */     BufferedImage bi = new BufferedImage(25, 25, 1);
/* 208 */     Graphics g = bi.getGraphics();
/* 209 */     g.setColor(Color.white);
/* 210 */     g.fillRect(0, 0, 25, 25);
/* 211 */     invoker.drawPoint(g, 13, 13);
/* 212 */     return new ImageIcon(bi);
/*     */   }
/*     */   private void hideArrow() {
/* 215 */     Component[] c = this.combobox.getComponents();
/* 216 */     for (Component x : c)
/* 217 */       if ((x instanceof AbstractButton))
/* 218 */         ((AbstractButton)x).setVisible(false);
/*     */   }
/*     */ 
/*     */   private void updateOptions()
/*     */   {
/* 223 */     this.colorButton.setIcon(getButtonIcon());
/* 224 */     int selected = this.combobox.getSelectedIndex();
/* 225 */     int spinneramt = ((Integer)this.spinner.getValue()).intValue();
/* 226 */     this.combobox.removeAllItems();
/*     */ 
/* 228 */     for (ExtendedPointInvoker x : this.invokers) {
/* 229 */       x.setColor(this.colorChosen);
/* 230 */       x.setSize(spinneramt);
/*     */     }
/* 232 */     for (int i = 0; i < this.invokers.length; i++) {
/* 233 */       this.combobox.addItem(makeIconInstance(this.invokers[i], spinneramt));
/*     */     }
/* 235 */     this.combobox.setSelectedIndex(selected);
/* 236 */     this.combobox.updateUI();
/* 237 */     this.spinner.updateUI();
/* 238 */     this.colorButton.updateUI();
/*     */   }
/*     */ 
/*     */   public void stateChanged(ChangeEvent e)
/*     */   {
/* 243 */     if (e.getSource() == this.spinner) updateOptions();
/*     */   }
/*     */ 
/*     */   private class PointPanel extends JPanel
/*     */   {
/*     */     private JTextField xVal;
/*     */     private JTextField yVal;
/*     */     private JCheckBox box;
/*     */ 
/*     */     public PointPanel()
/*     */     {
/* 125 */       this.box = new JCheckBox();
/* 126 */       this.xVal = new JTextField("0.0", 4);
/* 127 */       this.yVal = new JTextField("0.0", 4);
/* 128 */       setLayout(new FlowLayout());
/* 129 */       add(this.box);
/* 130 */       add(new JLabel("("));
/* 131 */       add(this.xVal);
/* 132 */       add(new JLabel(","));
/* 133 */       add(this.yVal);
/* 134 */       add(new JLabel("),"));
/*     */     }
/*     */ 
/*     */     public Point2D getPoint() {
/* 138 */       return new Point2D(Double.parseDouble(this.xVal.getText()), Double.parseDouble(this.yVal.getText()));
/*     */     }
/*     */     public boolean isSelected() {
/* 141 */       return this.box.isSelected();
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     StatPlotCreationDialog
 * JD-Core Version:    0.6.2
 */