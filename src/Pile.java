public class Pile{
	private Point[] tableau;
	private int taille;
/*Points initialise a une taille de 64 et une taille de pile initialisee a 0. */
	public Pile(){
		this.tableau = new Point[64];
		this.taille = 0;
	}
/*Methode push : elle ajoute l'element Point specifie au sommet de la pile. */
	public void push(Point valeur){
		if (this.taille >= this.tableau.length){
			this.agrandirTableau(this.tableau.length);
		}
		this.tableau[this.taille] = valeur;
		this.taille++;
	}
/* Methode pop : elle supprime et renvoie l'element en haut de la pile. Si la pile est vide, renvoie null. */
	public Point pop(){
		if (this.empty()){
			return null;
		}
		this.taille--;
		return(this.tableau[this.taille]);
	}
/*Methode read : elle renvoie l'element de la pile situe a l'index specifie. Si l'index est en dehors de la plage valide des elements de la pile, il est ramene dans la plage valide en ajoutant ou en soustrayant la taille de la pile. */
	public Point read(int numElement){
		if (this.empty()){
			return null;
		}
		while (numElement < 0){
			numElement = this.taille+numElement;
		}
		while (numElement >= this.taille){
			numElement = numElement-this.taille;
		}
		return(this.tableau[numElement]);
	}
/*Methode empty : elle renvoie true si la pile est vide, false sinon */
	public boolean empty(){
		if (this.taille <= 0){
			return true;
		}
		return false;
	}
/* Methode getTaille : elle renvoie la taille actuelle de la pile.*/
	public int getTaille(){
		return this.taille;
	}
/*Methode agrandirTableau : elle agrandit le tableau de la pile en creant un nouveau tableau avec une taille augmentee
 en copiant les elements de l'ancien tableau dans le nouveau tableau et en remplaeant l'ancien tableau par le nouveau tableau. */
	private void agrandirTableau(int espaceEnPlus){
		int ancienneCapacite = this.tableau.length;
		int nouvelleCapacite = ancienneCapacite + espaceEnPlus;
		Point[] nouveauTableau = new Point[nouvelleCapacite];
		for (int i=0; i<ancienneCapacite; i++){
			nouveauTableau[i] = this.tableau[i];
		}
		this.tableau = nouveauTableau;
	}
}