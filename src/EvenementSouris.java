import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*Classe EvenementSouris qui implemente les interfaces MouseListener et MouseMotionListener. */
public class EvenementSouris implements MouseListener, MouseMotionListener{
    private DataFenetre dataFenetre;
    private DataCase debutMouvement;
    private boolean isDragged;
/*Constructeur EvenementSouris : il initialise les attributs de la classe. */
    public EvenementSouris(DataFenetre dataFenetre){
    	this.dataFenetre = dataFenetre;
        this.debutMouvement = null;
        this.isDragged = false;
    }
/*Methode getDataCase : elle permet de recuperer l'objet DataCase associe a un JPanel.  */
    private DataCase getDataCase(JPanel panneau){
        DataCase[][] listeCase = this.dataFenetre.getDataGrille().getCase();
        int taille = this.dataFenetre.getDataGrille().getSize();
        for (int y=0; y<taille; y++){
            for (int x=0; x<taille; x++){
                if (listeCase[x][y].getPanel() == panneau){
                    if (listeCase[x][y].getModificationActive() == true){
                        return listeCase[x][y];
                    }
                    else{
                        return null;
                    }
                }
            }
        }
        return null;
    }
/*Methode mouseClicked : elle gere l'evenement de clic sur un JPanel et modifie la couleur de la case associee. */
    @Override
    public void mouseClicked(MouseEvent e){
        DataCase caseCliquee = this.getDataCase((JPanel) e.getSource());
        if (caseCliquee != null){
            caseCliquee.couleurSuivante();
        }
    }
/*Methode mouseEntered : elle gere l'evenement de survol d'un JPanel et change l'image de la case si elle est vide. */
    @Override
    public void mouseEntered(MouseEvent e){
        if (this.isDragged == true){
            DataCase caseEntered = this.getDataCase((JPanel) e.getSource());
            if (caseEntered != null && caseEntered.getCouleurInt() == AfficheCase.BLANC && caseEntered.getImageInt() == AfficheCase.AUCUN){
                int imageInt = this.debutMouvement.getImageInt();
                this.debutMouvement.changeImage(AfficheCase.AUCUN);
                this.debutMouvement = caseEntered;
                this.debutMouvement.changeImage(imageInt);
            }
        }
    }
    @Override
    public void mouseExited(MouseEvent e){
    }
/*Methode mousePressed : elle gere l'evenement de pression de la souris sur un JPanel et recupere l'objet DataCase associe. */
    @Override
    public void mousePressed(MouseEvent e){
        this.debutMouvement = this.getDataCase((JPanel) e.getSource());
    }
/*Methode mouseReleased : elle gere l'evenement de relachement de la souris et reinitialise les attributs de l'objet. */
    @Override
    public void mouseReleased(MouseEvent e){
        this.debutMouvement = null;
        this.isDragged = false;
    }
    @Override
    public void mouseDragged(MouseEvent e){
        this.isDragged = true;
    }
    @Override
    public void mouseMoved(MouseEvent e){
    }
}
