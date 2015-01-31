/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GridLayout;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JViewport;
/*    */ 
/*    */ public class PushablePanel extends JPanel
/*    */ {
/*    */   private JScrollPane scroll;
/*    */   private ArrayList<JComponent> panels;
/*    */   private JPanel holdingPanel;
/*    */ 
/*    */   public PushablePanel(int height, int width, ArrayList<JComponent> start)
/*    */   {
/* 24 */     setLayout(new GridLayout(1, 1));
/* 25 */     this.panels = start;
/* 26 */     this.scroll = new JScrollPane(22, 32);
/* 27 */     this.holdingPanel = new JPanel();
/* 28 */     this.holdingPanel.setLayout(new BoxLayout(this.holdingPanel, 1));
/*    */ 
/* 31 */     for (JComponent p : this.panels) {
/* 32 */       this.holdingPanel.add(p);
/*    */     }
/* 34 */     this.scroll.getViewport().add(this.holdingPanel);
/* 35 */     add(this.scroll);
/*    */   }
/*    */   public PushablePanel() {
/* 38 */     this(150, 50, new ArrayList());
/*    */   }
/*    */ 
/*    */   public void pushComponent(JComponent panel) {
/* 42 */     this.panels.add(panel);
/* 43 */     this.holdingPanel.add(panel);
/* 44 */     updateUI();
/*    */   }
/*    */   public void removeComponent(JComponent panel) {
/* 47 */     this.panels.remove(panel);
/* 48 */     this.holdingPanel.remove(panel);
/* 49 */     updateUI();
/*    */   }
/*    */   public ArrayList<JComponent> getPushedComponents() {
/* 52 */     return this.panels;
/*    */   }
/*    */   public void setPreferredSize(Dimension x) {
/* 55 */     super.setPreferredSize(x);
/* 56 */     this.scroll.setPreferredSize(x);
/*    */   }
/*    */   public void removeAllComponents() {
/* 59 */     while (this.panels.size() > 0)
/* 60 */       removeComponent((JComponent)this.panels.get(0));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.PushablePanel
 * JD-Core Version:    0.6.2
 */