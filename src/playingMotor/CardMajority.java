package playingMotor;
import java.util.*;

/**
 * Sous classe de Card {@link Card} qui représente les cartes dont la condition de trophée est
 * la possession du plus grand nombre de carte d'une valeur donnée.
 */
public class CardMajority extends Card {
	
	/**
	 * attribut privé de type Color représentant la valeur liée à la condition de trophée.
	 */
	private Value majorityValue;
	
	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur et sa couleur
	 * et qui initialise la visibilité de la carte à false. La valeur de la condition
	 * de trophée est séléctionnée aléatoirement.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public CardMajority(Value v, Color c) {
		super(v,c);
		this.majorityValue = Value.getRandomValue();
	}
	
	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur, sa couleur et
	 * sa valeur de condition de trophée et qui initialise la visibilité de la carte à false.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 * @param color valeur de la condition de trophée (de type Value {@link Value})
	 */
	public CardMajority (Value v, Color c, Value mv) {
		super(v,c);
		this.majorityValue = mv;
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
		int numberOfMajorityChosenPlayer = chosenPlayer.getJest().countMajority(this.majorityValue);
		int numberOfMajority;
		
		while(it.hasNext()) {
			
			Player p = it.next();
			numberOfMajority = p.getJest().countMajority(this.majorityValue);
			
			if(numberOfMajority > numberOfMajorityChosenPlayer) {
				chosenPlayer = p;
				numberOfMajorityChosenPlayer = numberOfMajority;
			} else if (numberOfMajority == numberOfMajorityChosenPlayer) { // tie break
				if (p.getJest().searchHighestSuitCard(this.majorityValue).getColor().ordinal() > chosenPlayer.getJest().searchHighestSuitCard(this.majorityValue).getColor().ordinal()) {
					chosenPlayer = p;
				}
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
		return super.toString()/*+ " Condition : Majority of "+this.majorityValue*/;
	}

}
