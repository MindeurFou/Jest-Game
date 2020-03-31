package playingMotor;
import java.util.*;

import graphicMotor.ViewConsole;
import graphicMotor.ViewGame;

// Game est un singleton
public class Game extends Observable implements Runnable {
	
	private static Game game = null;
	
	private ArrayList<Player> players;
	private Player chosenPlayer;
	private Card chosenCard;
	private StackOfCards mainStack;
	private StackOfCards tempStack;
	private LinkedList<Card> trophies;	
	private boolean lastRound;
	private boolean inOfferPhase;
	private boolean inExchangingPhase;
	private int turn;
	
	private ViewGame viewGame;
	private ViewConsole consoleView;
	
	private ScoreVisitor sv;
	
	private Scanner scanner;
	
	// On accède au constructeur par la méthode getInstanceGame
	private Game() {
		super();
		this.players = new ArrayList<Player>();
		this.mainStack = new StackOfCards();
		this.tempStack = new StackOfCards();
		this.trophies = new LinkedList<Card>();
		this.scanner = new Scanner(System.in) ;
		this.lastRound = false;
		this.inExchangingPhase = false;
		this.inOfferPhase = false;
		this.turn = 0;
		this.viewGame = null;		
		
		this.chosenCard = null;
		this.chosenPlayer = null;
		
	}
	
	// On accède au constructeur par cette méthode
	public static Game getInstanceGame() {
		if(Game.game == null) {
			Game.game = new Game();
		}
		return Game.game;
	}
	
	// distribue les deux trophés grace aux méthodes implantées dans les sous classes de Card.
	public void distributeTrophies() {
		
		Card trophie1 = game.trophies.pop();
		Card trophie2 = game.trophies.pop();
		
		Player p1 = trophie1.chooseTrophyPlayer(this.players);
		Player p2 = trophie2.chooseTrophyPlayer(this.players);
		
		p1.takeCardIntoJest(trophie1);
		p2.takeCardIntoJest(trophie2);
		
		this.notifyObservers("clear trophies");
				
	}
	
	
	public void addPlayer(String name, Strategies strat ) {
		this.players.add(new Player(name,strat));
	}

	public void dealCards() {
		
		Iterator<Player> it = this.players.iterator();
		
		if (turn == 1) {
			this.tempStack.addCard(this.mainStack.draw(2*this.players.size()));
		}
		else {
			while(it.hasNext()) { // récupere l'unique carte qui restait dans la main de chaque joueur
				Player player = it.next();
				this.tempStack.addCard(player.giveCardFromOffer());
			}
			
			this.tempStack.addCard(this.mainStack.draw(this.players.size()));
		}
		
		
		this.tempStack.shuffle();
		
		it = this.players.iterator(); // reinitialisation de l'itérateur
		
		while(it.hasNext()) { // redistribue deux cartes a chaque joueur
			Player player = it.next() ;
			player.takeCardIntoHand(this.tempStack.draw(2)); //setChanged est dans cette méthode
			player.notifyObservers("hide hand");
		}
		
		
	}
	
	public void makeOffer() {
		
		Iterator<Player> it = this.players.iterator();
		
		while(it.hasNext()) {			
			it.next().makingOffer(scanner);		
		}
	}
	
	public void exchangingPhase() {
		
		// copie des joueurs pour pouvoir manipuler correctement chaque tour
		ArrayList<Player> playersWithTwoCards = new ArrayList<Player>(this.players);
		ArrayList<Player> playersWhoHaventPlayed = new ArrayList<Player>(this.players);
		
		
		Player player = this.firstPlayer(playersWhoHaventPlayed);
		
		for (int i=0; i < this.players.size(); i++) {
			
			boolean canSelfDraw = false;
			
			// présentation des offres disponibles ==================================
			game.display("Au tour de " + player.getName() +" de choisir le joueur a qui il veut prendre une carte.");
			System.out.println("\nPour rappel votre jest vaut :"+player.getJest()+".\nChoisissez une carte parmi ces offres disponibles :");
			
			if(i == this.players.size()-1 && playersWithTwoCards.contains(player)) { // si le joueur ne peut piocher que dans son offre
				display(player.getName()+" doit choisir dans sa propre offre");
				canSelfDraw = true;			
			} else {
				this.printOffers(playersWithTwoCards, player);
			}
			// fin de la présentation des offres disponibles =========================
				
			player.choosePlayer(playersWithTwoCards, canSelfDraw, this.scanner);
			player.chooseCard(chosenPlayer, this.scanner);			

			this.chosenPlayer.getOffer().removeCard(chosenCard);
			player.takeCardIntoJest(chosenCard); // l'ajoute au jest du joueur qui a choisi
			this.chosenCard = null;
			
			playersWhoHaventPlayed.remove(player);
			playersWithTwoCards.remove(chosenPlayer);
			
			// choix du prochain joueur
			if(playersWhoHaventPlayed.contains(chosenPlayer)) {
				player = chosenPlayer;
			} else {
				player = this.firstPlayer(playersWhoHaventPlayed);
			}
			chosenPlayer = null;
		}
		
		this.setAllVisibility(true);
		
	}
	
	// fonction principale pour le jeu, sera appelé en boucle dans le main. Je n'ai pas pris en compte les conditions d'arret
	public void playARound() {
		
		turn++;
		this.setChanged();
		this.notifyObservers("update turn");
		this.dealCards();
		
		System.out.println("\n============= Fin de la phase de distribution ===============\n");
		
		this.inOfferPhase = true;
		this.makeOffer();
		this.inOfferPhase = false;
		
		System.out.println("\n============= Fin de la phase d'offre =======================\n");
		
		this.inExchangingPhase = true;
		this.exchangingPhase();
		this.inExchangingPhase = false;
		
		sv.calculateScore(this);
		System.out.println("\n============= Fin de la phase d'échange =====================\n");
		
	}
	
	public void playAllRounds() {
		
		while (!lastRound) {

			if (mainStack.getNumberOfCards() == players.size() || mainStack.getNumberOfCards() < 2 * players.size()) {
				lastRound = true;
				this.display("Attention dernier tour de jeu !");
			}
			System.out.print("Tour : " + (turn+1)+". ");
			this.display("Il y a " + mainStack.getNumberOfCards()+ " cartes dans le paquet principal");
			game.playARound();

		}
	}
	
	public void display(String message) {		
		System.out.println(message);
		
		if (viewGame != null) {
			this.viewGame.getTextLabel().setText(message);
		}
	}
	
	public Player firstPlayer(ArrayList<Player> players) {
		
		if (players.size()==1) {
			return players.get(0) ;
		}
		else {
			Player firstPlayer = new Player("JOUEUR INTERMEDIAIRE") ;
			Card bestCard = new CardBestJest(Value.ACE,Color.HEART) ;
			for (Player player : players) {
				for (Card card : player.getOffer().getCardList()) {
					if (card.visible && (card.value != null || card.color != null)) {
						if (card.value.ordinal() > bestCard.value.ordinal()) {
							firstPlayer = player;
							bestCard = card ;
						}
						else if (card.value.ordinal() == bestCard.value.ordinal()) {
							if (card.color.ordinal() > bestCard.color.ordinal()) {
								firstPlayer = player;
								bestCard = card ;
							}
							else if (card.color.ordinal() == bestCard.color.ordinal()) {
								firstPlayer = player;
								bestCard = card ;
							}
						}
					}
				}
			}
			return firstPlayer ;
		}
		
	}
	
	// change la visibilité à la valeur choisie dans toutes les cartes du jest ET main
	public void setAllVisibility(boolean visible) {
		
		for (Player p : this.players) {

			Iterator<Card> it = p.getHand().getCardList().iterator();
			
			while(it.hasNext()) {
				it.next().setVisible(visible);
			}
			
			Iterator<Card> it2 = p.getJest().getCardList().iterator();
			
			while(it2.hasNext()) {
				it2.next().setVisible(visible);
			}
			
			Iterator<Card> it3 = p.getOffer().getCardList().iterator();
			
			while(it3.hasNext()) {
				it3.next().setVisible(visible);
			}
		}
	}
	
	public void printOffers() {
		
		Iterator<Player> it = this.players.iterator();
		
		System.out.println("Toutes les offres : ");
		
		while(it.hasNext()) {
			Player p = it.next();
			System.out.println("Offre de "+p.getName()+" : "+p.getOffer());					
		}
	}
	
	public void printOffers(ArrayList<Player> players, Player actualPlayer) {
		
		Iterator<Player> it = players.iterator();
		
		while(it.hasNext()) {
			Player p = it.next();
			if (p != actualPlayer) {
				System.out.println("Offre de "+p.getName()+" : "+p.getOffer());
			}				
		}
	}
	
	// determine le grand gagnant en fonction des scores. exaequo pris en compte
	public String winner() {
		
		Iterator<Player> it = this.players.iterator();
		
		Player winner = it.next();
		
		while(it.hasNext()) {
			Player p = it.next();
			
			if(p.getScore() > winner.getScore()) {
				winner = p;
			} else if (p.getScore() == winner.getScore()) {
				
				if (Player.valueTieBreak(p, winner) == null) { //2eme tieBreak
					winner = Player.colorTieBreak(p, winner);
				} else {
					winner = Player.valueTieBreak(p, winner);
				}
				
			}
		}
		
		return winner.getName();
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public void chooseStack() {
		
		
		boolean correct = false;
		
		while (!correct) {
			
			System.out.print("Voulez-vous jouer avec :\n1) Le paquet de cartes classiques\n2) Un paquet dont les conditions de trophée sont aléatoires ");		
			int answer = scanner.nextInt();
			
			if (answer == 1) {
				game.mainStack.generateClassicFullStack();
				correct = true;
			} else if (answer == 2) {
				game.mainStack.generateFullStack();
				correct = true;
			} else {
				System.out.println("Veuillez rentrer 1 ou 2 comme réponse");
			}
		}
		scanner.nextLine();
	}
	
	public void gameWaiting(int millis) {
		try {
			Thread.sleep(millis);
		} catch ( InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getPlayersName() {
		
		String names = "";
		
		for (Player p : this.players) {
			names += p.getName()+", ";
		}
		return names;
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	public StackOfCards getMainStack() {
		return this.mainStack;
	}
	
	public LinkedList<Card> getTrophies(){
		return this.trophies;
	}
	
	public void setViewGame(ViewGame viewGame) {
		this.viewGame = viewGame;
	}
	
	public void setViewConsole(ViewConsole viewConsole) {
		this.consoleView = viewConsole;
	}
	
	public void setChosenPlayer(Player player) {
		this.chosenPlayer = player;
	}
	
	public Player getChosenPlayer() {
		return this.chosenPlayer;
	}
	
	public boolean isInOfferPhase() {
		return this.inOfferPhase;
	}
	
	public boolean isInExchangingPhase() {
		return this.inExchangingPhase;
	}
	public void setChosenCard(Card card) {
		this.chosenCard = card;
	}
	
	public Card getChosenCard() {
		return this.chosenCard;
	}
	public void accept(Visitor v) {
		if(v instanceof ScoreVisitor) {
			v.visit(this);
		}		
	}
	
	public void run() {
		
		sv = new ScoreVisitor1();
		
		System.out.println("\nCommencons la partie ! Il y a "+ this.getPlayersName()+"comme joueurs");
		
		this.gameWaiting(1500);
		this.getMainStack().shuffle();
		this.getTrophies().addAll(this.getMainStack().draw(2));
		
		this.setChanged();
		this.notifyObservers("display trophies");
		System.out.println("Les trophées sont :" + this.getTrophies()+"\n");
		
		this.playAllRounds();
		
		
		// a la fin de la partie, chaque joueur prend la carte qui reste dans sa main et la met dans son jest
		for (Player p : game.players) {
			p.takeCardIntoJest(p.giveCardFromOffer());
		}
		
		sv.calculateScore(this);
		this.distributeTrophies();
		sv.calculateScore(this);
		this.setChanged();
		this.notifyObservers("end of game");
		
		System.out.println(game);		
		System.out.println("\nFin de la partie ! "+this.winner()+" est le grand vainqueur !!!!");
		
		
		
		
		
		
		
	}
	
	public String toString() {
		return " A game with : "+this.players+", mainStack : "+ this.mainStack+ ", trophies : "+ this.trophies;
	}
	
	public static void main(String[] args) {
		
		Game game = Game.getInstanceGame();
		
		game.display("Partie lancée");
		
		game.addPlayer("Stacy", Strategies.strat0);
		game.addPlayer("Tony", Strategies.strat0);
		game.addPlayer("Jay", Strategies.strat0);
		
		game.consoleView = new ViewConsole(game);
		ScoreVisitor scoreVisitor = new ScoreVisitor1();
		
		game.chooseStack();
		
		System.out.println("\nCommencons la partie ! Il y a "+ game.getPlayersName()+"comme joueurs");
		
		game.gameWaiting(3000);
		game.mainStack.shuffle();	
		game.trophies.addAll(game.mainStack.draw(2));   // dépose les deux cartes dans l'endroit des trophées
		
		System.out.println("Les trophées sont :" + game.trophies+"\n") ;
		
		game.playAllRounds();		
		
		
		
		// a la fin de la partie, chaque joueur prend la carte qui reste dans sa main et la met dans son jest
		for (Player p : game.players) {
			p.takeCardIntoJest(p.giveCardFromOffer());
		}
		
		System.out.println(game);
			
		game.distributeTrophies();
		scoreVisitor.calculateScore(game);
		
		System.out.println(game);
		
		System.out.println("Fin de la partie ! "+game.winner()+" est le grand vainqueur !!!!");
	}
	

}
