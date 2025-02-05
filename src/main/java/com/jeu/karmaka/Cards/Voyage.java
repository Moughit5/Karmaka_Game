/**
 * This Class represent The card Voyage 
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Voyage extends KarmicCard {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8283874782867340840L;

	public Voyage(boolean cardIsVisible) {
		super("Voyage", 3, ColorCard.GREEN, "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer, Player playerOpponent,GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Voyage ## : Puisez 3 cartes à la Source.   >>>>>>>>>>>>>>>>>");
		for(int i = 0; i < 3; i++) {
			if(currentGameBoard.getTheWell().isEmpty()) {
	        	currentGameBoard.reCreateThewell();
			}
			currentPlayer.drawCardToYourHand(currentGameBoard.getTheWell());
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Voyage ## : Vous pouvez ensuite jouer une autre carte   >>>>>>>>>>>>>>>>>");
		// Vous pouvez ensuite jouer une autre carte
		if(!currentPlayer.getPlayerHand().isEmpty()) {
			currentPlayer.playCard(currentGameBoard, playerOpponent, false, currentGameManager);
		}
		else {
			System.out.println("----- Info Pouvoir Carte ### Voyage ### : Votre MAIN   <<<< " + currentPlayer.getPlayerName() + " >>>>  est Vide");
		}
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Voyage ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	
	
}
