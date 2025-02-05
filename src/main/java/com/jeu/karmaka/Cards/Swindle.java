/**
 *This Class represent The card Swindle (Duperie)
 */
package com.jeu.karmaka.Cards;


import java.util.LinkedList;
import java.util.Random;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Swindle extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8464380063333179169L;

	public Swindle (boolean cardIsVisible) {
		super("Duperie", 3, ColorCard.BLUE, "Regardez 3 cartes de la Main d’un rival. Ajoutez-en une à votre Main", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Duperie ## : Regardez 3 cartes de la Main d’un rival. Ajoutez-en une à votre Main  >>>>>>>>>>>>>>>>>");
		
		//Creer une liste temporaire
        LinkedList<KarmicCard> tempList = new LinkedList<KarmicCard>();
        Random random = new Random();
        
		if(!playerOpponent.getPlayerHand().isEmpty() && playerOpponent.getPlayerHand().size() >= 3 ) {
			
			int firstIndex = random.nextInt(playerOpponent.getPlayerHand().size());
			int secondIndex,thirdIndex;
			
			do {
	            secondIndex = random.nextInt(playerOpponent.getPlayerHand().size());
	            thirdIndex  = random.nextInt(playerOpponent.getPlayerHand().size());
	        } while (firstIndex == secondIndex || firstIndex == thirdIndex || secondIndex == thirdIndex);
			
			tempList.add(playerOpponent.getPlayerHand().get(firstIndex));
			tempList.add(playerOpponent.getPlayerHand().get(secondIndex));
			tempList.add(playerOpponent.getPlayerHand().get(thirdIndex));
			
			KarmicCard cardToPlay = currentPlayer.pickCardFromList(tempList);
			if(cardToPlay != null) {
				cardToPlay.setcardIsVisible(true);
				currentPlayer.moveCardFromListToAnother(cardToPlay, playerOpponent.getPlayerHand(), currentPlayer.getPlayerHand());
			}
			
		}
		 else if(playerOpponent.getPlayerHand().size() < 3  && !playerOpponent.getPlayerHand().isEmpty() )
		 {
			 KarmicCard cardToPlay2 = currentPlayer.pickCardFromList(playerOpponent.getPlayerHand());
			 if(cardToPlay2 != null) {
				 cardToPlay2.setcardIsVisible(true);
				 currentPlayer.moveCardFromListToAnother(cardToPlay2, playerOpponent.getPlayerHand(), currentPlayer.getPlayerHand());
			 }
			 
		 }
		 else if(playerOpponent.getPlayerHand().isEmpty()){
			 System.out.println("----- Info Pouvoir Carte ### Duperie ### : la MAIN de votre Rival  <<<< " + playerOpponent.getPlayerName() + " >>>>  est Vide");
		 }


		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Duperie ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}

	
}
