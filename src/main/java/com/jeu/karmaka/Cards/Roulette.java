/**
 * This Class represent The card Roulette 
 */
package com.jeu.karmaka.Cards;


import java.util.Scanner;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Roulette extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2526869402253638046L;

	public Roulette (boolean cardIsVisible) {
		super("Roulette", 2, ColorCard.RED, "Défaussez jusqu’à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) + 1.", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Roulette ## : Défaussez jusqu’à 2 cartes de votre Main. >>>>>>>>>>>>>>>>>");
		
		 // Si la source est vide on la recree
        if(currentGameBoard.getTheWell().isEmpty()) {
        	currentGameBoard.reCreateThewell();
		}
		
		int counter = -1;
		while (counter < 0 || counter > 2) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.print(">>>>>>>>  ENTREZ VOTRE CHOIX  (0 - 2 ) >>>>>>>>>>>>>>>>>>");
			System.out.println("    \r\n\n" + 
					"  0-		PRENDRE 0 CARTE \r\n\n" +
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
		if(!currentPlayer.getPlayerHand().isEmpty() && currentPlayer.getPlayerHand().size() >=2 ) {
			// Si le joueur choisit une carte on boucle une seule fois sinon on boucle deux fois 
			for(int i=0; i < counter; i++) {
				KarmicCard cardToPlay2 = currentPlayer.pickCardFromList(currentPlayer.getPlayerHand());
				if(cardToPlay2 != null) {
					currentPlayer.moveCardFromListToAnother(cardToPlay2, currentPlayer.getPlayerHand(), currentGameBoard.getTheRuins());
				}
			}
			
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<  Pouvoir de la Carte ## Roulette ## :  Vous pouvez ensuite puiser à la Source autant de carte(s) + 1.   >>>>>>>>>>>>>>>>>>>");
			
			for(int i=0; i < (counter + 1); i++) {
				 // Si la source est vide on la recree
	            if(currentGameBoard.getTheWell().isEmpty()) {
	            	currentGameBoard.reCreateThewell();
	    		}
				currentPlayer.drawCardToYourHand(currentGameBoard.getTheWell());
			}
		}
		else if(currentPlayer.getPlayerHand().isEmpty()) {
			currentPlayer.drawCardToYourHand(currentGameBoard.getTheWell());
		}
		
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Roulette ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}


	
}
