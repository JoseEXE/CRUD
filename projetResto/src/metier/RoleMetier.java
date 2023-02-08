package metier;

import javax.swing.table.DefaultTableModel;

import controller.RoleDao;
import model.Role;

public class RoleMetier {

	RoleDao roleDao = new RoleDao();
	public int totalM = 0;
	
	public DefaultTableModel lister(String txt) {
		String col[]= {"id","Titre","Description","Statut"};
		DefaultTableModel list = new DefaultTableModel(null,col);
		totalM=0;
		for (Role item : roleDao.read(txt)) {
			list.addRow(new Object[] {
					item.getId(),
					item.getNom(),
					item.getDescription(),
					item.getStatut()
					
			});
			totalM++;
		}
		return list;
		
	}
}
