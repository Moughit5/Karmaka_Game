/**
 * This Class represent The card Sowing (Semis)
 */
package com.jeu.karmaka.Cards;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Sowing extends KarmicCard {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -16423904267067216L;

	public Sowing (boolean cardIsVisible) {
		super("Semis", 2, ColorCard.GREEN, "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Semis ## : Puisez 2 cartes à la Source   >>>>>>>>>>>>>>>>>");
		
		
		for(int i = 0; i < 2; i++) {
			// Si la source est vide on la recree
	        if(currentGameBoard.getTheWell().isEmpty()) {
	        	currentGameBoard.reCreateThewell();
			}
			currentPlayer.drawCardToYourHand(currentGameBoard.getTheWell());
		}
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Semis ## : puis placez sur votre Vie Future 2 cartes de votre Main   >>>>>>>>>>>>>>>>>");
		for(int i=0; i < 2; i++) {
			KarmicCard cardToPlay2 = currentPlayer.pickCardFromList(currentPlayer.getPlayerHand());
			if(cardToPlay2 != null) {
				currentPlayer.moveCardFromListToAnother(cardToPlay2, currentPlayer.getPlayerHand(), currentPlayer.getPlayerFutureLife());
			}
		}
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Semis ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}
	
	
}
