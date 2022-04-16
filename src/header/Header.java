package header;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bank.Bank;
import retrait.Retrait;
import transfert.Transfert;
import versement.Versement;

public class Header {
	public void header(Panel panel, JFrame frame) {
		

		Panel panel_1 = new Panel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 0, 1018, 154);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		Label TITLE = new Label("GESTION BANCAIRE");
		TITLE.setBounds(578, 10, 254, 20);
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
		PanelClient.setBounds(508, 46, 170, 38);
		panel_1.add(PanelClient);
		PanelClient.setLayout(null);
		
		
		
		Label ClientLabel = new Label("Client(es)");
		ClientLabel.setBounds(51, 10, 62, 12);
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
		PanelVersement.setBounds(726, 46, 170, 38);
		panel_1.add(PanelVersement);
		
		Label VersementLabel = new Label("Versement");
		VersementLabel.setBounds(50, 10, 71, 12);
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
		PanelRetrait.setBounds(726, 102, 170, 38);
		panel_1.add(PanelRetrait);
		
		Label RetraitLabel = new Label("Retrait");
		RetraitLabel.setAlignment(Label.RIGHT);
		RetraitLabel.setBounds(60, 10, 46, 12);
		PanelRetrait.add(RetraitLabel);
		
		Panel PanelTransfert = new Panel();
		PanelTransfert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				Transfert transfert = new Transfert();
				transfert.frame.setVisible(true);
				transfert.frame.setLocationRelativeTo(null);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setColor(PanelTransfert);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				resetColor(PanelTransfert);
			}
		});
		PanelTransfert.setLayout(null);
		PanelTransfert.setFont(new Font("Sylfaen", Font.BOLD, 12));
		PanelTransfert.setBackground(SystemColor.menu);
		PanelTransfert.setBounds(508, 102, 170, 38);
		panel_1.add(PanelTransfert);
		
		Label TransfertLabel = new Label("Tranf\u00E9rer de l'argent");
		TransfertLabel.setAlignment(Label.RIGHT);
		TransfertLabel.setBounds(10, 10, 137, 18);
		PanelTransfert.add(TransfertLabel);
		
		JLabel lblLabel = new JLabel("label");
		lblLabel.setIcon(new ImageIcon("D:\\Bossy\\java\\BanqueInterface\\img\\BankTR.png"));
		lblLabel.setBounds(79, -11, 269, 154);
		panel_1.add(lblLabel);
		
	}
	
	
	public void footer(Panel panel) {
		Panel footerTab = new Panel();
		footerTab.setBackground(SystemColor.activeCaption);
		footerTab.setBounds(0, 644, 1018, 47);
		panel.add(footerTab);
		footerTab.setLayout(null);
		
		Label label = new Label("Gestion bancaire by Ianjy and Nancy");
		label.setBounds(10, 15, 207, 22);
		footerTab.add(label);
		
		Label label_1 = new Label("Copyright 2022");
		label_1.setBounds(916, 15, 102, 22);
		footerTab.add(label_1);
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
