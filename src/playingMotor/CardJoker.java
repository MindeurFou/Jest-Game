package playingMotor;
import java.util.*;

/**
 * Sous classe de Card {@link Card} qui repr�sente les cartes dont la condition de troph�e est
 * la possession de la carte Joker.
 */
public class CardJoker extends Card {

	/**
	 * constructeur de la classe Carte avec comme param�tres sa valeur et sa couleur
	 * et qui initialise la visibilit� de la carte � false.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public CardJoker(Value v, Color c) {
		super(v,c);
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
