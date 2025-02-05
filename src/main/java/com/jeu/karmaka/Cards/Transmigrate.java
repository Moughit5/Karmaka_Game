/**
 * This Class represent The card Transmigrate (Transmigration)
 */

package com.jeu.karmaka.Cards;


import java.util.Random;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 *
 */
public class Transmigrate extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2437051456407854204L;

	public Transmigrate(boolean cardIsVisible) {
		super("Transmigration", 1, ColorCard.BLUE, "Placez dans votre Main n’importe quelle carte de votre Vie Future", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) { 
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Transmigration ## : Placez dans votre Main n’importe quelle carte de votre Vie Future   >>>>>>>>>>>>>>>>>");
		if(!currentPlayer.getPlayerFutureLife().isEmpty()) {
			Random random = new Random();
			int firstIndex = random.nextInt(currentPlayer.getPlayerFutureLife().size());
			
			currentPlayer.moveCardFromListToAnother(currentPlayer.getPlayerFutureLife().get(firstIndex), currentPlayer.getPlayerFutureLife(), currentPlayer.getPlayerHand());
		}
		else {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< VOTRE " + currentPlayer.getPlayerName() +" VIE FUTURE EST VIDE  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Transmigration ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	

}
