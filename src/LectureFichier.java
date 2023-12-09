import java.io.*;
import javax.swing.*;
import java.util.Arrays;

/*Classe LectureFichier : elle permet de lire un fichier contenant les donnees du labyrinthe et de stocker ces donnees
 pour qu'elles soient utilisees par EcritureFichier*/

 public class LectureFichier {
    
    private static Point thesee;
    private static Point sortie;
    private static int[][] grille;
/*Methode lireFichier : elle permet de lire les donnees du fichier selectionne par l'utilisateur et de stocker ces donnees dans les variables de classe. */   
    public static String lireFichier() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
            // Lire la taille du tableau
            int taille = inputStream.readByte();
            grille = new int[taille][taille];

            // Lire les coordonnees de thesee et de sortie
            int theseeY = inputStream.readByte();
            int theseeX = inputStream.readByte();
            int sortieY = inputStream.readByte();
            int sortieX = inputStream.readByte();

            // Verifier que les coordonnees de thesee et de sortie sont valides
            if (theseeX >= taille || theseeY >= taille || sortieX >= taille || sortieY >= taille) {
                LectureFichier.erreur();
                return "Format de fichier incorrect : les coordonnees de thesee ou de sortie sont invalides.";
            }

            thesee = new Point(theseeX, theseeY);
            sortie = new Point(sortieX, sortieY);

            // Lire les donnees de la grille
            int bitsRemaining = 8;
            byte currentByte = 0;
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                     if (bitsRemaining == 8) {
                        currentByte = inputStream.readByte();
                        bitsRemaining = 0;
                    }
                    grille[i][j] = (currentByte & (1 << (7 - bitsRemaining))) == 0 ? 0 : 1;
                    bitsRemaining++;
                }
            }

            int remainingBits = inputStream.available();
            if (remainingBits >= 8) {
                LectureFichier.erreur();
                return "Format de fichier incorrect : il reste des bits non lus.";
            }

        } catch (FileNotFoundException e) {
            LectureFichier.erreur();
            return "Fichier non trouve !";
        } catch (IOException e) {
            LectureFichier.erreur();
            return "Erreur lors de la lecture du fichier !";
        } catch (NullPointerException e){
            LectureFichier.erreur();
            return "Choisissez un fichier";
        }
        return "";
    }
    /*Methode getThesee : elle permet d'obtenir les coordonnees de depart du personnage thesee. */
    public static Point getThesee() {
        return LectureFichier.thesee;
    }
    /*Methode getSortie : elle permet d'obtenir les coordonnees de la sortie du labyrinthe. */
    public static Point getSortie() {
        return LectureFichier.sortie;
    }
   /*Methode getGrille : elle permet d'obtenir la grille de jeu. */ 
    public static int[][] getGrille(){
        return LectureFichier.grille;
    }
    /*Methode erreur : elle permet de reinitialiser les variables de classe en cas d'erreur lors de la lecture du fichier. */
    private static void erreur(){
        LectureFichier.thesee = null;
        LectureFichier.sortie = null;
        LectureFichier.grille = null;
    }
    /*Methode isAvalaible : elle permet de savoir si les donnees du labyrinthe ont ete correctement lues a partir du fichier. */
    public static boolean isAvalaible(){
        if (LectureFichier.thesee == null || LectureFichier.sortie == null || LectureFichier.grille == null){
            return false;
        }
        return true;
    }
}