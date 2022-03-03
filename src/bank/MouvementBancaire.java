package bank;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import header.Header;
import retrait.Retrait;
import versement.Versement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MouvementBancaire {

	JFrame frame;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MouvementBancaire window = new MouvementBancaire();
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
	public MouvementBancaire() {
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
		
		Label numCompteLabel = new Label("Num\u00E9ro de compte :");
		numCompteLabel.setFont(new Font("Century Gothic", Font.BOLD, 11));
		numCompteLabel.setBounds(512, 39, 126, 22);
		panel_1.add(numCompteLabel);
		
		Label nomLabel = new Label("Nom :");
		nomLabel.setFont(new Font("Century Gothic", Font.BOLD, 11));
		nomLabel.setBounds(510, 78, 45, 22);
		panel_1.add(nomLabel);
		
		Label soldeLabel = new Label("Solde actuel :");
		soldeLabel.setFont(new Font("Century Gothic", Font.BOLD, 11));
		soldeLabel.setBounds(512, 121, 126, 22);
		panel_1.add(soldeLabel);
		
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
		
		table = new JTable();
		table.setBounds(34, 203, 425, 351);
		panel.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(541, 203, 439, 351);
		panel.add(table_1);
	}
}
