import javax.swing.*;
import java.awt.*;
/*Classe qui represente une case dans le labyrinthe */
public class DataCase{
   private int couleurInt;
   private int imageInt;
   private JPanel panneau;
   private AfficheCase contenuPanneau;
   private boolean modificationActive;

    public DataCase(int couleurInt, int imageInt, boolean modificationActive){
	   this.couleurInt = couleurInt;
	   this.imageInt = imageInt;
      this.modificationActive = modificationActive;
      this.contenuPanneau = new AfficheCase(this.couleurInt, this.imageInt);
      this.panneau = new JPanel(new BorderLayout());
   	this.panneau.add(this.contenuPanneau);
    }
/*La methode getPanel renvoie le panneau de la case. */
   public JPanel getPanel(){
	   return this.panneau;
   }
/*methodes getImageInt et getCouleurInt renvoient l'index de l'image et de la couleur de la case. */
   public int getImageInt(){
      return this.imageInt;
   }

   public int getCouleurInt(){
      return this.couleurInt;
   }

/*La methode getModificationActive renvoie l'etat de modification. */
   
   public boolean getModificationActive(){
      return this.modificationActive;
   }
/*La methode couleurSuivante fait basculer la couleur de la case entre blanc et noir si l'image est vide. */
   public void couleurSuivante(){
    	if (this.imageInt == AfficheCase.AUCUN){
    	   this.couleurInt = (this.couleurInt+1)%2;
    	   this.updateCase();
    	}
   }
/*La methode changeModificationActive fait changer l'etat du drapeau de modification. */
   public void changeModificationActive(boolean modificationActive){
      this.modificationActive = modificationActive;
   }
/*La methode changeImag permet de changer l'image de la case */
   public void changeImage(int imageInt){
      this.imageInt = imageInt;
      this.updateCase();
   }
/* la methode updateCase() met a jour l'affichage de la case */
    private void updateCase(){
	   this.contenuPanneau.changeDessin(this.couleurInt, this.imageInt);
    } 
}
