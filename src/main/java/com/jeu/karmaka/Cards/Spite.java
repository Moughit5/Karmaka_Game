/**
 * This Class represent The card spite (Bassesse)
 */
package com.jeu.karmaka.Cards;

import java.util.Random;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Spite extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1525804792281747957L;

	public Spite (boolean cardIsVisible) {
		super("Bassesse", 3, ColorCard.RED, "Défaussez au hasard 2 cartes de la Main d’un rival", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Bassesse ## : Défaussez au hasard 2 cartes de la Main d’un rival   >>>>>>>>>>>>>>>>>");
	
		 if(!playerOpponent.getPlayerHand().isEmpty() && playerOpponent.getPlayerHand().size() > 1 ) {
			Random random = new Random();
			for(int i = 0; i < 2; i++) {
				int firstIndex = random.nextInt(playerOpponent.getPlayerHand().size());
		        currentPlayer.moveCardFromListToAnother(playerOpponent.getPlayerHand().get(firstIndex), playerOpponent.getPlayerHand(), currentGameBoard.getTheRuins());
			}
		}
		 else if(playerOpponent.getPlayerHand().size() == 1 && !playerOpponent.getPlayerHand().isEmpty() )
		 {
			 currentPlayer.moveCardFromListToAnother(playerOpponent.getPlayerHand().get(0), playerOpponent.getPlayerHand(), currentGameBoard.getTheRuins());
		 }
		 
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Bassesse ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

}
