/*     */ import GUIComponents.PushablePanel;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ 
/*     */ public class StatPlotDialog extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private JButton add;
/*     */   private JButton cancel;
/*     */   private JButton delete;
/*     */   private JButton ok;
/*     */   private JButton edit;
/*     */   private PushablePanel panel;
/*  21 */   private ArrayList<StatPlotDialog.CheckAndRadioButton> plots = new ArrayList();
/*  22 */   private ArrayList<StatPlot> statplots = new ArrayList();
/*  23 */   private ArrayList<StatPlotCreationDialog> dialogs = new ArrayList();
/*     */   private Graph2D graph;
/*  25 */   private ButtonGroup buttonGroup = new ButtonGroup();
/*     */ 
/*  27 */   public StatPlotDialog(Frame owner, Graph2D graph) { super(owner, "Statplot", true);
/*  28 */     this.graph = graph;
/*  29 */     JPanel main = new JPanel();
/*  30 */     JPanel buttons = new JPanel(new FlowLayout());
/*  31 */     JPanel head = new JPanel(new FlowLayout());
/*  32 */     JPanel addDelete = new JPanel();
/*  33 */     this.panel = new PushablePanel();
/*  34 */     addDelete.setLayout(new BoxLayout(addDelete, 1));
/*  35 */     main.setLayout(new BoxLayout(main, 1));
/*     */ 
/*  37 */     addDelete.setPreferredSize(new Dimension(75, 250));
/*  38 */     this.panel.setPreferredSize(new Dimension(150, 250));
/*  39 */     buttons.setPreferredSize(new Dimension(350, 50));
/*     */ 
/*  41 */     this.add = new JButton("Add");
/*  42 */     this.cancel = new JButton("Cancel");
/*  43 */     this.delete = new JButton("Delete");
/*  44 */     this.ok = new JButton("Apply");
/*  45 */     this.edit = new JButton("Edit");
/*     */ 
/*  47 */     this.add.addActionListener(this);
/*  48 */     this.cancel.addActionListener(this);
/*  49 */     this.delete.addActionListener(this);
/*  50 */     this.ok.addActionListener(this);
/*  51 */     this.edit.addActionListener(this);
/*     */ 
/*  53 */     addDelete.add(this.add);
/*  54 */     addDelete.add(this.delete);
/*  55 */     addDelete.add(this.edit);
/*     */ 
/*  57 */     buttons.add(this.ok);
/*  58 */     buttons.add(this.cancel);
/*     */ 
/*  60 */     head.add(this.panel);
/*  61 */     head.add(addDelete);
/*     */ 
/*  63 */     main.add(head);
/*  64 */     main.add(buttons);
/*     */ 
/*  66 */     add(main);
/*  67 */     setPreferredSize(new Dimension(275, 350));
/*  68 */     setResizable(false);
/*  69 */     pack(); }
/*     */ 
/*     */   private void sweep() {
/*  72 */     ArrayList delete = new ArrayList();
/*  73 */     for (int i = 0; i < this.plots.size(); i++) {
/*  74 */       if (((StatPlotDialog.CheckAndRadioButton)this.plots.get(i)).isBoxSelected()) {
/*  75 */         delete.add((StatPlotDialog.CheckAndRadioButton)this.plots.get(i));
/*     */       }
/*     */     }
/*  78 */     for (int i = 0; i < delete.size(); i++) {
/*  79 */       this.statplots.remove(this.plots.indexOf(delete.get(i)));
/*  80 */       this.plots.remove(delete.get(i));
/*  81 */       this.dialogs.remove(delete.get(i));
/*  82 */       this.panel.removeComponent((JComponent)delete.get(i));
/*     */     }
/*  84 */     for (int i = 0; i < this.plots.size(); i++) ((StatPlotDialog.CheckAndRadioButton)this.plots.get(i)).setText("Stat #" + (i + 1)); 
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*  88 */     if ((e.getSource() == this.ok) || (e.getSource() == this.cancel)) {
/*  89 */       if (e.getSource() == this.ok) {
/*  90 */         dumpPlots();
/*     */       }
/*  92 */       setVisible(false);
/*  93 */     } else if (e.getSource() == this.add) {
/*  94 */       this.dialogs.add(new StatPlotCreationDialog(this, this.graph, this.statplots.size()));
/*  95 */       ((StatPlotCreationDialog)this.dialogs.get(this.dialogs.size() - 1)).setVisible(true);
/*  96 */     } else if (e.getSource() == this.delete) {
/*  97 */       sweep();
/*  98 */     } else if (e.getSource() == this.edit) {
/*  99 */       openEditor();
/*     */     }
/*     */   }
/*     */ 
/* 103 */   public void setStatPlot(StatPlot x, int idx) { if (idx >= this.statplots.size()) addStatPlot(x); else
/* 104 */       this.statplots.set(idx, x); }
/*     */ 
/*     */   public void addStatPlot(StatPlot x) {
/* 107 */     StatPlotDialog.CheckAndRadioButton temp = new StatPlotDialog.CheckAndRadioButton("Stat #" + (this.plots.size() + 1));
/* 108 */     this.panel.pushComponent(temp);
/* 109 */     this.plots.add(temp);
/* 110 */     this.statplots.add(x);
/*     */   }
/*     */   private void dumpPlots() {
/* 113 */     this.graph.resetPlots();
/* 114 */     for (int i = 0; i < this.statplots.size(); i++)
/* 115 */       if (((StatPlotDialog.CheckAndRadioButton)this.plots.get(i)).isBoxSelected())
/* 116 */         this.graph.addStatPlot((StatPlot)this.statplots.get(i));
/*     */   }
/*     */ 
/*     */   private void openEditor() {
/* 120 */     for (int i = 0; i < this.plots.size(); i++)
/* 121 */       if (((StatPlotDialog.CheckAndRadioButton)this.plots.get(i)).isButtonSelected()) {
/* 122 */         ((StatPlotCreationDialog)this.dialogs.get(i)).setVisible(true);
/* 123 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public ArrayList<StatPlot> getPlots() {
/* 128 */     return this.statplots;
/*     */   }
/*     */ 
/*     */   private class CheckAndRadioButton extends JPanel
/*     */   {
/*     */     private JCheckBox box;
/* 133 */     private JRadioButton button = new JRadioButton();
/*     */ 
/* 135 */     public CheckAndRadioButton(String text) { setLayout(new FlowLayout());
/* 136 */       this.box = new JCheckBox(text, true);
/* 137 */       StatPlotDialog.this.buttonGroup.add(this.button);
/* 138 */       add(this.box);
/* 139 */       add(this.button); }
/*     */ 
/*     */     public boolean isBoxSelected() {
/* 142 */       return this.box.isSelected();
/*     */     }
/*     */     public boolean isButtonSelected() {
/* 145 */       return this.button.isSelected();
/*     */     }
/*     */     public void setText(String text) {
/* 148 */       this.box.setText(text);
/*     */     }
/*     */   }
/*     */ }

/* Location:           Modulus.jar
 * Qualified Name:     StatPlotDialog
 * JD-Core Version:    0.6.2
 */