package vue;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


import metier.AdresseMetier;
import metier.ClientMetier;
import metier.ProduitMetier;
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
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VueCommande extends JPanel {
	private JTextField textTel;
	private JTable tableClient;
	private JTable tableAdresse;
	ClientMetier clientM =new ClientMetier();
	AdresseMetier adresseM = new AdresseMetier();
	ProduitMetier prodM=new ProduitMetier();
	private JTextField textCode;
	private JTextField textQte;
	private JTextField textNom;
	private JTable TableProduits;
	private JTable tableCommande;
	


	/**
	 * Create the panel.
	 */
	public VueCommande(int optionC) {
		
		setBounds(0, 0, 1136, 565);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1136, 565);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Commande", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMain.setBounds(0, 10, 1126, 555);
		panel.add(panelMain);
		panelMain.setLayout(null);
		
		/*
		 * creation double intercalaires
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 31, 1106, 514);
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Adresses", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(413, 10, 437, 439);
		panelClient.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 100, 393, 182);
		panel_2.add(scrollPane_1);
		
		JButton btnAddAdresse = new JButton("Ajouter");
		btnAddAdresse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.add(new VueClient(3));
				Client.clientLast =new Client ();
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
		
		JButton btnContinue = new JButton("Continuer ->");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabled(getFocusTraversalKeysEnabled());
			}
		});
		btnContinue.setBounds(309, 406, 106, 23);
		panel_2.add(btnContinue);
		btnContinue.setVisible(false);
		
		tableAdresse = new JTable();
		//scrollPane_1.setViewportView(tableAdresse);
		
		
		tableClient = new JTable();
		tableClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Client.idNewClient=String.valueOf(tableClient.getValueAt(tableClient.getSelectedRow(),0));
				tableAdresse.setModel(adresseM.lister(Client.idNewClient));
				tableAdresse.getColumnModel().getColumn(0).setMaxWidth(20);
				tableAdresse.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
				tableAdresse.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Adresse.idNewAdresse=String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(),1));
						btnContinue.setVisible(true);
					}
				});
				scrollPane_1.setViewportView(tableAdresse);
				
				if(tableAdresse.getRowCount()==0) {
					btnAddAdresse.setVisible(true);
					btnContinue.setVisible(false);
					lblNoAdresse.setText("L'adresse de "+tableClient.getValueAt(tableClient.getSelectedRow(), 2)+" n'est pas enregistrée");
				}else {
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
				panelMain.setVisible(false);
				panel.add(new VueClient(2));
				panel.repaint();
				panel.revalidate();
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
		
		JLabel lblNewLabel_4 = new JLabel("Photo New client");
		lblNewLabel_4.setBounds(860, 10, 163, 439);
		panelClient.add(lblNewLabel_4);
		
		
		
		JPanel panelProduits = new JPanel();
		tabbedPane.addTab("Produits", null, panelProduits, null);
		panelProduits.setLayout(null);
		
		textCode = new JTextField();
		textCode.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textCode.setBounds(145, 10, 80, 44);
		panelProduits.add(textCode);
		textCode.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(50, 105, 658, 80);
		panelProduits.add(scrollPane_2);
		
		TableProduits = new JTable();
		TableProduits.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				TableProduits.setModel(prodM.listeProdCommande(textCode.getText().trim(),textNom.getText().trim()));
				
			}
		});
		scrollPane_2.setViewportView(TableProduits);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(50, 210, 658, 220);
		panelProduits.add(scrollPane_3);
		
		tableCommande = new JTable();
		scrollPane_3.setViewportView(tableCommande);
		
		JLabel lblNewLabel_1 = new JLabel("Quantite: ");
		lblNewLabel_1.setBounds(549, 440, 58, 26);
		panelProduits.add(lblNewLabel_1);
		
		textQte = new JTextField();
		textQte.setBounds(601, 444, 96, 19);
		panelProduits.add(textQte);
		textQte.setColumns(10);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setBounds(718, 28, 289, 226);
		panelProduits.add(editorPane);
		
		JLabel lblNewLabel_2 = new JLabel("Total:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(752, 311, 80, 26);
		panelProduits.add(lblNewLabel_2);
		
		JLabel lblTotal = new JLabel("27");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotal.setBounds(861, 293, 58, 59);
		panelProduits.add(lblTotal);
		
		JLabel lblNewLabel_3 = new JLabel("Code de produit:");
		lblNewLabel_3.setBounds(50, 18, 96, 26);
		panelProduits.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nom:");
		lblNewLabel_3_1.setBounds(50, 64, 85, 26);
		panelProduits.add(lblNewLabel_3_1);
		
		textNom = new JTextField();
		textNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNom.setColumns(10);
		textNom.setBounds(145, 64, 170, 26);
		panelProduits.add(textNom);
		
		JButton btnClear = new JButton("Vider les champs");
		btnClear.setBounds(235, 28, 111, 21);
		panelProduits.add(btnClear);
		
		JLabel lblTotal_1 = new JLabel("€");
		lblTotal_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotal_1.setBounds(929, 293, 58, 59);
		panelProduits.add(lblTotal_1);
		
		JButton btnFinaliser = new JButton("Finaliser");
		btnFinaliser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFinaliser.setBounds(812, 391, 115, 39);
		panelProduits.add(btnFinaliser);
		
		if(optionC == 2) {
			tabbedPane.setEnabledAt(1, true);
	        tabbedPane.setEnabledAt(0, false);
	        tabbedPane.setSelectedIndex(1);
	        
	        
	        
		}else {
			tabbedPane.setEnabledAt(1, false);
	        tabbedPane.setEnabledAt(0, true);
	        tabbedPane.setSelectedIndex(0);
		}

	}
}
