import javax.swing.JComponent;
import java.awt.*;
/*Classe affichecase qui permet de creer une case pour representer le labyrinthe et ses differents elments */
public class AfficheCase extends JComponent {
    private Image element;
    private Color couleur;
    private static final Color[] LISTE_COULEUR = {Color.WHITE, Color.BLACK, new Color(150,150,150)};
    private static final String[] LISTE_IMAGE = {"image/thesee.png", "image/sortie.png", "image/nord.png", "image/ouest.png", "image/sud.png", "image/est.png"};
    public static final int DEPART = 0;
    public static final int ARRIVEE = 1;
    public static final int NORD = 2;
    public static final int OUEST = 3;
    public static final int SUD = 4;
    public static final int EST = 5;
    public static final int BLANC = 0;
    public static final int NOIR = 1;
    public static final int GRIS = 2;
    public static final int AUCUN = -1;
	/*Constructeur permettant de definir les couleurss des cases ,les images servant a faire deplacee thesee et les positions*/
    public AfficheCase(int couleurInt, int elementInt){
		if (couleurInt >= 0 && couleurInt < AfficheCase.LISTE_COULEUR.length){
		    this.couleur = AfficheCase.LISTE_COULEUR[couleurInt];
		}
		else{
		    this.couleur = null;
		}
		if (elementInt >= 0 && elementInt < AfficheCase.LISTE_IMAGE.length){
		    this.element = Toolkit.getDefaultToolkit().getImage(AfficheCase.LISTE_IMAGE[elementInt]);
		}
		else{
		    this.element = null;
		}
    }
/*methode changerDessin permet de changer la couleur et l'image des cases */
    public void changeDessin(int couleurInt, int elementInt){
	    if (couleurInt >= 0 && couleurInt < AfficheCase.LISTE_COULEUR.length){
		    this.couleur = AfficheCase.LISTE_COULEUR[couleurInt];
		}
		else{
		    this.couleur = null;
		}
		if (elementInt >= 0 && elementInt < AfficheCase.LISTE_IMAGE.length){
		    this.element = Toolkit.getDefaultToolkit().getImage(AfficheCase.LISTE_IMAGE[elementInt]);
		}
		else{
		    this.element = null;
		}
		this.repaint();
    }
    /*methode appelee automatiquement chaque fois que une case doit etre dessinee */
    @Override
    protected void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		if (this.couleur != null){
		    secondPinceau.setColor(this.couleur);
		    secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		    secondPinceau.setColor(new Color(122,122,122));
		    secondPinceau.drawRect(0, 0, this.getWidth(), this.getHeight());
		}
		if (this.element != null){
			secondPinceau.drawImage(element,0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
