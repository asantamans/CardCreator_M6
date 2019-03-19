package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Logica.ControladorInterfaz;
import Objectes.Carta;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Editor {

	private JFrame frame;
	private JList list;
	private JTextField textField;
	private JButton toCartas;
	private JButton toDeck;
	private JLabel lblNomBaralla;
	public static JList<Carta> cartasList;
	public static JList<Carta> deckList;
	private JButton btnRandomDeck;
	public static ArrayList<Carta> cartesArray;
	public static ArrayList<Carta> deckArray;
	private static DefaultListModel<Carta> cartesDLM;
	private static DefaultListModel<Carta> deckDLM;
	private JScrollPane cartesScroll;
	private JScrollPane deckScroll;
	private static ControladorInterfaz controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Editor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controller = new ControladorInterfaz();
		cartesArray = new ArrayList<Carta>();
		deckArray = new ArrayList<Carta>();
		cartesDLM = new DefaultListModel<Carta>();
		deckDLM = new DefaultListModel<Carta>();

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 480);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		cartasList = new JList(cartesDLM);
		cartasList.setLayoutOrientation(JList.VERTICAL);// Surt rara si no
		cartasList.setBounds(54, 80, 206, 332);
		cartasList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		panel.add(cartasList);

		deckList = new JList(deckDLM);
		deckList.setLayoutOrientation(JList.VERTICAL);
		deckList.setBounds(533, 80, 206, 332);
		deckList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		panel.add(deckList);

		toCartas = new JButton("<---");
		toCartas.setBackground(UIManager.getColor("CheckBox.light"));
		toCartas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		toCartas.setBounds(323, 232, 127, 41);
		toCartas.setContentAreaFilled(false);
		toCartas.setFocusPainted(false);
		toCartas.setBorder(new EmptyBorder(1, 1, 1, 1));
		panel.add(toCartas);

		toDeck = new JButton("--->");
		toDeck.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		toDeck.setBounds(323, 297, 127, 41);
		toDeck.setContentAreaFilled(false);
		toDeck.setFocusPainted(false);
		toDeck.setBorder(new EmptyBorder(1, 1, 1, 1));
		panel.add(toDeck);

		lblNomBaralla = new JLabel("Nom Baralla:");
		lblNomBaralla.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomBaralla.setBounds(270, 99, 113, 20);
		panel.add(lblNomBaralla);

		textField = new JTextField();
		textField.setBounds(371, 101, 135, 20);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Carregar cartes");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBounds(44, 22, 198, 34);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false);
		btnNewButton.setForeground(Color.BLACK);

		JButton btnCarregarBaralla = new JButton("Carregar Baralla");
		btnCarregarBaralla.setForeground(Color.BLACK);
		btnCarregarBaralla.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnCarregarBaralla.setFocusPainted(false);
		btnCarregarBaralla.setContentAreaFilled(false);
		btnCarregarBaralla.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnCarregarBaralla.setBounds(290, 22, 198, 34);

		panel.add(btnCarregarBaralla);

		btnNewButton.setBorder(new EmptyBorder(1, 1, 1, 1));
		panel.add(btnNewButton);

		btnRandomDeck = new JButton("Baralla Aleatoria\r\n");
		// btnRandomDeck.setOpaque(false);
		btnRandomDeck.setForeground(Color.BLACK);
		btnRandomDeck.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnRandomDeck.setContentAreaFilled(false);
		btnRandomDeck.setFocusPainted(false);
		btnRandomDeck.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnRandomDeck.setBounds(301, 182, 198, 34);

		panel.add(btnRandomDeck);

		JButton btnGuardarBaralla = new JButton("Guardar Baralla");
		btnGuardarBaralla.setOpaque(false);
		btnGuardarBaralla.setForeground(Color.BLACK);
		btnGuardarBaralla.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnGuardarBaralla.setFocusPainted(false);
		btnGuardarBaralla.setContentAreaFilled(false);
		btnGuardarBaralla.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnGuardarBaralla.setBounds(510, 22, 198, 34);
		panel.add(btnGuardarBaralla);

		// Action Listeners
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cargarCardList();
				controller.setCartasCargadas(true);
			}
		});

		btnCarregarBaralla.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Nom de la Baralla",
						"Introdueix el nom de la baralla a cercar", 1);
				if (name != null) {

					cargarCardList();
				}
			}
		});
		toDeck.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (controller.isCartasCargadas() || controller.isDeckCargado()) {
					int point = cartasList.getSelectedIndex();
					if (point >= 0) {
					deckDLM.addElement(cartasList.getSelectedValue());
					cartesDLM.remove(cartasList.getSelectedIndex());
					}
				}

			}

		});
		toCartas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (controller.isCartasCargadas() || controller.isDeckCargado()) {
				int point = deckList.getSelectedIndex();
				if (point >= 0) {
					cartesDLM.addElement(deckList.getSelectedValue());
					deckDLM.remove(point);
				}
				
			}else {
				showError("No has cargado ninguna baraja o cartas");
			}
			}

			
		});

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Editor.class.getResource("/Interfaz/editor_background.jpg")));
		lblNewLabel.setBounds(0, 0, 784, 441);
		panel.add(lblNewLabel);
	}

	private void carregarCartes() {
		// Funcio hardcodejada temporalment
		for (int i = 1; i < 17; ++i) {
			Carta a = new Carta(i, "Carta "+i,i, i+2, i-2, 2);
			cartesArray.add(a);
		}
	}

	// Deprecated
	private void cargarInvisibles() {
		cartasList.setVisible(true);
		deckList.setVisible(true);
		toDeck.setVisible(true);
		toCartas.setVisible(true);
		textField.setVisible(true);
		lblNomBaralla.setVisible(true);
		btnRandomDeck.setVisible(true);
	}

	private void cargarCardList() {
		carregarCartes();

		for (Carta a : cartesArray) {
			cartesDLM.addElement(a);
		}
		cartasList.setModel(cartesDLM);
	}
	private void showError(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null,string, "Advertencia",1);
	}

}
