//ce morceau de programme contient et affiche la fenetre graphique

import javax.swing.*;
import java.awt.*;

public class DataFenetre{
    private JFrame fenetre;
    private DataGrille dataGrille;
    private EvenementSouris interaction;
    private JPanel conteneur;
    private ModifieFenetre disposition;
/*Constructeur DataFenetre() :  il initialise les elements de la fenetre. */
    public DataFenetre(){
    	this.fenetre = new JFrame("SAE21_2022");
    	this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.fenetre.setSize(800, 600);
    	this.fenetre.setLocation(0, 0);
    	this.conteneur = new JPanel();
    	this.fenetre.add(this.conteneur);
    	this.disposition = new ModifieFenetre(this);
    	this.disposition.changeMenu(ModifieFenetre.MENU_ACCEUIL);
    }
/* Cette methode permet d'ajouter un JPanel a la fenetre. */
    public void addJPanel(JPanel conteneur){
    	this.conteneur = conteneur;
    	this.fenetre.add(conteneur);
    	this.fenetre.revalidate();
    	this.fenetre.repaint();
    }
/* Cette methode cree un nouveau JPanel et le retourne. */
    public JPanel nouvelleInterface(){
    	this.fenetre.remove(this.conteneur);
    	this.conteneur = new JPanel();
    	return this.conteneur;
    }
/*Cette methode permet d'ajouter une grille de jeu a la fenetre et d'initialiser les evenements de souris pour chaque element de la grille. */
    public void addDataGrille(DataGrille dataGrille){
    	this.dataGrille = dataGrille;
    	this.interaction = new EvenementSouris(this);
    	int taille = this.dataGrille.getSize();
		JPanel[][] listePanel = this.dataGrille.getPanel();
	    for (int y=0; y<taille; y++){
	    	for (int x=0; x<taille; x++){
	    		listePanel[x][y].addMouseListener(this.interaction);
	    		listePanel[x][y].addMouseMotionListener(this.interaction);
		   	}
	    }
	    this.disposition.changeMenu(ModifieFenetre.NOUVELLE_GRILLE);
	}
/*Cette methode permet d'afficher la fenetre */
    public void rendreVisible(){
       	this.fenetre.setVisible(true);
    }

    public DataGrille getDataGrille(){
    	return this.dataGrille;
    }

    public JFrame getJFrame(){
    	return this.fenetre;
    }

    public JPanel getConteneur(){
    	return this.conteneur;
    }

    public ModifieFenetre getDisposition(){
        return this.disposition;
    }
}
