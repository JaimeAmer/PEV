package pr1.iu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author BOOTCAMP
 */
public class GUI extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Application app;
    /**
     * Creates new form GUI
     */
    public GUI(Application app) {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
    	
    	initComponents();
        this.app = app;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        plot2DPanel2 = new org.math.plot.Plot2DPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1280, 720));

        plot2DPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Grafica"));
        plot2DPanel2.addLegend("SOUTH");
        
        this.setTitle("Practica 1 PEV");
        
        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tam. Poblacion");

        jTextField1.setText("100");

        jLabel2.setText("Prob. Cruce");

        jTextField2.setText("60");

        jLabel3.setText("Prob. Mutacion");

        jTextField3.setText("5");

        jLabel4.setText("Precision");

        jLabel5.setText("Núm. Generaciones");

        jLabel6.setText("Semilla");

        jLabel7.setText("N (Solo en Funcion 4)");

        jLabel8.setText("Participantes (Solo Torneo)");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Output"));
        jScrollPane1.setViewportView(jTextArea1);

        jTextField4.setText("0.001");

        jTextField5.setText("100");

        jTextField6.setText("0");

        jTextField7.setText("1");

        jTextField8.setText("2");

        jCheckBox1.setText("Elitismo");
        

        jLabel10.setText("Funcion");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Funcion 1", "Funcion 2", "Funcion 3", "Funcion 4", "Funcion 4-R", "Funcion 5" }));

        jLabel9.setText("Metodo de Seleccion");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Ruleta", "Ranking", "Torneo", "Torneo Probabilistico", "Estocastico Universal" }));

        jLabel11.setText("Tipo de Algoritmo (Solo en Funcion 4-R)");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Externo", "Discreto Uniforme", "Aritmetico", "SBX" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField8)
                                            .addComponent(jTextField7)
                                            .addComponent(jTextField6)
                                            .addComponent(jTextField5)
                                            .addComponent(jTextField4)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addComponent(plot2DPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(plot2DPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(17, 17, 17)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        
        double[] x = {1};
        double[] y = {1};
        plot2DPanel2.addLinePlot("Mejor Absoluto", Color.RED, x, y);
        plot2DPanel2.addLinePlot("Mejor Generacion", Color.BLUE, x, y);
        plot2DPanel2.addLinePlot("Media Generacion", Color.GREEN, x, y);
        this.setResizable(false);
        pack();
    }// </editor-fold>    
    
    private void executeFuncion4R(){
    	float precision = Float.parseFloat(this.jTextField4.getText());
		float probabilidadCruce = Float.parseFloat(this.jTextField2.getText()) / 100;
		float probabilidadMutacion = Float.parseFloat(this.jTextField3.getText()) / 100;
		String metodoSeleccion = this.jComboBox2.getSelectedItem().toString();
		String funcion = this.jComboBox1.getSelectedItem().toString();
		boolean elitismo = this.jCheckBox1.isSelected();
		int tamanoPoblacion = Integer.parseInt(this.jTextField1.getText());
		int numGeneraciones = Integer.parseInt(this.jTextField5.getText());
		long semilla = Long.parseLong(this.jTextField6.getText());
		int participantes = Integer.parseInt(this.jTextField7.getText());
		int n = Integer.parseInt(this.jTextField7.getText());
		String tipoAlgoritmo = this.jComboBox3.getSelectedItem().toString();
		if(this.jComboBox2.getSelectedItem().toString().split(" ")[0].equals("Torneo")){
			participantes = Integer.parseInt(this.jTextField8.getText());
		}
		
		if(semilla == 0)
			semilla = System.currentTimeMillis();

    	app.init(precision, probabilidadCruce, probabilidadMutacion, metodoSeleccion, elitismo, funcion, tamanoPoblacion, numGeneraciones, semilla, n, participantes, tipoAlgoritmo);
    
    }
    
    private void executeFuncion4(){
    	float precision = Float.parseFloat(this.jTextField4.getText());
		float probabilidadCruce = Float.parseFloat(this.jTextField2.getText()) / 100;
		float probabilidadMutacion = Float.parseFloat(this.jTextField3.getText()) / 100;
		String metodoSeleccion = this.jComboBox2.getSelectedItem().toString();
		String funcion = this.jComboBox1.getSelectedItem().toString();
		boolean elitismo = this.jCheckBox1.isSelected();
		int tamanoPoblacion = Integer.parseInt(this.jTextField1.getText());
		int numGeneraciones = Integer.parseInt(this.jTextField5.getText());
		long semilla = Long.parseLong(this.jTextField6.getText());
		int participantes = Integer.parseInt(this.jTextField7.getText());
		int n = Integer.parseInt(this.jTextField7.getText());
		String tipoAlgoritmo = "";
		if(this.jComboBox2.getSelectedItem().toString().split(" ")[0].equals("Torneo")){
			participantes = Integer.parseInt(this.jTextField8.getText());
		}
		
		if(semilla == 0)
			semilla = System.currentTimeMillis();

    	app.init(precision, probabilidadCruce, probabilidadMutacion, metodoSeleccion, elitismo, funcion, tamanoPoblacion, numGeneraciones, semilla, n, participantes, tipoAlgoritmo);
    
    }
    
    private void execute(){
    	float precision = Float.parseFloat(this.jTextField4.getText());
		float probabilidadCruce = Float.parseFloat(this.jTextField2.getText()) / 100;
		float probabilidadMutacion = Float.parseFloat(this.jTextField3.getText()) / 100;
		String metodoSeleccion = this.jComboBox2.getSelectedItem().toString();
		String funcion = this.jComboBox1.getSelectedItem().toString();
		boolean elitismo = this.jCheckBox1.isSelected();
		int tamanoPoblacion = Integer.parseInt(this.jTextField1.getText());
		int numGeneraciones = Integer.parseInt(this.jTextField5.getText());
		long semilla = Long.parseLong(this.jTextField6.getText());
		int participantes = 0;
		int n = Integer.parseInt(this.jTextField7.getText());
		String tipoAlgoritmo = "";
		if(this.jComboBox2.getSelectedItem().toString().split(" ")[0].equals("Torneo")){
			participantes = Integer.parseInt(this.jTextField8.getText());
		}
		
		if(semilla == 0)
			semilla = System.currentTimeMillis();

    	app.init(precision, probabilidadCruce, probabilidadMutacion, metodoSeleccion, elitismo, funcion, tamanoPoblacion, numGeneraciones, semilla, n, participantes, tipoAlgoritmo);
    
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	switch(this.jComboBox1.getSelectedItem().toString()){
    	case "Funcion 4":
    		executeFuncion4();
    		break;
    	case "Funcion 4-R":
    		executeFuncion4R();
    		break;
    	default:
    		execute();
    		break;
    	}
    }                                        

                                             

    /**
     * @param args the command line arguments
     * @return 
     */
    
    
    public void setResultado(String resultado, Long t, Long semilla){
    	
    	String texto = "Generado en: " + t.floatValue() + " seg." + "\n" + 
    					"Ultima semilla utilizada: " + semilla + "\n" +
    					"\n" +
    					resultado;
    	this.jTextArea1.setText(texto);
    	this.pack();
    }
    
    public void addPlot(Double[] y, String titulo, Color color){
		double[] auxX = new double[y.length];
		for(int i=0; i<auxX.length; i++){
			auxX[i] = i+1;
		}
		
		double[] auxY = new double[y.length];
		for(int i=0; i<y.length; i++){
			auxY[i] = y[i];
		}
		
		plot2DPanel2.addLinePlot(titulo, color, auxX, auxY);
	}
    
    public void resetPlots(){
    	plot2DPanel2.removeAllPlots();
    	
    }

    // Variables declaration - do not modify          
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private org.math.plot.Plot2DPanel plot2DPanel2;
    // End of variables declaration                   
}

	
	
	

