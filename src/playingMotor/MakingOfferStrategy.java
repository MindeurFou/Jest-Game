package playingMotor;
import java.util.Scanner;
import java.util.Observer;

/**
 * Interface des strat�gies d'offre
 */
public interface MakingOfferStrategy {
	
	/**
	 * M�thode permettant de faire les offres
	 */
	public void makingOffer (Player player , Scanner scanner);
	

}
