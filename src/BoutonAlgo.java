import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoutonAlgo implements ActionListener{
	private int idBouton;
	private DataFenetre dataFenetre;
	private DeplacerThesee controleurDeplacement;
	public static final int RETOUR_CHOIX_ALGO = 8;

	public BoutonAlgo(int idBouton, DataFenetre dataFenetre, DeplacerThesee controleurDeplacement){
		this.idBouton = idBouton;
		this.controleurDeplacement = controleurDeplacement;
		this.dataFenetre = dataFenetre;
	}

	@Override
    public void actionPerformed(ActionEvent evenement){
    	if (this.idBouton == BoutonAlgo.RETOUR_CHOIX_ALGO){
    		if (this.controleurDeplacement != null){
    			this.reinitialiseGrille();
    		}
    		this.dataFenetre.getDisposition().changeMenu(ModifieFenetre.RETOUR_MENU_CHOIX_ALGO);
    	}
    }

    /*methode qui est appellee pour reinitiliser l'affichage de la grille de jeu en fonction du mouvement de Thesee. */
    private void reinitialiseGrille(){
    	DataGrille grille = this.controleurDeplacement.getDataGrille();
    	Pile parcoursThesee = this.controleurDeplacement.getAlgo().getParcours();
    	int numDeplacement = this.controleurDeplacement.getNumDeplacement();
    	int nbDeplacementMax = this.controleurDeplacement.getNbDeplacementMax();
    	Point debut = parcoursThesee.read(0);
    	Point caseFleche = parcoursThesee.read(numDeplacement+1);
        Point caseThesee = parcoursThesee.read(numDeplacement);

        if (numDeplacement == nbDeplacementMax){
        	grille.changeImage(AfficheCase.ARRIVEE, caseThesee);
        }
        else if (numDeplacement == nbDeplacementMax-1){
        	grille.changeImage(AfficheCase.ARRIVEE, caseFleche);
        	grille.changeImage(AfficheCase.AUCUN, caseThesee);
        }
        else{
        	grille.changeImage(AfficheCase.AUCUN, caseFleche);
        	grille.changeImage(AfficheCase.AUCUN, caseThesee);
        }
        grille.changeImage(AfficheCase.DEPART, debut);
    }
}
