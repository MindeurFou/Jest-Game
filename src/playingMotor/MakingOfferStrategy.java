package playingMotor;
import java.util.Scanner;
import java.util.Observer;

/**
 * Interface des stratégies d'offre
 */
public interface MakingOfferStrategy {
	
	/**
	 * Méthode permettant de faire les offres
	 */
	public void makingOffer (Player player , Scanner scanner);
	

}
