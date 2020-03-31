package playingMotor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe de strat�gie de s�lection de joueur
 */
public class ChoosePlayerStrategy0 implements ChoosePlayerStrategy{

	/**
	 * Attribut priv� repr�sentant la liste de jueur pouvant �tre s�lectionn� (de type ArrayList<Player> {@link Player})
	 */
	private ArrayList<Player> players;
	
	/**
	 * Attribut priv� repr�sentant le joueur faisant son choix (de type Player {@link Player})
	 */
	private Player currentPlayer;
	
	/**
	 * Attribut priv� indiquant si le joueur peut se s�lectionner comme cible (de type boolean)
	 */
	private boolean canSelfDraw;
	
	/**
	 * M�thode permettant la s�lection de joueur
	 */
	public void choosePlayer(ArrayList<Player> players, Player currentPlayer , boolean canSelfDraw, Scanner scanner) {
		
		this.players = players;
		this.currentPlayer = currentPlayer;
		this.canSelfDraw = canSelfDraw;
		
		
		currentPlayer.notifyObservers(this);
		
		
		while(currentPlayer.isChoosingPlayer()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		
	}
	
	/**
	 * Getter de la liste de joueur
	 * 
	 * @return la liste de joueur (de type ArrayList<Player> {@link Player})
	 */
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * Getter du joueur actif
	 * 
	 * @return le joueur actif (de type Player {@link Player})
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	/**
	 * Getter du boolean
	 * 
	 * @return le boolean
	 */
	public boolean getCanSelfDraw() {
		return this.canSelfDraw;
	}

	
}
