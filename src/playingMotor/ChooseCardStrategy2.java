package playingMotor;
import java.util.Scanner;

/**
 * Interface des strat�gies de s�lection de carte
 */
public class ChooseCardStrategy2 implements ChooseCardStrategy {

	/**
	 * M�thode permettant la s�lection de carte
	 */
	public Card chooseCard(Player chosenPlayer, Scanner scanner) {
		
		int numberOfCards = chosenPlayer.getOffer().getCardList().size() ;
		double rand = Math.random() ;
		
		for (int compteur = 1 ; compteur <= numberOfCards ; compteur ++) {
			if (rand < compteur/numberOfCards) {
				return chosenPlayer.getOffer().getCard(compteur);
			}
		}
		return null;
	}

}
