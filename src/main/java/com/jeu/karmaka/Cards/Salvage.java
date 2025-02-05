/**
 * This Class represent The card Salvage (Sauvetage)
 */
package com.jeu.karmaka.Cards;


import java.util.LinkedList;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 *  @author Moughit
 */
public class Salvage extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5223621614400529442L;

	public Salvage (boolean cardIsVisible) {
		super("Sauvetage", 2, ColorCard.GREEN, "Ajoutez à votre Main une des 3 dernières cartes de la Fosse", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent,GameManager currentGameManager) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Sauvetage ## : Ajoutez à votre Main une des 3 dernières cartes de la Fosse   >>>>>>>>>>>>>>>>>");
		
		if(!currentGameBoard.getTheRuins().isEmpty() && currentGameBoard.getTheRuins().size() >= 3) {
			//Creer une liste temporaire
	        LinkedList<KarmicCard> tempList = new LinkedList<KarmicCard>();
	   
	        // Iterate en inverse, mais juste 3 fois et ajouter l'element dans la liste temporaire
	        for(int i=0 ; i < 3; i++) {
	        	currentGameBoard.getTheRuins().getLast().setcardIsVisible(true);
	        	currentPlayer.moveCardFromListToAnother(currentGameBoard.getTheRuins().getLast(), currentGameBoard.getTheRuins(), tempList);
	        }
	        
	        KarmicCard cardToPlay2 = currentPlayer.pickCardFromList(tempList);
	        if(cardToPlay2 != null) {
	        	cardToPlay2.setcardIsVisible(true);
	        	currentPlayer.moveCardFromListToAnother(cardToPlay2, tempList, currentPlayer.getPlayerHand());
	        }
	        
			
			// On remet le reste des cartes dans la Fosse
			for(KarmicCard iterCard:  tempList) {
				iterCard.setcardIsVisible(true);
				currentPlayer.moveCardFromListToAnother(iterCard, tempList, currentGameBoard.getTheRuins());
			}
		}
		else {
			System.out.println("----- Info Pouvoir Carte ### Sauvetage ### :  la FOSSE est Vide ou Contient moins de 3 Cartes");
		}
		


		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Sauvetage ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}


	
}
