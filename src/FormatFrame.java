/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JRadioButton;
/*    */ import utilities.DecimalFormatter;
/*    */ import utilities.EFormatter;
/*    */ import utilities.NoFormatter;
/*    */ import utilities.ScientificFormatter;
/*    */ 
/*    */ public class FormatFrame extends JDialog
/*    */   implements ActionListener
/*    */ {
/* 16 */   private JRadioButton[] buttons = { 
/* 17 */     new JRadioButton("No Format"), 
/* 18 */     new JRadioButton("Scientific Notation"), 
/* 19 */     new JRadioButton("E Notation") };
/*    */   private MButtonGroup group;
/*    */   private JCheckBox flt;
/*    */   private JButton ok;
/*    */   private JButton cancel;
/* 25 */   private JComboBox num = new JComboBox(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" });
/*    */   private JPanel mainPanel;
/*    */   private JPanel secondPanel;
/*    */   private JPanel buttonPanel;
/*    */   private JPanel bottomPanel;
/*    */ 
/*    */   public FormatFrame(JFrame parent, DecimalFormatter f)
/*    */   {
/* 31 */     super(parent, true);
/* 32 */     this.flt = new JCheckBox("Float");
/* 33 */     this.group = new MButtonGroup(this.buttons);
/* 34 */     if ((f instanceof NoFormatter)) this.buttons[0].setSelected(true);
/* 35 */     else if ((f instanceof EFormatter)) this.buttons[2].setSelected(true); else {
/* 36 */       this.buttons[1].setSelected(true);
/*    */     }
/*    */ 
/* 39 */     this.ok = new JButton("Set");
/* 40 */     this.cancel = new JButton("Cancel");
/*    */ 
/* 42 */     this.ok.addActionListener(this);
/* 43 */     this.cancel.addActionListener(this);
/*    */ 
/* 45 */     this.mainPanel = new JPanel(new BorderLayout());
/* 46 */     this.secondPanel = new JPanel(new FlowLayout());
/* 47 */     this.buttonPanel = new JPanel(new BorderLayout());
/* 48 */     this.bottomPanel = new JPanel(new FlowLayout());
/*    */ 
/* 50 */     this.secondPanel.add(new JLabel("Float To:"));
/* 51 */     this.secondPanel.add(this.num);
/* 52 */     this.secondPanel.add(this.flt);
/*    */ 
/* 55 */     this.buttonPanel.add(this.buttons[0], "North");
/* 56 */     this.buttonPanel.add(this.buttons[1], "Center");
/* 57 */     this.buttonPanel.add(this.buttons[2], "South");
/*    */ 
/* 59 */     this.bottomPanel.add(this.ok);
/* 60 */     this.bottomPanel.add(this.cancel);
/*    */ 
/* 62 */     this.mainPanel.add(this.buttonPanel, "West");
/* 63 */     this.mainPanel.add(this.secondPanel, "East");
/* 64 */     this.mainPanel.add(this.bottomPanel, "South");
/*    */ 
/* 66 */     add(this.mainPanel);
/* 67 */     pack();
/*    */   }
/*    */   public DecimalFormatter createInstance() {
/* 70 */     if (this.buttons[0].isSelected()) return new NoFormatter(0);
/* 71 */     if (this.buttons[1].isSelected()) return new ScientificFormatter(this.num.getSelectedIndex() + 1, Calculator.getBase(), getFloat());
/* 72 */     return new EFormatter(this.num.getSelectedIndex() + 1, Calculator.getBase(), getFloat());
/*    */   }
/*    */   public void actionPerformed(ActionEvent e) {
/* 75 */     if (e.getSource() == this.ok)
/* 76 */       CalculatorGUI.getCurrentInstance().setDecimalFormatter(createInstance());
/* 77 */     setVisible(false);
/*    */   }
/*    */   public boolean getFloat() {
/* 80 */     return this.flt.isSelected();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     FormatFrame
 * JD-Core Version:    0.6.2
 */