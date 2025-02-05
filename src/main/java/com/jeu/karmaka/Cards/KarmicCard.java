/**
 * This Class represent the Card Object as defined in the game
 */
package com.jeu.karmaka.Cards;


import java.io.Serializable;

import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;
import com.jeu.karmaka.Players.Player;

/**
 * @author Moughit
 *
 */
public abstract class KarmicCard implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7641413389886998238L;
	protected String cardName;
	protected int cardPoint;
	protected ColorCard cardColor;
	protected String cardPower;
	protected boolean cardIsVisible;  // false for Hidden Face card , True for Visible Face card
	
	
	/************************************************ Constructor ************************************************/

	public KarmicCard(String cardName, int cardPoint, ColorCard cardColor, String cardPower, boolean cardIsVisible) {
		this.cardName = cardName;
		this.cardPoint = cardPoint;
		this.cardColor = cardColor;
		this.cardPower = cardPower;
		this.cardIsVisible = cardIsVisible;
	}
	
	/******************************************************************************************************************************/
	/*****************************               Methods and Features             ***************************************************/
	
	/**
	 * Copy a Card in another Temporary Card
	 * <p>
	 * @return copyTempoCard  &nbsp;&nbsp;&nbsp; a copy of this card
	 * 
	 * */
	
	public KarmicCard copyCard() {
		KarmicCard copyTempoCard = this;
		return copyTempoCard;
	}
	
	
	/**
	 * Allow you to activate the power card
	 * <p>
	 * Each Card has it own power and a given name and Point
	 * @param currentGameBoard
	 * @param currentPlayer : define the player who activate the power card
	 * @param playerOpponent : define the player against you play
	 * 
	 * */
	public abstract void activatePowerCard(GameBoard currentGameBoard, Player currentPlayer, Player playerOpponent, GameManager currentGameManager);
	
	/**
	 * toString method for KarmicCard Class
	 * */
	public String toString() {
		return "Card Name ::: "  + this.cardName.toString() + " || Point :: " +  this.cardPoint 
				+ " || Color :::  " +  this.cardColor + " || Power ::: " + this.cardPower + " || card Face is Visible :: " + this.cardIsVisible  + " || \n\n";
	}
	
	/******************************************************************************************************************************/
	/******************************************************************************************************************************/

	/***   Getters and Setters  **/
	
	public String getCardName() {
		return cardName;
	}


	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


	public int getCardPoint() {
		return cardPoint;
	}


	public void setCardPoint(int cardPoint) {
		this.cardPoint = cardPoint;
	}


	public ColorCard getCardColor() {
		return cardColor;
	}


	public void setCardColor(ColorCard cardColor) {
		this.cardColor = cardColor;
	}


	public String getCardPower() {
		return cardPower;
	}


	public void setCardPower(String cardPower) {
		this.cardPower = cardPower;
	}


	public boolean iscardIsVisible() {
		return cardIsVisible;
	}


	public void setcardIsVisible(boolean cardIsVisible) {
		this.cardIsVisible = cardIsVisible;
	}
}
