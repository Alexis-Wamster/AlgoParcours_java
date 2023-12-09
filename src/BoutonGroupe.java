import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoutonGroupe implements ActionListener{
	private int idBouton;
	private DataFenetre dataFenetre;
	private ButtonGroup groupeAlgorithme;
	private ButtonGroup groupeVisualisation;
	public static final int VALIDER_ALGO = 6;

	public BoutonGroupe(int idBouton, ButtonGroup  groupeAlgorithme, ButtonGroup groupeVisualisation, DataFenetre dataFenetre){
		this.idBouton = idBouton;
		this.dataFenetre = dataFenetre;
		this.groupeAlgorithme = groupeAlgorithme;
		this.groupeVisualisation = groupeVisualisation;
	}

	@Override
    public void actionPerformed(ActionEvent evenement){
    	if (this.idBouton == BoutonGroupe.VALIDER_ALGO){
    		String choixAlgo = this.groupeAlgorithme.getSelection().getActionCommand();
    		String choixVisuel = this.groupeVisualisation.getSelection().getActionCommand();
    		this.dataFenetre.getDisposition().changeMenu(ModifieFenetre.MENU_RESULTAT,choixAlgo,choixVisuel);
    	}
    }
}