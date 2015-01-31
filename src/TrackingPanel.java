/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.GridLayout;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ import java.text.DecimalFormat;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class TrackingPanel extends JPanel
/*    */   implements MouseMotionListener
/*    */ {
/*    */   private JTextField[] fields;
/*    */   private DecimalFormat format;
/*    */   private JLabel[] labels;
/*    */   private JPanel[] panels;
/*    */   private Point2D mousePoint;
/*    */   private GraphTranslator translator;
/*    */   private AnswerPanel slave;
/*    */ 
/*    */   public TrackingPanel(GraphTranslator translator, AnswerPanel slave)
/*    */   {
/* 22 */     this.fields = new JTextField[4];
/* 23 */     this.panels = new JPanel[5];
/* 24 */     this.labels = new JLabel[] { 
/* 25 */       new JLabel("x = "), 
/* 26 */       new JLabel("y = "), 
/* 27 */       new JLabel("θ = "), 
/* 28 */       new JLabel("r = ") };
/*    */ 
/* 30 */     setLayout(new GridLayout(1, 5));
/* 31 */     for (int i = 0; i < this.fields.length; i++) {
/* 32 */       this.panels[i] = new JPanel(new FlowLayout());
/* 33 */       this.fields[i] = new JTextField("", 5);
/* 34 */       this.fields[i].setEditable(false);
/* 35 */       this.panels[i].add(this.labels[i]);
/* 36 */       this.panels[i].add(this.fields[i]);
/* 37 */       add(this.panels[i]);
/*    */     }
/* 39 */     this.translator = translator;
/* 40 */     this.format = new DecimalFormat(".00");
/* 41 */     this.slave = slave;
/*    */   }
/*    */   public void mouseDragged(MouseEvent e) {
/*    */   }
/* 45 */   public void mouseMoved(MouseEvent e) { this.mousePoint = this.translator.translateInv(new Point2D(e.getPoint()));
/* 46 */     this.fields[0].setText(this.format.format(this.mousePoint.getRealX()));
/* 47 */     this.fields[1].setText(this.format.format(this.mousePoint.getRealY()));
/* 48 */     double angle = Math.toDegrees(Math.atan2(this.mousePoint.getRealX(), this.mousePoint.getRealY()));
/* 49 */     this.fields[2].setText(this.format.format(360.0D - (angle - 90.0D + 360.0D) % 360.0D));
/* 50 */     this.fields[3].setText(this.format.format(Math.sqrt(this.mousePoint.getRealX() * this.mousePoint.getRealX() + this.mousePoint.getRealY() * this.mousePoint.getRealY())));
/* 51 */     this.slave.load(this.mousePoint.getRealX(), this.mousePoint.getRealY(), 360.0D - (angle - 90.0D + 360.0D) % 360.0D);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     TrackingPanel
 * JD-Core Version:    0.6.2
 */