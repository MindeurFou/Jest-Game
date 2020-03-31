package playingMotor;


/**
 * Classe d'exceptions ayant lieux lorsqu'un joueur entre un champ non valide dans l'invite de commande.
 */
public class WrongInputException extends Exception{
	
	/**
	 * Attribut priv� permettant � Deserialization de ne pas jeter d'exception
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Remont�e du message d'erreur
	 */
	
	public WrongInputException(String message) {
		super(message);
		System.out.println(message);
	}
}
