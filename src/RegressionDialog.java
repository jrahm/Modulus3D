/*    */ import GUIComponents.PushablePanel;
/*    */ import GUIComponents.ToggleButtonGroup;
/*    */ import GUIComponents.WrapperPanel;
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Frame;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JToggleButton;
/*    */ 
/*    */ public abstract class RegressionDialog extends JDialog
/*    */   implements ActionListener
/*    */ {
/*    */   private StatPlotDialog dialog;
/*    */   private PushablePanel panel;
/* 23 */   private ToggleButtonGroup buttonGroup = new ToggleButtonGroup();
/* 24 */   private ArrayList<ActionListener> listeners = new ArrayList();
/*    */   private JButton ok;
/*    */   private JButton cancel;
/*    */ 
/*    */   public RegressionDialog(Frame owner, String title, StatPlotDialog dialog)
/*    */   {
/* 27 */     super(owner, title, true);
/* 28 */     JPanel main = new JPanel();
/* 29 */     main.setLayout(new BoxLayout(main, 1));
/* 30 */     this.dialog = dialog;
/* 31 */     this.panel = new PushablePanel();
/* 32 */     for (int i = 0; i < dialog.getPlots().size(); i++) {
/* 33 */       JToggleButton button = new JToggleButton("Statplot #" + (i + 1));
/* 34 */       button.setBackground(Color.white);
/* 35 */       this.buttonGroup.addToggleButton(button);
/* 36 */       this.panel.pushComponent(button);
/*    */     }
/* 38 */     this.panel.setPreferredSize(new Dimension(150, 250));
/* 39 */     this.ok = new JButton("Calculate");
/* 40 */     this.cancel = new JButton("Cancel");
/* 41 */     this.ok.addActionListener(this);
/* 42 */     this.cancel.addActionListener(this);
/* 43 */     main.add(this.panel);
/* 44 */     main.add(new WrapperPanel(new JComponent[] { this.ok, this.cancel }));
/* 45 */     add(main);
/* 46 */     pack();
/*    */   }
/*    */   public void addActionListener(ActionListener listen) {
/* 49 */     this.listeners.add(listen);
/*    */   }
/*    */ 
/*    */   public void actionPerformed(ActionEvent e) {
/* 53 */     if (e.getSource() == this.ok) {
/* 54 */       e.setSource(this);
/* 55 */       for (int i = 0; i < this.listeners.size(); i++) {
/* 56 */         ((ActionListener)this.listeners.get(i)).actionPerformed(e);
/*    */       }
/* 58 */       calculateRegression();
/*    */     }
/* 60 */     setVisible(false);
/*    */   }
/*    */   public ToggleButtonGroup getToggleButtonGroup() {
/* 63 */     return this.buttonGroup;
/*    */   }
/*    */   public void setVisible(boolean b) {
/* 66 */     if (b) {
/* 67 */       this.panel.removeAllComponents();
/* 68 */       this.buttonGroup = new ToggleButtonGroup();
/* 69 */       for (int i = 0; i < this.dialog.getPlots().size(); i++) {
/* 70 */         JToggleButton button = new JToggleButton("Statplot #" + (i + 1));
/* 71 */         button.setBackground(Color.white);
/* 72 */         this.buttonGroup.addToggleButton(button);
/* 73 */         this.panel.pushComponent(button);
/*    */       }
/* 75 */       super.setVisible(b);
/*    */     }
/*    */     else {
/* 78 */       super.setVisible(b);
/*    */     }
/*    */   }
/*    */ 
/* 82 */   public StatPlotDialog getStatPlotDialog() { return this.dialog; }
/*    */ 
/*    */   public StatPlot getSelectedStatPlot() {
/* 85 */     return (StatPlot)this.dialog.getPlots().get(this.buttonGroup.getSelectedIndex());
/*    */   }
/*    */ 
/*    */   public abstract double[] calculateRegression();
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     RegressionDialog
 * JD-Core Version:    0.6.2
 */