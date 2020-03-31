package playingMotor;

/**
 * Enum�ration des couleurs potentielles des cartes du jeu.
 */
public enum Color {
	HEART , DIAMOND , CLUB , SPADE;
	
	/**
	 * M�thode qui renvoie une couleur au hasard parmis toutes les couleurs possibles.
	 * 
	 * @return couleur s�l�ctionn�e al�atoirement de fa�on �quiprobable.
	 */
	public static Color getRandomColor() {
		
		double colorChoice = Math.random();
		Color lc;
		
		if( colorChoice <= 0.25) {
			lc = Color.CLUB;
		}else if( 0.25 < colorChoice && colorChoice <= 0.5){
			lc = Color.DIAMOND;
		}else if( 0.5 < colorChoice && colorChoice <= 0.75){
			lc = Color.HEART;
		}else {
			lc = Color.SPADE;
		}
		
		return lc;
	}
}