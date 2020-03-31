package playingMotor;
import java.util.*;

/**
 * Sous classe de Card {@link Card} qui représente les cartes dont la condition de trophée est
 * la possession de la carte Joker.
 */
public class CardJoker extends Card {

	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur et sa couleur
	 * et qui initialise la visibilité de la carte à false.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public CardJoker(Value v, Color c) {
		super(v,c);
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
		
		boolean trouve = false;
		
		while (it.hasNext() && trouve == false) {			
			
			Player p = it.next();
			
			if(p.getJest().contains(null , null)) {
				trouve = true;
				chosenPlayer = p;
			}		
		}
		
		return chosenPlayer;
		
	}
}
