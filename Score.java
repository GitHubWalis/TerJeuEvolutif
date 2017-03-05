package moteur;

public class Score{   // utilisation du patron singleton pour modéliser le score. Le score est mis à jour à chaque fois qu'un alignement est détecté
    	private int nbPointScore;
    	private static Score instance = new Score();
    	private Score(){	
    	}
    	public void incrementScore(int un){
    		nbPointScore = nbPointScore + un;
    	}
    	public static Score getScore() {
    		return instance;
    	}
    	public int afficherScore(){
    		return nbPointScore;
    	}
}


