package playingMotor;
import java.util.*;

/**
 * Classe représentant un paquet de carte sous forme de LinkedList.
 */
public class StackOfCards extends Observable {
	
	/**
	 * attribut final stativ privé représentant le nombre de carte de condition
	 * de trophée 'Highest card' dans un paquet généré aléatoirement. {@link CardHighest}
	 */
	private final static int NUMBER_OF_HIGHEST_CARD = 4;
	
	/**
	 * attribut final stativ privé représentant le nombre de carte de condition
	 * de trophée 'Lowest card' dans un paquet généré aléatoirement. {@link CardLowest}
	 */
	private final static int NUMBER_OF_LOWEST_CARD = 3;
	
	/**
	 * attribut final stativ privé représentant le nombre de carte de condition
	 * de trophée 'Best Jest' dans un paquet généré aléatoirement. {@link CardBestJest}
	 */
	private final static int NUMBER_OF_BEST_JEST = 4;
	
	/**
	 * attribut final stativ privé représentant le nombre de carte de condition
	 * de trophée 'Majority' dans un paquet généré aléatoirement. {@link CardMajority}
	 */
	private final static int NUMBER_OF_MAJORITY = 3;
	
	/**
	 * attribut final stativ privé représentant le nombre de carte de condition
	 * de trophée 'Joker card' dans un paquet généré aléatoirement. {@link CardJoker}
	 */
	private final static int NUMBER_OF_JOKER_CARD = 2; // n'est pas vraiment utilisé puisqu'on sait qu'il y a 16 cartes mais permet d'éviter les erreurs de calculs de l'utilisateur
	
	/**
	 * attribut privé contennant les cartes du paquet (de type LinkedList<Card> {@link Card})
	 */
	private LinkedList<Card> cardList ;
	
	/**
	 * constructeur de la classe StackOfCards avec comme paramètres sa liste de carte.
	 * 
	 * @param cardList cartes du paquet (de type LinkedList<Card> {@link Card})
	 */
	public StackOfCards(LinkedList<Card> cardList) {
		this.cardList = cardList;
	}

	/**
	 * constructeur de la classe StackOfCards qui créer un paquet vide.
	 */
	public StackOfCards () { 
		this.cardList = new LinkedList<Card>();
	}
	
	/**
	 * Méthode qui remplie un paquet de carte d'un jeu de carte classique.
	 */
	public void generateClassicFullStack() {
		
		this.addCard(new CardHighest(Value.ACE,Color.CLUB, Color.SPADE));
		this.addCard(new CardHighest(Value.ACE, Color.SPADE, Color.CLUB));
		this.addCard(new CardMajority(Value.ACE, Color.DIAMOND, Value.FOUR));
		this.addCard(new CardJoker(Value.ACE, Color.HEART));
		this.addCard(new CardHighest(Value.TWO,Color.CLUB, Color.SPADE));
		this.addCard(new CardMajority(Value.TWO, Color.SPADE, Value.TREE));
		this.addCard(new CardHighest(Value.TWO, Color.DIAMOND, Color.DIAMOND));
		this.addCard(new CardJoker(Value.TWO, Color.HEART));
		this.addCard(new CardHighest(Value.TREE, Color.CLUB, Color.DIAMOND));
		this.addCard(new CardMajority(Value.TREE, Color.SPADE, Value.TWO));
		this.addCard(new CardLowest(Value.TREE, Color.DIAMOND, Color.DIAMOND));
		this.addCard(new CardJoker(Value.TREE, Color.HEART));
		this.addCard(new CardLowest(Value.FOUR, Color.CLUB, Color.SPADE));
		this.addCard(new CardLowest(Value.FOUR, Color.SPADE, Color.CLUB));
		this.addCard(new CardBestJest(Value.FOUR, Color.DIAMOND));
		this.addCard(new CardJoker(Value.FOUR, Color.HEART));
		
		// ajout du joker
		this.addCard(new CardBestJest(null, null));
		
				
	}
	
	/**
	 * Méthode qui remplie un paquet de carte d'un jeu de carte réduit.
	 */
	public void generateReducedStack() {
		
		this.addCard(new CardHighest(Value.ACE,Color.CLUB, Color.SPADE));
		this.addCard(new CardJoker(Value.ACE, Color.HEART));
		this.addCard(new CardMajority(Value.TWO, Color.SPADE, Value.TREE));
		this.addCard(new CardHighest(Value.TWO, Color.DIAMOND, Color.DIAMOND));
		this.addCard(new CardMajority(Value.TREE, Color.SPADE, Value.TWO));
		this.addCard(new CardLowest(Value.TREE, Color.DIAMOND, Color.DIAMOND));
		this.addCard(new CardJoker(Value.FOUR, Color.HEART));
		this.addCard(new CardLowest(Value.FOUR, Color.SPADE, Color.CLUB));
		this.addCard(new CardBestJest(null, null));
		
	}
	
	/**
	 * Méthode qui remplie un paquet de carte d'un jeu de carte aléatoire.
	 */
	public void generateFullStack() {
		
		int i = 0;
		
		// algorithme qui fonctionne mais pas parfait parce que ce sera difficile de changer la condition pour une carte précise
		for (Value v : Value.values()) {
			for (Color c : Color.values()) { 
				
				if( 0 <= i && i < StackOfCards.NUMBER_OF_HIGHEST_CARD) {  // crée un certain nombre de carte avec tel ou tel conditon				
					Card card = new CardHighest(v,c);                  
					this.addCard(card);
					
				}else if( StackOfCards.NUMBER_OF_HIGHEST_CARD <= i && i < StackOfCards.NUMBER_OF_HIGHEST_CARD + StackOfCards.NUMBER_OF_BEST_JEST){
					Card card = new CardBestJest(v,c);
					this.addCard(card);
					
				}
				else if( StackOfCards.NUMBER_OF_HIGHEST_CARD + StackOfCards.NUMBER_OF_BEST_JEST <= i && i < StackOfCards.NUMBER_OF_HIGHEST_CARD + StackOfCards.NUMBER_OF_BEST_JEST + StackOfCards.NUMBER_OF_MAJORITY) {
					Card card = new CardMajority(v,c);                
					this.addCard(card);
					
				}
				else if ( StackOfCards.NUMBER_OF_HIGHEST_CARD + StackOfCards.NUMBER_OF_BEST_JEST + StackOfCards.NUMBER_OF_MAJORITY <= i && i < StackOfCards.NUMBER_OF_HIGHEST_CARD + StackOfCards.NUMBER_OF_BEST_JEST + StackOfCards.NUMBER_OF_MAJORITY + StackOfCards.NUMBER_OF_LOWEST_CARD) {
					Card card = new CardLowest(v,c);
					this.addCard(card);
					
				}else {
					Card card = new CardJoker(v,c);
					this.addCard(card);
					
				}
				
				i++;	
			}
		}
		
		Card joker = new CardHighest(null, null);	
		this.addCard(joker);
	}
	
	/**
	 * Méthode qui ajoute une carte au paquet.
	 * 
	 * @param card carte à ajouter (de type Card {@link Card})
	 */
	public void addCard(Card card) {
		this.cardList.add(card);
		this.setChanged();
	}

	/**
	 * Méthode qui ajoute une liste de carte au paquet.
	 * 
	 * @param cards cartes à ajouter (de type LinkedList<Card> {@link Card})
	 */
	public void addCard(LinkedList<Card> cards) {
		this.cardList.addAll(cards);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Méthode qui supprime une carte du paquet.
	 * 
	 * @param card carte à supprimer (de type Card {@link Card})
	 */
	public void removeCard(Card card) {
		this.cardList.remove(card);
	}

	/**
	 * Méthode qui supprime toutes les cartes du paquet.
	 */
	public void removeAll() {
		this.cardList.clear();
	}
	
	/**
	 * Méthode qui retre la première carte du paquet et qui la retourne en sortie.
	 * 
	 * @return première carte du paquet (de type Card {@link Card})
	 */
	public Card draw() {
		if (this.cardList.size() > 0) {
			return this.cardList.pop();
		} else {
			return null;
		}
	}
	
	/**
	 * Méthode qui retre les n premières cartes du paquet et qui les retourne en sortie.
	 *
	 * @param n nombre de carte à piocher (de type int)
	 * 
	 * @return premières cartes du paquet (de type LinkedList<Card> {@link Card})
	 */
	public LinkedList<Card> draw(int n){
		
		LinkedList<Card> cards = new LinkedList<Card>();
		
		if(this.cardList.size() >= n) {
	
			for (int i = 0 ; i < n ; i++) {
				cards.add(this.cardList.pop());
			}
			
		}
		else {
			System.out.println("On ne peut pas piocher "+n+" cartes");
			cards = null;
		}
		
		return cards;
	}
	
	/**
	 * Méthode qui mélange l'ordre des cartes dans le paquet.
	 */
	public void shuffle() {
		Collections.shuffle(this.cardList);
	}

	/**
	 * Méthode qui renvoie un String contenant la liste des cartes du paquet.
	 * 
	 * @return Liste des cartes du paquet (de type String)
	 */
	public String toString() {
		return "" + this.cardList;
	}

	/**
	 * Méthode permetant de savoir si une carte est dans un paquet.
	 * 
	 * @param card carte dont on veut savoir si elle est dans le paquet (de type Card {@link Card})
	 * 
	 * @return présence ou non de la carte (de type boolean)
	 */
	public boolean contains (Card card) {
		
		Iterator<Card> it = this.cardList.iterator();	
		boolean find = false;
		
		while (it.hasNext() && find == false) {
			Card localCard = it.next();
			
			if ( card.value == localCard.value && card.color == localCard.color) {
				find = true;
			}
		}
		return find;
		
	}
	
	/**
	 * Méthode permetant de savoir si une carte est dans un paquet.
	 * 
	 * @param v valeur de la carte dont on veut savoir si elle est dans le paquet (de type Card {@link Value})
	 * @param c couleur de la carte dont on veut savoir si elle est dans le paquet (de type Card {@link Color})
	 * 
	 * @return présence ou non de la carte (de type boolean)
	 */
	public boolean contains (Value v, Color c) {
		
		Iterator<Card> it = this.cardList.iterator();
		boolean trouve = false;
		
		while (it.hasNext() && trouve == false) {
			Card card = it.next();
			
			if ( card.value == v && card.color == c) {
				trouve = true;
			}
		}
		return trouve;
	}
	
	/**
	 * Méthode qui retourne la nième carte du paquet en sortie.
	 * 
	 * @param n rang de la carte que l'on souhaite (de type int)
	 * 
	 * @return première carte du paquet (de type Card {@link Card})
	 */
	public Card getCard(int n) {	
		return cardList.get(n);
	}
	
	/**
	 * Méthode qui renvoie une carte si elle est dans le paquet.
	 * 
	 * @param v valeur de la carte dont on veut savoir si elle est dans le paquet (de type Value {@link Value})
	 * @param c couleur de la carte dont on veut savoir si elle est dans le paquet (de type Color {@link Color})
	 * 
	 * @return la carte souhaitée si ell est présente. (de type Card {@link Card})
	 */
	public Card getCard(Value v, Color c) {
		
		Card card = null;
		Card currentCard;
		Iterator<Card> it = this.cardList.iterator();
		
		while (it.hasNext()) {
			currentCard = it.next();
			
			if (v == currentCard.getValue() && c == currentCard.getColor() ) {
				card = currentCard;
			}
			
		}
			return card;
		
	}
	
	/**
	 * Méthode renvoie la carte avec la plus haute valeur d'une couleur particuliere dans un jest.
	 * 
	 * @param hc couleur dont on veut la plus haute valeur (de type Color {@link Color})
	 * 
	 * @return carte de couleur hc ayant la plus haute valeur dans le paquet (de type Card {@link Card})
	 */
	public Card searchHighestCard(Color hc) {
		
		Iterator<Card> it = this.cardList.iterator();
				
		Card highestCard = null;
			
		while(it.hasNext()) { // parcours de toutes les cartes du jest
			
			Card card = it.next();
			
			if(card.getColor() == hc) {
				if(highestCard == null) {
					highestCard = card;
				}
				else if(card.getValue().ordinal() > highestCard.getValue().ordinal()) {
					highestCard = card;
				}
				
			}
		}
		
		return highestCard;
	}

	/**
	 * Méthode renvoie la carte avec la plus haute valeur dans un jest.
	 * 
	 * @return carte ayant la plus haute valeur dans le paquet (de type Card {@link Card})
	 */
	public Card searchHighestCard() {
		
		Iterator<Card> it = this.cardList.iterator();
		
		Card highestCard = this.getCard(0);
			
		while(it.hasNext()) { // parcours de toutes les cartes du jest

			Card card = it.next();

			if (card.getValue().ordinal() > highestCard.getValue().ordinal()) {
				highestCard = card;
			}

		}
		
		return highestCard;
	}
	
	/**
	 * Méthode renvoie la carte avec la plus basse valeur d'une couleur particuliere dans un jest.
	 * 
	 * @param hc couleur dont on veut la plus basse valeur (de type Color {@link Color})
	 * 
	 * @return carte de couleur hc ayant la plus basse valeur dans le paquet (de type Card {@link Card})
	 */
	public Card searchLowestCard(Color lc) {
		
		Iterator<Card> it = this.cardList.iterator();
		
		Card lowestCard = null;
		
		while(it.hasNext()) {
			
			Card card = it.next();
			
			if(card.getColor() == lc) {
				if(lowestCard == null) {
					lowestCard = card;
				}
				else if( card.getValue().ordinal() < lowestCard.getValue().ordinal()) {
					lowestCard = card;
				}
			}
		}
		
		return lowestCard;
	}
	
	/**
	 * Méthode renvoie la carte avec la couleur la plus forte d'une valeur particuliere dans un jest.
	 * 
	 * @param v valeur dont on veut connaitre lacarte ayant la couleur plus forte dans le paquet (de type Value {@link Value})
	 * 
	 * @return carte de valeur v ayant la couleur la plus forte dans le paquet (de type Card {@link Card})
	 */
	public Card searchHighestSuitCard(Value v) {
		
		Iterator<Card> it = this.cardList.iterator();
		Card hc = null; //highestCard
		
		while (it.hasNext()) {
			
			Card c = it.next();
			
			if (hc == null && c.getValue() == v) {
				hc = c;
			}
			else if (c.getValue() == v && c.getColor().ordinal() > hc.getColor().ordinal()) {
				hc = c;
			}
		}
		
		return hc;
	}
	
	/**
	 * Méthode qui compte le nombre de cartes d'une certaine valeur dans un jest.
	 * 
	 * @param Valeur dont on veut connaitre le nombre de carte l'ayant (de type Value {@link Value})
	 * 
	 * @return nombre de cartes de cette valeur (de type int)
	 */
	public int countMajority(Value v) {
		int numberOfMajority = 0;

		Iterator<Card> it = this.cardList.iterator();

		while (it.hasNext()) {
			if (it.next().getValue() == v) {
				numberOfMajority++;
			}
		}

		return numberOfMajority;
	}
	
	/**
	 * Getter du nombre de cartes dans le paquet
	 * 
	 * @return nombre de carte dans le paquet (de type int)
	 */
	public int getNumberOfCards() { return this.cardList.size() ; }
	
	/**
	 * Getter des cartes du paquet
	 * 
	 * @return les cartes du le paquet (de type LinkedList<Card> {@link Card})
	 */
	public LinkedList<Card> getCardList(){ return this.cardList ; }

}
