import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
public class Ventana extends JFrame implements ActionListener, ItemListener {

    //Clipboard clipboard = getToolkit().getSystemClipboard();
    int normal=0,bold=1,itali=2,opcion;
    JPopupMenu menuContextual;
    JButton bIm1,bIm2,bIm3,bIm4,bIm5,bIm6,bIm7,bIm8;
    JComboBox combotamanio,comboletra,comboTipo;
    DefaultComboBoxModel modelotamanio,modeloletra,modeloTipo;
    JMenuBar bar,menubar;
    JMenu menu,menu2,menu3;
    JMenuItem menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,menuItem8,menuItem9,menuItem10,menuItem11,menuItem12,copy,cut,paste;
    CardLayout cardLayout;
    Container container;
    JPanel panelSuperior,panelCentro,panelCentro1,panelInferior,panelInferior2;
    JLabel letra,letra2;
    JTextArea textoArea;

    public void initGUI() {
         instancias();
         configurarMenu();
         configurarContainer();
         configurarClick();
         rellenarTipo();
         setBounds(50,50,800,400);
         acciones();
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
         setVisible(true);
    }

    private void acciones() {
        textoArea.addMouseListener(new ManejoPulsacion());
        comboletra.addItemListener(this);
        combotamanio.addItemListener(this);
        comboTipo.addItemListener(this);
        bIm8.addActionListener(this);
        bIm1.addActionListener(this);
        bIm2.addActionListener(this);
        bIm3.addActionListener(this);
        bIm4.addActionListener(this);
        menu3.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);
        menuItem6.addActionListener(this);
        menuItem10.addActionListener(this);
        menuItem11.addActionListener(this);
        menuItem12.addActionListener(this);
        menuItem1.addActionListener(this);
    }
    private void configurarClick() {
        menuContextual.add(copy);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,InputEvent.ALT_DOWN_MASK));
        menuContextual.add(cut);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,InputEvent.ALT_DOWN_MASK));
        menuContextual.add(paste);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,InputEvent.ALT_DOWN_MASK));
    }
    private void rellenarLetras() {
        Font[] fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAllFonts();
        for (Font item : fuentes) {
            modeloletra.addElement(item.getName());
        }
    }
    private void rellenarTamanio() {
        for(int i=14;i<50;i++){
            modelotamanio.addElement(i);
        }
    }
    private void rellenarTipo() {
        modeloTipo.addElement(normal);
        modeloTipo.addElement(bIm8);
        modeloTipo.addElement(itali);
    }

    private void configurarMenu() {
        bar.add(menu);
        bar.add(menu2);
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);
        menu.add(menuItem6);
        menu2.add(menuItem7);
        menu2.add(menuItem8);
        menu2.add(menuItem9);
        menu3.add(menuItem10);
        menu3.add(menuItem11);
        menu3.add(menuItem12);
        menu2.add(menu3);
        this.setJMenuBar(bar);
    }public void configurarConstraint(int posX, int posY,int fill, int anchor
            ,double pesX, double pesY, int tamX, int tamY, JComponent component){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = posX;
        constraints.gridy = posY;
        constraints.fill = fill ;
        constraints.anchor= anchor;
        constraints.weightx = pesX;
        constraints.weighty = pesY;
        constraints.gridwidth= tamX;
        constraints.gridheight= tamY;
        panelInferior.add(component,constraints);

    }
    private void configurarContainer() {
        container.setLayout(new BorderLayout());
        container.add(panelSuperior(),BorderLayout.NORTH);
        container.add(panelInferior());
    }
    private JPanel panelInferior() {
        panelInferior.setLayout(new GridBagLayout());
        configurarConstraint(1,1,GridBagConstraints.BOTH,GridBagConstraints.CENTER,
                1,1, 3,1,new JScrollPane(textoArea));
        rellenarLetras();
        rellenarTamanio();
        return panelInferior;
    }
    private JPanel panelSuperior() {
       panelSuperior.add(panelCentro);
       panelCentro();
       return panelSuperior;
    }
    private void panelCentro() {
        panelCentro.setLayout(new GridLayout(1,3));
        panelCentro.add(menubar);
        menubar.add(bIm1);
        menubar.add(bIm2);
        menubar.add(bIm3);
        menubar.add(bIm4);
        menubar.add(bIm5);
        menubar.add(bIm6);
        menubar.add(bIm7);
        menubar.add(bIm8);
        menubar.add(letra);
        menubar.add(combotamanio);
        menubar.add(letra2);
        menubar.add(comboletra);
    }
    private void instancias() {
        container = getContentPane();
        menuContextual = new JPopupMenu();
        menu = new JMenu("Archivo");
        menu2= new JMenu("Edicion");
        menu3 = new JMenu("Estilo de la fuente");
        menuItem1 = new JMenuItem("Nuevo",new ImageIcon("src/Imagenes/new.png"));
        menuItem2 = new JMenuItem("Abrir ..", new ImageIcon("src/Imagenes/open.png"));
        menuItem3 = new JMenuItem("Cerrar", new ImageIcon("src/Imagenes/close.png"));
        menuItem4 = new JMenuItem("Guardar", new ImageIcon("src/Imagenes/save.png"));
        menuItem5 = new JMenuItem("Guardar Como..");
        menuItem6 = new JMenuItem("Imprimir");
        menuItem7 = new JMenuItem(new DefaultEditorKit.CopyAction());
        menuItem7.setIcon(new ImageIcon("src/Imagenes/copy.png"));
        menuItem7.setText("Copiar");
        menuItem8 = new JMenuItem(new DefaultEditorKit.CutAction());
        menuItem8.setIcon(new ImageIcon("src/Imagenes/cut.png"));
        menuItem8.setText("Cortar");
        menuItem9 = new JMenuItem(new DefaultEditorKit.PasteAction());
        menuItem9.setIcon(new ImageIcon("src/Imagenes/paste.png"));
        menuItem9.setText("Pegar");
        copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setIcon(new ImageIcon("src/Imagenes/copy.png"));
        copy.setText("Copiar");
        cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setIcon(new ImageIcon("src/Imagenes/cut.png"));
        cut.setText("Cortar");
        paste= new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setIcon(new ImageIcon("src/Imagenes/paste.png"));
        paste.setText("Pegar");

        menuItem10 = new JMenuItem("Bold",new ImageIcon("src/Imagenes/bold.png"));
        menuItem11= new JMenuItem("Normal",new ImageIcon("src/Imagenes/normal.png"));
        menuItem12= new JMenuItem("Italic",new ImageIcon("src/Imagenes/italic.png"));
       // bIm1.setIcon(new ImageIcon("src/Imagenes/new.png"));
        bIm1 = new JButton(new ImageIcon("src/Imagenes/new.png"));
        bIm2 = new JButton(new ImageIcon("src/Imagenes/open.png"));
        bIm3 = new JButton(new ImageIcon("src/Imagenes/save.png"));
        bIm4 = new JButton("Imprimir");
        bIm5 = new JButton( new DefaultEditorKit.CopyAction());
        bIm5.setText(" ");
        bIm5.setIcon(new ImageIcon("src/Imagenes/copy.png"));
        bIm6 = new JButton( new DefaultEditorKit.CutAction());
        bIm6.setText(" ");
        bIm6.setIcon(new ImageIcon("src/Imagenes/cut.png"));
        bIm7 = new JButton(new DefaultEditorKit.PasteAction());
        bIm7.setText(" ");
        bIm7.setIcon(new ImageIcon("src/Imagenes/paste.png"));
        bIm8 = new JButton(new ImageIcon("src/Imagenes/bold.png"));
        menubar = new JMenuBar();
        bar = new JMenuBar();
        cardLayout = new CardLayout();

        panelSuperior = new JPanel();
        panelCentro = new JPanel();
        panelCentro1 = new JPanel();
        panelInferior = new JPanel();
        panelInferior2 = new JPanel();
        modeloletra = new DefaultComboBoxModel();
        comboletra = new JComboBox(modeloletra);

        modelotamanio = new DefaultComboBoxModel();
        combotamanio = new JComboBox(modelotamanio);
        letra= new JLabel("Tamaño de letra");
        letra2 = new JLabel("Tipo de letra");
        textoArea = new JTextArea();
        modeloTipo = new DefaultComboBoxModel();
        comboTipo = new JComboBox(modeloTipo);
    }
    public void Guardar(){
    JFileChooser fileChooser = new JFileChooser();
    int i = fileChooser.showSaveDialog(this);
    if (i == JFileChooser.APPROVE_OPTION){
        String f = fileChooser.getSelectedFile().getName();
        String ex = f.substring(f.indexOf(".")+1);
        System.out.println(ex);
    }else if (i == JFileChooser.CANCEL_OPTION){}
    }
    public void Abrir(){
        JFileChooser fileChooser = new JFileChooser();
        //  int i = fileChooser.showDialog(this, "Aceptar");
        int a = fileChooser.showOpenDialog(this);
        if (a == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }String nombre = file.getName();
            String ext = nombre.substring(nombre.indexOf(". ") + 1);
            System.out.println(ext);
        }
    }public void Imprimir(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable( new MiPrintable());
        try{
            job.print();
        }catch (PrinterException a){
            a.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem4){
         Guardar();
        }else if (e.getSource() == menuItem2){
            Abrir();
        }else if (e.getSource() == bIm3){
            Guardar();
        }else if (e.getSource() == bIm2){
            Abrir();
        }else if (e.getSource() ==bIm4){
            Imprimir();
        }else if (e.getSource()==menuItem6){
            Imprimir();
        } else if (e.getSource()==menuItem1){
            if (!textoArea.getText().toString().isEmpty()){
                textoArea.setText(" ");
            }
        } else if (e.getSource()==bIm1){
           if (!textoArea.getText().toString().isEmpty()){
            textoArea.setText(" ");
           }
        } else if (e.getSource()==menuItem10) {
                Font fuente = new Font((String) modeloletra.getSelectedItem(), bold,(int) modelotamanio.getSelectedItem());
                textoArea.setFont(fuente);
        } else if (e.getSource()==menuItem11) {
                    Font fuente = new Font((String) modeloletra.getSelectedItem(), normal, (int) modelotamanio.getSelectedItem());
                    textoArea.setFont(fuente);
        } else if (e.getSource()==menuItem12) {
                 Font fuente = new Font((String) modeloletra.getSelectedItem(), itali,(int) modelotamanio.getSelectedItem());
                 textoArea.setFont(fuente);
        } else if (e.getSource()==bIm8){
            String familia = (String )comboletra.getModel().getSelectedItem();
            Font fuente = new Font(familia,bold,(int) modelotamanio.getSelectedItem());
            textoArea.setFont(fuente);
        } else if (e.getSource() == menuItem3) {
            opcion = JOptionPane.showConfirmDialog(Ventana.this,
                    "¿Desea guardar los cambios Sin título?",
                    "Titulo del mensaje", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null);
            if (opcion == JOptionPane.YES_OPTION) {
                System.out.println("Pulsado si");
                Guardar();
            } else if (opcion == JOptionPane.NO_OPTION) {
                System.out.println("Pulsado no");
                //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                //Runtime system;
                System.exit(0);
            } else if (opcion == JOptionPane.CANCEL_OPTION) {
                System.out.println("Pulsado cancelar");
            }
        }
    }class ManejoPulsacion extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("PULSADO");
            if(e.getButton()==MouseEvent.BUTTON3){
                menuContextual.show(panelInferior,e.getX(),e.getY()); }}
    }@Override
    public void itemStateChanged(ItemEvent e) {
        Font fuente = new Font((String) modeloletra.getSelectedItem(), comboTipo.getSelectedIndex(),(int) modelotamanio.getSelectedItem());
        textoArea.setFont(fuente);
        Component[] components = this.getComponents();
        for(Component item : components) {
            item.setFont(fuente);
        }
    }class MiPrintable implements Printable {
        public int print (Graphics g, PageFormat f, int pageIndex){
            if (pageIndex == 0){
                g.drawString(String.valueOf(textoArea), 100,100);
                return PAGE_EXISTS;
            }else return NO_SUCH_PAGE;
        }
    }
}
