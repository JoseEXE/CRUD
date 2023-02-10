package metier;

import javax.swing.table.DefaultTableModel;

import controller.RoleDao;
import controller.UserDao;
import model.Role;
import model.User;

public class UserMetier {
	/*
	 * instanciation Class roleDao
	 */
	UserDao userDao = new UserDao();
	/*
	 * instanciation variable qui compte le nbr total de registres dans la bdd
	 */
	public int totalM = 0;
	/*
	 * méthode qui affiche la iste la bdd role et l'affiche dans vue role
	 * @param txt: contenu de la barre recherche si on faire un findByName
	 */
	
	public DefaultTableModel lister(String txt) {
		String col[]= {"id","Nom","Prenom","email","url","Statut"};
		DefaultTableModel list = new DefaultTableModel(null,col);
		totalM=0;
		/*
		 * injection du param txt (recherche dans méthode read(), qui devient un findByName
		 */
		for (User item : userDao.read(txt)) {
			list.addRow(new Object[] {
					item.getId(),
					item.getNom(),
					item.getPrenom(),
					item.getEmail(),
					item.getUrl(),
					item.getId_role().getNom(),
					item.getStatut()
			});
			totalM++;
		}
		return list;
	}
}
