/*    */ import StandardIO.Approvable;
/*    */ import StandardIO.MFileFilter;
/*    */ import StandardIO.ModulusFileChooser;
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ 
/*    */ public class ImageFrame extends JDialog
/*    */   implements MouseListener, ActionListener
/*    */ {
/*    */   private BufferedImage image;
/*    */   private JButton saveButton;
/* 25 */   private Approvable imageSaveApprove = new Approvable()
/*    */   {
/*    */     public void onApprove(File file) {
/*    */       try {
/* 29 */         file.createNewFile();
/* 30 */         ImageIO.write(ImageFrame.this.image, file.toString().substring(file.toString().lastIndexOf(".") + 1), file);
/*    */       }
/*    */       catch (IOException localIOException)
/*    */       {
/*    */       }
/*    */     }
/*    */ 
/*    */     public void onCancel()
/*    */     {
/*    */     }
/* 25 */   };
/*    */ 
/*    */   public ImageFrame(BufferedImage img)
/*    */   {
/* 37 */     setAlwaysOnTop(true);
/* 38 */     setLayout(new FlowLayout());
/* 39 */     setTitle("Snapshot");
/* 40 */     this.image = img;
/* 41 */     this.saveButton = new JButton("Save");
/* 42 */     add(this.saveButton);
/* 43 */     this.saveButton.setVisible(true);
/* 44 */     this.saveButton.addActionListener(this);
/* 45 */     addMouseListener(this);
/* 46 */     reset();
/*    */   }
/*    */   public void setImage(BufferedImage image) {
/* 49 */     this.image = image;
/* 50 */     reset();
/*    */   }
/*    */   private void reset() {
/* 53 */     setSize(350, 300);
/* 54 */     repaint();
/*    */   }
/*    */   public void paint(Graphics g2) {
/* 57 */     Graphics2D g = (Graphics2D)g2;
/* 58 */     if (this.image != null)
/* 59 */       g.drawImage(this.image, AffineTransform.getScaleInstance(getWidth() / this.image.getWidth(), getHeight() / this.image.getHeight()), null); 
/*    */   }
/*    */   public void mouseClicked(MouseEvent e) {
/*    */   }
/*    */   public void mouseEntered(MouseEvent e) {
/*    */   }
/*    */ 
/*    */   public void mouseExited(MouseEvent e) {
/*    */   }
/*    */ 
/*    */   public void mousePressed(MouseEvent e) {
/*    */   }
/*    */ 
/*    */   public void mouseReleased(MouseEvent e) {
/*    */   }
/*    */ 
/* 75 */   public void actionPerformed(ActionEvent e) { ModulusFileChooser mfc = new ModulusFileChooser(this.imageSaveApprove, "", new MFileFilter[] { new MFileFilter(new String[] { ".png" }, "png Image"), new MFileFilter(new String[] { ".gif" }, "Comput"), new MFileFilter(new String[] { ".bmp" }, "Bitmap") });
/* 76 */     mfc.promptSave();
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     ImageFrame
 * JD-Core Version:    0.6.2
 */