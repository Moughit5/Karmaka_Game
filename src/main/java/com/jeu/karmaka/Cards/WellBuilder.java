/**
 * This class allow us to build the Well(La source) cards that the game need
 * This is our Builder Pattern
 */
package com.jeu.karmaka.Cards;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author Moughit
 *
 */
public class WellBuilder implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4856165003378948191L;
	private LinkedList<KarmicCard> listTheWell;

	public WellBuilder() {
		this.listTheWell = new LinkedList<KarmicCard>();
	}

	/** this add the card Embody for 5 times*/
	public WellBuilder addEmbody(int nbr) {
		if(nbr == 5 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Embody(false));
			}	 
		}
		return this;
	}	

	/** this add the card Denial for 3 times */
	public WellBuilder addDenial(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Denial(false));
			}	 
		}
		return this;
	}

	/** this add the card AnotherDay for 3 times */
	public WellBuilder addAnotherDay(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new AnotherDay(false));
			}	 
		}
		return this;
	}
	/** this add the card Crisis for 3 times */
	public WellBuilder addCrisis(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Crisis(false));
			}	 
		}
		return this;
	}
	/** this add the card Transmigrate for 3 times */
	public WellBuilder addTransmigrate(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Transmigrate(false));
			}	 
		}
		return this;
	}	
	/** this add the card Peek for 3 times */
	public WellBuilder addPeek(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Peek(false));
			}	 
		}
		return this;
	}		
	/** this add the card Destiny for 3 times */
	public WellBuilder addDestiny(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Destiny(false));
			}	 
		}
		return this;
	}	
	/** this add the card Dwindle for 3 times */
	public WellBuilder addDwindle(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Dwindle(false));
			}	 
		}
		return this;
	}	
	/** this add the card Hell Heart for 3 times */
	public WellBuilder addHellHeart(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new HellHeart(false));
			}	 
		}
		return this;
	}	
	/** this add the card Jubilee for 2 times **/
	public WellBuilder addJubilee(int nbr) {
		if(nbr == 2 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Jubilee(false));
			}	 
		}
		return this;
	}	
	/** this add the card Longevity for 3 times */
	public WellBuilder addLongevity(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Longevity(false));
			}	 
		}
		return this;
	}	
	/** this add the card Mimic for 2 times */
	public WellBuilder addMimic(int nbr) {
		if(nbr == 2 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Mimic(false));
			}	 
		}
		return this;
	}	
	/** this add the card Panic for 3 times */
	public WellBuilder addPanic(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Panic(false));
			}	 
		}
		return this;
	}	
	/** this add the card Recycle for 3 times */
	public WellBuilder addRecycle(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Recycle(false));
			}	 
		}
		return this;
	}	
	/** this add the card Roulette for 3 times */
	public WellBuilder addRoulette(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Roulette(false));
			}	 
		}
		return this;
	}	
	/** this add the card Salvage for 3 times */
	public WellBuilder addSalvage(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Salvage(false));
			}	 
		}
		return this;
	}	
	/** this add the card Sowing for 3 times */
	public WellBuilder addSowing(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Sowing(false));
			}	 
		}
		return this;
	}	
	/** this add the card Spite for 2 times */
	public WellBuilder addSpite(int nbr) {
		if(nbr == 2 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Spite(false));
			}	 
		}
		return this;
	}
	/** this add the card Stolen Dreams for 3 times */
	public WellBuilder addStolenDreams(int nbr) {
		if(nbr == 3 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new StolenDreams(false));
			}	 
		}
		return this;
	}
	/** this add the card Swindle for 2 times */
	public WellBuilder addSwindle(int nbr) {
		if(nbr == 2 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Swindle(false));
			}	 
		}
		return this;
	}
	/** this add the card Thievery for 2 times */
	public WellBuilder addThievery(int nbr) {
		if(nbr == 2 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Thievery(false));
			}	 
		}
		return this;
	}
	/** this add the card Vengeance for 2 times */
	public WellBuilder addVengeance(int nbr) {
		if(nbr == 2 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Vengeance(false));
			}	 
		}
		return this;
	}
	/** this add the card Voyage for 2 times */
	public WellBuilder addVoyage(int nbr) {
		if(nbr == 2 ){
			for(int i=0; i<nbr;i++) {
				listTheWell.add(new Voyage(false));
			}	 
		}
		return this;
	}
	
	/****************************************************************************************************************************************************/
	/**
	 * Build the well for the game it must contains 64 cards
	 * */

	public LinkedList<KarmicCard> build() {
		return listTheWell;
	}

}
