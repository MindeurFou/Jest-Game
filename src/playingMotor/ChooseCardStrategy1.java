package playingMotor;
import java.util.Scanner;

/**
 * Interface des strat�gies de s�lection de carte
 */
public class ChooseCardStrategy1 implements ChooseCardStrategy {

	/**
	 * M�thode permettant la s�lection de carte
	 */
	public Card chooseCard(Player chosenPlayer, Scanner scanner) {
		return chosenPlayer.getOffer().getCard(0);
	}

}
