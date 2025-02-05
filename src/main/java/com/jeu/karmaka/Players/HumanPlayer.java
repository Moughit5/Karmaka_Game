/**
 * This Class Represent a Human Player
 */
package com.jeu.karmaka.Players;


import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import com.jeu.karmaka.KarmakaMainGame;
import com.jeu.karmaka.Cards.KarmicCard;
import com.jeu.karmaka.Ladder.KaramaLevel;
import com.jeu.karmaka.MainGame.GameBoard;
import com.jeu.karmaka.MainGame.GameManager;

/**
 * @author Moughit
 *
 */
public class HumanPlayer extends Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7709966408785327174L;

	/************************************** Constructor *************************************************/
	public HumanPlayer(String playerName, int nbAnneauxKarmiques, KaramaLevel niveauKarmique) {
		super(playerName, nbAnneauxKarmiques, niveauKarmique);
	}

	/******************************************************************************************************************************/
	/*****************************               Methods and Features             ***************************************************/
	@Override
	public void playCard(GameBoard currentGameBoard, Player playerOpponent, boolean newTurn, GameManager currentGameManager){
		
		int choice = 0;
		// Piocher de la pile 
		if(newTurn) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("--------------------------------------- DEBUT DU TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUER <<< PlayerName : " + this.getPlayerName() + "   >>>> \n");
			drawCardToYourHand(this.getPlayerDeck());	
		}
		else {
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("---------------------------------------  TOUR N° [ " + currentGameManager.getTurnCounter() + " ]  N'EST PAS ENCORE TERMINER DU JOUER  <<< PlayerName : " + this.getPlayerName() + "   >>>>  \n");
		}
		
		
		// Afficher la main du joueur et donne le choix au joueur pour tirer une carte qu'il va jouer
		System.out.println("\n----------------------- VOTRE MAIN CONTIENT LES CARTES SUIVANTES  <<<< PlayerName [ " + this.getPlayerName() + " ] >>>> ---------- ");
		KarmicCard cardToPlay = pickCardFromList(this.getPlayerHand());
		
		if(cardToPlay != null) {
			while (choice < 1 || choice > 12) {
				System.out.println("\n--------------------------------------------------------------------------------------------");
				System.out.println("..............   Veuillez choisir une des options suivantes :      \r\n" + 
						"                         1-		Jouer les Points de la carte\r\n" + 
						"                         2-		Jouer le Pouvoir de la carte\r\n" + 
						"                         3-		Jouer la Carte dans votre Vie Future\r\n" + 
						"                         4-		Passer votre tour\r\n" +
						"                         5-		Afficher MON Oeuvre \r\n" +
						"                         6-		Afficher MON Oeuvre Exposée \r\n" +
						"                         7-		Afficher Ma Vie Future \r\n" +
						"                         8-		Afficher les Informations sur l'Espace du Jeu \r\n" +
						"                         9-		Voir Toutes l'Oeuvre de Votre Rival \r\n" +
						"                         10-		Voir l'Oeuvre Exposée de Votre Rival \r\n" +
						"                         11-		Sauvegarder Partie \r\n" +
						"                         12-		Quitter Application  \r\n" +
						"\n");
				System.out.println("---------------------------------------------------------------------------------------------------");
				
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				if (scanner.hasNextInt()) {
					choice = scanner.nextInt();
				}else {
					scanner.next(); // Consume the non-integer input
					System.out.println("!!!!!!!!!!!! VEUILLEZ ENTRE UN CHIFFRE SVP !!!!!!!!!!!!\n");
				}
				
			}
			switch(choice) {
				case 1:
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					System.out.println("++++++ A JOUER LES POINTS DE LA CARTE #### " + cardToPlay.getCardName().toUpperCase() + " #### \n");
					System.out.println("++++++ POINT DE LA CARTE ###  " + cardToPlay.getCardPoint() + "  ### \n");
					
					this.moveCardFromListToAnother(cardToPlay,this.getPlayerHand(),this.getPlayerDeeds());
					
					System.out.println("++++++ CARTE PLACE DANS OEUVRE DU JOUEUR <<< PlayerName [ " + this.getPlayerName() + " ] >>>> \n");
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... FIN TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE A LA FIN DE VOTRE TOUR EST : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					
					// Changement des ordres des tours des joueurs: 
					// On met le joueur qui joue son tour en cours a true pour dire qu'il a complété son tour 
					// Et on met son rival sur false pour dire qu'il n'a pas encore jouer son tour ou pas encore finit
					this.setHasCompletedTurn(true);
					playerOpponent.setHasCompletedTurn(false);
					
					System.out.println("\n\n");
					break;
				case 2:
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					System.out.println("++++++ A JOUER LE POUVOIR DE LA CARTE #### " + cardToPlay.getCardName().toUpperCase() + " #### \n");
					System.out.println("++++++ DESCRIPTION DU POUVOIR ##  " + cardToPlay.getCardPower() + "  ## .\n");
					
					// first we copy the card
					KarmicCard copyTempoCard = cardToPlay.copyCard();
					
					// second we remove the card from the Hand of the Current Player
					this.pulloffFromListCards(cardToPlay, this.getPlayerHand());
					
					// Activate the power Card
					System.out.println("........................ ACTIVATION DU POUVOIR EN COURS  ...........................\n");
					cardToPlay.activatePowerCard(currentGameBoard, this, playerOpponent, currentGameManager);
					
					//copy the card on The table list of the Common Area
					this.pushToListCards(copyTempoCard, currentGameBoard.getTheTable());
					
					/*-------------------------------- Test if the Rival Is a Human player or a Robot player ---------------------------- */
					this.playerKarmicCost(copyTempoCard, currentGameBoard, playerOpponent);
					/* -------------------------------------------------------------------------------------------------------------------- */
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... FIN TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE A LA FIN DE VOTRE TOUR EST : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					
					// Changement des ordres des tours des joueurs: 
					// On met le joueur qui joue son tour en cours a true pour dire qu'il a complété son tour 
					// Et on met son rival sur false pour dire qu'il n'a pas encore jouer son tour ou pas encore finit
					this.setHasCompletedTurn(true);
					playerOpponent.setHasCompletedTurn(false);
					System.out.println("\n\n");
					break;
				case 3:
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					System.out.println("++++++ A JOUER LA CARTE #### " + cardToPlay.getCardName().toUpperCase() + " #### DANS SA VIE FUTURE.\n");
					
					// la carte se pose en face caché dans la vie future 
					cardToPlay.setcardIsVisible(true);
					this.moveCardFromListToAnother(cardToPlay,this.getPlayerHand(),this.getPlayerFutureLife());
					
					System.out.println("++++++ CARTE PLACE DANS VIE FUTURE DU JOUEUR <<<< PlayerName [ " + this.getPlayerName() + " ] >>>> .............\n");
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... FIN TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE A LA FIN DE VOTRE TOUR EST : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					
					// Changement des ordres des tours des joueurs: 
					// On met le joueur qui joue son tour en cours a true pour dire qu'il a complété son tour 
					// Et on met son rival sur false pour dire qu'il n'a pas encore jouer son tour ou pas encore finit
					this.setHasCompletedTurn(true);
					playerOpponent.setHasCompletedTurn(false);
					System.out.println("\n\n");
					break;
				case 4:
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					if(this.getPlayerDeck().isEmpty()) {
						System.out.println("............. ** VOTRE PILE EST VIDE, Vous DEVRIEZ IMPERATIVEMENT JOUER UNE CARTE *** .....................\n");
						System.out.println("\n\n");
						// Faut OBLIGER le joueur en cours de jouer un tour selon les Regles du jeu, car sa PILE est vide
						this.playCard(currentGameBoard,playerOpponent,false,currentGameManager);
					}
					else {
						System.out.println("\n.........   <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> A CHOISI DE PASSER SON TOUR \n");
					}
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... FIN TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE A LA FIN DE VOTRE TOUR EST : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					
					// Changement des ordres des tours des joueurs: 
					// On met le joueur qui joue son tour en cours a true pour dire qu'il a complété son tour 
					// Et on met son rival sur false pour dire qu'il n'a pas encore jouer son tour ou pas encore finit
					this.setHasCompletedTurn(true);
					playerOpponent.setHasCompletedTurn(false);
					System.out.println("\n\n");
					break;
				case 5:
					/* ----------------------------------------------     CURRENT PLAYER   ------------------------------------------------------  */
					
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					System.out.println("***************************   VOTRE OEUVRE CONTIENT LES CARTES SUIVANTES *********************************\n");
					
					// Fonction pour afficher votre oeuvre
					this.displayListCards(this.getPlayerDeeds());	
					System.out.println("\n\n");
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					
					System.out.println("\n\n");
					
					// Faut donner au joueur en cours de jouer un tour car ce choix est juste Informative
					playCard(currentGameBoard,playerOpponent,false,currentGameManager);
					break;
				case 6:
					/* ----------------------------------------------     CURRENT PLAYER  ------------------------------------------------------  */
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					System.out.println("***************************  VOTRE OEUVRE EXPOSEE EST LA SUIVANTE *********************************\n");
					// Fonction pour afficher la carte exposée
					this.seeExposedDeed(this);
					System.out.println("\n");
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					System.out.println("\n\n");
					
					// Faut donner au joueur en cours de jouer un tour car ce choix est juste Informative
					playCard(currentGameBoard,playerOpponent,false,currentGameManager);
					
					break;
				case 7:
					/* ----------------------------------------------     CURRENT PLAYER  ------------------------------------------------------  */
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					System.out.println("***************************   VOTRE VIE FUTURE CONTIENT LES CARTES SUIVANTES *********************************\n");
					// Fonction pour La Vie Future du joueur en cours
					this.displayListCards(this.getPlayerFutureLife());
					System.out.println("\n");
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					System.out.println("\n\n");
					
					// Faut donner au joueur en cours de jouer un tour car ce choix est juste Informative
					playCard(currentGameBoard,playerOpponent,false,currentGameManager);
					
					break;
				case 8:
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					this.displayInformationCurrentGameBoard(currentGameBoard);
					
					System.out.println("\n\n");				
					// Faut donner au joueur en cours de jouer un tour car ce choix est juste Informative
					playCard(currentGameBoard,playerOpponent,false,currentGameManager);
					
					break;
				case 9:
					/* ----------------------------------------------     RIVAL  ------------------------------------------------------  */
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					System.out.println("***************************  TOUTES LES CARTES D'OEUVRE DU RIVAL CONTIENT *********************************\n");
					
					// Fonction pour afficher toutes l'oeuvre de votre rival
					this.displayListCards(playerOpponent.getPlayerDeeds());	
					System.out.println("\n\n");
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					
					System.out.println("\n\n");
					// Faut donner au joueur en cours de jouer un tour car ce choix est juste Informative
					playCard(currentGameBoard,playerOpponent,false,currentGameManager);
					
					break;
				case 10:
					/* ----------------------------------------------     RIVAL  ------------------------------------------------------  */
					System.out.println(".......TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> \n");
					System.out.println("***************************  OEUVRE EXPOSEE DU RIVAL CONTIENT *********************************\n");
					
					// Fonction pour afficher la carte exposée
					this.seeExposedDeed(playerOpponent);
					System.out.println("\n");
					
					// Les joueurs sont autorisés à connaître à tout moment le nombre de cartes dans la Source et dans les zones de jeu personnelles (Pile, Main, Vie Future).
					System.out.println("....... TOUR N° [ " + currentGameManager.getTurnCounter() + " ] DU JOUEUR  <<< PlayerName [ " + this.getPlayerName().toUpperCase() + " ] >>>> Informations DE VOTRE ZONE : \n");
					this.displayInformationCurrentGameBoard(currentGameBoard);
					
					System.out.println("\n\n");
					
					// Faut donner au joueur en cours de jouer un tour car ce choix est juste Informative
					playCard(currentGameBoard,playerOpponent,false,currentGameManager);
					
					break;
				case 11:
					
					// Sauvegarder l'etat de la partie
					try {
						if(currentGameManager.isHuman()) {
							KarmakaMainGame.saveGame(currentGameManager, "karmmakaGameStateHuman.sav");
						}
						else {
							KarmakaMainGame.saveGame(currentGameManager, "karmmakaGameStateRobot.sav");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					// Faut donner au joueur en cours de jouer un tour car ce choix est juste Informative
					playCard(currentGameBoard,playerOpponent,false,currentGameManager);
					
					break;
				case 12:
					System.out.println(">>>>> Fermeture de l'Application ");
					System.exit(0);
					
					break;
				default:
					break;
			}
			
		}
	}
	
	
	

}
