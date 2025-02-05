/**
 * This Class represent The card Panic (Paique)
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Panic extends KarmicCard {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6719806407123957834L;

	public Panic(boolean cardIsVisible) {
		super("Panique", 1, ColorCard.RED, "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Panique ## : Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte   >>>>>>>>>>>>>>>>>");
		
		 if(!playerOpponent.getPlayerDeck().isEmpty()) {
			 currentPlayer.moveCardFromListToAnother(playerOpponent.getPlayerDeck().getLast(), playerOpponent.getPlayerDeck(), currentGameBoard.getTheRuins());
			// Vous pouvez ensuite jouer une autre carte
			if(!currentPlayer.getPlayerHand().isEmpty()) {
				currentPlayer.playCard(currentGameBoard, playerOpponent, false, currentGameManager);
			}
			else {
				System.out.println("----- Info Pouvoir Carte ### Panique ### : Votre MAIN   <<<< " + currentPlayer.getPlayerName() + " >>>>  est Vide");
			}
		 }
		 else {
			 System.out.println("----- Info Pouvoir Carte ### Panique ### : la PILE de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
		 }
		
		


		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Panique ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	
}
