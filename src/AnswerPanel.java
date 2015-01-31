/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.GridLayout;
/*    */ import java.text.DecimalFormat;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class AnswerPanel extends JPanel
/*    */   implements GraphTypeStateChangedListener, EquationChangeListener
/*    */ {
/*    */   private JTextField[] fields;
/*    */   private JPanel[] panels;
/*    */   private JLabel[] labels;
/*    */   private String[] equations;
/*    */   private DecimalFormat format;
/*    */   private Graph2D graph;
/*    */ 
/*    */   public AnswerPanel(String[] equations, Graph2D graph)
/*    */   {
/* 19 */     this.equations = equations;
/*    */ 
/* 21 */     this.fields = new JTextField[equations.length];
/* 22 */     this.panels = new JPanel[equations.length];
/* 23 */     this.labels = new JLabel[equations.length];
/* 24 */     setLayout(new GridLayout(5, 1));
/* 25 */     for (int i = 0; i < this.fields.length; i++) {
/* 26 */       this.fields[i] = new JTextField("", 5);
/* 27 */       this.fields[i].setEditable(false);
/* 28 */       this.labels[i] = new JLabel("<html><a style='font-family:Times New Roman'>y<sub>" + i + "</sub>(" + GraphTypeHolder.getInstance().getGraphPointMaker().getIndependentVariable() + ")</a> =<html>");
/* 29 */       this.panels[i] = new JPanel(new FlowLayout());
/* 30 */       this.panels[i].add(this.labels[i]);
/* 31 */       this.panels[i].add(this.fields[i]);
/* 32 */       add(this.panels[i]);
/*    */     }
/*    */ 
/* 36 */     GraphTypeHolder.getInstance().addGraphTypeStateChangedListener(this);
/* 37 */     GraphTypeHolder.getInstance().fireGraphTypeStateChanged();
/* 38 */     this.format = new DecimalFormat(".00");
/* 39 */     this.graph = graph;
/*    */   }
/*    */   public void graphTypeChanged(Point2DMaker maker) {
/* 42 */     for (int i = 0; i < this.labels.length; i++)
/* 43 */       this.labels[i].setText("<html><a style='font-family:Times New Roman'>" + maker.getDependentVariable() + "<sub>" + i + "</sub>(" + GraphTypeHolder.getInstance().getGraphPointMaker().getIndependentVariable() + ")</a> =<html>");
/*    */   }
/*    */ 
/*    */   public void setEquations(String[] equations) {
/* 47 */     this.equations = equations;
/*    */   }
/*    */   public void load(double x, double y, double t) {
/* 50 */     Point2DMaker type = GraphTypeHolder.getInstance().getGraphPointMaker();
/* 51 */     double ivar = type.getIndependentVariable() == 'y' ? y : type.getIndependentVariable() == 't' ? t : type.getIndependentVariable() == 'x' ? x : 0.0D;
/* 52 */     for (int i = 0; (i < this.equations.length) && (i < this.fields.length); i++)
/* 53 */       if ((this.equations[i] != null) && (this.equations[i].length() > 0))
/* 54 */         this.fields[i].setText(this.format.format(this.graph.equation(ivar, this.equations[i])));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     AnswerPanel
 * JD-Core Version:    0.6.2
 */