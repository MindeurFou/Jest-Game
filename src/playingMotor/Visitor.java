package playingMotor;

/**
 * Classe Visitor de Java adapt�e � notre projet
 */
public abstract class Visitor {

	/**
	 * Attribut priv� repr�sentant une carte (de type Card {@link Card})
	 */
	protected Card card;
	
	/**
	 * Attribut priv� repr�sentant un joueur (de type Player {@link Player})
	 */
	protected Player player;
	
	/**
	 * Attribut priv� repr�sentant une partie (de type Game {@link Game})
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
	 *M�thode permettant de visiter une partie
	 *
	 *@param game partie � visiter (de type Game {@link Game})
	 */
	public void visit(Game game) {
		this.game = game;
	}
	
	/**
	 *M�thode permettant de visiter un joueur
	 *
	 *@param gplayer joueur � visiter (de type Player {@link Player})
	 */
	public void visit(Player player) {
		this.player = player;
	}
	
	/**
	 *M�thode permettant de visiter une carte
	 *
	 *@param card carte � visiter (de type Card {@link Card})
	 */
	public void visit(Card card) {
		this.card = card;
	}

}
