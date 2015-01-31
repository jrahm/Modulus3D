/*      */ import StandardIO.Approvable;
/*      */ import StandardIO.MFileFilter;
/*      */ import StandardIO.ModulusFileChooser;
/*      */ import StandardIO.ModulusOutputStream;
/*      */ import StandardIO.PropertiesReader;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.FlowLayout;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.datatransfer.DataFlavor;
/*      */ import java.awt.datatransfer.Transferable;
/*      */ import java.awt.dnd.DropTarget;
/*      */ import java.awt.dnd.DropTargetDragEvent;
/*      */ import java.awt.dnd.DropTargetDropEvent;
/*      */ import java.awt.dnd.DropTargetEvent;
/*      */ import java.awt.dnd.DropTargetListener;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.AbstractButton;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JCheckBoxMenuItem;
/*      */ import javax.swing.JColorChooser;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JRadioButtonMenuItem;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JSeparator;
/*      */ import javax.swing.JTextArea;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.JToggleButton;
/*      */ import javax.swing.JWindow;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import utilities.BaseConverter;
/*      */ import utilities.DecimalFormatter;
/*      */ import utilities.NoFormatter;
/*      */ 
/*      */ public class CalculatorGUI extends JFrame
/*      */   implements ActionListener, DropTargetListener, KeyListener, MouseListener
/*      */ {
/*      */   private static final long serialVersionUID = -7536111096507880724L;
/*      */   private static CalculatorGUI currentInstance;
/*   89 */   private String[] props = { "1234567890\\QWERTYUIOP\\ASDFGHJKL\\ZXCVBNM", "none", "none", "none", "full", "false" };
/*   90 */   private static CalculatorGUI.SplashScreen splash = new CalculatorGUI.SplashScreen();
/*   91 */   public static String CREDITS = "Modulus v2.0\nBy: Joshua Rahm\nSkyline High School\nLongmont, CO\nEmail: rahm_joshua@digitalagenation.com\nWebpage: http://www.digitalagenation/modulus.html";
/*   92 */   private Keyboard[] keys = { 
/*   93 */     new Keyboard("1234567890\\QWERTYUIOP\\ASDFGHJKL\\ZXCVBNM"), 
/*   94 */     new Keyboard(new String[][] { { "7", "8", "9" }, { "4", "5", "6" }, { "1", "2", "3" }, { ".", "0", "Entr" } }, 25, 35, null), 
/*   95 */     new Keyboard(new String[][] { { "acos(", "cos(" }, { "asin(", "sin(" }, { "atan(", "tan(" }, { "ln(", "log(" } }, 25, 45, null), 
/*   96 */     new Keyboard(":£^%e"), 
/*   97 */     new Keyboard("/\\*\\-\\+"), 
/*   98 */     new Keyboard("~?|&"), 
/*   99 */     new Keyboard("[(|!\\]),_"), 
/*  100 */     new Keyboard(new String[][] { { ">=", "<=", ">", "<", "=", " else " }, { " and ", " or ", " xor ", "not(", " if ", " then " } }, 25, 45, null), 
/*  101 */     new Keyboard(new String[][] { { "<html>x<sup>2</sup></html>", "√x", "<html>x<sup>3</sup></html>", "<html>x<sup>-1</sup></html>" } }, 25, 35, null), 
/*  102 */     new Keyboard(new String[][] { { "Bkspc", "Clear" } }, 25, 45, null), 
/*  103 */     new Keyboard(new String[][] { { "ClrHome" } }, 25, 55, null), 
/*  104 */     new Keyboard(new String[][] { { "STO ", "STP ", "PUSH ", "POP " }, { "DISP ", "STACK ", "DEL ", "DEC " } }, 25, 55, null) };
/*      */   private DecimalFormatter formatter;
/*  108 */   private boolean controlHeld = false;
/*  109 */   private boolean shiftHeld = false;
/*  110 */   private ArrayList<String> last = new ArrayList();
/*  111 */   private ArrayList<String> ans = new ArrayList();
/*  112 */   private int lastIndex = 0;
/*  113 */   private String[] topOpsArr = { "^2", "sqrt(", "^3", "^-1" };
/*      */ 
/*  115 */   private JPanel[] panels = { new JPanel(new FlowLayout()), new JPanel(new FlowLayout()), new JPanel(new BorderLayout()) };
/*  116 */   private JPanel numberPanel = new JPanel(new BorderLayout());
/*      */ 
/*  118 */   private JTextArea answerArea = new JTextArea(18, 17);
/*      */ 
/*  120 */   private JTextField answerField = new JTextField(35);
/*      */   private JScrollPane scroll;
/*  123 */   private JMenuBar mainbar = new JMenuBar();
/*      */ 
/*  126 */   private Object temp = null;
/*  127 */   private long sysTime = 0L;
/*      */ 
/*  129 */   private JButton terminate = new JButton("Terminate");
/*  130 */   private ArrayList<Thread> exec = new ArrayList();
/*  131 */   private File functions = new File("./functions");
/*  132 */   private ButtonGroup layout = new ButtonGroup();
/*      */ 
/*  134 */   private String[][] map = { 
/*  135 */     { "Add", "Subtract", "Multiply", "Divide", "Power", "Shift", "Root", "Sine", "Cosine", "Tangent", "Arcsine", "Arccosine", "Arctangent", "Logarithm", "Greater Than", "Less Than", 
/*  136 */     "Greater Than Equal To", "Less Than Equal To", "Equal To", "And", "Or", "Exclusive or", "Not", "if", "then", "else", "Bitwise And", "Bitwise or", "Bitwise Exclusive or", "Bitwise not", 
/*  137 */     "x Squared", "x Cubed", "Square Root x", "Inverse of x" }, 
/*  138 */     { "+", "-", "*", "/", "^", "e", "?", "sin(", "cos(", "tan(", "asin(", "acos(", "atan(", "log(", ">", "<", ">=", "<=", "=", " and ", " or ", " xor ", "not(", "if( ", " then( ", " else( ", "&", "\\", "?", "~(", 
/*  139 */     "^2", "^3", "sqrt(", "^-1" } };
/*      */ 
/*  145 */   private Menu[] menus = { 
/*  146 */     new Menu("File", new AbstractButton[] { 
/*  147 */     new JMenuItem("Close"), 
/*  148 */     new JMenuItem("Save") }), 
/*  150 */     new Menu("Edit", new AbstractButton[] { 
/*  151 */     new JMenuItem("Change Base"), 
/*  152 */     new JMenuItem("Convert Base"), 
/*  153 */     new JMenuItem("Clear"), 
/*  154 */     new JMenuItem("Manage Programs") }), 
/*  157 */     new Menu("Calculate", new AbstractButton[] { 
/*  158 */     new Menu("Basic Operators", new AbstractButton[] { 
/*  159 */     new JMenuItem("Add"), 
/*  160 */     new JMenuItem("Subtract"), 
/*  161 */     new JMenuItem("Multiply"), 
/*  162 */     new JMenuItem("Divide"), 
/*  163 */     new JMenuItem("Power"), 
/*  164 */     new JMenuItem("Shift"), 
/*  165 */     new JMenuItem("Root") }), 
/*  167 */     new Menu("Trigometic Operators", new AbstractButton[] { 
/*  168 */     new JMenuItem("Sine"), 
/*  169 */     new JMenuItem("Cosine"), 
/*  170 */     new JMenuItem("Tangent"), 
/*  171 */     new JMenuItem("Arccosine"), 
/*  172 */     new JMenuItem("Arcsin"), 
/*  173 */     new JMenuItem("Arctangent"), 
/*  174 */     new JMenuItem("Loagrithm") }), 
/*  176 */     new Menu("Boolean Operators", new AbstractButton[] { 
/*  177 */     new Menu("Relational Operators", new JComponent[] { 
/*  178 */     new JMenuItem("Greater Than"), 
/*  179 */     new JMenuItem("Less Than"), 
/*  180 */     new JMenuItem("Greater Than Equal To"), 
/*  181 */     new JMenuItem("Less Than Equal To"), 
/*  182 */     new JMenuItem("Equal To") }), 
/*  184 */     new Menu("Logical Operators", new AbstractButton[] { 
/*  185 */     new JMenuItem("And"), 
/*  186 */     new JMenuItem("Or"), 
/*  187 */     new JMenuItem("Exclusive or"), 
/*  188 */     new JMenuItem("Not") }), 
/*  190 */     new Menu("Flow Operators", new AbstractButton[] { 
/*  191 */     new JMenuItem("if"), 
/*  192 */     new JMenuItem("then"), 
/*  193 */     new JMenuItem("else") }) }), 
/*  196 */     new Menu("Bitwise Operators", new AbstractButton[] { 
/*  197 */     new JMenuItem("Bitwise And"), 
/*  198 */     new JMenuItem("Bitwise or"), 
/*  199 */     new JMenuItem("Bitwise Exclusive or"), 
/*  200 */     new JMenuItem("Bitwise not") }), 
/*  202 */     new Menu("Other Operators", new AbstractButton[] { 
/*  203 */     new JMenuItem("x Squared"), 
/*  204 */     new JMenuItem("x Cubed"), 
/*  205 */     new JMenuItem("Square Root x"), 
/*  206 */     new JMenuItem("Inverse of x") }), 
/*  208 */     new JCheckBoxMenuItem("Radians") }), 
/*  210 */     new Menu("Programming", new JComponent[] { 
/*  211 */     new JCheckBoxMenuItem("Toggle Shell Mode"), 
/*  212 */     new JMenuItem("Run a Script..."), 
/*  213 */     new JMenuItem("Function..."), 
/*  214 */     new JMenuItem("Load Library"), 
/*  215 */     new JMenuItem("Manage Startup Libraries"), 
/*  216 */     new JSeparator(), 
/*  217 */     new JSeparator(), 
/*  218 */     new JMenuItem("View Stacks"), 
/*  219 */     new JMenuItem("View Hold Variable"), 
/*  220 */     new JSeparator(), 
/*  221 */     new JSeparator(), 
/*  222 */     new JMenuItem("Run in Terminal...") }), 
/*  224 */     new Menu("Properties", new JComponent[] { 
/*  225 */     new JMenuItem("Change Color"), 
/*  226 */     new JMenuItem("Change Keyboard Style"), 
/*  227 */     new JCheckBoxMenuItem("Always Show Commands", true), 
/*  228 */     new JCheckBoxMenuItem("Auto Complete", true), 
/*  229 */     new JSeparator(), 
/*  230 */     new JSeparator(), 
/*  231 */     new JMenuItem("Set Formatting") }), 
/*  233 */     new Menu("View", new JComponent[] { 
/*  234 */     new JCheckBox("Always On Top"), 
/*  235 */     new JSeparator(), 
/*  236 */     new JSeparator(), 
/*  237 */     new JRadioButtonMenuItem("Standard"), 
/*  238 */     new JRadioButtonMenuItem("Scientific"), 
/*  239 */     new JRadioButtonMenuItem("Boolean"), 
/*  240 */     new JRadioButtonMenuItem("Bitwise"), 
/*  241 */     new JRadioButtonMenuItem("Full Calc", true), 
/*  242 */     new JSeparator(), 
/*  243 */     new JSeparator(), 
/*  244 */     new JMenuItem("View Graph"), 
/*  245 */     new JMenuItem("View 3D Graph") }) };
/*      */ 
/*  248 */   private JPanel[] subPanels = { 
/*  249 */     new JPanel(new FlowLayout()), new JPanel(new FlowLayout()), new JPanel(new FlowLayout()), new JPanel(new BorderLayout()) };
/*      */ 
/*  251 */   private Approvable[] approvables = { 
/*  252 */     new Approvable() {
/*      */     public void onApprove(File file) {
/*  254 */       CalculatorGUI.silentPrint("------------------------------\n");
/*  255 */       CalculatorGUI.silentPrint("Started Running File: " + file + " On new Thread\n");
/*  256 */       CalculatorGUI.silentPrint("------------------------------\n");
/*      */       Thread run;
/*  258 */       CalculatorGUI.this.exec.add(run = new SilentThread(new CalculatorGUI.Run(CalculatorGUI.this, file), file.toString()));
/*  259 */       run.start();
/*  260 */       CalculatorGUI.this.props[2] = file.getParent().toString();
/*  261 */       CalculatorGUI.this.writeProperties();
/*      */     }
/*      */ 
/*      */     public void onCancel()
/*      */     {
/*      */     }
/*      */   }
/*      */   , new Approvable() {
/*      */     public void onApprove(File file) {
/*  267 */       CalculatorGUI.silentPrint("------------------------------\n");
/*  268 */       CalculatorGUI.silentPrint("Loaded " + file);
/*  269 */       CalculatorGUI.silentPrint("------------------------------\n");
/*      */       try {
/*  271 */         ControlPanel.load(file.toString());
/*      */       }
/*      */       catch (Exception e) {
/*  274 */         e.printStackTrace();
/*      */       }
/*  276 */       CalculatorGUI.this.props[3] = file.getParent().toString();
/*  277 */       CalculatorGUI.this.writeProperties();
/*      */     }
/*      */ 
/*      */     public void onCancel()
/*      */     {
/*      */     }
/*      */   }
/*      */   , new Approvable() {
/*      */     public void onApprove(File file) {
/*  283 */       if ((System.out instanceof ModulusOutputStream))
/*  284 */         ((ModulusOutputStream)System.out).flushTo(file);  } 
/*      */     public void onCancel() {  }  }  };
/*      */ 
/*  449 */   private JButton[] hold = { new JButton("OK"), new JButton("OK"), new JButton("OK"), new JButton("OK") };
/*  450 */   private Prompt[] prompt = { new Prompt(this, "Change Base", new JComboBox[] { new BaseComboBox(9) }, this, this.hold[0]), 
/*  451 */     new Prompt(this, "Layouts", new JComponent[] { new JRadioButton("Qwerty"), 
/*  452 */     new JRadioButton("Dvorak"), 
/*  453 */     new JRadioButton("Alphabetical"), 
/*  454 */     new JRadioButton("Other"), 
/*  455 */     new JTextField("Enter other (\\ = new line)") }, this, this.hold[1]), 
/*  456 */     new Prompt(this, "Functions", new JComponent[] { new ActionList(ControlPanel.getFunctions(), this), 
/*  457 */     new JTextField("", 10) }, this, this.hold[2]), 
/*  458 */     new Prompt(this, "Convert Bases", new JComponent[] { new BaseComboBox(9), new JTextField("", 7), new JLabel("To"), new BaseComboBox(9) }, this, this.hold[3]), 
/*  459 */     new Prompt(this, "Hold Stack:", new JComponent[] { new JList() }, this, new JButton()) };
/*      */   private ButtonGroup keyboards;
/*      */ 
/*  294 */   public CalculatorGUI() { currentInstance = this;
/*  295 */     this.answerArea.setPreferredSize(new Dimension(100, 200));
/*  296 */     this.formatter = new NoFormatter(5);
/*      */     try {
/*  298 */       UIManager.setLookAndFeel(
/*  299 */         UIManager.getSystemLookAndFeelClassName());
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  303 */       e.printStackTrace();
/*      */     }
/*  305 */     this.keys[5].get(2).setText("\\");
/*      */ 
/*  307 */     this.exec.add(null);
/*  308 */     this.last.add("");
/*  309 */     this.ans.add("");
/*      */ 
/*  318 */     readProperties();
/*      */     try
/*      */     {
/*  321 */       if (this.props[2].equals("none")) this.props[2] = (new File(".").getCanonicalPath().toString() + "/functions");
/*  322 */       if (this.props[3].equals("none")) this.props[3] = (new File(".").getCanonicalPath().toString() + "/programs"); 
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  325 */       e.printStackTrace();
/*      */     }
/*      */ 
/*  331 */     this.layout.add((AbstractButton)this.menus[5].get(3));
/*  332 */     this.layout.add((AbstractButton)this.menus[5].get(4));
/*  333 */     this.layout.add((AbstractButton)this.menus[5].get(5));
/*  334 */     this.layout.add((AbstractButton)this.menus[5].get(6));
/*  335 */     this.layout.add((AbstractButton)this.menus[5].get(7));
/*      */     try
/*      */     {
/*  338 */       ScriptReader.runScript(this.functions.toString() + "/functionsLOD.prop");
/*      */     } catch (Exception e) {
/*  340 */       e.printStackTrace();
/*      */     }
/*  342 */     setTitle("Modulus v2.0");
/*      */ 
/*  344 */     this.keyboards = new ButtonGroup();
/*  345 */     this.keyboards.add((AbstractButton)this.prompt[1].get(0));
/*  346 */     this.keyboards.add((AbstractButton)this.prompt[1].get(1));
/*  347 */     this.keyboards.add((AbstractButton)this.prompt[1].get(2));
/*  348 */     this.keyboards.add((AbstractButton)this.prompt[1].get(3));
/*      */ 
/*  350 */     for (int i = 0; i < this.menus.length; i++) {
/*  351 */       this.mainbar.add(this.menus[i]);
/*  352 */       this.menus[i].addActionListener(this);
/*      */     }
/*  354 */     for (int i = 0; i < this.keys.length; i++) this.keys[i].addActionListener(this);
/*      */ 
/*  356 */     setLayout(new BorderLayout());
/*  357 */     add(this.panels[0], "Center");
/*      */ 
/*  359 */     add(this.panels[2], "West");
/*      */ 
/*  361 */     this.scroll = new JScrollPane(this.answerArea);
/*  362 */     this.scroll.setVerticalScrollBarPolicy(22);
/*  363 */     this.panels[2].add(this.scroll, "Center");
/*  364 */     this.panels[2].add(this.terminate, "South");
/*  365 */     this.terminate.addActionListener(this);
/*  366 */     this.panels[1].add(this.keys[10]);
/*  367 */     this.panels[1].add(this.answerField);
/*  368 */     this.panels[1].add(this.keys[9]);
/*  369 */     this.answerArea.setEditable(false);
/*      */ 
/*  371 */     ControlPanel.setComponent(this.answerArea);
/*      */ 
/*  373 */     this.numberPanel.add(this.keys[1], "Center");
/*  374 */     this.numberPanel.add(this.keys[4], "East");
/*      */ 
/*  376 */     this.panels[0].setLayout(new BorderLayout());
/*  377 */     this.panels[0].add(this.subPanels[0], "Center");
/*  378 */     this.panels[0].add(this.subPanels[3], "North");
/*      */ 
/*  380 */     this.subPanels[3].add(this.subPanels[1], "Center");
/*  381 */     this.subPanels[3].add(this.panels[1], "North");
/*      */ 
/*  383 */     this.panels[0].add(this.subPanels[2], "South");
/*      */ 
/*  385 */     this.subPanels[0].add(this.keys[2]);
/*  386 */     this.subPanels[0].add(this.numberPanel);
/*  387 */     this.subPanels[0].add(this.keys[0]);
/*      */ 
/*  389 */     this.subPanels[1].add(this.keys[3]);
/*  390 */     this.subPanels[1].add(this.keys[8]);
/*  391 */     this.subPanels[1].add(this.keys[5]);
/*      */ 
/*  394 */     this.subPanels[2].add(this.keys[6]);
/*  395 */     this.subPanels[2].add(this.keys[7]);
/*  396 */     this.subPanels[2].add(this.keys[11]);
/*      */ 
/*  399 */     setJMenuBar(this.mainbar);
/*  400 */     new DropTarget(this.answerArea, this);
/*  401 */     pack();
/*  402 */     setMinimumSize(getSize());
/*  403 */     setDefaultCloseOperation(3);
/*  404 */     this.answerField.addKeyListener(this);
/*      */ 
/*  407 */     SwingUtilities.updateComponentTreeUI(this);
/*      */ 
/*  409 */     applyProps();
/*  410 */     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
/*  411 */     int x = (dim.width - getSize().width) / 2;
/*  412 */     int y = (dim.height - getSize().height) / 2;
/*  413 */     setLocation(x, y);
/*      */ 
/*  415 */     setVisible(true);
/*  416 */     setIconImage(Toolkit.getDefaultToolkit().getImage("modulus_symbol.png"));
/*      */ 
/*  418 */     System.setOut(new ModulusOutputStream(this.answerArea, System.out, true));
/*  419 */     splash.setVisible(false);
/*  420 */     splash = null; }
/*      */ 
/*      */   public void addListeners(Container x)
/*      */   {
/*  424 */     Component[] arr = x.getComponents();
/*  425 */     for (int i = 0; i < arr.length; i++) {
/*  426 */       if (((arr[i] instanceof Container)) || (arr[i].getClass() == JMenu.class)) addListeners((Container)arr[i]);
/*  427 */       if (AbstractButton.class.isInstance(arr[i]))
/*  428 */         ((AbstractButton)arr[i]).addActionListener(this);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/*  439 */     ModulusThreads.addThread("Splash Screen", new CalculatorGUI.SplashThread(null));
/*      */     try {
/*  441 */       Thread.sleep(1000L);
/*      */     }
/*      */     catch (InterruptedException e) {
/*  444 */       e.printStackTrace();
/*      */     }
/*  446 */     ModulusThreads.addThread("Modulus Calculator", getThread());
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e)
/*      */   {
/*  463 */     if (Math.abs(System.currentTimeMillis() - this.sysTime) < 400L) return;
/*  464 */     this.sysTime = System.currentTimeMillis();
/*  465 */     e.getSource();
/*  466 */     this.keys[11].setVisible((((AbstractButton)this.menus[3].get(0)).isSelected()) || (
/*  467 */       (((AbstractButton)this.menus[4].get(2)).isSelected()) && (((AbstractButton)this.menus[5].get(7)).isSelected())));
/*  468 */     if (e.getSource() == this.menus[3].get(0)) {
/*  469 */       silentPrint("Shell Mode set to " + ((AbstractButton)this.menus[3].get(0)).isSelected() + "\n");
/*  470 */       silentPrint("------------------------------\n");
/*      */     }
/*  472 */     if (e.getSource() == this.menus[0].get(0)) {
/*  473 */       System.exit(1);
/*      */     }
/*  475 */     else if (e.getSource() == this.menus[0].get(1)) {
/*      */       try {
/*  477 */         ModulusFileChooser fc = new ModulusFileChooser(this.approvables[2], "", new MFileFilter[] { 
/*  478 */           new MFileFilter(new String[] { ".txt" }, "Text Files") });
/*      */ 
/*  480 */         fc.promptDialog("Save");
/*      */       }
/*      */       catch (Exception c) {
/*  483 */         c.printStackTrace();
/*  484 */         this.answerArea.setText("Error while parsing file");
/*      */       }
/*      */     }
/*      */ 
/*  488 */     if (e.getSource() == this.terminate) {
/*  489 */       for (Thread i : this.exec) if (i != null) i.interrupt();
/*  490 */       this.exec = new ArrayList();
/*  491 */       this.exec.add(null);
/*      */     }
/*  493 */     else if (e.getSource() == this.menus[4].get(0)) {
/*  494 */       Color newColor = JColorChooser.showDialog(this, "Choose a button color: ", new Color(0, 0, 255));
/*  495 */       changeColor(newColor);
/*      */     }
/*  497 */     else if (e.getSource() == this.keys[1].get(11)) {
/*  498 */       if (this.answerField.getText().length() > 0)
/*      */       {
/*  500 */         this.last.add(this.lastIndex + 1, this.answerField.getText());
/*  501 */         if (this.last.size() >= 100) this.last.remove(100);
/*  502 */         if (((AbstractButton)this.menus[3].get(0)).isSelected()) {
/*      */           try {
/*  504 */             silentPrint(">>>");
/*  505 */             ControlPanel.parseLine(this.answerField.getText());
/*  506 */             silentPrint(this.answerField.getText() + "\n");
/*      */           } catch (Exception localException1) {
/*      */           }
/*  509 */           this.answerField.setText("");
/*      */         }
/*      */         else {
/*  512 */           System.out.println(this.answerField.getText());
/*  513 */           new AppendingThread(this.answerField.getText(), this.lastIndex, this.ans, this.formatter).start();
/*  514 */           this.answerField.setText("");
/*      */         }
/*      */       }
/*      */     }
/*  518 */     else if (e.getSource() == this.menus[3].get(1)) {
/*  519 */       ModulusFileChooser fc = new ModulusFileChooser(this.approvables[0], this.props[2], new MFileFilter[] { 
/*  520 */         new MFileFilter(new String[] { ".calc", ".marc" }, "All Accepted Scripts (.calc, .marc)"), 
/*  521 */         new MFileFilter(new String[] { ".calc" }, "Modulus Calc Scripts (.calc)"), 
/*  522 */         new MFileFilter(new String[] { ".marc" }, "Marc Bytecode (.marc)") });
/*      */ 
/*  524 */       fc.promptDialog("Run");
/*      */     }
/*  526 */     else if (e.getSource() == this.menus[5].get(10)) {
/*  527 */       ModulusThreads.addThread("2DGraph", GraphFrame.getThread());
/*      */     }
/*  529 */     else if (e.getSource() == this.menus[5].get(11)) {
/*  530 */       ModulusThreads.addThread("3DGraph", M3DGraphWindow.getThread());
/*      */     }
/*  532 */     else if (e.getSource() == this.menus[3].get(3)) {
/*      */       try {
/*  534 */         ModulusFileChooser fc = new ModulusFileChooser(this.approvables[1], this.props[2], new MFileFilter[] { 
/*  535 */           new MFileFilter(new String[] { ".calc", ".marc", ".class" }, "All Accepted Libraries (.calc, .marc, .class)"), 
/*  536 */           new MFileFilter(new String[] { ".calc" }, "Modulus Calc Scripts (.calc)"), 
/*  537 */           new MFileFilter(new String[] { ".marc" }, "Marc Bytecode (.marc"), 
/*  538 */           new MFileFilter(new String[] { ".class" }, "Java Class (.class)") });
/*      */ 
/*  540 */         fc.promptDialog("Load");
/*      */       }
/*      */       catch (Exception c) {
/*  543 */         c.printStackTrace();
/*  544 */         this.answerArea.setText("Error while parsing file");
/*      */       }
/*      */ 
/*      */     }
/*  548 */     else if (e.getSource() == this.keys[10].get(0)) {
/*  549 */       this.answerArea.setText("");
/*      */     }
/*  551 */     else if (e.getSource() == this.keys[10].get(0)) {
/*  552 */       this.answerArea.setText("");
/*      */     }
/*  554 */     else if (e.getSource() == this.keys[9].get(0)) {
/*  555 */       if (this.answerField.getText().length() > 0)
/*  556 */         this.answerField.setText(this.answerField.getText().substring(0, this.answerField.getText().length() - 1));
/*      */     }
/*  558 */     else if (e.getSource() == this.keys[9].get(1)) {
/*  559 */       this.answerField.setText("");
/*      */     }
/*  561 */     else if (e.getSource() == this.menus[1].get(0)) {
/*  562 */       this.hold[0] = new JButton("OK");
/*  563 */       this.prompt[0] = new Prompt(this, "Change Base", new JComboBox[] { new BaseComboBox(9) }, this, this.hold[0]);
/*  564 */       this.prompt[0].setVisible(true);
/*      */     }
/*  566 */     else if (e.getSource() == this.menus[4].get(1)) {
/*  567 */       this.prompt[1].setVisible(true);
/*      */     }
/*  569 */     else if (e.getSource() == this.menus[1].get(1)) {
/*  570 */       this.hold[3] = new JButton("OK");
/*  571 */       this.prompt[3] = new Prompt(this, "Convert Bases", new JComponent[] { new BaseComboBox(9), new JTextField("", 7), new JLabel("To"), new BaseComboBox(9) }, this, this.hold[3]);
/*  572 */       this.prompt[3].setVisible(true);
/*      */     }
/*  574 */     else if (e.getSource() == this.menus[3].get(4)) {
/*  575 */       ControlPanel.reset();
/*      */ 
/*  577 */       FileChanger x = new FileChanger(this.functions.toString());
/*  578 */       x.setVisible(true);
/*      */     }
/*  581 */     else if (e.getSource() == this.menus[3].get(2)) {
/*  582 */       this.temp = new ActionList(ControlPanel.getFunctions(), this);
/*  583 */       JScrollPane pane = new JScrollPane((JComponent)this.temp, 22, 30);
/*  584 */       JPanel layout = new JPanel(new BorderLayout());
/*  585 */       pane.setPreferredSize(new Dimension(200, 100));
/*  586 */       layout.add(pane, "Center");
/*  587 */       this.prompt[2] = new Prompt(this, "Functions", new JComponent[] { layout, 
/*  588 */         new JTextField("", 10) }, this, this.hold[2]);
/*  589 */       this.prompt[2].setVisible(true);
/*      */     }
/*  591 */     else if (e.getSource() == this.menus[3].get(8)) {
/*  592 */       JScrollPane pane = new JScrollPane(new JList(ControlPanel.getCurrentHold().toArray()), 22, 30);
/*  593 */       JPanel layout = new JPanel(new BorderLayout());
/*  594 */       pane.setPreferredSize(new Dimension(75, 100));
/*  595 */       layout.add(pane, "Center");
/*  596 */       JButton x = new JButton("OK");
/*      */ 
/*  598 */       this.prompt[4] = new Prompt(this, "Hold Stack:", new JPanel[] { layout }, this, x);
/*  599 */       this.prompt[4].setVisible(true);
/*      */     }
/*  601 */     else if (e.getSource() == this.menus[3].get(7)) {
/*  602 */       JScrollPane[] pane = new JScrollPane[26];
/*  603 */       JPanel[] panes = new JPanel[26];
/*  604 */       JPanel layout = new JPanel(new FlowLayout());
/*      */ 
/*  606 */       for (int i = 0; i < pane.length; i++) {
/*  607 */         panes[i] = new JPanel(new BorderLayout());
/*  608 */         pane[i] = new JScrollPane(new JList(ControlPanel.getStacks()[i].toArray()));
/*  609 */         pane[i].setPreferredSize(new Dimension(50, 75));
/*  610 */         panes[i].add(new JLabel((char)(i + 65)), "North");
/*  611 */         panes[i].add(pane[i], "South");
/*  612 */         layout.add(panes[i]);
/*      */       }
/*  614 */       JScrollPane finalPane = new JScrollPane(layout);
/*  615 */       finalPane.setPreferredSize(new Dimension(350, 100));
/*  616 */       JButton x = new JButton("OK");
/*      */ 
/*  618 */       Prompt prompt = new Prompt(this, "Variable Stacks:", new JScrollPane[] { finalPane }, this, x);
/*  619 */       prompt.setVisible(true);
/*      */     }
/*  621 */     else if (e.getSource().equals(this.hold[0])) {
/*  622 */       Calculator.setBase(((JComboBox)this.prompt[0].get(0)).getSelectedIndex() + 1);
/*  623 */       if (Calculator.getBase() != 10) {
/*  624 */         this.menus[3].get(2).setEnabled(false);
/*  625 */         ((AbstractButton)this.menus[3].get(2)).setText("Function... ( Base needed to be 10)");
/*      */       }
/*      */       else {
/*  628 */         this.menus[3].get(2).setEnabled(true);
/*  629 */         ((AbstractButton)this.menus[3].get(2)).setText("Function...");
/*      */       }
/*      */     }
/*  632 */     else if (e.getSource().equals(this.hold[1])) {
/*  633 */       if (((AbstractButton)this.prompt[1].get(0)).isSelected())
/*  634 */         this.keys[0].reset("1234567890\\QWERTYUIOP\\ASDFGHJKL\\ZXCVBNM");
/*  635 */       else if (((AbstractButton)this.prompt[1].get(1)).isSelected())
/*  636 */         this.keys[0].reset("1234567890\\PYFGCRL\\AOEUIDHTNS\\QJKXBMWVZ");
/*  637 */       else if (((AbstractButton)this.prompt[1].get(2)).isSelected())
/*  638 */         this.keys[0].reset("0123456789\\ABCDEFGHIJ\\KLMNOPQRST\\UVWXYZ");
/*  639 */       else this.keys[0].reset(((JTextField)this.prompt[1].get(4)).getText());
/*  640 */       this.keys[0].addActionListener(this);
/*  641 */       this.props[0] = this.keys[0].getString();
/*  642 */       writeProperties();
/*      */     }
/*  644 */     else if (e.getSource().equals(this.menus[4].get(6))) {
/*  645 */       new FormatFrame(this, this.formatter).setVisible(true);
/*      */     }
/*  647 */     else if (e.getSource() == this.temp) {
/*  648 */       String text = ((ActionList)this.temp).getSelected().getText();
/*  649 */       if (!text.contains("(")) text = text + "(";
/*  650 */       ((JTextField)this.prompt[2].get(1)).setText(text);
/*      */     }
/*  652 */     else if (e.getSource().equals(this.hold[2])) {
/*      */       try {
/*  654 */         System.out.println(ScriptReader.preformFunction(
/*  655 */           ((JTextField)this.prompt[2].get(1)).getText(), 
/*  656 */           ControlPanel.getFiles()[((ActionList)this.temp).getSelectedIndex()].toString(), 
/*  657 */           new Object[] { ControlPanel.getClasses().get(((ActionList)this.temp).getSelectedIndex()) }));
/*      */       }
/*      */       catch (Exception c)
/*      */       {
/*  661 */         System.err.println(c);
/*  662 */         c.printStackTrace();
/*  663 */         if (!c.getMessage().equals("The Parameters do not match")) return; 
/*  664 */       }new AlertBox("The parameters of " + ((JTextField)this.prompt[2].get(1)).getText() + " cannot be applied, the parameters must match", "Parameter Exception");
/*      */     }
/*  668 */     else if (e.getSource().equals(this.hold[3])) {
/*      */       try {
/*  670 */         System.out.println(((JTextField)this.prompt[3].get(1)).getText() + " base " + (((JComboBox)this.prompt[3].get(0)).getSelectedIndex() + 1) + " to base " + (((JComboBox)this.prompt[3].get(3)).getSelectedIndex() + 1));
/*  671 */         System.out.println(this.answerArea.getText() + BaseConverter.convertFromDecimal(BaseConverter.convertToDecimal(((JTextField)this.prompt[3].get(1)).getText(), ((JComboBox)this.prompt[3].get(0)).getSelectedIndex() + 1), ((JComboBox)this.prompt[3].get(3)).getSelectedIndex() + 1));
/*      */       }
/*      */       catch (Exception c) {
/*  674 */         System.err.println(c);
/*  675 */         c.printStackTrace();
/*      */       }
/*      */     }
/*  678 */     else if (this.keys[8].contains(e.getSource())) {
/*  679 */       this.answerField.setText(this.answerField.getText() + this.topOpsArr[this.keys[8].indexOf(e.getSource())]);
/*      */     }
/*  681 */     else if (e.getSource() == this.menus[2].get(5)) {
/*  682 */       Calculator.setRadians(((AbstractButton)this.menus[2].get(5)).isSelected());
/*      */     }
/*  684 */     else if (e.getSource() == this.menus[5].get(3)) {
/*  685 */       changeSize("standard");
/*      */     }
/*  687 */     else if (e.getSource() == this.menus[5].get(4)) {
/*  688 */       changeSize("scientific");
/*      */     }
/*  690 */     else if (e.getSource() == this.menus[5].get(5)) {
/*  691 */       changeSize("boolean");
/*      */     }
/*  693 */     else if (e.getSource() == this.menus[5].get(6)) {
/*  694 */       changeSize("bitwise");
/*      */     }
/*  696 */     else if (e.getSource() == this.menus[5].get(7)) {
/*  697 */       changeSize("full");
/*      */     }
/*  699 */     else if (e.getSource() == this.menus[3].get(11)) {
/*  700 */       File file = null;
/*  701 */       JFileChooser fc = new JFileChooser(this.props[2]);
/*  702 */       int returnVal = fc.showOpenDialog(this);
/*      */ 
/*  704 */       if (returnVal == 0) {
/*  705 */         file = fc.getSelectedFile();
/*      */ 
/*  707 */         this.props[2] = file.getParent().toString();
/*  708 */         writeProperties();
/*      */       }
/*  710 */       ProcessBuilder run = new ProcessBuilder(new String[] { "calcp", file.toString() });
/*      */       try {
/*  712 */         run.start();
/*      */       }
/*      */       catch (IOException c) {
/*  715 */         this.answerArea.setText("Process could not start");
/*      */       }
/*      */ 
/*      */     }
/*  719 */     else if (e.getSource().getClass() == JMenuItem.class) {
/*  720 */       int i = indexOf(this.map[0], ((JMenuItem)e.getSource()).getText());
/*  721 */       if (i < 0) return;
/*  722 */       String txt = this.map[1][i];
/*  723 */       if (txt.endsWith("(")) this.answerField.setText(txt + this.answerField.getText() + ")");
/*  724 */       else if (txt.startsWith(")")) this.answerField.setText("(" + this.answerField.getText() + txt); else
/*  725 */         this.answerField.setText("(" + this.answerField.getText() + ")" + txt);
/*      */     }
/*  727 */     else if (e.getSource().getClass() == JButton.class) {
/*  728 */       this.answerField.setText(this.answerField.getText() + ((JButton)e.getSource()).getText());
/*      */     }
/*  730 */     else if (e.getSource() == this.menus[5].get(0)) {
/*  731 */       setAlwaysOnTop(((AbstractButton)this.menus[5].get(0)).isSelected());
/*      */     }
/*      */   }
/*      */ 
/*      */   public void changeSize(String size) {
/*  736 */     if (size.equals("standard")) {
/*  737 */       this.keys[0].setVisible(false);
/*  738 */       this.keys[1].setVisible(true);
/*  739 */       this.keys[2].setVisible(false);
/*  740 */       this.keys[3].setVisible(true);
/*  741 */       this.keys[4].setVisible(true);
/*  742 */       this.keys[5].setVisible(false);
/*  743 */       this.keys[6].setVisible(false);
/*  744 */       this.keys[7].setVisible(false);
/*  745 */       this.keys[8].setVisible(false);
/*  746 */       this.keys[9].setVisible(false);
/*  747 */       this.keys[10].setVisible(true);
/*  748 */       this.keys[11].setVisible(false);
/*  749 */       setMinimumSize(new Dimension(0, 0));
/*  750 */       this.answerField.setColumns(17);
/*  751 */       this.answerArea.setRows(9);
/*  752 */       this.answerArea.setColumns(17);
/*  753 */       pack();
/*  754 */       setMinimumSize(getSize());
/*  755 */       ((AbstractButton)this.menus[5].get(3)).setSelected(true);
/*      */     }
/*  757 */     else if (size.equals("scientific")) {
/*  758 */       this.keys[0].setVisible(false);
/*  759 */       this.keys[1].setVisible(true);
/*  760 */       this.keys[2].setVisible(true);
/*  761 */       this.keys[3].setVisible(true);
/*  762 */       this.keys[4].setVisible(true);
/*  763 */       this.keys[5].setVisible(false);
/*  764 */       this.keys[6].setVisible(false);
/*  765 */       this.keys[7].setVisible(false);
/*  766 */       this.keys[8].setVisible(true);
/*  767 */       this.keys[9].setVisible(false);
/*  768 */       this.keys[10].setVisible(true);
/*  769 */       this.keys[11].setVisible(false);
/*  770 */       setMinimumSize(new Dimension(0, 0));
/*  771 */       this.answerField.setColumns(20);
/*  772 */       this.answerArea.setRows(9);
/*  773 */       this.answerArea.setColumns(20);
/*  774 */       pack();
/*  775 */       setMinimumSize(getSize());
/*  776 */       ((AbstractButton)this.menus[5].get(4)).setSelected(true);
/*      */     }
/*  778 */     else if (size.equals("boolean")) {
/*  779 */       this.keys[0].setVisible(false);
/*  780 */       this.keys[1].setVisible(true);
/*  781 */       this.keys[2].setVisible(true);
/*  782 */       this.keys[3].setVisible(true);
/*  783 */       this.keys[4].setVisible(true);
/*  784 */       this.keys[5].setVisible(false);
/*  785 */       this.keys[6].setVisible(false);
/*  786 */       this.keys[7].setVisible(true);
/*  787 */       this.keys[8].setVisible(true);
/*  788 */       this.keys[9].setVisible(false);
/*  789 */       this.keys[10].setVisible(true);
/*  790 */       this.keys[11].setVisible(false);
/*  791 */       setMinimumSize(new Dimension(0, 0));
/*  792 */       this.answerField.setColumns(20);
/*  793 */       this.answerArea.setRows(9);
/*  794 */       this.answerArea.setColumns(20);
/*  795 */       pack();
/*  796 */       setMinimumSize(getSize());
/*  797 */       ((AbstractButton)this.menus[5].get(5)).setSelected(true);
/*      */     }
/*  799 */     else if (size.equals("bitwise")) {
/*  800 */       this.keys[0].setVisible(false);
/*  801 */       this.keys[1].setVisible(true);
/*  802 */       this.keys[2].setVisible(true);
/*  803 */       this.keys[3].setVisible(true);
/*  804 */       this.keys[4].setVisible(true);
/*  805 */       this.keys[5].setVisible(true);
/*  806 */       this.keys[6].setVisible(false);
/*  807 */       this.keys[7].setVisible(true);
/*  808 */       this.keys[8].setVisible(true);
/*  809 */       this.keys[9].setVisible(false);
/*  810 */       this.keys[10].setVisible(true);
/*  811 */       this.keys[11].setVisible(false);
/*  812 */       setMinimumSize(new Dimension(0, 0));
/*  813 */       this.answerField.setColumns(20);
/*  814 */       this.answerArea.setRows(9);
/*  815 */       this.answerArea.setColumns(20);
/*  816 */       pack();
/*  817 */       setMinimumSize(getSize());
/*  818 */       ((AbstractButton)this.menus[5].get(6)).setSelected(true);
/*      */     }
/*  820 */     else if (size.equals("full")) {
/*  821 */       this.keys[0].setVisible(true);
/*  822 */       this.keys[1].setVisible(true);
/*  823 */       this.keys[2].setVisible(true);
/*  824 */       this.keys[3].setVisible(true);
/*  825 */       this.keys[4].setVisible(true);
/*  826 */       this.keys[5].setVisible(true);
/*  827 */       this.keys[6].setVisible(true);
/*  828 */       this.keys[7].setVisible(true);
/*  829 */       this.keys[8].setVisible(true);
/*  830 */       this.keys[9].setVisible(true);
/*  831 */       this.keys[10].setVisible(true);
/*  832 */       this.keys[11].setVisible(true);
/*  833 */       setMinimumSize(new Dimension(0, 0));
/*  834 */       this.answerField.setColumns(35);
/*  835 */       this.answerArea.setRows(16);
/*  836 */       this.answerArea.setColumns(25);
/*  837 */       pack();
/*  838 */       setMinimumSize(getSize());
/*  839 */       ((AbstractButton)this.menus[5].get(7)).setSelected(true);
/*      */     }
/*      */ 
/*  842 */     this.props[4] = size;
/*      */   }
/*      */   public void changeColor(Color newColor) {
/*  845 */     this.props[1] = (BaseConverter.convertFromDecimal(newColor.getRed(), 16) + BaseConverter.convertFromDecimal(newColor.getGreen(), 16) + BaseConverter.convertFromDecimal(newColor.getBlue(), 16));
/*  846 */     for (int i = 0; i < this.keys.length; i++) this.keys[i].setColor(newColor);
/*  847 */     writeProperties();
/*      */   }
/*      */   public String fill(int x) {
/*  850 */     String ret = "";
/*  851 */     for (int i = 0; i < x; i++) ret = ret + " ";
/*  852 */     return ret;
/*      */   }
/*      */   public boolean contains(Container x, Object o) {
/*  855 */     Component[] comps = x.getComponents();
/*  856 */     for (Component i : comps) if (i == o) return true;
/*  857 */     return false;
/*      */   }
/*      */   public boolean keyboardsContains(Object o) {
/*  860 */     for (int i = 0; i < this.keys.length; i++) if (this.keys[i].contains(o)) return true;
/*  861 */     return false;
/*      */   }
/*      */   public void load() {
/*  864 */     for (File x : this.functions.listFiles())
/*      */       try {
/*  866 */         ControlPanel.load(x.toString());
/*      */       } catch (Exception localException) {
/*      */       }
/*      */   }
/*      */ 
/*      */   private int indexOf(Object[] args, Object o) {
/*  872 */     for (int i = 0; i < args.length; i++) if (args[i].equals(o)) return i;
/*  873 */     return -1;
/*      */   }
/*      */   public void readProperties() {
/*  876 */     this.props = new PropertiesReader("./properties/properties.prop").readProperties();
/*  877 */     applyProps();
/*      */   }
/*      */   public void writeProperties() {
/*  880 */     new PropertiesReader("./properties/properties.prop").writeProperties(this.props);
/*      */   }
/*      */   public void applyProps() {
/*  883 */     if (!this.keys[0].getString().equals(
/*  884 */       this.props[0]))
/*      */     {
/*  886 */       this.keys[0].reset(this.props[0]);
/*      */     }
/*  888 */     changeSize(this.props[4]);
/*  889 */     changeColor(new Color((int)BaseConverter.convertToDecimal(this.props[1], 16)));
/*  890 */     setAlwaysOnTop(Boolean.parseBoolean(this.props[5]));
/*      */   }
/*      */ 
/*      */   public void dragEnter(DropTargetDragEvent dtde)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void dragExit(DropTargetEvent dte)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void dragOver(DropTargetDragEvent dtde)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void dropActionChanged(DropTargetDragEvent dtde) {
/*      */   }
/*      */ 
/*      */   public void drop(DropTargetDropEvent dtde) {
/*      */     try {
/*  910 */       Transferable tr = dtde.getTransferable();
/*  911 */       DataFlavor[] flavors = tr.getTransferDataFlavors();
/*  912 */       for (int i = 0; i < flavors.length; i++)
/*  913 */         if (flavors[i].isFlavorJavaFileListType()) {
/*  914 */           dtde.acceptDrop(3);
/*      */ 
/*  916 */           List list = (List)tr.getTransferData(flavors[i]);
/*  917 */           for (int j = 0; j < list.size(); j++) {
/*  918 */             File file = new File(list.get(j).toString());
/*      */             Thread run;
/*  920 */             this.exec.set(0, run = new Thread(new CalculatorGUI.Run(file)));
/*  921 */             run.start();
/*  922 */             this.props[2] = file.getParent().toString();
/*  923 */             writeProperties();
/*      */           }
/*  925 */           dtde.dropComplete(true);
/*  926 */           return;
/*      */         }
/*      */     } catch (Exception e) {
/*  929 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void keyTyped(KeyEvent e) {
/*      */   }
/*      */ 
/*      */   public void keyPressed(KeyEvent e) {
/*  937 */     if (e.getKeyCode() == 17) this.controlHeld = true;
/*  938 */     if (e.getKeyCode() == 16) this.shiftHeld = true;
/*      */   }
/*      */ 
/*      */   public void keyReleased(KeyEvent e)
/*      */   {
/*  944 */     if (e.getKeyCode() == 17) this.controlHeld = false;
/*  945 */     if (e.getKeyCode() == 16) this.shiftHeld = false;
/*  946 */     if (e.getSource() == this.answerField) {
/*  947 */       if ((((AbstractButton)this.menus[4].get(3)).isSelected()) && (
/*  948 */         (contains((char)e.getKeyCode(), ",./\\[/*-+".toCharArray())) || ((this.shiftHeld) && (contains((char)e.getKeyCode(), "56789;,./\\=".toCharArray()))))) {
/*      */         String app;
/*      */         try {
/*  951 */           app = (String)this.ans.get(this.lastIndex + 1);
/*      */         }
/*      */         catch (IndexOutOfBoundsException c)
/*      */         {
/*      */           String app;
/*  954 */           app = (String)this.ans.get(this.lastIndex);
/*      */         }
/*  956 */         this.answerField.setText(this.answerField.getText() + app);
/*  957 */         this.answerField.setCaretPosition(this.answerField.getText().length() - app.length());
/*  958 */         this.answerField.moveCaretPosition(this.answerField.getText().length());
/*      */       }
/*      */ 
/*  961 */       if (e.getKeyCode() == 10) {
/*  962 */         actionPerformed(new ActionEvent(this.keys[1].get(11), 0, ""));
/*      */       }
/*  964 */       if (e.getKeyCode() == 38) {
/*  965 */         if (this.controlHeld) {
/*  966 */           if (this.lastIndex < this.ans.size() - 1) {
/*  967 */             String temp = this.answerField.getText();
/*  968 */             if (this.answerField.getSelectionStart() >= 0)
/*  969 */               temp = temp.substring(0, this.answerField.getSelectionStart()) + temp.substring(this.answerField.getSelectionEnd());
/*  970 */             temp = temp.substring(0, this.answerField.getCaretPosition()) + (String)this.ans.get(++this.lastIndex) + temp.substring(this.answerField.getCaretPosition());
/*  971 */             this.answerField.setText(temp);
/*      */           }
/*  973 */           return;
/*      */         }
/*      */ 
/*  977 */         if (this.lastIndex < this.last.size() - 1)
/*  978 */           this.answerField.setText((String)this.last.get(++this.lastIndex));
/*      */       }
/*  980 */       if (e.getKeyCode() == 40) {
/*  981 */         if (this.controlHeld) {
/*  982 */           if (this.lastIndex > 0) {
/*  983 */             String temp = this.answerField.getText();
/*  984 */             if (this.answerField.getSelectionStart() >= 0)
/*  985 */               temp = temp.substring(0, this.answerField.getSelectionStart()) + temp.substring(this.answerField.getSelectionEnd());
/*  986 */             temp = temp.substring(0, this.answerField.getCaretPosition()) + (String)this.ans.get(--this.lastIndex) + temp.substring(this.answerField.getCaretPosition());
/*  987 */             this.answerField.setText(temp);
/*      */           }
/*  989 */           return;
/*      */         }
/*      */ 
/*  993 */         if (this.lastIndex > 0)
/*  994 */           this.answerField.setText((String)this.last.get(--this.lastIndex)); 
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  999 */   public static void silentPrint(String prnt) { if ((System.out instanceof ModulusOutputStream)) ((ModulusOutputStream)System.out).silentPrint(prnt);  } 
/*      */   public DecimalFormatter getFormatter()
/*      */   {
/* 1002 */     return this.formatter;
/*      */   }
/*      */   public void setDecimalFormatter(DecimalFormatter set) {
/* 1005 */     this.formatter = set;
/*      */   }
/*      */   public static CalculatorGUI getCurrentInstance() {
/* 1008 */     return currentInstance;
/*      */   }
/*      */   private boolean contains(char x, char[] cs) {
/* 1011 */     for (char y : cs) if (y == x) return true;
/* 1012 */     return false;
/*      */   }
/*      */ 
/*      */   private void removeSelected()
/*      */   {
/* 1027 */     for (Menu m : this.menus) m.setSelected(false); 
/*      */   }
/* 1029 */   public void mouseClicked(MouseEvent e) { removeSelected(); } 
/* 1030 */   public void mousePressed(MouseEvent e) { removeSelected(); } 
/* 1031 */   public void mouseReleased(MouseEvent e) { removeSelected(); } 
/*      */   public void mouseEntered(MouseEvent e) {
/*      */   }
/*      */   public void mouseExited(MouseEvent e) {  } 
/* 1035 */   public static Thread getThread() { return new CalculatorGUI.GuiThread(null); }
/*      */ 
/*      */   private static class GuiThread extends Thread {
/*      */     public void run() {
/* 1039 */       new CalculatorGUI().setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private class Run
/*      */     implements Runnable
/*      */   {
/*      */     File f;
/*      */ 
/*      */     public Run(File file)
/*      */     {
/* 1017 */       this.f = file;
/*      */     }
/*      */     public void run() {
/*      */       try {
/* 1021 */         ScriptReader.runScript(this.f.toString());
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class SplashScreen extends JWindow
/*      */   {
/*      */     private static final long serialVersionUID = 4883331298436699474L;
/*      */     private BufferedImage image;
/*      */ 
/*      */     public SplashScreen()
/*      */     {
/*      */       try
/*      */       {
/* 1051 */         this.image = ImageIO.read(new File(new File(".").getCanonicalFile(), "splash-screen.png"));
/*      */       }
/*      */       catch (IOException e) {
/* 1054 */         e.printStackTrace();
/*      */       }
/* 1056 */       setSize(this.image.getWidth(), this.image.getHeight());
/* 1057 */       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
/* 1058 */       int x = (dim.width - getSize().width) / 2;
/* 1059 */       int y = (dim.height - getSize().height) / 2;
/* 1060 */       setLocation(x, y);
/*      */     }
/*      */     public void paint(Graphics g) {
/* 1063 */       g.drawImage(this.image, 0, 0, null);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class SplashThread extends Thread {
/* 1068 */     public void run() { CalculatorGUI.splash = new CalculatorGUI.SplashScreen();
/* 1069 */       CalculatorGUI.splash.setAlwaysOnTop(true);
/* 1070 */       CalculatorGUI.splash.setVisible(true);
/*      */     }
/*      */   }
/*      */ }

/* Location:           Modulus.jar
 * Qualified Name:     CalculatorGUI
 * JD-Core Version:    0.6.2
 */