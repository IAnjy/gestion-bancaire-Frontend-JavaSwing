package header;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTable;

import bank.AjoutClient;
import bank.Bank;
import bank.ModifClient;
import versement.Versement;

public class Template {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Template window = new Template();
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
	public Template() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		/*-----------------HEADER---------------*/
		Panel panel_1 = new Panel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 0, 794, 87);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		Label TITLE = new Label("GESTION BANCAIRE");
		TITLE.setBounds(270, 5, 254, 38);
		TITLE.setForeground(Color.WHITE);
		TITLE.setFont(new Font("Sylfaen", Font.BOLD, 24));
		panel_1.add(TITLE);
		
		Panel PanelClient = new Panel();
		PanelClient.setFont(new Font("Sylfaen", Font.BOLD, 12));
		PanelClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Header color = new Header();
				color.setColor(PanelClient);
			}
			
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				Header color = new Header();
				color.resetColor(PanelClient);
			}
			
			
			/*public void setColor(Panel panelClient) {
				panelClient.setBackground(new java.awt.Color(255,255,255));
			}
			
			public void resetColor(Panel panely) {
				panely.setBackground(new java.awt.Color(240,240,240));
			}
			*/
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
					//Window.frame.hide();
					frame.setVisible(false);
					Bank bank = new Bank();
					bank.frame.setVisible(true);
				
			}
		});
		PanelClient.setBackground(SystemColor.menu);
		PanelClient.setBounds(43, 53, 170, 25);
		panel_1.add(PanelClient);
		PanelClient.setLayout(null);
		
		
		
		Label ClientLabel = new Label("Client(es)");
		ClientLabel.setBounds(51, 7, 62, 12);
		PanelClient.add(ClientLabel);
		
		Panel PanelVersement = new Panel();
		PanelVersement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Window.frame.hide();
				frame.setVisible(false);
				Versement versement = new Versement();
				versement.frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Header color = new Header();
				color.setColor(PanelVersement);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Header color = new Header();
				color.resetColor(PanelVersement);
			}
		});
		PanelVersement.setLayout(null);
		PanelVersement.setFont(new Font("Sylfaen", Font.BOLD, 12));
		PanelVersement.setBackground(SystemColor.menu);
		PanelVersement.setBounds(311, 53, 170, 25);
		panel_1.add(PanelVersement);
		
		Label VersementLabel = new Label("Versement");
		VersementLabel.setBounds(51, 7, 71, 12);
		PanelVersement.add(VersementLabel);
		
		Panel PanelRetrait = new Panel();
		PanelRetrait.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Header color = new Header();
				color.setColor(PanelRetrait);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Header color = new Header();
				color.resetColor(PanelRetrait);
			}
		});
		PanelRetrait.setLayout(null);
		PanelRetrait.setFont(new Font("Sylfaen", Font.BOLD, 12));
		PanelRetrait.setBackground(SystemColor.menu);
		PanelRetrait.setBounds(577, 53, 170, 25);
		panel_1.add(PanelRetrait);
		
		Label RetraitLabel = new Label("Retrait");
		RetraitLabel.setAlignment(Label.RIGHT);
		RetraitLabel.setBounds(60, 7, 46, 12);
		PanelRetrait.add(RetraitLabel);
		
		/*----------------- FIN HEADER---------------*/
		
		/*-----------------FOOTER---------------*/
		
		Panel footerTab = new Panel();
		footerTab.setBackground(SystemColor.activeCaption);
		footerTab.setBounds(0, 534, 794, 37);
		panel.add(footerTab);
		/*-----------------FIN FOOTER---------------*/
		
		
	}

}
