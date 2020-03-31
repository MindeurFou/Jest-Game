package playingMotor;
import java.util.Iterator;

/**
 * Classe de calcul des scores
 */
public class ScoreVisitor1 extends Visitor implements ScoreVisitor {

	/**
	 * Constructeur de la classe
	 */
	public ScoreVisitor1() {
		super();
	}
	
	/**
	 * Méthode de calcul des scores
	 * 
	 * @param mage partie dont il faut calculer le score
	 */
	public void calculateScore(Game game) {
		game.accept(this);
		int score = 0;
		
		
		Iterator<Player> it = this.game.getPlayers().iterator();
		
		while(it.hasNext()) { //boucle sur les joueurs
			
			it.next().accept(this);		
			StackOfCards jest = this.player.getJest();	
			score = 0;
			
			Iterator<Card> itCard = jest.getCardList().iterator();
			
			while(itCard.hasNext()) { //boucle sur les cartes
				itCard.next().accept(this);

				if (this.card.color == Color.SPADE || this.card.color == Color.CLUB) {

					if (this.card.color == Color.SPADE && jest.contains(this.card.value, Color.CLUB)) {
						score += 1;
					} else if (this.card.color == Color.CLUB && jest.contains(this.card.value, Color.SPADE)) {
						score += 1;
					}
					score += this.aceCheck(jest);
					
				} else if (this.card.color == Color.DIAMOND) {
					score -= this.aceCheck(jest);
					
				} else if (this.card.color == Color.HEART) {
					if (jest.contains(Value.ACE, Color.HEART) && jest.contains(Value.TWO, Color.HEART)
							&& jest.contains(Value.TREE, Color.HEART) && jest.contains(Value.FOUR, Color.HEART)
							&& jest.contains(null, null)) {
						score += this.card.value.ordinal()+1; // pas besoin de faire aceCheck, si on passe la condition c'est
														// qu'il y a plusieurs cartes de la même couleur
					} else if (!jest.contains(null,null)){ // si le joueur n'a pas le joker
						// la carte vaut 0
					} else {
						score -= this.aceCheck(jest);
					}

				} else { // si la carte est le joker

					if (!jest.contains(Value.ACE, Color.HEART) && !jest.contains(Value.TWO, Color.HEART)
							&& !jest.contains(Value.TREE, Color.HEART) && !jest.contains(Value.FOUR, Color.HEART)) {
						score += 4;
					}
				}

			}
			
			this.player.setScore(score);

		}

	}
	
	/**
	 * Méthode permettant de vérifier la présence d'un as
	 * 
	 *@param cards cartes parmis lesquelles chercher un as (de type StackOfCards {@link StackOfCards})
	 *
	 *@return nombre d'as trouvé (de type int)
	 */
	public int aceCheck(StackOfCards cards) {
		if (this.card.value == Value.ACE) {
			if (cards.contains(Value.FOUR, this.card.color) || cards.contains(Value.TREE, this.card.color)
					|| cards.contains(Value.TWO, this.card.color)) {
				return 1;
			} else {
				return 5;
			}
		} else {
			return this.card.value.ordinal()+1;
		}
	}

}