/**
 * This Class represent The card StolenDreams (Rêves Brisés)
 */
package com.jeu.karmaka.Cards;


import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class StolenDreams extends KarmicCard {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5076437381656954595L;

	public StolenDreams (boolean cardIsVisible) {
		super("Rêves Brisés", 2, ColorCard.BLUE, "Placez la première carte de la Vie Future d'un rival sur la vôtre", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Rêves Brisés ## : Placez la première carte de la Vie Future d'un rival sur la vôtre   >>>>>>>>>>>>>>>>>");
		if(!playerOpponent.getPlayerFutureLife().isEmpty()) {
			currentPlayer.moveCardFromListToAnother(playerOpponent.getPlayerFutureLife().getLast(), playerOpponent.getPlayerFutureLife(), currentPlayer.getPlayerFutureLife());
		}
		else {
			System.out.println("----- Info Pouvoir Carte ### Rêves Brisés ### : la VIE FUTURE de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
		}
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Rêves Brisés ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	
}
