/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class Keyboard extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   public static final String QWERTY = "1234567890\\QWERTYUIOP\\ASDFGHJKL\\ZXCVBNM";
/*     */   public static final String ALPHABETICAL = "0123456789\\ABCDEFGHIJ\\KLMNOPQRST\\UVWXYZ";
/*     */   public static final String DVORAK = "1234567890\\PYFGCRL\\AOEUIDHTNS\\QJKXBMWVZ";
/*     */   public static final String NUMBER_PAD = "789\\456\\123\\0";
/*  21 */   private char lastTyped = ' ';
/*     */   private String[][] characters;
/*     */   private ArrayList<JButton> keys;
/*     */   private ArrayList<ActionListener> listenersList;
/*     */   GridLayout grid;
/*     */   JPanel[] centerPanels;
/*     */   JPanel[] gridPanels;
/*  28 */   private String str = "";
/*     */ 
/*     */   public Keyboard(String layout) {
/*  31 */     this(toTwoDimenstions(layout), 25, 30, null);
/*  32 */     this.str = layout;
/*     */   }
/*     */   public Keyboard(String layout, ActionListener listener) {
/*  35 */     this(layout);
/*  36 */     this.str = layout;
/*  37 */     this.listenersList.add(listener);
/*     */   }
/*     */   public Keyboard() {
/*  40 */     this("1234567890\\QWERTYUIOP\\ASDFGHJKL\\ZXCVBNM");
/*     */   }
/*     */   public Keyboard(String[][] layout, int height, int width, Color color) {
/*  43 */     this.characters = layout;
/*  44 */     this.listenersList = new ArrayList();
/*  45 */     int max = 0;
/*  46 */     for (String[] i : this.characters) if (i.length > max) max = i.length;
/*  47 */     int added = 0;
/*  48 */     for (int i = 0; i < this.characters.length; i++) {
/*  49 */       added += this.characters[i].length;
/*     */     }
/*  51 */     this.keys = new ArrayList();
/*  52 */     this.grid = new GridLayout(this.characters.length, 1);
/*  53 */     setLayout(this.grid);
/*  54 */     setSize(max * 30 + 5, this.characters.length * 30 + 5);
/*  55 */     this.gridPanels = new JPanel[this.characters.length];
/*  56 */     this.centerPanels = new JPanel[this.characters.length];
/*  57 */     int i = 0; for (int k = 0; i < this.gridPanels.length; k++) {
/*  58 */       this.gridPanels[i] = new JPanel(new FlowLayout());
/*  59 */       add(this.gridPanels[i]);
/*  60 */       this.centerPanels[i] = new JPanel(new GridLayout(1, this.characters[i].length));
/*  61 */       this.gridPanels[i].add(this.centerPanels[i]);
/*  62 */       for (int j = 0; j < this.characters[i].length; k++) {
/*  63 */         JButton temp = new JButton(this.characters[i][j]);
/*  64 */         temp.setFont(new Font("Times New Roman", 0, 12));
/*  65 */         temp.addActionListener(this);
/*  66 */         this.centerPanels[i].setPreferredSize(new Dimension(this.characters[i].length * 50, 50));
/*  67 */         temp.setMargin(new Insets(0, 0, 0, 0));
/*  68 */         this.keys.add(temp);
/*  69 */         this.centerPanels[i].add(temp);
/*  70 */         temp.setPreferredSize(new Dimension(width, height));
/*  71 */         temp.setBackground(color);
/*     */ 
/*  62 */         j++;
/*     */       }
/*     */ 
/*  73 */       this.centerPanels[i].setPreferredSize(new Dimension(this.characters[i].length * width, height));
/*     */ 
/*  57 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void fireActionPerformed(ActionEvent e)
/*     */   {
/*  79 */     for (int i = 0; i < this.listenersList.size(); i++) {
/*  80 */       ((ActionListener)this.listenersList.get(i)).actionPerformed(e);
/*  81 */       e.setSource(this);
/*  82 */       ((ActionListener)this.listenersList.get(i)).actionPerformed(e);
/*     */     }
/*     */   }
/*     */ 
/*  86 */   public JButton get(int index) { return (JButton)this.keys.get(index); }
/*     */ 
/*     */   public JButton getByName(String name) {
/*  89 */     for (int i = 0; i < this.keys.size(); i++) if (((JButton)this.keys.get(i)).getText().equals(name)) return (JButton)this.keys.get(i);
/*  90 */     return null;
/*     */   }
/*     */   public JButton getByObject(Object o) {
/*  93 */     for (int i = 0; i < this.keys.size(); i++) if (this.keys.get(i) == o) return (JButton)this.keys.get(i);
/*  94 */     return null;
/*     */   }
/*     */   public boolean contains(Object o) {
/*  97 */     return getByObject(o) != null;
/*     */   }
/*     */   public void actionPerformed(ActionEvent e) {
/* 100 */     fireActionPerformed(e);
/*     */   }
/* 102 */   public void addActionListener(ActionListener listen) { this.listenersList.add(listen); } 
/* 103 */   public char lastTyped() { return this.lastTyped; } 
/*     */   public void setColor(Color color) {
/* 105 */     for (int i = 0; i < this.keys.size(); i++)
/* 106 */       ((JButton)this.keys.get(i)).setBackground(color);
/*     */   }
/*     */ 
/*     */   public void setText(int index, String text) {
/* 110 */     ((JButton)this.keys.get(index)).setText(text);
/*     */   }
/*     */   private static String[][] toTwoDimenstions(String layout) {
/* 113 */     String[] x = layout.split("\\\\");
/* 114 */     int max = 0;
/* 115 */     for (String i : x) if (i.length() > max) max = i.length();
/* 116 */     String[][] lay = new String[x.length][max];
/* 117 */     for (int i = 0; i < x.length; i++) {
/* 118 */       lay[i] = new String[x[i].length()];
/* 119 */       for (int j = 0; j < lay[i].length; j++)
/*     */       {
/* 121 */         lay[i][j] = x[i].charAt(j);
/*     */       }
/*     */     }
/* 124 */     return lay;
/*     */   }
/*     */ 
/*     */   public void reset(String layout) {
/* 128 */     reset(toTwoDimenstions(layout), 25, 30, null);
/* 129 */     this.str = layout;
/*     */   }
/*     */   public void reset(String[][] layout, int height, int width, Color color) {
/* 132 */     removeAll();
/* 133 */     this.characters = layout;
/* 134 */     this.listenersList = new ArrayList();
/* 135 */     int max = 0;
/* 136 */     for (String[] i : this.characters) if (i.length > max) max = i.length;
/* 137 */     int added = 0;
/* 138 */     for (int i = 0; i < this.characters.length; i++) {
/* 139 */       added += this.characters[i].length;
/*     */     }
/* 141 */     this.keys = new ArrayList();
/* 142 */     this.grid = new GridLayout(this.characters.length, 1);
/* 143 */     setLayout(this.grid);
/* 144 */     setSize(max * 30 + 5, this.characters.length * 30 + 5);
/* 145 */     this.gridPanels = new JPanel[this.characters.length];
/* 146 */     this.centerPanels = new JPanel[this.characters.length];
/* 147 */     int i = 0; for (int k = 0; i < this.gridPanels.length; k++) {
/* 148 */       this.gridPanels[i] = new JPanel(new FlowLayout());
/* 149 */       add(this.gridPanels[i]);
/* 150 */       this.centerPanels[i] = new JPanel(new GridLayout(1, this.characters[i].length));
/* 151 */       this.gridPanels[i].add(this.centerPanels[i]);
/* 152 */       for (int j = 0; j < this.characters[i].length; k++) {
/* 153 */         JButton temp = new JButton(this.characters[i][j]);
/* 154 */         temp.addActionListener(this);
/* 155 */         this.centerPanels[i].setPreferredSize(new Dimension(this.characters[i].length * 50, 50));
/* 156 */         temp.setMargin(new Insets(0, 0, 0, 0));
/* 157 */         this.keys.add(temp);
/* 158 */         this.centerPanels[i].add(temp);
/* 159 */         temp.setPreferredSize(new Dimension(width, height));
/* 160 */         temp.setBackground(color);
/*     */ 
/* 152 */         j++;
/*     */       }
/*     */ 
/* 162 */       this.centerPanels[i].setPreferredSize(new Dimension(this.characters[i].length * width, height));
/*     */ 
/* 147 */       i++;
/*     */     }
/*     */ 
/* 164 */     updateUI();
/*     */   }
/*     */   public int indexOf(Object o) {
/* 167 */     for (int i = 0; i < this.keys.size(); i++) if (this.keys.get(i) == o) return i;
/* 168 */     return -1;
/*     */   }
/* 170 */   public String getString() { return this.str; }
/*     */ 
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     Keyboard
 * JD-Core Version:    0.6.2
 */