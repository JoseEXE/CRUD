package metier;

import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import controller.DetailDao;
import model.Detail_commande;
import model.Produit;

public class DetailMetier {
	DetailDao detailD=new DetailDao();
	public DefaultTableModel listeDetail(int id) {
		String col[]= {"id","Commande N°","Produit","Catégorie","Quantité","Prix unitaire"};
		DefaultTableModel tableau=new DefaultTableModel(null,col);
		
		for (Detail_commande item : detailD.findById(id)) {
			tableau.addRow(new Object[] {
					item.getId(),
					item.getId_commande().getId(),
					item.getId_produit().getNom(),
					item.getId_produit().getId_cat_produit().getNom(),
					item.getQuantite(),
					item.getPrix_unitaire()
			});
		}
		return tableau;
	}
	public Boolean checkRegexQte(String txt) {

		Boolean test =Pattern.matches("[1-9]", txt);
		System.out.println(test);
		return test;
		
	}
}
