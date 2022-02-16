package header;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import bank.Bank;
import retrait.Retrait;
import versement.Versement;

public class Header {
	public void header(Panel panel, JFrame frame) {
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
				setColor(PanelClient);
			}
			
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				resetColor(PanelClient);
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
					bank.frame.setLocationRelativeTo(null);
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
				versement.frame.setLocationRelativeTo(null);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(PanelVersement);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				resetColor(PanelVersement);
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
				frame.setVisible(false);
				Retrait retrait = new Retrait();
				retrait.frame.setVisible(true);
				retrait.frame.setLocationRelativeTo(null);
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(PanelRetrait);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				resetColor(PanelRetrait);
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
	}
	
	
	public void footer(Panel panel) {
		Panel footerTab = new Panel();
		footerTab.setBackground(SystemColor.activeCaption);
		footerTab.setBounds(0, 534, 794, 37);
		panel.add(footerTab);
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
