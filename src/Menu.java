/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JMenu;
/*    */ 
/*    */ public class Menu extends JMenu
/*    */   implements ActionListener
/*    */ {
/*    */   private JComponent[] comps;
/*    */ 
/*    */   public Menu(String name, JComponent[] args)
/*    */   {
/* 14 */     super(name);
/* 15 */     this.comps = args;
/* 16 */     for (int i = 0; i < args.length; i++) {
/* 17 */       add(args[i]);
/* 18 */       if ((args[i] instanceof AbstractButton))
/* 19 */         ((AbstractButton)args[i]).addActionListener(this); 
/*    */     }
/*    */   }
/*    */ 
/* 23 */   public JComponent get(int x) { return this.comps[x]; } 
/*    */   public void actionPerformed(ActionEvent e) {
/* 25 */     for (int i = 0; i < getActionListeners().length; i++) getActionListeners()[i].actionPerformed(e);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     Menu
 * JD-Core Version:    0.6.2
 */