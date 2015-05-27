
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;


import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListCellRenderer;

import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Alexis
 */
public class Interface extends javax.swing.JFrame {

	/**
	 * Creates new form Interface
	 */
	public Interface(VDC vdc) {
		initComponents(vdc);
		pDessin.Refresh(this.vdc);
	}
	
	private void initComponents(VDC vdc) {

		pCarte = new javax.swing.JPanel();
		pDessin = new DessinVille();
		pModifVille = new javax.swing.JPanel();
		lTitreCoordonnees = new javax.swing.JLabel();
		lCoordonees = new javax.swing.JLabel();
		lTitreVille = new javax.swing.JLabel();
		tfVille = new javax.swing.JTextField();
		lCoordonneeVille = new javax.swing.JLabel();
		pAlgo = new javax.swing.JPanel();
		cbChoixAlgo = new javax.swing.JComboBox();
		bExecuter = new javax.swing.JButton();
		pTabVille = new javax.swing.JPanel();
		jScrollTabVille = new javax.swing.JScrollPane();
		listVilleAlgoModel = new DefaultListModel<Object>();
		listVilleAlgo = new javax.swing.JList<Object>(listVilleAlgoModel);
		jMenuBar1 = new javax.swing.JMenuBar();
		mFichier = new javax.swing.JMenu();
		miGrapheAleatoire = new javax.swing.JMenuItem();
		miQuitter = new javax.swing.JMenuItem();
		miCharger = new javax.swing.JMenuItem();
		miEnregistrer = new javax.swing.JMenuItem();
		mEditer = new javax.swing.JMenu();
		miReinitialiser = new javax.swing.JMenuItem();
		this.vdc = vdc;
		selectVille = null;
		contextMenu = new JPopupMenu();

		pDessin.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				// Creation de la chaine de caractère
				StringBuffer chaine = new StringBuffer();
				chaine.append("X :");
				chaine.append(e.getX());
				chaine.append(" | ");
				chaine.append("Y :");
				chaine.append(e.getX());
				lCoordonees.setText(chaine.toString());
			}
		});

		pDessin.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Focus sur le champ de ville quand on clique sur le panneau de
				// dessin
				tfVille.requestFocus();
				lCoordonneeVille.setText(lCoordonees.getText());
				if (SwingUtilities.isRightMouseButton(e)) {
					selectVille = pDessin.getVilleEnCours();
					if (selectVille != null) {
						contextMenu.setLocation(e.getXOnScreen(),
								e.getYOnScreen());
						contextMenu.setEnabled(true);
						contextMenu.setVisible(true);
					}
				} else {
					contextMenu.setEnabled(false);
					contextMenu.setVisible(false);
				}
			}
		});

		tfVille.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfVilleActionPerformed(evt);
				refreshJlist();
			}
		});

		JMenuItem modif = new JMenuItem("Modifier");
		JMenuItem suppr = new JMenuItem("Supprimer");
		modif.addActionListener(contextMenuModif);
		suppr.addActionListener(contextMenuSuppr);
		contextMenu.add(modif);
		contextMenu.addSeparator();
		contextMenu.add(suppr);
		listVilleAlgo.add(contextMenu);

		// contextMenu.addPopupMenuListener(null);

		//Classe interne pour modifier couleur de la JList
		class JlistVilleAlgoRenderer extends DefaultListCellRenderer {
			public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
	            Component c = super.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );
	            if ( value instanceof Chemin){
	            	int indexDuChemin=Interface.this.vdc.listeSolution.indexOf(value);
	            	Color couleurDuChemin=Interface.this.pDessin.listeCouleurChemin.get(indexDuChemin);
	            	c.setBackground(couleurDuChemin);
	            }
	            return c;
			}
		}
		listVilleAlgo.setCellRenderer(new JlistVilleAlgoRenderer());
		
//		listVilleAlgo.setCellRenderer(new ListCellRenderer<Object>() {

		
		listVilleAlgo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					listVilleAlgo.setSelectedIndex(listVilleAlgo
							.locationToIndex(e.getPoint()));
					int row = listVilleAlgo.getSelectedIndex();
					if (listVilleAlgo.getModel().getElementAt(row) instanceof Ville) {
						if (row >= 0) {
							selectVille = (Ville) listVilleAlgo.getModel()
									.getElementAt(row);
							contextMenu.setLocation(e.getXOnScreen(),
									e.getYOnScreen());
							contextMenu.setEnabled(true);
							contextMenu.setVisible(true);
						}
					}
				} else {
					contextMenu.setEnabled(false);
					contextMenu.setVisible(false);
				}
			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setFocusCycleRoot(false);
		setFocusTraversalPolicyProvider(true);
		setMinimumSize(new java.awt.Dimension(850, 600));
		setPreferredSize(new java.awt.Dimension(900, 700));

		pCarte.setLayout(new java.awt.BorderLayout());

		pDessin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(
				102, 102, 102), 1, true));

		javax.swing.GroupLayout pDessinLayout = new javax.swing.GroupLayout(
				pDessin);
		pDessin.setLayout(pDessinLayout);
		pDessinLayout.setHorizontalGroup(pDessinLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 645,
				Short.MAX_VALUE));
		pDessinLayout.setVerticalGroup(pDessinLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 369,
				Short.MAX_VALUE));

		pCarte.add(pDessin, java.awt.BorderLayout.CENTER);

		pModifVille.setPreferredSize(new java.awt.Dimension(682, 40));

		lTitreCoordonnees.setText("Coordonnées :");

		lTitreVille.setText("Ville :");

		tfVille.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfVilleActionPerformed(evt);
			}
		});

		lCoordonneeVille.setName("lCoordonneVille"); // NOI18N

		javax.swing.GroupLayout pModifVilleLayout = new javax.swing.GroupLayout(
				pModifVille);
		pModifVille.setLayout(pModifVilleLayout);
		pModifVilleLayout
				.setHorizontalGroup(pModifVilleLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pModifVilleLayout
										.createSequentialGroup()
										.addComponent(
												lCoordonees,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												124,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(21, 21, 21)
										.addComponent(
												lTitreVille,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												36,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												tfVille,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												95,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(32, 32, 32)
										.addComponent(
												lTitreCoordonnees,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												84,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lCoordonneeVille,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												114,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(131, Short.MAX_VALUE)));
		pModifVilleLayout
				.setVerticalGroup(pModifVilleLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pModifVilleLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(
												lTitreCoordonnees,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												lCoordonees,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												lTitreVille,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												tfVille,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(lCoordonneeVille,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));

		pCarte.add(pModifVille, java.awt.BorderLayout.NORTH);

		pAlgo.setPreferredSize(new java.awt.Dimension(682, 40));

		cbChoixAlgo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Plus Proche Voisin", "Insertion Du Plus Eloigne", "Two Opt",
				"Vider" }));

		// cbChoixAlgo.addActionListener(new java.awt.event.ActionListener() {
		// public void actionPerformed(java.awt.event.ActionEvent evt) {
		// cbChoixAlgoActionPerformed(evt);
		// }
		// });

		bExecuter.setText("Exécuter");
		bExecuter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bExecuterActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pAlgoLayout = new javax.swing.GroupLayout(pAlgo);
		pAlgo.setLayout(pAlgoLayout);
		pAlgoLayout
				.setHorizontalGroup(pAlgoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pAlgoLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												cbChoixAlgo,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												180,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												bExecuter,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												111,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(340, Short.MAX_VALUE)));
		pAlgoLayout.setVerticalGroup(pAlgoLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				pAlgoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(bExecuter,
								javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cbChoixAlgo,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		pCarte.add(pAlgo, java.awt.BorderLayout.SOUTH);

		getContentPane().add(pCarte, java.awt.BorderLayout.CENTER);

		pTabVille.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));
		pTabVille.setFocusTraversalPolicyProvider(true);
		pTabVille.setPreferredSize(new java.awt.Dimension(300, 399));

		jScrollTabVille.setName("lVilleAlgo"); // NOI18N

		listVilleAlgo.setName("listVilleAlgo"); // NOI18N
		jScrollTabVille.setViewportView(listVilleAlgo);

		javax.swing.GroupLayout pTabVilleLayout = new javax.swing.GroupLayout(
				pTabVille);
		pTabVille.setLayout(pTabVilleLayout);
		pTabVilleLayout.setHorizontalGroup(pTabVilleLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 148, Short.MAX_VALUE)
				.addGroup(
						pTabVilleLayout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollTabVille,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										148, Short.MAX_VALUE)));
		pTabVilleLayout.setVerticalGroup(pTabVilleLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 449, Short.MAX_VALUE)
				.addGroup(
						pTabVilleLayout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollTabVille,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										449, Short.MAX_VALUE)));

		getContentPane().add(pTabVille, java.awt.BorderLayout.EAST);

		mFichier.setText("Fichier");

		miCharger.setText("Charger");
		miCharger.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				miChargerActionPerformed(evt);
			}
		});
		mFichier.add(miCharger);

		miEnregistrer.setText("Enregistrer");
		miEnregistrer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				miEnregistrerActionPerformed(evt);
			}
		});
		mFichier.add(miEnregistrer);

		miGrapheAleatoire.setText("Créer un graphe aléatoire");
		miGrapheAleatoire
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						miGrapheAleatoireActionPerformed(evt);
					}
				});
		mEditer.add(miGrapheAleatoire);

		miQuitter.setText("Quitter");
		miQuitter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				miQuitterActionPerformed(evt);
			}
		});
		mFichier.add(miQuitter);

		jMenuBar1.add(mFichier);

		mEditer.setText("Editer");

		miReinitialiser.setText("Re-initialiser");
		miReinitialiser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				miReinitialiserActionPerformed(evt);
			}
		});
		mEditer.add(miReinitialiser);

		jMenuBar1.add(mEditer);

		setJMenuBar(jMenuBar1);
		setResizable(false);
		pack();
	}

	// private void cbChoixAlgoActionPerformed(java.awt.event.ActionEvent evt)
	// {// GEN-FIRST:event_cbChoixAlgoActionPerformed
	// // TODO add your handling code here:
	// }// GEN-LAST:event_cbChoixAlgoActionPerformed

	private void bExecuterActionPerformed(java.awt.event.ActionEvent evt) {
		// on switch sur le choix et on lance l'algo
		String algo = cbChoixAlgo.getSelectedItem().toString();
		switch (algo) {
		case "Plus Proche Voisin":
			vdc.plusProcheVoisin((Ville) vdc.liste_noeud.get(1));
			break;
		case "Insertion Du Plus Eloigne":
			vdc.insertionVoisinLePlusEloigne();
			break;
		case "Two Opt":
			vdc.two_opt();
			break;
		case "Vider":
			vdc.listeSolution.clear();
			break;
			
		}
		pDessin.Refresh(vdc);
		refreshJlist();

	}

	private void tfVilleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tfVilleActionPerformed
		String text = tfVille.getText();
		if (text.length() != 0) {
			int x = pDessin.getx();
			int y = pDessin.gety();
			// System.out.println(Integer.toString(x)+" "+Integer.toString(y));
			Ville v = new Ville(x, y, text);
			vdc.ajouterVille(v);
			pDessin.Refresh(vdc);
			tfVille.setText("");

		} else {
			tfVille.setText("");
		}

	}

	private void miEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {
		//on recupere via le jfc le nom du fichier
		JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int retourVal = jfc.showSaveDialog(this);
		//si on recup un fichier
		if (retourVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			String nomFichier = file.getAbsoluteFile().toString();
			//on crée le CSV au nom du fichier
			CSV csv = this.vdc.vdc_to_CSV(nomFichier);
			//on essaye de l'imprimer et on affiche un message d'erreur
			try {
				csv.imprimeCSV();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"Erreur lors de l'écriture du fichier", "Notification",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void miChargerActionPerformed(java.awt.event.ActionEvent evt) {
		//on recupere via le jfc le nom du fichier
		JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int retourVal = jfc.showOpenDialog(this);
		//si on recup un fichier
		if (retourVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			String nomFichier = file.getAbsoluteFile().toString();
			//on crée le CSV et le potentiel nouveau Modele (VDC)
			CSV csv = new CSV(nomFichier);
			VDC chargeVDC = new VDC("");
			//on essaye de le charger
			try {
				csv.chargerCSV();
				chargeVDC.csv_to_VDC(csv);
				//si le chargement a été possible, on modifie le modèle
				if (chargeVDC != null) {
					//on essaye de le mettre a l'échelle si les valeurs sont trop grandes
					VDC modif=chargeVDC.miseAEchelle(pDessin.getWidth(), pDessin.getHeight());
					if (modif!=null){
						chargeVDC=modif;
					}
					this.vdc = chargeVDC;
					pDessin.Refresh(vdc);
					refreshJlist();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Erreur de chargement",
						"Notification", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void miQuitterActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void miReinitialiserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_miReinitialiserActionPerformed
		// TODO add your handling code here:

		vdc.liste_noeud.clear();
		vdc.listeSolution.clear();
		pDessin.Refresh(vdc);
		refreshJlist();

	}// GEN-LAST:event_miReinitialiserActionPerformed

	private void listVilleAlgoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_miQuitterActionPerformed

	}// GEN-LAST:event_miQuitterActionPerformed

	ActionListener contextMenuSuppr = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			vdc.supprimerNoeud(selectVille);
			vdc.listeSolution.clear();
			pDessin.Refresh(vdc);
			refreshJlist();
			contextMenu.setEnabled(false);
			contextMenu.setVisible(false);
		}
	};

	ActionListener contextMenuModif = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String inputValue = JOptionPane.showInputDialog(
					"Donnez le nouveau nom :", selectVille.nom);
			if (inputValue != null) {
				selectVille.setNom(inputValue);
			}
			// pDessin.Refresh(vdc);
			refreshJlist();
			contextMenu.setEnabled(false);
			contextMenu.setVisible(false);
		}
	};

	private void refreshJlist() {
		// System.out.println("coucou");
		// DefaultListModel model=new DefaultListModel();
		// String[] list=new String[vdc.liste_noeud.size()];
		listVilleAlgoModel.clear();
		for (Chemin c : vdc.listeSolution) {
			listVilleAlgoModel.addElement(c);
		}
		listVilleAlgoModel.addElement(" ");
		for (int i = 0; i < vdc.liste_noeud.size(); i++) {
			Noeud n = vdc.liste_noeud.get(i);
			Ville v = (Ville) n;
			listVilleAlgoModel.addElement(v);
		}
		listVilleAlgo = new JList(listVilleAlgoModel);
		listVilleAlgo.updateUI();
	}

	private void miGrapheAleatoireActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbChoixAlgoActionPerformed
		String inputValue = JOptionPane
				.showInputDialog("Donner le nombre de ville a générer :");
		boolean valeur = true;
		char[] tab = inputValue.toCharArray();
		for (char carac : tab) {
			if (!Character.isDigit(carac)) {
				valeur = false;
			}
		}
		if (valeur) {
			int limit = Integer.parseInt(inputValue);
			vdc.listeSolution.clear();
			vdc.liste_noeud.clear();
			double limitX = pDessin.getSize().getWidth();
			int X = (int) limitX;
			double limitY = pDessin.getSize().getHeight();
			int Y = (int) limitY;
			vdc = new VDC(limit, X, Y);
			pDessin.Refresh(vdc);
			refreshJlist();
		} else {
			JOptionPane.showMessageDialog(pDessin,
					"Vous devez renseigner un integer", "Attention",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// compo graphique
	private javax.swing.JButton bExecuter;
	private javax.swing.JComboBox cbChoixAlgo;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JScrollPane jScrollTabVille;
	private javax.swing.JLabel lCoordonees;
	private javax.swing.JLabel lCoordonneeVille;
	private javax.swing.JLabel lTitreCoordonnees;
	private javax.swing.JLabel lTitreVille;
	private javax.swing.JList<Object> listVilleAlgo;
	private javax.swing.JMenu mEditer;
	private javax.swing.JMenu mFichier;
	private javax.swing.JMenuItem miGrapheAleatoire;
	private javax.swing.JMenuItem miEnregistrer;
	private javax.swing.JMenuItem miCharger;
	private javax.swing.JMenuItem miQuitter;
	private javax.swing.JMenuItem miReinitialiser;
	private javax.swing.JPanel pAlgo;
	private javax.swing.JPanel pCarte;
	private DessinVille pDessin;
	private javax.swing.JPanel pModifVille;
	private javax.swing.JPanel pTabVille;
	private javax.swing.JTextField tfVille;
	private DefaultListModel<Object> listVilleAlgoModel;
	private JPopupMenu contextMenu;

	private Ville selectVille;
	private VDC vdc;

}
