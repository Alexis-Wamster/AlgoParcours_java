import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoutonSimple implements ActionListener{
	private int idBouton;
	private DataFenetre dataFenetre;
	public static final int TERMINER = 4;
	public static final int ACCEUIL = 5;
	public static final int RETOUR_MODIFICATION = 7;
	public static final int SAUVEGARDER = 3;
	public static final int RETOUR_CHOIX_ALGO = 8;

	public BoutonSimple(int idBouton, DataFenetre dataFenetre){
		this.idBouton = idBouton;
		this.dataFenetre = dataFenetre;
	}

	@Override
    public void actionPerformed(ActionEvent evenement){
    	if (this.idBouton == BoutonSimple.TERMINER){
    		this.dataFenetre.getDataGrille().changeModificationActive(false);
    		this.dataFenetre.getDisposition().changeMenu(ModifieFenetre.MENU_CHOIX_ALGO);
    	}
    	if (this.idBouton == BoutonSimple.ACCEUIL){
    		this.dataFenetre.getDisposition().changeMenu(ModifieFenetre.MENU_ACCEUIL);
    	}
    	if (this.idBouton == BoutonSimple.RETOUR_MODIFICATION){
    		this.dataFenetre.getDataGrille().changeModificationActive(true);
    		this.dataFenetre.getDisposition().changeMenu(ModifieFenetre.MENU_MODIFIE_GRILLE);
    	}
    	if (this.idBouton == BoutonSimple.SAUVEGARDER){
    		int [][] grille = this.dataFenetre.getDataGrille().getTableauInt();
    		Point thesee = this.dataFenetre.getDataGrille().getPositionThesee();
    		Point sortie = this.dataFenetre.getDataGrille().getPositionSortie();
    		grille[thesee.x][thesee.y] = AfficheCase.BLANC;
    		grille[sortie.x][sortie.y] = AfficheCase.BLANC;
    		EcritureFichier.ecrireFichier(grille, thesee, sortie);
    	}
    	if (this.idBouton == BoutonSimple.RETOUR_CHOIX_ALGO){
    		this.dataFenetre.getDisposition().changeMenu(ModifieFenetre.RETOUR_MENU_CHOIX_ALGO);
    	}
    }
}