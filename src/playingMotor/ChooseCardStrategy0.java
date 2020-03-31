package playingMotor;
import java.util.Scanner;

/**
 * Interface des strat�gies de s�lection de carte
 */
public class ChooseCardStrategy0 implements ChooseCardStrategy {

	/**
	 * M�thode permettant la s�lection de carte
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
