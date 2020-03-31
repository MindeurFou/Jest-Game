package playingMotor;
import java.util.*;

/**
 * Il s'agit de la classe objet principale repr�sentant une carte.
 */
public abstract class Card {
	
	/**
	 * attribut priv� de type Couleur repr�sentant la couleur de la carte {@link Color}
	 */
	protected Color color;
	/**
	 * attribut priv� de type Couleur repr�sentant la valeur de la carte {@link Value}
	 */
	protected Value value;
	/**
	 * attribut priv� de type boolean repr�sentant le c�t� visible de la carte (recto / verso)
	 */
	protected boolean visible; 
	
	/**
	 * constructeur de la classe Carte avec comme param�tres sa valeur et sa couleur
	 * et qui initialise la visibilit� de la carte � false
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
	 * constructeur de la classe Carte avec comme param�tres sa valeur, sa couleur et sa visibilit�
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 * @param state visibilit� de la carte (de type boolean)
	 */
	public Card (Value value , Color color , boolean state) {
		this.color = color ;
		this.value = value ;
		this.visible = state ;
	}
	
	/**
	 * getter permettant de r�cup�rer la couleur d'une carte
	 * 
	 * @return couleur de la carte (de type Color {@link Color})
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * getter permettant de r�cup�rer la valeur d'une carte
	 * 
	 * @return valeur de la carte (de type Value {@link Value})
	 */
	public Value getValue() {
		return value;
	}
	
	/**
	 * getter permettant de r�cup�rer la visibilit� d'une carte
	 * 
	 * @return visibilit� de la carte (de type boolean)
	 */
	public boolean getVisible() {
		return visible;
	}
	
	/**
	 * M�thode permettant de connaitre le nom d'une carte
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
	 * Setter permettant de modifier la visibilit� d'une carte
	 * 
	 * @param visible visibilit� de la carte (de type boolean)
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * M�thode abstraite qui permmet de savoir � qui revient cette carte si elle est un troph�e
	 * 
	 * @param players liste de joueur de laquelle tirer un gagnant (de type ArrayList<Player> {@link Player})
	 * 
	 * @return joueur qui remporte la carte (de type Player {@link Player})
	 */
	public abstract Player chooseTrophyPlayer(ArrayList<Player> players);
	
	/**
	 * M�thode qui cherche la carte dans la main d'un joueur. Renvoie null si ne trouve pas.
	 * 
	 * @param line nom de la carte � retirer du paquet (de type String)
	 * @param stack paquet de carte duquel retirer la carte (de type StackOfCards {@link StackOfCards})
	 * 
	 * @return carte souhait� (de type Card {@link Card})
	 */
	public static Card identifyCard(String line, StackOfCards stack) throws WrongInputException {
		
		if ( line.equalsIgnoreCase("joker") ) {
			return stack.getCard(null,null); //la fonction renvoie null si pas trouv�
		}
		else if ( line.equalsIgnoreCase("hidden card")) {
			return stack.getCard(1); // C'est toujours la deuxieme card qui est cach�
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
				throw new WrongInputException("La carte n'a pas �t� trouv�e...\n");
			}
			
		}
			else {
				throw new WrongInputException("La carte n'a pas �t� trouv�e...\n");
			}
	}
	
	/**
	 * M�thode qui autorise un objet de la classe Visitor {@link Visitor}
	 * 
	 * @param v objet souaitant acc�der � cette carte (de type Visitor {@link Visitor})
	 */
	public void accept(Visitor v) {
		if(v instanceof ScoreVisitor) {
			v.visit(this);
		}		
	}
	
	/**
	 * M�thode qui transcrit la carte en String pour les affichage
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
