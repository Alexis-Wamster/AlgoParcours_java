import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DeplacerThesee implements KeyListener{
	private JLabel labelNumDeplacement;
	private DataGrille dataGrille;
	private ParcoursLabyrinthe algo;
	private String choixAlgo;
	private int nbDeplacementMax;
	private int numDeplacement;

	public DeplacerThesee(JLabel labelNumDeplacement, DataGrille dataGrille, ParcoursLabyrinthe algo, String choixAlgo, int nbDeplacementMax){
		this.labelNumDeplacement = labelNumDeplacement;
		this.dataGrille = dataGrille;
		this.algo = algo;
		this.choixAlgo = choixAlgo;
		this.numDeplacement = -1;
		this.nbDeplacementMax = nbDeplacementMax;

		if (this.choixAlgo == "En profondeur"){
	    	if (this.nbDeplacementMax < 0){
	    		this.nbDeplacementMax = -1-nbDeplacementMax;
	    	}
	    }
	    if (this.choixAlgo == "Aleatoire"){
	    	if (this.nbDeplacementMax > 0){
	    		this.nbDeplacementMax = this.algo.parcoursAleatoire(true)+1;
	    	}
	    	else if (this.nbDeplacementMax == -1){
	    		this.nbDeplacementMax = 0;
	    	}
	    	else {
	    		this.algo.resetParcours();
	    		this.algo.avancerAleatoirement(true);
	    	}
	    }
	    this.theseeAvance();
	}
/*Methode changeNumDeplacemen : Elle met a jour le texte affiche sur le JLabel labelNumDeplacement. */
	

private void changeNumDeplacement(){
		if (this.numDeplacement >= 0 && this.numDeplacement <= this.nbDeplacementMax){
			this.labelNumDeplacement.setText("deplacement : "+this.numDeplacement+"/"+this.nbDeplacementMax);
		}
		else if (this.numDeplacement >= 0 && this.nbDeplacementMax < 0){
			this.labelNumDeplacement.setText("deplacement : "+this.numDeplacement+"/infini");
		}
	}
/* Methodes :  "keyTyped", "keyPressed" et "keyReleased" sont des methodes de l'interface KeyListener qui permettent de detecter les touches pressees sur le clavier.  */
	@Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int touche = e.getKeyCode();
        if (touche==37){
        	this.theseeRecule();
        }
        if (touche==39){
        	this.theseeAvance();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    	int touche = e.getKeyCode();
    }
/* Methode theseeAvance permet de faire avancer Thesee d'une case dans le labyrintheavec plusieurs conditions. Si Thesee a atteint la sortie, la methode s'arrete. Si Thesee est autorise a effectuer un nombre limite de deplacements, la methode s'arrete egalement une fois que le nombre maximum de deplacements a ete atteint.
 Si Thesee utilise l'algorithme "Aleatoire", la methode continue a avancer aleatoirement dans le labyrinthe jusqu'a atteindre la sortie. */
    
	private void theseeAvance(){
    	if (this.numDeplacement < this.nbDeplacementMax || this.nbDeplacementMax < 0){
    		if (this.numDeplacement >= 0){
        		Point caseBlanche = this.algo.getParcours().read(this.numDeplacement);
        		this.dataGrille.changeImage(AfficheCase.AUCUN, caseBlanche);
        	}
        	this.numDeplacement++;
        	this.changeNumDeplacement();
        	while (this.numDeplacement+1 >= this.algo.getParcours().getTaille() && this.choixAlgo == "Aleatoire"){
        		this.algo.avancerAleatoirement(true);
        	}
        	Point caseThesee = this.algo.getParcours().read(this.numDeplacement);
        	this.dataGrille.changeImage(AfficheCase.DEPART, caseThesee);
        	Point caseFleche = this.algo.getParcours().read(this.numDeplacement+1);
        	if (this.numDeplacement != this.nbDeplacementMax){
        		int directionFleche = directionFelche(caseFleche, caseThesee);
        		this.dataGrille.changeImage(directionFleche, caseFleche);
        	}
        }
    }
/*Methode 0theseeRecule permet de faire reculer Thesee d'une case dans le labyrinthe. */
    private void theseeRecule(){
    	if (this.numDeplacement > 0){
    		Point caseBlanche = this.algo.getParcours().read(this.numDeplacement+1);
        	Point caseFleche = this.algo.getParcours().read(this.numDeplacement);
        	Point caseThesee = this.algo.getParcours().read(this.numDeplacement-1);
        	this.numDeplacement--;
        	this.changeNumDeplacement();
        	if (caseBlanche != null){
        		if (this.numDeplacement == this.nbDeplacementMax-2){
        			this.dataGrille.changeImage(AfficheCase.ARRIVEE, caseBlanche);
        		}
        		else{
        			this.dataGrille.changeImage(AfficheCase.AUCUN, caseBlanche);
        		}
        	}
        	this.dataGrille.changeImage(AfficheCase.DEPART, caseThesee);
        	if (caseFleche != null){
        		int directionFleche = directionFelche(caseFleche, caseThesee);
        		this.dataGrille.changeImage(directionFleche, caseFleche);
        	}
        }
    }
/*Methode directionFelche calcule la direction a laquelle doit etre affichee la fleche qui indique la prochaine direction a prendre pour atteindre la sortie. */
    private int directionFelche(Point fleche, Point thesee){
    	if (fleche != null && thesee!=null){
    		Point difference = fleche.subtractVecteur(thesee);
    		int direction = difference.findInTab(ParcoursLabyrinthe.POINT_CARDINAUX);
    		if (direction != -1){
    			return direction+2;
    		}
    	}
    	return -1;
    }

    public DataGrille getDataGrille(){
    	return this.dataGrille;
    }

    public ParcoursLabyrinthe getAlgo(){
    	return this.algo;
    }

    public int getNumDeplacement(){
    	return this.numDeplacement;
    }

    public int getNbDeplacementMax(){
    	return this.nbDeplacementMax;
    }
}
