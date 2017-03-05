package moteur;

public class Pierres {
	public int type;
	  public int niveau;
	 
	   /* Crée une Pierres aléatoire */
	   public Pierres(){
	     this(((int) (Math.random()*6))+1 , ((int) (Math.random()*3))+1);
	   }
	   /* Crée une Pierres de type et niveau précisés */
	   public Pierres(int type, int niveau){
	     this.type=type;
	     this.niveau = niveau;
	   }
	   public int getType(){ return type; }
	   public int getNiveau(){ return niveau; }
}

