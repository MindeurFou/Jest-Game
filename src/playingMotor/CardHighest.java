package playingMotor;
import java.util.*;

/**
 * Sous classe de Card {@link Card} qui représente les cartes dont la condition de trophée est
 * la possession de la carte ayant la plus haute valeur d'une couleur donnée.
 */
public class CardHighest extends Card {
	
	/**
	 * attribut privé de type Color représentant la couleur liée à la condition de trophée.
	 */
	private Color highestColor;
	
	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur et sa couleur
	 * et qui initialise la visibilité de la carte à false. La couleur de la condition
	 * de trophée est séléctionnée aléatoirement.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public CardHighest(Value v, Color c) {
		super(v,c);
		this.highestColor = Color.getRandomColor();
	}
	
	/**
	 * constructeur de la classe Carte avec comme paramètres sa valeur, sa couleur et
	 * sa couleur de condition de trophée et qui initialise la visibilité de la carte à false.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 * @param color couleur de la condition de trophée (de type Color {@link Color})
	 */
	public CardHighest(Value v, Color c, Color hc) {
		super(v,c);
		this.highestColor = hc;
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
		chosenPlayer.setCurrentCard(chosenPlayer.getJest().searchHighestCard(this.highestColor));
				
		
		while(it.hasNext()) { // parcours de tout les joueurs
			Player player = it.next();
			player.setCurrentCard(player.getJest().searchHighestCard(this.highestColor));
			
			if(chosenPlayer.getCurrentCard() == null) {
				chosenPlayer = player;
			}
			else if (player.getCurrentCard() != null && player.getCurrentCard().getValue().ordinal() > chosenPlayer.getCurrentCard().getValue().ordinal()) {
				chosenPlayer = player;
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
		return super.toString() /*+ "Condition : highestValue of : "+ this.highestColor*/;
	}
	
}
