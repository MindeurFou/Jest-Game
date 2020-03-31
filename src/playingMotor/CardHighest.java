package playingMotor;
import java.util.*;

/**
 * Sous classe de Card {@link Card} qui repr�sente les cartes dont la condition de troph�e est
 * la possession de la carte ayant la plus haute valeur d'une couleur donn�e.
 */
public class CardHighest extends Card {
	
	/**
	 * attribut priv� de type Color repr�sentant la couleur li�e � la condition de troph�e.
	 */
	private Color highestColor;
	
	/**
	 * constructeur de la classe Carte avec comme param�tres sa valeur et sa couleur
	 * et qui initialise la visibilit� de la carte � false. La couleur de la condition
	 * de troph�e est s�l�ctionn�e al�atoirement.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public CardHighest(Value v, Color c) {
		super(v,c);
		this.highestColor = Color.getRandomColor();
	}
	
	/**
	 * constructeur de la classe Carte avec comme param�tres sa valeur, sa couleur et
	 * sa couleur de condition de troph�e et qui initialise la visibilit� de la carte � false.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 * @param color couleur de la condition de troph�e (de type Color {@link Color})
	 */
	public CardHighest(Value v, Color c, Color hc) {
		super(v,c);
		this.highestColor = hc;
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
	 * M�thode qui transcrit la carte en String pour les affichage
	 * 
	 * @return nom de la carte (de type String)
	 */
	public String toString() {
		return super.toString() /*+ "Condition : highestValue of : "+ this.highestColor*/;
	}
	
}
