/**
 * This Class represent The card Crsis (Crise)
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Crisis extends KarmicCard {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8926696909525334204L;

	public Crisis (boolean cardIsVisible) {
		super("Crise", 2, ColorCard.RED, "Le rival de votre choix défausse une de ses Oeuvres", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer, Player playerOpponent, GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<  Pouvoir de la Carte ## Crise ## : Le rival de votre choix défausse une de ses Oeuvres  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        if(!playerOpponent.getPlayerDeeds().isEmpty()) {
        	KarmicCard cardToPlay = playerOpponent.pickCardFromList(playerOpponent.getPlayerDeeds());
        	if(cardToPlay != null) {
        		playerOpponent.moveCardFromListToAnother(cardToPlay, playerOpponent.getPlayerDeeds(), currentGameBoard.getTheRuins());
        	}
    		
        }
        else {
        	System.out.println("----- Info Pouvoir Carte ### Crise ### : Votre Rival <<<< " + playerOpponent.getPlayerName() + " >>>> n'a pas de carte dans son OEUVRE");
        }
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<  Pouvoir de la Carte ## Crise ## : Fin du Pouvoir  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
	}

	
}
