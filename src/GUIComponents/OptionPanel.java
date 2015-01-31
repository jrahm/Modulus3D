/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GridLayout;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class OptionPanel extends JPanel
/*    */ {
/*    */   private JOptionPanel[] outerPanels;
/*    */ 
/*    */   public OptionPanel(String[] titles, JPanel[] panels)
/*    */   {
/* 21 */     this.outerPanels = new JOptionPanel[(int)Math.ceil(titles.length / 3.0D)];
/* 22 */     setLayout(new GridLayout(1, this.outerPanels.length));
/* 23 */     int i = 0; for (int count = 0; i < this.outerPanels.length; i++) {
/* 24 */       count += 3;
/* 25 */       int max = titles.length < count ? titles.length : count;
/* 26 */       if (panels == null)
/* 27 */         this.outerPanels[i] = new JOptionPanel(split(titles, count - 3, max));
/*    */       else
/* 29 */         this.outerPanels[i] = new JOptionPanel(split(titles, count - 3, max), split(panels, count - 3, max));
/* 30 */       this.outerPanels[i].setPreferredSize(new Dimension(getWidth() / this.outerPanels.length, getHeight()));
/*    */       String layout;
/*    */       String layout;
/* 32 */       if (i == 0) { layout = "Center"; }
/*    */       else
/*    */       {
/* 33 */         String layout;
/* 33 */         if (i == 1) layout = "West"; else
/* 34 */           layout = "East";
/*    */       }
/* 36 */       add(this.outerPanels[i], i);
/*    */     }
/*    */   }
/*    */ 
/*    */   public JPanel getPanel(int index) {
/* 41 */     return this.outerPanels[index];
/*    */   }
/*    */   private String[] split(String[] other, int min, int max) {
/* 44 */     String[] ret = new String[max - min];
/* 45 */     for (int i = min; i < max; i++) {
/* 46 */       ret[(i - min)] = other[i];
/*    */     }
/* 48 */     return ret;
/*    */   }
/*    */   private JPanel[] split(JPanel[] other, int min, int max) {
/* 51 */     JPanel[] ret = new JPanel[max - min];
/* 52 */     for (int i = min; i < max; i++) {
/* 53 */       ret[(i - min)] = other[i];
/*    */     }
/* 55 */     return ret;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.OptionPanel
 * JD-Core Version:    0.6.2
 */