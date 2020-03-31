package playingMotor;
import java.util.Scanner;

/**
 * Interface des stratégies de sélection de carte
 */
public class ChooseCardStrategy1 implements ChooseCardStrategy {

	/**
	 * Méthode permettant la sélection de carte
	 */
	public Card chooseCard(Player chosenPlayer, Scanner scanner) {
		return chosenPlayer.getOffer().getCard(0);
	}

}
