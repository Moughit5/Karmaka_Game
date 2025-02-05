/**
 * This class represent the Play area such The Well(Source) The Ruins(Fosse) The table and The Karmic Rings
 */
package com.jeu.karmaka.MainGame;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.jeu.karmaka.Cards.KarmicCard;
import com.jeu.karmaka.Cards.WellBuilder;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 *
 */
public class GameBoard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4684843711434300971L;
	private LinkedList<KarmicCard> theWell; // La Source
	private LinkedList<KarmicCard> theRuins; // La Fosse
	private LinkedList<KarmicCard> theTable; // La Table
	private int karmicRings;
	
	
	
	/************************************** Constructor *************************************************/
	
	public GameBoard(WellBuilder objectWellBuilder) {
		
		this.buildShuffleTheWell(objectWellBuilder);
		this.theRuins =  new LinkedList<KarmicCard>();
		this.theTable =  new LinkedList<KarmicCard>();
		this.karmicRings = 12;
		
	}
	
	/******************************************************************************************************************************/
	/*****************************               Methods and Features             ***************************************************/
	
	
	/***
	 * This Function Create the Well from the cards and shuffle the cards to Set Up a Game Match
	 * @param objectWellBuilder
	 * 
	 * */
	
	public void buildShuffleTheWell(WellBuilder objectWellBuilder)
	{
		// Create the well must contains 64 Cards
		this.setTheWell(objectWellBuilder.addAnotherDay(3).addCrisis(3).addDenial(3).addDestiny(3).addDwindle(3).addEmbody(5).addHellHeart(3).addJubilee(2).
				addLongevity(3).addMimic(2).addPanic(3).addPeek(3).addRecycle(3).addRoulette(3).addSalvage(3).addSowing(3).addSpite(2).addStolenDreams(3).
				addSwindle(2).addThievery(2).addTransmigrate(3).addVengeance(2).addVoyage(2).build());
		
		// Shuffle is called to have random order elements in the list
		Collections.shuffle(this.getTheWell());
	}

	/***
	 * This Function allow to a Player to receive Karmic Ring
	 * @param currentPlayer
	 * 
	 * */
	public void receiveKarmicRing(Player currentPlayer) {
		int tempNbrRings=0;
		if(this.karmicRings > 0 && this.karmicRings <= 12) {
			
			// add ring to player consider that the player may have a rings in it's own personnal space
			tempNbrRings +=1;
			tempNbrRings += currentPlayer.getnbrKarmicRings();
			currentPlayer.setnbrKarmicRings(tempNbrRings);
			
			// Subtract a ring from the total rings
			tempNbrRings = this.karmicRings - 1 ;
			this.setKarmicRings(tempNbrRings);
			
		}
	}
	
	/***
	 * Recreate The Well when it is empty, shuffle the cards in the Ruins (except the top 3 cards, which remain in the Ruins) into a new Well
	 * 
	 * 
	 * */
	public void reCreateThewell() {
		
		if(!this.getTheRuins().isEmpty() && this.getTheRuins().size() >= 3) {
			
			// Calculer l'index pour la separation de la liste
			int splitIndex = Math.max(this.getTheRuins().size() - 3, 0);

			// Copier les elements à l'eception des 3 dernieres cartes
			LinkedList<KarmicCard> tempCopiedList = new LinkedList<KarmicCard>(this.getTheRuins().subList(0, splitIndex));
			
			this.setTheWell(tempCopiedList);
			// mélangez toutes les cartes
			Collections.shuffle(this.getTheWell());

			// Supprimez les mêmes éléments de la Fosse
			this.getTheRuins().subList(0, splitIndex).clear();
		}
		else {
			System.out.println("----- Info GameBoard la Fosse est vide ou contient moins de 3 Cartes ");
		}
		
		
	}
	
	
	
	/***
	 * Deal cards to the players, move them one at a time (Distribuer les cartes une par une à tour de role)
	 * @param players
	 * 
	 * */
	public void dealCardsToPlayers(List<Player> players) {
		int size = players.size();
		// Deal cards one at a time
        for (int i = 0; i < (4 * size) ; i++) {
            Player currentPlayer = players.get(i % size);
            currentPlayer.drawCardToYourHand(this.getTheWell());
        }
        
        for (int i = 0; i < (2 * size); i++) {
            Player currentPlayer = players.get(i % size);
            currentPlayer.drawCardToYourDeck(this.getTheWell());
        }
	}

	/***********************************   Getters and Setters  **********************************/
	
	/**
	 * @return the theWell
	 */
	public LinkedList<KarmicCard> getTheWell() {
		return theWell;
	}

	/**
	 * @param theWell the theWell to set
	 */
	public void setTheWell(LinkedList<KarmicCard> theWell) {
		this.theWell = theWell;
	}

	/**
	 * @return the theRuins
	 */
	public LinkedList<KarmicCard> getTheRuins() {
		return theRuins;
	}

	/**
	 * @param theRuins the theRuins to set
	 */
	public void setTheRuins(LinkedList<KarmicCard> theRuins) {
		this.theRuins = theRuins;
	}

	/**
	 * @return the theTable
	 */
	public LinkedList<KarmicCard> getTheTable() {
		return theTable;
	}

	/**
	 * @param theTable the theTable to set
	 */
	public void setTheTable(LinkedList<KarmicCard> theTable) {
		this.theTable = theTable;
	}

	/**
	 * @return the karmicRings
	 */
	public int getKarmicRings() {
		return karmicRings;
	}

	/**
	 * @param karmicRings the karmicRings to set
	 */
	public void setKarmicRings(int karmicRings) {
		this.karmicRings = karmicRings;
	}
	
	

}
