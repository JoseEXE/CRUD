package metier;

import javax.swing.table.DefaultTableModel;

import controller.ProduitDao;
import model.Produit;

public class ProduitMetier {
	ProduitDao prodD=new ProduitDao();
	/*
	 * instanciation variable qui compte le nbr total de registres dans la bdd
	 */
	public int totalM;
	/*
	 * méthode qui affiche la iste la bdd role et l'affiche dans vue role
	 * @param txt: contenu de la barre recherche si on faire un findByName
	 */
	public DefaultTableModel lister(String txt) {
		String col[]= {"id","Nom","Description","Code","Categorie","Type","Prix","modifiant","Date-modif","statut"};
		DefaultTableModel tableau=new DefaultTableModel(null,col);
		
		for (Produit item : prodD.read(txt)) {
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
}
