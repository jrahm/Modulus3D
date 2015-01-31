/*    */ import javax.swing.JComboBox;
/*    */ 
/*    */ public class BaseComboBox extends JComboBox
/*    */ {
/*    */   public BaseComboBox(int index)
/*    */   {
/* 14 */     super(new String[] { "Unary (1)", "Binary (2)", "Ternary (3)", "Quaternal (4)", "Quintal (5)", "Sextal (6)", "Septernal (7)", "Ocatal (8)", "Nonary (9)", "Decimal (default)", "Unidecimal (11)", "Dozenal (12)", "Tredecimal (13)", "Tetradecimal (14)", "Quindecimal (15)", "Hexadecimal (16)", "Septendecimal (17)", "Octodecimal (18)", "Nonadecimal (19)", "Vigesimal (20)", "Base 21", "Base 22", "Base 23", "Base 24", "Base 25", "Base 26", "Base 27", "Base 28", "Base 29", "Base 30", "Base 31", "Base 32", "Base 33", "Base 34", "Base 35", "Base 36" });
/* 15 */     setSelectedIndex(index);
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     BaseComboBox
 * JD-Core Version:    0.6.2
 */