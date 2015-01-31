/*    */ package StandardIO;
/*    */ 
/*    */ import javax.swing.JFileChooser;
/*    */ 
/*    */ public class ModulusFileChooser extends JFileChooser
/*    */ {
/*    */   Approvable onApprove;
/*    */ 
/*    */   public ModulusFileChooser(Approvable app, String currentDirectory, MFileFilter[] chooseableFilters)
/*    */   {
/* 16 */     super(currentDirectory);
/* 17 */     for (MFileFilter f : chooseableFilters) {
/* 18 */       super.addChoosableFileFilter(f);
/*    */     }
/* 20 */     this.onApprove = app;
/* 21 */     setFileFilter(chooseableFilters[0]);
/*    */   }
/*    */   public void promptOpen() {
/* 24 */     int returnVal = super.showOpenDialog(this);
/* 25 */     if (returnVal == 0) {
/* 26 */       this.onApprove.onApprove(getSelectedFile());
/*    */     }
/*    */     else
/* 29 */       this.onApprove.onCancel();
/*    */   }
/*    */ 
/*    */   public void promptSave() {
/* 33 */     int returnVal = super.showSaveDialog(this);
/* 34 */     if (returnVal == 0) {
/* 35 */       this.onApprove.onApprove(getSelectedFile());
/*    */     }
/*    */     else
/* 38 */       this.onApprove.onCancel();
/*    */   }
/*    */ 
/*    */   public void promptDialog(String dialog) {
/* 42 */     int returnVal = super.showDialog(this, dialog);
/* 43 */     if (returnVal == 0) {
/* 44 */       this.onApprove.onApprove(getSelectedFile());
/*    */     }
/*    */     else
/* 47 */       this.onApprove.onCancel();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.ModulusFileChooser
 * JD-Core Version:    0.6.2
 */