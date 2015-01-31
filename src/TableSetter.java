/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class TableSetter extends JFrame
/*    */   implements ActionListener
/*    */ {
/*    */   private JButton OK;
/*    */   private JButton cancel;
/*    */   private ActionListener flg;
/*    */   private JPanel main;
/* 19 */   private JTextField[] fields = { 
/* 20 */     new JTextField(5), 
/* 21 */     new JTextField(5) };
/*    */ 
/* 23 */   private JLabel[] lbls = { 
/* 24 */     new JLabel("TblStart"), 
/* 25 */     new JLabel("TblStep") };
/*    */ 
/*    */   public TableSetter(ActionListener caller, String[] originals)
/*    */   {
/* 28 */     this.flg = caller;
/* 29 */     this.OK = new JButton("Set");
/* 30 */     this.cancel = new JButton("Cancel");
/* 31 */     for (int i = 0; (i < originals.length) && (i < this.fields.length); i++) {
/* 32 */       this.fields[i].setText(originals[i]);
/*    */     }
/* 34 */     this.OK.addActionListener(this);
/* 35 */     this.cancel.addActionListener(this);
/* 36 */     this.main = new JPanel();
/* 37 */     this.main.setLayout(new BoxLayout(this.main, 1));
/*    */ 
/* 39 */     for (int i = 0; i < this.fields.length; i++) {
/* 40 */       JPanel temp = new JPanel(new FlowLayout());
/* 41 */       temp.add(this.lbls[i]);
/* 42 */       temp.add(this.fields[i]);
/* 43 */       this.main.add(temp);
/*    */     }
/*    */ 
/* 46 */     JPanel temp = new JPanel(new FlowLayout());
/* 47 */     temp.add(this.OK);
/* 48 */     temp.add(this.cancel);
/* 49 */     this.main.add(temp);
/* 50 */     add(this.main);
/* 51 */     setIconImage(Toolkit.getDefaultToolkit().getImage("modulus_symbol.png"));
/* 52 */     pack();
/*    */   }
/*    */ 
/*    */   public String[] getText() {
/* 56 */     return new String[] { 
/* 57 */       this.fields[0].getText(), 
/* 58 */       this.fields[1].getText() };
/*    */   }
/*    */ 
/*    */   public void actionPerformed(ActionEvent e) {
/* 62 */     if (e.getSource() == this.OK) {
/* 63 */       e.setSource(this);
/* 64 */       this.flg.actionPerformed(e);
/*    */     }
/* 66 */     setVisible(false);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     TableSetter
 * JD-Core Version:    0.6.2
 */