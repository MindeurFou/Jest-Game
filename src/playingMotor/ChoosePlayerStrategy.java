package playingMotor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interface des stratégies de sélection de joueur
 */
public interface ChoosePlayerStrategy {
	
	/**
	 * Méthode permettant la sélection de joueur
	 */
	public void choosePlayer (ArrayList<Player> players ,Player currentPlayer , boolean canSelfDraw, Scanner scanner);
}
