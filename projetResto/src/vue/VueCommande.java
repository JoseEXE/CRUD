package vue;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


import metier.AdresseMetier;
import metier.ClientMetier;
import model.Adresse;
import model.Client;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class VueCommande extends JPanel {
	private JTextField textTel;
	private JTable tableClient;
	private JTable tableAdresse;
	ClientMetier clientM =new ClientMetier();
	AdresseMetier adresseM = new AdresseMetier();
	


	/**
	 * Create the panel.
	 */
	public VueCommande() {
		
		setBounds(0, 0, 1136, 565);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1136, 565);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Commande", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMain.setBounds(0, 10, 1136, 555);
		panel.add(panelMain);
		panelMain.setLayout(null);
		
		/*
		 * creation double intercalaires
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 31, 1116, 514);
		panelMain.add(tabbedPane);
		
		JPanel panelClient = new JPanel();
		tabbedPane.addTab("Clients", null, panelClient, null);
		panelClient.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(42, 10, 361, 439);
		panelClient.add(panel_1);
		panel_1.setLayout(null);
		
		textTel = new JTextField();
		textTel.setBounds(20, 41, 106, 19);
		panel_1.add(textTel);
		textTel.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 96, 306, 182);
		panel_1.add(scrollPane);
		
		JButton btnContinue = new JButton("Continuer");
		btnContinue.setBounds(20, 331, 106, 23);
		panel_1.add(btnContinue);
		btnContinue.setVisible(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Adresses", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(413, 10, 490, 439);
		panelClient.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 100, 458, 182);
		panel_2.add(scrollPane_1);
		
		JButton btnAddAdresse = new JButton("Ajouter");
		btnAddAdresse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.add(new VueClient(3));
			}
		});
		btnAddAdresse.setBounds(22, 367, 106, 23);
		panel_2.add(btnAddAdresse);
		btnAddAdresse.setVisible(false);
		
		JLabel lblNoAdresse = new JLabel("");
		lblNoAdresse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNoAdresse.setBounds(22, 292, 269, 23);
		lblNoAdresse.setForeground(Color.red);
		panel_2.add(lblNoAdresse);
		
		tableAdresse = new JTable();
		//scrollPane_1.setViewportView(tableAdresse);
		
		
		tableClient = new JTable();
		tableClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Client.idNewClient=String.valueOf(tableClient.getValueAt(tableClient.getSelectedRow(),0));
				System.out.println(Client.idNewClient);
				tableAdresse.setModel(adresseM.lister(Client.idNewClient));
				tableAdresse.getColumnModel().getColumn(0).setMaxWidth(20);
				tableAdresse.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
				tableAdresse.getColumnModel().getColumn(1).setMaxWidth(20);
				tableAdresse.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(20);
				tableAdresse.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Adresse.idNewAdresse=String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(),1));
						btnContinue.setVisible(true);
					}
				});
				scrollPane_1.setViewportView(tableAdresse);
				
				if(tableAdresse.getRowCount()==0) {
					System.out.println("IF");
					btnAddAdresse.setVisible(true);
					btnContinue.setVisible(false);
					lblNoAdresse.setText("L'adresse de "+tableClient.getValueAt(tableClient.getSelectedRow(), 2)+" n'est pas enregistrée");
				}else {
					System.out.println("ELSE");
					btnAddAdresse.setVisible(false);
					lblNoAdresse.setText("");
				}
				
				
			}
		});
		scrollPane.setViewportView(tableClient);
		
		JLabel lblNoClient = new JLabel("");
		lblNoClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNoClient.setBounds(20, 287, 197, 23);
		lblNoClient.setForeground(Color.red);
		panel_1.add(lblNoClient);
		
		JButton btnAddClient = new JButton("Ajouter");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddClient.setBounds(20, 373, 106, 23);
		panel_1.add(btnAddClient);
		btnAddClient.setVisible(false);
		
		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tel = textTel.getText();
				tableClient.setModel(clientM.clientExist(tel));
				tableClient.getColumnModel().getColumn(0).setMaxWidth(20);
				tableClient.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
				if(tableClient.getRowCount()==0) {
					btnAddClient.setVisible(true);
					lblNoClient.setText("Ce client n'existe pas");
				}else {
					btnAddClient.setVisible(false);
					lblNoClient.setText("");
				}
			
			}
		});
		btnNewButton.setBounds(156, 39, 106, 23);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Numéro de téléphone :");
		lblNewLabel.setBounds(20, 17, 162, 24);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Photo New Client");
		lblNewLabel_1.setBounds(913, 10, 188, 439);
		panelClient.add(lblNewLabel_1);
		
		
		
		JPanel panelProduits = new JPanel();
		tabbedPane.addTab("Produits", null, panelProduits, null);
		panelProduits.setLayout(null);
		
		tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(0, true);
        tabbedPane.setSelectedIndex(0);
	}
}
