/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GridLayout;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.border.TitledBorder;
/*    */ 
/*    */ public class JOptionPanel extends JPanel
/*    */ {
/*    */   private JPanel[] panels;
/*    */ 
/*    */   public JOptionPanel(String[] titles, JPanel[] p)
/*    */   {
/* 19 */     setLayout(new GridLayout(getCount(titles), 1));
/* 20 */     if (p != null) {
/* 21 */       this.panels = p;
/*    */     }
/*    */     else {
/* 24 */       this.panels = new JPanel[titles.length];
/* 25 */       for (int i = 0; i < this.panels.length; i++) {
/* 26 */         this.panels[i] = new JPanel(new BorderLayout());
/* 27 */         this.panels[i].setPreferredSize(new Dimension(getWidth(), getHeight() / titles.length));
/*    */       }
/*    */     }
/* 30 */     for (int i = 0; i < titles.length; i++)
/* 31 */       if (titles[i] != null) {
/* 32 */         if (!titles[i].equals("")) {
/* 33 */           TitledBorder titledBorder = BorderFactory.createTitledBorder(titles[i]);
/* 34 */           this.panels[i].setBorder(titledBorder);
/*    */ 
/* 36 */           if (i == 0) String str1 = "Center";
/* 37 */           String layout;
/* 37 */           if (i == 1) layout = "North"; else
/* 38 */             layout = "South";
/*    */         }
/* 40 */         add(this.panels[i], i);
/*    */       }
/*    */   }
/*    */ 
/*    */   public JOptionPanel(String[] titles) {
/* 45 */     this(titles, null);
/*    */   }
/*    */   public int getCount(Object[] obj) {
/* 48 */     int i = 0;
/* 49 */     for (Object x : obj) {
/* 50 */       if (x != null) i++;
/*    */     }
/* 52 */     return i;
/*    */   }
/*    */   public JPanel getPanel(int index) {
/* 55 */     return this.panels[index];
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.JOptionPanel
 * JD-Core Version:    0.6.2
 */