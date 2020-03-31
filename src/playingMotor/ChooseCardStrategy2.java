package playingMotor;
import java.util.Scanner;

/**
 * Interface des stratégies de sélection de carte
 */
public class ChooseCardStrategy2 implements ChooseCardStrategy {

	/**
	 * Méthode permettant la sélection de carte
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
