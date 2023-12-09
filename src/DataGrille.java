import javax.swing.*;
import java.awt.*;
import java.util.Random;
/*Classe DataGrille : elle definit une grille de cases qui peuvent etre utilisees pour representer une carte ou un labyrinthe. */
public class DataGrille{
    private DataCase[][] listeCase;
    public static final int ALGO_VIDE = 0;
    public static final int ALGO_ALEATOIRE = 1;
    /*Constructeur qui initialise la grille .  */
    public DataGrille(int numAlgo,int taille){
    	if (numAlgo == DataGrille.ALGO_VIDE){
    		this.algoVide(taille);
    	}
    	else{
    		this.algoAleatoire(taille);
    	}
    }
/* Constructeur DatgGrille il initialise la grille a partir d'un tableau d'entiers donne en parametre. Les points de depart et d'arrivee sont egalement fournis en parametre. */
    public DataGrille(int[][] grille, Point depart, Point arrivee){
    	int taille = grille.length;
    	this.listeCase = new DataCase[taille][taille];
    	for (int y=0; y<taille; y++){
            for (int x=0; x<taille; x++){
            	if ((x==depart.x && y==depart.y)){
				    this.listeCase[x][y] = new DataCase(grille[x][y], AfficheCase.DEPART, true);
				}
				else if (x==arrivee.x && y==arrivee.y){
				    this.listeCase[x][y] = new DataCase(grille[x][y], AfficheCase.ARRIVEE, true);
				}
				else{
				    this.listeCase[x][y] = new DataCase(grille[x][y], AfficheCase.AUCUN, true);
				}
            }
        }
    }
/*Methode changeModificationActive : elle permet de changer l'etat de modification de chaque case de la grille en fonction du parametre booleen fourni. */
    public void changeModificationActive(boolean modificationActive){
	    int taille = this.listeCase.length;
	    for (int y=0; y<taille; y++){
	    	for (int x=0; x<taille; x++){
	    		this.listeCase[x][y].changeModificationActive(modificationActive);
	    	}
	    }
	}
/*Methode changeImage : elle permet de changer l'image d'une case a la position donnee. */
	public void changeImage(int imageInt, Point position){
		this.listeCase[position.x][position.y].changeImage(imageInt);
	}
/*Methodes privee algoVide : elle  remplit la grille avec des cases blanches . */ 
    private void algoVide(int taille){
		this.listeCase = new DataCase[taille][taille];
		for (int y=0; y<taille; y++){
            for (int x=0; x<taille; x++){
				if ((x==0 && y==0)){
				    this.listeCase[x][y] = new DataCase(AfficheCase.BLANC, AfficheCase.DEPART, true);
				}
				else if ((x==taille-1 && y==taille-1)){
				    this.listeCase[x][y] = new DataCase(AfficheCase.BLANC, AfficheCase.ARRIVEE, true);
				}
				else{
				    this.listeCase[x][y] = new DataCase(AfficheCase.BLANC, AfficheCase.AUCUN, true);
				}
	   		}
		}
    }
/*Methode privee algoAleatoire : elle cree une grille avec des cases blanches et noires, avec un point de depart et d'arrivee choisi au hasard. */ 
    private void algoAleatoire(int taille){
    	this.listeCase = new DataCase[taille][taille];
    	Random random = new Random();
    	Point depart = new Point(random,taille);
    	Point arrivee = new Point(random,taille);
    	while (depart.x==arrivee.x && depart.y==arrivee.y){
    		arrivee = new Point(random,taille);
    	}
    	for (int y=0; y<taille; y++){
            for (int x=0; x<taille; x++){
				if (x==depart.x && y==depart.y){
				    this.listeCase[x][y] = new DataCase(AfficheCase.BLANC, AfficheCase.DEPART, true);
				}
				else if (x==arrivee.x && y==arrivee.y){
				    this.listeCase[x][y] = new DataCase(AfficheCase.BLANC, AfficheCase.ARRIVEE, true);
				}
				else{
					int proba = random.nextInt(3);
					if (proba == 1){
						this.listeCase[x][y] = new DataCase(AfficheCase.NOIR, AfficheCase.AUCUN, true);
					}
					else{
						this.listeCase[x][y] = new DataCase(AfficheCase.BLANC, AfficheCase.AUCUN, true);
					}
				}
	   		}
		}
    }
/*Methode getPanel : elle renvoie un tableau de JPanel qui represente chaque case de la grille. */
    public JPanel[][] getPanel(){
		int taille = this.listeCase.length;
		JPanel[][] resultat = new JPanel[taille][taille];
		for (int y=0; y<taille; y++){
	        for (int x=0; x<taille; x++){
				resultat[x][y] = this.listeCase[x][y].getPanel();
		    }
		}
		return resultat;
    }
/*Methode getTableauInt : elle renvoie un tableau d'entiers qui represente chaque case de la grille. Les valeurs possibles sont DEPART, ARRIVEE et CASE_VIDE. */
    public int[][] getTableauInt(){
		int taille = this.listeCase.length;
		int[][] resultat = new int[taille][taille];
		for (int y=0; y<taille; y++){
	        for (int x=0; x<taille; x++){
	        	if (this.listeCase[x][y].getImageInt() == AfficheCase.DEPART){
	        		resultat[x][y] = ParcoursLabyrinthe.DEPART;
	        	}
	        	else if (this.listeCase[x][y].getImageInt() == AfficheCase.ARRIVEE){
	        		resultat[x][y] = ParcoursLabyrinthe.ARRIVEE;
	        	}
	        	else{
	        		resultat[x][y] = this.listeCase[x][y].getCouleurInt();
	        	}
		    }
		}
		return resultat;
    }

    public Point getPositionThesee(){
		int taille = this.listeCase.length;
		for (int y=0; y<taille; y++){
	        for (int x=0; x<taille; x++){
	        	if (this.listeCase[x][y].getImageInt() == AfficheCase.DEPART){
					return new Point(x,y);
	        	}
		    }
		}
		return null;
    }

    public Point getPositionSortie(){
		int taille = this.listeCase.length;
		for (int y=0; y<taille; y++){
	        for (int x=0; x<taille; x++){
	        	if (this.listeCase[x][y].getImageInt() == AfficheCase.ARRIVEE){
					return new Point(x,y);
	        	}
		    }
		}
		return null;
    }

    public int getSize(){
    	return this.listeCase.length;
    }

    public DataCase[][] getCase(){
    	return this.listeCase;
    }
}
