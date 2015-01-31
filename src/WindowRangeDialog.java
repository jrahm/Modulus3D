/*     */ import GUIComponents.WrapperPanel;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ public class WindowRangeDialog extends JDialog
/*     */   implements ActionListener, ChangeListener
/*     */ {
/*     */   private Graph2D graph;
/*     */   private WindowRangeDialog.TextFieldBundle xMin;
/*     */   private WindowRangeDialog.TextFieldBundle xMax;
/*     */   private WindowRangeDialog.TextFieldBundle yMin;
/*     */   private WindowRangeDialog.TextFieldBundle yMax;
/*     */   private WindowRangeDialog.TextFieldBundle tMin;
/*     */   private WindowRangeDialog.TextFieldBundle tMax;
/*     */   private JSpinner xStep;
/*     */   private JSpinner tStep;
/*     */   private JButton ok;
/*     */   private JButton cancel;
/*     */ 
/*     */   public WindowRangeDialog(Frame owner, Graph2D toSet)
/*     */   {
/*  27 */     super(owner, true);
/*  28 */     this.graph = toSet;
/*  29 */     WindowRange range = toSet.getWindowRange();
/*     */ 
/*  31 */     this.xMin = new WindowRangeDialog.TextFieldBundle("X-Min", new JTextField(range.getXMin(), 5));
/*  32 */     this.xMax = new WindowRangeDialog.TextFieldBundle("X-Max", new JTextField(range.getXMax(), 5));
/*  33 */     this.yMin = new WindowRangeDialog.TextFieldBundle("Y-Min", new JTextField(range.getYMin(), 5));
/*  34 */     this.yMax = new WindowRangeDialog.TextFieldBundle("Y-Max", new JTextField(range.getYMax(), 5));
/*  35 */     this.tMin = new WindowRangeDialog.TextFieldBundle("θ-Min", new JTextField(this.graph.getTStart(), 5));
/*  36 */     this.tMax = new WindowRangeDialog.TextFieldBundle("θ-Max", new JTextField(this.graph.getTEnd(), 5));
/*     */ 
/*  38 */     this.xStep = new JSpinner();
/*  39 */     this.tStep = new JSpinner();
/*     */ 
/*  41 */     this.xStep.setValue(Integer.valueOf(this.graph.getXSkip()));
/*  42 */     this.tStep.setValue(Double.valueOf(this.graph.getTStep()));
/*     */ 
/*  44 */     this.ok = new JButton("Apply");
/*  45 */     this.cancel = new JButton("Cancel");
/*  46 */     this.ok.addActionListener(this);
/*  47 */     this.cancel.addActionListener(this);
/*  48 */     this.xStep.addChangeListener(this);
/*  49 */     this.tStep.addChangeListener(this);
/*  50 */     JPanel main = new JPanel();
/*  51 */     JPanel buttons = new JPanel(new FlowLayout());
/*  52 */     main.setLayout(new GridLayout(3, 3));
/*  53 */     main.add(this.xMax);
/*  54 */     main.add(this.yMax);
/*  55 */     main.add(this.tMax);
/*  56 */     main.add(this.xMin);
/*  57 */     main.add(this.yMin);
/*  58 */     main.add(this.tMin);
/*  59 */     main.add(new WrapperPanel(new Component[] { new JLabel("X-Res"), this.xStep }));
/*  60 */     main.add(new JPanel());
/*  61 */     main.add(new WrapperPanel(new Component[] { new JLabel("θ-Step"), this.tStep }));
/*  62 */     buttons.add(this.ok);
/*  63 */     buttons.add(this.cancel);
/*  64 */     setLayout(new BorderLayout());
/*  65 */     add(main, "Center");
/*  66 */     add(buttons, "South");
/*  67 */     pack();
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*  94 */     if ((e.getSource() == this.ok) || (e.getSource() == this.cancel)) {
/*  95 */       boolean upset = false;
/*  96 */       if (e.getSource() == this.ok) {
/*     */         try {
/*  98 */           applySettings();
/*     */         } catch (Exception c) {
/* 100 */           c.printStackTrace();
/* 101 */           upset = true;
/* 102 */           new ComplicationAlert(this, "One or more of the option fields could not be parsed!");
/*     */         }
/*     */       }
/* 105 */       if (!upset)
/* 106 */         setVisible(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void stateChanged(ChangeEvent e)
/*     */   {
/* 112 */     if ((e.getSource() instanceof JSpinner)) {
/* 113 */       JSpinner src = (JSpinner)e.getSource();
/* 114 */       if (((Integer)src.getValue()).intValue() <= 0) {
/* 115 */         src.setValue(Integer.valueOf(1));
/*     */       }
/* 117 */       src.updateUI();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void applySettings() throws NumberFormatException, Exception
/*     */   {
/* 123 */     WindowRange newWinRange = new WindowRange(
/* 124 */       Double.parseDouble(ControlPanel.figure(this.xMin.getFieldText())), 
/* 125 */       Double.parseDouble(ControlPanel.figure(this.yMin.getFieldText())), 
/* 126 */       Double.parseDouble(ControlPanel.figure(this.xMax.getFieldText())), 
/* 127 */       Double.parseDouble(ControlPanel.figure(this.yMax.getFieldText())));
/*     */ 
/* 129 */     double tMinNum = Double.parseDouble(ControlPanel.figure(this.tMin.getFieldText()));
/* 130 */     double tMaxNum = Double.parseDouble(ControlPanel.figure(this.tMax.getFieldText()));
/*     */ 
/* 132 */     this.graph.setWindowRange(newWinRange);
/* 133 */     this.graph.setTStart(tMinNum);
/* 134 */     this.graph.setTEnd(tMaxNum);
/* 135 */     this.graph.setXSkip(((Integer)this.xStep.getValue()).intValue());
/* 136 */     if ((this.tStep.getValue() instanceof Double))
/* 137 */       this.graph.setTStep(((Double)this.tStep.getValue()).doubleValue());
/* 138 */     if ((this.tStep.getValue() instanceof Integer))
/* 139 */       this.graph.setTStep(((Integer)this.tStep.getValue()).intValue());
/*     */   }
/*     */ 
/*     */   private class TextFieldBundle extends JPanel
/*     */   {
/*     */     private JTextField field;
/*     */     private JLabel text;
/*     */ 
/*     */     public TextFieldBundle(String text, JTextField field)
/*     */     {
/*  73 */       super();
/*  74 */       this.text = new JLabel(text);
/*  75 */       this.field = field;
/*  76 */       add(this.text);
/*  77 */       add(this.field);
/*     */     }
/*     */     public String getLabelText() {
/*  80 */       return this.text.getText();
/*     */     }
/*     */     public void setLabelText(String text) {
/*  83 */       this.text.setText(text);
/*     */     }
/*     */     public void setFieldText(String text) {
/*  86 */       this.field.setText(text);
/*     */     }
/*     */     public String getFieldText() {
/*  89 */       return this.field.getText();
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     WindowRangeDialog
 * JD-Core Version:    0.6.2
 */