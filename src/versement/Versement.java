package versement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JLabel;

import header.Header;
import utils.Pdf;
import utils.Recherche;

import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Versement {

	public JFrame frame;
	private JTable tableVersement;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Versement window = new Versement();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Versement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*-----------------GET VERSEMENT---------------*/
		
		OperationVersement operation = new OperationVersement();		
		List<VersementBean> APIVersement = operation.getVersement();
		
		/*-----------------FIN GET VERSEMENT---------------*/
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1024, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		/*-----------------HEADER---------------*/
		
		Header header = new Header();
		header.header(panel, frame);
		
		/*-----------------HEADER---------------*/
		
		/*-----------------FOOTER---------------*/
		
		header.footer(panel);
		/*-----------------FIN FOOTER---------------*/
		
		Label Client = new Label("Versement");
		Client.setForeground(Color.BLACK);
		Client.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 14));
		Client.setBounds(62, 161, 97, 22);
		panel.add(Client);
		
		Button ajouter = new Button("Faire un versement");
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				AjoutVersement ajoutVersement = new AjoutVersement();
				ajoutVersement.frame.setVisible(true);
				ajoutVersement.frame.setLocationRelativeTo(null);
			}
		});
		ajouter.setForeground(Color.BLACK);
		ajouter.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		ajouter.setBackground(new Color(102, 255, 102));
		ajouter.setBounds(83, 194, 120, 26);
		panel.add(ajouter);
		
		Label Rechercher_label = new Label("Rechercher :");
		Rechercher_label.setBounds(594, 194, 77, 26);
		panel.add(Rechercher_label);
		
		TextField recherche_versement = new TextField();
		recherche_versement.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String query = recherche_versement.getText();
				//System.out.println(query);
				Recherche recherche = new Recherche();
				recherche.sort(query, model, tableVersement);
			}
		});
		recherche_versement.setBounds(687, 194, 261, 26);
		panel.add(recherche_versement);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 233, 865, 370);
		panel.add(scrollPane);
		
		tableVersement = new JTable();
		
		tableVersement.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVersement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableVersement.setBackground(Color.WHITE);
		model = new DefaultTableModel();
		Object[] column = {"N° de Versement","N° de Compte","Nom", "Montant [ARIARY]","Date de Versement"};
		
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		
		for (VersementBean bean : APIVersement) {
			
			row[0] = bean.getId();
			row[1] = bean.getNumCompte();
			row[2] = bean.getNomPrenoms();
			row[3] = bean.getMontantVersement();
			row[4] = bean.getDate();
			
			model.addRow(row);
		}
		
		
		
		
		tableVersement.setModel(model);


		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tableVersement.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tableVersement.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		TableColumnModel columnModel = tableVersement.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(1);
		columnModel.getColumn(1).setPreferredWidth(1);
		columnModel.getColumn(3).setPreferredWidth(1);
		
		scrollPane.setViewportView(tableVersement);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pdf pdf = new Pdf();
				MessageFormat header = new MessageFormat("Liste des Versements : ");
				pdf.print(tableVersement, header);
			}
		});
		btnImprimer.setBounds(215, 194, 89, 26);
		panel.add(btnImprimer);
		
		
		
		
	}
}
