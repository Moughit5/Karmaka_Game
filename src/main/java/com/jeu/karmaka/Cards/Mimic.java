/**
 *  This Class represent The card Mimic (Mimétisme)
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Mimic extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2823395909665404934L;

	public Mimic (boolean cardIsVisible) {
		super("Mimétisme", 1, ColorCard.MOSAIQUE, "Choisissez un Rival. Copiez le pouvoir de son Oeuvre Exposée", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Mimétisme ## : Choisissez un Rival. Copiez le pouvoir de son Oeuvre Exposée   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        if(!playerOpponent.getPlayerDeeds().isEmpty()) {
        	
        	currentPlayer.seeExposedDeed(playerOpponent);
            KarmicCard cardToPlay2  = playerOpponent.getPlayerDeeds().getLast();
            cardToPlay2.activatePowerCard(currentGameBoard, currentPlayer, playerOpponent, currentGameManager);
        }
        else {
        	System.out.println("----- Info Pouvoir Carte ### Mimétisme ### : l'OEUVRE de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
        }
        
        
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Mimétisme ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}
	
}
