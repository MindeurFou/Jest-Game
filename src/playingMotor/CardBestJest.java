package playingMotor;
import java.util.*;

/**
 * Sous classe de Card {@link Card} qui repr�sente les cartes dont la condition de troph�e est la meilleur valeur de Jest.
 */
public class CardBestJest extends Card {
	
	/**
	 * attribut priv� de type boolean qui repr�sente la non possession de cate Joker.
	 */
	private boolean noJoker;
	
	/**
	 * constructeur de la classe Carte avec comme param�tres sa valeur et sa couleur
	 * et qui initialise la visibilit� de la carte � false. Le caract�re Joker de la
	 * carte est choisit al�atoirement avec une probabilit� de 0,5.
	 * 
	 * @param value  valeur de la carte (de type Value {@link Value}) 
	 * @param color couleur de la carte (de type Color {@link Color})
	 */
	public CardBestJest(Value v, Color c) {
		super(v, c);
		
		double i = Math.random();		
		if (i > 0.5) {
			this.noJoker = true;
		}		
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
		
		if (this.noJoker == false) {
			
			while (it.hasNext()) {
				Player player = it.next();
			
				if(player.getScore() > chosenPlayer.getScore()) {
					chosenPlayer = player;
				} 
				else if ( player.getScore() == chosenPlayer.getScore()) { // tie break
	
					if (Player.valueTieBreak(chosenPlayer, player) == null) { //2eme tieBreak
						chosenPlayer = Player.colorTieBreak(chosenPlayer, player);
					} else {
						chosenPlayer = Player.valueTieBreak(chosenPlayer, player);
					}
					
				
				}
			}
			
		}else { 
			while (it.hasNext()) {
				Player player = it.next();
			
				if(player.getScore() > chosenPlayer.getScore() && player.getJest().contains(null , null) == false) {
					chosenPlayer = player;
				}
				else if (player.getScore() == chosenPlayer.getScore() && player.getJest().contains(null , null) == false) { // tie break
					
					if (Player.valueTieBreak(chosenPlayer, player) == null) { //2eme tieBreak
						chosenPlayer = Player.colorTieBreak(chosenPlayer, player);
					} else {
						chosenPlayer = Player.valueTieBreak(chosenPlayer, player);
					}
					
				}
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
		return super.toString() /*+ " Condition : BestJest, noJoker = "+ this.noJoker*/;
	}
}
