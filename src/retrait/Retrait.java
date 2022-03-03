package retrait;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JLabel;

import header.Header;
import utils.Recherche;
import versement.AjoutVersement;
import versement.OperationVersement;
import versement.VersementBean;

import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Retrait {

	public JFrame frame;
	private JTable table;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Retrait window = new Retrait();
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
	public Retrait() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*-----------------GET VERSEMENT---------------*/
		
		OperationRetrait operation = new OperationRetrait();		
		List<RetraitBean> APIRetrait = operation.getRetrait();
		
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
		
		Label Client = new Label("Retrait");
		Client.setForeground(Color.BLACK);
		Client.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 14));
		Client.setBounds(67, 159, 97, 22);
		panel.add(Client);
		
		Button ajouterRetrait = new Button("Faire un retrait");
		ajouterRetrait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				AjoutRetrait ajoutRetrait = new AjoutRetrait();
				ajoutRetrait.frame.setVisible(true);
				ajoutRetrait.frame.setLocationRelativeTo(null);
			}
		});
		ajouterRetrait.setForeground(Color.BLACK);
		ajouterRetrait.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		ajouterRetrait.setBackground(new Color(102, 255, 102));
		ajouterRetrait.setBounds(82, 194, 124, 26);
		panel.add(ajouterRetrait);
		
		Label Rechercher_label = new Label("Rechercher :");
		Rechercher_label.setBounds(604, 194, 77, 26);
		panel.add(Rechercher_label);
		
		TextField recherche_retrait = new TextField();
		recherche_retrait.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String query = recherche_retrait.getText();
				//System.out.println(query);
				Recherche recherche = new Recherche();
				recherche.sort(query, model, table);
			}
		});
		recherche_retrait.setBounds(687, 194, 261, 26);
		panel.add(recherche_retrait);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 233, 865, 370);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		model = new DefaultTableModel();
		Object[] column = {"id","N° de Compte","N° de Chèque","Nom", "Montant [ARIARY]","Date de Retrait"};
		
		Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
		
		for (RetraitBean bean : APIRetrait) {
			
			row[0] = bean.getId();
			row[1] = bean.getNumCheque();
			row[2] = bean.getNumCompte();
			row[3] = bean.getNomPrenoms();
			row[4] = bean.getMontantRetrait();
			row[5] = bean.getDate();
			
			model.addRow(row);
		}
		

		table.setModel(model);
		table.removeColumn(table.getColumnModel().getColumn(0));
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(1);
		columnModel.getColumn(1).setPreferredWidth(1);
		columnModel.getColumn(3).setPreferredWidth(1);
		
		scrollPane.setViewportView(table);
		
	}

}
