package playingMotor;
import java.util.*;

/**
 * Il s'agit de la classe objet principale représentant une carte.
 */
public abstract class Card {
	
	/**
	 * attribut privé de type Couleur représentant la couleur de la carte {@link Color}
	 */
	protected Color color;
	/**
	 * attribut privé de type Couleur représentant la valeur de la carte {@link Value}
	 */
	protected Value value;
	/**
	 * attribut privé de type boolean représentant le côté visible de la carte (recto / verso)
	 */
	protected boolean visible; 
	
	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur et sa couleur
	 * et qui initialise la visibilité de la carte à false
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public Card(Value value, Color color) {
		this.color = color;
		this.value = value;
		this.visible = true;
	}
	
	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur, sa couleur et sa visibilité
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 * @param state visibilité de la carte (de type boolean)
	 */
	public Card (Value value , Color color , boolean state) {
		this.color = color ;
		this.value = value ;
		this.visible = state ;
	}
	
	/**
	 * getter permettant de récupérer la couleur d'une carte
	 * 
	 * @return couleur de la carte (de type Color {@link Color})
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * getter permettant de récupérer la valeur d'une carte
	 * 
	 * @return valeur de la carte (de type Value {@link Value})
	 */
	public Value getValue() {
		return value;
	}
	
	/**
	 * getter permettant de récupérer la visibilité d'une carte
	 * 
	 * @return visibilité de la carte (de type boolean)
	 */
	public boolean getVisible() {
		return visible;
	}
	
	/**
	 * Méthode permettant de connaitre le nom d'une carte
	 * 
	 * @return nom de la carte (de type String)
	 */
	public String getName() {
		
		if (this.value != null) {
			return this.value.toString()+" of "+ this.color.toString();
		} else {
			return "joker";
		}
		
	}
	
	/**
	 * Setter permettant de modifier la visibilité d'une carte
	 * 
	 * @param visible visibilité de la carte (de type boolean)
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Méthode abstraite qui permmet de savoir à qui revient cette carte si elle est un trophée
	 * 
	 * @param players liste de joueur de laquelle tirer un gagnant (de type ArrayList<Player> {@link Player})
	 * 
	 * @return joueur qui remporte la carte (de type Player {@link Player})
	 */
	public abstract Player chooseTrophyPlayer(ArrayList<Player> players);
	
	/**
	 * Méthode qui cherche la carte dans la main d'un joueur. Renvoie null si ne trouve pas.
	 * 
	 * @param line nom de la carte à retirer du paquet (de type String)
	 * @param stack paquet de carte duquel retirer la carte (de type StackOfCards {@link StackOfCards})
	 * 
	 * @return carte souhaité (de type Card {@link Card})
	 */
	public static Card identifyCard(String line, StackOfCards stack) throws WrongInputException {
		
		if ( line.equalsIgnoreCase("joker") ) {
			return stack.getCard(null,null); //la fonction renvoie null si pas trouvé
		}
		else if ( line.equalsIgnoreCase("hidden card")) {
			return stack.getCard(1); // C'est toujours la deuxieme card qui est caché
		}
		else if (line.contains("of") || line.contains("OF")){

			Card card = null;
			Card currentCard;
			boolean find = false;			

			String[] valueAndColor = line.split(" of ");

			
		
			Iterator<Card> it = stack.getCardList().iterator();
				
			while ( it.hasNext() && !find) {
				currentCard = it.next();

				if(valueAndColor[0].equalsIgnoreCase(currentCard.getValue().name()) && valueAndColor[1].equalsIgnoreCase(currentCard.getColor().name())) {
					card = currentCard;
					find = true;
				}			
			}		
			
			if (card != null) {
				return card;
			}
			else {
				throw new WrongInputException("La carte n'a pas été trouvée...\n");
			}
			
		}
			else {
				throw new WrongInputException("La carte n'a pas été trouvée...\n");
			}
	}
	
	/**
	 * Méthode qui autorise un objet de la classe Visitor {@link Visitor}
	 * 
	 * @param v objet souaitant accéder à cette carte (de type Visitor {@link Visitor})
	 */
	public void accept(Visitor v) {
		if(v instanceof ScoreVisitor) {
			v.visit(this);
		}		
	}
	
	/**
	 * Méthode qui transcrit la carte en String pour les affichage
	 * 
	 * @return nom de la carte (de type String)
	 */
	public String toString() {
		if (!this.visible) {
			return "hidden card";
		} else if (value == null && color == null) {
			return "JOKER";
		} else {
			return this.value + " of " + this.color;
		}
	}

}
