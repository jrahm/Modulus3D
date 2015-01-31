/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.GridLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JToggleButton;
/*    */ 
/*    */ public class ActionList extends JPanel
/*    */   implements ActionListener
/*    */ {
/*    */   private JToggleButton[] buttons;
/*    */   private JScrollPane pane;
/*    */   private ArrayList<ActionListener> listeners;
/*    */   private JPanel mainPanel;
/*    */ 
/*    */   public ActionList(Object[] args)
/*    */   {
/* 22 */     this.listeners = new ArrayList();
/* 23 */     this.mainPanel = new JPanel(new GridLayout(args.length, 1, 0, 0));
/* 24 */     this.buttons = new JToggleButton[args.length];
/* 25 */     for (int i = 0; i < args.length; i++)
/*    */     {
/* 27 */       this.buttons[i] = new JToggleButton(args[i].toString());
/* 28 */       this.buttons[i].setBackground(Color.white);
/* 29 */       this.buttons[i].setFont(new Font("Courier New", 0, 10));
/* 30 */       this.buttons[i].setSize(70, 20);
/* 31 */       this.mainPanel.add(this.buttons[i]);
/* 32 */       this.buttons[i].addActionListener(this);
/*    */     }
/* 34 */     add(this.mainPanel);
/* 35 */     this.pane = new JScrollPane(this);
/*    */   }
/*    */   public ActionList(Object[] args, ActionListener listen) {
/* 38 */     this(args);
/* 39 */     addActionListener(listen);
/*    */   }
/*    */   public JToggleButton getSelected() {
/* 42 */     for (int i = 0; i < this.buttons.length; i++) {
/* 43 */       if (this.buttons[i].isSelected()) return this.buttons[i];
/*    */     }
/* 45 */     return null;
/*    */   }
/*    */   public int getSelectedIndex() {
/* 48 */     for (int i = 0; i < this.buttons.length; i++) {
/* 49 */       if (this.buttons[i].isSelected()) return i;
/*    */     }
/* 51 */     return -1;
/*    */   }
/* 53 */   public void addActionListener(ActionListener x) { this.listeners.add(x); } 
/* 54 */   public JToggleButton get(int index) { return this.buttons[index]; } 
/*    */   public void actionPerformed(ActionEvent e) {
/* 56 */     for (int i = 0; i < this.buttons.length; i++) {
/* 57 */       if ((this.buttons[i].isSelected()) && (e.getSource() != this.buttons[i])) this.buttons[i].setSelected(false);
/*    */     }
/* 59 */     e.setSource(this);
/* 60 */     for (int i = 0; i < this.listeners.size(); i++) ((ActionListener)this.listeners.get(i)).actionPerformed(e);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     ActionList
 * JD-Core Version:    0.6.2
 */