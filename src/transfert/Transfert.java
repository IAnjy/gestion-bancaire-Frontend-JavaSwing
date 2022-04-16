package transfert;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import bank.ClientBean;
import bank.OperationClient;
import header.Header;
import utils.Pdf;

import java.awt.Panel;
import java.awt.SystemColor;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import java.awt.Button;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Transfert {

	public JFrame frame;
	private JTable tableTransfert;
	DefaultTableModel model;

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
		
		
		/*-----------------GET TRANSFERT---------------*/
		
		OperationTransfert operation = new OperationTransfert();		
		List<TransfertBean> APITransfert = operation.getTransfert();
		
		/*-----------------FIN GET TRANSFERT---------------*/
		
		//--------------------------------------------
		
		OperationClient getCli = new OperationClient();		
		List<ClientBean> APIClient = getCli.getClient();
				
		//--------------------------------------------
		
		
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
		
		Label textNomExp = new Label("");
		textNomExp.setFont(new Font("Tahoma", Font.BOLD, 11));
		textNomExp.setBounds(177, 24, 170, 22);
		panelExpediteur.add(textNomExp);
		
		Label textPrenomExp = new Label("");
		textPrenomExp.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPrenomExp.setBounds(199, 56, 148, 22);
		panelExpediteur.add(textPrenomExp);
		
		Label textSoldeExp = new Label("");
		textSoldeExp.setFont(new Font("Tahoma", Font.BOLD, 11));
		textSoldeExp.setBounds(209, 91, 138, 22);
		panelExpediteur.add(textSoldeExp);
		
		Choice listeExp = new Choice();
		listeExp.setBounds(10, 65, 104, 20);
		panelExpediteur.add(listeExp);
		
		Choice IDhiddenExp = new Choice();
		IDhiddenExp.setBounds(32, 91, 67, 20);
		panelExpediteur.add(IDhiddenExp);
		IDhiddenExp.setVisible(false);
		
		
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
		
		Label textNomDest = new Label("");
		textNomDest.setFont(new Font("Tahoma", Font.BOLD, 11));
		textNomDest.setBounds(181, 32, 170, 22);
		panelDestinataire.add(textNomDest);
		
		Label textPrenomDest = new Label("");
		textPrenomDest.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPrenomDest.setBounds(203, 64, 148, 22);
		panelDestinataire.add(textPrenomDest);
		
		Label textSoldeDest = new Label("");
		textSoldeDest.setFont(new Font("Tahoma", Font.BOLD, 11));
		textSoldeDest.setBounds(213, 95, 157, 22);
		panelDestinataire.add(textSoldeDest);
		
		Choice listeDest = new Choice();
		listeDest.setBounds(10, 69, 104, 20);
		panelDestinataire.add(listeDest);
		
		Choice IDhiddenDest = new Choice();
		IDhiddenDest.setBounds(32, 95, 67, 20);
		panelDestinataire.add(IDhiddenDest);
		IDhiddenDest.setVisible(false);
		
		//--------------------------------------------------------------------------
		
		for (ClientBean bean : APIClient) {			
			
			listeExp.addItem(bean.getNumCompte());
			IDhiddenExp.addItem(bean.getId().toString());
			
			listeDest.addItem(bean.getNumCompte());
			IDhiddenDest.addItem(bean.getId().toString());
		}
	
		
		//--------------------------------------------------------------------------
		JSpinner IDclientExp = new JSpinner();
		IDclientExp.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		IDclientExp.setBounds(138, 173, 39, 20);
		panel.add(IDclientExp);
		IDclientExp.setVisible(false);
		
		JSpinner IDclientDest = new JSpinner();
		IDclientDest.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		IDclientDest.setBounds(742, 173, 39, 20);
		panel.add(IDclientDest);
		IDclientDest.setVisible(false);
		
		//---------------------Fill INformation------------------------
		
			listeExp.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					
					int i = listeExp.getSelectedIndex();
					String temp = IDhiddenExp.getItem(i);
					System.out.println(temp);
					Long idClientExp = (long) Integer.parseInt(temp);
					
					OperationClient op = new OperationClient();
					
					List<ClientBean> UNClientBean = op.getClientById(idClientExp);
					
					for (ClientBean unclient : UNClientBean) {				
						textNomExp.setText(unclient.getNom());
						textPrenomExp.setText(unclient.getPrenoms());
						textSoldeExp.setText(unclient.getSolde().toString()+ " ARIARY");
						IDclientExp.setValue(unclient.getId());
						//IDclient.setValue(unclient.getId());
					}
				}
			});
			
			listeDest.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					
					int i = listeDest.getSelectedIndex();
					String temp = IDhiddenDest.getItem(i);
					
					Long idClientDest = (long) Integer.parseInt(temp);
					
					OperationClient op = new OperationClient();
					
					List<ClientBean> UNClientBean = op.getClientById(idClientDest);
					
					for (ClientBean unclient : UNClientBean) {				
						textNomDest.setText(unclient.getNom());
						textPrenomDest.setText(unclient.getPrenoms());
						textSoldeDest.setText(unclient.getSolde().toString()+ " ARIARY");
						IDclientDest.setValue(unclient.getId());
					}
				}
			});
				
		//---------------------Fill INformation------------------------
		JSpinner montantTransfert = new JSpinner();
		montantTransfert.setFont(new Font("Tahoma", Font.PLAIN, 12));
		montantTransfert.setModel(new SpinnerNumberModel(new Long(100), new Long(100), null, new Long(1)));
		montantTransfert.setBounds(444, 224, 129, 28);
		panel.add(montantTransfert);
		
		
		
		//------------------------POST------------------------------
		
		Button transferer = new Button("TRANSFERER");
		transferer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				Long idExp = (Long) IDclientExp.getValue();
				Long idDest = (Long) IDclientDest.getValue();
				
				Long montTransfert = (Long) montantTransfert.getValue();
				
				if(idExp == 0 || idDest ==0) {
					JOptionPane.showMessageDialog(frame,  "Veuillez séléctionner un expéditeur / un destinataire SVP!", "ERREUR", JOptionPane.ERROR_MESSAGE);//System.out.println("tsisy select");
				}else {
					
					if(idExp == idDest) {
						JOptionPane.showMessageDialog(frame,  "Desolé, vous ne pouvez pas faire un transfert sur un même compte!", "ERREUR", JOptionPane.ERROR_MESSAGE);
					}else {
						//System.out.println("Transfert...");
						Integer reply = JOptionPane.showConfirmDialog(frame,  "Voulez-vous vraiment transférer ?! Cette opération est irréversible", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							TransfertBean transfert = new TransfertBean(null, montTransfert, idExp.toString(), idDest.toString(), "");
							OperationTransfert op = new OperationTransfert();
							Boolean vita = op.ajoutTransfert(transfert);
							if (vita) {
								frame.setVisible(false);
								Transfert trans = new Transfert();
								trans.frame.setVisible(true);
								trans.frame.setLocationRelativeTo(null);
								JOptionPane.showMessageDialog(frame,  "Transfert réussi !");
								
							} else {
								JOptionPane.showMessageDialog(frame, "Une erreur s'est produite. Veuillez réessayez !", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				
				
			}
		});
		
		
		//------------------------POST------------------------------
		
		
		transferer.setForeground(Color.WHITE);
		transferer.setFont(new Font("Sylfaen", Font.BOLD, 12));
		transferer.setBackground(SystemColor.activeCaption);
		transferer.setBounds(444, 279, 129, 40);
		panel.add(transferer);
		
		Label labelMontantTransfert = new Label("Montant*");
		labelMontantTransfert.setBounds(444, 199, 48, 22);
		panel.add(labelMontantTransfert);
		
		Label historique = new Label("Historique(s) des tranferts");
		historique.setForeground(Color.BLACK);
		historique.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 14));
		historique.setBounds(422, 342, 184, 22);
		panel.add(historique);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(157, 370, 700, 229);
		panel.add(scrollPane);
		
		tableTransfert = new JTable();
		tableTransfert.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTransfert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableTransfert.setBackground(Color.WHITE);
		model = new DefaultTableModel();
		
		Object[] column = {"N° Transfert","N° Compte Expéditeur","N° Compte Destinataire", "Montant [ARIARY]","Date de Transfert"};
		model.setColumnIdentifiers(column);
		Object[] row = new Object[5];
		
		for (TransfertBean bean : APITransfert) {
			
			row[0] = bean.getId();
			row[3] = bean.getMontantTransfert();
			row[1] = bean.getNumCompteExpediteur();
			row[2] = bean.getNumCompteDestinataire();
			row[4] = bean.getDate();
			
			model.addRow(row);
		}
		
		tableTransfert.setModel(model);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tableTransfert.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tableTransfert.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		scrollPane.setViewportView(tableTransfert);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pdf pdf = new Pdf();
				MessageFormat header = new MessageFormat("Liste des Transferts : ");
				pdf.print(tableTransfert, header);
			}
		});
		btnImprimer.setBounds(768, 598, 89, 26);
		panel.add(btnImprimer);
		
		
		
		
	
		
		
		
	}
}
