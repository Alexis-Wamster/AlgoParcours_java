import java.io.*;
import javax.swing.*;

public class EcritureFichier {
	public static void ecrireFichier(int[][] grille, Point thesee, Point sortie) {
        
	    // Utiliser un JFileChooser pour permettre a l'utilisateur de choisir l'emplacement du fichier
	    JFileChooser fileChooser = new JFileChooser();
	    int userSelection = fileChooser.showSaveDialog(null);
	    
	    // Verifier si l'utilisateur a selectionne un emplacement valide pour ecrire le fichier
	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToSave = fileChooser.getSelectedFile();
	        try {
	            // Ouvrir un flux de sortie pour ecrire dans le fichier binaire
	            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(fileToSave));
	            
	            // Ecrire la taille de la grille dans les 8 premiers bits
	            outputStream.writeByte((byte) grille.length);
	            
	            // Ecrire les coordonnees de Thesee et de la sortie dans les 8 bits suivants chacun
	            outputStream.writeByte((byte) thesee.y);
	            outputStream.writeByte((byte) thesee.x);
	            outputStream.writeByte((byte) sortie.y);
	            outputStream.writeByte((byte) sortie.x);
	            
	            // Ecrire les donnees de la grille dans les bits restants
	            int bitsRemaining = 8;
	            byte currentByte = 0;
	            for (int i = 0; i < grille.length; i++) {
	                for (int j = 0; j < grille[i].length; j++) {
	                    if (grille[i][j] == 1) {
	                        currentByte |= (1 << (bitsRemaining - 1));
	                    }
	                    bitsRemaining--;
	                    if (bitsRemaining == 0) {
	                        outputStream.writeByte(currentByte);
	                        bitsRemaining = 8;
	                        currentByte = 0;
	                    }
	                }
	            }
	            
	            // Si nous avons encore des bits non ecrits, ecrire le dernier octet partiellement rempli
	            if (bitsRemaining < 8) {
	                outputStream.writeByte(currentByte);
	            }
	            
	            // Fermer le flux de sortie
	            outputStream.close();
	            
	        } catch (IOException ex) {
	            System.err.println("Erreur lors de l'ecriture du fichier : " + ex.getMessage());
	        }
	    }
	}
}