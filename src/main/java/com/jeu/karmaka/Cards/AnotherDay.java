/**
 * This Class represent The card Another Day (Lendemain)
 */
package com.jeu.karmaka.Cards;



import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class AnotherDay extends KarmicCard{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4672032334991416479L;


	public AnotherDay(boolean cardIsVisible) {
		super("Lendemain", 1, ColorCard.GREEN, "Puisez une carte à la Source, Vous pouvez ensuite jouer une autre carte", cardIsVisible);
	}


	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer, Player playerOpponent, GameManager currentGameManager) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< Pouvoir de la Carte #### Lendemain #### : Puisez une carte à la Source >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        // Si la source est vide on la recree
        if(currentGameBoard.getTheWell().isEmpty()) {
        	currentGameBoard.reCreateThewell();
		}
		// Puisez une carte à la Source
		currentPlayer.drawCardToYourHand(currentGameBoard.getTheWell());
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< Pouvoir de la Carte #### Lendemain #### : Vous pouvez ensuite jouer une autre carte >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// Vous pouvez ensuite jouer une autre carte
		if(!currentPlayer.getPlayerHand().isEmpty()) {
			currentPlayer.playCard(currentGameBoard, playerOpponent, false, currentGameManager);
		}
		else {
			System.out.println("----- Info Pouvoir Carte ### Lendemain ### : Votre MAIN   <<<< " + currentPlayer.getPlayerName() + " >>>>  est Vide");
		}
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<  Pouvoir de la Carte #### Lendemain #### : Fin du Pouvoir >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
       
	}
	
	
}
