/**
 * This Class represent the Player 
 */
package com.jeu.karmaka.Players;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jeu.karmaka.Cards.KarmicCard;
import com.jeu.karmaka.Ladder.KaramaLevel;
import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;


/**
 * @author Moughit
 *
 */
public abstract class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8734347903440345383L;
	protected String playerName;
	protected boolean isaTranscendent;
	protected boolean hasCompletedTurn;
	protected int nbrKarmicRings;
	protected KaramaLevel playerKarmicLevel;
	protected LinkedList<KarmicCard> playerHand;  // Main
	protected LinkedList<KarmicCard> playerDeck;  // Pile
	protected LinkedList<KarmicCard> playerDeeds; // Oeuvre
	protected LinkedList<KarmicCard> playerFutureLife;  // Vie Future
	


	/************************************** Constructor *************************************************/

	public Player(String playerName, int nbrKarmicRings, KaramaLevel playerKarmicLevel) {
		this.playerName = playerName;
		this.isaTranscendent = false;
		this.hasCompletedTurn=false;
		this.nbrKarmicRings = nbrKarmicRings;
		this.playerKarmicLevel = playerKarmicLevel;
		this.playerHand =  new LinkedList<KarmicCard>();
		this.playerDeck = new LinkedList<KarmicCard>();
		this.playerDeeds = new LinkedList<KarmicCard>();
		this.playerFutureLife = new LinkedList<KarmicCard>();
	}	
	/******************************************************************************************************************************/
	/*****************************               Methods and Features             ***************************************************/

	/***
	 * Add card to a given list (i.e Deed or Future life etc etc...)
	 * @param destinationList
	 * @param targetCard

	 * @return true <p> if the adding element is success
	 * */
	public boolean pushToListCards(KarmicCard targetCard, LinkedList<KarmicCard> destinationList) {
		return destinationList.add(targetCard);
	}

	/***
	 * Remove a card from a given list (i.e Deed or Future life etc etc...)
	 * @param srcList
	 * @param targetCard
	 * @return true if the deletion is succeed
	 * 
	 * */
	public boolean pulloffFromListCards(KarmicCard targetCard, LinkedList<KarmicCard> srcList) {
		boolean result = false;
		if(!srcList.isEmpty()) {
			result= srcList.remove(targetCard);
		}
		return result;
	}

	/***
	 * Move a card from a source cards list to another cards list (i.e pull a card from the deck and puch it to you hand list card)
	 * @param srcList
	 * @param destinationList
	 * @param cardToMove
	 * */
	public void moveCardFromListToAnother(KarmicCard cardToMove, LinkedList<KarmicCard> srcList, LinkedList<KarmicCard> destinationList) {
		this.pulloffFromListCards(cardToMove,srcList);
		this.pushToListCards(cardToMove, destinationList);
	}

	/**
	 * Draw a card from a list card to your Hand (La Main)
	 * @param srcList 
	 * <p> the list that the player choose to pick a card from
	 * **/
	public void drawCardToYourHand(LinkedList<KarmicCard> srcList) {

		if(!srcList.isEmpty()) {
			KarmicCard card = srcList.getLast();
			card.setcardIsVisible(true);
			moveCardFromListToAnother(card, srcList, this.getPlayerHand());
		}
		else {
			System.out.println("-- Info MAIN: No Card Found to Draw");
		}
	}

	/**
	 * Draw a card from a list card to your Deck (Pile)
	 * @param srcList 
	 * <p> the list that the player choose to pick a card from
	 * **/
	public void drawCardToYourDeck(LinkedList<KarmicCard> srcList) {

		if(!srcList.isEmpty()) {
			KarmicCard card = srcList.getLast();
			card.setcardIsVisible(false);
			moveCardFromListToAnother(card, srcList, this.getPlayerDeck());
		}
		else {
			System.out.println("-- Info PILE: No Card Found to Draw");
		}
	}

	/***
	 * Display a card
	 * @param cardToDisplay
	 * <p>
	 */
	public void displayCard(KarmicCard cardToDisplay) {

		System.out.println("######  Nom de la carte : " + cardToDisplay.getCardName().toUpperCase());
		System.out.println("######  Point de la carte : " + cardToDisplay.getCardPoint());
		System.out.println("######  Couleur de la carte : " + cardToDisplay.getCardColor());
		System.out.println("######  Pouvoir de la carte : " + cardToDisplay.getCardPower().toUpperCase());
	}



	/***
	 * Display a given List of Cards
	 * <p>
	 * The list can be one of the following : Deeds (Oeuvre),Hand (Main du joueur), Future life, The Well (Source), Deck(Pile), etc...
	 * @param cardsList
	 *  <p>
	 *  a format string contains the information regarding the cards
	 * */
	public void displayListCards(LinkedList<KarmicCard> cardsList){

		int i=1;
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------------------------");

		for(KarmicCard iterCard:  cardsList) {
			if(iterCard.iscardIsVisible()) {
				System.out.println("#############################################################################");
				System.out.println("######  Carte Numero : " + i++);
				displayCard(iterCard);
				System.out.println("#############################################################################");
			}
			else {
				System.out.println("This Card is Hidden You cannot see it" );
			}
		}
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------------------------");

	}

	/***
	 * Allow you to pick a Card from a given List 
	 * @param cardsList
	 * <p> 
	 * represents for example deeds or Hand or The well, etc....
	 * @return  KarmicCard
	 * <p>
	 * a format string contains the information regarding the cards
	 * */
	public KarmicCard pickCardFromList(LinkedList<KarmicCard> cardsList) {

		int choice = 0;
		KarmicCard resultCard = null;

		if(!cardsList.isEmpty()) {
			System.out.println("__________________________________________________________________________________________________________________________________________________");
			System.out.println("____________________   <<<< PlayerName [ " + this.getPlayerName() + " ] >>>> CHOISISSEZ UNE CARTE DE LA LSITE SUIVANTE: ");

			displayListCards(cardsList);

			System.out.println("__________________________________________________________________________________________________________________________________________________");


			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			while (choice < 1 || choice > cardsList.size()) {
				System.out.println("___________________________________________________ ENTREZ VOTRE CHOIX  (1-" + cardsList.size() + "):  \n");

				if (scanner.hasNextInt()) {
					choice = scanner.nextInt();
				} else {
					scanner.next(); // Consume the non-integer input
					System.out.println("________________________________________ VEUILLEZ ENTRE UN CHOIX VALIDE ____________________________________");
				}
			}
			resultCard = cardsList.get(choice - 1);
		}

		return resultCard;

	}

	/***
	 * See the Exposed Deed of a given player, the player It can be the Current player or the Rival 
	 * @param player
	 * */
	public void seeExposedDeed(Player player) {

		if(!player.getPlayerDeeds().isEmpty()) {
			KarmicCard exposedCard = player.getPlayerDeeds().getLast();

			if(exposedCard != null) {
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("%%%% OEUVRE EXPOSEE de PlayerName %%%% " + player.getPlayerName() + "  %%%%");
				System.out.println("%%%%%%%%  Nom de la carte : " + exposedCard.getCardName());
				System.out.println("%%%%%%%%  Point de la carte : " + exposedCard.getCardPoint());
				System.out.println("%%%%%%%%  Couleur de la carte : " + exposedCard.getCardColor());
				System.out.println("%%%%%%%%  Pouvoir de la carte : " + exposedCard.getCardPower());
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			}
		}
		else {
			System.out.println("%%%% OEUVRE de l'Adversaire  PlayerName %%%% " + player.getPlayerName() + " EST VIDE %%%%");
		}	
	}

	/**
	 *  Display Information regarding the number of cards in The Well and The Personal Area Zone such a ( Deck (Pile), Deeds (Oeuvre), FutureLife (Vie Future))
	 *
	 * @param theGameBoard <p> current Game Board
	 * 
	 * */
	public void displayInformationCurrentGameBoard(GameBoard theGameBoard) {

		System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("--------------- Informations sur l'Espace de jeu (la SOURCE, la FOSSE et dans la zones de jeu personnelle du Joueur <<< PlayerName [ " + this.getPlayerName().toUpperCase() +  " ] >>> \n");
		System.out.println("..................................................    Milieu de la Table   .............................................................................");
		System.out.println("........       Nombre de cartes dans la SOURCE :  " + theGameBoard.getTheWell().size() + "  ........");
		System.out.println("........       Nombre de cartes dans la FOSSE :  " + theGameBoard.getTheRuins().size() + "  ........");
		System.out.println("........       Nombre de Anneaux Karmiques dans le plateau :  " + theGameBoard.getKarmicRings() + "  ........\n");
		System.out.println("..................................................    Zone Personnelle   .............................................................................");
		System.out.println("........       Nombre de cartes dans la MAIN :  " + this.getPlayerHand().size() + "  ........");
		System.out.println("........       Nombre de cartes dans l'OEUVRE :  " + this.getPlayerDeeds().size() + "  ........");
		System.out.println("........       Nombre de cartes dans la PILE :  " + this.getPlayerDeck().size() + "  ........");
		System.out.println("........       Nombre de cartes dans la VIE FUTURE :  " + this.getPlayerFutureLife().size() + "  ........");
		System.out.println("........       Nombre de mes ANNEAUX KARMIQUES : " + this.getnbrKarmicRings() + "  ........");
		System.out.println("........       NIVEAU KARMIQUE : " + this.getplayerKarmicLevel() + "  ........\n");
		System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/***
	 * 	Scoring Reincarnation (additionner les points de votre couleur la plus rentable)	
	 * 	Once the Player is dead (No cards in the Hand and Deck), we compute the points of the cards of each color
	 * 
	 * @return Total
	 * <p>
	 *  Total :  is the highest Color score point
	 * */

	public int scoringReincarnation() {

		int totalRedPoints =0 ;
		int totalBluepoints =0;
		int totalGreenPoints = 0;
		int resultMax =0;

		if(!this.getPlayerDeeds().isEmpty()) {

			for(KarmicCard iterCard:  this.getPlayerDeeds()) {

				switch (iterCard.getCardColor()) {
				case RED:
					totalRedPoints += iterCard.getCardPoint();
					break;
				case BLUE:
					totalBluepoints += iterCard.getCardPoint();
					break;
				case GREEN:
					totalGreenPoints += iterCard.getCardPoint();
					break;
				default:
					totalRedPoints += iterCard.getCardPoint();
					totalBluepoints += iterCard.getCardPoint();
					totalGreenPoints += iterCard.getCardPoint();
					break;
				}
			}

			List<Integer> tempList = Stream.of(totalRedPoints, totalBluepoints, totalGreenPoints).collect(Collectors.toList());
			resultMax = tempList.stream().max(Comparator.comparing(i -> i)).get();

			System.out.println("Max Score is  :::: " + resultMax);
		}

		return resultMax;
	}

	/***
	 * Copy All Deeds to The Ruins
	 * @param currentGameBoard
	 * @param currentPlayer
	 * 
	 * */
	public void oldLifeDeedsToRuins(GameBoard currentGameBoard, Player currentPlayer) {
		currentGameBoard.getTheRuins().addAll(currentPlayer.getPlayerDeeds());
		currentPlayer.getPlayerDeeds().clear();	
	}

	/***
	 * This function implements the Rebirth process when a player is dead and he did not Reach The TRANSCENDENCE
	 * @param currentGameBoard
	 * @param currentPlayer
	 * 
	 * */
	public void playerRebirth(GameBoard currentGameBoard, Player currentPlayer) {

		// Move the Deeds to the Ruins
		currentPlayer.oldLifeDeedsToRuins(currentGameBoard,currentPlayer);

		// get the size of the Future Life
		int handSize = currentPlayer.getPlayerFutureLife().size();

		// Draw all the cards from FL to the Hand
		for(int i=0; i < handSize; i++) {
			currentPlayer.drawCardToYourHand(currentPlayer.getPlayerFutureLife());
		}

		// Create the new Deck in case the nbr of card in Future Life does not exceed 6 cards, otherwise we leave the deck empty 
		if(handSize < 6) {
			for(int i=0; i < (6 - handSize) ; i++) {
				// Si la source est vide on la recree
				if(currentGameBoard.getTheWell().isEmpty()) {
					currentGameBoard.reCreateThewell();
				}
				currentPlayer.drawCardToYourDeck(currentGameBoard.getTheWell());
			}
		}

		System.out.println("-- Info Renaissance [ " + currentPlayer.getPlayerName().toUpperCase() + " ] :  votre MAIN (Hand) contient <<< " + currentPlayer.getPlayerHand().size() + " >>> Cartes");
		System.out.println("-- Info Renaissance [ " + currentPlayer.getPlayerName().toUpperCase() + " ] :  votre PILE (Deck) contient <<< " + currentPlayer.getPlayerDeck().size() + " >>> Cartes");
		System.out.println("-- Info Renaissance [ " + currentPlayer.getPlayerName().toUpperCase() + " ] :  vos ANNEAUX KARMIQUE  <<< " + currentPlayer.getnbrKarmicRings() + " >>>\n");

	}

	/***
	 *  Update the Karma Level
	 * @param scorePoint
	 * @param theGameBoard &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; current game board
	 * @param currentPlayer
	 * 
	 * */
	public void updateKarmaLevel(int scorePoint, GameBoard theGameBoard, Player currentPlayer) {

		int tempRing = currentPlayer.getnbrKarmicRings();
		int total = scorePoint + tempRing;

		/* Niveau BOUSIER  */
		if(total < 4 && KaramaLevel.DUNG_BEETLE.equals(currentPlayer.getplayerKarmicLevel())) {
			theGameBoard.receiveKarmicRing(currentPlayer);
		}
		else if (total >= 4 && KaramaLevel.DUNG_BEETLE.equals(currentPlayer.getplayerKarmicLevel())) {
			if (scorePoint < 4) {
				tempRing -= (4 - scorePoint);
				currentPlayer.setnbrKarmicRings(tempRing); 
				theGameBoard.setKarmicRings(theGameBoard.getKarmicRings()+(4 - scorePoint));
			}
			currentPlayer.setplayerKarmicLevel(KaramaLevel.SERPENT);
		}

		/* Niveau SERPENT  */
		else if(total < 5 && KaramaLevel.SERPENT.equals(currentPlayer.getplayerKarmicLevel())) {
			theGameBoard.receiveKarmicRing(currentPlayer);
		}
		else if(total >= 5 && KaramaLevel.SERPENT.equals(currentPlayer.getplayerKarmicLevel())) {
			if (scorePoint < 5) {
				tempRing -= (5 - scorePoint);
				currentPlayer.setnbrKarmicRings(tempRing); 
				theGameBoard.setKarmicRings(theGameBoard.getKarmicRings()+(5 - scorePoint));
			}
			currentPlayer.setplayerKarmicLevel(KaramaLevel.WOLF);
		}

		/* Niveau LOUP  */
		else if(total < 6 && KaramaLevel.WOLF.equals(currentPlayer.getplayerKarmicLevel())) {
			theGameBoard.receiveKarmicRing(currentPlayer);
		}
		else if(total >= 6 && KaramaLevel.WOLF.equals(currentPlayer.getplayerKarmicLevel())) {
			if (scorePoint < 6) {
				tempRing -= (6 - scorePoint);
				currentPlayer.setnbrKarmicRings(tempRing);
				theGameBoard.setKarmicRings(theGameBoard.getKarmicRings()+((6 - scorePoint)));
			}
			currentPlayer.setplayerKarmicLevel(KaramaLevel.MONKEY);
		}

		/* Niveau SINGE */
		else if(total < 7 && KaramaLevel.MONKEY.equals(currentPlayer.getplayerKarmicLevel())) {
			theGameBoard.receiveKarmicRing(currentPlayer);
		}
		else if(total >= 7 && KaramaLevel.MONKEY.equals(currentPlayer.getplayerKarmicLevel())) {
			currentPlayer.setplayerKarmicLevel(KaramaLevel.TRANSCENDENCE);
			currentPlayer.setisaTranscendent(true);
		}

	}

	/**
	 * This Function give the Choice to the Rival to put the Card in his Future Life or to the Ruins (NB : the card in question is the one played for its power)
	 * <p> 
	 * Or Choose Randomly to put the Card in the Future Life or to the Ruins if you play against a Robot Player
	 * @param card
	 * @param currentGameBoard
	 * @param playerOpponent
	 * */
	public void playerKarmicCost(KarmicCard card, GameBoard currentGameBoard, Player playerOpponent) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		System.out.println(" $$$$$$$$$$  DEBUT DU COUT KARMIQUE :::::: PlayerName <<< " + playerOpponent.getPlayerName().toUpperCase() + " >>>> "
				+ "VOTRE RIVALE <<<< " + this.getPlayerName().toUpperCase() + " >>>> A JOUER LE POUVOIR DE LA CARTE  #### " + card.getCardName() + " #### $$$$$$$$$$$$$$$$$ \n");
		
		int targetChoice = 0;
		
		if (playerOpponent instanceof HumanPlayer){
			while (targetChoice < 1 || targetChoice > 2) {
				System.out.println("$$$$$$$$$$$$  PlayerName <<< " + playerOpponent.getPlayerName().toUpperCase() + " >>>> QUE SOUHAITEZ-VOUS FAIRE DE CETTE CARTE $$$$$$$$$$$$\r\n" + 
						" $$$$$$    1-	Recuperer la carte dans votre Vie Future \r\n" + 
						" $$$$$$    2-	Defausser La carte \r\n" + 
						"\n");

				@SuppressWarnings("resource")
				Scanner scTarget = new Scanner(System.in);
				if (scTarget.hasNextInt()) {
					targetChoice = scTarget.nextInt();
				} else {
					scTarget.next(); // Consume the non-integer input
					System.out.println(" $$$$$     VEUILLEZ ENTRE UN CHIFFRE    $$$$$$\n");
				}
			}	
		}
		else if(playerOpponent instanceof RobotPlayer) {
			Random random = new Random();
			targetChoice = random.nextInt(2) + 1;
		}
		
		switch(targetChoice) {
		case 1:
			card.setcardIsVisible(false);
			playerOpponent.moveCardFromListToAnother(card, currentGameBoard.getTheTable(), playerOpponent.getPlayerFutureLife());
			System.out.println("$$$$$$  JOUEUR ADVERSE PlayerName <<< " + playerOpponent.getPlayerName() + " >>> A PRIS LA CARTE  ####  " + card.getCardName().toUpperCase() + "  #### DANS SA VIE FUTURE $$$$$$$$$\n");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$  FIN DU COUT KARMIQUE $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
			break;
		case 2:
			playerOpponent.moveCardFromListToAnother(card, currentGameBoard.getTheTable(), currentGameBoard.getTheRuins());
			System.out.println("$$$$$$ JOUEUR ADVERSE  PlayerName <<< " + playerOpponent.getPlayerName() + "  >>>  A DEFAUSSER LA CARTE ####  " + card.getCardName() + " #### $$$$$$$$$$$$\n");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$  FIN DU COUT KARMIQUE $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
			break;
		}
	}
	
	/**
	 * This Function allow to each player of drawing a card and playing a card.
	 * 
	 * @param currentGameBoard &nbsp;&nbsp;&nbsp; an object from the GameBoard class see the class for more information
	 * @param playerOpponent
	 * @param firstPassage
	 * 
	 **/	
	public abstract void playCard(GameBoard currentGameBoard, Player playerOpponent, boolean firstPassage, GameManager currentGameManager);


	/******************************************************************************************************************************/
	/******************************************************************************************************************************/

	/***   Getters and Setters  **/


	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	public boolean isisaTranscendent() {
		return isaTranscendent;
	}
	public void setisaTranscendent(boolean isaTranscendent) {
		this.isaTranscendent = isaTranscendent;
	}
	
	/**
	 * @return the hasCompletedTurn
	 */
	public boolean isHasCompletedTurn() {
		return hasCompletedTurn;
	}

	/**
	 * @param hasCompletedTurn the hasCompletedTurn to set
	 */
	public void setHasCompletedTurn(boolean hasCompletedTurn) {
		this.hasCompletedTurn = hasCompletedTurn;
	}

	public int getnbrKarmicRings() {
		return nbrKarmicRings;
	}
	public void setnbrKarmicRings(int nbrKarmicRings) {
		this.nbrKarmicRings = nbrKarmicRings;
	}


	public KaramaLevel getplayerKarmicLevel() {
		return playerKarmicLevel;
	}
	public void setplayerKarmicLevel(KaramaLevel playerKarmicLevel) {
		this.playerKarmicLevel = playerKarmicLevel;
	}


	public LinkedList<KarmicCard> getPlayerHand() {
		return playerHand;
	}
	public void setPlayerHand(LinkedList<KarmicCard> playerHand) {
		this.playerHand = playerHand;
	}


	public LinkedList<KarmicCard> getPlayerDeck() {
		return playerDeck;
	}
	public void setPlayerDeck(LinkedList<KarmicCard> playerDeck) {
		this.playerDeck = playerDeck;
	}


	public LinkedList<KarmicCard> getPlayerDeeds() {
		return playerDeeds;
	}
	public void setPlayerDeeds(LinkedList<KarmicCard> playerDeeds) {
		this.playerDeeds = playerDeeds;
	}


	public LinkedList<KarmicCard> getPlayerFutureLife() {
		return playerFutureLife;
	}
	public void setPlayerFutureLife(LinkedList<KarmicCard> playerFutureLife) {
		this.playerFutureLife = playerFutureLife;
	}


}
