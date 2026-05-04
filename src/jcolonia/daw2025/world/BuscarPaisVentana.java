package jcolonia.daw2025.world;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;

public class BuscarPaisVentana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPatron;
	private JLabel lblEstado; // Barra de estado

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarPaisVentana frame = new BuscarPaisVentana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BuscarPaisVentana() {
		setTitle("Buscador de Países World");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);

		// --- 1. MENÚS ---
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(e -> System.exit(0));
		mnArchivo.add(mntmSalir);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		// --- 2. PANEL SUPERIOR ---
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);

		JLabel JLblPais = new JLabel("País:");
		panelSuperior.add(JLblPais);

		txtPatron = new JTextField();
		panelSuperior.add(txtPatron);
		txtPatron.setColumns(15);

		JButton BotonBuscar = new JButton("Buscar");
		panelSuperior.add(BotonBuscar);

		// --- 3. ZONA CENTRAL (SCROLL) ---
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JTextArea areaResultados = new JTextArea();
		areaResultados.setEditable(false);
		scrollPane.setViewportView(areaResultados);

		// --- 4. BARRA DE ESTADO (SUR) ---
		lblEstado = new JLabel(" Listo. Introduzca patrón (ej. S%)");
		lblEstado.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(lblEstado, BorderLayout.SOUTH);

		// --- 5. LÓGICA ---
		BotonBuscar.addActionListener(e -> {
			try {
				AccesoBDWorld dao = new AccesoBDWorld();
				List<String> paises = dao.buscarPaises(txtPatron.getText());

				areaResultados.setText("");
				if (paises.isEmpty()) {
					areaResultados.setText("No se han encontrado países.");
					lblEstado.setText(" Búsqueda finalizada: 0 resultados.");
				} else {
					for (int i = 0; i < paises.size(); i++) {
						areaResultados.append(String.format("%2d) %s%n", i + 1, paises.get(i)));
					}
					lblEstado.setText(" Búsqueda finalizada: " + paises.size() + " países encontrados.");
				}
			} catch (Exception ex) {
				lblEstado.setText(" Error en la base de datos.");
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}

