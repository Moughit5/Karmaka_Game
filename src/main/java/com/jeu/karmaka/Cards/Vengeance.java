/**
 * This Class represent The card Vengeance
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Vengeance extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3061795105044930818L;

	public Vengeance (boolean cardIsVisible) {
		super("Vengeance", 3, ColorCard.RED, "Défaussez l’Oeuvre Exposée d’un rival", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer, Player playerOpponent,GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Vengeance ## : Défaussez l’Oeuvre Exposée d’un rival  >>>>>>>>>>>>>>>>>");
		
		if(!playerOpponent.getPlayerDeeds().isEmpty()) {
			
			playerOpponent.getPlayerDeeds().getLast().setcardIsVisible(true);
			currentPlayer.moveCardFromListToAnother(playerOpponent.getPlayerDeeds().getLast(), playerOpponent.getPlayerDeeds(), currentGameBoard.getTheRuins());
		}
		else {
			System.out.println("----- Info Pouvoir Carte ### Vengeance ### : l'Oeuvre de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
		}
		
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Vengeance ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	
}
