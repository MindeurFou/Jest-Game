package playingMotor;

import java.util.*;


/**
 * Classe représentant un joueur, réel ou non
 */
public class Player extends Observable {
	
	/**
	 * Attribut privé représentant le nom du joueur (de type String)
	 */
	private String name ;
	
	/**
	 * Attribut privé représentant le score du joueur (de type int)
	 */
	private int score ;
	
	/**
	 * Attribut privé représentant le jest du joueur (de type StackOfCards {@link StackOfCards})
	 */
	private StackOfCards jest;
	
	/**
	 * Attribut privé représentant la main du joueur (de type StackOfCards {@link StackOfCards})
	 */
	private StackOfCards hand;
	
	/**
	 * Attribut privé représentant l'offre du joueur (de type StackOfCards {@link StackOfCards})
	 */
	private StackOfCards offer;
	
	/**
	 * Attribut privé représentant une carte en cours d'utilisation (de type Card {@link Card})
	 */
	private Card currentCard;
	
	/**
	 * Attribut privé indiquant si le joueur est réel ou virtuel (de type boolean)
	 */
	private boolean isReal;
	
	/**
	 * Attribut privé indiquant si le joueur est entrain de faire une offre (de type boolean)
	 */
	private boolean isMakingOffer;
	
	/**
	 * Attribut privé indiquant si le joueur est entrain de choisir un joueur (de type boolean)
	 */
	private boolean isChoosingPlayer;
	
	/**
	 * Attribut privé indiquant si le joueur est entrain de choisir une carte (de type boolean)
	 */
	private boolean isChoosingCard;
	
	/**
	 * Attribut privé indiquant si le joueur peut piocher dans ses propres cartes (de type boolean)
	 */
	private boolean canSelfDraw;
	
	/**
	 * Attribut privé indiquant quelle stratégie le joueur utilise pour faire une offre (de type MakingOfferStrategy {@link MakingOfferStrategy})
	 */
	private MakingOfferStrategy makingOfferStrategy;
	
	/**
	 * Attribut privé indiquant quelle stratégie le joueur utilise pour choisir un joueur (de type ChoosePlayerStrategy {@link ChoosePlayerStrategy})
	 */
	private ChoosePlayerStrategy choosePlayerStrategy;	
	
	/**
	 * Attribut privé indiquant quelle stratégie le joueur utilise pour choisir une carte (de type ChooseCardStrategy {@link ChooseCardStrategy})
	 */
	private ChooseCardStrategy chooseCardStrategy;	
	
	/**
	 * constructeur de la classe Player prennant en paramètre le nom du joueur à créer.
	 * 
	 * @param name le nom du joueur à créer (de type String)
	 */
	public Player (String name) {
		this.name = name;
		this.score = 0;
		this.jest = new StackOfCards();
		this.hand = new StackOfCards();
		this.offer = new StackOfCards();
		this.isReal = true;
		
		
	}
	
	/**
	 * constructeur de la classe Player prennant en paramètre le nom du joueur à créer et les stratégies à utiliser.
	 * 
	 * @param name le nom du joueur à créer (de type String)
	 * @param strategy stratégies à utiliser (de type Strategies {@link Strategies})
	 */
	public Player (String name , Strategies strategy) {
		super();
		this.name = name;
		this.score = 0; // C'est la valeur du jest a la fin de la partie
		this.jest = new StackOfCards();
		this.hand = new StackOfCards();
		this.offer = new StackOfCards();
		this.isMakingOffer = false;
		this.isChoosingPlayer = false;
		this.isChoosingCard = false;
		this.canSelfDraw = false;
		
		if(strategy == Strategies.strat0) {
			this.isReal = true;
			this.chooseCardStrategy = new ChooseCardStrategy0();
			this.choosePlayerStrategy = new ChoosePlayerStrategy0();
			this.makingOfferStrategy = new MakingOfferStrategy0();			
		} else if (strategy == Strategies.strat1) {
			this.isReal = false;
			this.chooseCardStrategy = new ChooseCardStrategy1();
			this.choosePlayerStrategy = new ChoosePlayerStrategy1();
			this.makingOfferStrategy = new MakingOfferStrategy1();	
		} else {
			this.isReal = false;
			this.chooseCardStrategy = new ChooseCardStrategy2();
			this.choosePlayerStrategy = new ChoosePlayerStrategy2();
			this.makingOfferStrategy = new MakingOfferStrategy1();	
		}
		
	}
	
	/**
	 * Méthode appellé a la fin de toutes les making offer. 
	 * Prend en argument la carte qui doit etre cachée.
	 * 
	 * @param chosenCard carte à cacer (de type Card {@link Card})
	 */
	public void putCardsIntoOffer(Card chosenCard) {
	
		chosenCard.setVisible(false);		
		this.getOffer().addCard(this.getHand().getCardList()); // ajoute le reste de sa main a son offre
		this.getHand().removeAll();
		
		this.isMakingOffer = false;
		this.setChanged();
		this.notifyObservers("hide a card");
		
		
	}
	
	/**
	 * Méthode méthode utilisant le patron stratégie pour moduler son effet.
	 * 
	 * @param scanner Sert à faire passer le scanner dans la méthode (de type Scanner)
	 */
	public void makingOffer (Scanner scanner) {
		this.isMakingOffer = true;
		this.setChanged();
		this.makingOfferStrategy.makingOffer(this,scanner);		
	}
	
	/**
	 * Méthode méthode utilisant le patron stratégie pour moduler son effet.
	 * 
	 * @param players Liste de joueurs parmis lesquels choisir (de type ArrayList<Player> {@link Player})
	 * @param canSelfDraw boolean permettant de savoir si le joueur peut piocher dans ces cartes (de type boolean)
	 * @param scanner Sert à faire passer le scanner dans la méthode (de type Scanner)
	 */
	public void choosePlayer (ArrayList<Player> players, boolean canSelfDraw, Scanner scanner) {
		this.isChoosingPlayer = true;
		this.setChanged();
		this.canSelfDraw = canSelfDraw;
		this.choosePlayerStrategy.choosePlayer(players,this, canSelfDraw , scanner);
		this.canSelfDraw = false;
	}
	
	/**
	 * Méthode méthode utilisant le patron stratégie pour moduler son effet.
	 * 
	 * @param choosePlayer joueur à qui prendre les cartes (de type Player {@link Player})
	 * @param scanner Sert à faire passer le scanner dans la méthode (de type Scanner)
	 */
	public void chooseCard (Player chosenPlayer , Scanner scanner) {
		this.isChoosingCard = true;
		this.setChanged();
		this.chooseCardStrategy.chooseCard(chosenPlayer,this,scanner);
	}
	
	/**
	 * Méthode permettant d'identifier un joueur à partir d'une chaine de caractère.
	 * 
	 * @param line chaine de caractère dont on veut tirer le nom d'un joueur (de type String)
	 * @param players liste des joueurs pouvant être identifiés (de type ArrayList<Player> {@link Player})
	 */
	public static Player identifyPlayer(String line, ArrayList<Player> players) throws WrongInputException {
	
		Player player = null;
		Player currentPlayer;
		boolean find = false;
		
		Iterator<Player> it = players.iterator();
		
		while(it.hasNext() && !find) {
			
			currentPlayer = it.next();
			if(line.equalsIgnoreCase(currentPlayer.name)) {
				player = currentPlayer;
				find = true;
			}
		}
		if (player != null) {
			return player;
		} else {
			throw new WrongInputException("Le player n'a pas été trouvé...\n");
		}
		
	}
	
	/**
	 * Méthode permettant d'identifier un joueur à partir d'une chaine de caractère.
	 * Elle prend en compte la possibilité pour le joueur de piocher dans son offre.
	 * 
	 * @param line chaine de caractère dont on veut tirer le nom d'un joueur (de type String)
	 * @param players liste des joueurs pouvant être identifiés (de type ArrayList<Player> {@link Player})
	 * @param actualPlayer joueur voulant choisir un joueur (de type Player {@link Player})
	 * @param canSelfDraw indique si le joueur peut piocher dans sa pioche (de type boolean)
	 */
	public static Player identifyPlayer(String line,ArrayList<Player> players, Player actualPlayer, boolean canSelfDraw) throws WrongInputException {
		
		Player player = null;
		
		try {
			player = Player.identifyPlayer(line, players);
		} catch ( WrongInputException e ) {
			throw e; 
		}
	
		if (canSelfDraw == true) {
			return player;	
		} else {
			if ( player != actualPlayer) {
				return player;
			} else {
				throw new WrongInputException("Le joueur ne peut pas piocher dans sa propre offre !");
			}
		}
		
	}
	
	/**
	 * Méthode permettant de savoir qui de deux joueur a la carte la plus forte en valeur.
	 * 
	 * @param p1 premier joueur (de type Player {@link Player})
	 * @param p1 deuxième joueur (de type Player {@link Player})
	 * 
	 * @return Joueur ayant la carte la plus forte
	 */
	public static Player valueTieBreak(Player p1, Player p2) {
		if ( p1.jest.searchHighestCard().getValue().ordinal() > p2.getJest().searchHighestCard().getValue().ordinal()) {
			return p1;
		} else  if (p1.getJest().searchHighestCard().getValue().ordinal() == p2.getJest().searchHighestCard().getValue().ordinal()) {
			return null;
		} else {
			return p2;
		}
	}
	
	/**
	 * Méthode permettant de savoir qui de deux joueur a la carte la plus forte en couleur.
	 * 
	 * @param p1 premier joueur (de type Player {@link Player})
	 * @param p1 deuxième joueur (de type Player {@link Player})
	 * 
	 * @return Joueur ayant la carte la plus forte
	 */	
	public static Player colorTieBreak(Player p1, Player p2) {
		
		Value highestValue = p1.jest.searchHighestCard().getValue();
		
		if (p1.jest.searchHighestSuitCard(highestValue).getColor().ordinal() > p2.jest.searchHighestSuitCard(highestValue).getColor().ordinal()) {
			return p1;
		} else {
			return p2;
		}
	}
	
	/**
	 * Getter du jest du joueur
	 * 
	 * @return Jest du joueur (de type {@link StackOfCards})
	 */
	public StackOfCards getJest() {
		return this.jest;
	}

	/**
	 * Getter de l'état du joueur
	 * 
	 * @return état du joueur (de type boolean)
	 */
	public boolean getIsReal() {
		return this.isReal;
	}
	
	/**
	 * Méthode qui convertit un joueur en chaine de caractère
	 * 
	 * @return les informations sur le joueur (de type String)
	 */
	public String toString() {
		return (this.name + " main : " + this.hand + " offre : " + this.offer + " jest :" + this.jest + " score = "+ this.score);
	}

	/**
	 * Seeter de la carte courante du joueur
	 * 
	 * @param card carte courante du joueur (de type {@link Card})
	 */
	public void setCurrentCard(Card card) {
		this.currentCard = card;
	}

	/**
	 * Getter de la carte courante du joueur
	 * 
	 * @return Jest du joueur (de type Card {@link Card}
	 */
	public Card getCurrentCard() {
		return this.currentCard;
	}

	/**
	 * Getter de la main du joueur
	 * 
	 * @return la main du joueur (de type {@link StackOfCards})
	 */
	public StackOfCards getHand() {
		return this.hand;
	}

	/**
	 * Getter du nom du joueur
	 * 
	 * @return le nom du joueur (de type String)
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter de l'offre du joueur
	 * 
	 * @return l'offre du joueur (de type {@link StackOfCards})
	 */
	public StackOfCards getOffer() {
		return this.offer;
	}
	
	/**
	 * Getter de la stratégie d'offre du joueur
	 * 
	 * @return la stratégie d'offre du joueur (de type {@link MakingOfferStrategy})
	 */
	public MakingOfferStrategy getMakingOfferStrategy() {
		return this.makingOfferStrategy;
	}
	
	/**
	 * Getter de la stratégie de séléction de joueur du joueur
	 * 
	 * @return la stratégie de sélection de joueur du joueur (de type {@link ChoosePlayerStrategy})
	 */
	public ChoosePlayerStrategy getChoosePlayerStrategy() {
		return this.choosePlayerStrategy;
	}

	/**
	 * Méthode permettant au joueur de prendre une carte dans son Jest.
	 * 
	 * @param card carte à déplacer dans le Jest (de type Card {@link Card})
	 */
	public void takeCardIntoJest(Card card) { 
		this.setChanged();
		this.jest.addCard(card);
		this.jest.notifyObservers();
		
	}

	/**
	 * Méthode permettant au joueur de prendre une carte dans sa main.
	 * 
	 * @param card carte à déplacer dans la main (de type Card {@link Card})
	 */
	public void takeCardIntoHand(Card card) {
		this.setChanged();
		this.hand.addCard(card);
	}

	/**
	 * Méthode permettant au joueur de prendre une ou plusieurs cartes dans sa main.
	 * 
	 * @param card cartes à déplacer dans la main (de type Card {@link StackOfCards})
	 */
	public void takeCardIntoHand(LinkedList<Card> cards) {
		this.setChanged();
		this.hand.addCard(cards);
	}
	
	/**
	 * Méthode qui retourne la premiere carte de la main.
	 * 
	 * @return la première carte de la main du joueur (de type {@link Card})
	 */
	public Card giveCardFromHand() {
		return this.hand.draw();
	}
	
	/**
	 * Méthode qui retourne la premiere carte de l'offre du joueur.
	 * 
	 * @return la première carte de l'offre du joueur (de type {@link Card})
	 */
	public Card giveCardFromOffer() {
		return this.offer.draw();
	}
	
	/**
	 * Getter du score du joueur
	 * 
	 * @return score du joueur (de type int)
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Getter du premier boolean du Joueur
	 * 
	 * @return boolean d'état 1 du joueur (de type boolean)
	 */
	public boolean isMakingOffer() {
		return this.isMakingOffer;
	}
	
	/**
	 * Getter du second boolean du Joueur
	 * 
	 * @return boolean d'état 2 du joueur (de type boolean)
	 */
	public boolean isChoosingPlayer() {
		return this.isChoosingPlayer;
	}
	
	/**
	 * Seeter du score du joueur
	 * 
	 * @param score score du joueur (de type int)
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Seeter du premier boolean du joueur
	 * 
	 * @param bool boolean indiquant si le joueur choisit un joueur (de type boolean)
	 */
	public void setIsChoosingPlayer(boolean bool) {
		this.isChoosingPlayer = bool;
	}
	
	/**
	 * Getter du troisième boolean du Joueur
	 * 
	 * @return boolean d'état 3 du joueur (de type boolean)
	 */
	public boolean isChoosingCard() {
		return this.isChoosingCard;
	}
	
	/**
	 * Getter du boolean indiquant si le joueur put piocher dans son offre
	 * 
	 * @return boolean indiquand si le joueur put piocher dans son offre (de type boolean)
	 */
	public boolean getCanSelfDraw() {
		return this.canSelfDraw;
	}
	
	/**
	 * Seeter du boolean indiquand si le joueur choisit une carte
	 * 
	 * @param bool boolean indiquand si le joueur choisit une carte (de type boolean)
	 */
	public void setIsChoosingCard(boolean bool) {
		this.isChoosingCard = bool;
	}
	
	/**
	 * Méthode permettant d'accepter qu'un objet ScoreVisitor puisse visiter le joueur
	 */
	public void accept(Visitor v) {
		if(v instanceof ScoreVisitor) {
			v.visit(this);
		}		
	}

}
