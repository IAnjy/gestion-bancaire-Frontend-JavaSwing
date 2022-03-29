package bank;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import header.Header;
import retrait.OperationRetrait;
import retrait.Retrait;
import retrait.RetraitBean;
import versement.OperationVersement;
import versement.Versement;
import versement.VersementBean;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Button;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MouvementBancaire {

	JFrame frame;
	private JTable tableVersement;
	private JTable tableRetrait;
	DefaultTableModel modelVersement;
	DefaultTableModel modelRetrait;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MouvementBancaire window = new MouvementBancaire(null);
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
	 * @param id 
	 */
	public MouvementBancaire(Long id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param id 
	 */
	private void initialize(Long id) {
		
		//System.out.println(id);
		//----------------API--------------------------------------------------
		
		OperationClient operation = new OperationClient();		
		List<ClientBean> UNClientBean = operation.getClientById(id);
		
		OperationVersement versements = new OperationVersement();
		List<VersementBean> unVersement = versements.getVersementByClientId(id);
		
		OperationRetrait retraits = new OperationRetrait();
		List<RetraitBean> unRetrait = retraits.getRetraitByClientId(id);
		
		
		//----------------API--------------------------------------------------
		
		frame = new JFrame();
		frame.setLocationRelativeTo(frame);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1024, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Header header = new Header();
		header.footer(panel);
		
		

		Panel panel_1 = new Panel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 5, 1018, 154);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		Label TITLE = new Label("MOUVEMENT BANCAIRE");
		TITLE.setBounds(545, 10, 315, 20);
		TITLE.setForeground(Color.WHITE);
		TITLE.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 24));
		panel_1.add(TITLE);
		
		JLabel lblLabel = new JLabel("label");
		lblLabel.setIcon(new ImageIcon("D:\\Bossy\\java\\BanqueInterface\\img\\BankTR.png"));
		lblLabel.setBounds(79, -11, 269, 154);
		panel_1.add(lblLabel);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(500, 36, 4, 102);
		panel_1.add(panel_2);
		
		Label numCompte = new Label("Num\u00E9ro de compte :");
		numCompte.setFont(new Font("Century Gothic", Font.BOLD, 11));
		numCompte.setBounds(512, 39, 126, 22);
		panel_1.add(numCompte);
		
		Label nom = new Label("Nom :");
		nom.setFont(new Font("Century Gothic", Font.BOLD, 11));
		nom.setBounds(510, 78, 45, 22);
		panel_1.add(nom);
		
		Label solde = new Label("Solde actuel :");
		solde.setFont(new Font("Century Gothic", Font.BOLD, 11));
		solde.setBounds(512, 121, 126, 22);
		panel_1.add(solde);
		
		Label numCompteLabel = new Label("");
		numCompteLabel.setFont(new Font("Sylfaen", Font.BOLD, 12));
		numCompteLabel.setBounds(653, 39, 276, 22);
		panel_1.add(numCompteLabel);
		
		Label nomLabel = new Label("");
		nomLabel.setFont(new Font("Sylfaen", Font.BOLD, 12));
		nomLabel.setBounds(653, 78, 355, 22);
		panel_1.add(nomLabel);
		
		Label soldeLabel = new Label("");
		soldeLabel.setFont(new Font("Sylfaen", Font.BOLD, 12));
		soldeLabel.setBounds(653, 121, 224, 22);
		panel_1.add(soldeLabel);
		
		for (ClientBean unclient : UNClientBean) {
			numCompteLabel.setText(unclient.getNumCompte());
			nomLabel.setText(unclient.getNom() +" "+ unclient.getPrenoms());
			soldeLabel.setText(unclient.getSolde().toString()+" ARIARY");
			
			//IdClientInput.setValue(unclient.getId());	
			
		}
		
		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.activeCaption);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(501, 184, 9, 370);
		panel.add(separator);
		
		Label Versement = new Label("VERSEMENT");
		Versement.setFont(new Font("Sylfaen", Font.BOLD, 16));
		Versement.setBounds(34, 175, 116, 22);
		panel.add(Versement);
		
		Label Retrait = new Label("RETRAIT");
		Retrait.setFont(new Font("Sylfaen", Font.BOLD, 16));
		Retrait.setBounds(905, 175, 75, 22);
		panel.add(Retrait);
		
		Button retourBoutton = new Button("RETOUR");
		retourBoutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				Bank bank = new Bank();
				bank.frame.setVisible(true);
				bank.frame.setLocationRelativeTo(null);
			}
		});
		retourBoutton.setForeground(Color.WHITE);
		retourBoutton.setFont(new Font("Sylfaen", Font.BOLD, 14));
		retourBoutton.setBackground(SystemColor.activeCaption);
		retourBoutton.setBounds(792, 574, 141, 45);
		panel.add(retourBoutton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 203, 425, 351);
		panel.add(scrollPane);
		
		tableVersement = new JTable();
		modelVersement = new DefaultTableModel();
		Object[] column = {"N° de Versement","Montant [ARIARY]","Date de Versement"};
		modelVersement.setColumnIdentifiers(column);
		
		Object[] row = new Object[3];		
		for (VersementBean bean : unVersement) {
			row[0] = bean.getId();
			row[1] = bean.getMontantVersement();
			row[2] = bean.getDate();
			modelVersement.addRow(row);
		}		
		tableVersement.setModel(modelVersement);
		
		
		scrollPane.setViewportView(tableVersement);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(541, 203, 439, 351);
		panel.add(scrollPane_1);
		
		tableRetrait = new JTable();
		modelRetrait = new DefaultTableModel();
		Object[] column1 = {"N° de Chèque","Montant [ARIARY]","Date de Retrait"};
		modelRetrait.setColumnIdentifiers(column1);
		
		Object[] row1 = new Object[3];
		for (RetraitBean bean : unRetrait) {
			row1[0] = bean.getNumCheque();
			row1[1] = bean.getMontantRetrait();
			row1[2] = bean.getDate();
			modelRetrait.addRow(row1);
		}		
		tableRetrait.setModel(modelRetrait);
		
		
		scrollPane_1.setViewportView(tableRetrait);
	}
}
