/**
 * This Class represent The card Thievery (Vol)
 */
package com.jeu.karmaka.Cards;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Thievery extends KarmicCard {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6807507663221923811L;

	public Thievery (boolean cardIsVisible) {
		super("Vol", 3, ColorCard.BLUE, "Placez dans votre Main l’Oeuvre Exposée d'un rival.", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Vol ## : Placez dans votre Main l’Oeuvre Exposée d'un rival.   >>>>>>>>>>>>>>>>>");
		if(!playerOpponent.getPlayerDeeds().isEmpty()) {
			currentPlayer.drawCardToYourHand(playerOpponent.getPlayerDeeds());
		}
		else {
			 System.out.println("----- Info Pouvoir Carte ### Vol ### : l'OEUVRE de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
		}
		
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Vol ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	

}
