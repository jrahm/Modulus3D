/*    */ package StandardIO;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class SaveFileWriter extends MFileWriter
/*    */ {
/*    */   public SaveFileWriter(String file)
/*    */     throws IOException
/*    */   {
/* 14 */     super(file);
/* 15 */     println("Modulus 2.0.0 Save File");
/* 16 */     super.writeDateAndTime();
/* 17 */     println("------------------------------");
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.SaveFileWriter
 * JD-Core Version:    0.6.2
 */