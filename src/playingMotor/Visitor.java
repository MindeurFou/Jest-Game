package playingMotor;

/**
 * Classe Visitor de Java adaptée à notre projet
 */
public abstract class Visitor {

	/**
	 * Attribut privé représentant une carte (de type Card {@link Card})
	 */
	protected Card card;
	
	/**
	 * Attribut privé représentant un joueur (de type Player {@link Player})
	 */
	protected Player player;
	
	/**
	 * Attribut privé représentant une partie (de type Game {@link Game})
	 */
	protected Game game;
	
	/**
	 * Constructeur de la classe
	 */
	public void Visitor() {
		this.game = null;
		this.player = null;
		this.card = null;
	}

	/**
	 *Méthode permettant de visiter une partie
	 *
	 *@param game partie à visiter (de type Game {@link Game})
	 */
	public void visit(Game game) {
		this.game = game;
	}
	
	/**
	 *Méthode permettant de visiter un joueur
	 *
	 *@param gplayer joueur à visiter (de type Player {@link Player})
	 */
	public void visit(Player player) {
		this.player = player;
	}
	
	/**
	 *Méthode permettant de visiter une carte
	 *
	 *@param card carte à visiter (de type Card {@link Card})
	 */
	public void visit(Card card) {
		this.card = card;
	}

}
