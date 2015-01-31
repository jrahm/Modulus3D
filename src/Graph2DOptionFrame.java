/*    */ import java.awt.Dimension;
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class Graph2DOptionFrame extends JDialog
/*    */   implements ActionListener
/*    */ {
/*    */   private static final long serialVersionUID = 1255261492376898359L;
/*    */   private JPanel panel;
/*    */   private JPanel buttonHolder;
/*    */   private JComboBox comboBox;
/*    */   private Point2DMaker[] link;
/*    */   private String[] keys;
/*    */   private JButton ok;
/*    */   private JButton cancel;
/*    */ 
/*    */   public Graph2DOptionFrame(JFrame owner)
/*    */   {
/* 28 */     super(owner, true);
/* 29 */     this.panel = new JPanel();
/* 30 */     this.panel.setLayout(new BoxLayout(this.panel, 1));
/* 31 */     this.buttonHolder = new JPanel(new FlowLayout());
/* 32 */     this.keys = new String[] { 
/* 33 */       "Euclidean", 
/* 34 */       "Polar", 
/* 35 */       "X Equals" };
/*    */ 
/* 37 */     this.link = new Point2DMaker[] { 
/* 38 */       new Euclidean2DPointMaker(), 
/* 39 */       new Polar2DPointMaker(), 
/* 40 */       new ReverseEuclidean2DPointMaker() };
/*    */ 
/* 43 */     this.comboBox = new JComboBox(this.keys);
/* 44 */     this.ok = new JButton("Apply");
/* 45 */     this.cancel = new JButton("Cancel");
/*    */ 
/* 47 */     this.ok.addActionListener(this);
/* 48 */     this.cancel.addActionListener(this);
/*    */ 
/* 50 */     this.buttonHolder.add(this.ok);
/* 51 */     this.buttonHolder.add(this.cancel);
/*    */ 
/* 53 */     this.panel.add(this.comboBox);
/* 54 */     this.panel.add(this.buttonHolder);
/* 55 */     this.panel.setPreferredSize(new Dimension(200, 50));
/* 56 */     add(this.panel);
/* 57 */     pack();
/*    */   }
/*    */ 
/*    */   public void actionPerformed(ActionEvent arg0)
/*    */   {
/* 62 */     setVisible(false);
/* 63 */     if (arg0.getSource() == this.ok)
/* 64 */       GraphTypeHolder.getInstance().setGraphPointMaker(this.link[this.comboBox.getSelectedIndex()]);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Graph2DOptionFrame
 * JD-Core Version:    0.6.2
 */