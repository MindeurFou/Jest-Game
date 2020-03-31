package playingMotor;
import java.util.Scanner;

/**
 * Interface des stratégies de sélection de carte
 */
public interface ChooseCardStrategy {
	
	/**
	 * Méthode permettant la sélection de carte
	 */
	public void chooseCard(Player chosenPlayer,Player currentPlayer, Scanner scanner);
}
