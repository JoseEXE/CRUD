package vue;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.RoleDao;
import controller.UserDao;
import metier.RoleMetier;
import metier.UserMetier;
import model.Role;
import model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class VueUser extends JPanel {
	private JTextField textField;
	private JTable table;
	/*
	 * instanciation Class userMetier
	 */
	UserMetier userM =new UserMetier();
	/*
	 * instanciation Class userDao
	 */
	UserDao userD = new UserDao();
	RoleDao roleD = new RoleDao();
	Role roledeTest = new Role("","");
	/*
	 * creation variable string role pour les differents messages d'affichage fenetre role
	 */
	String nomModel = "utilisateur";
	/*
	 * creation variable pour les 2 actions create et update
	 */
	String action = "";
	/*
	 * creation variable pour recupere ancien nom lors de la modification de registre
	 */
	String ancienNom="";
	
	private JTextField textNom;
	private JTextField textId;
	private JTextField textPrenom;
	private JTextField textEmail;
	private JPasswordField textPassword1;
	private JPasswordField textPassword2;
	private JTextField textUrl;
	/**
	 * Create the panel.
	 */
	public VueUser() {
		setBounds(0, 0, 1136, 565);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1136, 565);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(null, "R\u00F4les", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.setBounds(0, 10, 1136, 555);
		panel.add(panelMain);
		panelMain.setLayout(null);
		/*
		 * creation double intercalaires
		 */
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 31, 1116, 514);
		panelMain.add(tabbedPane);
		
		JPanel panelListe = new JPanel();
		tabbedPane.addTab("Liste", null, panelListe, null);
		panelListe.setLayout(null);
		
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
		table.setModel(userM.lister(""));
		JLabel lblAffichage = new JLabel("Affichage de "+userM.totalM +" registres sur un total de "+ userD.total()+" registres");
		JButton btnChercher = new JButton("Chercher");
		JTextArea textDescription = new JTextArea();
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(userM.lister(textField.getText()));
				lblAffichage.setText("Affichage de "+ userM.totalM +" registres sur un total de "+ userD.total()+" registres");
			}
		});
		btnChercher.setBounds(518, 34, 106, 23);
		panelListe.add(btnChercher);
		/*
		 * conditions pour les create et update, en fonction de la variable Action initialisée plus haut
		 */
		JButton btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(action.equalsIgnoreCase("modifier")){
					 
					 if(textNom.getText().equalsIgnoreCase("") 	|| textNom.getText().length() >40  || textDescription.getText().length()>100) {
						 JOptionPane.showMessageDialog(null,"Merci de remplir les champs obligatoire(*) et de respecter le nombre de caractères", "Modification", JOptionPane.ERROR_MESSAGE);
					 }else {
						 if(ancienNom.equalsIgnoreCase(textNom.getText())) {
							 
							 
	
							 
							 User newUser = new User(Integer.parseInt(textId.getText()), roledeTest , textNom.getText(), textPrenom.getText(), textEmail.getText());
							 if(userD.update(newRole)) {
								 JOptionPane.showMessageDialog(null,"Le rôle "+newRole.getNom()+" a bien été enregistré","Modification",JOptionPane.INFORMATION_MESSAGE);
								 tabbedPane.setEnabledAt(1, false);
					             tabbedPane.setEnabledAt(0, true);
					             tabbedPane.setSelectedIndex(0);
					             table.setModel(userM.lister(textField.getText()));
							 }else {
								 JOptionPane.showMessageDialog(null,"Impossible de modifier le "+nomModel, "Modification", JOptionPane.ERROR_MESSAGE);
							 }
						 }else {
						 
							 if(!userD.isExist(textNom.getText())) {
								 Role newRole =new Role(Integer.parseInt(textId.getText()),textNom.getText(),textDescription.getText());
								
								 if(userD.update(newRole)) {
									 JOptionPane.showMessageDialog(null,"Le rôle "+newRole.getNom()+" a bien été enregistré","Modification",JOptionPane.INFORMATION_MESSAGE);
									 tabbedPane.setEnabledAt(1, false);
					                 tabbedPane.setEnabledAt(0, true);
					                 tabbedPane.setSelectedIndex(0);
					                 table.setModel(userM.lister(textField.getText()));
								 }else {
									 JOptionPane.showMessageDialog(null,"Impossible de modifier le "+nomModel, "Modification", JOptionPane.ERROR_MESSAGE);
								 }//fin du update
							 }else {	 
								 JOptionPane.showMessageDialog(null,"Ce "+nomModel+" existe déjà", "Modification", JOptionPane.ERROR_MESSAGE);
							 }//fin if isExiste
						 }//fin if ancienNom egual new nom
					 }
				 }else if(action.equalsIgnoreCase("Sauvegarder")){
					 if(textNom.getText().equalsIgnoreCase("")	|| textNom.getText().length() >40  || textDescription.getText().length()>100) {
						 JOptionPane.showMessageDialog(null,"Merci de remplir les champs obligatoire(*) et de respecter le nombre de caractères", "Création", JOptionPane.ERROR_MESSAGE);
					 }else {
						 if(!userD.isExist(textNom.getText())) {
							 Role newRole =new Role(textNom.getText(),textDescription.getText());
							 if(userD.create(newRole)) {
								 JOptionPane.showMessageDialog(null,"Le rôle "+newRole.getNom()+" a bien été enregistré","Création",JOptionPane.INFORMATION_MESSAGE);
								 tabbedPane.setEnabledAt(1, false);
					             tabbedPane.setEnabledAt(0, true);
					             tabbedPane.setSelectedIndex(0);
					             table.setModel(userM.lister(textField.getText()));
							 }else {
								 JOptionPane.showMessageDialog(null,"Impossible de créer le "+nomModel, "Création", JOptionPane.ERROR_MESSAGE);
							 }
						 }else {
							 JOptionPane.showMessageDialog(null,"Ce "+nomModel+" existe déjà", "Création", JOptionPane.ERROR_MESSAGE);
						 }
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
					
					textId.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
					textNom.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
					ancienNom= String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
					textDescription.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
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
					String desc = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
					String stat = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));
						if(stat.equalsIgnoreCase("active")) {
							JOptionPane.showMessageDialog(null,"Le "+nomModel+" est déjà actif","Statut",JOptionPane.WARNING_MESSAGE);
						}else {
							Role newRole =new Role(Integer.parseInt(id), nom, desc, stat);
								if(userD.activer(newRole)) {
									JOptionPane.showMessageDialog(null,"Le "+nomModel+" est maintenant actif","Statut",JOptionPane.INFORMATION_MESSAGE);
									table.setModel(userM.lister(textField.getText()));	
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
					String email = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));
					String url = String.valueOf(table.getValueAt(table.getSelectedRow(), 4));
					String idrol = String.valueOf(table.getValueAt(table.getSelectedRow(), 5));
					String stat = String.valueOf(table.getValueAt(table.getSelectedRow(), 6));
						if(stat.equalsIgnoreCase("inactif")) {
							JOptionPane.showMessageDialog(null,"Le "+nomModel+" est déjà inactif","Statut",JOptionPane.WARNING_MESSAGE);
							
						}else {
							//Role roleuser = new Role(userD.findRolUser(Integer.parseInt(id)));
							
								User userNew =new User(Integer.parseInt(id), (Role) userD.findRolUser(Integer.parseInt(id)), nom, prenom, email, url, stat);
								if(userD.desactiver(userNew)) {
									JOptionPane.showMessageDialog(null,"Le "+nomModel+" est maintenant inactif","Statut",JOptionPane.INFORMATION_MESSAGE);
									table.setModel(userM.lister(textField.getText()));	
								}
						}
				}
			}
		});
		btnDesactiver.setBounds(194, 18, 106, 23);
		panel_1.add(btnDesactiver);
		
		lblAffichage.setBounds(476, 387, 367, 19);
		panelListe.add(lblAffichage);
		
		JPanel panelGestion = new JPanel();
		tabbedPane.addTab("Gestion", null, panelGestion, null);
		panelGestion.setLayout(null);
		
		tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(0, true);
        tabbedPane.setSelectedIndex(0);
		
		JLabel lblNom = new JLabel("Nom : (*)");
		lblNom.setBounds(10, 11, 70, 21);
		panelGestion.add(lblNom);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(646, 187, 70, 21);
		panelGestion.add(lblDescription);
		
		textNom = new JTextField();
		textNom.setBounds(115, 12, 210, 20);
		panelGestion.add(textNom);
		textNom.setColumns(10);
		
		textId = new JTextField();
		textId.setColumns(10);
		textId.setBounds(932, 11, 142, 20);
		panelGestion.add(textId);
		textId.setVisible(false);
		
		textDescription.setBounds(751, 185, 210, 65);
		panelGestion.add(textDescription);
		
		JLabel lblNewLabel_2 = new JLabel("(*) Champs obligatoires");
		lblNewLabel_2.setBounds(10, 388, 142, 21);
		panelGestion.add(lblNewLabel_2);
		/*
		 * Annulation et retour a la page accueil role
		 */
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(1, false);
                tabbedPane.setEnabledAt(0, true);
                tabbedPane.setSelectedIndex(0);
                table.setModel(userM.lister(textField.getText()));
			}
		});
		btnAnnuler.setBounds(93, 431, 106, 23);	
		panelGestion.add(btnAnnuler);
		
		btnSauvegarder.setBounds(271, 432, 108, 23);
		panelGestion.add(btnSauvegarder);
		
		JLabel lblNewLabel = new JLabel("40 caractères maximum");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(115, 31, 119, 18);
		panelGestion.add(lblNewLabel);
		
		JLabel lblCaratresMaximum = new JLabel("100 caractères maximum");
		lblCaratresMaximum.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblCaratresMaximum.setBounds(751, 248, 119, 18);
		panelGestion.add(lblCaratresMaximum);
		
		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setBounds(10, 61, 70, 21);
		panelGestion.add(lblPrenom);
		
		textPrenom = new JTextField();
		textPrenom.setColumns(10);
		textPrenom.setBounds(115, 59, 210, 20);
		panelGestion.add(textPrenom);
		
		JLabel lblNewLabel_1 = new JLabel("40 caractères maximum");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(115, 78, 119, 18);
		panelGestion.add(lblNewLabel_1);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(10, 187, 70, 21);
		panelGestion.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(115, 187, 210, 20);
		panelGestion.add(textEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(408, 11, 70, 21);
		panelGestion.add(lblPassword);
		
		textPassword1 = new JPasswordField();
		textPassword1.setBounds(476, 12, 176, 20);
		panelGestion.add(textPassword1);
		
		textPassword2 = new JPasswordField();
		textPassword2.setBounds(476, 43, 176, 20);
		panelGestion.add(textPassword2);
		
		JComboBox cmbRoles = new JComboBox();
		cmbRoles.setBounds(115, 128, 210, 22);
		panelGestion.add(cmbRoles);
		
		JLabel lblRole = new JLabel("Role :");
		lblRole.setBounds(10, 129, 70, 21);
		panelGestion.add(lblRole);
		
		textUrl = new JTextField();
		textUrl.setColumns(10);
		textUrl.setBounds(932, 42, 142, 20);
		panelGestion.add(textUrl);
	}
}
