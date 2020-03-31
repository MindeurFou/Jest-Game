package playingMotor;
import java.util.Scanner;

/**
 * Classe de strat�gie d'offre
 */
public class MakingOfferStrategy0 implements MakingOfferStrategy {
	
	/**
	 * M�thode permettant de faire les offres
	 */
	public void makingOffer(Player player , Scanner scanner) {
					
		player.notifyObservers(this);

		
		while(player.isMakingOffer()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	


}
