package moteur;

public class Score{   // utilisation du patron singleton pour mod�liser le score. Le score est mis � jour � chaque fois qu'un alignement est d�tect�
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


