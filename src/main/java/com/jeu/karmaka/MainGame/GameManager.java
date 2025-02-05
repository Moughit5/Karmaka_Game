/**
 * This Class responsible for managing various game components, such as Players, the Well, the Ruins, the Karmic Ladder, Karmic Rings, and the Turn Structure
 */
package com.jeu.karmaka.MainGame;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jeu.karmaka.KarmakaMainGame;
import com.jeu.karmaka.Cards.WellBuilder;
import com.jeu.karmaka.Ladder.KaramaLevel;
import com.jeu.karmaka.Players.HumanPlayer;
import com.jeu.karmaka.Players.Player;
import com.jeu.karmaka.Players.RobotPlayer;

/**
 * @author Moughit
 *
 */
public class GameManager implements Serializable{
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = -8295531391329465205L;
	private List<Player> players;
	private GameBoard currentGameBoard;
	private boolean isGameOver;
	private boolean isHuman;
    private int currentPlayerIndex;
    private int turnCounter;
    boolean tempCheckLoad;
    
    
    
    /************************************** Constructor *************************************************/
	public GameManager(String playerNameOne, String playerNameTwo,int choice) {
		// create the players
		this.players = new ArrayList<>();
		
		switch(choice) {
		case 1:
			// Humain VS Humain
			players.add(new HumanPlayer(playerNameOne,0,KaramaLevel.DUNG_BEETLE));
	        players.add(new HumanPlayer(playerNameTwo,0,KaramaLevel.DUNG_BEETLE));
	        isHuman = true;
	        
			break;
		case 2:
			// Humain VS ROBOT
			players.add(new HumanPlayer(playerNameOne,0,KaramaLevel.DUNG_BEETLE));
	        players.add(new RobotPlayer(playerNameTwo,0,KaramaLevel.DUNG_BEETLE));
	        isHuman = false;
			break;
		}
		
        // create The well and The Ruins and The Play Area
        WellBuilder varBuild = new WellBuilder();
        this.currentGameBoard = new GameBoard(varBuild);
        
        // for the progression game
        this.isGameOver = false;
        this.tempCheckLoad = false;
        this.currentPlayerIndex = 0;
        this.turnCounter = 1;
		
	}
    
	
	/******************************************************************************************************************************/
	/*****************************               Methods and Features    ************************************************/
	
	public void startGame(boolean newOrLoadGame) {
		
		if(newOrLoadGame) {
			// Create opening Decks and Hands:
			this.currentGameBoard.dealCardsToPlayers(players);
		}
		else {
			// Pour le test du Load Game
			this.tempCheckLoad = true;
		}
		
		while (!this.isGameOver) {
			// choose the first player to start
			Player currentPlayer = this.players.get(currentPlayerIndex);
			
			System.out.println("\n\n----------------------------------<<<<<< KARMAKA MATCH, TOUR NUMERO : " + turnCounter  +  " >>>>>>>------------------------------------------------");
			// Player Turn Structure
			this.playTurn(currentPlayer,currentGameBoard,newOrLoadGame);
			
			
			// Check if the Game end
			if (this.checkEndGameConditions(currentPlayer)) {
				System.out.println("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE Have A Winnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnner...... ");
				System.out.println("Player name :::: " + currentPlayer.getPlayerName() + " :::::::  win the Game Match");
				this.isGameOver = true;
				break;
			}
			else {
				// Move to the next player
				this.currentPlayerIndex = (this.currentPlayerIndex + 1) % players.size();
			}
			
			// Automatique sauvegrade
			try {
				System.out.println("Automatique sauvegrade");
				if(this.isHuman) {
					KarmakaMainGame.saveGame(this, "karmmakaGameStateHuman.sav");
				}
				else {
					KarmakaMainGame.saveGame(this, "karmmakaGameStateRobot.sav");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			turnCounter++;
		}
		
	}
	
	
	/**
	 * This Method code the logic for a player turn <p>
	 * Checking the Karmic point for the KarmicLevel, number of the karmic rings, Rebirth if it the case
	 * 
	 * @param currentPlayer
	 * @param theGameBoard
	 * **/
	private void playTurn(Player currentPlayer,GameBoard theGameBoard,boolean newOrLoadGame) {
	
		
		
		// retrieve the index of the next player
		int tempIndex = (this.currentPlayerIndex + 1) % this.players.size();
		Player rivalPlayer = players.get(tempIndex);
		
		// check if we still have any card on the Well (La source) if the Well is empty we recreate it from the Ruins
		if(theGameBoard.getTheWell().isEmpty()) {
			theGameBoard.reCreateThewell();
		}
		
		// Reincarnation Or TRANSCENDENCE
		if(currentPlayer.getPlayerHand().isEmpty() && currentPlayer.getPlayerDeck().isEmpty()) {
			System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("---------  REINCARNATION DU JOUEUR  <<<< " + currentPlayer.getPlayerName().toUpperCase()  + " >>>> ----------\n");
			System.out.println("--- LE NIVEAU KARMIQUE DU JOUEUR  <<<< " + currentPlayer.getPlayerName().toUpperCase()  + " >>>>  AVANT MARQUAGE DES POINT ET REINCARNATION <<<< " + currentPlayer.getplayerKarmicLevel() + " >>>> -------------------------------\n");
			
			int karmakaScorePoints = currentPlayer.scoringReincarnation();
			currentPlayer.updateKarmaLevel(karmakaScorePoints, theGameBoard, currentPlayer);
			
			/* On distribue les cartes dans le cas ou le joueur n'a pas atteint la Transcendance Sinon on ne fait rien car le joueur a gagné */
		
			if(!currentPlayer.getplayerKarmicLevel().equals(KaramaLevel.TRANSCENDENCE)){
				System.out.println("----------CREATION DE LA NOUVELLE # PILE ET MAIN # APRES RENAISSANCE DU PlayerName <<<< " + currentPlayer.getPlayerName().toUpperCase() + " >>>\n");
				currentPlayer.playerRebirth(theGameBoard,currentPlayer);
			}
			
			System.out.println("--- LE NIVEAU KARMIQUE DU JOUEUR  <<<< [ " + currentPlayer.getPlayerName().toUpperCase()  + " ] >>>>  APRES MARQUAGE DES POINT ET REINCARNATION <<<< " + currentPlayer.getplayerKarmicLevel() + " >>>> ------------------------------\n-");
			
			System.out.println("---------  FIN DE REINCARNATION DU JOUEUR   <<<< " + currentPlayer.getPlayerName().toUpperCase()  + " >>>> ----------\n");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			
			currentPlayer.setHasCompletedTurn(true);
			rivalPlayer.setHasCompletedTurn(false);
			
		}
		else {
			// Au moment du chargement d'une partie, ce test empéche le joueur de tiré une carte de la pile car la Sauvegarde se fait aprés avoir tiré une carte
			// sinon il tire une carte de la pile
			if(!newOrLoadGame && !currentPlayer.isHasCompletedTurn() && this.tempCheckLoad) {
				currentPlayer.playCard(currentGameBoard, rivalPlayer, false,this);
				this.tempCheckLoad =false;
			}
			else if(!newOrLoadGame && !currentPlayer.isHasCompletedTurn() && !this.tempCheckLoad) {
				currentPlayer.playCard(currentGameBoard, rivalPlayer, true,this);
			}
			else {
				currentPlayer.playCard(currentGameBoard, rivalPlayer, true,this);
			}
		}	
	}
	
	
	/**
	 * Check the condition to ending a Game Match
	 * @param player
	 * **/
	private boolean checkEndGameConditions(Player player) {
        return player.isisaTranscendent();
    }


	
	
	/******************************************************************************************************************************/
	/******************************************************************************************************************************/

	/***   Getters and Setters  **/
	
	
	/**
	 * @return the isHuman
	 */
	public boolean isHuman() {
		return isHuman;
	}
	/**
	 * @param isHuman the isHuman to set
	 */
	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}


	/**
	 * @return the turnCounter
	 */
	public int getTurnCounter() {
		return turnCounter;
	}

	/**
	 * @param turnCounter the turnCounter to set
	 */
	public void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
	}
	
}
