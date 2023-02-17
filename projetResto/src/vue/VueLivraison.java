package vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import controller.CommandeDao;
import controller.DetailDao;
import metier.CommandeMetier;
import metier.DetailMetier;
import model.Commande;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class VueLivraison extends JPanel {
	private JTextField textField;
	private JTable tableCommande;
	/*
	 * instanciation Class CommandeMetier
	 */
	CommandeDao commandeD = new CommandeDao();
	/*
	 * instanciation Class CommandeDao
	 */
	CommandeMetier commandeM = new CommandeMetier();
	/*
	 * instanciation Class DetailMetier
	 */
	DetailMetier detailM = new DetailMetier();
	/*
	 * instanciation Class DetailDao
	 */
	DetailDao detailD = new DetailDao();
	/*
	 * creation variable string Commande pour les differents messages d'affichage fenetre role
	 */
	String nomModel = "Commande(s)";
	String nomModel2 = "détail(s)de commande";
	/*
	 * creation variable pour les 2 actions create et update
	 */
	String action = "";
	/*
	 * creation variable pour recupere ancien nom lors de la modification de registre
	 */
	int idCommande;
	private JTable tableDetail;
	private JTextField textField_1;
	private JTable tableArchive;
	/**
	 * Create the panel.
	 */
	public VueLivraison() {
		setBounds(0, 0, 1136, 565);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1136, 565);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Tableau de bord commandes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		tabbedPane.setEnabledAt(0, true);
		panelListe.setLayout(null);
		
		JLabel lblRecherche = new JLabel("Rechercher par id :");
		lblRecherche.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecherche.setBounds(20, 30, 124, 27);
		panelListe.add(lblRecherche);
		
		
		textField = new JTextField();
		textField.setBounds(194, 35, 237, 19);
		panelListe.add(textField);
		textField.setColumns(10);
		
		JLabel lblAffichage_1 = new JLabel("");
		lblAffichage_1.setBounds(533, 454, 367, 19);
		panelListe.add(lblAffichage_1);
		
		JLabel lblAffichage = new JLabel("");
		lblAffichage.setBounds(533, 188, 367, 19);
		panelListe.add(lblAffichage);
		
		tableCommande = new JTable();
		tableCommande.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idCommande =(int)tableCommande.getValueAt(tableCommande.getSelectedRow(), 0);
				tableDetail(idCommande);
				lblAffichage_1.setText("Affichage de "+detailM.totalM +" "+nomModel2+" sur un total de "+ detailD.total()+" registres");
			}
		});
		tableCommande("");
		lblAffichage.setText("Affichage de "+commandeM.totalM +" "+nomModel+" sur un total de "+ commandeD.total()+" registres");
		JButton btnChercher = new JButton("Chercher");
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableCommande(textField.getText());
				lblAffichage.setText("Affichage de "+commandeM.totalM +" "+nomModel+" sur un total de "+ commandeD.total()+" registres");
			}
		});
		btnChercher.setBounds(518, 34, 106, 23);
		panelListe.add(btnChercher);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 67, 897, 111);
		panelListe.add(scrollPane);
		scrollPane.setViewportView(tableCommande);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "D\u00E9tails de la Commande ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(20, 230, 897, 214);
		panelListe.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 877, 182);
		panel_2.add(scrollPane_1);
		
		tableDetail = new JTable();
		scrollPane_1.setViewportView(tableDetail);
		JButton btnExpedier = new JButton("Expédiée");
		btnExpedier.setBounds(30, 188, 114, 32);
		panelListe.add(btnExpedier);
		
		btnExpedier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(tableCommande.getSelectedColumnCount()==0) {
				JOptionPane.showMessageDialog(null, "Vous devez selectionner une commande","Expediée", JOptionPane.WARNING_MESSAGE);
			}else {
				Double total =(Double)tableCommande.getValueAt(tableCommande.getSelectedRow(), 2);
				String type =String.valueOf(tableCommande.getValueAt(tableCommande.getSelectedRow(), 3));
				String etat = "Expédiée";
				Commande commandeEnCours=new Commande(idCommande,total,type,etat);
				commandeD.update(commandeEnCours);
				tableCommande(textField.getText());
				idCommande=0;
				tableDetail(idCommande);
			}
			}});
		
		JPanel panelArchive = new JPanel();
		tabbedPane.addTab("Archive", null, panelArchive, null);
		tabbedPane.setEnabledAt(1, true);
		panelArchive.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Archives", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(26, 69, 906, 356);
		panelArchive.add(panel_1);
		panel_1.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(200, 37, 237, 19);
		panelArchive.add(textField_1);
		
		JLabel lblRecherche_1 = new JLabel("Rechercher par id :");
		lblRecherche_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecherche_1.setBounds(26, 32, 124, 27);
		panelArchive.add(lblRecherche_1);

		JLabel lblAffichageArchive = new JLabel("");
		lblAffichageArchive.setBounds(565, 438, 367, 19);
		panelArchive.add(lblAffichageArchive);
		
		
		tableArchive = new JTable();
		tableArchive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		tableArchive("");
		lblAffichageArchive.setText("Affichage de "+commandeM.totalM +" "+nomModel+" sur un total de "+ commandeD.total()+" registres");
	    
		JButton btnChercherArchive = new JButton("Chercher");
		btnChercherArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableArchive(textField_1.getText());
				lblAffichageArchive.setText("Affichage de "+commandeM.totalM +" registres sur un total de "+ commandeD.total()+" registres");
			}
		});
		btnChercherArchive.setBounds(524, 36, 106, 23);
		panelArchive.add(btnChercherArchive);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 22, 886, 324);
		panel_1.add(scrollPane_2);
		scrollPane_2.setViewportView(tableArchive);
		
		
		JButton btnGnrerUnPdf = new JButton("Générer un PDF");
		btnGnrerUnPdf.setBounds(26, 437, 137, 23);
		panelArchive.add(btnGnrerUnPdf);
	}
	public void tableCommande(String txt) {
		
		tableCommande.setModel(commandeM.lister(txt));
		tableCommande.getColumnModel().getColumn(0).setMaxWidth(80);
		tableCommande.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		tableCommande.getColumnModel().getColumn(2).setMaxWidth(80);
		tableCommande.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(80);
		TableRowSorter order = new TableRowSorter(tableCommande.getModel());
		tableCommande.setRowSorter(order);
	}
	public void tableDetail(int id) {
		
		tableDetail.setModel(detailM.listeDetail1(id));
		tableDetail.getColumnModel().getColumn(0).setMaxWidth(80);
		tableDetail.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
		tableDetail.getColumnModel().getColumn(1).setMaxWidth(80);
		tableDetail.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(80);
		tableDetail.getColumnModel().getColumn(2).setMinWidth(120);
		tableDetail.getTableHeader().getColumnModel().getColumn(2).setMinWidth(120);
		tableDetail.getColumnModel().getColumn(2).setMaxWidth(120);
		tableDetail.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(120);
		tableDetail.getColumnModel().getColumn(3).setMaxWidth(80);
		tableDetail.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(80);
		tableDetail.getColumnModel().getColumn(4).setMaxWidth(30);
		tableDetail.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(30);
		TableRowSorter order = new TableRowSorter(tableDetail.getModel());
		tableDetail.setRowSorter(order);
	}
	public void tableArchive(String txt) {
		
		tableArchive.setModel(commandeM.listerArchive(txt));
		tableArchive.getColumnModel().getColumn(0).setMaxWidth(80);
		tableArchive.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(80);
	}
}
