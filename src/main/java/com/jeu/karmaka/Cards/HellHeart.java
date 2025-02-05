/**
 * This Class represent The card Hell Heart (Fournaise)
 * 
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class HellHeart extends KarmicCard {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2169143867799074118L;


	public HellHeart (boolean cardIsVisible) {
		super("Fournaise", 2, ColorCard.RED, "Défaussez les 2 premières cartes de la Vie Future d'un rival.", cardIsVisible);
	}


	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer, Player playerOpponent,GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Fournaise ## : Défaussez les 2 premières cartes de la Vie Future d'un rival.   >>>>>>>>>>>>>>>>>");
        
        if(!playerOpponent.getPlayerFutureLife().isEmpty()) {
        	
        	if(playerOpponent.getPlayerFutureLife().size() == 1) {
        		currentPlayer.moveCardFromListToAnother(playerOpponent.getPlayerFutureLife().getLast(), playerOpponent.getPlayerFutureLife(), currentGameBoard.getTheRuins());
        	}
        	else {
        		for(int i = 0; i < 2; i++ ) {
                	currentPlayer.moveCardFromListToAnother(playerOpponent.getPlayerFutureLife().getLast(), playerOpponent.getPlayerFutureLife(), currentGameBoard.getTheRuins());
                }
        	}
        }
        else {
        	System.out.println("----- Info Pouvoir Carte ### Fournaise ### : la VIE FUTURE de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
        }
        
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Fournaise ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}
