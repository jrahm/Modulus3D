/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract interface DynamicGraphBuilder extends Serializable
/*    */ {
/* 12 */   public static final DynamicGraphBuilder DO_NOTHING_GRAPH_BUILDER = new DynamicGraphBuilder() {
/* 12 */     public void recreate(Graph2D graph) {  }  } ;
/*    */ 
/*    */   public abstract void recreate(Graph2D paramGraph2D);
/*    */ }

/* Location:           Modulus.jar
 * Qualified Name:     DynamicGraphBuilder
 * JD-Core Version:    0.6.2
 */