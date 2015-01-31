/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JViewport;
/*     */ 
/*     */ public class EquationDialog extends JDialog
/*     */   implements GraphTypeStateChangedListener, ActionListener
/*     */ {
/*     */   private JScrollPane scroll;
/*     */   private JTextField[] equations;
/*     */   private JLabel[] equationNames;
/*     */   private JPanel[] packages;
/*     */   private JPanel holder;
/*     */   private JPanel fullPanel;
/*     */   private JButton ok;
/*     */   private JButton cancel;
/*     */   private JPanel okButtons;
/*     */   private Graph2D graph;
/*     */ 
/*     */   public EquationDialog(Frame parent, Graph2D graph)
/*     */   {
/*  25 */     super(parent, true);
/*  26 */     this.graph = graph;
/*  27 */     setLayout(new GridBagLayout());
/*  28 */     this.ok = new JButton("Apply");
/*  29 */     this.cancel = new JButton("Cancel");
/*  30 */     this.okButtons = new JPanel(new FlowLayout());
/*  31 */     this.okButtons.add(this.ok);
/*  32 */     this.okButtons.add(this.cancel);
/*  33 */     this.ok.addActionListener(this);
/*  34 */     this.cancel.addActionListener(this);
/*  35 */     this.holder = new JPanel();
/*     */ 
/*  37 */     this.equations = new JTextField[10];
/*  38 */     this.equationNames = new JLabel[10];
/*  39 */     this.scroll = new JScrollPane();
/*  40 */     this.holder.setLayout(new GridLayout(this.equations.length, 1));
/*  41 */     this.scroll.setPreferredSize(new Dimension(200, 200));
/*  42 */     this.packages = new JPanel[10];
/*  43 */     for (int i = 0; i < this.equations.length; i++) {
/*  44 */       this.equations[i] = new JTextField("", 10);
/*  45 */       this.equations[i].setFocusable(true);
/*  46 */       this.equationNames[i] = new JLabel("<html><a style='font-family:Times New Roman'>y<sub>" + i + "</sub>(x)</a>=<html>");
/*  47 */       this.packages[i] = new JPanel(new FlowLayout());
/*  48 */       this.packages[i].add(this.equationNames[i]);
/*  49 */       this.packages[i].add(this.equations[i]);
/*  50 */       this.packages[i].setPreferredSize(new Dimension(150, 65));
/*  51 */       this.holder.add(this.packages[i]);
/*     */     }
/*  53 */     this.scroll.getViewport().add(this.holder);
/*  54 */     this.fullPanel = new JPanel();
/*  55 */     this.fullPanel.setLayout(new BoxLayout(this.fullPanel, 1));
/*  56 */     this.fullPanel.add(this.scroll);
/*  57 */     this.fullPanel.add(this.okButtons);
/*  58 */     add(this.fullPanel);
/*     */ 
/*  60 */     pack();
/*     */   }
/*     */ 
/*     */   public void setEquations(String[] args) {
/*  64 */     for (int i = 0; (i < args.length) && (i < this.equations.length); i++)
/*  65 */       this.equations[i].setText(args[i].replaceAll("<ivar>", GraphTypeHolder.getInstance().getGraphPointMaker().getIndependentVariable()));
/*     */   }
/*     */ 
/*     */   public void setVisible(boolean visible) {
/*  69 */     if (visible) {
/*  70 */       setEquations(this.graph.getEquations());
/*     */     }
/*  72 */     super.setVisible(visible);
/*     */   }
/*     */   public void applyEquations() {
/*  75 */     this.graph.setEquations(getEquations());
/*  76 */     ModulusThreads.addThread("Remaking Points", this.graph.getThread());
/*     */   }
/*     */   public String[] getEquations() {
/*  79 */     ArrayList strs = new ArrayList();
/*  80 */     for (JTextField jtf : this.equations) {
/*  81 */       if (jtf.getText().length() != 0)
/*  82 */         strs.add(parseEquation(jtf.getText()));
/*     */     }
/*  84 */     String[] ret = new String[strs.size()];
/*  85 */     for (int i = 0; i < strs.size(); i++) ret[i] = ((String)strs.get(i));
/*  86 */     return ret;
/*     */   }
/*     */   public static String parseEquation(String equation) {
/*  89 */     if (equation.equals("")) return "";
/*  90 */     if (equation.length() == 1) return equation.charAt(0) == GraphTypeHolder.getInstance().getGraphPointMaker().getIndependentVariable() ? "<ivar>" : equation;
/*  91 */     Point2DMaker maker = GraphTypeHolder.getInstance().getGraphPointMaker();
/*  92 */     String ret = "";
/*  93 */     for (int i = 0; i < equation.length(); i++) {
/*  94 */       char next = equation.charAt(i);
/*  95 */       if (next == maker.getIndependentVariable()) {
/*  96 */         if (i == 0) {
/*  97 */           if (!Character.isLowerCase(equation.charAt(i + 1))) {
/*  98 */             ret = ret + "<ivar>";
/*  99 */             continue;
/*     */           }
/*     */         }
/* 102 */         else if (i == equation.length() - 1) {
/* 103 */           if (!Character.isLowerCase(equation.charAt(i - 1))) {
/* 104 */             ret = ret + "<ivar>";
/* 105 */             continue;
/*     */           }
/*     */         }
/* 108 */         else if ((!Character.isLowerCase(equation.charAt(i - 1))) && (!Character.isLowerCase(equation.charAt(i + 1)))) {
/* 109 */           ret = ret + "<ivar>";
/* 110 */           continue;
/*     */         }
/*     */       }
/* 113 */       ret = ret + equation.charAt(i);
/*     */     }
/* 115 */     return ret;
/*     */   }
/*     */   public void graphTypeChanged(Point2DMaker maker) {
/* 118 */     for (int i = 0; i < this.equationNames.length; i++)
/* 119 */       this.equationNames[i].setText("<html><a style='font-family:Times New Roman'>" + maker.getDependentVariable() + "<sub>" + i + "</sub>(" + GraphTypeHolder.getInstance().getGraphPointMaker().getIndependentVariable() + ")</a> =<html>");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 124 */     setVisible(false);
/* 125 */     if (e.getSource() == this.ok)
/* 126 */       applyEquations();
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     EquationDialog
 * JD-Core Version:    0.6.2
 */