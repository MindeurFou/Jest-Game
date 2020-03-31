package playingMotor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interface des strat�gies de s�lection de joueur
 */
public interface ChoosePlayerStrategy {
	
	/**
	 * M�thode permettant la s�lection de joueur
	 */
	public void choosePlayer (ArrayList<Player> players ,Player currentPlayer , boolean canSelfDraw, Scanner scanner);
}
