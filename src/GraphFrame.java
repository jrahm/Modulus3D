/*     */ import GUIComponents.MouseClickListener;
/*     */ import GUIComponents.MouseOneClickListener;
/*     */ import StandardIO.Approvable;
/*     */ import StandardIO.ModulusFileChooser;
/*     */ import equations.FiveVariableEquation;
/*     */ import equations.FourVariableEquation;
/*     */ import equations.LinearEquation;
/*     */ import equations.ThreeVariableEquation;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class GraphFrame extends JFrame
/*     */   implements ActionListener, GraphBoxListener, WindowListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  34 */   private static GraphFrame instance = null;
/*     */   private Graph2D graph;
/*     */   private GraphEventHandler eventHandler;
/*     */   private JPanel panel;
/*     */   private JPanel graphContainer;
/*     */   private TrackingPanel trackingPanel;
/*     */   private AnswerPanel answerPanel;
/*     */   private JMenuBar menubar;
/*     */   private EquationDialog equationDialog;
/*     */   private Graph2DOptionFrame graphOptions;
/*     */   private StatPlotDialog statplot;
/*     */   private WindowRangeDialog windowRangeDialog;
/*     */   private Menu zoomMenu;
/*     */   private int graphBoxListenerIndex;
/*     */   private int graphClickListenerIndex;
/*     */   private GraphFrame ths;
/*     */   public static final double DERIV_DIFFERENCE = 2.E-05D;
/*     */   private MouseClickListener currentThreading;
/*     */   private Menu[] menus;
/*     */   private Approvable saveBitmap;
/*     */   private ModulusFileChooser saveBitmapChooser;
/*     */   private GraphBoxListener[] gboxlisteners;
/*     */   private MouseClickListener[] clickListeners;
/*     */   private RegressionDialog[] regressionDialogs;
/*     */ 
/*     */   // ERROR //
/*     */   public GraphFrame()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial 71	javax/swing/JFrame:<init>	()V
/*     */     //   4: aload_0
/*     */     //   5: invokestatic 73	Graph2D:getGraphInstance	()LGraph2D;
/*     */     //   8: putfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   11: aload_0
/*     */     //   12: new 81	StatPlotDialog
/*     */     //   15: dup
/*     */     //   16: aload_0
/*     */     //   17: aload_0
/*     */     //   18: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   21: invokespecial 83	StatPlotDialog:<init>	(Ljava/awt/Frame;LGraph2D;)V
/*     */     //   24: putfield 86	GraphFrame:statplot	LStatPlotDialog;
/*     */     //   27: aload_0
/*     */     //   28: iconst_0
/*     */     //   29: putfield 88	GraphFrame:graphBoxListenerIndex	I
/*     */     //   32: aload_0
/*     */     //   33: iconst_0
/*     */     //   34: putfield 90	GraphFrame:graphClickListenerIndex	I
/*     */     //   37: aload_0
/*     */     //   38: iconst_5
/*     */     //   39: anewarray 92	Menu
/*     */     //   42: dup
/*     */     //   43: iconst_0
/*     */     //   44: new 92	Menu
/*     */     //   47: dup
/*     */     //   48: ldc 94
/*     */     //   50: iconst_2
/*     */     //   51: anewarray 96	javax/swing/JComponent
/*     */     //   54: dup
/*     */     //   55: iconst_0
/*     */     //   56: new 98	javax/swing/JMenuItem
/*     */     //   59: dup
/*     */     //   60: ldc 100
/*     */     //   62: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   65: aastore
/*     */     //   66: dup
/*     */     //   67: iconst_1
/*     */     //   68: new 98	javax/swing/JMenuItem
/*     */     //   71: dup
/*     */     //   72: ldc 105
/*     */     //   74: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   77: aastore
/*     */     //   78: invokespecial 107	Menu:<init>	(Ljava/lang/String;[Ljavax/swing/JComponent;)V
/*     */     //   81: aastore
/*     */     //   82: dup
/*     */     //   83: iconst_1
/*     */     //   84: new 92	Menu
/*     */     //   87: dup
/*     */     //   88: ldc 110
/*     */     //   90: bipush 6
/*     */     //   92: anewarray 96	javax/swing/JComponent
/*     */     //   95: dup
/*     */     //   96: iconst_0
/*     */     //   97: new 98	javax/swing/JMenuItem
/*     */     //   100: dup
/*     */     //   101: ldc 112
/*     */     //   103: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   106: aastore
/*     */     //   107: dup
/*     */     //   108: iconst_1
/*     */     //   109: new 98	javax/swing/JMenuItem
/*     */     //   112: dup
/*     */     //   113: ldc 114
/*     */     //   115: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   118: aastore
/*     */     //   119: dup
/*     */     //   120: iconst_2
/*     */     //   121: new 98	javax/swing/JMenuItem
/*     */     //   124: dup
/*     */     //   125: ldc 116
/*     */     //   127: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   130: aastore
/*     */     //   131: dup
/*     */     //   132: iconst_3
/*     */     //   133: new 118	javax/swing/JSeparator
/*     */     //   136: dup
/*     */     //   137: invokespecial 120	javax/swing/JSeparator:<init>	()V
/*     */     //   140: aastore
/*     */     //   141: dup
/*     */     //   142: iconst_4
/*     */     //   143: new 118	javax/swing/JSeparator
/*     */     //   146: dup
/*     */     //   147: invokespecial 120	javax/swing/JSeparator:<init>	()V
/*     */     //   150: aastore
/*     */     //   151: dup
/*     */     //   152: iconst_5
/*     */     //   153: new 98	javax/swing/JMenuItem
/*     */     //   156: dup
/*     */     //   157: ldc 121
/*     */     //   159: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   162: aastore
/*     */     //   163: invokespecial 107	Menu:<init>	(Ljava/lang/String;[Ljavax/swing/JComponent;)V
/*     */     //   166: aastore
/*     */     //   167: dup
/*     */     //   168: iconst_2
/*     */     //   169: new 92	Menu
/*     */     //   172: dup
/*     */     //   173: ldc 123
/*     */     //   175: bipush 15
/*     */     //   177: anewarray 96	javax/swing/JComponent
/*     */     //   180: dup
/*     */     //   181: iconst_0
/*     */     //   182: new 98	javax/swing/JMenuItem
/*     */     //   185: dup
/*     */     //   186: ldc 125
/*     */     //   188: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   191: aastore
/*     */     //   192: dup
/*     */     //   193: iconst_1
/*     */     //   194: new 98	javax/swing/JMenuItem
/*     */     //   197: dup
/*     */     //   198: ldc 127
/*     */     //   200: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   203: aastore
/*     */     //   204: dup
/*     */     //   205: iconst_2
/*     */     //   206: new 98	javax/swing/JMenuItem
/*     */     //   209: dup
/*     */     //   210: ldc 129
/*     */     //   212: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   215: aastore
/*     */     //   216: dup
/*     */     //   217: iconst_3
/*     */     //   218: new 118	javax/swing/JSeparator
/*     */     //   221: dup
/*     */     //   222: invokespecial 120	javax/swing/JSeparator:<init>	()V
/*     */     //   225: aastore
/*     */     //   226: dup
/*     */     //   227: iconst_4
/*     */     //   228: new 118	javax/swing/JSeparator
/*     */     //   231: dup
/*     */     //   232: invokespecial 120	javax/swing/JSeparator:<init>	()V
/*     */     //   235: aastore
/*     */     //   236: dup
/*     */     //   237: iconst_5
/*     */     //   238: new 98	javax/swing/JMenuItem
/*     */     //   241: dup
/*     */     //   242: ldc 131
/*     */     //   244: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   247: aastore
/*     */     //   248: dup
/*     */     //   249: bipush 6
/*     */     //   251: new 98	javax/swing/JMenuItem
/*     */     //   254: dup
/*     */     //   255: ldc 133
/*     */     //   257: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   260: aastore
/*     */     //   261: dup
/*     */     //   262: bipush 7
/*     */     //   264: new 98	javax/swing/JMenuItem
/*     */     //   267: dup
/*     */     //   268: ldc 135
/*     */     //   270: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   273: aastore
/*     */     //   274: dup
/*     */     //   275: bipush 8
/*     */     //   277: new 118	javax/swing/JSeparator
/*     */     //   280: dup
/*     */     //   281: invokespecial 120	javax/swing/JSeparator:<init>	()V
/*     */     //   284: aastore
/*     */     //   285: dup
/*     */     //   286: bipush 9
/*     */     //   288: new 118	javax/swing/JSeparator
/*     */     //   291: dup
/*     */     //   292: invokespecial 120	javax/swing/JSeparator:<init>	()V
/*     */     //   295: aastore
/*     */     //   296: dup
/*     */     //   297: bipush 10
/*     */     //   299: new 98	javax/swing/JMenuItem
/*     */     //   302: dup
/*     */     //   303: ldc 137
/*     */     //   305: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   308: aastore
/*     */     //   309: dup
/*     */     //   310: bipush 11
/*     */     //   312: new 98	javax/swing/JMenuItem
/*     */     //   315: dup
/*     */     //   316: ldc 139
/*     */     //   318: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   321: aastore
/*     */     //   322: dup
/*     */     //   323: bipush 12
/*     */     //   325: new 98	javax/swing/JMenuItem
/*     */     //   328: dup
/*     */     //   329: ldc 141
/*     */     //   331: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   334: aastore
/*     */     //   335: dup
/*     */     //   336: bipush 13
/*     */     //   338: new 98	javax/swing/JMenuItem
/*     */     //   341: dup
/*     */     //   342: ldc 143
/*     */     //   344: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   347: aastore
/*     */     //   348: dup
/*     */     //   349: bipush 14
/*     */     //   351: new 98	javax/swing/JMenuItem
/*     */     //   354: dup
/*     */     //   355: ldc 145
/*     */     //   357: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   360: aastore
/*     */     //   361: invokespecial 107	Menu:<init>	(Ljava/lang/String;[Ljavax/swing/JComponent;)V
/*     */     //   364: aastore
/*     */     //   365: dup
/*     */     //   366: iconst_3
/*     */     //   367: new 92	Menu
/*     */     //   370: dup
/*     */     //   371: ldc 147
/*     */     //   373: iconst_3
/*     */     //   374: anewarray 96	javax/swing/JComponent
/*     */     //   377: dup
/*     */     //   378: iconst_0
/*     */     //   379: new 98	javax/swing/JMenuItem
/*     */     //   382: dup
/*     */     //   383: ldc 149
/*     */     //   385: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   388: aastore
/*     */     //   389: dup
/*     */     //   390: iconst_1
/*     */     //   391: new 151	javax/swing/JRadioButton
/*     */     //   394: dup
/*     */     //   395: ldc 153
/*     */     //   397: invokespecial 155	javax/swing/JRadioButton:<init>	(Ljava/lang/String;)V
/*     */     //   400: aastore
/*     */     //   401: dup
/*     */     //   402: iconst_2
/*     */     //   403: aload_0
/*     */     //   404: new 92	Menu
/*     */     //   407: dup
/*     */     //   408: ldc 156
/*     */     //   410: bipush 7
/*     */     //   412: anewarray 96	javax/swing/JComponent
/*     */     //   415: dup
/*     */     //   416: iconst_0
/*     */     //   417: new 98	javax/swing/JMenuItem
/*     */     //   420: dup
/*     */     //   421: ldc 158
/*     */     //   423: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   426: aastore
/*     */     //   427: dup
/*     */     //   428: iconst_1
/*     */     //   429: new 98	javax/swing/JMenuItem
/*     */     //   432: dup
/*     */     //   433: ldc 160
/*     */     //   435: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   438: aastore
/*     */     //   439: dup
/*     */     //   440: iconst_2
/*     */     //   441: new 98	javax/swing/JMenuItem
/*     */     //   444: dup
/*     */     //   445: ldc 162
/*     */     //   447: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   450: aastore
/*     */     //   451: dup
/*     */     //   452: iconst_3
/*     */     //   453: new 98	javax/swing/JMenuItem
/*     */     //   456: dup
/*     */     //   457: ldc 164
/*     */     //   459: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   462: aastore
/*     */     //   463: dup
/*     */     //   464: iconst_4
/*     */     //   465: new 118	javax/swing/JSeparator
/*     */     //   468: dup
/*     */     //   469: invokespecial 120	javax/swing/JSeparator:<init>	()V
/*     */     //   472: aastore
/*     */     //   473: dup
/*     */     //   474: iconst_5
/*     */     //   475: new 118	javax/swing/JSeparator
/*     */     //   478: dup
/*     */     //   479: invokespecial 120	javax/swing/JSeparator:<init>	()V
/*     */     //   482: aastore
/*     */     //   483: dup
/*     */     //   484: bipush 6
/*     */     //   486: new 98	javax/swing/JMenuItem
/*     */     //   489: dup
/*     */     //   490: ldc 166
/*     */     //   492: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   495: aastore
/*     */     //   496: invokespecial 107	Menu:<init>	(Ljava/lang/String;[Ljavax/swing/JComponent;)V
/*     */     //   499: dup_x1
/*     */     //   500: putfield 168	GraphFrame:zoomMenu	LMenu;
/*     */     //   503: aastore
/*     */     //   504: invokespecial 107	Menu:<init>	(Ljava/lang/String;[Ljavax/swing/JComponent;)V
/*     */     //   507: aastore
/*     */     //   508: dup
/*     */     //   509: iconst_4
/*     */     //   510: new 92	Menu
/*     */     //   513: dup
/*     */     //   514: ldc 170
/*     */     //   516: iconst_3
/*     */     //   517: anewarray 96	javax/swing/JComponent
/*     */     //   520: dup
/*     */     //   521: iconst_0
/*     */     //   522: new 98	javax/swing/JMenuItem
/*     */     //   525: dup
/*     */     //   526: ldc 172
/*     */     //   528: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   531: aastore
/*     */     //   532: dup
/*     */     //   533: iconst_1
/*     */     //   534: new 98	javax/swing/JMenuItem
/*     */     //   537: dup
/*     */     //   538: ldc 174
/*     */     //   540: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   543: aastore
/*     */     //   544: dup
/*     */     //   545: iconst_2
/*     */     //   546: new 98	javax/swing/JMenuItem
/*     */     //   549: dup
/*     */     //   550: ldc 176
/*     */     //   552: invokespecial 102	javax/swing/JMenuItem:<init>	(Ljava/lang/String;)V
/*     */     //   555: aastore
/*     */     //   556: invokespecial 107	Menu:<init>	(Ljava/lang/String;[Ljavax/swing/JComponent;)V
/*     */     //   559: aastore
/*     */     //   560: putfield 178	GraphFrame:menus	[LMenu;
/*     */     //   563: aload_0
/*     */     //   564: new 180	GraphFrame$1
/*     */     //   567: dup
/*     */     //   568: aload_0
/*     */     //   569: invokespecial 182	GraphFrame$1:<init>	(LGraphFrame;)V
/*     */     //   572: putfield 185	GraphFrame:saveBitmap	LStandardIO/Approvable;
/*     */     //   575: aload_0
/*     */     //   576: new 187	StandardIO/ModulusFileChooser
/*     */     //   579: dup
/*     */     //   580: aload_0
/*     */     //   581: getfield 185	GraphFrame:saveBitmap	LStandardIO/Approvable;
/*     */     //   584: ldc 189
/*     */     //   586: iconst_3
/*     */     //   587: anewarray 191	StandardIO/MFileFilter
/*     */     //   590: dup
/*     */     //   591: iconst_0
/*     */     //   592: new 191	StandardIO/MFileFilter
/*     */     //   595: dup
/*     */     //   596: iconst_2
/*     */     //   597: anewarray 193	java/lang/String
/*     */     //   600: dup
/*     */     //   601: iconst_0
/*     */     //   602: ldc 195
/*     */     //   604: aastore
/*     */     //   605: dup
/*     */     //   606: iconst_1
/*     */     //   607: ldc 197
/*     */     //   609: aastore
/*     */     //   610: ldc 199
/*     */     //   612: invokespecial 201	StandardIO/MFileFilter:<init>	([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   615: aastore
/*     */     //   616: dup
/*     */     //   617: iconst_1
/*     */     //   618: new 191	StandardIO/MFileFilter
/*     */     //   621: dup
/*     */     //   622: iconst_1
/*     */     //   623: anewarray 193	java/lang/String
/*     */     //   626: dup
/*     */     //   627: iconst_0
/*     */     //   628: ldc 204
/*     */     //   630: aastore
/*     */     //   631: ldc 206
/*     */     //   633: invokespecial 201	StandardIO/MFileFilter:<init>	([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   636: aastore
/*     */     //   637: dup
/*     */     //   638: iconst_2
/*     */     //   639: new 191	StandardIO/MFileFilter
/*     */     //   642: dup
/*     */     //   643: iconst_1
/*     */     //   644: anewarray 193	java/lang/String
/*     */     //   647: dup
/*     */     //   648: iconst_0
/*     */     //   649: ldc 208
/*     */     //   651: aastore
/*     */     //   652: ldc 210
/*     */     //   654: invokespecial 201	StandardIO/MFileFilter:<init>	([Ljava/lang/String;Ljava/lang/String;)V
/*     */     //   657: aastore
/*     */     //   658: invokespecial 212	StandardIO/ModulusFileChooser:<init>	(LStandardIO/Approvable;Ljava/lang/String;[LStandardIO/MFileFilter;)V
/*     */     //   661: putfield 215	GraphFrame:saveBitmapChooser	LStandardIO/ModulusFileChooser;
/*     */     //   664: aload_0
/*     */     //   665: iconst_2
/*     */     //   666: anewarray 7	GraphBoxListener
/*     */     //   669: dup
/*     */     //   670: iconst_0
/*     */     //   671: new 217	GraphFrame$2
/*     */     //   674: dup
/*     */     //   675: aload_0
/*     */     //   676: invokespecial 219	GraphFrame$2:<init>	(LGraphFrame;)V
/*     */     //   679: aastore
/*     */     //   680: dup
/*     */     //   681: iconst_1
/*     */     //   682: new 220	GraphFrame$3
/*     */     //   685: dup
/*     */     //   686: aload_0
/*     */     //   687: invokespecial 222	GraphFrame$3:<init>	(LGraphFrame;)V
/*     */     //   690: aastore
/*     */     //   691: putfield 223	GraphFrame:gboxlisteners	[LGraphBoxListener;
/*     */     //   694: aload_0
/*     */     //   695: iconst_3
/*     */     //   696: anewarray 225	GUIComponents/MouseClickListener
/*     */     //   699: dup
/*     */     //   700: iconst_0
/*     */     //   701: new 227	GraphFrame$4
/*     */     //   704: dup
/*     */     //   705: aload_0
/*     */     //   706: aload_0
/*     */     //   707: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   710: invokespecial 229	GraphFrame$4:<init>	(LGraphFrame;Ljava/awt/Container;)V
/*     */     //   713: aastore
/*     */     //   714: dup
/*     */     //   715: iconst_1
/*     */     //   716: new 232	GraphFrame$5
/*     */     //   719: dup
/*     */     //   720: aload_0
/*     */     //   721: invokespecial 234	GraphFrame$5:<init>	(LGraphFrame;)V
/*     */     //   724: aastore
/*     */     //   725: dup
/*     */     //   726: iconst_2
/*     */     //   727: new 235	GraphFrame$6
/*     */     //   730: dup
/*     */     //   731: aload_0
/*     */     //   732: aload_0
/*     */     //   733: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   736: invokespecial 237	GraphFrame$6:<init>	(LGraphFrame;Ljava/awt/Container;)V
/*     */     //   739: aastore
/*     */     //   740: putfield 238	GraphFrame:clickListeners	[LGUIComponents/MouseClickListener;
/*     */     //   743: aload_0
/*     */     //   744: iconst_4
/*     */     //   745: anewarray 240	RegressionDialog
/*     */     //   748: dup
/*     */     //   749: iconst_0
/*     */     //   750: new 242	GraphFrame$7
/*     */     //   753: dup
/*     */     //   754: aload_0
/*     */     //   755: aload_0
/*     */     //   756: getfield 244	GraphFrame:ths	LGraphFrame;
/*     */     //   759: ldc 137
/*     */     //   761: aload_0
/*     */     //   762: getfield 86	GraphFrame:statplot	LStatPlotDialog;
/*     */     //   765: invokespecial 246	GraphFrame$7:<init>	(LGraphFrame;Ljava/awt/Frame;Ljava/lang/String;LStatPlotDialog;)V
/*     */     //   768: aastore
/*     */     //   769: dup
/*     */     //   770: iconst_1
/*     */     //   771: new 249	GraphFrame$8
/*     */     //   774: dup
/*     */     //   775: aload_0
/*     */     //   776: aload_0
/*     */     //   777: getfield 244	GraphFrame:ths	LGraphFrame;
/*     */     //   780: ldc 139
/*     */     //   782: aload_0
/*     */     //   783: getfield 86	GraphFrame:statplot	LStatPlotDialog;
/*     */     //   786: invokespecial 251	GraphFrame$8:<init>	(LGraphFrame;Ljava/awt/Frame;Ljava/lang/String;LStatPlotDialog;)V
/*     */     //   789: aastore
/*     */     //   790: dup
/*     */     //   791: iconst_2
/*     */     //   792: new 252	GraphFrame$9
/*     */     //   795: dup
/*     */     //   796: aload_0
/*     */     //   797: aload_0
/*     */     //   798: getfield 244	GraphFrame:ths	LGraphFrame;
/*     */     //   801: ldc 141
/*     */     //   803: aload_0
/*     */     //   804: getfield 86	GraphFrame:statplot	LStatPlotDialog;
/*     */     //   807: invokespecial 254	GraphFrame$9:<init>	(LGraphFrame;Ljava/awt/Frame;Ljava/lang/String;LStatPlotDialog;)V
/*     */     //   810: aastore
/*     */     //   811: dup
/*     */     //   812: iconst_3
/*     */     //   813: new 255	GraphFrame$10
/*     */     //   816: dup
/*     */     //   817: aload_0
/*     */     //   818: aload_0
/*     */     //   819: getfield 244	GraphFrame:ths	LGraphFrame;
/*     */     //   822: ldc 143
/*     */     //   824: aload_0
/*     */     //   825: getfield 86	GraphFrame:statplot	LStatPlotDialog;
/*     */     //   828: invokespecial 257	GraphFrame$10:<init>	(LGraphFrame;Ljava/awt/Frame;Ljava/lang/String;LStatPlotDialog;)V
/*     */     //   831: aastore
/*     */     //   832: putfield 258	GraphFrame:regressionDialogs	[LRegressionDialog;
/*     */     //   835: aload_0
/*     */     //   836: ldc_w 260
/*     */     //   839: invokevirtual 262	GraphFrame:setTitle	(Ljava/lang/String;)V
/*     */     //   842: aload_0
/*     */     //   843: new 265	java/awt/GridBagLayout
/*     */     //   846: dup
/*     */     //   847: invokespecial 267	java/awt/GridBagLayout:<init>	()V
/*     */     //   850: invokevirtual 268	GraphFrame:setLayout	(Ljava/awt/LayoutManager;)V
/*     */     //   853: new 272	java/awt/GridBagConstraints
/*     */     //   856: dup
/*     */     //   857: invokespecial 274	java/awt/GridBagConstraints:<init>	()V
/*     */     //   860: astore_1
/*     */     //   861: aload_0
/*     */     //   862: new 275	javax/swing/JPanel
/*     */     //   865: dup
/*     */     //   866: invokespecial 277	javax/swing/JPanel:<init>	()V
/*     */     //   869: putfield 278	GraphFrame:panel	Ljavax/swing/JPanel;
/*     */     //   872: aload_0
/*     */     //   873: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   876: aload_0
/*     */     //   877: getfield 280	GraphFrame:trackingPanel	LTrackingPanel;
/*     */     //   880: invokevirtual 282	Graph2D:addMouseMotionListener	(Ljava/awt/event/MouseMotionListener;)V
/*     */     //   883: aload_0
/*     */     //   884: new 286	AnswerPanel
/*     */     //   887: dup
/*     */     //   888: iconst_5
/*     */     //   889: anewarray 193	java/lang/String
/*     */     //   892: dup
/*     */     //   893: iconst_0
/*     */     //   894: ldc_w 288
/*     */     //   897: aastore
/*     */     //   898: dup
/*     */     //   899: iconst_1
/*     */     //   900: ldc_w 288
/*     */     //   903: aastore
/*     */     //   904: dup
/*     */     //   905: iconst_2
/*     */     //   906: ldc_w 288
/*     */     //   909: aastore
/*     */     //   910: dup
/*     */     //   911: iconst_3
/*     */     //   912: ldc_w 288
/*     */     //   915: aastore
/*     */     //   916: dup
/*     */     //   917: iconst_4
/*     */     //   918: ldc_w 288
/*     */     //   921: aastore
/*     */     //   922: aload_0
/*     */     //   923: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   926: invokespecial 290	AnswerPanel:<init>	([Ljava/lang/String;LGraph2D;)V
/*     */     //   929: putfield 293	GraphFrame:answerPanel	LAnswerPanel;
/*     */     //   932: aload_0
/*     */     //   933: new 295	TrackingPanel
/*     */     //   936: dup
/*     */     //   937: aload_0
/*     */     //   938: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   941: aload_0
/*     */     //   942: getfield 293	GraphFrame:answerPanel	LAnswerPanel;
/*     */     //   945: invokespecial 297	TrackingPanel:<init>	(LGraphTranslator;LAnswerPanel;)V
/*     */     //   948: putfield 280	GraphFrame:trackingPanel	LTrackingPanel;
/*     */     //   951: aload_0
/*     */     //   952: new 275	javax/swing/JPanel
/*     */     //   955: dup
/*     */     //   956: new 300	java/awt/FlowLayout
/*     */     //   959: dup
/*     */     //   960: invokespecial 302	java/awt/FlowLayout:<init>	()V
/*     */     //   963: invokespecial 303	javax/swing/JPanel:<init>	(Ljava/awt/LayoutManager;)V
/*     */     //   966: putfield 305	GraphFrame:graphContainer	Ljavax/swing/JPanel;
/*     */     //   969: aload_0
/*     */     //   970: getfield 278	GraphFrame:panel	Ljavax/swing/JPanel;
/*     */     //   973: new 300	java/awt/FlowLayout
/*     */     //   976: dup
/*     */     //   977: invokespecial 302	java/awt/FlowLayout:<init>	()V
/*     */     //   980: invokevirtual 307	javax/swing/JPanel:setLayout	(Ljava/awt/LayoutManager;)V
/*     */     //   983: aload_0
/*     */     //   984: getfield 305	GraphFrame:graphContainer	Ljavax/swing/JPanel;
/*     */     //   987: aload_0
/*     */     //   988: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   991: invokevirtual 308	javax/swing/JPanel:add	(Ljava/awt/Component;)Ljava/awt/Component;
/*     */     //   994: pop
/*     */     //   995: aload_0
/*     */     //   996: getfield 293	GraphFrame:answerPanel	LAnswerPanel;
/*     */     //   999: new 312	java/awt/Dimension
/*     */     //   1002: dup
/*     */     //   1003: bipush 120
/*     */     //   1005: sipush 500
/*     */     //   1008: invokespecial 314	java/awt/Dimension:<init>	(II)V
/*     */     //   1011: invokevirtual 317	AnswerPanel:setPreferredSize	(Ljava/awt/Dimension;)V
/*     */     //   1014: aload_0
/*     */     //   1015: getfield 280	GraphFrame:trackingPanel	LTrackingPanel;
/*     */     //   1018: new 312	java/awt/Dimension
/*     */     //   1021: dup
/*     */     //   1022: sipush 500
/*     */     //   1025: bipush 70
/*     */     //   1027: invokespecial 314	java/awt/Dimension:<init>	(II)V
/*     */     //   1030: invokevirtual 321	TrackingPanel:setPreferredSize	(Ljava/awt/Dimension;)V
/*     */     //   1033: aload_1
/*     */     //   1034: bipush 23
/*     */     //   1036: putfield 322	java/awt/GridBagConstraints:anchor	I
/*     */     //   1039: aload_1
/*     */     //   1040: dconst_0
/*     */     //   1041: putfield 325	java/awt/GridBagConstraints:weightx	D
/*     */     //   1044: aload_1
/*     */     //   1045: iconst_2
/*     */     //   1046: putfield 328	java/awt/GridBagConstraints:gridwidth	I
/*     */     //   1049: aload_1
/*     */     //   1050: iconst_2
/*     */     //   1051: putfield 331	java/awt/GridBagConstraints:gridheight	I
/*     */     //   1054: aload_1
/*     */     //   1055: iconst_2
/*     */     //   1056: putfield 334	java/awt/GridBagConstraints:fill	I
/*     */     //   1059: aload_1
/*     */     //   1060: iconst_0
/*     */     //   1061: putfield 337	java/awt/GridBagConstraints:gridx	I
/*     */     //   1064: aload_1
/*     */     //   1065: iconst_0
/*     */     //   1066: putfield 340	java/awt/GridBagConstraints:gridy	I
/*     */     //   1069: aload_0
/*     */     //   1070: aload_0
/*     */     //   1071: getfield 305	GraphFrame:graphContainer	Ljavax/swing/JPanel;
/*     */     //   1074: aload_1
/*     */     //   1075: invokevirtual 343	GraphFrame:add	(Ljava/awt/Component;Ljava/lang/Object;)V
/*     */     //   1078: aload_1
/*     */     //   1079: bipush 22
/*     */     //   1081: putfield 322	java/awt/GridBagConstraints:anchor	I
/*     */     //   1084: aload_1
/*     */     //   1085: dconst_0
/*     */     //   1086: putfield 325	java/awt/GridBagConstraints:weightx	D
/*     */     //   1089: aload_1
/*     */     //   1090: iconst_3
/*     */     //   1091: putfield 334	java/awt/GridBagConstraints:fill	I
/*     */     //   1094: aload_1
/*     */     //   1095: iconst_2
/*     */     //   1096: putfield 337	java/awt/GridBagConstraints:gridx	I
/*     */     //   1099: aload_1
/*     */     //   1100: iconst_1
/*     */     //   1101: putfield 340	java/awt/GridBagConstraints:gridy	I
/*     */     //   1104: aload_0
/*     */     //   1105: aload_0
/*     */     //   1106: getfield 293	GraphFrame:answerPanel	LAnswerPanel;
/*     */     //   1109: aload_1
/*     */     //   1110: invokevirtual 343	GraphFrame:add	(Ljava/awt/Component;Ljava/lang/Object;)V
/*     */     //   1113: aload_1
/*     */     //   1114: bipush 25
/*     */     //   1116: putfield 322	java/awt/GridBagConstraints:anchor	I
/*     */     //   1119: aload_1
/*     */     //   1120: dconst_0
/*     */     //   1121: putfield 325	java/awt/GridBagConstraints:weightx	D
/*     */     //   1124: aload_1
/*     */     //   1125: iconst_2
/*     */     //   1126: putfield 334	java/awt/GridBagConstraints:fill	I
/*     */     //   1129: aload_1
/*     */     //   1130: iconst_0
/*     */     //   1131: putfield 337	java/awt/GridBagConstraints:gridx	I
/*     */     //   1134: aload_1
/*     */     //   1135: iconst_2
/*     */     //   1136: putfield 340	java/awt/GridBagConstraints:gridy	I
/*     */     //   1139: aload_0
/*     */     //   1140: aload_0
/*     */     //   1141: getfield 280	GraphFrame:trackingPanel	LTrackingPanel;
/*     */     //   1144: aload_1
/*     */     //   1145: invokevirtual 343	GraphFrame:add	(Ljava/awt/Component;Ljava/lang/Object;)V
/*     */     //   1148: aload_0
/*     */     //   1149: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1152: new 312	java/awt/Dimension
/*     */     //   1155: dup
/*     */     //   1156: sipush 500
/*     */     //   1159: sipush 500
/*     */     //   1162: invokespecial 314	java/awt/Dimension:<init>	(II)V
/*     */     //   1165: invokevirtual 346	Graph2D:setPreferredSize	(Ljava/awt/Dimension;)V
/*     */     //   1168: aload_0
/*     */     //   1169: getfield 305	GraphFrame:graphContainer	Ljavax/swing/JPanel;
/*     */     //   1172: invokestatic 347	javax/swing/BorderFactory:createLoweredBevelBorder	()Ljavax/swing/border/Border;
/*     */     //   1175: ldc_w 353
/*     */     //   1178: invokestatic 355	javax/swing/BorderFactory:createTitledBorder	(Ljavax/swing/border/Border;Ljava/lang/String;)Ljavax/swing/border/TitledBorder;
/*     */     //   1181: invokevirtual 359	javax/swing/JPanel:setBorder	(Ljavax/swing/border/Border;)V
/*     */     //   1184: aload_0
/*     */     //   1185: getfield 293	GraphFrame:answerPanel	LAnswerPanel;
/*     */     //   1188: invokestatic 347	javax/swing/BorderFactory:createLoweredBevelBorder	()Ljavax/swing/border/Border;
/*     */     //   1191: ldc_w 363
/*     */     //   1194: invokestatic 355	javax/swing/BorderFactory:createTitledBorder	(Ljavax/swing/border/Border;Ljava/lang/String;)Ljavax/swing/border/TitledBorder;
/*     */     //   1197: invokevirtual 365	AnswerPanel:setBorder	(Ljavax/swing/border/Border;)V
/*     */     //   1200: aload_0
/*     */     //   1201: getfield 280	GraphFrame:trackingPanel	LTrackingPanel;
/*     */     //   1204: invokestatic 347	javax/swing/BorderFactory:createLoweredBevelBorder	()Ljavax/swing/border/Border;
/*     */     //   1207: ldc_w 366
/*     */     //   1210: invokestatic 355	javax/swing/BorderFactory:createTitledBorder	(Ljavax/swing/border/Border;Ljava/lang/String;)Ljavax/swing/border/TitledBorder;
/*     */     //   1213: invokevirtual 368	TrackingPanel:setBorder	(Ljavax/swing/border/Border;)V
/*     */     //   1216: aload_0
/*     */     //   1217: new 369	javax/swing/JMenuBar
/*     */     //   1220: dup
/*     */     //   1221: invokespecial 371	javax/swing/JMenuBar:<init>	()V
/*     */     //   1224: putfield 372	GraphFrame:menubar	Ljavax/swing/JMenuBar;
/*     */     //   1227: aload_0
/*     */     //   1228: getfield 178	GraphFrame:menus	[LMenu;
/*     */     //   1231: dup
/*     */     //   1232: astore 5
/*     */     //   1234: arraylength
/*     */     //   1235: istore 4
/*     */     //   1237: iconst_0
/*     */     //   1238: istore_3
/*     */     //   1239: goto +25 -> 1264
/*     */     //   1242: aload 5
/*     */     //   1244: iload_3
/*     */     //   1245: aaload
/*     */     //   1246: astore_2
/*     */     //   1247: aload_0
/*     */     //   1248: getfield 372	GraphFrame:menubar	Ljavax/swing/JMenuBar;
/*     */     //   1251: aload_2
/*     */     //   1252: invokevirtual 374	javax/swing/JMenuBar:add	(Ljavax/swing/JMenu;)Ljavax/swing/JMenu;
/*     */     //   1255: pop
/*     */     //   1256: aload_2
/*     */     //   1257: aload_0
/*     */     //   1258: invokevirtual 377	Menu:addActionListener	(Ljava/awt/event/ActionListener;)V
/*     */     //   1261: iinc 3 1
/*     */     //   1264: iload_3
/*     */     //   1265: iload 4
/*     */     //   1267: if_icmplt -25 -> 1242
/*     */     //   1270: aload_0
/*     */     //   1271: aload_0
/*     */     //   1272: getfield 372	GraphFrame:menubar	Ljavax/swing/JMenuBar;
/*     */     //   1275: invokevirtual 381	GraphFrame:setJMenuBar	(Ljavax/swing/JMenuBar;)V
/*     */     //   1278: aload_0
/*     */     //   1279: new 385	GraphEventHandler
/*     */     //   1282: dup
/*     */     //   1283: aload_0
/*     */     //   1284: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1287: invokespecial 387	GraphEventHandler:<init>	(LGraph2D;)V
/*     */     //   1290: putfield 390	GraphFrame:eventHandler	LGraphEventHandler;
/*     */     //   1293: aload_0
/*     */     //   1294: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1297: aload_0
/*     */     //   1298: getfield 390	GraphFrame:eventHandler	LGraphEventHandler;
/*     */     //   1301: invokevirtual 282	Graph2D:addMouseMotionListener	(Ljava/awt/event/MouseMotionListener;)V
/*     */     //   1304: aload_0
/*     */     //   1305: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1308: aload_0
/*     */     //   1309: getfield 390	GraphFrame:eventHandler	LGraphEventHandler;
/*     */     //   1312: invokevirtual 392	Graph2D:addMouseListener	(Ljava/awt/event/MouseListener;)V
/*     */     //   1315: aload_0
/*     */     //   1316: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1319: aload_0
/*     */     //   1320: getfield 280	GraphFrame:trackingPanel	LTrackingPanel;
/*     */     //   1323: invokevirtual 282	Graph2D:addMouseMotionListener	(Ljava/awt/event/MouseMotionListener;)V
/*     */     //   1326: aload_0
/*     */     //   1327: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1330: aload_0
/*     */     //   1331: getfield 293	GraphFrame:answerPanel	LAnswerPanel;
/*     */     //   1334: invokevirtual 396	Graph2D:addEquationChangeListener	(LEquationChangeListener;)V
/*     */     //   1337: invokestatic 400	GraphTypeHolder:getInstance	()LGraphTypeHolder;
/*     */     //   1340: aload_0
/*     */     //   1341: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1344: invokevirtual 406	GraphTypeHolder:addGraphTypeStateChangedListener	(LGraphTypeStateChangedListener;)V
/*     */     //   1347: aload_0
/*     */     //   1348: getfield 293	GraphFrame:answerPanel	LAnswerPanel;
/*     */     //   1351: aload_0
/*     */     //   1352: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1355: invokevirtual 410	Graph2D:getEquations	()[Ljava/lang/String;
/*     */     //   1358: invokevirtual 414	AnswerPanel:setEquations	([Ljava/lang/String;)V
/*     */     //   1361: aload_0
/*     */     //   1362: aload_0
/*     */     //   1363: getfield 390	GraphFrame:eventHandler	LGraphEventHandler;
/*     */     //   1366: invokevirtual 418	GraphFrame:addMouseWheelListener	(Ljava/awt/event/MouseWheelListener;)V
/*     */     //   1369: aload_0
/*     */     //   1370: invokevirtual 422	GraphFrame:pack	()V
/*     */     //   1373: aload_0
/*     */     //   1374: iconst_1
/*     */     //   1375: invokevirtual 425	GraphFrame:setVisible	(Z)V
/*     */     //   1378: aload_0
/*     */     //   1379: invokevirtual 422	GraphFrame:pack	()V
/*     */     //   1382: ldc2_w 429
/*     */     //   1385: invokestatic 431	java/lang/Thread:sleep	(J)V
/*     */     //   1388: goto +4 -> 1392
/*     */     //   1391: astore_2
/*     */     //   1392: aload_0
/*     */     //   1393: new 437	EquationDialog
/*     */     //   1396: dup
/*     */     //   1397: aload_0
/*     */     //   1398: aload_0
/*     */     //   1399: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1402: invokespecial 439	EquationDialog:<init>	(Ljava/awt/Frame;LGraph2D;)V
/*     */     //   1405: putfield 440	GraphFrame:equationDialog	LEquationDialog;
/*     */     //   1408: aload_0
/*     */     //   1409: new 442	Graph2DOptionFrame
/*     */     //   1412: dup
/*     */     //   1413: aload_0
/*     */     //   1414: invokespecial 444	Graph2DOptionFrame:<init>	(Ljavax/swing/JFrame;)V
/*     */     //   1417: putfield 447	GraphFrame:graphOptions	LGraph2DOptionFrame;
/*     */     //   1420: aload_0
/*     */     //   1421: new 449	WindowRangeDialog
/*     */     //   1424: dup
/*     */     //   1425: aload_0
/*     */     //   1426: aload_0
/*     */     //   1427: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1430: invokespecial 451	WindowRangeDialog:<init>	(Ljava/awt/Frame;LGraph2D;)V
/*     */     //   1433: putfield 452	GraphFrame:windowRangeDialog	LWindowRangeDialog;
/*     */     //   1436: invokestatic 400	GraphTypeHolder:getInstance	()LGraphTypeHolder;
/*     */     //   1439: aload_0
/*     */     //   1440: getfield 440	GraphFrame:equationDialog	LEquationDialog;
/*     */     //   1443: invokevirtual 406	GraphTypeHolder:addGraphTypeStateChangedListener	(LGraphTypeStateChangedListener;)V
/*     */     //   1446: aload_0
/*     */     //   1447: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1450: invokevirtual 454	Graph2D:makePoints	()V
/*     */     //   1453: aload_0
/*     */     //   1454: getfield 79	GraphFrame:graph	LGraph2D;
/*     */     //   1457: invokevirtual 457	Graph2D:getRectangleDrawer	()LRectangleDrawer;
/*     */     //   1460: aload_0
/*     */     //   1461: invokevirtual 461	RectangleDrawer:addGraphBoxListener	(LGraphBoxListener;)V
/*     */     //   1464: aload_0
/*     */     //   1465: aload_0
/*     */     //   1466: putfield 244	GraphFrame:ths	LGraphFrame;
/*     */     //   1469: aload_0
/*     */     //   1470: getfield 178	GraphFrame:menus	[LMenu;
/*     */     //   1473: iconst_2
/*     */     //   1474: aaload
/*     */     //   1475: bipush 12
/*     */     //   1477: invokevirtual 467	Menu:get	(I)Ljavax/swing/JComponent;
/*     */     //   1480: iconst_0
/*     */     //   1481: invokevirtual 471	javax/swing/JComponent:setEnabled	(Z)V
/*     */     //   1484: aload_0
/*     */     //   1485: getfield 178	GraphFrame:menus	[LMenu;
/*     */     //   1488: iconst_2
/*     */     //   1489: aaload
/*     */     //   1490: bipush 13
/*     */     //   1492: invokevirtual 467	Menu:get	(I)Ljavax/swing/JComponent;
/*     */     //   1495: iconst_0
/*     */     //   1496: invokevirtual 471	javax/swing/JComponent:setEnabled	(Z)V
/*     */     //   1499: aload_0
/*     */     //   1500: getfield 178	GraphFrame:menus	[LMenu;
/*     */     //   1503: iconst_2
/*     */     //   1504: aaload
/*     */     //   1505: bipush 14
/*     */     //   1507: invokevirtual 467	Menu:get	(I)Ljavax/swing/JComponent;
/*     */     //   1510: iconst_0
/*     */     //   1511: invokevirtual 471	javax/swing/JComponent:setEnabled	(Z)V
/*     */     //   1514: aload_0
/*     */     //   1515: aload_0
/*     */     //   1516: invokevirtual 474	GraphFrame:addWindowListener	(Ljava/awt/event/WindowListener;)V
/*     */     //   1519: return
/*     */     //
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   1382	1388	1391	java/lang/Exception
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 206 */     if (this.currentThreading != null) {
/* 207 */       this.graph.removeMouseListener(this.currentThreading);
/*     */     }
/* 209 */     if (e.getSource() == this.menus[0].get(0)) {
/* 210 */       this.saveBitmapChooser.promptSave();
/*     */     }
/* 212 */     if (e.getSource() == this.menus[1].get(0))
/* 213 */       this.equationDialog.setVisible(true);
/* 214 */     if (e.getSource() == this.menus[4].get(1))
/* 215 */       this.graphOptions.setVisible(true);
/* 216 */     if (e.getSource() == this.menus[1].get(1))
/* 217 */       this.statplot.setVisible(true);
/* 218 */     if (e.getSource() == this.menus[3].get(0))
/* 219 */       this.windowRangeDialog.setVisible(true);
/* 220 */     if (e.getSource() == this.menus[4].get(0))
/* 221 */       ModulusThreads.addThread("Remaking Points", this.graph.getThread());
/* 222 */     if (e.getSource() == this.zoomMenu.get(1))
/* 223 */       scaleToInt();
/* 224 */     if (e.getSource() == this.zoomMenu.get(6))
/* 225 */       recenter();
/* 226 */     if (e.getSource() == this.zoomMenu.get(2)) {
/* 227 */       this.graphBoxListenerIndex = 0;
/* 228 */       this.graph.setRectangleMode(true);
/* 229 */     }if (e.getSource() == this.menus[2].get(2)) {
/* 230 */       this.graphBoxListenerIndex = 1;
/* 231 */       this.graph.setRectangleMode(true);
/* 232 */     }if (e.getSource() == this.menus[2].get(5)) {
/* 233 */       this.graphClickListenerIndex = 0;
/* 234 */       this.graph.addMouseListener(this.clickListeners[this.graphClickListenerIndex]);
/* 235 */     }if (e.getSource() == this.menus[2].get(6)) {
/* 236 */       this.graphClickListenerIndex = 2;
/* 237 */       this.graph.addMouseListener(this.clickListeners[this.graphClickListenerIndex]);
/* 238 */     }if (e.getSource() == this.menus[2].get(7)) {
/* 239 */       this.graphClickListenerIndex = 1;
/* 240 */       this.graph.addMouseListener(this.clickListeners[this.graphClickListenerIndex]);
/* 241 */       this.currentThreading = this.clickListeners[this.graphClickListenerIndex];
/* 242 */     }if (e.getSource() == this.menus[2].get(10))
/* 243 */       this.regressionDialogs[0].setVisible(true);
/* 244 */     if (e.getSource() == this.menus[2].get(11))
/* 245 */       this.regressionDialogs[1].setVisible(true);
/* 246 */     if (e.getSource() == this.menus[2].get(12))
/* 247 */       this.regressionDialogs[2].setVisible(true);
/* 248 */     if (e.getSource() == this.menus[2].get(13))
/* 249 */       this.regressionDialogs[3].setVisible(true);
/* 250 */     if (e.getSource() == this.menus[1].get(5))
/*     */       try {
/* 252 */         new TableFrame(this.graph.getEquations(), 0.0D, 10.0D).setVisible(true);
/*     */       } catch (Exception ex) {
/* 254 */         ex.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/* 258 */   public static void main(String[] args) { run(); }
/*     */ 
/*     */   public static void run()
/*     */   {
/* 262 */     if (instance == null)
/* 263 */       instance = new GraphFrame();
/*     */     else
/* 265 */       instance.setVisible(true); 
/*     */   }
/*     */ 
/* 268 */   private void scaleToInt() { WindowRange graphWindowRange = this.graph.getWindowRange();
/* 269 */     int differenceX = (int)Math.round((graphWindowRange.getXMax() - graphWindowRange.getXMin()) / this.graph.getWidth()) * getWidth();
/* 270 */     int differenceY = (int)Math.round((graphWindowRange.getYMax() - graphWindowRange.getYMin()) / this.graph.getHeight()) * getHeight();
/*     */ 
/* 272 */     if (differenceX == 0) differenceX = this.graph.getWidth();
/* 273 */     if (differenceY == 0) differenceY = this.graph.getHeight();
/*     */ 
/* 275 */     double pivX = (graphWindowRange.getXMax() + graphWindowRange.getXMin()) / 2.0D;
/* 276 */     double pivY = (graphWindowRange.getYMax() + graphWindowRange.getYMin()) / 2.0D;
/*     */ 
/* 278 */     WindowRange newRange = new WindowRange(pivX - differenceX / 2, pivY - differenceY / 2, pivX + differenceX / 2, pivY + differenceY / 2);
/* 279 */     this.graph.setWindowRange(newRange);
/* 280 */     this.graph.recreate(); }
/*     */ 
/*     */   private void recenter() {
/* 283 */     WindowRange graphWindowRange = this.graph.getWindowRange();
/* 284 */     double halfX = (graphWindowRange.getXMax() - graphWindowRange.getXMin()) / 2.0D;
/* 285 */     double halfY = (graphWindowRange.getYMax() - graphWindowRange.getYMin()) / 2.0D;
/* 286 */     WindowRange newRange = new WindowRange(-halfX, -halfY, halfX, halfY);
/* 287 */     this.graph.setWindowRange(newRange);
/* 288 */     this.graph.recreate();
/*     */   }
/*     */   private static double round(double x, int radix) {
/* 291 */     return Math.round(x * Math.pow(10.0D, radix)) / Math.pow(10.0D, radix);
/*     */   }
/*     */ 
/*     */   public void graphBoxMade(int x1, int y1, int x2, int y2) {
/* 295 */     this.gboxlisteners[this.graphBoxListenerIndex].graphBoxMade(x1, y1, x2, y2);
/*     */   }
/*     */ 
/*     */   public static Thread getThread()
/*     */   {
/* 530 */     return new GraphFrame.GuiThread(null);
/*     */   }
/*     */ 
/*     */   public void windowActivated(WindowEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowClosed(WindowEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent arg0)
/*     */   {
/* 546 */     this.graph.removeMouseMotionListener(this.eventHandler);
/* 547 */     this.graph.removeMouseListener(this.eventHandler);
/* 548 */     this.graph.removeMouseMotionListener(this.trackingPanel);
/* 549 */     this.graph.removeEquationChangeListener(this.answerPanel);
/*     */   }
/*     */ 
/*     */   public void windowDeactivated(WindowEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowDeiconified(WindowEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowIconified(WindowEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowOpened(WindowEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   private static class GuiThread extends Thread
/*     */   {
/*     */     public void run()
/*     */     {
/* 534 */       new GraphFrame().setVisible(true);
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     GraphFrame
 * JD-Core Version:    0.6.2
 */