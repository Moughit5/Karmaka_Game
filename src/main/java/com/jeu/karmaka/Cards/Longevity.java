/**
 * This Class represent The card Longevity (Longévité)
 */
package com.jeu.karmaka.Cards;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Longevity extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 506512139837858425L;

	public Longevity (boolean cardIsVisible) {
		super("Longévité", 2, ColorCard.GREEN, "Placez 2 cartes puisées à la Source sur la Pile d'un joueur", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Longévité ## : Placez 2 cartes puisées à la Source sur la Pile d'un joueur.   >>>>>>>>>>>>>>>>>");
		
		 // Si la source est vide on la recree
        if(currentGameBoard.getTheWell().isEmpty()) {
        	currentGameBoard.reCreateThewell();
		}
		
		for(int i = 0; i < 2; i++) {
			 // Si la source est vide on la recree
            if(currentGameBoard.getTheWell().isEmpty()) {
            	currentGameBoard.reCreateThewell();
    		}
            
 			currentPlayer.moveCardFromListToAnother(currentGameBoard.getTheWell().getLast(), currentGameBoard.getTheWell(), playerOpponent.getPlayerDeck());
 		}

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Longévité ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	

}
