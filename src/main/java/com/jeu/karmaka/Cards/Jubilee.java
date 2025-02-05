/**
 * This Class represent The card Jubilee (Jubilé)
 */
package com.jeu.karmaka.Cards;


import java.util.Scanner;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Jubilee extends KarmicCard {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7895414099603828656L;


	public Jubilee(boolean cardIsVisible) {
		super("Jubilé", 3, ColorCard.GREEN, "Placez jusqu’à 2 cartes de votre Main sur vos Oeuvres", cardIsVisible);
	}


	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Jubilé ## : Placez jusqu’à 2 cartes de votre Main sur vos Oeuvres.   >>>>>>>>>>>>>>>>>");
        
        if(!currentPlayer.getPlayerHand().isEmpty()) {
        	// Prendre Maximum 2 cartes 
     		int counter = 0;
     		while (counter < 1 || counter > 2) {
     			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
     			System.out.println("    \r\n\n" + 
     					"  1-		PRENDRE 1 CARTE \r\n\n" +
     					"  2-		PRENDRE 2 CARTES \r\n\n" +
     					"\n");
     			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
     			@SuppressWarnings("resource")
     			Scanner sc = new Scanner(System.in);
     			if (sc.hasNextInt()) {
    				counter = sc.nextInt();
                } else {
                	sc.next(); // Consume the non-integer input
                    System.out.println(" $$$$$     VEUILLEZ ENTRE UN CHIFFRE    $$$$$$");
                }
     		}
     		
     		if(counter == 2 && currentPlayer.getPlayerHand().size()==1) {
     			counter -= 1;
     		}
     		// Si le joueur choisit une carte on boucle une seule fois sinon on boucle deux fois 
     		for(int i = 0; i < counter; i++) {
     			KarmicCard cardToPlay2 = currentPlayer.pickCardFromList(currentPlayer.getPlayerHand());
     			if(cardToPlay2 != null) {
     				currentPlayer.moveCardFromListToAnother(cardToPlay2, currentPlayer.getPlayerHand(), currentPlayer.getPlayerDeeds());
     			}	
     		}
        }
        else {
        	System.out.println("----- Info Pouvoir Carte ### Jubilé ### : Votre MAIN est vide Joueur  <<<< " + currentPlayer.getPlayerName() + " >>>> ");
        }
        
        
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Jubilé ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}
}
