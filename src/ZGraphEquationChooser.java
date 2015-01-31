/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class ZGraphEquationChooser extends JFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private JButton OK;
/*     */   private JButton cancel;
/*     */   private Flagger flg;
/*     */   private JPanel main;
/*  25 */   private char replaceX = 'X';
/*  26 */   private char replaceY = 'Y';
/*  27 */   private JTextField[] fields = { 
/*  28 */     new JTextField(25), 
/*  29 */     new JTextField(25), 
/*  30 */     new JTextField(25), 
/*  31 */     new JTextField(25), 
/*  32 */     new JTextField(25) };
/*     */ 
/*  34 */   private JLabel[] lbls = { 
/*  35 */     new JLabel("z1 = "), 
/*  36 */     new JLabel("z2 = "), 
/*  37 */     new JLabel("z3 = "), 
/*  38 */     new JLabel("z4 = "), 
/*  39 */     new JLabel("z5 = ") };
/*     */ 
/*     */   public ZGraphEquationChooser(Flagger caller, String[] originals)
/*     */   {
/*  42 */     this.flg = caller;
/*  43 */     this.OK = new JButton("Set");
/*  44 */     this.cancel = new JButton("Cancel");
/*  45 */     for (JTextField i : this.fields) {
/*  46 */       i.setFont(new Font("Times New Roman", 2, 12));
/*     */     }
/*  48 */     for (int i = 0; (i < originals.length) && (i < this.fields.length); i++)
/*     */     {
/*  50 */       this.fields[i].setText(originals[i]);
/*     */     }
/*  52 */     this.OK.addActionListener(this);
/*  53 */     this.cancel.addActionListener(this);
/*     */ 
/*  55 */     this.main = new JPanel();
/*  56 */     this.main.setLayout(new BoxLayout(this.main, 1));
/*     */ 
/*  58 */     for (int i = 0; i < this.fields.length; i++) {
/*  59 */       JPanel temp = new JPanel(new FlowLayout());
/*  60 */       temp.add(this.lbls[i]);
/*  61 */       temp.add(this.fields[i]);
/*  62 */       this.main.add(temp);
/*     */     }
/*     */ 
/*  65 */     JPanel temp = new JPanel(new FlowLayout());
/*  66 */     temp.add(this.OK);
/*  67 */     temp.add(this.cancel);
/*  68 */     this.main.add(temp);
/*  69 */     add(this.main);
/*  70 */     pack();
/*  71 */     setIconImage(Toolkit.getDefaultToolkit().getImage("modulus_symbol.png"));
/*     */   }
/*     */ 
/*     */   public String[] getEqs() {
/*  75 */     return new String[] { 
/*  76 */       this.fields[0].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y"), 
/*  77 */       this.fields[1].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y"), 
/*  78 */       this.fields[2].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y"), 
/*  79 */       this.fields[3].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y"), 
/*  80 */       this.fields[4].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y") };
/*     */   }
/*     */ 
/*     */   public boolean isFocusable() {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/*  88 */     if (e.getSource() == this.OK) {
/*  89 */       ControlPanel.setGraphFunctions(
/*  90 */         new String[] { 
/*  91 */         this.fields[0].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y"), 
/*  92 */         this.fields[1].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y"), 
/*  93 */         this.fields[2].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y"), 
/*  94 */         this.fields[3].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y"), 
/*  95 */         this.fields[4].getText().replaceAll(this.replaceX, "x").replaceAll(this.replaceY, "y") });
/*     */ 
/*  98 */       this.flg.flag2(getEqs());
/*     */     }
/* 100 */     setVisible(false);
/*     */   }
/*     */ 
/*     */   public void setXReplace(char xReplace)
/*     */   {
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     ZGraphEquationChooser
 * JD-Core Version:    0.6.2
 */