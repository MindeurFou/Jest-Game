package playingMotor;

/**
 * Interface des calculs de scores
 */
public interface ScoreVisitor {
	
	/**
	 * Méthode de calcul des scores
	 * 
	 * @param mage partie dont il faut calculer le score
	 */
	public void calculateScore(Game game);
	
}
