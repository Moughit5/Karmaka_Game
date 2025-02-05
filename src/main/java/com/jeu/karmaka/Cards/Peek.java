/**
 * This Class represent The card Peek (Coup d'Oeil)
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 *
 */
public class Peek extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8322206643769117693L;

	public Peek(boolean cardIsVisible) {
		super("Coup d'Oeil", 1, ColorCard.BLUE, "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Coup d'Oeil ## : Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte   >>>>>>>>>>>>>>>>>");
		if(!playerOpponent.getPlayerHand().isEmpty()) {
			currentPlayer.displayListCards(playerOpponent.getPlayerHand());
			
			// Vous pouvez ensuite jouer une autre carte
			if(!currentPlayer.getPlayerHand().isEmpty()) {
				currentPlayer.playCard(currentGameBoard, playerOpponent, false, currentGameManager);
			}
			else {
				System.out.println("----- Info Pouvoir Carte ### Coup d'Oeil ### : Votre MAIN   <<<< " + currentPlayer.getPlayerName() + " >>>>  est Vide");
			}
			
		}
		else {
			System.out.println("----- Info Pouvoir Carte ### Coup d'Oeil ### : la MAIN de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
		}

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Coup d'Oeil ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	

}
