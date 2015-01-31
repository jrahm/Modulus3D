/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JToggleButton;
/*    */ 
/*    */ public class ToggleButtonGroup
/*    */   implements ActionListener
/*    */ {
/*    */   private ArrayList<JToggleButton> toggleButtons;
/*    */ 
/*    */   public ToggleButtonGroup()
/*    */   {
/* 12 */     this.toggleButtons = new ArrayList();
/*    */   }
/*    */   public void addToggleButton(JToggleButton button) {
/* 15 */     this.toggleButtons.add(button);
/* 16 */     button.addActionListener(this);
/*    */   }
/*    */   public void removeToggleButton(JToggleButton button) {
/* 19 */     this.toggleButtons.remove(button);
/* 20 */     button.removeActionListener(this);
/*    */   }
/*    */   public JToggleButton getSelectedButton() {
/* 23 */     for (int i = 0; i < this.toggleButtons.size(); i++) if (((JToggleButton)this.toggleButtons.get(i)).isSelected()) return (JToggleButton)this.toggleButtons.get(i);
/* 24 */     return null;
/*    */   }
/*    */   public int getSelectedIndex() {
/* 27 */     for (int i = 0; i < this.toggleButtons.size(); i++) if (((JToggleButton)this.toggleButtons.get(i)).isSelected()) return i;
/* 28 */     return -1;
/*    */   }
/*    */   public void setSelectedIndex(int index) {
/* 31 */     clear();
/* 32 */     ((JToggleButton)this.toggleButtons.get(index)).setSelected(true);
/*    */   }
/*    */   private void clear() {
/* 35 */     for (int i = 0; i < this.toggleButtons.size(); i++) ((JToggleButton)this.toggleButtons.get(i)).setSelected(false); 
/*    */   }
/*    */ 
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 39 */     clear();
/* 40 */     ((JToggleButton)e.getSource()).setSelected(true);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.ToggleButtonGroup
 * JD-Core Version:    0.6.2
 */