package retrait;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import bank.ClientBean;
import bank.OperationClient;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.UIManager;
import javax.swing.JSpinner;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.TextField;

public class AjoutRetrait {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutRetrait window = new AjoutRetrait();
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
	public AjoutRetrait() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 0, 444, 55);
		panel.add(panel_1);
		
		Label ajouterRetrait = new Label("Faire un retrait");
		ajouterRetrait.setForeground(Color.WHITE);
		ajouterRetrait.setFont(new Font("Sylfaen", Font.BOLD, 24));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(131)
					.addComponent(ajouterRetrait, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(ajouterRetrait, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		Panel panel_1_1 = new Panel();
		panel_1_1.setBackground(SystemColor.activeCaption);
		panel_1_1.setBounds(-10, 427, 454, 34);
		panel.add(panel_1_1);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 454, Short.MAX_VALUE)
				.addGap(0, 454, Short.MAX_VALUE)
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE)
				.addGap(0, 20, Short.MAX_VALUE)
		);
		panel_1_1.setLayout(gl_panel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 61, 424, 147);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		JComboBox<String> ListeNumCompte = new JComboBox<String>();
		ListeNumCompte.setBounds(10, 76, 127, 20);
		panel_2.add(ListeNumCompte);
		
		JComboBox<String> idHiddenClient = new JComboBox<String>();
		idHiddenClient.setBounds(10, 111, 127, 20);
		panel_2.add(idHiddenClient);
		idHiddenClient.setVisible(false);
		

		Label textNom = new Label("");
		textNom.setFont(new Font("Tahoma", Font.BOLD, 11));
		textNom.setBounds(217, 46, 179, 22);
		panel_2.add(textNom);
		
		Label textPrenoms = new Label("");
		textPrenoms.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPrenoms.setBounds(239, 74, 175, 22);
		panel_2.add(textPrenoms);
		
		Label textSolde = new Label("");
		textSolde.setFont(new Font("Tahoma", Font.BOLD, 11));
		textSolde.setBounds(258, 109, 156, 22);
		panel_2.add(textSolde);
		
		JSpinner IDclient = new JSpinner();
		IDclient.setModel(new SpinnerNumberModel(new Long(0), new Long(0), null, new Long(1)));
		IDclient.setBounds(349, 17, 65, 20);
		IDclient.setVisible(false);
		
		//--------------------------------------------
		
		OperationClient operation = new OperationClient();		
		List<ClientBean> APIClient = operation.getClient();
		
		//--------------------------------------------
		//ListeNumCompte.addItem(" ");
		for (ClientBean bean : APIClient) {					
			ListeNumCompte.addItem(bean.getNumCompte());
			idHiddenClient.addItem(bean.getId().toString());		
		}

		//Long ID = (long) 0;
		ListeNumCompte.addActionListener(event -> {
			int i = ListeNumCompte.getSelectedIndex();
			String temp = idHiddenClient.getItemAt(i);
			Long idClient = (long) Integer.parseInt(temp);
			
			OperationClient op = new OperationClient();
			
			List<ClientBean> UNClientBean = op.getClientById(idClient);
			
			for (ClientBean unclient : UNClientBean) {				
				textNom.setText(unclient.getNom());
				textPrenoms.setText(unclient.getPrenoms());
				textSolde.setText(unclient.getSolde().toString()+ " ARIARY");
				IDclient.setValue(unclient.getId());
			}
			
			//System.out.println(UNClientBean);
		});
		
		
		JLabel lblSlectionnerUnNumro = new JLabel("S\u00E9lectionner ");
		lblSlectionnerUnNumro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSlectionnerUnNumro.setBounds(10, 31, 119, 20);
		panel_2.add(lblSlectionnerUnNumro);
		
		JLabel lblUnNumroDe = new JLabel("un num\u00E9ro de compte :");
		lblUnNumroDe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUnNumroDe.setBounds(10, 48, 119, 20);
		panel_2.add(lblUnNumroDe);
		
		Panel panel_3 = new Panel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBounds(158, 31, 5, 100);
		panel_2.add(panel_3);
		
		Label label = new Label("Informations compl\u00E9mentaires");
		label.setFont(new Font("Sylfaen", Font.ITALIC, 13));
		label.setBounds(203, 10, 179, 22);
		panel_2.add(label);
		
		Label labelNom = new Label("Nom :");
		labelNom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelNom.setBounds(170, 46, 41, 22);
		panel_2.add(labelNom);
		
		Label labelPrenoms = new Label("Pr\u00E9nom(s) :");
		labelPrenoms.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelPrenoms.setBounds(169, 74, 64, 22);
		panel_2.add(labelPrenoms);
		
		Label labelSolde = new Label("Solde Actuel :");
		labelSolde.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelSolde.setBounds(169, 109, 83, 22);
		panel_2.add(labelSolde);
		
		
		
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "D\u00E9tail du retrait", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1.setBounds(10, 219, 424, 153);
		panel.add(panel_2_1);
		
		JSpinner montantRetrait = new JSpinner();
		montantRetrait.setModel(new SpinnerNumberModel(new Long(1), new Long(1), null, new Long(1)));
		montantRetrait.setBounds(137, 101, 153, 22);
		panel_2_1.add(montantRetrait);
		
		Label labelMontantVersement = new Label("Montant \u00E0 retirer*");
		labelMontantVersement.setBounds(164, 73, 106, 22);
		panel_2_1.add(labelMontantVersement);
		
		
		panel_2_1.add(IDclient);
		
		Label labelNumCheque = new Label("Num\u00E9ro ch\u00E8que*");
		labelNumCheque.setBounds(164, 17, 95, 22);
		panel_2_1.add(labelNumCheque);
		
		TextField numChequeInput = new TextField();
		numChequeInput.setBounds(174, 45, 116, 22);
		panel_2_1.add(numChequeInput);
		
		Label label_1 = new Label("CQ");
		label_1.setBackground(Color.WHITE);
		label_1.setFont(new Font("Sylfaen", Font.BOLD, 14));
		label_1.setBounds(144, 45, 35, 22);
		panel_2_1.add(label_1);
		
		Button retourBoutton = new Button("Annuler");
		retourBoutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				frame.setVisible(false);
				Retrait retrait = new Retrait();
				retrait.frame.setVisible(true);
				retrait.frame.setLocationRelativeTo(null);
			}
		});
		retourBoutton.setForeground(Color.WHITE);
		retourBoutton.setFont(new Font("Sylfaen", Font.BOLD, 12));
		retourBoutton.setBackground(SystemColor.activeCaption);
		retourBoutton.setBounds(62, 378, 111, 34);
		panel.add(retourBoutton);
		
		Button ajouterBoutton = new Button("Retirer");
		ajouterBoutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Long solde = (Long) montantRetrait.getValue();
				Long id = (Long) IDclient.getValue();
				String numCheque = "CQ"+numChequeInput.getText();
				if (id == 0) {
					JOptionPane.showMessageDialog(frame,  "Veuillez séléctionner un Client SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
				} else {
				 //GO post
					RetraitBean retrait = new RetraitBean(id, "", numCheque,  "", solde, "");
					OperationRetrait operation = new OperationRetrait();
					Boolean vita = operation.ajoutRetrait(retrait);
					if (vita) {
						//fermena ny fenetre d'ajout de averina any amn client
						
						frame.setVisible(false);
						Retrait ret = new Retrait();
						ret.frame.setVisible(true);
						ret.frame.setLocationRelativeTo(null);
						JOptionPane.showMessageDialog(frame,  "Retrait réussi !");
						
					} else {
						//misy erreur
						JOptionPane.showMessageDialog(frame, "Une erreur s'est produite. Veuillez réessayez !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		ajouterBoutton.setForeground(Color.WHITE);
		ajouterBoutton.setFont(new Font("Sylfaen", Font.BOLD, 12));
		ajouterBoutton.setBackground(SystemColor.activeCaption);
		ajouterBoutton.setBounds(261, 378, 110, 34);
		panel.add(ajouterBoutton);
	}
}
