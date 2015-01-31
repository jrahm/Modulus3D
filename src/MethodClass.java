/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ public class MethodClass
/*    */ {
/*    */   Class cls;
/*    */ 
/*    */   public MethodClass(Class cls)
/*    */   {
/* 12 */     this.cls = cls;
/*    */   }
/*    */   public Method getMethod(String name) {
/* 15 */     for (Method m : this.cls.getMethods()) {
/* 16 */       if (m.getName().equals(name)) {
/* 17 */         return m;
/*    */       }
/*    */     }
/* 20 */     return null;
/*    */   }
/*    */   public Class getCls() {
/* 23 */     return this.cls;
/*    */   }
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     MethodClass
 * JD-Core Version:    0.6.2
 */