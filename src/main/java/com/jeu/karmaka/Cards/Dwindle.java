/**
 * This Class represent The card Dwindle (Dernier Souffle)
 */
package com.jeu.karmaka.Cards;


import java.util.Scanner;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Dwindle extends KarmicCard{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8808769601238368576L;

	public Dwindle (boolean cardIsVisible) {
		super("Dernier Souffle", 1, ColorCard.RED, "Le joueur de votre choix défausse une carte de sa Main", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Dernier Souffle  ## : Le joueur de votre choix défausse une carte de sa Main   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        int counter = 0;
		while (counter < 1 || counter > 2) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("    \r\n\n" + 
					"  1-		VOULEZ-VOUS JOUER LA CARTE SUR VOUS MEMES \r\n\n" +
					"  2-		VOULEZ-VOUS JOUER LA CARTE SUR PlayerName %%%%% " + playerOpponent.getPlayerName() + " %%%%%  \r\n\n" +
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
		switch(counter) {
		case 1:
			if(!currentPlayer.getPlayerHand().isEmpty()) {
				KarmicCard cardToPlay  = currentPlayer.pickCardFromList(currentPlayer.getPlayerHand());
				if(cardToPlay != null) {
					currentPlayer.moveCardFromListToAnother(cardToPlay, currentPlayer.getPlayerHand(), currentGameBoard.getTheRuins());
				}
			}
			else {
				System.out.println("----- Info Pouvoir Carte ### Dernier Souffle ### : Votre MAIN est vide Joueur  <<<< " + currentPlayer.getPlayerName() + " >>>> ");
			}
			break;
		case 2:
			if(!playerOpponent.getPlayerHand().isEmpty()) {
				KarmicCard cardToPlay2  = playerOpponent.pickCardFromList(playerOpponent.getPlayerHand());
				if(cardToPlay2 != null) {
					playerOpponent.moveCardFromListToAnother(cardToPlay2, playerOpponent.getPlayerHand(), currentGameBoard.getTheRuins());
				}
			}
			else {
				System.out.println("----- Info Pouvoir Carte ### Dernier Souffle ### : la MAIN de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
			}
			break;
	}
        
        
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Dernier Souffle ##: Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	
}
