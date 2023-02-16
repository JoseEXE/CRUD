package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.CommandeDao;
import controller.DetailDao;
import metier.AdresseMetier;
import metier.ClientMetier;
import metier.DetailMetier;
import metier.ProduitMetier;
import model.Adresse;
import model.Client;
import model.Commande;
import model.Detail_commande;
import model.Produit;
import model.User;


@SuppressWarnings("serial")
public class VueCommande extends JPanel {
	private JTextField textTel;
	private JTable tableClient;
	private JTable tableAdresse;
	ClientMetier clientM =new ClientMetier();
	AdresseMetier adresseM = new AdresseMetier();
	ProduitMetier prodM=new ProduitMetier();
	CommandeDao commandeD=new CommandeDao();
	static Commande newCommande= new Commande();
	DetailDao detailD=new DetailDao();
	DetailMetier detailM=new DetailMetier();
	int idNewCommande;
	int idDetail;
	private JTextField textCode;
	private JTextField textQte;
	private JTextField textNom;
	private JTable tableProduits;
	private JTable tableDetail;
	
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
		
		/*
		 * bouton pour ajouter une adresse, envoi vers creer adresse (appel de la page VueClient() avec option 3, creation adresse
		 */
		JButton btnAddAdresse = new JButton("Ajouter");
		btnAddAdresse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Client.clientLast);
				panel.removeAll();
				panel.add(new VueClient(3));
				panel.repaint();
				panel.revalidate();
			}
		});
		btnAddAdresse.setBounds(22, 367, 106, 23);
		panel_2.add(btnAddAdresse);
		btnAddAdresse.setVisible(false);
	
		JPanel panelProduits = new JPanel();
		tabbedPane.addTab("Commande", null, panelProduits, null);
		panelProduits.setLayout(null);
		
		JLabel lblNoAdresse = new JLabel("");
		lblNoAdresse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNoAdresse.setBounds(22, 292, 269, 23);
		lblNoAdresse.setForeground(Color.red);
		panel_2.add(lblNoAdresse);
		
		JPanel cadreNom = new JPanel();
		cadreNom.setBackground(new Color(255, 206, 206));
		cadreNom.setBounds(725, 18, 280, 220);
		panelProduits.add(cadreNom);
		cadreNom.setLayout(null);
		
		JPanel panelCadre = new JPanel();
		panelCadre.setBounds(10, 10, 260, 200);
		cadreNom.add(panelCadre);
		panelCadre.setLayout(null);
		
		/*
		 * etiquette du récapitulatif client/adresse de l'intercalaire 2
		 */
		JLabel lblNewLabel_4 = new JLabel("CLIENT :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 33, 71, 27);
		panelCadre.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel(": ADRESSE");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_1.setBounds(189, 144, 71, 27);
		panelCadre.add(lblNewLabel_4_1);
		
		JLabel lblNomClient = new JLabel("New label");
		lblNomClient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNomClient.setBounds(91, 10, 159, 24);
		panelCadre.add(lblNomClient);
		
		JLabel lblTelClient = new JLabel("New label");
		lblTelClient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblTelClient.setBounds(91, 41, 159, 24);
		panelCadre.add(lblTelClient);
		
		JLabel lblRueClient = new JLabel("New label");
		lblRueClient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblRueClient.setBounds(10, 114, 202, 24);
		panelCadre.add(lblRueClient);
		
		JLabel lblCodePostal = new JLabel("New label");
		lblCodePostal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblCodePostal.setBounds(10, 139, 57, 24);
		panelCadre.add(lblCodePostal);
		
		JLabel lblComplement = new JLabel("New label");
		lblComplement.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblComplement.setBounds(10, 166, 162, 24);
		panelCadre.add(lblComplement);
		
		JLabel lblVille = new JLabel("New label");
		lblVille.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblVille.setBounds(77, 139, 102, 24);
		panelCadre.add(lblVille);
		
		JButton btnContinue = new JButton("Continuer ->");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableAdresse.getSelectedColumnCount()==0 || tableClient.getSelectedColumnCount()==0) {
					JOptionPane.showMessageDialog(null, "Merci de selectionner une adresse avant de continuer", "Adresse", JOptionPane.WARNING_MESSAGE);
				}else {
				/*
				 * récupération des données client pour instancicier clientLast et instancier une commande a l'aide de l'utilisateur
				 */
				commandeD.create(newCommande);
				idNewCommande =commandeD.dernierIdCommande();
				newCommande = (Commande) commandeD.findById(idNewCommande);
				/*
				 * injection des données Client dans les etiquettes
				 * 
				 */
				lblNomClient.setText(Client.clientLast.getPrenom()+" "+Client.clientLast.getNom());
				lblTelClient.setText(Client.clientLast.getNumtel());
				lblRueClient.setText(Adresse.adresseLast.getRue());
				lblCodePostal.setText(Adresse.adresseLast.getCod_postal());
				lblVille.setText(Adresse.adresseLast.getVille());
				lblComplement.setText(Adresse.adresseLast.getComplement());
				
				tabbedPane.setEnabledAt(1, true);
		        tabbedPane.setEnabledAt(0, false);
		        tabbedPane.setSelectedIndex(1);
		        tableProduit();
				}
			}
		});
		btnContinue.setBounds(309, 406, 106, 23);
		panel_2.add(btnContinue);
		
		/*
		 * bouton pour modifier une adresse, envoi vers creer/modifier adresse (appel de la page VueClient() avec option 4, modification adresse
		 */
		JButton btnModifAdresse = new JButton("Modifier");
		btnModifAdresse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				/*
				 * appel de VueClient() option4
				 */
				panel.removeAll();
				panel.add(new VueClient(4));
				panel.repaint();
				panel.revalidate();	
			}
		});
		btnModifAdresse.setBounds(185, 406, 106, 23);
		panel_2.add(btnModifAdresse);
		btnModifAdresse.setVisible(false);
		btnContinue.setVisible(false);
	
		
		tableAdresse = new JTable();
		scrollPane_1.setViewportView(tableAdresse);
		
		
		tableClient = new JTable();
		tableClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * attribution des caracteristes du clientLast, client selectionné par l'utilisateur.
				 */
				Client.idNewClient=(int)(tableClient.getValueAt(tableClient.getSelectedRow(),0));
				String nom=String.valueOf(tableClient.getValueAt(tableClient.getSelectedRow(), 1));
				String prenom=String.valueOf(tableClient.getValueAt(tableClient.getSelectedRow(), 2));
				
				Client.clientLast =new Client (Client.idNewClient,nom,prenom,textTel.getText());
				/*
				 * creation de la table adresse en fonction du client selectionné
				 */
				tableAdresse.setModel(adresseM.lister(String.valueOf(Client.idNewClient)));
				tableAdresse.getColumnModel().getColumn(0).setMaxWidth(20);
				tableAdresse.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
				tableAdresse.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Adresse.idNewAdresse=String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(),1));
						/*
						 * récupération des données adresse du client pour instancicier adresselLast et l'envoyer vers VueClient() option 4
						 */
						int idAd=(int) (tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 0));
						String rue=String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 1));
						String codePost=String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 2));
						String ville=String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 3));
						String complet=String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 4));
						Adresse.adresseLast =new Adresse (idAd,rue,codePost,ville,complet);
						
						btnContinue.setVisible(true);
						btnModifAdresse.setVisible(true);
					}
				});
				
				if(tableAdresse.getRowCount()==0) {
					btnContinue.setVisible(false);
					lblNoAdresse.setText("L'adresse de "+tableClient.getValueAt(tableClient.getSelectedRow(), 2)+" n'est pas enregistrée");
				}else {
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
		
		/*
		 * bouton pour ajouter un client, envoi vers creer client (appel de la page VueClient() avec option 2, creation client et adresse
		 */
		
		JButton btnAddClient = new JButton("Ajouter");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.add(new VueClient(2));
				panel.repaint();
				panel.revalidate();
			}
		});
		btnAddClient.setBounds(20, 366, 106, 23);
		panel_1.add(btnAddClient);
		btnAddClient.setVisible(false);
		
		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tel = textTel.getText();
				if(clientM.checkRegexTel(tel)) {
					tableClient.setModel(clientM.clientExist(tel));
					tableClient.getColumnModel().getColumn(0).setMaxWidth(20);
					tableClient.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
					if(tableClient.getRowCount()==0) {
						btnAddClient.setVisible(true);
						lblNoClient.setText("Ce client n'existe pas");
					}else {
						btnAddAdresse.setVisible(true);
						btnAddClient.setVisible(false);
						lblNoClient.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null, "le numéro de téléphone doit être valide et composé de 10 chiffres maximum ", "Clients", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(156, 39, 106, 23);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Numéro de téléphone :");
		lblNewLabel.setBounds(20, 17, 162, 24);
		panel_1.add(lblNewLabel);
		
		JLabel lblimage = new JLabel("Photo New client");
		lblimage.setBounds(849, -58, 182, 535);

		//lblimage.setIcon(new ImageIcon(VueFrameMain.class.getResource("/ressources/sushiman.jfif")));
		panelClient.add(lblimage);
	
		
		textCode = new JTextField();
		textCode.setHorizontalAlignment(SwingConstants.CENTER);
		textCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tableProduit();
			}
		});
		textCode.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textCode.setBounds(145, 10, 80, 44);
		panelProduits.add(textCode);
		textCode.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(50, 105, 658, 80);
		panelProduits.add(scrollPane_2);
		
		JLabel lblTotal = new JLabel("0");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTotal.setBounds(841, 273, 92, 59);
		panelProduits.add(lblTotal);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(50, 210, 658, 220);
		panelProduits.add(scrollPane_3);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInfo.setBounds(50, 183, 257, 19);
		lblInfo.setForeground(new Color(40, 210, 45));
		panelProduits.add(lblInfo);
		
		
		tableDetail = new JTable();
		tableDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*injection de la quantité de produit dans le label en bas de tableau
				 * 
				 */
				idDetail =(int)(tableDetail.getValueAt(tableDetail.getSelectedRow(), 0));
				lblInfo.setText("");
				textQte.requestFocus();			}
		});
		
		tableProduits = new JTable();
		tableProduits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			/*
			 * ici selection de la ligne de produit pour creer un produit, puis un détail de commande 
			 */
				int idProd=(int) (tableProduits.getValueAt(tableProduits.getSelectedRow(), 0));
				Double prixProd=(double) (tableProduits.getValueAt(tableProduits.getSelectedRow(), 5));
				Produit newProduit= new Produit(idProd,User.userLogin,prixProd);
				Detail_commande newDetail = new Detail_commande(newCommande,newProduit);
				detailD.create(newDetail);
				lblTotal.setText(String.valueOf(commandeD.totalCommande(idNewCommande)));
				lblInfo.setText("");
				tableDetail(idNewCommande);
			}
		});
		
		scrollPane_2.setViewportView(tableProduits);
		scrollPane_3.setViewportView(tableDetail);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Changer la quantite ici : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(148, 440, 126, 26);
		panelProduits.add(lblNewLabel_1);
		
		textQte = new JTextField("");
		textQte.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(tableDetail.getSelectedColumnCount()==0) {
					JOptionPane.showMessageDialog(null,"Merci de selectionner une ligne de détail dans le tableau","Quantités",JOptionPane.WARNING_MESSAGE);
					textQte.setText("");
				}else {
						String newQte=textQte.getText();
						if(detailM.checkRegexQte(newQte)) {
							int newQte1 =Integer.parseInt(newQte)  ;
							if(detailD.updateQuantité(newQte1, idDetail)) {
								lblInfo.setText("La quantité a bien été modifiée");
							}else {
								JOptionPane.showMessageDialog(null,"La quantité n'a pas pu être modifiée.","Quantité", JOptionPane.ERROR_MESSAGE);
							}
								tableDetail(idNewCommande);
								lblTotal.setText(String.valueOf(commandeD.totalCommande(idNewCommande)));
								textQte.setText("");
						}else {
							JOptionPane.showMessageDialog(null,"La quantité doit être un chiffre en 1 et 9.","Quantité", JOptionPane.ERROR_MESSAGE);
							textQte.setText("");
						}
				}
			}
		});
		textQte.setBounds(284, 444, 36, 19);
		panelProduits.add(textQte);
		textQte.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Total:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(752, 293, 80, 26);
		panelProduits.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Code de produit:");
		lblNewLabel_3.setBounds(50, 18, 96, 26);
		panelProduits.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nom:");
		lblNewLabel_3_1.setBounds(50, 64, 85, 26);
		panelProduits.add(lblNewLabel_3_1);
		
		textNom = new JTextField();
		textNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tableProduit();
			}
		});
		textNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNom.setColumns(10);
		textNom.setBounds(145, 64, 170, 26);
		panelProduits.add(textNom);
		
		JButton btnClear = new JButton("Vider les champs");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCode.setText("");
				textNom.setText("");
				tableProduit();
			}
		});
		btnClear.setBounds(235, 28, 138, 21);
		panelProduits.add(btnClear);
		
		JLabel lblTotal_1 = new JLabel("€");
		lblTotal_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotal_1.setBounds(963, 275, 58, 59);
		panelProduits.add(lblTotal_1);
		
		JPanel PanelGo = new JPanel();
		PanelGo.setBackground(new Color(0, 0, 0));
		PanelGo.setBounds(749, 330, 241, 147);
		panelProduits.add(PanelGo);
		PanelGo.setLayout(null);
		PanelGo.setVisible(false);
		
		JLabel lbllivreur = new JLabel("New label");
		lbllivreur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switch (Commande.ready) {
				case 0:
					JOptionPane.showMessageDialog(null, "Merci de selectionner au minimum un mode de paiement","Paiement", JOptionPane.WARNING_MESSAGE);
					break;
				case 1:
					newCommande.setEtat("Validée");
					newCommande.setTotal(Double.parseDouble(lblTotal.getText()));
					if(commandeD.update(newCommande)) {
						JOptionPane.showMessageDialog(null, "La commande est validée","Paiement", JOptionPane.INFORMATION_MESSAGE);
						panel.removeAll();
						panel.add(new VueCommande(1));
						panel.repaint();
						panel.revalidate();
					}
					break;
				}
			}
		});
		lbllivreur.setBounds(10, 10, 221, 127);
		lbllivreur.setIcon(new ImageIcon(VueFrameMain.class.getResource("/ressources/go.jfif")));
		PanelGo.add(lbllivreur);
		
		JButton btnDelete = new JButton("Effacer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableDetail.getSelectedColumnCount()==0) {
					JOptionPane.showMessageDialog(null,"Merci de selectionner une ligne de détail dans le tableau","Effacement",JOptionPane.WARNING_MESSAGE);
				}else {
				int reponse=JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir effacer la ligne sélectionnée?","Effacement", JOptionPane.YES_NO_CANCEL_OPTION);
					if(reponse==0) {
						int idDetail=(int)tableDetail.getValueAt(tableDetail.getSelectedRow(), 0);
						if(detailD.Delete(idDetail)) {
							lblInfo.setText("La quantité a bien été modifiée");
							tableDetail(idNewCommande);
							lblTotal.setText(String.valueOf(commandeD.totalCommande(idNewCommande)));
						}else {
							JOptionPane.showMessageDialog(null,"La ligne n'a pas pu être effacée","Effacement",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		JButton btnFinaliser = new JButton("Finaliser");
		btnFinaliser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCadre.setEnabled(false);
				panelCadre.removeAll();
				panelCadre.add(new VuePaiement());
				panelCadre.repaint();
				panelCadre.revalidate();
				PanelGo.setVisible(true);
				btnFinaliser.setVisible(false);
				btnFinaliser.setEnabled(false);
				btnDelete.setEnabled(false);
				textQte.setEditable(false);
			}
		});
		btnFinaliser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFinaliser.setBounds(777, 391, 183, 39);
		panelProduits.add(btnFinaliser);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(602, 442, 106, 23);
		panelProduits.add(btnDelete);
		
		JLabel lblNewLabel_1_1 = new JLabel("Effacer la ligne de commande ici : ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1_1.setBounds(435, 440, 157, 26);
		panelProduits.add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("Annuler la commande");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse=JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir annuler la commande en cours?","Annulation", JOptionPane.YES_NO_CANCEL_OPTION);
				switch (reponse) {
				case 0:
					detailD.DeleteAll(idNewCommande);
					commandeD.Delete(idNewCommande);
					panel.removeAll();
					panel.add(new VueCommande(1));
					panel.repaint();
					panel.revalidate();
					break;
				case 1:
					
					break;
				}
				
			}
		});
		btnNewButton_1.setBounds(479, 51, 138, 44);
		panelProduits.add(btnNewButton_1);
		
		
	
		if(optionC == 2) {
			tabbedPane.setEnabledAt(1, true);
	        tabbedPane.setEnabledAt(0, false);
	        tabbedPane.setSelectedIndex(1);
	        
	    	commandeD.create(newCommande);
			idNewCommande =commandeD.dernierIdCommande();
			newCommande = (Commande) commandeD.findById(idNewCommande);
	        tableProduit();
	        
		}else {
			tabbedPane.setEnabledAt(1, false);
	        tabbedPane.setEnabledAt(0, true);
	        tabbedPane.setSelectedIndex(0);
		}

	}
	public void tableProduit() {
		
		tableProduits.setModel(prodM.listeProdCommande(textCode.getText().trim(),textNom.getText().trim()));
		tableProduits.getColumnModel().getColumn(0).setMaxWidth(20);
		tableProduits.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
	}
	public void tableDetail(int id) {
		
		tableDetail.setModel(detailM.listeDetail(id));
		tableDetail.getColumnModel().getColumn(0).setMaxWidth(20);
		tableDetail.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(20);
		System.out.println(detailM.listeDetail(id));
	}
}
