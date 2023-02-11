package metier;

import javax.swing.table.DefaultTableModel;

import controller.ClientDao;
import model.Client;




public class ClientMetier {
	/*
	 * instanciation Class roleDao
	 */
ClientDao clientD=new ClientDao();
	/*
	 * instanciation variable qui compte le nbr total de registres dans la bdd
	 */
	public int totalM = 0;
	/*
	 * méthode qui affiche la iste la bdd role et l'affiche dans vue role
	 * @param txt: contenu de la barre recherche si on faire un findByName
	 */
	
	public DefaultTableModel lister(String txt) {
		String col[]= {"id","Nom","Prenom","Telephone","Statut"};
		DefaultTableModel list = new DefaultTableModel(null,col);
		totalM=0;
		/*
		 * injection du param txt (recherche dans méthode read(), qui devient un findByName
		 */
		for (Client item : clientD.read(txt)) {
			list.addRow(new Object[] {
					item.getId(),
					item.getNom(),
					item.getPrenom(),
					item.getNumtel(),
					item.getStatut()
			});
			System.out.println(item);
			totalM++;
		}
		System.out.println(list);
		return list;
	}
}
