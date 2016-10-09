package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import ayuda.TextAreaUpdater;
import entidades.Clase;
import entidades.Metodo;
import metricas.ResultadoMetrica;
import principal.HerramientaTesting;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfRuta;
	private JComboBox<String> cbClases;
	private JComboBox<String> cbMetodos;
	private HerramientaTesting herramienta;
	private List<Clase> clasesProyecto;
	private List<Metodo> metodosClaseElegida;
	private TextArea txtAreaCodigo;
	private JLabel datoComplejidadCiclomatica;
	private JLabel datoLineasCodigo;
	private JLabel datoLineasComentarios;
	private JLabel datoPorcentajeComentarios;
	private JLabel datoLongitud;
	private JLabel datoVolumen;
	private JLabel datoFanIn;
	private JLabel datoFanOut;

	public GUI() {
		
		setResizable(false);
		setTitle("Herramienta de Testing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 738, 560);
		setBounds(100, 100, 1000, 560);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		
		JLabel lblCarpeta = new JLabel("Carpeta: ");
		lblCarpeta.setBounds(10, 11, 79, 14);
		contentPane.add(lblCarpeta);
		
		tfRuta = new JTextField();
		tfRuta.setBackground(Color.WHITE);
		tfRuta.setEditable(false);
		tfRuta.setBounds(89, 8, 523, 20);
		contentPane.add(tfRuta);
		tfRuta.setColumns(10);
		
		JButton btnAbrir = new JButton("Buscar directorio..");
		btnAbrir.setBounds(630, 7, 160, 23);
		btnAbrir.addActionListener(abrirDirectorio);
		contentPane.add(btnAbrir);
		
		cbClases = new JComboBox<String>();
		cbClases.setBounds(89, 41, 376, 20);
		cbClases.addActionListener(cargarMetodos);
		contentPane.add(cbClases);
		
		JLabel lblClass = new JLabel("Clases:");
		lblClass.setForeground(Color.BLACK);
		lblClass.setBounds(10, 43, 61, 14);
		contentPane.add(lblClass);
		
		JLabel lblMetodos = new JLabel("M\u00E9todos:");
		lblMetodos.setForeground(Color.BLACK);
		lblMetodos.setBounds(10, 73, 68, 14);
		contentPane.add(lblMetodos);
		
		cbMetodos = new JComboBox<String>();
		cbMetodos.setBounds(89, 71, 376, 20);
		cbMetodos.addActionListener(cargarMetricasCodigo);
		contentPane.add(cbMetodos);
		
		JLabel lblCodigo = new JLabel("Fuente:");
		lblCodigo.setForeground(Color.BLACK);
		lblCodigo.setBounds(10, 129, 89, 14);
		contentPane.add(lblCodigo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 39, 709, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 89, 495, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(505, 39, 2, 471);
		contentPane.add(separator_2);
		
		JLabel label = new JLabel("Complejidad Ciclom\u00E1tica: ");
		label.setForeground(Color.BLACK);
		label.setBounds(6, 373, 200, 20);
		contentPane.add(label);
		
		datoComplejidadCiclomatica = new JLabel("");
		datoComplejidadCiclomatica.setBounds(6, 393, 200, 20);
		contentPane.add(datoComplejidadCiclomatica);
		
		JLabel label_1 = new JLabel("L\u00EDneas de C\u00F3digo:");
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(206, 373, 200, 20);
		contentPane.add(label_1);
		
		datoLineasCodigo = new JLabel("");
		datoLineasCodigo.setBounds(206, 393, 200, 20);
		contentPane.add(datoLineasCodigo);
		
		JLabel label_2 = new JLabel("L\u00EDneas de Comentarios:");
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(6, 425, 200, 20);
		contentPane.add(label_2);
		
		datoLineasComentarios = new JLabel("");
		datoLineasComentarios.setBounds(6, 445, 200, 20);
		contentPane.add(datoLineasComentarios);
		
		JLabel lblPorcentajeDeComentarios = new JLabel("Porcentaje de Comentarios:");
		lblPorcentajeDeComentarios.setForeground(Color.BLACK);
		lblPorcentajeDeComentarios.setBounds(206, 425, 200, 20);
		contentPane.add(lblPorcentajeDeComentarios);
		
		datoPorcentajeComentarios = new JLabel("");
		datoPorcentajeComentarios.setBounds(206, 445, 200, 20);
		contentPane.add(datoPorcentajeComentarios);
		
		JLabel label_3 = new JLabel("Longitud:");
		label_3.setForeground(Color.BLACK);
		label_3.setBounds(6, 477, 156, 20);
		contentPane.add(label_3);
		
		datoLongitud = new JLabel("");
		datoLongitud.setBounds(6, 497, 156, 20);
		contentPane.add(datoLongitud);
		
		JLabel label_4 = new JLabel("Volumen:");
		label_4.setForeground(Color.BLACK);
		label_4.setBounds(206, 477, 200, 20);
		contentPane.add(label_4);
		
		datoVolumen = new JLabel("");
		datoVolumen.setBounds(206, 497, 200, 20);
		contentPane.add(datoVolumen);
		
		JLabel lblFanin = new JLabel("Fan-In:");
		lblFanin.setForeground(Color.BLACK);
		lblFanin.setBounds(405, 373, 200, 20);
		contentPane.add(lblFanin);
		
		datoFanIn = new JLabel("");
		datoFanIn.setBounds(405, 393, 200, 20);
		contentPane.add(datoFanIn);
		
		JLabel label_5 = new JLabel("Fan-Out:");
		label_5.setForeground(Color.BLACK);
		label_5.setBounds(405, 425, 200, 20);
		contentPane.add(label_5);
		
		datoFanOut = new JLabel("");
		datoFanOut.setBounds(405, 445, 200, 20);
		contentPane.add(datoFanOut);
		
		txtAreaCodigo = new TextArea();
		txtAreaCodigo.setBackground(Color.WHITE);
		txtAreaCodigo.setEditable(false);
		txtAreaCodigo.setBounds(10,149,968,218);
		//txtAreaCodigo.setBounds(10,135,480,385);

		contentPane.add(txtAreaCodigo);
		
		JButton btnInfo = new JButton("");
		btnInfo.addActionListener(mostrarOperadoresHalstead);
		Image infoIcon = new ImageIcon(this.getClass().getResource("/info.ico")).getImage();
		btnInfo.setIcon(new ImageIcon(infoIcon));
		btnInfo.setToolTipText("M\u00e1s informaci\u00f3n");
		//btnInfo.setBounds(800, 291, 32, 32);
		btnInfo.setBounds(687, 42, 32, 32);
		btnInfo.setVisible(false);

		contentPane.add(btnInfo);
		setLocationRelativeTo(null);
	}
	
	ActionListener abrirDirectorio = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//Creamos el objeto JFileChooser
			JFileChooser fc=new JFileChooser();
			 
			//Indicamos lo que podemos seleccionar
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 
			//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
			int seleccion = fc.showOpenDialog(contentPane);
			 
			//Si el usuario, pincha en aceptar
			if(seleccion == JFileChooser.APPROVE_OPTION){
			    //Seleccionamos el fichero
			    File fichero = fc.getSelectedFile();
			 
			    //Ecribe la ruta del fichero seleccionado en el campo de texto
			    tfRuta.setText(fichero.getAbsolutePath());
			    
			    //Elimina las clases y metodos cargados en el combobox
			    cbClases.removeAllItems();
			    cbMetodos.removeAllItems();
			    
			    herramienta = new HerramientaTesting(new File(fichero.getAbsolutePath()));
			    clasesProyecto = herramienta.getProyecto();	
			    
			    //Cargo el comboBox de clases
			    for(int indice = 0; indice < clasesProyecto.size(); indice++){
					cbClases.addItem(clasesProyecto.get(indice).getNombre());			
				}
			}
		}
	};
	
	ActionListener cargarMetodos = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			//Elimino los metodos cargados en el comboBox
			cbMetodos.removeAllItems();
			
			JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
			
			if(null==comboBox.getSelectedItem())
			       return ;
			
            Integer claseSeleccionada = comboBox.getSelectedIndex();
			Clase claseElegida = clasesProyecto.get(claseSeleccionada);
			metodosClaseElegida = claseElegida.getMetodos();
			
			//Cargo el comboBox de m�todos
		    for(int indice = 0; indice < metodosClaseElegida.size(); indice++){
				cbMetodos.addItem(metodosClaseElegida.get(indice).getNombre());			
			}
		}
	};
	
	ActionListener cargarMetricasCodigo = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			JComboBox<?> comboBox = (JComboBox<?>) e.getSource();

            Integer metodoSeleccionado = comboBox.getSelectedIndex();
            if (metodoSeleccionado == -1) {
				return;
			}
            Metodo metodoElegido = metodosClaseElegida.get(metodoSeleccionado);
            
            List<ResultadoMetrica> resultados = herramienta.calcularMetricas(metodoElegido);
			
            new Thread(
            		new TextAreaUpdater(txtAreaCodigo, metodoElegido.getCodigo())
        		).start();
            
            //Aca hay un problema, si cambiamos el orden de resolucion de las metricas esto tambien hay que cambiarlo
            
            /**
             * Complejidad Ciclomatica
             */
            Integer complejidadCiclomatica = Integer.parseInt(resultados.get(0).getResultado());            
            if( complejidadCiclomatica > 10){
            	datoComplejidadCiclomatica.setForeground(Color.RED);
            	datoComplejidadCiclomatica.setToolTipText("La complejidad ciclom\u00E1tica supera 10, es recomendable modularizar el m\u00E9todo.");
            }else{
            	datoComplejidadCiclomatica.setForeground(Color.DARK_GRAY);
            	datoComplejidadCiclomatica.setToolTipText(null);
            }            
			datoComplejidadCiclomatica.setText(complejidadCiclomatica.toString());
			
			
			/**
			 * Lineas de codigo, comentarios y porcentaje de comentarios
			 */
			datoLineasCodigo.setText(resultados.get(1).getResultado());
			datoLineasComentarios.setText(resultados.get(2).getResultado());
			
			Double porcentajeComentarios = Integer.parseInt(datoLineasComentarios.getText()) * 100.0 / Integer.parseInt(datoLineasCodigo.getText());
			
			if( porcentajeComentarios < 15){
				datoPorcentajeComentarios.setForeground(Color.RED);
				datoPorcentajeComentarios.setToolTipText("El porcentaje de comentarios recomendable es del 15%. Agregue m\u00E1s comentarios al m\u00E9todo.");
            }else{
            	datoPorcentajeComentarios.setForeground(Color.DARK_GRAY);
            	datoPorcentajeComentarios.setToolTipText(null);
            }
			datoPorcentajeComentarios.setText(String.format("%.2f", porcentajeComentarios) + "%");
			
			/**
			 * Halstead
			 */
			String halstead[] = resultados.get(3).getResultado().split(" ");
			datoLongitud.setText(halstead[1]);
			datoVolumen.setText(halstead[3]);
			
			/**
			 * Fan-In y Fan-Out
			 */
			datoFanIn.setText(resultados.get(4).getResultado());
			datoFanOut.setText(resultados.get(5).getResultado());
			
		}
	};
	
	ActionListener mostrarOperadoresHalstead = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			String msg = "";
			for (String operador : HerramientaTesting.getOperadoresConsiderados()) {
				msg += operador + "\n";
			}
			JOptionPane.showMessageDialog(new JFrame(), msg, "Operadores considerados - Halstead", JOptionPane.INFORMATION_MESSAGE);
			
		}
	};
}

