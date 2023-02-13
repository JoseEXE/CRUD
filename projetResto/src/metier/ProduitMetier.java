package metier;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import controller.Cat_produitDao;
import controller.ProduitDao;
import model.Cat_produit;
import model.Produit;
import model.Role;

public class ProduitMetier {
	ProduitDao prodD=new ProduitDao();
	Cat_produitDao catprodD=new Cat_produitDao();
	/*
	 * instanciation variable qui compte le nbr total de registres dans la bdd
	 */
	public int totalM;
	/*
	 * méthode qui affiche la iste la bdd role et l'affiche dans vue role
	 * @param txt: contenu de la barre recherche si on faire un findByName
	 */
	public DefaultTableModel lister(String txt,String tri) {
		String col[]= {"id","Nom","Description","Code","Categorie","Type","Prix","modifiant","Date-modif","statut"};
		DefaultTableModel tableau=new DefaultTableModel(null,col);
		
		for (Produit item : prodD.filtrer(txt,tri)) {
			tableau.addRow(new Object[] {
					item.getId(),
					item.getNom(),
					item.getDescription(),
					item.getCode(),
					item.getId_cat_produit().getNom(),
					item.getType_statut(),
					item.getPrix(),
					item.getId_user().getNom(),
					item.getDate_mod(),
					item.getStatut()
			});
			totalM++;
		}
		
		return tableau;
	}
	@SuppressWarnings("rawtypes")
	public DefaultComboBoxModel selectCmb(){
		DefaultComboBoxModel itemCmb = new DefaultComboBoxModel();
		ArrayList<Cat_produit> listRoles = new ArrayList<>();
		listRoles = catprodD.selectRoles();
		//itemCmb.addElement(new Cat_produit(0, "Tous"));
		for(Cat_produit item: listRoles){
			itemCmb.addElement(new Cat_produit(item.getId(), item.getNom()));
		}

		
		return itemCmb;
	}
}
