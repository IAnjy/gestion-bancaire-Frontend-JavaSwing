package transfert;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bank.Bank;
import header.Header;
import retrait.Retrait;
import versement.Versement;

import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JSpinner;
import java.awt.Button;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;

public class Transfert {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transfert window = new Transfert();
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
	public Transfert() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setLocationRelativeTo(frame);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1024, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		/*-----------------HEADER---------------*/		
		Header header = new Header();
		header.header(panel, frame);
		/*-----------------FIN HEADER---------------*/
		

		/*-----------------FOOTER---------------*/		
		header.footer(panel);
		/*-----------------FIN FOOTER---------------*/
		
		Label transfert = new Label("TRANSFERT");
		transfert.setFont(new Font("Sylfaen", Font.BOLD, 16));
		transfert.setBounds(457, 160, 116, 22);
		panel.add(transfert);
		
		JPanel panelExpediteur = new JPanel();
		panelExpediteur.setBorder(new TitledBorder(null, "EXPEDITEUR", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelExpediteur.setBounds(55, 188, 357, 146);
		panel.add(panelExpediteur);
		panelExpediteur.setLayout(null);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBackground(SystemColor.activeCaption);
		separator_1_1.setBounds(120, 23, 9, 108);
		panelExpediteur.add(separator_1_1);
		
		JComboBox<String> listeExpediteur = new JComboBox<String>();
		listeExpediteur.setBounds(10, 60, 101, 20);
		panelExpediteur.add(listeExpediteur);
		
		JLabel lblNumroDeCompte_1 = new JLabel("num\u00E9ro de compte :");
		lblNumroDeCompte_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumroDeCompte_1.setBounds(10, 39, 106, 20);
		panelExpediteur.add(lblNumroDeCompte_1);
		
		Label labelNom = new Label("Nom :");
		labelNom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelNom.setBounds(130, 28, 41, 22);
		panelExpediteur.add(labelNom);
		
		Label labelPrenoms = new Label("Pr\u00E9nom(s) :");
		labelPrenoms.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelPrenoms.setBounds(129, 56, 64, 22);
		panelExpediteur.add(labelPrenoms);
		
		Label labelSolde = new Label("Solde Actuel :");
		labelSolde.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelSolde.setBounds(129, 91, 83, 22);
		panelExpediteur.add(labelSolde);
		
		JPanel panelDestinataire = new JPanel();
		panelDestinataire.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DESTINATAIRE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDestinataire.setBounds(610, 188, 357, 146);
		panel.add(panelDestinataire);
		panelDestinataire.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(SystemColor.activeCaption);
		separator_1.setBounds(126, 23, 9, 108);
		panelDestinataire.add(separator_1);
		
		JComboBox<String> listeExpediteur_1 = new JComboBox<String>();
		listeExpediteur_1.setBounds(15, 64, 101, 20);
		panelDestinataire.add(listeExpediteur_1);
		
		JLabel lblNumroDeCompte = new JLabel("num\u00E9ro de compte :");
		lblNumroDeCompte.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumroDeCompte.setBounds(15, 43, 106, 20);
		panelDestinataire.add(lblNumroDeCompte);
		
		Label labelNom_1 = new Label("Nom :");
		labelNom_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelNom_1.setBounds(134, 35, 41, 22);
		panelDestinataire.add(labelNom_1);
		
		Label labelPrenoms_1 = new Label("Pr\u00E9nom(s) :");
		labelPrenoms_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelPrenoms_1.setBounds(133, 63, 64, 22);
		panelDestinataire.add(labelPrenoms_1);
		
		Label labelSolde_1 = new Label("Solde Actuel :");
		labelSolde_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelSolde_1.setBounds(133, 98, 83, 22);
		panelDestinataire.add(labelSolde_1);
		
		Label historique = new Label("Historique(s) des tranferts");
		historique.setForeground(Color.BLACK);
		historique.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 14));
		historique.setBounds(422, 342, 184, 22);
		panel.add(historique);
		
		table = new JTable();
		table.setBounds(195, 370, 616, 229);
		panel.add(table);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner.setModel(new SpinnerNumberModel(new Long(100), new Long(100), null, new Long(1)));
		spinner.setBounds(444, 224, 129, 28);
		panel.add(spinner);
		
		Button tranferer = new Button("TRANSFERER");
		tranferer.setForeground(Color.WHITE);
		tranferer.setFont(new Font("Sylfaen", Font.BOLD, 12));
		tranferer.setBackground(SystemColor.activeCaption);
		tranferer.setBounds(444, 279, 129, 40);
		panel.add(tranferer);
		
		Label labelMontantTransfert = new Label("Montant*");
		labelMontantTransfert.setBounds(444, 199, 48, 22);
		panel.add(labelMontantTransfert);
		
		
	}
}
