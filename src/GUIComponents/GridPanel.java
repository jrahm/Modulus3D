/*    */ package GUIComponents;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.GridLayout;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class GridPanel extends JPanel
/*    */ {
/*    */   private JPanel[][] panels;
/*    */ 
/*    */   public GridPanel(int width, int height, int spacingx, int spacingy)
/*    */   {
/* 18 */     this.panels = new JPanel[width][height];
/* 19 */     setLayout(new GridLayout(height, width));
/* 20 */     int i = 0; for (int a = 0; i < this.panels.length; a++) {
/* 21 */       for (int j = 0; j < this.panels[0].length; a++)
/*    */       {
/* 23 */         add(this.panels[i][j] =  = new JPanel(new FlowLayout()));
/*    */ 
/* 21 */         j++;
/*    */       }
/* 20 */       i++;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void add(Component component, int col, int row)
/*    */   {
/* 30 */     this.panels[col][row].add(component);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     GUIComponents.GridPanel
 * JD-Core Version:    0.6.2
 */