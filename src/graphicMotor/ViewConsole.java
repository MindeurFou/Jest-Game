package graphicMotor;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import playingMotor.Card;
import playingMotor.ChooseCardStrategy0;
import playingMotor.ChoosePlayerStrategy0;
import playingMotor.Game;
import playingMotor.MakingOfferStrategy0;
import playingMotor.Player;
import playingMotor.WrongInputException;

public class ViewConsole implements Observer, Runnable {

	private Game game;
	private Scanner scanner;
	
	private String line;
	
	public ViewConsole(Game game) {
		
		this.scanner = new Scanner(System.in);
		this.game = game;
		Iterator<Player> it = this.game.getPlayers().iterator();
		
		while(it.hasNext()) {
			it.next().addObserver(this);
		}
		
		Thread t = new Thread(this,"Thread console");
		t.start();
		
	}

	public void update(Observable instanceObservable, Object o) {

		if (instanceObservable instanceof Player) {
			
			Player player = (Player)instanceObservable;

			if (o instanceof MakingOfferStrategy0) {
				
				game.display("\nAu tour de "+player.getName() +" de choisir quelles cartes offrir.");
				System.out.println("Main de " + player.getName() + " : " + player.getHand());
				System.out.print("Quelle carte voulez-vous cacher ? ");
				
				Card chosenCard = null;
				
				
				while (player.isMakingOffer()) {		
					
					if (line != null) {
						try {
							chosenCard = Card.identifyCard(line, player.getHand()); // trouve la reference de la carte
							player.putCardsIntoOffer(chosenCard);
						} catch (WrongInputException e) {
							System.out.println("Saisir le nom de la carte comme écrite ci-dessous ou en minuscules");							
						} finally {
							this.line = null;
						}
					} else {
						try {	
							Thread.sleep(1000);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					
				}

			} else if (o instanceof ChoosePlayerStrategy0) {
				
				ChoosePlayerStrategy0 strat = (ChoosePlayerStrategy0)o;
							
				Player chosenPlayer = null ;
			
				System.out.print("\nA quel joueur voulez-vous prendre une carte ? ");

				while (player.isChoosingPlayer()) {
					
					if (line != null) {
						try {
							chosenPlayer = Player.identifyPlayer(line, strat.getPlayers(), player, strat.getCanSelfDraw()); 																								
							game.setChosenPlayer(chosenPlayer);							
							player.setIsChoosingPlayer(false);
						} catch (WrongInputException e) {
							System.out.println("Saisir le nom d'un joueur existant, en majuscules ou minuscules");
						} finally {
							this.line = null;
						}
					} else {
						try {						
							Thread.sleep(1000);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
				
				}
			
			
		} else if (o instanceof ChooseCardStrategy0) {

			
			Card chosenCard = null;

			System.out.print("Quelle carte voulez-vous prendre à ce joueur ? ");

			while (player.isChoosingCard()) {
				if (this.line != null) {

					try {
						chosenCard = Card.identifyCard(line, game.getChosenPlayer().getOffer()); 																						// carte
						game.setChosenCard(chosenCard);
						player.setIsChoosingCard(false);
						this.notify();
					} catch (WrongInputException e) {
						System.out.println("Saisir le nom de la carte comme écrite ci-dessus ou en minuscules, ou \"hidden card\" pour choisir la carte face cachée");						
					} finally {
						line = null;
					}

				} else {
					try {						
						Thread.sleep(1000);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}
		}
	}
	
	
	public void run() {	
		while(game.getTurn() < 15) {	
			this.line = scanner.nextLine();
			
		}
	}
}
