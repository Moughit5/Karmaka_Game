/**
 * This The Main Class of the Karmaka Game Cards 
 * <p>
 *  This where we start a new game match or load a saved game match of Karmaka
 */
package com.jeu.karmaka;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.jeu.karmaka.MainGame.GameManager;

/**
 * @author Moughit
 *
 */
public class KarmakaMainGame {
	
	static GameManager gameKarmaka;
	
	/**
	 * Method to save a GameManager Object Class to a file
	 * 
	 * @param currentGameManager
	 * @param filename
	 * 
	 * **/
    public static void saveGame(GameManager currentGameManager, String filename) throws IOException {
    	
        try {
        	
        	File saveFile = new File(filename);
        	
            if(!saveFile.exists()) {
                saveFile.createNewFile();
            }
            
            FileOutputStream saveFileSub = new FileOutputStream(saveFile);
            ObjectOutputStream save = new ObjectOutputStream(saveFileSub);
       
            save.writeObject(currentGameManager); 
            save.flush();
            save.close();
            
            System.out.println(">>>>>> Sauvegarde Réussie de la Partie");
        } 
        catch (Exception  e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenu lors de la sauvegarde \n "
            		+ e.getClass() + " :  " + e.getMessage()  + " \n");
        }
        
    }

    /**
     * Method to load an GameManager object from a file
     * 
     * @param filename
     * 
     * @return GameManager  &nbsp;&nbsp;&nbsp; Object that contains the game match state
     * */
    public static GameManager loadGame(String filename) {
    	
        try {
			File file = new File(filename);
			if (!file.exists()) {
				System.out.println("Pas de Partie Sauvegarder dans le fichier : " + filename);
				return null;
			}

			FileInputStream loadFileSub = new FileInputStream(filename);
			ObjectInputStream load = new ObjectInputStream(loadFileSub);
			
			GameManager game = (GameManager) load.readObject();
			
			System.out.println(">>>>> Partie chargé avec succès");
			
			load.close();
			
			return game;
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Une erreur s'est produite lors du chargement de la Partie sauvegarder \n"
            		+ e.getClass() + " : " + e.getMessage() +  " \n");
            return null;
        }
    }
    
    /**
     * Function that takes user input, ensuring each input is a string of at least 5 characters and contains no spaces.
     * @param scanner
     * @param message
     * 
     * @return inputFormat
     * */
    private static String getStringInput(Scanner scanner, String message) {
        String inputFormat;
        do {
            System.out.println(message);
            inputFormat = scanner.nextLine();
            if (inputFormat.length() < 5) {
                System.out.println("La saisie doit comporter au moins 5 caractères. Veuillez réessayer.");
            } else if (inputFormat.matches(".*\\s.*")) {
                System.out.println("L'entrée ne doit contenir aucun espace. Veuillez réessayer.");
                inputFormat = ""; // Reset input
            }
        } while (inputFormat.length() < 5);
        return inputFormat;
    }
    

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException,ClassNotFoundException{
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int choice = 0, secondChoice = 0;
		String savedFileName = null, playerNameOne = null, playerNameTwo = null;
		
		System.out.println(">>>>>        Bienvenue à KARMAKA, le jeu de cartes compétitif se déroulant sur plusieurs vies.     <<<<<");
		System.out.println(">>>>>        Ce jeu est doté de Sauvegarde Automatique.                                         <<<<<");
		System.out.println(">>>>> ******************************************************************************************   <<<<<");
		System.out.println(">>>>> Choix De Type de Partie ..... <<<<<");
		while (choice < 1 || choice > 2) {
			
			System.out.println(">>>>>   1-  Joueur vs Joueur  <<<<<\n");
			System.out.println(">>>>>   2-  Joueur vs Robot   <<<<<\n");
			
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
			} else {
				scanner.next(); // Consume the non-integer input
				System.out.println("!!!!!!!!!!!! VEUILLEZ ENTRE UN CHIFFRE SVP !!!!!!!!!!!!\n");
			}
		}
		switch (choice) {
			case 1:
				while (secondChoice < 1 || secondChoice > 2) {
					System.out.println(">>>>>  Partie Joueur vs Joueur <<<<<\n");
					System.out.println(">>>>>  1-  Demarrage d'une Nouvelle Partie  <<<<<\n");
					System.out.println(">>>>>  2-  Charger Partie Sauvegarder       <<<<<\n");
					
					if (scanner.hasNextInt()) {
						secondChoice = scanner.nextInt();
					} else {
						scanner.next(); // Consume the non-integer input
						System.out.println("!!!!!!!!!!!! VEUILLEZ ENTRE UN CHIFFRE SVP !!!!!!!!!!!!\n");
					}
				}
				switch(secondChoice) {
					case 1:
						@SuppressWarnings("resource")
						Scanner sc = new Scanner(System.in);
						playerNameOne = getStringInput(sc, ">>>> Saisissez le nom du 1ere Joueur (au moins 5 caractères, sans espaces) :  <<<<<<");
				        playerNameTwo = getStringInput(sc, ">>>> Saisissez le nom du 2eme Joueur (au moins 5 caractères, sans espaces) :  <<<<<<");
				        // Demarrage d'une nouvelle partie 
				        gameKarmaka = new GameManager(playerNameOne, playerNameTwo, 1);
				        gameKarmaka.startGame(true);
						break;
					case 2:
						System.out.println(">>>>>  Chargement de la Partie Joueur vs Joueur  <<<<<\n");
						savedFileName = "karmmakaGameStateHuman.sav";
						gameKarmaka = loadGame(savedFileName);
						if(gameKarmaka  != null)
							gameKarmaka.startGame(false);
						
						break;
				}
				break;
			case 2:
				while (secondChoice < 1 || secondChoice > 2) {
					System.out.println(">>>>>  Partie Joueur vs Robot <<<<<\n");
					System.out.println(">>>>>  1-  Demarrage d'une Nouvelle Partie  <<<<<\n");
					System.out.println(">>>>>  2-  Charger Partie Sauvegarder       <<<<<\n");
					
					if (scanner.hasNextInt()) {
						secondChoice = scanner.nextInt();
					} else {
						scanner.next(); // Consume the non-integer input
						System.out.println("!!!!!!!!!!!! VEUILLEZ ENTRE UN CHIFFRE SVP !!!!!!!!!!!!\n");
					}
				}
				switch(secondChoice) {
					case 1:
						@SuppressWarnings("resource")
						Scanner sc = new Scanner(System.in);
						playerNameOne = getStringInput(sc, ">>>> Saisissez le nom du Joueur (au moins 5 caractères, sans espaces) :  <<<<<<");
						playerNameTwo = "CYBORG_17";
						// Demarrage d'une nouvelle partie 
				        gameKarmaka = new GameManager(playerNameOne, playerNameTwo, 2);
				        gameKarmaka.startGame(true);
						break;
					case 2:
						System.out.println(">>>>>  Chargement de la Partie Joueur vs Robot  <<<<<\n");
						savedFileName = "karmmakaGameStateRobot.sav";
						gameKarmaka = loadGame(savedFileName);
						if(gameKarmaka  != null)
							gameKarmaka.startGame(false);
						
						break;
				}
				break;
		}	
	}

}
