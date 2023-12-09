import java.util.Random;
/* classe resout le labyrinthe un labyrinthe en utilisant deux methodes de parcours : aleatoire et en profondeur. */
public class ParcoursLabyrinthe{
	private DataGrille dataGrille;
	private int[][] grille;
	private Point positionThesee;
	private Pile parcoursThesee;
	public static final Point[] POINT_CARDINAUX = {new Point(0,-1), new Point(-1,0), new Point(0,1), new Point(1,0)};
	public static final int ARRIVEE = 4;
	public static final int DEPART = 3;

	public ParcoursLabyrinthe(DataGrille dataGrille){
		this.dataGrille = dataGrille;
	}

/*Methode parcoursAleatoire : elle reinitialise le parcours en appelant la methode "resetParcours", puis boucle jusqu'a ce que Thesee atteigne la sortie */
	public int parcoursAleatoire(boolean saveHistorique){
		this.resetParcours();
		int nbDeplacement = 0;
		while (avancerAleatoirement(saveHistorique)==false){
			nbDeplacement++;
		}
		return nbDeplacement;
	}
/*Methode parcoursProfondeur : elle renvoie le nombre de deplacements necessaires pour que Thesee atteigne la sortie du labyrinthe en un parcours en profondeur. 
Elle reinitialise le parcours en appelant la methode resetParcours, puis boucle jusqu'a ce que Thesee atteigne la sortie en appelant la methode avancerOuReculer . */
	public int parcoursProfondeur(){
		this.resetParcours();
		int nbDeplacement=0;
		Pile repere = new Pile();
		repere.push(this.positionThesee);
		while (repere.empty()==false){
			if (this.grille[this.positionThesee.x][this.positionThesee.y] == ParcoursLabyrinthe.ARRIVEE){
				return nbDeplacement;
			}
			this.grille[this.positionThesee.x][this.positionThesee.y] = AfficheCase.GRIS;
			this.avancerOuReculer(repere);
			this.parcoursThesee.push(this.positionThesee);
			nbDeplacement++;
		}
		return -nbDeplacement;
	}
/*Methode resetParcours: elle reinitialise les attributs "grille", "positionThesee" et "parcoursThesee". */
	public void resetParcours(){
		this.grille = this.dataGrille.getTableauInt();
		this.positionThesee = this.dataGrille.getPositionThesee();
		this.parcoursThesee = new Pile();
		this.parcoursThesee.push(positionThesee);
	}

	public Pile getParcours(){
		return this.parcoursThesee;
	}
/*Methode avancerOuReculer : elle utilise une pile pour stocker les positions precedentes de Thesee, et utilise une boucle pour essayer les directions possibles dans l'ordre (nord, ouest, sud, est).  */
	private void avancerOuReculer(Pile parcours){
		boolean flagCheminTrouve = false;
		for (int i =0; i<4 && flagCheminTrouve==false; i++){
			Point positionSuivante = this.positionThesee.addVecteur(ParcoursLabyrinthe.POINT_CARDINAUX[i]);
			if (positionSuivante.isIn(new Point(0,0), new Point(grille.length-1,grille.length-1))){
				if (grille[positionSuivante.x][positionSuivante.y] == AfficheCase.BLANC || grille[positionSuivante.x][positionSuivante.y] == ParcoursLabyrinthe.ARRIVEE || grille[positionSuivante.x][positionSuivante.y] == ParcoursLabyrinthe.DEPART){
					flagCheminTrouve = true;
					this.positionThesee = positionSuivante;
					parcours.push(positionThesee);
				}
			}
		}
		if (flagCheminTrouve == false){
			 parcours.pop();
			 this.positionThesee = parcours.read(-1);
		}
	}
/*Methode avancerAleatoirement: elle utilise une boucle pour essayer les directions possibles dans un ordre aleatoire . */
	public boolean avancerAleatoirement(boolean saveHistorique){
		int[] listeDirection = {0,1,2,3};
		Random random = new Random();
		while (listeDirection.length > 0){
			int choixDirection = listeDirection[random.nextInt(listeDirection.length)];
			Point vecteurDeplacement = ParcoursLabyrinthe.POINT_CARDINAUX[choixDirection];
			Point positionSuivante = this.positionThesee.addVecteur(vecteurDeplacement);
			if (positionSuivante.isIn(new Point(0,0), new Point(grille.length-1,grille.length-1))){
				if (grille[positionSuivante.x][positionSuivante.y] == AfficheCase.BLANC || grille[positionSuivante.x][positionSuivante.y] == ParcoursLabyrinthe.DEPART){
					this.positionThesee = positionSuivante;
					if (saveHistorique){
						this.parcoursThesee.push(positionThesee);
					}
					return false; //Thesee a avance
				}
				if (grille[positionSuivante.x][positionSuivante.y] == ParcoursLabyrinthe.ARRIVEE){
					this.positionThesee = positionSuivante;
					if (saveHistorique){
						this.parcoursThesee.push(positionThesee);
					}
					return true;//Thesee a Gagne
				}
			}
			listeDirection = ParcoursLabyrinthe.supprimerValueur(listeDirection,choixDirection);
		}
		return true;//Thesee est entre 4 murs
	}
/*Methode supprimerValueur : elle a pour but de supprimer toutes les occurrences d'une valeur donnee dans une liste donnee */
	private static int[] supprimerValueur(int[] ancienTableau, int valeurASupprimer){
		int ancienneCapacite = ancienTableau.length;
		int[] nouveauTableau = new int[ancienneCapacite-1];
		for (int i1=0, i2=0; i2<ancienneCapacite; i2++){
			if (ancienTableau[i2] != valeurASupprimer){
				nouveauTableau[i1] = ancienTableau[i2];
				i1++;
			}
		}
		return nouveauTableau;
	}
}