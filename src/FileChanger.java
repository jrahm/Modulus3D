/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ 
/*     */ public class FileChanger extends JFrame
/*     */   implements ActionListener, FileFilter, FilenameFilter
/*     */ {
/*     */   private File parent2;
/*     */   private String file1;
/*     */   private String file2;
/*     */   private Object[] list1;
/*     */   private Object[] list2;
/*     */   private JList jList1;
/*     */   private JList jList2;
/*  26 */   private JButton move = new JButton("Move");
/*  27 */   private JButton ok = new JButton("OK");
/*     */   private JScrollPane pane1;
/*     */   private JScrollPane pane2;
/*  30 */   private JButton clr1 = new JButton("Clear");
/*  31 */   private JButton clr2 = new JButton("Clear");
/*     */ 
/*  33 */   public FileChanger(String filename, String[] lst1, String[] lst2) { this.file2 = filename;
/*  34 */     this.parent2 = new File(filename);
/*     */ 
/*  36 */     if (!this.parent2.exists()) this.parent2.mkdirs();
/*     */ 
/*  38 */     this.list1 = lst1;
/*  39 */     this.list2 = lst2;
/*     */ 
/*  41 */     this.jList1 = new JList(this.list1);
/*  42 */     this.jList2 = new JList(this.list2);
/*     */ 
/*  44 */     this.pane1 = new JScrollPane(this.jList1, 22, 30);
/*  45 */     this.pane2 = new JScrollPane(this.jList2, 22, 30);
/*     */ 
/*  50 */     this.pane1.setPreferredSize(new Dimension(130, 260));
/*  51 */     this.pane2.setPreferredSize(new Dimension(130, 260));
/*  52 */     JPanel sto1 = new JPanel(new BorderLayout());
/*  53 */     JPanel sto2 = new JPanel(new BorderLayout());
/*  54 */     this.ok.addActionListener(this);
/*  55 */     this.move.addActionListener(this);
/*  56 */     this.clr1.addActionListener(this);
/*  57 */     this.clr2.addActionListener(this);
/*     */ 
/*  59 */     setLayout(new BorderLayout());
/*  60 */     JPanel sto = new JPanel(new FlowLayout());
/*  61 */     add(new JLabel("Add/Remove Functions"), "North");
/*  62 */     add(sto, "Center");
/*     */ 
/*  64 */     sto1.add(new JLabel("Unused Libraries"), "North");
/*  65 */     sto2.add(new JLabel("Current Libraries"), "North");
/*  66 */     sto1.add(this.pane1, "Center");
/*  67 */     sto2.add(this.pane2, "Center");
/*  68 */     sto1.add(this.clr1, "South");
/*  69 */     sto2.add(this.clr2, "South");
/*     */ 
/*  71 */     sto.add(sto1);
/*  72 */     sto.add(this.move);
/*  73 */     sto.add(sto2);
/*  74 */     JPanel tmp = new JPanel();
/*  75 */     tmp.add(this.ok);
/*  76 */     add(tmp, "South");
/*  77 */     pack();
/*  78 */     setIconImage(Toolkit.getDefaultToolkit().getImage("modulus_symbol.png"));
/*  79 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   public FileChanger(String parent)
/*     */   {
/*  85 */     String filename = parent;
/*  86 */     this.file2 = filename;
/*  87 */     String[] lst1 = subtract(
/*  88 */       new File(parent).list(this), 
/*  89 */       pickup(parent));
/*     */ 
/*  91 */     String[] lst2 = pickup(parent);
/*  92 */     this.parent2 = new File(filename);
/*     */ 
/*  94 */     if (!this.parent2.exists()) this.parent2.mkdirs();
/*     */ 
/*  96 */     this.list1 = lst1;
/*  97 */     this.list2 = lst2;
/*     */ 
/*  99 */     this.jList1 = new JList(this.list1);
/* 100 */     this.jList2 = new JList(this.list2);
/*     */ 
/* 102 */     this.pane1 = new JScrollPane(this.jList1, 22, 30);
/* 103 */     this.pane2 = new JScrollPane(this.jList2, 22, 30);
/*     */ 
/* 108 */     this.pane1.setPreferredSize(new Dimension(130, 260));
/* 109 */     this.pane2.setPreferredSize(new Dimension(130, 260));
/* 110 */     JPanel sto1 = new JPanel(new BorderLayout());
/* 111 */     JPanel sto2 = new JPanel(new BorderLayout());
/* 112 */     this.ok.addActionListener(this);
/* 113 */     this.move.addActionListener(this);
/* 114 */     this.clr1.addActionListener(this);
/* 115 */     this.clr2.addActionListener(this);
/*     */ 
/* 117 */     setLayout(new BorderLayout());
/* 118 */     JPanel sto = new JPanel(new FlowLayout());
/* 119 */     add(new JLabel("Add/Remove Functions"), "North");
/* 120 */     add(sto, "Center");
/*     */ 
/* 122 */     sto1.add(new JLabel("Unused Libraries"), "North");
/* 123 */     sto2.add(new JLabel("Current Libraries"), "North");
/* 124 */     sto1.add(this.pane1, "Center");
/* 125 */     sto2.add(this.pane2, "Center");
/* 126 */     sto1.add(this.clr1, "South");
/* 127 */     sto2.add(this.clr2, "South");
/*     */ 
/* 129 */     sto.add(sto1);
/* 130 */     sto.add(this.move);
/* 131 */     sto.add(sto2);
/* 132 */     JPanel tmp = new JPanel();
/* 133 */     tmp.add(this.ok);
/* 134 */     add(tmp, "South");
/* 135 */     pack();
/*     */   }
/*     */   public void setList1(String[] arg) {
/* 138 */     this.list1 = arg;
/* 139 */     update();
/*     */   }
/*     */   public void setList2(String[] arg) {
/* 142 */     this.list1 = arg;
/* 143 */     update();
/*     */   }
/*     */ 
/*     */   public void update() {
/* 147 */     ArrayList lst1 = asList(this.list1);
/* 148 */     ArrayList lst2 = asList(this.list2);
/*     */ 
/* 150 */     for (int i : this.jList1.getSelectedIndices()) {
/* 151 */       lst2.add((String)lst1.get(i));
/*     */     }
/* 153 */     for (int i : this.jList2.getSelectedIndices()) {
/* 154 */       lst1.add((String)lst2.get(i));
/*     */     }
/* 156 */     for (int i : this.jList1.getSelectedIndices()) {
/* 157 */       lst1.set(i, "<removed>");
/*     */     }
/* 159 */     for (int i : this.jList2.getSelectedIndices()) {
/* 160 */       lst2.set(i, "<removed>");
/*     */     }
/* 162 */     for (int i = 0; (i < lst2.size()) || (i < lst1.size()); i++) {
/* 163 */       if ((i < lst2.size()) && (((String)lst2.get(i)).equals("<removed>"))) lst2.remove(i);
/* 164 */       if ((i < lst1.size()) && (((String)lst1.get(i)).equals("<removed>"))) lst1.remove(i);
/*     */     }
/* 166 */     this.list1 = toStringArray(lst1);
/* 167 */     this.list2 = toStringArray(lst2);
/* 168 */     this.jList1.setListData(this.list1);
/* 169 */     this.jList2.setListData(this.list2);
/*     */   }
/*     */   public void load() {
/* 172 */     write();
/* 173 */     for (Object str : this.list2)
/*     */       try {
/* 175 */         ControlPanel.load(new File(".").getCanonicalPath().toString() + "/functions/" + str.toString());
/*     */       } catch (Exception e) {
/* 177 */         e.printStackTrace();
/*     */       }
/* 179 */     setVisible(false);
/*     */   }
/*     */   public void actionPerformed(ActionEvent o) {
/* 182 */     if (o.getSource() == this.ok) {
/* 183 */       load();
/*     */     }
/* 185 */     if (o.getSource() == this.move)
/*     */     {
/* 194 */       update();
/*     */     }
/* 196 */     if (o.getSource() == this.clr1) this.jList1.clearSelection();
/* 197 */     if (o.getSource() == this.clr2) this.jList2.clearSelection(); 
/*     */   }
/*     */ 
/* 200 */   public boolean accept(File dir, String name) { return accept(new File(dir, name)); }
/*     */ 
/*     */ 
/*     */   public boolean accept(File pathname)
/*     */   {
/* 205 */     return ((pathname.getName().endsWith(".calc")) || (pathname.getName().endsWith(".marc")) || (pathname.getName().endsWith(".class"))) && 
/* 205 */       (!pathname.isDirectory());
/*     */   }
/*     */   public void write() {
/*     */     try {
/* 209 */       BufferedWriter out = new BufferedWriter(new FileWriter(new File(this.parent2, "functionsLOD.prop")));
/* 210 */       for (Object i : this.list2) {
/* 211 */         out.write("lod " + i + "\n");
/*     */       }
/* 213 */       out.close();
/*     */     } catch (IOException localIOException) {
/*     */     }
/*     */   }
/*     */ 
/*     */   private ArrayList<String> asList(Object[] x) {
/* 219 */     ArrayList ret = new ArrayList();
/* 220 */     for (Object i : x) ret.add(i.toString());
/* 221 */     return ret;
/*     */   }
/*     */   public static String[] toStringArray(ArrayList<String> x) {
/* 224 */     String[] ret = new String[x.size()];
/* 225 */     for (int i = 0; i < x.size(); i++) {
/* 226 */       ret[i] = ((String)x.get(i));
/*     */     }
/* 228 */     return ret;
/*     */   }
/*     */   private static String[] pickup(String parent) {
/*     */     try {
/* 232 */       File par = new File(parent);
/* 233 */       File sub = new File(par, "functionsLOD.prop");
/* 234 */       FileInputStream fstream = new FileInputStream(sub);
/* 235 */       DataInputStream in = new DataInputStream(fstream);
/* 236 */       BufferedReader br = new BufferedReader(new InputStreamReader(in));
/* 237 */       ArrayList x = new ArrayList();
/*     */       String str;
/* 239 */       while ((str = br.readLine()) != null)
/*     */       {
/* 240 */         String str;
/* 240 */         if (str.startsWith("lod")) x.add(str.substring(3).trim());
/*     */       }
/* 242 */       return toStringArray(x);
/*     */     }
/*     */     catch (Exception e) {
/* 245 */       AlertBox.throwError(e.getMessage());
/* 246 */       e.printStackTrace();
/* 247 */     }return null;
/*     */   }
/*     */   public static String[] subtract(String[] arg1, String[] arg2) {
/* 250 */     ArrayList ret = new ArrayList();
/* 251 */     String[] arrayOfString1 = arg1; int j = arg1.length; for (int i = 0; i < j; i++) { String i = arrayOfString1[i];
/* 252 */       boolean b = false;
/* 253 */       for (String j : arg2) if ((j != null) && (j.equals(i))) b = true;
/* 254 */       if (!b) ret.add(i);
/*     */     }
/* 256 */     return toStringArray(ret);
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     FileChanger
 * JD-Core Version:    0.6.2
 */