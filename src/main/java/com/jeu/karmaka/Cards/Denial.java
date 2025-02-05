/**
 * This Class represent The card Denial (Déni)
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Denial extends KarmicCard {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7215296209561263885L;

	public Denial (boolean cardIsVisible) {
		super("Déni", 2, ColorCard.BLUE, "Défaussez une carte de votre Main, Copiez le pouvoir de cette carte", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer, Player playerOpponent, GameManager currentGameManager) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<  Pouvoir de la Carte ## Déni ## :  Défaussez une carte de votre Main  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
		// Défaussez une carte de votre Main
        if(!currentPlayer.getPlayerHand().isEmpty()) {
        	
        	KarmicCard cardToPlay = currentPlayer.pickCardFromList(currentPlayer.getPlayerHand());
        	if(cardToPlay != null) {
        		currentPlayer.moveCardFromListToAnother(cardToPlay, currentPlayer.getPlayerHand(), currentGameBoard.getTheRuins());
        		
        		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<  Pouvoir de la Carte ## Déni ## : Copiez le pouvoir de cette carte  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    			// Copiez le pouvoir de cette carte c-a-d activer le pouvoir de la carte defausser dans la fosse
    			cardToPlay.activatePowerCard(currentGameBoard, currentPlayer, playerOpponent,currentGameManager);	
        	}	
        }
        else {
        	System.out.println("----- Info Pouvoir Carte ### Déni ### : Votre MAIN est vide Joueur  <<<< " + currentPlayer.getPlayerName() + " >>>> ");
        }
		
		
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<  Pouvoir de la Carte ## Déni ## : Fin du Pouvoir  >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
	}

	
}
