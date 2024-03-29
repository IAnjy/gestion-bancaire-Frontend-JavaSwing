package bank;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import header.Header;
import utils.Pdf;
import utils.Recherche;

import java.awt.TextField;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class Bank {

	public JFrame frame;
	private JTable tableClient;
	DefaultTableModel model;
	List<ClientBean> lista ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bank window = new Bank();
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
	public Bank() {
		initialize();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		
		/*-----------------GET CLIENT---------------*/
		
		OperationClient operation = new OperationClient();		
		List<ClientBean> APIClient = operation.getClient();
		
		
		/*-----------------FIN GET CLIENT---------------*/
		
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
		
		
		
		/*-----------------FOOTER---------------*/		
		header.footer(panel);
		/*-----------------FIN FOOTER---------------*/
		
		Label Client = new Label("Client(es)");
		Client.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 14));
		Client.setForeground(Color.BLACK);
		Client.setBounds(61, 160, 97, 22);
		panel.add(Client);
		
		Button ajouter = new Button("Ajouter");
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String NumCompteFarany = (String) model.getValueAt(tableClient.getRowCount()-1, 0);
				String temp = NumCompteFarany.substring(1, NumCompteFarany.length());// manala anle "C" ammn numCompte
				int next = Integer.parseInt(temp) + 1;
				String nextNumCompte = "";
				System.out.println(next);				
				if (next < 99) {
					nextNumCompte = "C000"+ next;					
				} 
				else {
					nextNumCompte = "C00"+ next;					
				}
				
				
				//String LastNumCompte = 
				//Window.frame.hide();
				frame.setVisible(false);
				AjoutClient ajoutClient = new AjoutClient(nextNumCompte);
				ajoutClient.frame.setVisible(true);
				ajoutClient.frame.setLocationRelativeTo(null);
			}
		});
		ajouter.setForeground(new Color(0, 0, 0));
		ajouter.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		ajouter.setBackground(new Color(102, 255, 102));
		ajouter.setBounds(77, 194, 70, 26);
		panel.add(ajouter);
		
		Label Rechercher_label = new Label("Rechercher :");
		Rechercher_label.setBounds(619, 194, 77, 26);
		panel.add(Rechercher_label);
		
		TextField recherche_client = new TextField();
		recherche_client.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String query = recherche_client.getText();
				//System.out.println(query);
				Recherche recherche = new Recherche();
				recherche.sort(query, model, tableClient);
			}
		});
		recherche_client.setBounds(702, 194, 240, 26);
		panel.add(recherche_client);
		
		Button modifier = new Button("Modifier");
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer i = tableClient.getSelectedRow();
				if (i>=0) {		
					Long id = (Long) model.getValueAt(i, 4);
					//System.out.println(id);							
					//Window.frame.hide();
					frame.setVisible(false);
					ModifClient modifClient = new ModifClient(id);
					modifClient.frame.setVisible(true);
					modifClient.frame.setLocationRelativeTo(null);
				} else {
					//erreur mila miselecta
					JOptionPane.showMessageDialog(frame,  "Veuillez s�l�ctionner une ligne SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		modifier.setForeground(Color.BLACK);
		modifier.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		modifier.setBackground(new Color(255, 255, 51));
		modifier.setBounds(153, 194, 70, 26);
		panel.add(modifier);
		
		Button supprimer = new Button("Supprimer");
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer i = tableClient.getSelectedRow();
				if (i>=0) {
					Integer reply = JOptionPane.showConfirmDialog(frame,  "Voulez-vous vraiment supprimer ?!", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						//get id de GO http request
						Long id = (Long) model.getValueAt(i, 4);
						//System.out.println(id);
						
						OperationClient operation = new OperationClient();
						Boolean vita = operation.delete(id);
						if (vita) {
							//fermena ny fenetre d'ajout de averina any amn client
							
							frame.setVisible(false);
							Bank bank = new Bank();
							bank.frame.setVisible(true);
							bank.frame.setLocationRelativeTo(null);
							JOptionPane.showMessageDialog(frame,  "Supprim� ! ");
						} else {
							JOptionPane.showMessageDialog(frame,  "Une erreur s'est produite ! R�esseyez plus tard", "ERREUR", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				} else {
					//erreur mila miselecta
					JOptionPane.showMessageDialog(frame,  "Veuillez s�l�ctionner une ligne SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		supprimer.setForeground(Color.BLACK);
		supprimer.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		supprimer.setBackground(new Color(255, 0, 0));
		supprimer.setBounds(229, 194, 77, 26);
		panel.add(supprimer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 240, 865, 370);
		panel.add(scrollPane);
		
		tableClient = new JTable();
		tableClient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableClient.setBackground(Color.WHITE);
		model = new DefaultTableModel();
		Object[] column = {"Num�ro Compte","Nom","Pr�noms", "SOLDE [ARIARY]","id"};
		
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		//System.out.println(lista);
		
		for (ClientBean bean : APIClient) {
			
			row[0] = bean.getNumCompte();
			row[1] = bean.getNom();
			row[2] = bean.getPrenoms();
			row[3] = bean.getSolde();
			row[4] = bean.getId();
			
			model.addRow(row);
		}
		
		
		
		tableClient.setModel(model);
		tableClient.removeColumn(tableClient.getColumnModel().getColumn(4));
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tableClient.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		
		TableColumnModel columnModel = tableClient.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(1);
		
		//------------------------------------------------------------------------------
		columnModel.getColumn(1).setPreferredWidth(2);
		columnModel.getColumn(2).setPreferredWidth(2);
		columnModel.getColumn(3).setPreferredWidth(1);
		
		
		scrollPane.setViewportView(tableClient);
		
		Button mouvementBancaire = new Button("Mouvement Bancaire");
		mouvementBancaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer i = tableClient.getSelectedRow();
				if (i>=0) {		
					Long id = (Long) model.getValueAt(i, 4);
					//System.out.println(id);							
					//Window.frame.hide();
					frame.setVisible(false);
					MouvementBancaire mouvementBancaire = new MouvementBancaire(id);
					mouvementBancaire.frame.setVisible(true);
					mouvementBancaire.frame.setLocationRelativeTo(null);
					
					
					//ModifClient modifClient = new ModifClient(id);
					//modifClient.frame.setVisible(true);
					//modifClient.frame.setLocationRelativeTo(null);
				} else {
					//erreur mila miselecta
					JOptionPane.showMessageDialog(frame,  "Veuillez s�l�ctionner une ligne SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mouvementBancaire.setForeground(Color.WHITE);
		mouvementBancaire.setFont(new Font("Sylfaen", Font.BOLD, 12));
		mouvementBancaire.setBackground(SystemColor.activeCaption);
		mouvementBancaire.setBounds(312, 194, 141, 26);
		panel.add(mouvementBancaire);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			/*	StringBuilder builder = new StringBuilder();
				builder.append("line 1");
				builder.append(System.getProperty("line.separator"));
				builder.append("line 2");
				builder.append(System.getProperty("line.separator"));
				builder.append("line 3");
				
				*/
				//MessageFormat header = new MessageFormat(builder.toString());
				
				//String newLine = System.getProperty("line.separator");
				
				//System.out.println(builder.toString());
				//MessageFormat titre = new MessageFormat("Liste des clients : ");
				Pdf pdf = new Pdf();
				MessageFormat header = new MessageFormat("Liste des clients : ");
				pdf.print(tableClient, header);
				
			}
		});
		btnImprimer.setBounds(459, 194, 89, 26);
		panel.add(btnImprimer);
	}
	
	public void resetColor(Panel panely) {
		// TODO Auto-generated method stub
		panely.setBackground(new java.awt.Color(240,240,240));
	}

	public void setColor(Panel panely) {
		// TODO Auto-generated method stub
		panely.setBackground(new java.awt.Color(255,255,255));
	}
}
