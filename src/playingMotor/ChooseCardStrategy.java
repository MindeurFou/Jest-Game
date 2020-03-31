package playingMotor;
import java.util.Scanner;

/**
 * Interface des strat�gies de s�lection de carte
 */
public interface ChooseCardStrategy {
	
	/**
	 * M�thode permettant la s�lection de carte
	 */
	public void chooseCard(Player chosenPlayer,Player currentPlayer, Scanner scanner);
}
