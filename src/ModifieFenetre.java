import javax.swing.*;
import java.awt.*;

public class ModifieFenetre{

	private DataFenetre dataFenetre;
	private JPanel grille;
	private JPanel menu;
	public static final int MENU_ACCEUIL=0;
	public static final int MENU_SAUVEGARDER=1;
	public static final int MENU_MODIFIE_GRILLE=2;
	public static final int NOUVELLE_GRILLE=3;
	public static final int MENU_CHOIX_ALGO=4;
	public static final int RETOUR_MENU_CHOIX_ALGO=5;
	public static final int MENU_MODE_MANUEL=6;
	public static final int MENU_RESULTAT=7;

	public ModifieFenetre(DataFenetre dataFenetre){
		this.dataFenetre = dataFenetre;
	}

	public void changeMenu(int numMenu){
		if (numMenu == ModifieFenetre.MENU_ACCEUIL){
			this.acceuil();
		}
		if (numMenu == ModifieFenetre.MENU_SAUVEGARDER){
			this.acceuil();
		}
		if (numMenu == ModifieFenetre.MENU_MODIFIE_GRILLE){
			this.modifieGrille();
		}
		if (numMenu == ModifieFenetre.NOUVELLE_GRILLE){
			this.creerGrille();
		}
		if (numMenu == ModifieFenetre.MENU_CHOIX_ALGO){
			this.newMenuChoixAlgo();
		}
		if (numMenu == ModifieFenetre.RETOUR_MENU_CHOIX_ALGO){
			this.retourChoixAlgo();
		}
		if (numMenu == ModifieFenetre.MENU_MODE_MANUEL){
			this.acceuil();
		}
	}

	public void changeMenu(int numMenu, String choixAlgorithme, String choixVisuel){
		if (numMenu == ModifieFenetre.MENU_RESULTAT){
			ParcoursLabyrinthe algo = new ParcoursLabyrinthe(this.dataFenetre.getDataGrille());
	    	int nbDeplacement = algo.parcoursProfondeur();
	    	if (choixVisuel == "Automatique"){
	    		if (choixAlgorithme == "En profondeur" || nbDeplacement < 0){
	    			resultat(choixAlgorithme, nbDeplacement);
	    		}
	    		else if (choixAlgorithme == "Aleatoire"){
	    			int totalDeplcement=0;
		    		for (int i=0; i<100; i++){
		    			totalDeplcement += algo.parcoursAleatoire(false);
		    		}
		    		resultat(choixAlgorithme, totalDeplcement/100);
	    		}
	    	}
	    	if (choixVisuel == "Manuel"){
	    		this.newMenuDeplacement(choixAlgorithme, nbDeplacement, algo);
	    	}
	    }	
	}

	private void addMenu(){
		JPanel conteneur = this.dataFenetre.getConteneur();
		conteneur.add(this.menu, BorderLayout.WEST);
    	this.menu.revalidate();
    	this.menu.repaint();
    }

    private void nouveauMenu(){
    	JPanel conteneur = this.dataFenetre.getConteneur();
    	conteneur.remove(this.menu);
    	this.menu = new JPanel();
    }

    private void creerGrille(){
		JPanel contenant = this.dataFenetre.nouvelleInterface();
		contenant.setLayout(new BorderLayout());
		this.newGrille();
	    contenant.add(this.grille, BorderLayout.CENTER);
		this.newMenuModifieGrille();
		contenant.add(this.menu, BorderLayout.WEST);
		this.dataFenetre.addJPanel(contenant);
	}

	private void retourChoixAlgo(){
    	JPanel contenant = this.dataFenetre.nouvelleInterface();
		contenant.setLayout(new BorderLayout());
		this.newGrille();
	    contenant.add(this.grille, BorderLayout.CENTER);
		this.newMenuChoixAlgo();
		contenant.add(this.menu, BorderLayout.WEST);
		this.dataFenetre.addJPanel(contenant);
    }

	private void modifieGrille(){
		this.nouveauMenu();
		this.newMenuModifieGrille();
		this.addMenu();
	}


    private void newGrille(){
    	this.grille = new JPanel();
		int taille = this.dataFenetre.getDataGrille().getSize();
		JPanel[][] listePanel = this.dataFenetre.getDataGrille().getPanel();
		this.grille.setLayout(new GridLayout(taille, taille));
		for (int y=0; y<taille; y++){
	    	for (int x=0; x<taille; x++){
	    		this.grille.add(listePanel[x][y]);
		   	}
	    }
    }

    private JTextArea createConsigne(int hauteur, String contenu){
    	JTextArea consigne = new JTextArea(contenu);
		consigne.setLineWrap(true);
		consigne.setWrapStyleWord(true);
		//consigne.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		consigne.setPreferredSize(new Dimension(200, hauteur));
		consigne.setBackground(this.dataFenetre.getJFrame().getBackground());
    	return consigne;
    }

	private void acceuil(){
		JPanel contenant = this.dataFenetre.nouvelleInterface();
		contenant.setLayout(new BorderLayout());

		JLabel labelTitre = new JLabel("Construisez votre grille !", JLabel.CENTER);
		JLabel labelFichier = new JLabel("A partir d'un fichier :", JLabel.CENTER);
		JLabel labelRien = new JLabel("A partir de rien :", JLabel.CENTER);
		JLabel labelTaille = new JLabel("Taille de la grille : ", JLabel.RIGHT);
		JLabel labelCheminErreur = new JLabel("", JLabel.LEFT);
		JLabel labelTailleErreur = new JLabel("", JLabel.LEFT);

		Font fontTitre = new  Font("Arial", Font.BOLD, 50);
		Font fontSousTitre = new  Font("Arial", Font.BOLD, 20);
		labelTitre.setFont(fontTitre);
		labelFichier.setFont(fontSousTitre);
		labelRien.setFont(fontSousTitre);
		labelCheminErreur.setForeground(Color.RED);
		labelTailleErreur.setForeground(Color.RED);

		JTextField champsTaille = new JTextField();
		champsTaille.setPreferredSize(new Dimension(50,20));

		JButton buttonFichier = new JButton("Choisir le fichier");
		JButton buttonAleatoire = new JButton("Grille aleatoire");
		JButton buttonVide = new JButton("Grille vide");
		JLabel[] output = {labelTailleErreur, labelCheminErreur};
		buttonFichier.addActionListener(new BoutonOutput(output, BoutonOutput.FICHIER, this.dataFenetre));
		buttonAleatoire.addActionListener(new BoutonInputOutput(champsTaille, output, BoutonInputOutput.ALEATOIRE, this.dataFenetre));
		buttonVide.addActionListener(new BoutonInputOutput(champsTaille, output, BoutonInputOutput.VIDE, this.dataFenetre));

		JPanel panelRien = new JPanel(new GridLayout(3,1));
		JPanel panelRienTaille = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelRienTaille.add(labelTaille);
		panelRienTaille.add(champsTaille);
		panelRienTaille.add(labelTailleErreur);
		JPanel panelRienBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelRienBouton.add(buttonAleatoire);
		panelRienBouton.add(buttonVide);
		panelRien.add(labelRien);
		panelRien.add(panelRienTaille);
		panelRien.add(panelRienBouton);

		JPanel panelFichier = new JPanel(new GridLayout(3,1));
		JPanel panelFichierBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelFichierBouton.add(buttonFichier);
		panelFichierBouton.add(labelCheminErreur);
		panelFichier.add(labelFichier);
		panelFichier.add(panelFichierBouton);

		JPanel sousContenant = new JPanel(new GridLayout(1,2));
		sousContenant.add(panelFichier);
		sousContenant.add(panelRien);

		contenant.add(labelTitre, BorderLayout.NORTH);
		contenant.add(sousContenant, BorderLayout.CENTER);

		this.dataFenetre.addJPanel(contenant);
	}

    private void newMenuModifieGrille(){
    	JButton btnTerminer = new JButton("Terminer");
		JButton btnSauvegarder = new JButton("Sauvegarder");
		JButton btnAcceuil = new JButton("Acceuil");
		btnTerminer.addActionListener(new BoutonSimple(BoutonSimple.TERMINER, this.dataFenetre));
		btnSauvegarder.addActionListener(new BoutonSimple(BoutonSimple.SAUVEGARDER, this.dataFenetre));
		btnAcceuil.addActionListener(new BoutonSimple(BoutonSimple.ACCEUIL, this.dataFenetre));
		btnTerminer.setPreferredSize(new Dimension(150, 50));
		btnSauvegarder.setPreferredSize(new Dimension(150, 50));
		btnAcceuil.setPreferredSize(new Dimension(150, 50));
		JPanel panelTerminer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelSauvegarder = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelAcceuil = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelTerminer.add(btnTerminer);
		panelSauvegarder.add(btnSauvegarder);
		panelAcceuil.add(btnAcceuil);
		JPanel conteneurBoutons = new JPanel(new GridLayout(3,1));
		conteneurBoutons.add(panelTerminer);
		conteneurBoutons.add(panelSauvegarder);
		conteneurBoutons.add(panelAcceuil);

		JTextArea consigne = createConsigne(150,"Modifier la grille en cliquant sur les cases et en deplaeant Thesee et la porte de sortie");
		JPanel conteneurConsigne = new JPanel(new FlowLayout(FlowLayout.CENTER));
		conteneurConsigne.add(consigne);

		this.menu = new JPanel(new BorderLayout());
		this.menu.setPreferredSize(new Dimension(200, 1));
		this.menu.add(conteneurConsigne, BorderLayout.NORTH);
		this.menu.add(conteneurBoutons, BorderLayout.CENTER);
    }

    private void newMenuChoixAlgo(){
    	this.nouveauMenu();
		JButton btnRetour = new JButton("Retour");
		JButton btnAcceuil = new JButton("Acceuil");
		btnRetour.setPreferredSize(new Dimension(150, 50));
		btnAcceuil.setPreferredSize(new Dimension(150, 50));
		btnRetour.addActionListener(new BoutonSimple(BoutonSimple.RETOUR_MODIFICATION, this.dataFenetre));
		btnAcceuil.addActionListener(new BoutonSimple(BoutonSimple.ACCEUIL, this.dataFenetre));
		JPanel conteneurBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		conteneurBoutons.setPreferredSize(new Dimension(200,150));
		conteneurBoutons.add(btnRetour);
		conteneurBoutons.add(btnAcceuil);

		JTextArea consigne1 = createConsigne(30,"Choisissez un algorithme qui feras sortir thesee du labyrinthe :");
		JRadioButton algo1 = new JRadioButton("Aleatoire",true);
		JRadioButton algo2 = new JRadioButton("En profondeur",false);
		algo1.setActionCommand("Aleatoire");
		algo2.setActionCommand("En profondeur");
		algo1.setPreferredSize(new Dimension(150, 30));
		algo2.setPreferredSize(new Dimension(150, 30));
		ButtonGroup groupe1 = new ButtonGroup();
		groupe1.add(algo1);
		groupe1.add(algo2);
		JPanel conteneurConsigne1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		conteneurConsigne1.add(consigne1);
		conteneurConsigne1.add(algo1);
		conteneurConsigne1.add(algo2);

		JTextArea consigne2 = createConsigne(30,"Choisissez un mode de visualisation de l'algorithme :");
		JRadioButton visualisation1 = new JRadioButton("Manuel",true);
		JRadioButton visualisation2 = new JRadioButton("Automatique",false);
		visualisation1.setActionCommand("Manuel");
		visualisation2.setActionCommand("Automatique");
		visualisation1.setPreferredSize(new Dimension(150, 30));
		visualisation2.setPreferredSize(new Dimension(150, 30));
		ButtonGroup groupe2 = new ButtonGroup();
		groupe2.add(visualisation1);
		groupe2.add(visualisation2);
		JPanel conteneurConsigne2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		conteneurConsigne2.add(consigne2);
		conteneurConsigne2.add(visualisation1);
		conteneurConsigne2.add(visualisation2);

		JButton btnValider = new JButton("Valider");
		btnValider.setPreferredSize(new Dimension(150, 50));
		btnValider.addActionListener(new BoutonGroupe(BoutonGroupe.VALIDER_ALGO, groupe1, groupe2, this.dataFenetre));
		JPanel conteneurConsigne3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		conteneurConsigne3.add(btnValider);

		JPanel conteneurConsigne = new JPanel(new GridLayout(3,1));
		conteneurConsigne.add(conteneurConsigne1);
		conteneurConsigne.add(conteneurConsigne2);
		conteneurConsigne.add(conteneurConsigne3);

		this.menu = new JPanel(new BorderLayout());
		this.menu.setPreferredSize(new Dimension(200, 1));
		this.menu.add(conteneurConsigne, BorderLayout.CENTER);
		this.menu.add(conteneurBoutons, BorderLayout.SOUTH);
		this.addMenu();
    }

    private void resultat(String algo, int valeur){
    	JPanel contenant = this.dataFenetre.nouvelleInterface();
		contenant.setLayout(new BorderLayout());

		JLabel labelTitre = new JLabel("Resultat", JLabel.CENTER);
		JLabel labelIntro = new JLabel("");
		JLabel labelResultat = new JLabel("");
		if (valeur < 0){
			labelIntro.setText("Pas de solution !");
			labelResultat.setText("Thesee ne peut pas sortir du labyrinthe");
		}
		else{
			if (algo == "Aleatoire"){
				labelIntro.setText("Moyenne de deplacement de thesee pour sortir du labyrinthe a partir de");
				labelResultat.setText("100 algorithme Aleatoire : "+valeur);
			}
			if (algo == "En profondeur"){
				labelIntro.setText("Nombre deplacement de thesee pour sortir du labyrinthe a partir de");
				labelResultat.setText("L'algorithme en profondeur : "+valeur);
			}
		}
		Font fontTitre = new  Font("Arial", Font.BOLD, 50);
		labelTitre.setFont(fontTitre);

		JButton buttonAcceuil = new JButton("Acceuil");
		JButton buttonRetour = new JButton("Retour");
		buttonAcceuil.addActionListener(new BoutonSimple(BoutonSimple.ACCEUIL, this.dataFenetre));
		buttonRetour.addActionListener(new BoutonSimple(BoutonSimple.RETOUR_CHOIX_ALGO, this.dataFenetre));

		JPanel souContenantTexte = new JPanel(new FlowLayout(FlowLayout.LEFT));
		souContenantTexte.setPreferredSize(new Dimension(500,100));
		souContenantTexte.add(labelIntro);
		souContenantTexte.add(labelResultat);
		JPanel contenantTexte = new JPanel(new FlowLayout());
		contenantTexte.add(souContenantTexte);

		JPanel contenantBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contenantBoutons.add(buttonRetour);
		contenantBoutons.add(buttonAcceuil);

		JPanel sousContenant = new JPanel(new GridLayout(3,1));
		sousContenant.add(new JPanel());
		sousContenant.add(contenantTexte);
		sousContenant.add(contenantBoutons);

		contenant.add(labelTitre, BorderLayout.NORTH);
		contenant.add(sousContenant, BorderLayout.CENTER);

		this.dataFenetre.addJPanel(contenant);
    }

    private JLabel newMenuDeplacement(String choixAlgorithme, int nbDeplacement, ParcoursLabyrinthe algo){
    	this.nouveauMenu();
    	JButton btnRetour = new JButton("Retour");
		JButton btnAcceuil = new JButton("Acceuil");
		btnAcceuil.addActionListener(new BoutonSimple(BoutonSimple.ACCEUIL, this.dataFenetre));
		btnRetour.setPreferredSize(new Dimension(150, 50));
		btnAcceuil.setPreferredSize(new Dimension(150, 50));
		JPanel panelRetour = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelAcceuil = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelRetour.add(btnRetour);
		panelAcceuil.add(btnAcceuil);
		JPanel conteneurBoutons = new JPanel(new GridLayout(2,1));
		conteneurBoutons.add(panelRetour);
		conteneurBoutons.add(panelAcceuil);

		JTextArea consigne = createConsigne(100,"Appuyez sur les fleches suivant et precedent pour visualiser les differents deplacements de Thesee dans le labyrinthe");
		JLabel labelNumDeplacement = new JLabel();
		labelNumDeplacement.setPreferredSize(new Dimension(200,50));
		JPanel conteneurConsigne = new JPanel(new FlowLayout(FlowLayout.CENTER));
		conteneurConsigne.setPreferredSize(new Dimension(200,150));
		conteneurConsigne.add(consigne);
		conteneurConsigne.add(labelNumDeplacement);

		this.menu = new JPanel(new BorderLayout());
		this.menu.setPreferredSize(new Dimension(200, 1));
		this.menu.add(conteneurConsigne, BorderLayout.NORTH);
		this.menu.add(conteneurBoutons, BorderLayout.CENTER);
		this.addMenu();

		DeplacerThesee controleurDeplacement = new DeplacerThesee(labelNumDeplacement, this.dataFenetre.getDataGrille(), algo, choixAlgorithme, nbDeplacement);
		labelNumDeplacement.requestFocusInWindow();
		labelNumDeplacement.addKeyListener(controleurDeplacement);
		btnRetour.addActionListener(new BoutonAlgo(BoutonAlgo.RETOUR_CHOIX_ALGO, this.dataFenetre, controleurDeplacement));

		return labelNumDeplacement;
    }
}