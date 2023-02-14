package vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import controller.AdresseDao;
import controller.ClientDao;
import metier.AdresseMetier;
import metier.ClientMetier;
import model.Adresse;
import model.Client;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextArea;




public class VueClient extends JPanel {
	private JTextField textField;
	private JTable table;
	/*
	 * instanciation Class roleMetier
	 */
	ClientMetier clientM =new ClientMetier();
	AdresseMetier adresseM = new AdresseMetier();
	/*
	 * instanciation Class roleDao
	 */
	ClientDao clientD=new ClientDao();
	AdresseDao adresseDao = new AdresseDao();
	/*
	 * creation variable string role pour les differents messages d'affichage fenetre role
	 */
	String nomModel = "Client";
	/*
	 * creation variable pour les 2 actions create et update
	 */
	String action = "";
	String actionAdresse = "";
	/*
	 * creation variable pour recupere ancien nom lors de la modification de registre
	 */
	String ancienNom="";
	
	private JTextField textNom;
	private JTextField textId;
	private JTextField textPrenom;
	private JTextField textTel;
	private JTextField textCodPostal;
	private JTextField textVille;
	private JTable tableAdresse;
	private JTextField textIdAdresse;
	AdresseDao adresseD = new AdresseDao();
	/*
	 * optionGestion = 1 -> Local de gestion Client
	 * */
	

	
	/**
	 * Create the panel.
	 */

	public VueClient(int optionGestion) {
		//
		//optionGestion = 1 -> Menu option Gestion de clients.
		//optionGestion = 2 -> Menu option Commandes nouvelle Cliente.
		//optionGestion = 3 -> Menu option Commandes nouvelle Adresse.
		
		setBounds(0, 0, 1136, 565);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1136, 565);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Clients", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMain.setBounds(0, 10, 1136, 555);
		panel.add(panelMain);
		panelMain.setLayout(null);
		/*
		 * creation double intercalaires
		 */
		JTextArea textRue = new JTextArea();
		textCodPostal = new JTextField();
		JPanel panelAdresseClient = new JPanel();
		JPanel panelListeAdresse = new JPanel();
		textVille = new JTextField();
		JTextArea textComplement = new JTextArea();
		JButton btnSauvegarderAdresse = new JButton("Sauvegarder Adresse");
		JButton btnAdresseMod = new JButton("Modifie Adresse");
		JButton btnAdresseEffacer = new JButton("Effacer Adresse");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JButton btnClientNoVisible = new JButton("Modifie");
		JPanel panelClient = new JPanel();
		JButton btnAnnuler = new JButton("Annuler");
		JButton btnSauvegarder = new JButton("Sauvegarder");
		btnClientNoVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //panelClient.setEnabled(true);
				 btnClientNoVisible.setVisible(false);
				 textNom.setEditable(true);
				 textNom.requestFocus();
				 textPrenom.setEditable(true);
				 textTel.setEditable(true);
				 btnAnnuler.setEnabled(true);
				 btnSauvegarder.setEnabled(true);
				 ////
				 //panelAdresseClient.setEnabled(false);
				 textIdAdresse.setEditable(false);
				 textRue.setEditable(false);
				 textCodPostal.setEditable(false);
				 textVille.setEditable(false);
				 textComplement.setEditable(false);
				 btnSauvegarderAdresse.setEnabled(false);
				 
				 //panelListeAdresse.setEnabled(false);
				 btnAdresseMod.setEnabled(false);
				 btnAdresseEffacer.setEnabled(false);
				 
				 action ="modifier";
			     btnSauvegarder.setText("Modifier");
			}
		});
		

		tabbedPane.setBounds(10, 31, 1116, 514);
		panelMain.add(tabbedPane);
		
		JPanel panelListe = new JPanel();
		tabbedPane.addTab("Liste", null, panelListe, null);
		panelListe.setLayout(null);
		

		
		JPanel panelGestion = new JPanel();
		tabbedPane.addTab("Gestion Client", null, panelGestion, null);
		panelGestion.setLayout(null);
		
		JLabel lblRecherche = new JLabel("Rechercher par nom :");
		lblRecherche.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecherche.setBounds(20, 30, 124, 27);
		panelListe.add(lblRecherche);
		textField = new JTextField();
		textField.setBounds(194, 35, 237, 19);
		panelListe.add(textField);
		textField.setColumns(10);
		
		/*
		 * Creation table liste Role
		 */
		
		table = new JTable();
		table.setModel(clientM.lister(""));
		JLabel lblAffichage = new JLabel("Affichage de "+clientM.totalM +" registres sur un total de "+ clientD.total()+" registres");
		JButton btnChercher = new JButton("Chercher");
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(clientM.lister(textField.getText()));
				TableRowSorter order = new TableRowSorter(table.getModel());
				table.setRowSorter(order);
				lblAffichage.setText("Affichage de "+clientM.totalM +" registres sur un total de "+ clientD.total()+" registres");
			}
		});
		btnChercher.setBounds(518, 34, 106, 23);
		panelListe.add(btnChercher);
		/*
		 * conditions pour les create et update, en fonction de la variable Action initialisée plus haut
		 */

		btnSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(action.equalsIgnoreCase("modifier")){
					 
					 if(textNom.getText().equalsIgnoreCase("") 	|| textNom.getText().length() >40  || textPrenom.getText().length()>100) {
						 JOptionPane.showMessageDialog(null,"Merci de remplir les champs obligatoire(*) et de respecter le nombre de caractères", "Modification", JOptionPane.ERROR_MESSAGE);
					 }else {
						 if(ancienNom.equalsIgnoreCase(textNom.getText())) {
							 Client newClient =new Client(Integer.parseInt(textId.getText()),textNom.getText(),textPrenom.getText(),textTel.getText());
							 if(clientD.update(newClient)) {
								 JOptionPane.showMessageDialog(null,"Le client "+newClient.getNom()+" a bien été enregistré","Modification",JOptionPane.INFORMATION_MESSAGE);
								 
								 //tabbedPane.setEnabledAt(1, false);
					             //tabbedPane.setEnabledAt(0, true);
					             //tabbedPane.setSelectedIndex(0);
					             //table.setModel(clientM.lister(textField.getText()));
								 btnClientNoVisible.setVisible(true);
								 textId.setText(String.valueOf(clientD.dernierIdClient()));
								 System.out.println("Id cree pour le client!!: "+textId.getText());
								 textNom.setEditable(false);
								 textPrenom.setEditable(false);
								 textTel.setEditable(false);
								 btnAnnuler.setEnabled(false);
								 btnSauvegarder.setEnabled(false);
								 
								 
							 }else {
								 JOptionPane.showMessageDialog(null,"Impossible de modifier le "+nomModel, "Modification", JOptionPane.ERROR_MESSAGE);
							 }
						 }else {
						 
							 if(!clientD.isExist(textTel.getText())) {
								 Client newClient =new Client(Integer.parseInt(textId.getText()),textNom.getText(),textPrenom.getText(),textTel.getText());
								
								 if(clientD.update(newClient)) {
									 JOptionPane.showMessageDialog(null,"Le client "+newClient.getNom()+" a bien été enregistré","Modification",JOptionPane.INFORMATION_MESSAGE);
									
									 tabbedPane.setEnabledAt(1, false);
					                 tabbedPane.setEnabledAt(0, true);
					                 tabbedPane.setSelectedIndex(0);
					                 table.setModel(clientM.lister(textField.getText()));
								 }else {
									 JOptionPane.showMessageDialog(null,"Impossible de modifier le "+nomModel, "Modification", JOptionPane.ERROR_MESSAGE);
								 }//fin du update
							 }else {	 
								 JOptionPane.showMessageDialog(null,"Ce "+nomModel+" existe déjà", "Modification", JOptionPane.ERROR_MESSAGE);
							 }//fin if isExiste
						 }//fin if ancienNom egual new nom
					 }
				 }else if(action.equalsIgnoreCase("Sauvegarder")){
					 if(textNom.getText().equalsIgnoreCase("")	|| textNom.getText().length() >40  || textPrenom.getText().length()>100) {
						 JOptionPane.showMessageDialog(null,"Merci de remplir les champs obligatoire(*) et de respecter le nombre de caractères", "Création", JOptionPane.ERROR_MESSAGE);
					 }else {
//Le isExiste n'est pas necessaire						
						 //if(!clientD.isExist(textTel.getText())) {
							 Client newClient =new Client(textNom.getText(),textPrenom.getText(),textTel.getText());
							 if(clientD.create(newClient)) {
								 JOptionPane.showMessageDialog(null,"Le client "+newClient.getNom()+" a bien été enregistré","Création",JOptionPane.INFORMATION_MESSAGE);
								
								 //tabbedPane.setEnabledAt(1, false);
					             //tabbedPane.setEnabledAt(0, true);
					             //tabbedPane.setSelectedIndex(0);
								 //panelClient.setEnabled(false);
								 btnClientNoVisible.setVisible(true);
								 textId.setText(String.valueOf(clientD.dernierIdClient()));
								 System.out.println("Id cree pour le client!!: "+textId.getText());
								 textNom.setEditable(false);
								 textPrenom.setEditable(false);
								 textTel.setEditable(false);
								 btnAnnuler.setEnabled(false);
								 btnSauvegarder.setEnabled(false);
								 ////
								 //panelAdresseClient.setEnabled(false);
								 textIdAdresse.requestFocus();
								 textIdAdresse.setEditable(true);
								 textRue.setEditable(true);
								 textCodPostal.setEditable(true);
								 textVille.setEditable(true);
								 textComplement.setEditable(true);
								 btnSauvegarderAdresse.setEnabled(true);
								 
					             table.setModel(clientM.lister(textField.getText()));
							 }else {
								 JOptionPane.showMessageDialog(null,"Impossible de créer le "+nomModel, "Création", JOptionPane.ERROR_MESSAGE);
							 }
						 //}else {
						 //	 JOptionPane.showMessageDialog(null,"Ce "+nomModel+" existe déjà", "Création", JOptionPane.ERROR_MESSAGE);
						 //}
//================================
					 } //fin if name ==""
				 }//fin if modifier or save
			}//fin ActionPerformed
		});//fin ActionListener
		
		/*
		 * passage vers la page creation de role 
		 */
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setEnabledAt(1, true);
                tabbedPane.setEnabledAt(0, false);
                tabbedPane.setSelectedIndex(1);
                action="Sauvegarder";
                btnSauvegarder.setText("Sauvegarder");
                //panel1
                //panelAdresseClient.setEnabled(false);
                textRue.setEditable(false);
                textCodPostal.setEditable(false);
                textVille.setEditable(false);
                textComplement.setEditable(false);
                btnSauvegarderAdresse.setEnabled(false);
                
                //panelListeAdresse.setEnabled(false);
                btnAdresseMod.setEnabled(false);
                btnAdresseEffacer.setEnabled(false);
                
			}
		});
		btnNouveau.setBounds(653, 34, 106, 23);
		panelListe.add(btnNouveau);
		/*
		 * passage vers la page modification de role 
		 */
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedColumnCount()==0) {
					JOptionPane.showMessageDialog(null,"Merci de selectionner un "+nomModel+" dans le tableau","Statut",JOptionPane.WARNING_MESSAGE);
				}else {
					
					tableAdresse.setModel(adresseM.lister(textId.getText()));
					textId.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
					textNom.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
					ancienNom= String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
					textPrenom.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
					textTel.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
				
					tabbedPane.setEnabledAt(1, true);
	                tabbedPane.setEnabledAt(0, false);
	                tabbedPane.setSelectedIndex(1);
	                action="Modifier";
	                btnSauvegarder.setText("Modifier");
				}
			}
		});
		btnModifier.setBounds(786, 34, 106, 23);
		panelListe.add(btnModifier);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 95, 1069, 282);
		panelListe.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Statuts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 438, 346, 49);
		panelListe.add(panel_1);
		panel_1.setLayout(null);
		/*
		 * condition (choix d'une ligne dans tableau)et activation (statut) d'un role
		 */
		JButton btnActiver = new JButton("Activer");
		btnActiver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedColumnCount()==0) {
					JOptionPane.showMessageDialog(null,"Merci de selectionner un "+nomModel+" dans le tableau","Statut",JOptionPane.WARNING_MESSAGE);
				}else {
					/*
					 * recupération des données selectionnées avant instanciation
					 */
					String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
					String nom = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
					String prenom = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
					String tel = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));
					String stat = String.valueOf(table.getValueAt(table.getSelectedRow(), 4));
						if(stat.equalsIgnoreCase("active")) {
							JOptionPane.showMessageDialog(null,"Le "+nomModel+" est déjà actif","Statut",JOptionPane.WARNING_MESSAGE);
						}else {
							Client newClient =new Client(Integer.parseInt(id), nom, prenom,tel, stat);
								if(clientD.activer(newClient)) {
									JOptionPane.showMessageDialog(null,"Le "+nomModel+" est maintenant actif","Statut",JOptionPane.INFORMATION_MESSAGE);
									table.setModel(clientM.lister(textField.getText()));	
								}
						}
				}
			}
		});
		btnActiver.setBounds(40, 18, 106, 23);
		panel_1.add(btnActiver);
		/*
		 * condition (choix d'une ligne dans tableau)et desactivation (statut) d'un role
		 */
		JButton btnDesactiver = new JButton("Desactiver");
		btnDesactiver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedColumnCount()==0) {
					JOptionPane.showMessageDialog(null,"Merci de selectionner un "+nomModel+" dans le tableau","Statut",JOptionPane.WARNING_MESSAGE);
				}else {
					/*
					 * recupération des données selectionnées avant instanciation
					 */
					String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
					String nom = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
					String prenom = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
					String tel = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));
					String stat = String.valueOf(table.getValueAt(table.getSelectedRow(), 4));
						if(stat.equalsIgnoreCase("inactif")) {
							JOptionPane.showMessageDialog(null,"Le "+nomModel+" est déjà inactif","Statut",JOptionPane.WARNING_MESSAGE);
							
						}else {
								Client newClient =new Client(Integer.parseInt(id), nom, prenom,tel, stat);
								if(clientD.desactiver(newClient)) {
									JOptionPane.showMessageDialog(null,"Le "+nomModel+" est maintenant inactif","Statut",JOptionPane.INFORMATION_MESSAGE);
									table.setModel(clientM.lister(textField.getText()));	
								}
						}
				}
			}
		});
		btnDesactiver.setBounds(194, 18, 106, 23);
		panel_1.add(btnDesactiver);
		
		lblAffichage.setBounds(476, 387, 367, 19);
		panelListe.add(lblAffichage);
		

		
		if(optionGestion == 1) {
			tabbedPane.setEnabledAt(1, false);
	        tabbedPane.setEnabledAt(0, true);
	        tabbedPane.setSelectedIndex(0);
		}else {
			tabbedPane.setEnabledAt(1, true);
	        tabbedPane.setEnabledAt(0, false);
	        tabbedPane.setSelectedIndex(1);
		}

		
		JLabel lblNom = new JLabel("Nom : (*)");
		lblNom.setBounds(10, 76, 70, 21);
		panelGestion.add(lblNom);
		
		JLabel lblDescription = new JLabel("Prénom :");
		lblDescription.setBounds(10, 137, 70, 21);
		panelGestion.add(lblDescription);
		
		textNom = new JTextField();
		textNom.setBounds(76, 76, 188, 20);
		panelGestion.add(textNom);
		textNom.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("(*) Champs obligatoires");
		lblNewLabel_2.setBounds(23, 262, 142, 21);
		panelGestion.add(lblNewLabel_2);
		/*
		 * Annulation et retour a la page accueil role
		 */
		
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(optionGestion == 1) {
//					tabbedPane.setEnabledAt(1, false);
//	                tabbedPane.setEnabledAt(0, true);
//	                tabbedPane.setSelectedIndex(0);
//	                table.setModel(clientM.lister(textField.getText()));
//				}else {
//					
//				}
//	
	

			
			}
		});
		btnAnnuler.setBounds(34, 361, 106, 23);	
		panelGestion.add(btnAnnuler);
		
		btnSauvegarder.setBounds(150, 361, 108, 23);
		panelGestion.add(btnSauvegarder);
		
		JLabel lblNewLabel = new JLabel("40 caractères maximum");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(76, 93, 119, 18);
		panelGestion.add(lblNewLabel);
		
		textPrenom = new JTextField();
		textPrenom.setColumns(10);
		textPrenom.setBounds(76, 137, 188, 20);
		panelGestion.add(textPrenom);
		
		JLabel lblTlphone = new JLabel("Téléphone: (*)");
		lblTlphone.setBounds(10, 198, 95, 21);
		panelGestion.add(lblTlphone);
		
		textTel = new JTextField();
		textTel.setColumns(10);
		textTel.setBounds(115, 198, 149, 20);
		panelGestion.add(textTel);
		
		JLabel lblNewLabel_1 = new JLabel("40 caractères maximum");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(76, 155, 119, 18);
		panelGestion.add(lblNewLabel_1);
		
		JPanel panelAdresse = new JPanel();
		panelAdresse.setBorder(new TitledBorder(null, "Adresse client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAdresse.setBounds(274, 21, 837, 454);
		panelGestion.add(panelAdresse);
		panelAdresse.setLayout(null);
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Nouvell Adresse
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		
		
		panelAdresseClient.setBorder(new TitledBorder(null, "Nouvelle Adresse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAdresseClient.setBounds(10, 22, 289, 421);
		panelAdresse.add(panelAdresseClient);
		panelAdresseClient.setLayout(null);
		
		
		btnSauvegarderAdresse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textRue.getText().equals("") || textCodPostal.getText().equals("") || textVille.getText().equals("") || textRue.getText().length() > 60 || textCodPostal.getText().length() > 5 || textVille.getText().length() > 40 || textComplement.getText().length()>60) {
					JOptionPane.showMessageDialog(null,"Merci de remplir les champs obligatoire(*) et de respecter le nombre de caractères", "Adresse Client", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Adresse adresse = new Adresse(Integer.parseInt(textId.getText()), textRue.getText(), textCodPostal.getText(), textVille.getText(), textComplement.getText());
/*
 * 
 * 
 * ==============================================================================
 * 
 * */				
				if(actionAdresse.equalsIgnoreCase("Modifier")) {
					
					
				}else {
					if(adresseD.create(adresse)) {
						 JOptionPane.showMessageDialog(null,"L'adresse de client "+textNom.getText()+" a bien été enregistré","Adresse",JOptionPane.INFORMATION_MESSAGE);
							textRue.setText("");
							textCodPostal.setText("");
							textVille.setText("");
							textComplement.setText("");
							textRue.setEditable(false);
							textCodPostal.setEditable(false);
							textVille.setEditable(false);
							textComplement.setEditable(false);
							btnSauvegarderAdresse.setEnabled(false);
						    tableAdresse.setModel(adresseM.lister(textId.getText()));
						 
					}else {
						 JOptionPane.showMessageDialog(null,"Impossible de créer l'adresse de client "+textNom.getText(), "Adresse", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
					
				//textIdAdresse.setEditable(false);
				textRue.setEditable(false);
				textCodPostal.setEditable(false);
				textVille.setEditable(false);
				textComplement.setEditable(false);
				btnSauvegarderAdresse.setEnabled(false);
				//panelAdresseClient.setEnabled(false);
				textIdAdresse.setText("");
				textRue.setText("");
				textCodPostal.setText("");
				textVille.setText("");
				textComplement.setText("");
				
				//panelListeAdresse.setEnabled(true);
				btnAdresseMod.setEnabled(true);
				btnAdresseEffacer.setEnabled(true);
				tableAdresse.setModel(adresseM.lister(textId.getText()));
				
				
			}
		});
		btnSauvegarderAdresse.setBounds(52, 375, 186, 23);
		panelAdresseClient.add(btnSauvegarderAdresse);
		
		
		textComplement.setBounds(10, 272, 256, 73);
		panelAdresseClient.add(textComplement);
		
		JLabel lblComplement = new JLabel("Complement:");
		lblComplement.setBounds(10, 247, 123, 14);
		panelAdresseClient.add(lblComplement);
		
		JLabel lblVille = new JLabel("Ville: (*)");
		lblVille.setBounds(10, 219, 85, 14);
		panelAdresseClient.add(lblVille);
		
		
		textVille.setBounds(120, 216, 146, 20);
		panelAdresseClient.add(textVille);
		textVille.setColumns(10);
		
		
		textCodPostal.setBounds(120, 167, 146, 20);
		panelAdresseClient.add(textCodPostal);
		textCodPostal.setColumns(10);
		
		JLabel lblCodPostal = new JLabel("Cod Postal: (*)");
		lblCodPostal.setBounds(10, 170, 85, 14);
		panelAdresseClient.add(lblCodPostal);
		
		
		textRue.setBounds(10, 65, 256, 73);
		panelAdresseClient.add(textRue);
		
		JLabel lblRue = new JLabel("Rue: (*)");
		lblRue.setBounds(10, 40, 64, 14);
		panelAdresseClient.add(lblRue);
		
		textIdAdresse = new JTextField();
		textIdAdresse.setBounds(160, 22, 106, 20);
		panelAdresseClient.add(textIdAdresse);
		textIdAdresse.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("60 chiffres maximum");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1_1_1.setBounds(147, 138, 119, 18);
		panelAdresseClient.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("5 chiffres maximum");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1_1_1_1.setBounds(147, 187, 119, 18);
		panelAdresseClient.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("40 chiffres maximum");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1_1_1_1_1.setBounds(147, 232, 119, 18);
		panelAdresseClient.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("60 chiffres maximum");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1_1_1_2.setBounds(147, 346, 119, 18);
		panelAdresseClient.add(lblNewLabel_1_1_1_2);
		textIdAdresse.setVisible(false);
		
	
		panelListeAdresse.setBorder(new TitledBorder(null, "Liste Adresse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelListeAdresse.setBounds(303, 22, 524, 421);
		panelAdresse.add(panelListeAdresse);
		panelListeAdresse.setLayout(null);
		
		
		btnAdresseMod.setBounds(212, 275, 146, 23);
		panelListeAdresse.add(btnAdresseMod);
		

		btnAdresseEffacer.setBounds(368, 275, 146, 23);
		panelListeAdresse.add(btnAdresseEffacer);
		
		JScrollPane scrollPaneAdresse = new JScrollPane();
		scrollPaneAdresse.setBounds(10, 39, 504, 210);
		panelListeAdresse.add(scrollPaneAdresse);
		
		tableAdresse = new JTable();
		scrollPaneAdresse.setViewportView(tableAdresse);
		
		JButton btnContinuer = new JButton("Continuer->");
		btnContinuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				
			}
		});
		btnContinuer.setBounds(401, 387, 113, 23);
		panelListeAdresse.add(btnContinuer);
		
		
		panelClient.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Client", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelClient.setBounds(0, 21, 276, 454);
		panelGestion.add(panelClient);
		panelClient.setLayout(null);
		
		textId = new JTextField();
		textId.setBounds(10, 423, 106, 20);
		panelClient.add(textId);
		textId.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("10 chiffres maximum");
		lblNewLabel_1_1.setBounds(114, 195, 119, 18);
		panelClient.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		
		btnClientNoVisible.setBounds(92, 371, 106, 23);
		panelClient.add(btnClientNoVisible);
		btnClientNoVisible.setVisible(false);
		textId.setVisible(false);
		btnAdresseMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableAdresse.getSelectedColumnCount()==0) {
					JOptionPane.showMessageDialog(null,"Merci de selectionner une adresse dans le tableau","Adresse",JOptionPane.WARNING_MESSAGE);
				}else {
				    actionAdresse = "Modifier";
				    textIdAdresse.setEditable(true);
					textRue.setEditable(true);
					textCodPostal.setEditable(true);
					textVille.setEditable(true);
					textComplement.setEditable(true);
					btnSauvegarderAdresse.setEnabled(true);
					panelAdresseClient.setEnabled(true);

					
					textIdAdresse.setText(String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 0)));
					textRue.setText(String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 1)));
					textCodPostal.setText(String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 2)));;
					textVille.setText(String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 3)));
					textComplement.setText(String.valueOf(tableAdresse.getValueAt(tableAdresse.getSelectedRow(), 4)));
					
				}
				
	
		    
				//btnSauvegarderAdresse.setText("Modifier");
				
			}
		});
		
		
		/*
		 * Tri asc desc pour le tabeau produit
		 */

		TableRowSorter order = new TableRowSorter(table.getModel());
		table.setRowSorter(order);
	}
}