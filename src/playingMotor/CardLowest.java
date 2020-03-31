package playingMotor;
import java.util.*;

/**
 * Sous classe de Card {@link Card} qui représente les cartes dont la condition de trophée est
 * la possession de la carte ayant la plus basse valeur d'une couleur donnée.
 */
public class CardLowest extends Card {
	
	/**
	 * attribut privé de type Color représentant la couleur liée à la condition de trophée.
	 */
	private Color lowestColor;
	
	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur et sa couleur
	 * et qui initialise la visibilité de la carte à false. La couleur de la condition
	 * de trophée est séléctionnée aléatoirement.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public CardLowest(Value v, Color c) {
		super(v,c);
		this.lowestColor = Color.getRandomColor();
	}
	
	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur, sa couleur et
	 * sa couleur de condition de trophée et qui initialise la visibilité de la carte à false.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 * @param color couleur de la condition de trophée (de type Color {@link Color})
	 */
	public CardLowest(Value v, Color c, Color lc) {
		super(v,c);
		this.lowestColor = lc;
	}
	
	/**
	 * Redéfinition de la méthode de la classe Card {@link Card} qui permmet de savoir à qui revient cette carte si elle est un trophée
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
			
			if ( chosenPlayer.getCurrentCard() == null) { //si la recherche sur le premier joueur avait renvoyé null
				chosenPlayer = p;
			}
			else if( p.getCurrentCard() != null &&  p.getCurrentCard().getValue().ordinal() < chosenPlayer.getCurrentCard().getValue().ordinal()) {
				chosenPlayer = p;
			}
		}
		
		return chosenPlayer;
	}
	
	/**
	 * Méthode qui transcrit la carte en String pour les affichage
	 * 
	 * @return nom de la carte (de type String)
	 */
	public String toString() {
		return super.toString()/*+ "Condition : Lowest number of "+ this.lowestColor*/;
	}

}
