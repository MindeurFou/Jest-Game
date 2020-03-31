package playingMotor;
import java.util.*;

/**
 * Sous classe de Card {@link Card} qui repr�sente les cartes dont la condition de troph�e est
 * la possession de la carte ayant la plus basse valeur d'une couleur donn�e.
 */
public class CardLowest extends Card {
	
	/**
	 * attribut priv� de type Color repr�sentant la couleur li�e � la condition de troph�e.
	 */
	private Color lowestColor;
	
	/**
	 * constructeur de la classe Carte avec comme param�tres sa valeur et sa couleur
	 * et qui initialise la visibilit� de la carte � false. La couleur de la condition
	 * de troph�e est s�l�ctionn�e al�atoirement.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public CardLowest(Value v, Color c) {
		super(v,c);
		this.lowestColor = Color.getRandomColor();
	}
	
	/**
	 * constructeur de la classe Carte avec comme param�tres sa valeur, sa couleur et
	 * sa couleur de condition de troph�e et qui initialise la visibilit� de la carte � false.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 * @param color couleur de la condition de troph�e (de type Color {@link Color})
	 */
	public CardLowest(Value v, Color c, Color lc) {
		super(v,c);
		this.lowestColor = lc;
	}
	
	/**
	 * Red�finition de la m�thode de la classe Card {@link Card} qui permmet de savoir � qui revient cette carte si elle est un troph�e
	 * 
	 * @param players liste de joueur de laquelle tirer un gagnant (de type ArrayList<Player> {@link Player})
	 * 
	 * @return joueur qui remporte la carte (de type Player {@link Player})
	 */
	public Player chooseTrophyPlayer(ArrayList<Player> players) {
		
		Iterator<Player> it = players.iterator();
		
		Player chosenPlayer = it.next();
		chosenPlayer.setCurrentCard(chosenPlayer.getJest().searchLowestCard(this.lowestColor)); // initialisation avec le premier joueur
		
		while(it.hasNext()) {
			Player p = it.next();			
			p.setCurrentCard( p.getJest().searchLowestCard(this.lowestColor));
			
			if ( chosenPlayer.getCurrentCard() == null) { //si la recherche sur le premier joueur avait renvoy� null
				chosenPlayer = p;
			}
			else if( p.getCurrentCard() != null &&  p.getCurrentCard().getValue().ordinal() < chosenPlayer.getCurrentCard().getValue().ordinal()) {
				chosenPlayer = p;
			}
		}
		
		return chosenPlayer;
	}
	
	/**
	 * M�thode qui transcrit la carte en String pour les affichage
	 * 
	 * @return nom de la carte (de type String)
	 */
	public String toString() {
		return super.toString()/*+ "Condition : Lowest number of "+ this.lowestColor*/;
	}

}
