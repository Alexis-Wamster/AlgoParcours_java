import java.util.Random;
/*Classe point */
/*Constructeur Point : il cree un objet Point avec des coordonnees x et y donnees en entree. */
public class Point{
	public int x;
	public int y;
	public Point(int x,int y){
		this.x = x;
		this.y = y;
	}
/*Constructeur Point : il cree un objet Point avec des coordonnees aleatoires entre 0 et la taille.  */
	public Point(Random random, int taille){
		this.x = random.nextInt(taille);
		this.y = random.nextInt(taille);
	}
/*Methode addVecteur : elle retourne un nouvel objet Point avec les coordonnees du point additionnee a celle d'un vecteur. */
	public Point addVecteur(Point vecteur) {
  		return new Point(this.x+vecteur.x, this.y+vecteur.y);
  	}
/*Methode subtractVecteur : elle retourne un nouvel objet Point avec les coordonnees du point soustraites a celle d'un vecteur .*/
  	public Point subtractVecteur(Point vecteur) {
  		return new Point(this.x-vecteur.x, this.y-vecteur.y);
  	}
/*Methode findInTab : elle retourne l'indice du premier objet Point dans un tableau qui correspond au point courant, retourne -1 s'il n'existe pas. */
  	public int findInTab(Point[] tab){
  		for (int i=0 ; i<tab.length; i++){
  			if (this.equals(tab[i])){
  				return i;
  			}
  		}
  		return -1;
  	}
/*Methode isIn : elle retourne vrai si le point courant est a l'interieur du rectangle defini par les deux autres points, retourne faux sinon. */
  	public boolean isIn(Point limiteMin, Point limiteMax){
  		if (this.x < limiteMin.x || this.x > limiteMax.x || this.y < limiteMin.y || this.y > limiteMax.y){
			return false;
		}
		return true;
  	}
/*Methode equals : verifie si l'objet Point donne est egal au point courant, en comparant les coordonnees x et y. */
	@Override
  	public boolean equals(Object obj) {
  		Point point2 = (Point) obj;
  		if (this.x == point2.x && this.y == point2.y){
  			return true;
  		}
  		else{
  			return false;
  		}
  	}
}