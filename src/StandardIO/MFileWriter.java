/*    */ package StandardIO;
/*    */ 
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ 
/*    */ public class MFileWriter extends PrintWriter
/*    */ {
/*    */   public MFileWriter(String file)
/*    */     throws IOException
/*    */   {
/* 16 */     super(new FileWriter(file));
/*    */   }
/*    */   public void writeDateAndTime() {
/* 19 */     Calendar c = Calendar.getInstance();
/* 20 */     SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy\tHH:mm:ss");
/* 21 */     super.println(fmt.format(c.getTime()));
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     StandardIO.MFileWriter
 * JD-Core Version:    0.6.2
 */