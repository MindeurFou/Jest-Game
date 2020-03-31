package playingMotor;
import java.util.Scanner;

/**
 * Classe de stratégie d'offre
 */
public class MakingOfferStrategy1 implements MakingOfferStrategy {

	/**
	 * Méthode permettant de faire les offres
	 */
	public void makingOffer (Player player, Scanner scanner) {
		
		
		Card chosenCard = player.getHand().getCard(0);
		player.putCardsIntoOffer(chosenCard);

	}

}
