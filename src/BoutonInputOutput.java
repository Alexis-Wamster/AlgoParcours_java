import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoutonInputOutput implements ActionListener{
	private int idBouton;
	private JLabel[] listeOutput;
	private DataFenetre dataFenetre;
	private JTextField input;
	public static final int ALEATOIRE = 2;
	public static final int VIDE = 0;

	public BoutonInputOutput(JTextField input, JLabel[] listeOutput, int idBouton, DataFenetre dataFenetre){
		this.idBouton = idBouton;
		this.input = input;
		this.listeOutput = listeOutput;
		this.dataFenetre = dataFenetre;
	}

	@Override
    public void actionPerformed(ActionEvent evenement){
    	for (JLabel output: this.listeOutput){
    		output.setText("");
    	}
    	String donnees = this.input.getText();
    	if (donnees.equals("")){
    		this.listeOutput[idBouton%2].setText("*Remplissez ce champs");
    	}
    	else{
    		try{
    			int taille = Integer.parseInt(donnees);
    			this.input.setText("");
    			if (taille > 255 || taille < 2){
    				this.listeOutput[idBouton%2].setText("*Choisissez une taille entre 2 et 255");
    			}
    			else{
    				this.input.setText("");
    				DataGrille nouvelleGrille=null;
    				if (this.idBouton == BoutonInputOutput.ALEATOIRE){
    					nouvelleGrille = new DataGrille(DataGrille.ALGO_ALEATOIRE,taille);
    				}
    				if (this.idBouton == BoutonInputOutput.VIDE){
    					nouvelleGrille = new DataGrille(DataGrille.ALGO_VIDE,taille);
    				}
    				this.dataFenetre.addDataGrille(nouvelleGrille);
    			}
    		}
    		catch(NumberFormatException e){
    			this.listeOutput[idBouton%2].setText("*La taille doit etre un entier");
    		}
    	}
    }
}