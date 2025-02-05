/**
 * This Class represent The card Destiny (Destinée)
 */
package com.jeu.karmaka.Cards;


import java.util.LinkedList;
import java.util.Scanner;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 */
public class Destiny extends KarmicCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8582404995509089210L;

	public Destiny(boolean cardIsVisible) {
		super("Destinée", 2, ColorCard.BLUE, "Regardez les 3 premières cartes de la Source, Ajoutez-en jusqu’à 2 à votre Vie Future. Replacez le reste dans l'ordre souhaité", cardIsVisible);
	}

	@Override
	public void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer,	Player playerOpponent, GameManager currentGameManager) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Destinée ## : Regardez les 3 premières cartes de la Source   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        
        // Si la source est vide on la recree
        if(currentGameBoard.getTheWell().isEmpty()) {
        	currentGameBoard.reCreateThewell();
		}
        
        //Creer une liste temporaire
        LinkedList<KarmicCard> tempList = new LinkedList<KarmicCard>();
   
        // Iterate en inverse, mais juste 3 fois et ajouter l'element dans la liste temporaire
        for(int i=0 ; i < 3; i++) {
        	
        	 // Si la source est vide on la recree
            if(currentGameBoard.getTheWell().isEmpty()) {
            	currentGameBoard.reCreateThewell();
    		}
            
        	currentGameBoard.getTheWell().getLast().setcardIsVisible(true);
        	currentPlayer.moveCardFromListToAnother(currentGameBoard.getTheWell().getLast(), currentGameBoard.getTheWell(), tempList);
        }
        // Afficher la liste temporaire
        currentPlayer.displayListCards(tempList);
        
		System.out.println("<<<<<<<<<<<<<<<<<<<<<  Pouvoir de la Carte ## Destinée ## : Ajoutez-en jusqu’à 2 à votre Vie Future, Replacez le reste dans l'ordre souhaité >>>>>>>>>>>>>>>>");
		// Prendre Maximum 2 cartes et placez le reste dans la source
		int counter = 0;
		while (counter < 1 || counter > 2) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("    \r\n\n" + 
					"  1-		PRENDRE 1 CARTE \r\n\n" +
					"  2-		PRENDRE 2 CARTES \r\n\n" +
					"\n");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			if (sc.hasNextInt()) {
				counter = sc.nextInt();
            } else {
            	sc.next(); // Consume the non-integer input
                System.out.println(" $$$$$     VEUILLEZ ENTRE UN CHIFFRE    $$$$$$");
            }
		}
		
		// Si le joueur choisit une carte on boucle une seule fois sinon on boucle deux fois 
		for(int i=0; i < counter; i++) {
			KarmicCard cardToPlay2 = currentPlayer.pickCardFromList(tempList);
			if(cardToPlay2 != null) {
				currentPlayer.moveCardFromListToAnother(cardToPlay2, tempList, currentPlayer.getPlayerFutureLife());
			}
		}
		
		// Replacez le reste des cartes dans l'ordre souhaité
		for(KarmicCard iterCard:  tempList) {
			iterCard.setcardIsVisible(false);
			currentPlayer.moveCardFromListToAnother(iterCard, tempList, currentGameBoard.getTheWell());
		}
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<   Pouvoir de la Carte ## Destinée ## : Fin du Pouvoir   >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}
