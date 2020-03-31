package playingMotor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe de strat�gie de s�lection de joueur
 */
public class ChoosePlayerStrategy1 implements ChoosePlayerStrategy {

	/**
	 * M�thode permettant la s�lection de joueur
	 */
	public void choosePlayer(ArrayList<Player> players, Player currentPlayer , boolean canSelfDraw, Scanner scanner) {
		
		Player chosenPlayer = null ;
		
		if (canSelfDraw) {
			chosenPlayer = players.get(0);
		}
		else {
			if (currentPlayer.getName() != players.get(0).getName()) {
				chosenPlayer = players.get(0) ;
			}
			else {
				chosenPlayer = players.get(1) ;
			}
			
		}
		
		game.setChosenPlayer(chosenPlayer);							
		currentPlayer.setIsChoosingPlayer(false);
	}
	
	
}
