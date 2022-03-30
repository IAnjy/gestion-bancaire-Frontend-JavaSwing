package bank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.TextField;
import javax.swing.JSpinner;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ModifClient {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifClient window = new ModifClient(null);
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
	public ModifClient(Long id) {
		if (id == null) {
			System.out.println("null");
			JOptionPane.showMessageDialog(null, "Une Erreur s'est produite Veuillez r�essayer!", "ALERTE", JOptionPane.INFORMATION_MESSAGE);
			Bank client = new Bank();
			client.frame.setVisible(true);
			client.frame.setLocationRelativeTo(null);			
			//System.exit(0);
		} else {
			initialize(id);
		}
		
		/*		
		initialize(id);
		*/
	}

	/**
	 * Initialize the contents of the frame.
	 * @param id 
	 */
	private void initialize(Long id) {
		
		
		OperationClient operation = new OperationClient();
		
		List<ClientBean> UNClientBean = operation.getClientById(id);
		
		
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 460, 500);     
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel cadreModifClient = new Panel();
		frame.getContentPane().add(cadreModifClient, BorderLayout.CENTER);
		cadreModifClient.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 0, 454, 55);
		cadreModifClient.add(panel_1);
		
		Label ajouterClient = new Label("Modifier un(e) client(e)");
		ajouterClient.setForeground(Color.WHITE);
		ajouterClient.setFont(new Font("Sylfaen", Font.BOLD, 24));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 454, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(100)
					.addComponent(ajouterClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 55, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(ajouterClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel_1.setLayout(gl_panel_1);
		
		Panel panel_1_1 = new Panel();
		panel_1_1.setBackground(SystemColor.activeCaption);
		panel_1_1.setBounds(0, 451, 454, 20);
		cadreModifClient.add(panel_1_1);
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
		
		JPanel cadrage = new JPanel();
		cadrage.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Client(e)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		cadrage.setBounds(10, 61, 434, 373);
		cadreModifClient.add(cadrage);
		
		Label numCompte = new Label("N\u00B0 compte :");
		numCompte.setForeground(Color.BLACK);
		numCompte.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		
		TextField numCompteInput = new TextField();
		numCompteInput.setEditable(false);
		numCompteInput.setEnabled(false);
		
		Label nom = new Label("Nom  :");
		nom.setForeground(Color.BLACK);
		nom.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		
		TextField nomInput = new TextField();
		
		Label prenoms = new Label("Pr\u00E9nom(s) :");
		prenoms.setForeground(Color.BLACK);
		prenoms.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		
		TextField prenomInput = new TextField();
		
		Label solde = new Label("Solde :");
		solde.setForeground(Color.BLACK);
		solde.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		
		JSpinner SoldeInput = new JSpinner();
		SoldeInput.setEnabled(false);
		SoldeInput.setModel(new SpinnerNumberModel(new Long(100), new Long(100), null, new Long(1)));
		
		JSpinner IdClientInput = new JSpinner();
		IdClientInput.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		IdClientInput.setVisible(false);
		
		Button retourBoutton = new Button("Retour");
		retourBoutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				Bank bank = new Bank();
				bank.frame.setVisible(true);
				bank.frame.setLocationRelativeTo(null);
			}
		});
		retourBoutton.setForeground(Color.WHITE);
		retourBoutton.setFont(new Font("Sylfaen", Font.BOLD, 12));
		retourBoutton.setBackground(SystemColor.activeCaption);
		
		for (ClientBean unclient : UNClientBean) {
			
			numCompteInput.setText(unclient.getNumCompte());
			nomInput.setText(unclient.getNom());
			prenomInput.setText(unclient.getPrenoms());
			SoldeInput.setValue(unclient.getSolde());	
			IdClientInput.setValue(unclient.getId());	
			
		}
		
		Button modifierBoutton = new Button("Modifier");
		modifierBoutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String numCompte = numCompteInput.getText();
				String nom = nomInput.getText();
				String prenom = prenomInput.getText();
				Long solde = (Long) SoldeInput.getValue();
				Long id = (Long) IdClientInput.getValue();
				
				//System.out.println(id);
				if (numCompte.equals("") || nom.equals("") || prenom.equals("")) {
					JOptionPane.showMessageDialog(frame, "Les champs ne doivent pas �tre vides !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else {					
					//go Put
					ClientBean client = new ClientBean(id,numCompte, nom, prenom, solde);
					OperationClient operation = new OperationClient();
					Boolean vita = operation.update(client);
					if (vita) {
						//fermena ny fenetre d'ajout de averina any amn client
						JOptionPane.showMessageDialog(frame,  "Modification r�ussie !");
						frame.setVisible(false);
						Bank bank = new Bank();
						bank.frame.setVisible(true);
						bank.frame.setLocationRelativeTo(null);
						
					} else {
						//misy erreur
						JOptionPane.showMessageDialog(frame, "Une erreur s'est produite. Veuillez r�essayez !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
		});
		modifierBoutton.setForeground(Color.WHITE);
		modifierBoutton.setFont(new Font("Sylfaen", Font.BOLD, 12));
		modifierBoutton.setBackground(SystemColor.activeCaption);
		
	
		
		
		GroupLayout gl_cadrage = new GroupLayout(cadrage);
		gl_cadrage.setHorizontalGroup(
			gl_cadrage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_cadrage.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_cadrage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cadrage.createSequentialGroup()
							.addGroup(gl_cadrage.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_cadrage.createSequentialGroup()
									.addGap(40)
									.addComponent(numCompte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(numCompteInput, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_cadrage.createSequentialGroup()
									.addGap(40)
									.addComponent(nom, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(nomInput, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_cadrage.createSequentialGroup()
									.addGap(40)
									.addComponent(prenoms, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(prenomInput, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_cadrage.createSequentialGroup()
									.addGap(40)
									.addComponent(solde, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(SoldeInput, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_cadrage.createSequentialGroup()
									.addGap(144)
									.addComponent(retourBoutton, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
									.addGap(25)
									.addComponent(modifierBoutton, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
							.addGap(32))
						.addGroup(Alignment.TRAILING, gl_cadrage.createSequentialGroup()
							.addComponent(IdClientInput, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(190))))
		);
		gl_cadrage.setVerticalGroup(
			gl_cadrage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cadrage.createSequentialGroup()
					.addContainerGap()
					.addComponent(IdClientInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addGroup(gl_cadrage.createParallelGroup(Alignment.LEADING)
						.addComponent(numCompte, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(numCompteInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_cadrage.createParallelGroup(Alignment.LEADING)
						.addComponent(nom, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(nomInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_cadrage.createParallelGroup(Alignment.LEADING)
						.addComponent(prenoms, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(prenomInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_cadrage.createParallelGroup(Alignment.LEADING)
						.addComponent(solde, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(SoldeInput, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_cadrage.createParallelGroup(Alignment.LEADING, false)
						.addComponent(retourBoutton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(modifierBoutton, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addGap(13))
		);
		cadrage.setLayout(gl_cadrage);
		
		
		
	}
}
