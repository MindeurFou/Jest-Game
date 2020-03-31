package playingMotor;
import java.util.Scanner;

/**
 * Interface des stratégies de sélection de carte
 */
public class ChooseCardStrategy0 implements ChooseCardStrategy {

	/**
	 * Méthode permettant la sélection de carte
	 */
	public void chooseCard(Player chosenPlayer,Player currentPlayer, Scanner scanner) {
		
		currentPlayer.notifyObservers(this);
		
	
		
		while(currentPlayer.isChoosingCard()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
	}
	

}
