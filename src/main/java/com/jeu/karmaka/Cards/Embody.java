/**
 * This Class represent The card Embody (Incarnation)
 */
package com.jeu.karmaka.Cards;



import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Embody extends KarmicCard {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8256031533344293714L;


	public Embody (boolean cardIsVisible) {
		super("Incarnation", 1, ColorCard.MOSAIQUE, "Choisissez une de vos Oeuvres. Copiez son pouvoir", cardIsVisible);
	}


	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Incarnation ## : Choisissez une de vos Oeuvres. Copiez son pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        if(!currentPlayer.getPlayerDeeds().isEmpty()) {
        	KarmicCard cardToPlay2  = currentPlayer.pickCardFromList(currentPlayer.getPlayerDeeds());
        	if(cardToPlay2 != null) {
        		cardToPlay2.activatePowerCard(currentGameBoard, currentPlayer, playerOpponent, currentGameManager);
        	}
            
        }
        else {
        	System.out.println("----- Info Pouvoir Carte ### Incarnation ### : Votre OEUVRE est vide Joueur  <<<< " + currentPlayer.getPlayerName() + " >>>> ");
        }
        
        
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Incarnation ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}
}
