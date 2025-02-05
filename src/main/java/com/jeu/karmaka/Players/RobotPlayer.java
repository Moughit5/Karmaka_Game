/**
 * This Class Represent a Robot Player
 */
package com.jeu.karmaka.Players;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import com.jeu.karmaka.Cards.ColorCard;
import com.jeu.karmaka.Cards.KarmicCard;
import com.jeu.karmaka.Ladder.KaramaLevel;
import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;

/**
 * @author Moughit
 *
 */
public class RobotPlayer extends Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5910714107769707965L;

	enum DecisionType {
		DRAW_CARD, MOVE_TO_FUTURE_LIFE, PLAY_CARD_FOR_POINT, PLAY_CARD_FOR_POWER;
	}

	private Map<ColorCard, Integer> colorPoints;

	
	/************************************** Constructor *************************************************/
	
	public RobotPlayer(String playerName, int nbrKarmicRings, KaramaLevel playerKarmicLevel) {
		super(playerName, nbrKarmicRings, playerKarmicLevel);
		 colorPoints = new HashMap<>();
	}

	
	/******************************************************************************************************************************/
	/*****************************               Methods and Features             ***************************************************/
	@Override
	public void playCard(GameBoard currentGameBoard, Player playerOpponent, boolean newTurn, GameManager currentGameManager) {
		
		KarmicCard cardToplay= null;
		
		if(newTurn) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("--------------------------------------- DEBUT DU TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUER <<< PlayerName : " + this.getPlayerName() + "   >>>> -----------");
			drawCardToYourHand(this.getPlayerDeck());
		}
		else {
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("---------------------------------------  TOUR N° [ " + currentGameManager.getTurnCounter() + " ]  N'EST PAS ENCORE TERMINER DU JOUER  <<< PlayerName : " + this.getPlayerName() + "   >>>>  --------");
		}
		
		
		int maxPoint = 0;
		ColorCard bestColorCardToPlay = null;
		
		Map<ColorCard, Integer> bestColorPoint = this.findBestColorStrategyPoints();
		for (Map.Entry<ColorCard, Integer> entry : bestColorPoint.entrySet()) {
			maxPoint = entry.getValue();
			bestColorCardToPlay = entry.getKey();
		}
		System.out.println("BEST COLOR ROBOT :   +++" + bestColorCardToPlay.toString());
		
		
		if(this.getPlayerHand().size() == 1) {
			cardToplay = this.getPlayerHand().getLast();
		}
		else if(this.getPlayerHand().size() > 1) {
			Random random = new Random();
			cardToplay = this.getPlayerHand().get(random.nextInt(this.getPlayerHand().size()));
		}
		
		System.out.println("Robot Card to Play " + cardToplay.getCardColor());
		
		
		if(cardToplay != null && cardToplay.getCardColor().equals(ColorCard.MOSAIQUE)) {
			executeDecision(DecisionType.PLAY_CARD_FOR_POINT,currentGameBoard,playerOpponent,currentGameManager,cardToplay);
			System.out.println("***** ROBOT :  #####!!!!!#### Play @@@@@hjkze@((mlk Points iiiiiinnn deeeedds : (  "
					+ cardToplay.getCardName() + " ||| " + cardToplay.getCardPoint() + " ||| "
					+ cardToplay.getCardPower() + " ||| " + cardToplay.getCardColor() + " )");
		}
		else if(cardToplay != null && canEvolve(maxPoint, this) && cardToplay.getCardColor().equals(bestColorCardToPlay) ) {
			executeDecision(DecisionType.PLAY_CARD_FOR_POINT,currentGameBoard,playerOpponent,currentGameManager,cardToplay);
			System.out.println("***** ROBOT :  #####!!!!!#### Play @@@@@hjkze@((mlk Points iiiiiinnn deeeedds : (  "
					+ cardToplay.getCardName() + " ||| " + cardToplay.getCardPoint() + " ||| "
					+ cardToplay.getCardPower() + " ||| " + cardToplay.getCardColor() + " )");
		}
		else if(cardToplay != null && cardToplay.getCardColor().equals(bestColorCardToPlay) ) {
			executeDecision(DecisionType.PLAY_CARD_FOR_POINT,currentGameBoard,playerOpponent,currentGameManager,cardToplay);
			System.out.println("***** ROBOT :  #####!!!!!#### Play @@@@@hjkze@((mlk Points iiiiiinnn deeeedds : (  "
					+ cardToplay.getCardName() + " ||| " + cardToplay.getCardPoint() + " ||| "
					+ cardToplay.getCardPower() + " ||| " + cardToplay.getCardColor() + " )");
		}
		else {
			executeDecision(DecisionType.MOVE_TO_FUTURE_LIFE,currentGameBoard,playerOpponent,currentGameManager,cardToplay);
		}
		
		System.out.println("....... FIN TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE A LA FIN DE VOTRE TOUR EST : \n");
		// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
		this.displayInformationCurrentGameBoard(currentGameBoard);
		
		System.out.println("\n\n");	
	}

	/**
	 * Execute the chosen decision for the Robot player
	 * 
	 * @param decision
	 * @param currentGameBoard
	 * @param playerOpponent
	 * @param firstPassage
	 * @param currentGameManager
	 * @param cardToplay
	 * 
	 **/
	private void executeDecision(DecisionType decision, GameBoard currentGameBoard, Player playerOpponent, GameManager currentGameManager, KarmicCard cardToplay) {
		switch (decision) {
		case MOVE_TO_FUTURE_LIFE:
			this.moveCardFromListToAnother(cardToplay, this.getPlayerHand(), this.getPlayerFutureLife());
			this.setHasCompletedTurn(true);
			playerOpponent.setHasCompletedTurn(false);
			break;
		case DRAW_CARD:
			// Pas encore Implementé
			//drawCardToYourHand(srcList);
			break;
		case PLAY_CARD_FOR_POINT:
			this.moveCardFromListToAnother(cardToplay, this.getPlayerHand(), this.getPlayerDeeds());
			this.setHasCompletedTurn(true);
			playerOpponent.setHasCompletedTurn(false);
			break;
		case PLAY_CARD_FOR_POWER:
			//cardToplay.activatePowerCard(currentGameBoard, this, playerOpponent, currentGameManager);
			break;
		}
	}
	/**
	 * 
	 * AI logic to choose the best Color card to play according to the Highest points colors 
	 * @return result
	 *   <p>
	 *   a map of color and the total points
	 **/
	public Map<ColorCard, Integer> findBestColorStrategyPoints() {
		
		 // This map will hold the total points for each color present in the hand.
	    // Initialize the map with all colors set to 0 points.
	    colorPoints.put(ColorCard.BLUE, 0);
	    colorPoints.put(ColorCard.RED, 0);
	    colorPoints.put(ColorCard.GREEN, 0);
	    int mosaicPoints = 0;
	    
	    // Calculate the total points for each color in the hand.
	    for (KarmicCard card : this.getPlayerHand()) {
	    	if (card.getCardColor() == ColorCard.MOSAIQUE) {
	    		 mosaicPoints += card.getCardPoint();
	    	}
	    	else {
	    		ColorCard cardColor = card.getCardColor();
	 	        int currentPoints = colorPoints.getOrDefault(cardColor, 0);
	 	        colorPoints.put(cardColor, currentPoints + card.getCardPoint());
	    	}
	    }
	    
	    // Adding the total points for each color in the deeds.
	    for (KarmicCard card : this.getPlayerDeeds()) {
	    	if (card.getCardColor() == ColorCard.MOSAIQUE) {
	    		 mosaicPoints += card.getCardPoint();
	    	}
	    	else {
	    		ColorCard cardColor = card.getCardColor();
	 	        int currentPoints = colorPoints.getOrDefault(cardColor, 0);
	 	        colorPoints.put(cardColor, currentPoints + card.getCardPoint());
	    	}
	    }
	    
	    final int maxMosaicPoints = mosaicPoints;
	    // Add mosaic points to each color total in the map
	    colorPoints.replaceAll((color, points) -> points + maxMosaicPoints);
	    
	    // Find the color with the highest total points.
	    ColorCard bestColor = Collections.max(colorPoints.entrySet(), Map.Entry.comparingByValue()).getKey();
	    
	    Map<ColorCard, Integer> result = new HashMap<>();
	    result.put(bestColor, colorPoints.get(bestColor));
	    
		return result;
	}
	
	
	/***
	 * This function check if the robot can evolve through The Karmic Ladder 
	 * @param  scorePoint
	 * @param  robotPlayer
	 * */
	private boolean canEvolve(int scorePoint, Player robotPlayer) {

		boolean result = false;
		int total = scorePoint + robotPlayer.getnbrKarmicRings();

		/* Niveau BOUSIER */
		if (total >= 4 && KaramaLevel.DUNG_BEETLE.equals(robotPlayer.getplayerKarmicLevel())) {
			result = true;
		}
		/* Niveau SERPENT */
		else if (total >= 5 && KaramaLevel.SERPENT.equals(robotPlayer.getplayerKarmicLevel())) {
			result = true;
		}
		/* Niveau LOUP */
		else if (total >= 6 && KaramaLevel.WOLF.equals(robotPlayer.getplayerKarmicLevel())) {
			result = true;
		}
		/* Niveau SINGE */
		else if (total >= 7 && KaramaLevel.MONKEY.equals(robotPlayer.getplayerKarmicLevel())) {
			result = true;
		}

		return result;
	}

	/******************************************************************************************************************************/
	/******************************************************************************************************************************/

	/***   Getters and Setters  **/

	/**
	 * @return the colorPoints
	 */
	public Map<ColorCard, Integer> getColorPoints() {
		return colorPoints;
	}
	/**
	 * @param colorPoints the colorPoints to set
	 */
	public void setColorPoints(Map<ColorCard, Integer> colorPoints) {
		this.colorPoints = colorPoints;
	}

}
