import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoutonOutput implements ActionListener{
	private int idBouton;
	private JLabel[] listeOutput;
	private DataFenetre dataFenetre;
	public static final int FICHIER = 1;

	public BoutonOutput(JLabel[] listeOutput, int idBouton, DataFenetre dataFenetre){
		this.idBouton = idBouton;
		this.listeOutput = listeOutput;
		this.dataFenetre = dataFenetre;
	}

	@Override
    public void actionPerformed(ActionEvent evenement){
    	for (JLabel output: this.listeOutput){
    		output.setText("");
    	}
    	if (this.idBouton == BoutonOutput.FICHIER){
    		String erreur = LectureFichier.lireFichier();
    		int [][] grille = LectureFichier.getGrille();
    		Point thesee = LectureFichier.getThesee();
    		Point sortie = LectureFichier.getSortie();
    		this.listeOutput[idBouton%2].setText(erreur);
    		if (LectureFichier.isAvalaible()){
    			DataGrille nouvelleGrille = new DataGrille(grille, thesee, sortie);
    			this.dataFenetre.addDataGrille(nouvelleGrille);
    		}
    	}
    }
}