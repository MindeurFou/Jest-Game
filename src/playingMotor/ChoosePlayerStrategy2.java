package playingMotor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe de stratégie de sélection de joueur
 */
public class ChoosePlayerStrategy2 implements ChoosePlayerStrategy {

	/**
	 * Méthode permettant la sélection de joueur
	 */
	public Player choosePlayer(ArrayList<Player> players, Player currentPlayer, boolean canSelfDraw, Scanner scanner) {

		
		int numberOfCards = players.size() ;
		double rand = Math.random() ;
		
		for (int compteur = 1 ; compteur <= numberOfCards ; compteur ++) {
			if (rand <= compteur/numberOfCards) {
				return players.get(compteur-1);
			}
		}
		return null;
	}

}
