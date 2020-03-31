package playingMotor;

/**
 * Enumération des valeurs potentielles des cartes du jeu.
 */
public enum Value {
	ACE , TWO , TREE , FOUR;
	
	/**
	 * Méthode qui renvoie une valeur au hasard parmis toutes les valeurs possibles.
	 * 
	 * @return valeur séléctionnée aléatoirement de façon équiprobable.
	 */
	public static Value getRandomValue() {
		
		double valueChoice = Math.random();
		Value v;
		
		if( valueChoice <= 0.25) {
			v = Value.ACE;
		}else if( 0.25 < valueChoice && valueChoice <= 0.5){
			v = Value.FOUR;
		}else if( 0.5 < valueChoice && valueChoice <= 0.75){
			v = Value.TREE;
		}else {
			v = Value.TWO;
		}
		
		return v;
	}
}