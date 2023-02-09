package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VueFrameMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueFrameMain frame = new VueFrameMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VueFrameMain() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 779);
		JDesktopPane desktopPane = new JDesktopPane();
		/*
		 * creation menu
		 */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		/*
		 *Gestion menu carte
		 */
		JMenu mnCarte = new JMenu("Carte");
		menuBar.add(mnCarte);
		/*
		 *Gestion sous-menu produit
		 */
		JMenuItem mntmProduit = new JMenuItem("Produits");
		mntmProduit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		mnCarte.add(mntmProduit);
		/*
		 *Gestion sous-menu Catégorie
		 */
		JMenuItem mntmCategories = new JMenuItem("Catégories");
		mntmCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.add(new VueCat_produit());
				desktopPane.repaint();
				desktopPane.revalidate();
			}
		});
		mntmCategories.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mnCarte.add(mntmCategories);
		/*
		 *Gestion menu commandes
		 */
		JMenu mnCommandes = new JMenu("Commandes");
		menuBar.add(mnCommandes);
		/*
		 *Gestion sous-menu clients
		 */
		JMenuItem mntmClients = new JMenuItem("Clients");
		mntmClients.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mnCommandes.add(mntmClients);
		/*
		 *Gestion sous-menu commandes
		 */
		JMenuItem mntmCreerCom = new JMenuItem("Créer Commandes");
		mntmCreerCom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mnCommandes.add(mntmCreerCom);
		/*
		 *Gestion menu Accès
		 */
		JMenu mnAcces = new JMenu("Accès");
		menuBar.add(mnAcces);
		/*
		 *Gestion sous-menu users
		 */
		JMenuItem mntmUser = new JMenuItem("Utilisateurs");
		mntmUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
		mnAcces.add(mntmUser);
		/*
		 *Gestion sous-menu roles
		 */
		JMenuItem mntmRole = new JMenuItem("Rôles");
		mntmRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.removeAll();
				desktopPane.add(new VueRole());
				desktopPane.repaint();
				desktopPane.revalidate();
			}
		});
		mntmRole.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mnAcces.add(mntmRole);
		/*
		 *Gestion menu sortir
		 */
		JMenu mnExit = new JMenu("Sortir");
		mnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		 * Titre dynamique restaurant
		 */
		JLabel lblNomResto = new JLabel("Nom du resto");
		lblNomResto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomResto.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNomResto.setBounds(350, 10, 426, 57);
		contentPane.add(lblNomResto);
		/*
		 * Nom dynamique de l'utilisateur
		 */
		JLabel lblNewLabel = new JLabel("Bienvenue Nom User");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 87, 269, 28);
		contentPane.add(lblNewLabel);
		/*
		 * creation panneau qui recois toutes les autres vues
		 */
		desktopPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Restaurant Chipontha\u00EF", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		desktopPane.setBackground(SystemColor.control);
		desktopPane.setBounds(20, 125, 1136, 565);
		contentPane.add(desktopPane);
		/*
		 * label image accueil
		 */
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(VueFrameMain.class.getResource("/ressources/bateau_sushis.jpg")));
		lblNewLabel_1.setBounds(6, 19, 1122, 535);
		desktopPane.add(lblNewLabel_1);
	}
}
